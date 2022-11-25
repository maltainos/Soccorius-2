$(document).ready(function(){

	$("#findReport").on("click", function(ev){
		ev.preventDefault();
		getReport();
	});
});


function getReport() {
	doencaValue = $("#doenca").val();
	bairoValue = $("#bairo").val();
	startDate = $("#startDate").val();
	endDate = $("#endDate").val();
	csrfValue = $("input[name = '_csrf']").val();

	params = { doenca: doencaValue, bairo: bairoValue, dateStart : startDate, dateEnd : endDate, _csrf: csrfValue };

	$.post(reportUrl, params, function(response) {
		console.log(response);
	}).fail(function() {
		showModalDialog("ERROR", "Erro do Servidor Contacte o Administrador");
	});
	return false;
}