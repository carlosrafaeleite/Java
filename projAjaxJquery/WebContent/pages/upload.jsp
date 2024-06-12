<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html   >
<html lang="pt-br">
<head>
<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous">
</script>
<style type="text/css"> @import url("../resourse/css/upLoad.css"); </style>
<meta charset="ISO-8859-1">
<title>Upload de Imagens</title>
</head>
<body>
<h1>UpLoad de Imagens</h1>
<p></p>
<h3>Selecione uma imagem</h3>


 <form enctype="multipart/form-data" method="post">
<input type="file" id="file" name="file" onchange="uploadFile();"/>
<p></p>
<img alt="img" class="img" id="target" width="200" height="200" src"">
</form>
</body>

<script type="text/javascript">
	function uploadFile() {

		var target = document.querySelector('img');
		var file = document.querySelector('input[type=file]').files[0];

		var reader = new FileReader();

		reader.onloadend = function() {
			target.src = reader.result;
			
			/////-----Upload ajax------

			$.ajax({
				method : "POST",
				url : "Upload",
				data : { fileUpload : target.src }
			}).done(function(response) {
				alert("Sucesso: " + response);
				
			}).fail(function(xhr, status, errorThrown) {
				alert("Error: " + xhr.responseText);
			});
			

			/////-----------
		};

		if (file) {
			reader.readAsDataURL(file); 
		} else {
			target.src = "";
		}

	}
</script>
</html>