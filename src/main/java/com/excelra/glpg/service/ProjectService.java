package com.excelra.glpg.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excelra.glpg.comparator.DateOfCompletionComparator;
import com.excelra.glpg.entity.AreaMaster;
import com.excelra.glpg.entity.Constants;
import com.excelra.glpg.entity.EmployeeMaster;
import com.excelra.glpg.entity.PersonLeadStakeholder;
import com.excelra.glpg.entity.PrioritizationMaster;
import com.excelra.glpg.entity.ProjectDescription;
import com.excelra.glpg.entity.ProjectStatusDetail;
import com.excelra.glpg.entity.ResourceAllocation;
import com.excelra.glpg.entity.ResourceDetails;
import com.excelra.glpg.entity.RiskMaster;
import com.excelra.glpg.entity.SponsorStakeholderDetails;
import com.excelra.glpg.entity.VendorDetails;
import com.excelra.glpg.exception.ServiceException;
import com.excelra.glpg.model.AreaMasterDTO;
import com.excelra.glpg.model.CreateProjectDTO;
import com.excelra.glpg.model.LeadDto;
import com.excelra.glpg.model.PrioritizationMasterDTO;
import com.excelra.glpg.model.ProjectEditMainDTO;
import com.excelra.glpg.model.ProjectMasterDTO;
import com.excelra.glpg.model.ProjectMastersData;
import com.excelra.glpg.model.RiskMasterDTO;
import com.excelra.glpg.model.SearchInputs;
import com.excelra.glpg.model.SearchOrderInputs;
import com.excelra.glpg.repository.AreaMasterRepository;
import com.excelra.glpg.repository.EmployeeMasterRepository;
import com.excelra.glpg.repository.PersonLeadStakeholderRepository;
import com.excelra.glpg.repository.PrioritizationMasterRepository;
import com.excelra.glpg.repository.ProjectDescriptionRepository;
import com.excelra.glpg.repository.ProjectStatusDetailRepository;
import com.excelra.glpg.repository.ResourceAllocationRepository;
import com.excelra.glpg.repository.ResourceDetailsRepository;
import com.excelra.glpg.repository.RiskMasterRepository;
import com.excelra.glpg.repository.SponsorStakeholderDetailsRepository;
import com.excelra.glpg.repository.VendorDetailsRepository;
import com.excelra.glpg.utils.Mail;

@Service
public class ProjectService implements IProjectService {
    
    private Logger log = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    ProjectStatusDetailRepository projectStatusDetailRepository;

    @Autowired
    ProjectDescriptionRepository projectDescriptionRepository;

    @Autowired
    AreaMasterRepository areaMasterRepository;

    @Autowired
    PersonLeadStakeholderRepository personLeadStakeholderRepository;

    @Autowired
    EmployeeMasterRepository employeeMasterRepository;

    @Autowired
    SponsorStakeholderDetailsRepository sponsorStakeholderDetailsRepository;
    
    @Autowired
    ResourceAllocationRepository resourceAllocationRepository;
    
    @Autowired
    ResourceDetailsRepository resourceDetailsRepository;
    
    @Autowired
    VendorDetailsRepository vendorDetailsRepository;
    
    @Autowired
    RiskMasterRepository riskMasterRepository;
    
