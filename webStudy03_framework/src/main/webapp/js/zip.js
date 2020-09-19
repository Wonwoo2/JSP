/**
 * 
 */
$.fn.zipSearch = function(param) {
	const URL = param.url;
	let zipcode = param.tag.zipcode;
	let addr1 = param.tag.addr1;
	let addr2 = param.tag.addr2;
	let modalDiv = param.tag.modalDiv;
	let command = param.command;
	
	let selected = [];

	let modalFade = $("<div>");
	let modalDialog = $("<div>");
	let modalContent = $("<div>");
	let modalHeader = $("<div>");
	let modalBody = $("<div>");
	let modalFooter = $("<div>");

	modalFade.attr({
		"class" : "modal fade",
		"id" : "zipSearchModal",
		"tabindex" : -1,
		"role" : "dialog",
		"aria-labelledby" : "modalTitle",
		"aria-hidden" : "true"
	});
	modalDialog.addClass("modal-dialog");
	modalDialog.addClass("modal-lg");
	modalContent.addClass("modal-content");
	modalHeader.addClass("modal-header");
	modalBody.addClass("modal-body");
	modalFooter.addClass("modal-footer");

	let modalTitle = $("<h5>");
	modalTitle.attr({
		"class" : "modal-title",
		"id" : "modalTitle",
	});
	modalTitle.text("우편번호 검색");

	modalHeader.append(modalTitle);

	let modalTable = $("<table>");
	let modalThead = $("<thead>");
	let modalTr = $("<tr>");
	let modalZipcodeTh = $("<th>");
	let modalAddrTh = $("<th>");
	let modalTbody = $("<tbody>");

	modalTable.attr("id", "zipTable");

	modalZipcodeTh.text("우편번호");
	modalAddrTh.text("주소");

	modalTr.append(modalZipcodeTh, modalAddrTh);
	modalThead.append(modalTr);

	modalTable.append(modalThead, modalTbody);

	modalBody.append(modalTable);

	let modalClose = $("<button>");
	let modalConfirm = $("<button>");
	modalClose.attr({
		"type" : "button",
		"class" : "btn btn-secondary modalBtn",
		"data-dismiss" : "modal",
		"data-action" : "cancel"
	});
	modalClose.text("취소");
	
	modalConfirm.attr({
		"type" : "button",
		"class" : "btn btn-info modalBtn",
		"data-dismiss" : "modal",
		"data-action" : "confirm"
	});
	
	modalConfirm.text("확인");

	modalFooter.append(modalConfirm, modalClose);

	modalContent.append(modalHeader, modalBody, modalFooter);
	modalDialog.append(modalContent);
	modalFade.append(modalDialog);

	modalFade.modal("hide");

	modalDiv.append(modalFade);
	
	let zipDataTable = modalDiv.find("table").DataTable({
		processing : true,
        serverSide : true,
		ajax : URL,
		columns : [
            { "data": "zipcode" },
            { "data": "address" },
        ],
        rowCallback : function( row, data ) {
            if ( $.inArray(data.DT_RowId, selected) !== -1 ) {
                $(row).addClass('selected');
            }
        },
        select : "single"
	});
	
	zipDataTable.on("click", "tbody>tr", function() {
		if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
        	zipDataTable.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
	});
	
	$(".modalBtn").on("click", function() {
		let action = $(this).data("action");
		let data = zipDataTable.row(".selected").data();
		
		let zipcodeValue = "";
		let addr1Value = "";
		let addr2Value = "";
		if ("confirm" == action) {
			if (data) {
				zipcodeValue = data.zipcode;
				addr1Value = data.sido + " " + data.gugun + " " + data.dong;
				addr2Value = data.bunji;
			}
		} else if ("cancel" == action) {
			if ("regist" == command) {
				zipcodeValue = "";
				addr1Value = "";
				addr2Value = "";
			} else if ("modify" == command) {
				zipcodeValue = zipcode.val();
				addr1Value = addr1.val();
				addr2Value = addr2.val();
			}
		}
		
		zipcode.val(zipcodeValue);
		addr1.val(addr1Value);
		addr2.val(addr2Value);
	});
}