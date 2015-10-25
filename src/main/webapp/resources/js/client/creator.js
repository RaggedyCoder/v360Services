function submitClientData() {
	var url = '/Volume360Services/restapi/client/create';
	var client = {};
	client['name'] = document.getElementById('client-name').value;
	client['clientType'] = $("#client-type option:selected").text();
	client['_csrf'] = $("input[name=_csrf]").val();
	var json = JSON.stringify(client);
	console.log('Sending: ' + json);
	$.ajax({
		method : "POST",
		url : url,
		data : client
	}).success(
			function(newClient) {
				var clientCount = $('select#client option').length;
				var clientNewOption = JSON.stringify(newClient);
				$("select#client option").eq(clientCount-2).before(
						$("<option></option>").val(clientNewOption).html(
								newClient['name']));
				$('select#client option:eq('+(clientCount-2)+')').prop('selected', true);
				$('#myModal').modal("hide");
			});
}

function closeModal() {
	$('select#client option:eq(0)').prop('selected', true);
	$('#myModal').modal("hide");
}