    @Autowired
    PrioritizationMasterRepository prioritizationMasterRepository;

	
	@Override
	public ProjectMastersData getAllProjectsSortBy(SearchOrderInputs searchOrderInputs) {

		SearchInputs searchInputs = new SearchInputs();
		searchInputs.setLimit(searchOrderInputs.getLimit());
		searchInputs.setPageNo(searchOrderInputs.getPageNo());
		ProjectMastersData projectMastersData = new ProjectMastersData();
		Pageable pageable;
		pageable = PageRequest.of(searchInputs.getPageNo() - 1, searchInputs.getLimit());

		if (Objects.nonNull(searchOrderInputs.getSearchBy()) && Objects.nonNull(searchOrderInputs.getSearchName())) {

			log.info("getting all projects data based on quick search");
			List<ProjectMasterDTO> projectMasterList = new ArrayList<>();
			List<String> glpgProjectUids = new ArrayList<>();
			if (searchOrderInputs.getSearchBy().equalsIgnoreCase(Constants.GLPG_Project_Id)) {

				List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository
						.getByGlpgProjectUid(searchOrderInputs.getSearchName().toUpperCase());
				for (ProjectStatusDetail psd : projectStatusDetails) {
					glpgProjectUids.add(psd.getuId());
				}
				log.info("set of glpgProjectUids by searchName  {}", glpgProjectUids.size());

			} else if (searchOrderInputs.getSearchBy().equalsIgnoreCase(Constants.Project_Uid)) {

				List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository
						.getByProjectUid(searchOrderInputs.getSearchName().toUpperCase());
				for (ProjectStatusDetail psd : projectStatusDetails) {
					glpgProjectUids.add(psd.getuId());
				}
				log.info("set of projectUids by searchName  {}", glpgProjectUids.size());

			} else if (searchOrderInputs.getSearchBy().equalsIgnoreCase(Constants.AREA)) {

				List<String> areaIds = new ArrayList<>();
				List<AreaMaster> areaMasters = areaMasterRepository
						.findByAreaContainingIgnoreCase(searchOrderInputs.getSearchName().toUpperCase());
				for (AreaMaster am : areaMasters) {
					areaIds.add(am.getAreaId());
				}
				if(areaIds.isEmpty())
					throw new ServiceException("no records/projects exits with this employee Id , try with another employee Id");
				
				List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository.getUidsByArea(areaIds);

				for (ProjectStatusDetail psd : projectStatusDetails) {
					glpgProjectUids.add(psd.getuId());
				}
				log.info("set of areaProjectUids by searchName  {}", glpgProjectUids.size());

			} else if (searchOrderInputs.getSearchBy().equalsIgnoreCase(Constants.TITLE)) {

				List<ProjectDescription> projectDescriptions = projectDescriptionRepository
						.findByTitleContainingIgnoreCase(searchOrderInputs.getSearchName().toUpperCase());
				for (ProjectDescription pd : projectDescriptions) {
					glpgProjectUids.add(pd.getuId());
				}
				log.info("set of titleProjectUids by searchName  {}", glpgProjectUids.size());
			} else if (searchOrderInputs.getSearchBy().equalsIgnoreCase(Constants.GLPG_Compound_Id)) {

				List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository
						.findByGlpgCompoundIdsContainingIgnoreCase(searchOrderInputs.getSearchName().toUpperCase());
				for (ProjectStatusDetail psd : projectStatusDetails) {
					glpgProjectUids.add(psd.getuId());
				}
				log.info("set of glpgCompoundUids by searchName  {}", glpgProjectUids.size());
			} else if (searchOrderInputs.getSearchBy().equalsIgnoreCase(Constants.EMPLOYEE_ID)) {

				Set<String> employeeIds = new HashSet<>(); 
				List<SponsorStakeholderDetails> sponsorStakeholderDetails = sponsorStakeholderDetailsRepository
						.getAllBySponsorsOrStakeHolders(searchOrderInputs.getSearchName().toUpperCase());
				List<PersonLeadStakeholder> personLeadStakeHolders = personLeadStakeholderRepository
						.getAllLeads(searchOrderInputs.getSearchName().toUpperCase());
				List<ResourceDetails> resourceDetails = resourceDetailsRepository.getAllResources(searchOrderInputs.getSearchName().toUpperCase());
				log.info("list of sponsors and stakeholders {}", sponsorStakeholderDetails.size());
				log.info("list of leads  {}", personLeadStakeHolders.size());
				log.info("list of resource details  {}", resourceDetails.size());
				
				for (SponsorStakeholderDetails sshd : sponsorStakeholderDetails) {
					employeeIds.add(sshd.getuId());
				}
				for (PersonLeadStakeholder plsh : personLeadStakeHolders) {
					employeeIds.add(plsh.getuId());
				}
				for (ResourceDetails rd : resourceDetails) {
					employeeIds.add(rd.getuId());
				}
				glpgProjectUids.addAll(employeeIds);
				log.info("set of employee related projectIds  by searchName  {}", glpgProjectUids.size());
			}
			if(glpgProjectUids.isEmpty())
				throw new ServiceException("no records/projects exits with this employee Id , try with another employee Id");
			log.info("set of projectUids  {}", glpgProjectUids.size());
			List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository.getAllByUIdInAndStatusNot(glpgProjectUids,Constants.PROJECT_DELETE);
			log.info("count of project status details without pagination {}", projectStatusDetails.size());
			int count = 0;
			if (!projectStatusDetails.isEmpty()) {
				count = projectStatusDetails.size();
			}
			projectMastersData.setRecordsCount(count);
			glpgProjectUids  = new ArrayList<>();
			for( ProjectStatusDetail psd :projectStatusDetails)
			{
				glpgProjectUids.add(psd.getuId());
			}
			
			projectStatusDetails = projectStatusDetailRepository.findByUIdIn(glpgProjectUids, pageable);
			glpgProjectUids  = new ArrayList<>();
			for( ProjectStatusDetail psd :projectStatusDetails)
			{
				glpgProjectUids.add(psd.getuId());
			}
			log.info("list of projectStatusDetails  {}", projectStatusDetails.size());
			
			log.info("list of projectMasters size  {}", projectStatusDetails.size());
			projectMastersData.setProjectMasterDTOs(sortProjectsData(searchOrderInputs.getColumnName(), searchOrderInputs.getOrder(),glpgProjectUids));
		}

		else if (Objects.nonNull(searchOrderInputs.getAdvanceSearch())) {

			List<ProjectMasterDTO> projectMasterList = new ArrayList<>();
			log.info("getting all projects data based on advance search");

			Set<String> projectUids = new HashSet<>();
			List<String> glpgProjectUids = new ArrayList<>();
			List<String> areaProjectUids = new ArrayList<>();
			List<String> startDateProjectUids = new ArrayList<>();
			Set<String> leadIds = new HashSet<>();
			Set<String> personIntakeIds = new HashSet<>();

			if (Objects.nonNull(searchOrderInputs.getAdvanceSearch().getGlpgProjectId())) {

				log.info("advance search on glpgProjectId");

				List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository
						.getByGlpgProjectUid(searchOrderInputs.getAdvanceSearch().getGlpgProjectId().toUpperCase());
				for (ProjectStatusDetail psd : projectStatusDetails) {
					glpgProjectUids.add(psd.getuId());
					projectUids.add(psd.getuId());
				}
				log.info("list of glpgProjectUids size  {}", glpgProjectUids.size());
			}
			if (Objects.nonNull(searchOrderInputs.getAdvanceSearch().getIntakePersonId())) {
				log.info("advance search on intakepersonId");
                
				List<PersonLeadStakeholder> personLeadStakeholders = personLeadStakeholderRepository
						.getPersonIntakeId(searchOrderInputs.getAdvanceSearch().getIntakePersonId().toUpperCase());
				List<ResourceDetails> resourceDetails = resourceDetailsRepository.getAllResources(searchOrderInputs.getAdvanceSearch().getIntakePersonId());
				for (PersonLeadStakeholder plsh : personLeadStakeholders) {
					personIntakeIds.add(plsh.getuId());
				}
				for (ResourceDetails rd : resourceDetails) {
					personIntakeIds.add(rd.getuId());
				}
				projectUids.addAll(personIntakeIds);
				
				log.info("list of intakepersonId size  {}", projectUids.size());
			}
			if (Objects.nonNull(searchOrderInputs.getAdvanceSearch().getLeadId())) {
				log.info("advance search on leadId");

				
				List<PersonLeadStakeholder> personLeadStakeholders = personLeadStakeholderRepository
						.getLeadId(searchOrderInputs.getAdvanceSearch().getLeadId().toUpperCase());
				List<ResourceDetails> resourceDetails = resourceDetailsRepository.getAllResources(searchOrderInputs.getAdvanceSearch().getIntakePersonId());
				for (PersonLeadStakeholder plsh : personLeadStakeholders) {
					leadIds.add(plsh.getuId());
				}
				for (ResourceDetails rd : resourceDetails) {
					leadIds.add(rd.getuId());
				}
				projectUids.addAll(leadIds);
			
				log.info("list of leadId size  {}", projectUids.size());
			}
			if (Objects.nonNull(searchOrderInputs.getAdvanceSearch().getAreaId())) {

				log.info("advance search on areaId");

				List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository
						.getAreaId(searchOrderInputs.getAdvanceSearch().getAreaId().toUpperCase());
				for (ProjectStatusDetail psd : projectStatusDetails) {
					areaProjectUids.add(psd.getuId());
					projectUids.add(psd.getuId());
				}
				log.info("list of areas size  {}", areaProjectUids.size());
			}
			if (Objects.nonNull(searchOrderInputs.getAdvanceSearch().getFromDate())
					&& Objects.nonNull(searchOrderInputs.getAdvanceSearch().getToDate())) {

				log.info("advance search on startDate and endDate");

				List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository.getUidsByStartDate(
						searchOrderInputs.getAdvanceSearch().getFromDate(),
						searchOrderInputs.getAdvanceSearch().getToDate());
				for (ProjectStatusDetail pd : projectStatusDetails) {
					startDateProjectUids.add(pd.getuId());
					projectUids.add(pd.getuId());
				}
				log.info("list of startDateProjectUids size  {}", startDateProjectUids.size());
			}
			if (!glpgProjectUids.isEmpty())
				projectUids.retainAll(glpgProjectUids);
			if (!personIntakeIds.isEmpty())
				projectUids.retainAll(personIntakeIds);
			if (!leadIds.isEmpty())
				projectUids.retainAll(leadIds);
			if (!areaProjectUids.isEmpty())
				projectUids.retainAll(areaProjectUids);
			if (!startDateProjectUids.isEmpty())
				projectUids.retainAll(startDateProjectUids);
			log.info("set of projectUids  {}", projectUids.size());
			if(projectUids.isEmpty())
				throw new ServiceException("no records/projects exits with this search , try with another inputs");
			log.info("set of employee related projectIds  by searchName  {}", glpgProjectUids.size());
			List<String> uIds = new ArrayList<>();
			uIds.addAll(projectUids);
			List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository.getAllByUIdInAndStatusNot(uIds,Constants.PROJECT_DELETE);
			log.info("count of project status details without pagination {}", projectStatusDetails.size());
			int count = 0;
			if (!projectStatusDetails.isEmpty()) {
				count = projectStatusDetails.size();
			}
			projectMastersData.setRecordsCount(count);
			uIds = new ArrayList<>();
			for(ProjectStatusDetail psd : projectStatusDetails)
			{
				uIds.add(psd.getuId());
			}
			projectStatusDetails = projectStatusDetailRepository.findByUIdIn(uIds,pageable);
			uIds = new ArrayList<>();
			for(ProjectStatusDetail psd : projectStatusDetails)
			{
				uIds.add(psd.getuId());
			}
			log.info("list of projectStatusDetails  {}", projectStatusDetails.size());
			log.info("list of projectMasters size  {}", projectStatusDetails.size());
			projectMastersData.setProjectMasterDTOs(sortProjectsData(searchOrderInputs.getColumnName(), searchOrderInputs.getOrder(),uIds));
		}

		else {
			List<ProjectStatusDetail> projectStatusDetails = projectStatusDetailRepository
					.getAllByStatusNot(Constants.PROJECT_DELETE);
			List<String> uids = new ArrayList<>();
			for (ProjectStatusDetail psd : projectStatusDetails) {
				uids.add(psd.getuId());
			}
			projectMastersData.setRecordsCount(projectStatusDetails.size());
			projectStatusDetails = projectStatusDetailRepository.findByUIdIn(uids, pageable);
			uids = new ArrayList<>();
			for (ProjectStatusDetail psd : projectStatusDetails) {
				uids.add(psd.getuId());
			}
			log.info("list of project masters without search {}", projectStatusDetails.size());
			projectMastersData.setProjectMasterDTOs(
					sortProjectsData(searchOrderInputs.getColumnName(), searchOrderInputs.getOrder(), uids));
		}

		return projectMastersData;
	}

