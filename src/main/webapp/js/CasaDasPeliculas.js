$(document).ready(function() {
	var altura_tela = $(window).height();
	var altura_divConteudo = altura_tela - 50;
	$("#divBody").height(altura_tela);
	$("#divConteudo").height(altura_divConteudo);
	$(window).resize(function() {
		var altura_tela = $(window).height();
		var altura_divConteudo = altura_tela - 50;
		$("#divBody").height(altura_tela);
		$("#divConteudo").height(altura_divConteudo);
	});
});

function fecharWindow() {

	window.opener = window;
	window.close("#");

}

