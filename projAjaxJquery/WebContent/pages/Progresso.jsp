<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">@import url("../resourse/css/progresso.css");</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



<meta charset="ISO-8859-1">
<title>Pagina de Progresso</title>
</head>
<body>
	<h1>Página de Progresso</h1>

	<h2>Exemplo com JavaScript</h2>
	<div id="myProgress">
	<div id="myBar"></div>
	</div>
	<p></p>
	<input type="button" onclick="exibirBarra()"
		value="Iniciar Carregamento">
		
		<p>....................................</p>
		
		<h2>Exemplo com Jquery</h2>
		
		<div id="progress-bar">
		
		<div class="progress-label">Carregando...</div>
		
		</div>
		
</body>

<script type="text/javascript">


// barra com javaScript
function exibirBarra(){
	var elem = document.getElementById("myBar");
	var width = 1;
	var id = setInterval(frame, 10);

	function frame(){
		if(width >= 100){
			clearInterval(id);
			}else{
			width++;
			elem.style.width = width + "%";
		}
	}
}


//barra com jquery

$(function(){
	var progressbar = $("#progress-bar"), progresslabel = $(".progress-label");
	progressbar.progressbar ({
		value: false,
		change: function(){
				progresslabel.text(progressbar.progressbar('value')+"%");
			},
			complete: function(){
				progresslabel.text('Completo');
					
				}
	
		});

	function progress(){
		var val = progressbar.progressbar('value')|| 0;

		progressbar.progressbar("value", val + 2)

		if(val <= 99){
			setTimeout(progress, 80);
			}
			}
	setTimeout(progress, 500);
	
	
		
});



</script>
</html>