	public List<ProjectMasterDTO> sortProjectsData(String columnName, String order,
			List<String> projectUids) {

		log.info("get all projects sort by");
		
		List<ProjectStatusDetail> projectStatusDetails = null;
		List<ProjectMasterDTO> projectMasterDTOs =null;
		

		if ((columnName.equalsIgnoreCase(Constants.column_ProjectUid))) {

			log.info("column param is-----{}", columnName);

			if (order.equalsIgnoreCase(Constants.order_asc)) {
				log.info("asc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.findByUIdInOrderByUIdAsc(projectUids);
			} else {
				log.info("desc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.findByUIdInOrderByUIdDesc(projectUids);
			}
		} else if ((columnName.equalsIgnoreCase(Constants.column_status))) {

			log.info("column param is-----{}", columnName);

			if (order.equalsIgnoreCase(Constants.order_asc)) {
				log.info("asc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getUIdInOrderByStatusAsc(projectUids);
			} else {
				log.info("desc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getUIdInOrderByStatusDesc(projectUids);
			}
		} else if ((columnName.equalsIgnoreCase(Constants.column_title))) {
			log.info("column param is-----{}", columnName);

			if (order.equalsIgnoreCase(Constants.order_asc)) {
				log.info("asc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getByUIdInOrderByTitleAsc(projectUids);
			} else {
				log.info("desc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getByUIdInOrderByTitleDesc(projectUids);
			}
			System.out.println("================="+projectStatusDetails.size());
		} else if ((columnName.equalsIgnoreCase(Constants.column_glpgProjectId))) {

			log.info("column param is-----{}", columnName);

			if (order.equalsIgnoreCase(Constants.order_asc)) {
				log.info("asc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.findByUIdInOrderByGlpgProjectIdAsc(projectUids);
			} else {
				log.info("desc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.findByUIdInOrderByGlpgProjectIdDesc(projectUids);
			}
		} else if ((columnName.equalsIgnoreCase(Constants.column_lead))) {

			log.info("column param is-----{}", columnName);

			if (order.equalsIgnoreCase(Constants.order_asc)) {
				log.info("asc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getByUIdInOrderByleadAsc(projectUids);
			} else {
				log.info("desc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getByUIdInOrderByLeadDesc(projectUids);
			}
		} else if ((columnName.equalsIgnoreCase(Constants.column_startDate))) {

			log.info("column param is-----{}", columnName);

			if (order.equalsIgnoreCase(Constants.order_asc)) {
				log.info("asc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.findByUIdInOrderByStartDateAsc(projectUids);
			} else {
				log.info("desc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.findByUIdInOrderByStartDateDesc(projectUids);
			}
		} else if ((columnName.equalsIgnoreCase(Constants.column_area))) {

			log.info("column param is-----{}", columnName);
			if (order.equalsIgnoreCase(Constants.order_asc)) {
				log.info("asc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getByUIdInOrderByAreaAsc(projectUids);
			} else {
				log.info("desc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getByUIdInOrderByAreaDesc(projectUids);
			}
		} else if ((columnName.equalsIgnoreCase(Constants.column_sponsors))) {

			log.info("column param is-----{}", columnName);
			if (order.equalsIgnoreCase(Constants.order_asc)) {
				log.info("asc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getByUIdInOrderBySponsorsAsc(projectUids);
			} else {
				log.info("desc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getByUIdInOrderBySponsorsDesc(projectUids);
			}
		}else if ((columnName.equalsIgnoreCase(Constants.column_stakeHolders))) {

			log.info("column param is-----{}", columnName);
			if (order.equalsIgnoreCase(Constants.order_asc)) {
				log.info("asc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getByUIdInOrderByStakeHoldersAsc(projectUids);
			} else {
				log.info("desc order is-----{}", order);
				projectStatusDetails = projectStatusDetailRepository.getByUIdInOrderByStakeHoldersDesc(projectUids);
			}
		} else if ((columnName.equalsIgnoreCase(Constants.column_CompletionDate))) {

			log.info("column param is-----{}", columnName);
			projectStatusDetails = projectStatusDetailRepository.findByUIdIn(projectUids);
			projectMasterDTOs = getAllProjectsData(projectStatusDetails);
			if (order.equalsIgnoreCase(Constants.order_asc))
				Collections.sort(projectMasterDTOs, new DateOfCompletionComparator());
			else
				Collections.sort(projectMasterDTOs, new DateOfCompletionComparator().reversed());
			
			return projectMasterDTOs;
		}
		projectMasterDTOs = getAllProjectsData(projectStatusDetails);
		return projectMasterDTOs;
	}

	@Override
	public String createProjectData(CreateProjectDTO createProjectDTO) {

		log.info("save all projects data: {}");
		String msg = null;
		ProjectStatusDetail projectStatusDetail = new ProjectStatusDetail();
		ProjectDescription projectDescription = new ProjectDescription();
		AreaMaster areaMasterData = areaMasterRepository.findByArea(createProjectDTO.getArea());
		String areaId = areaMasterData.getAreaId();
		ProjectStatusDetail projectStatusDetailsUid = projectStatusDetailRepository
				.findTopByUIdContainingOrderByUIdDesc(areaId);
		log.info("uid---------->{}", projectStatusDetailsUid.getuId());
		String[] Uid = projectStatusDetailsUid.getuId().split("_");
		Integer id = new Integer(Uid[1]);
		int i = id + 1;
		String formattedStr = String.format("%04d", i);
		String newUId = areaId + "_" + formattedStr;
		log.info("newUid---------->{}", newUId);
		projectStatusDetail.setuId(newUId);
		projectStatusDetail.setGlpgProjectId(createProjectDTO.getGlpgProjectId().toUpperCase());
		projectStatusDetail.setAreaId(areaId);
		projectStatusDetail.setStartDate(new Timestamp(createProjectDTO.getStartDate().getTime()));
		projectStatusDetail.setIsDevelopment(createProjectDTO.getIsDevelopment());
		projectDescription.setuId(newUId);
		projectDescription.setTitle(createProjectDTO.getTitle());
		projectDescription.setGoals(createProjectDTO.getGoals());
		projectDescription.setBusinessValue(createProjectDTO.getBusinessValue());
		projectDescription.setComments(createProjectDTO.getComments());
		projectDescription.setDescription(createProjectDTO.getDescription());
		projectDescription.setDeliverables(createProjectDTO.getDeliverables());
		projectStatusDetailRepository.save(projectStatusDetail);
		projectDescriptionRepository.save(projectDescription);
		ProjectStatusDetail projectNewUidTest = projectStatusDetailRepository.findAllByUId(newUId);
		if (Objects.nonNull(projectNewUidTest)) {
			msg = "saved succesfully";

			ResourceBundle resourceBundle = ResourceBundle.getBundle("mails");
			String mailTo = resourceBundle.getString("mail.save.email.to");
			String mailCc = resourceBundle.getString("mail.save.email.cc");
			String subject = resourceBundle.getString("mail.save.email.subject");
			String bodydesc = resourceBundle.getString("mail.save.email.desc.body");
			String projbody = resourceBundle.getString("mail.save.email.proj.body");
			bodydesc = bodydesc.replace("projecttitle", createProjectDTO.getTitle());
			String projBodyUid = projbody.replace("projectuid", newUId);
			String projBodyTitle = projBodyUid.replace("projecttitle", createProjectDTO.getTitle());
			String projBodyStartDate = projBodyTitle.replace("projectstartdate",
					createProjectDTO.getStartDate().toString());
			String projBodyArea = projBodyStartDate.replace("projectarea", createProjectDTO.getArea());
			String projBodyDesc = projBodyArea.replace("projectdescription", createProjectDTO.getDescription());
			String[] recepients = mailTo.split(",");
			String[] recepientsCc = mailCc.split(",");

			Mail mail = new Mail();
			mail.sendMail(recepients, recepientsCc, subject, bodydesc + projBodyDesc);
		} else {
			msg = " not saved";
		}
		return msg;
	}

	@Override
	public List<String> getAllAreaData() {
		List<AreaMaster> areaMasterList = areaMasterRepository.findAll();
		List<String> area = new ArrayList<>();
		for (AreaMaster areaMaster : areaMasterList) {
			area.add(areaMaster.getArea());
		}
		return area;
	}

	@Override
	public List<ProjectMasterDTO> getAllProjectsData(List<ProjectStatusDetail> projectStatusDetails) {

		List<ProjectMasterDTO> projectMasterDTOs = new ArrayList<>();
		
		
		for (ProjectStatusDetail psd : projectStatusDetails) {

			if (Objects.nonNull(psd.getuId())) {

				ProjectMasterDTO projectMasterDTO = new ProjectMasterDTO();
				projectMasterDTO.setuId(psd.getuId());
				projectMasterDTO.setGlpgProjectId(psd.getGlpgProjectId());
				projectMasterDTO.setGlpgCompoundId(psd.getGlpgCompoundIds());
				projectMasterDTO.setStatus(psd.getStatus());
				projectMasterDTO.setStartDate(psd.getStartDate());
				projectMasterDTO.setIsDevelopment(psd.getIsDevelopment());
				projectMasterDTO.setEstimatedDateEnd(psd.getEstimatedDateEnd());
				projectMasterDTO.setActualDateEnd(psd.getActualDateEnd());

				if (Objects.nonNull(psd.getRisksCode())) {
					Optional<RiskMaster> riskMaster = riskMasterRepository.findById(psd.getRisksCode());
					if (riskMaster.isPresent())
					{
						RiskMasterDTO riskMasterDTO = new RiskMasterDTO();
						riskMasterDTO.setRiskCode(riskMaster.get().getRiskCode());
						riskMasterDTO.setRiskDetail(riskMaster.get().getRiskDetail());
						projectMasterDTO.setRisks(riskMasterDTO);
					}	
				}
				if (Objects.nonNull(psd.getTaPrioritizationCode())) {
					Optional<PrioritizationMaster> prioritizationMaster = prioritizationMasterRepository
							.findById(psd.getTaPrioritizationCode());
					if (prioritizationMaster.isPresent())
					{
						PrioritizationMasterDTO prioritizationMasterDTO = new PrioritizationMasterDTO();
						prioritizationMasterDTO.setTaPrioritizationCode(prioritizationMaster.get().getTaPrioritizationCode());
						prioritizationMasterDTO.setTaPrioritizationValue(prioritizationMaster.get().getTaPrioritizationValue());
						projectMasterDTO.setPriority(prioritizationMasterDTO);
					}
				}

				Optional<ProjectDescription> pd = projectDescriptionRepository.findById(psd.getuId());

				if (pd.isPresent()) {
					ProjectDescription projectDescription = pd.get();
					if (Objects.nonNull(projectDescription)) {
						projectMasterDTO.setTitle(projectDescription.getTitle());
						projectMasterDTO.setComments(projectDescription.getComments());
						projectMasterDTO.setInScope(projectDescription.getInScope());
						projectMasterDTO.setOutScope(projectDescription.getOutScope());
						projectMasterDTO.setDeliverables(projectDescription.getDeliverables());
						projectMasterDTO.setDescription(projectDescription.getDescription());
						projectMasterDTO.setBusinessValues(projectDescription.getBusinessValue());
						projectMasterDTO.setGoals(projectDescription.getGoals());
						projectMasterDTO.setMileStones(projectDescription.getMilestones());
					}
				}
				if (Objects.nonNull(psd.getAreaId())) {
					Optional<AreaMaster> am = areaMasterRepository.findById(psd.getAreaId());
					{
						AreaMasterDTO areaMasterDTO = new AreaMasterDTO();
						areaMasterDTO.setArea(am.get().getArea());
						areaMasterDTO.setAreaId(am.get().getAreaId());
						projectMasterDTO.setAreaMasterDTO(areaMasterDTO);
					}
				}
				Optional<ResourceDetails> rd = resourceDetailsRepository.findById(psd.getuId());
				if (rd.isPresent()) {
					ResourceDetails resourceDetails = rd.get();
					if (Objects.nonNull(resourceDetails)) {
						projectMasterDTO.setPersonInTakeRoleStartDate(resourceDetails.getPersonIntakeStartDate());
						projectMasterDTO.setPersonInTakeRoleEndDate(resourceDetails.getPersonIntakeEndDate());

						if (Objects.nonNull(resourceDetails.getManagementId())) {
							Optional<EmployeeMaster> em = employeeMasterRepository
									.findById(resourceDetails.getManagementId());
							if (em.isPresent()) {
								EmployeeMaster employeeMaster = em.get();
								LeadDto leadDto = new LeadDto();
								leadDto.setLeadId(employeeMaster.getEmployeeId());
								leadDto.setLeadName(employeeMaster.getEmployeeAccount());
								projectMasterDTO.setManagement(leadDto);
							}
						}
						projectMasterDTO.setConsulting(resourceDetails.getConsultingId());
					}
				}
				Optional<ResourceAllocation> ra = resourceAllocationRepository.findById(psd.getuId());
				if (ra.isPresent()) {
					ResourceAllocation resourceAllocation = ra.get();
					if (Objects.nonNull(resourceAllocation)) {
						projectMasterDTO.setFteConsulting(resourceAllocation.getResourcesConsulting());
						if(Objects.nonNull(resourceAllocation.getResourcesManagement()))
						projectMasterDTO.setFteManagment(resourceAllocation.getResourcesManagement());
						projectMasterDTO.setFteStartDate(resourceAllocation.getResourcesDateFrom());
						projectMasterDTO.setFteEndDate(resourceAllocation.getResourcesDateTo());
						projectMasterDTO.setLicenseUsed(resourceAllocation.getResourceLicenses());
						if(Objects.nonNull(resourceAllocation.getResourcesLead()))
							projectMasterDTO.setFteIntakePerson(resourceAllocation.getResourcesLead());
					}
				}
				Optional<VendorDetails> vd = vendorDetailsRepository.findById(psd.getuId());
				if (vd.isPresent()) {
					VendorDetails vendorDetails = vd.get();
					if (Objects.nonNull(vendorDetails)) {
						projectMasterDTO.setVendorComments(vendorDetails.getComments());
						projectMasterDTO.setVendorName(vendorDetails.getVendorName());
						projectMasterDTO.setVendorType(vendorDetails.getVendorType());
						projectMasterDTO.setVendorDetails(vendorDetails.getVendorDetails());
					}
				}
				Optional<PersonLeadStakeholder> ps = personLeadStakeholderRepository.findById(psd.getuId());
				if (ps.isPresent()) {
					
					PersonLeadStakeholder personLeadStakeholder = ps.get();
					projectMasterDTO.setLeadRoleStartDate(personLeadStakeholder.getHeirarchyStartDate());
					projectMasterDTO.setLeadRoleendDate(personLeadStakeholder.getHeirarchyEndDate());
					
					String leadId = personLeadStakeholder.getLeadId();
					if (Objects.nonNull(leadId)) {
						Optional<EmployeeMaster> em = employeeMasterRepository.findById(leadId);
						if (em.isPresent()) {
							EmployeeMaster employeeMaster = em.get();
							LeadDto leadDto = new LeadDto();
							leadDto.setLeadId(employeeMaster.getEmployeeId());
							leadDto.setLeadName(employeeMaster.getEmployeeAccount());
							projectMasterDTO.setLead(leadDto);
						}
					}
					 String inTakePersonId = personLeadStakeholder.getPersonIntakeId();
					if (Objects.nonNull(inTakePersonId)) {
						Optional<EmployeeMaster> em = employeeMasterRepository.findById(inTakePersonId);
						if (em.isPresent()) {
							EmployeeMaster employeeMaster = em.get();
							LeadDto leadDto = new LeadDto();
							leadDto.setLeadId(employeeMaster.getEmployeeId());
							leadDto.setLeadName(employeeMaster.getEmployeeAccount());
							projectMasterDTO.setPersonInTake(leadDto);
						}
					}
				}
				if (Objects.nonNull(psd.getStatus())) {
					if (psd.getStatus().equalsIgnoreCase(Constants.PROJECT_STATUS))
						projectMasterDTO.setDateOfCompletion(psd.getActualDateEnd());
					else
						projectMasterDTO.setDateOfCompletion(psd.getEstimatedDateEnd());
				}

				Optional<SponsorStakeholderDetails> sshd = sponsorStakeholderDetailsRepository.findById(psd.getuId());
				if (sshd.isPresent()) {
					SponsorStakeholderDetails sponsorStakeholderDetails = sshd.get();
					if (Objects.nonNull(sponsorStakeholderDetails)) {
						String sponsorId = sponsorStakeholderDetails.getSponsorId();
						String stakeHoldersId = sponsorStakeholderDetails.getStakeholdersId();

						String[] sponsorIds = null;
						List<String> sponsors = new ArrayList<>();
						String[] stakeHoldersIds = null;
						List<String> stakeHolders = new ArrayList<>();

						if (Objects.nonNull(sponsorId)) {

							sponsorIds = sponsorId.split(",");
							if (Objects.nonNull(sponsorIds))
								sponsors = Arrays.asList(sponsorIds);
						}

						if (Objects.nonNull(stakeHoldersId)) {
							stakeHoldersIds = stakeHoldersId.split(",");
							if (Objects.nonNull(stakeHoldersIds))
								stakeHolders = Arrays.asList(stakeHoldersIds);
						}

						List<LeadDto> sponsorNames = new ArrayList<>();
						List<LeadDto> stakeHoldersNames = new ArrayList<>();
						for (String sponsor : sponsors) {

							if (Objects.nonNull(sponsor)) {
								Optional<EmployeeMaster> em = employeeMasterRepository
										.findById(sponsor.replace(" ", ""));
								if (em.isPresent()) {
									LeadDto lead = new LeadDto();
									EmployeeMaster employeeMaster = em.get();
									if (Objects.nonNull(employeeMaster))
									{
										lead.setLeadId(employeeMaster.getEmployeeId());
										lead.setLeadName(employeeMaster.getEmployeeAccount());
									}
										sponsorNames.add(lead);
								}
							}
						}
						for (String stakeHolder : stakeHolders) {

							if (Objects.nonNull(stakeHolder)) {
								Optional<EmployeeMaster> em = employeeMasterRepository
										.findById(stakeHolder.replace(" ", ""));
								if (em.isPresent()) {
									LeadDto lead = new LeadDto();
									EmployeeMaster employeeMaster = em.get();
									if (Objects.nonNull(employeeMaster))
									{
										lead.setLeadId(employeeMaster.getEmployeeId());
										lead.setLeadName(employeeMaster.getEmployeeAccount());
									}
									stakeHoldersNames.add(lead);
								}
							}
						}
						projectMasterDTO.setSponsors(sponsorNames);
						projectMasterDTO.setStakeHolders(stakeHoldersNames);
					}
				}
				projectMasterDTOs.add(projectMasterDTO);
			}
		}
		return projectMasterDTOs;
	}

	@Override
	public void deleteProjectData(String projectUId) {

		log.info("deleting the entire projectUid data");
		ProjectStatusDetail projectStatusDetail = projectStatusDetailRepository.findByUId(projectUId);
		if (Objects.nonNull(projectStatusDetail)) {
			projectStatusDetail.setStatus(Constants.PROJECTUID_DELETE_STATUS);
			projectStatusDetailRepository.save(projectStatusDetail);
		} else {
			throw new ServiceException("projectUid is not found");
		}
	}

	@Override
	public void EditProjectData(ProjectEditMainDTO projectEditMainDTO) {

		log.info("updating project data ");

		String areaId = null;
		String taPrioritizationCode = null;
		ProjectStatusDetail projectStatusDetail = projectStatusDetailRepository.findByUId(projectEditMainDTO.getuId());
		if (Objects.isNull(projectStatusDetail)) {
			throw new ServiceException("UId is not found");
		}
		if (Objects.nonNull(projectEditMainDTO.getArea())) {
			AreaMaster areaMaster = areaMasterRepository.findByArea(projectEditMainDTO.getArea());
			if (Objects.nonNull(areaMaster)) {
				areaId = areaMaster.getAreaId();
			}
		}
		if (Objects.nonNull(projectEditMainDTO.getTaPrioritizationValue())) {
			PrioritizationMaster prioritizationMaster = prioritizationMasterRepository
					.findByTaPrioritizationValue(projectEditMainDTO.getTaPrioritizationValue());
			if (Objects.nonNull(prioritizationMaster)) {
				taPrioritizationCode = prioritizationMaster.getTaPrioritizationCode();
			}
		}

		if (Objects.nonNull(projectStatusDetail)) {
			projectStatusDetail.setGlpgProjectId(projectEditMainDTO.getGlpgProjectId().toUpperCase());
			if (projectEditMainDTO.getStartDate() != null) {
				projectStatusDetail.setStartDate(new Timestamp(projectEditMainDTO.getStartDate().getTime()));
			}
			if (projectEditMainDTO.getEstimatedDateEnd() != null) {
				projectStatusDetail
						.setEstimatedDateEnd(new Timestamp(projectEditMainDTO.getEstimatedDateEnd().getTime()));
			}
			if (projectEditMainDTO.getActualDateEnd() != null) {
				projectStatusDetail.setActualDateEnd(new Timestamp(projectEditMainDTO.getActualDateEnd().getTime()));
			}
			if (projectEditMainDTO.getArea() != null) {
				projectStatusDetail.setAreaId(areaId);
			}
			projectStatusDetail.setIsDevelopment(projectEditMainDTO.getIsDevelopment());
			projectStatusDetail.setStatus(projectEditMainDTO.getStatus());
			projectStatusDetail.setTaPrioritizationCode(taPrioritizationCode);
			projectStatusDetail.setRisksCode(projectEditMainDTO.getRisksCode());
			projectStatusDetailRepository.save(projectStatusDetail);
		}
		ProjectDescription projectDescription = projectDescriptionRepository.findByUId(projectEditMainDTO.getuId());
		if (Objects.nonNull(projectDescription)) {

			projectDescription.setGoals(projectEditMainDTO.getGoals());
			projectDescription.setDescription(projectEditMainDTO.getDescription());
			projectDescription.setBusinessValue(projectEditMainDTO.getBusinessValue());
			projectDescription.setInScope(projectEditMainDTO.getInScope());
			projectDescription.setOutScope(projectEditMainDTO.getOutScope());
			projectDescription.setTitle(projectEditMainDTO.getTitle());
			projectDescription.setMilestones(projectEditMainDTO.getMilestones());
			projectDescription.setDeliverables(projectEditMainDTO.getDeliverables());
			projectDescription.setComments(projectEditMainDTO.getComments());
			projectDescriptionRepository.save(projectDescription);
		}
		String personIntakeId = null;
		String leadId = null;
		String managementId = null;
		if (Objects.nonNull(projectEditMainDTO.getIntakePerson())) {
			EmployeeMaster employeeMaster = employeeMasterRepository
					.findByEmployeeAccount(projectEditMainDTO.getIntakePerson());
			if (Objects.nonNull(employeeMaster)) {
				personIntakeId = employeeMaster.getEmployeeId();
			}
		}
		if (Objects.nonNull(projectEditMainDTO.getLead())) {
			EmployeeMaster employeeMasters = employeeMasterRepository
					.findByEmployeeAccount(projectEditMainDTO.getLead());
			if (Objects.nonNull(employeeMasters)) {
				leadId = employeeMasters.getEmployeeId();
			}
		}

		PersonLeadStakeholder personLeadStakeholder = new PersonLeadStakeholder();
		personLeadStakeholder = personLeadStakeholderRepository.findByUId(projectEditMainDTO.getuId());

		if (Objects.isNull(personLeadStakeholder)) {

			personLeadStakeholder = new PersonLeadStakeholder();
			personLeadStakeholder.setuId(projectEditMainDTO.getuId());
		}
		personLeadStakeholder.setPersonIntakeId(personIntakeId);
		personLeadStakeholder.setLeadId(leadId);
		if (projectEditMainDTO.getLeadRoleStartDate() != null) {
			personLeadStakeholder
					.setHeirarchyStartDate(new Timestamp(projectEditMainDTO.getLeadRoleStartDate().getTime()));
		}
		if (projectEditMainDTO.getLeadRoleEndDate() != null) {
			personLeadStakeholder.setHeirarchyEndDate(new Timestamp(projectEditMainDTO.getLeadRoleEndDate().getTime()));
		}
		personLeadStakeholderRepository.save(personLeadStakeholder);

		if (Objects.nonNull(projectEditMainDTO.getManagementName())) {
			EmployeeMaster employeeMasterManagement = employeeMasterRepository
					.findByEmployeeAccount(projectEditMainDTO.getManagementName());
			if (Objects.nonNull(employeeMasterManagement)) {
				managementId = employeeMasterManagement.getEmployeeId();
			}
		}
		ResourceDetails resourceDetails = resourceDetailsRepository.findByUId(projectEditMainDTO.getuId());
		if (Objects.isNull(resourceDetails)) {
			resourceDetails = new ResourceDetails();
			resourceDetails.setuId(projectEditMainDTO.getuId());
		}
		if (projectEditMainDTO.getPersonRoleStartDate() != null) {
			resourceDetails
					.setPersonIntakeStartDate(new Timestamp(projectEditMainDTO.getPersonRoleStartDate().getTime()));
		}
		if (projectEditMainDTO.getPersonRoleEndDate() != null) {
			resourceDetails.setPersonIntakeEndDate(new Timestamp(projectEditMainDTO.getPersonRoleEndDate().getTime()));
		}
		resourceDetails.setConsultingId(projectEditMainDTO.getConsulting());
		resourceDetails.setManagementId(managementId);
		resourceDetailsRepository.save(resourceDetails);
		String sponsorIds = null;
		List<String> sponsorIdsList = new ArrayList<>();
		if (Objects.nonNull(projectEditMainDTO.getSponsors())) {
			String[] sponsorsNames = projectEditMainDTO.getSponsors().split(",");
			List<String> sponsorsList = Arrays.asList(sponsorsNames);
			for (String sponsors : sponsorsList) {
				EmployeeMaster empMaster = employeeMasterRepository.findByEmployeeAccount(sponsors);
				if (Objects.nonNull(empMaster)) {
					sponsorIdsList.add(empMaster.getEmployeeId());
				}
			}
			sponsorIds = String.join(",", sponsorIdsList);
			log.info("sponsorids---------->{}", sponsorIdsList);
		}

		String stakeholdersIds = null;
		List<String> stakeholdersIdList = new ArrayList<>();
		if (Objects.nonNull(projectEditMainDTO.getStakeholders())) {
			String[] stakeholdersNames = projectEditMainDTO.getStakeholders().split(",");
			List<String> stakeholdersList = Arrays.asList(stakeholdersNames);
			for (String stakeholders : stakeholdersList) {
				EmployeeMaster employeeMasters = employeeMasterRepository.findByEmployeeAccount(stakeholders);
				if (Objects.nonNull(employeeMasters)) {
					stakeholdersIdList.add(employeeMasters.getEmployeeId());
				}
			}
			stakeholdersIds = String.join(",", stakeholdersIdList);
			log.info("stakeholdersIds---------->{}", stakeholdersIdList);
		}
		SponsorStakeholderDetails sponsorStakeholderDetails = sponsorStakeholderDetailsRepository
				.findByUId(projectEditMainDTO.getuId());
		if (Objects.isNull(sponsorStakeholderDetails)) {
			sponsorStakeholderDetails = new SponsorStakeholderDetails();
			sponsorStakeholderDetails.setuId(projectEditMainDTO.getuId());
		}
		sponsorStakeholderDetails.setSponsorId(sponsorIds);
		sponsorStakeholderDetails.setStakeholdersId(stakeholdersIds);
		sponsorStakeholderDetailsRepository.save(sponsorStakeholderDetails);

		ResourceAllocation resourceAllocation = resourceAllocationRepository.findByUId(projectEditMainDTO.getuId());
		if (Objects.isNull(resourceAllocation)) {
			resourceAllocation = new ResourceAllocation();
			resourceAllocation.setuId(projectEditMainDTO.getuId());
		}
		resourceAllocation.setResourcesConsulting(projectEditMainDTO.getConsulting());
		if (Objects.nonNull(projectEditMainDTO.getManagement())) {
			resourceAllocation.setResourcesManagement(projectEditMainDTO.getManagement());
		}
		if (projectEditMainDTO.getResourcesDateFrom() != null) {
			resourceAllocation.setResourcesDateFrom(new Timestamp(projectEditMainDTO.getResourcesDateFrom().getTime()));
		}
		if (projectEditMainDTO.getResourcesDateTo() != null) {
			resourceAllocation.setResourcesDateTo(new Timestamp(projectEditMainDTO.getResourcesDateTo().getTime()));
		}
		resourceAllocationRepository.save(resourceAllocation);
		if (Objects.nonNull(resourceAllocation.getResourceLicenses())) {
			if (resourceAllocation.getResourceLicenses().equalsIgnoreCase(Constants.LICENSE_USED)) {
				VendorDetails vendorDetails = vendorDetailsRepository.findByUId(projectEditMainDTO.getuId());
				if (Objects.isNull(vendorDetails)) {
					vendorDetails = new VendorDetails();
					vendorDetails.setuId(projectEditMainDTO.getuId());
				}
				vendorDetails.setVendorName(projectEditMainDTO.getVendorName());
				vendorDetails.setVendorDetails(projectEditMainDTO.getVendorDetails());
				vendorDetails.setVendorType(projectEditMainDTO.getVendorType());
				vendorDetails.setComments(projectEditMainDTO.getVendorComments());
				vendorDetailsRepository.save(vendorDetails);
			}
		}
	}

}