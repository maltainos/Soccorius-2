$(document).ready(function(){
	$("#cancel").on("click", function(){
		window.location = moduleURL;
	});
	
	customizeDropDown();
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

function clearSearchForm(){
	window.location = moduleURL;
}