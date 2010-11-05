$(document).ready(function(){
	jQuery.noConflict();
});

function executaAjax(){
	jQuery.ajax({
		url: jQuery('#ctx').text() + "/jquery",
		success: function(data) {
			jQuery('#retornoAjax').text(data.membro.nome);
		}
	});
}