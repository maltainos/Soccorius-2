
function checkEmailIsUnique(form){
	idValue = $("#id").val();
	emailValue = $("#email").val();
	csrfValue = $("input[name = '_crsf']").val();
	
	params = {id : idValue , email : emailValue , _csrf : csrfValue};
	
	$.post(checkEmailUrl, params, function(response){
		if(response){
			form.submit();
		}else{
			showModalDialog("ALERTA!","Email Já esta em uso por outro utilizador");	
		}
	}).fail(function(){
		showModalDialog("ERROR","Erro do Servidor Contacte o Administrador");	
	});
	return false;
}