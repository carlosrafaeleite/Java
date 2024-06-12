function buscaCampo() {

	/*
	 * var $input = document.getElementById('inpFoto'), $fileName =
	 * document.getElementById('labelFoto');
	 * 
	 * $input.addEventListener('change', function() {
	 * 
	 * var file = $fileName.textContent = $(this)[0].files[0].name;
	 * $('#recebe').val(file);
	 * 
	 * 
	 * });
	 * 
	 * 
	 */
	document.getElementById("btn").onclick = function() {
		$('.inpFoto').trigger('click');

	};

	$('.inpFoto').on('change', function() {
		var fileName = $(this)[0].files[0].name;
		$('#recebe').val(fileName);
	});

}