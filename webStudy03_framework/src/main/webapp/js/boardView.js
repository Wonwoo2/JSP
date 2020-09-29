/**
 * 
 */
	function syncToAsync(event){
		event.preventDefault();
		let url = this.action?this.action:location.href;
		let method = this.method?this.method:"get";
		let data = $(this).serialize(); // query string 
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json",
			success : function(resp) {
				if(resp.result == "OK"){
					replyInsertForm.get(0).reset();
					replyModal.modal("hide");
					searchForm.submit();
				}else if(resp.message){
					alert(resp.message);
				}
			},
			error : function(errResp) {
				console.log(errResp);
			}
		});
		return false;
	}
	function updateReply(event){
		let reply = $(this).parents("tr:first").data("reply");
		$(replyUpdateForm).find("[name='rep_no']").val(reply.rep_no);
		$(replyUpdateForm).find("[name='bo_no']").val(reply.bo_no);
		$(replyUpdateForm).find("[name='rep_writer']").val(reply.rep_writer);
		$(replyUpdateForm).find("[name='rep_content']").val(reply.rep_content);
		replyModal.modal("show");
	}
	function deleteReply(event){
		if(!confirm("정말 지울텐가?")) return false;
		let password = prompt("비밀번호");
		if(!password){
			alert("비밀번호 입력!");
			return false;
		}
		let reply = $(this).parents("tr:first").data("reply");
		$(replyDeleteForm).find("[name='rep_no']").val(reply.rep_no);
		$(replyDeleteForm).find("[name='bo_no']").val(reply.bo_no);
		$(replyDeleteForm).find("[name='rep_pass']").val(password);
		$(replyDeleteForm).submit();
	}
	let listTable = $("#replyTable").on("click", ".updateBtn", updateReply)
									.on("click", ".delBtn", deleteReply);
	let replyModal = $("#replyModal").on("hidden.bs.modal", function(){
		$(this).find("form").get(0).reset();
	});
	let replyInsertForm = $("#replyInsertForm").on("submit", syncToAsync);
	let replyUpdateForm = replyModal.find("form").on("submit", syncToAsync);
	let replyDeleteForm = $("#replyDeleteForm").on("submit", syncToAsync);
	
	let pagingArea = $("#pagingArea");
	let pagingA = pagingArea.on('click', "a" ,function(){
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		searchForm.find("[name='page']").val(1);
		return false;
	});
	let searchForm = $("#searchForm").on("submit", function(event){
		event.preventDefault();
		let url = this.action?this.action:location.href;
		let method = this.method?this.method:"get";
		let data = $(this).serialize(); // query string 
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json",
			success : function(resp) {
				listTable.find("tbody").empty();
				pagingArea.empty();
				let replyList = resp.data;
				let trTags = [];
				if(replyList){
					$(replyList).each(function(idx, reply){
						let tr = $("<tr>");
						tr.append(
								$("<td>").text(reply.rep_content),
								$("<td>").text(reply.rep_writer+"("+reply.rep_ip+")"),
								$("<td>").text(reply.rep_date),	
								$("<td>").append(
									$("<input>").attr({
										type:"button",
										value:"수정"
									}).addClass("btn btn-info mr-2 updateBtn"),		
									$("<input>").attr({
										type:"button",
										value:"삭제"
									}).addClass("btn btn-danger mr-2 delBtn")		
								)	
						).data("reply", reply);
						trTags.push(tr);
					});
				}else{
					trTags.push(
						$("<tr>").html(
							$("<td>").text("댓글이 없음.")									
						)
					);
				}
				listTable.html(trTags);
				pagingArea.html(resp.pagingHTML_BS);
			},
			error : function(errResp) {
			}
		});
		return false;
	}).submit();