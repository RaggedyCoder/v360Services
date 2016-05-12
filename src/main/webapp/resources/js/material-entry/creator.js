function submitMaterialData() {
	var url = '/Volume360Services/restapi/material/create';
	var material = {};
	material['name'] = document.getElementById('material-name').value;
	material['materialType'] = $("#material-materialType option:selected").val();
	material['_csrf'] = $("input[name=_csrf]").val();
	var json = JSON.stringify(material);
	console.log('Sending: ' + json);
	 $.ajax({
		method : "POST",
		url : url,
		data : material
	}).success(
			function(newMaterial) {
				var materialCount = $('select#material option').length;
				var materialNewOption = newMaterial['id'];
				$("select#material option").eq(materialCount - 2).before(
						$("<option></option>").val(materialNewOption).html(
								material['name']));
				$('select#material option:eq(' + (materialCount - 2) + ')')
						.prop('selected', true);
				$('#myModal').modal("hide");
			});
}

function closeModal() {
	$('select#material option:eq(0)').prop('selected', true);
	$('#myModal').modal("hide");
}