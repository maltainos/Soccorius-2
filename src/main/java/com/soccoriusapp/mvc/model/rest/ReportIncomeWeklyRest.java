package com.soccoriusapp.mvc.model.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReportIncomeWeklyRest {
	
	private String dayOfWeek;
	private Long incomeQtd;
}
