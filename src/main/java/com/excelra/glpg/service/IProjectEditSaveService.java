package com.excelra.glpg.service;

import com.excelra.glpg.model.BasicInfoDTO;
import com.excelra.glpg.model.ConsultingDTO;
import com.excelra.glpg.model.FteAllocationDTO;
import com.excelra.glpg.model.IntakePersonLeadDTO;
import com.excelra.glpg.model.MilestonesDelivDTO;
import com.excelra.glpg.model.ProjectDescriptionDTO;
import com.excelra.glpg.model.StakeholdersSponsorManageDTO;
import com.excelra.glpg.model.VendorDetailsDTO;

public interface IProjectEditSaveService {

	String saveBasicInfoData(BasicInfoDTO basicInfoDTO);

	String saveProjectDescription(ProjectDescriptionDTO projectDescriptionDTO);

	String saveMilestonesDeliverablesData(MilestonesDelivDTO milestonesDelivDTO);

	void saveIntakePersonLeadData(IntakePersonLeadDTO intakePersonLeadDTO);

	void saveStakeholderSponsrsData(StakeholdersSponsorManageDTO stakeholdersSponsorManageDTO);

	void saveConsultingData(ConsultingDTO consultingDTO);

	void saveFteAllocationData(FteAllocationDTO fteAllocationDTO);

	String saveLicenseDetailsData(VendorDetailsDTO vendorDetailsDTO);

}
