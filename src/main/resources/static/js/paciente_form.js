let dateOfBirth = $("#dateOfBirth");
let yearsOld = $("#yearsOld");


$(document).ready(function() {
	dateOfBirth.on("change", function() {
		dateEntired();
	})
});


function dateEntired() {
	dateOfToday = new Date();
	thisYear = dateOfToday.getFullYear();
	dateOfBirthValue = dateOfBirth.val();
	
	dateOfBirth = new Date(dateOfBirthValue);
	
	yearsOld = thisYear - dateOfBirth.getFullYear();
	alert(yearsOld + " Anos de Idade");
}