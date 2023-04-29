
reportsTableBody = $("#reportsTableBody");

$(document).ready(function(){
	form = $("#formFindReport");
	
	$("#findReport").on("click", function(ev){
		ev.preventDefault();
		$("#findReport").attr('disabled', true);
		getReport(form);
	});
});


function getReport(form) {
	
	doencaValue = $("#doenca").val();
	bairoValue = $("#bairo").val();
	startDate = $("#startDate").val();
	endDate = $("#endDate").val();
	csrfValue = $("input[name = '_csrf']").val();

	if(doencaValue == ''){
		$("#doencaRequiredMessage").text('Doenca eh um campo obrigatorio!');
		return;
	}else{$("#doencaRequiredMessage").text('');}
	
	if(bairoValue == ''){
		$("#bairroRequiredMessage").text('Bairro eh um campo obrigatorio!');
		return;
	}else{$("#bairroRequiredMessage").text('');}
	
	if(startDate == ''){
		$("#startDateRequiredMessage").text('Data Inicial eh um campo obrigatorio!');
		return;
	}else{$("#startDateRequiredMessage").text('');}
	
	if(endDate == ''){
		$("#endDateRequiredMessage").text('Data Final eh um campo obrigatorio!');
		return;
	}else{$("#endDateRequiredMessage").text('');}

	params = { doenca: doencaValue, bairro: bairoValue, dateStart : startDate, dateEnd : endDate, _csrf: csrfValue };
	
	$.post(reportUrl, params, function(response) {
		reportsTableBody.empty();
		
		if(response['totalElements'] == 0){
			$("#noDataFound").text("NENHUM REGISTRO FOI ENCONTRADO...!");
		}else{
			console.log(response);
			$.each(response['content'], function(index, reportLine){
				
				var newRow = $("<tr>");
				var cols = "";
				cols += '<td>'+(index + 1) +'</td>';
				cols += '<td>'+reportLine.firstName+'</td>';
				cols += '<td>'+reportLine.lastName+'</td>';
				cols += '<td>'+reportLine.address+'</td>';
				cols += '<td>'+reportLine.dateOfBirth+'</td>';
				cols += '<td>'+reportLine.doenca+'</td>';
				cols += `<td><a class='btn btn-primary' th:href='@{/reports/detalis}'>Detalhes<a></td>`;
			
				newRow.append(cols);
				reportsTableBody.append(newRow);
			});
		$("#noDataFound").text("REGISTOS FORAM ENCONTRADOS: "+response['content'].length);
		}
		
	}).fail(function() {
		showModalDialog("ERROR", "Erro do Servidor Contacte o Administrador");
	});
	$("#findReport").attr('disabled', false);
	return false;
}