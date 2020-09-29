/**
 * 
 */

function syncToAsync(event) {
	event.preventDefault();
	let url = this.action ? this.action : location.href;
	let method = this.method ? this.method : "get";
	let data = $(this).serialize(); // query string
	$.ajax({
		url : url,
		method : method,
		data : data,
		dataType : "json",
		success : function(resp) {
			if (resp.result == "OK") {
				replyInsertForm.get(0).reset();
				replyModal.modal("hide");
				searchForm.submit();
			} else if (resp.message) {
				alert(resp.message);
			}
		},
		error : function(errResp) {
			console.log(errResp);
		}
	});
	return false;
}