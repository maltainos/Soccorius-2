package com.soccoriusapp.mvc.model.rest;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRest {
	
	private List<DashboardCaseRest> labelAndDads = new ArrayList<>();
	private List<Long> values = new ArrayList<>();
	private List<String> keys = new ArrayList<>();

	public void add(DashboardCaseRest report) {
		labelAndDads.add(report);
		keys.add(report.getKey());
		values.add(report.getValue());
	}
	
	public void addAll(List<DashboardCaseRest> reports) {
		reports.forEach(report -> {
			add(report);
		});
	}
}
