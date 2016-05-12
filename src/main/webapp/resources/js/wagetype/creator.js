function submitWageTypeData() {
	var url = '/Volume360Services/restapi/wagetype/create';
	var wageType = {};
	wageType['name'] = document.getElementById('wageType-name').value;
	wageType['wageCategory'] = $("#wageType-wageCategory option:selected").val();
	wageType['_csrf'] = $("input[name=_csrf]").val();
	var json = JSON.stringify(wageType);
	console.log('Sending: ' + json);
	 $.ajax({
		method : "POST",
		url : url,
		data : wageType
	}).success(
			function(newWageType) {
				var wageTypeCount = $('select#wageType option').length;
				var wageTypeNewOption = JSON.stringify(newWageType);
				$("select#wageType option").eq(wageTypeCount - 2).before(
						$("<option></option>").val(wageTypeNewOption).html(
								wageType['name']));
				$('select#wageType option:eq(' + (wageTypeCount - 2) + ')')
						.prop('selected', true);
				$('#myModal').modal("hide");
			});
}

function closeModal() {
	$('select#wageType option:eq(0)').prop('selected', true);
	$('#myModal').modal("hide");
}