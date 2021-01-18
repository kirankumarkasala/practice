package com.excelra.glpg.comparator;

import java.util.Comparator;
import java.util.Objects;

import com.excelra.glpg.model.ProjectMasterDTO;

public class DateOfCompletionComparator implements Comparator<ProjectMasterDTO> {

	@Override
	public int compare(ProjectMasterDTO o1, ProjectMasterDTO o2) {

		if (Objects.nonNull(o1.getDateOfCompletion()) && Objects.nonNull(o2.getDateOfCompletion()))
			return o1.getDateOfCompletion().compareTo(o2.getDateOfCompletion());
		else if (Objects.nonNull(o1.getDateOfCompletion()) && Objects.isNull(o2.getDateOfCompletion()))
			return -1;
		else if (Objects.isNull(o1.getDateOfCompletion()) && Objects.nonNull(o2.getDateOfCompletion()))
			return 1;
		else
			return 0;
	}
}
