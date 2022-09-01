$(document).ready(function(){

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
});



let btnBars = document.querySelector("#btn-bars");
let sidebar = document.querySelector(".sidebar");
let search = document.querySelector(".fa-search");
let page = document.querySelector(".page");
		
btnBars.onclick = function(){
	sidebar.classList.toggle('active');
	page.classList.toggle('full-width');
}

search.onclick = function(){
	sidebar.classList.toggle('active');
	page.classList.toggle('full-width');
}

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