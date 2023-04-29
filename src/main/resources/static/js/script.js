$(document).ready(function(){
	
	$("#usersPdfExport").on("click", function(){
		getUsersPdfExporterData();
	});

	$("#cancel").on("click", function(ev){
		ev.preventDefault();
		window.location = moduleURL;
	});
	
	$("#limit").on("change", function(ev){
		ev.preventDefault();
		document.limitForm.submit();
	});
	customizeDropDown();
	
	$('#logoutLink').on('click', function(ev){
		ev.preventDefault();
		document.logoutForm.submit();
	});
	
	getEndpointReport();
	getWeekReport();
	getAnnualReport();
});



let btnBars = document.querySelector("#btn-bars");
let sidebar = document.querySelector(".sidebar");
let search = document.querySelector(".fa-search");
let page = document.querySelector(".page");
		
btnBars.onclick = function(){
	sidebar.classList.toggle('active');
	page.classList.toggle('full-width');
}

/*
search.onclick = function(){
	sidebar.classList.toggle('active');
	page.classList.toggle('full-width');
}
*/

function customizeDropDown(){
	$(".navbar .dropdown").hover(
		function(){
			$(this).find('.dropdown-menu').first().stop(true, true).delay(250).slideDown();
		},
		function(){
			$(this).find('.dropdown-menu').first().stop(true, true).delay(100).slideUp();
		}
	)
	$(".dropdown > a").click(function(){
		location.href = this.href;
	});
}

function showModalDialog(title, message){
	$("#modalTitle").text(title);
	$("#modalText").text(message);
	$("#modalDialog").modal("show");
}

function showModalConfirmation(message){
	$("#confimationText").text(message);
	$("#modalConfirmation").modal("show");
}


function clearSearchForm(){
	window.location = moduleURL;
}

function getEndpointReport(){
	csrfValue = $("input[name = '_csrf']").val();
	params = {_csrf: csrfValue};
	$.get(reportCaseURL, params, function(response){
		todayCharts('Endpoints','bar','#general',response,0);
	}).fail(function(response){
		showModalDialog("Error","Erro Interno do Servidor "+response);
	});
}
function getWeekReport(){
	csrfValue = $("input[name = '_csrf']").val();
	params = {_csrf: csrfValue};
	$.get(weekURL, params, function(response){
		todayCharts('Entrada Semana','bar','#week',response,0);
	}).fail(function(response){
		showModalDialog("Error","Erro Interno do Servidor "+response);
	});
}
function getAnnualReport(){
	csrfValue = $("input[name = '_csrf']").val();
	params = {_csrf: csrfValue};
	$.get(annualURL, params, function(response){
		todayCharts('Triagem Anual','bar','#annual',response,1);
	}).fail(function(response){
		showModalDialog("Error","Erro Interno do Servidor "+response);
	});
}

function getUsersPdfExporterData(){
	csrfValue = $("input[name = '_csrf']").val();
	params = {_csrf: csrfValue};
	
	url = moduleURLRest + "/export/pdf";
	$.get(url, params, function(response){
		
		
		var file = new FileReader();
		console.log(file);

		//window.print(file);
		//todayCharts('Entrada Semana','bar','#week',response,0);
	}).fail(function(response){
		showModalDialog("Error","Erro Interno do Servidor "+response);
	});
}

function getUsersCsvExporterData(){
	csrfValue = $("input[name = '_csrf']").val();
	params = {_csrf: csrfValue};
	
	url = moduleURLRest + "/export/csv";
	$.get(url, params, function(response){
		console.log(response);
		//todayCharts('Entrada Semana','bar','#week',response,0);
	}).fail(function(response){
		showModalDialog("Error","Erro Interno do Servidor "+response);
	});
}

function getUsersExcelExporterData(){
	csrfValue = $("input[name = '_csrf']").val();
	params = {_csrf: csrfValue};
	
	url = moduleURLRest + "/export/excel";
	$.get(url, params, function(response){
		console.log(response);
		//todayCharts('Entrada Semana','bar','#week',response,0);
	}).fail(function(response){
		showModalDialog("Error","Erro Interno do Servidor "+response);
	});
}