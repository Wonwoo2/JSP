/**
 * 
 */
$.fn.searchZip = function(param) {
	const URL = param.url;
	const zipCodeTag = $(param.zipCodeTag);
	const address1Tag = $(param.address1Tag);
	const address2Tag = $(param.address2Tag);
	let modalDiv = null;
	if ($("#zipSearchModal").length == 0) {
		modalDiv = $(MODALDIVSRC);
		$("body").append(modalDiv);
	}
	let zipSearchResult = modalDiv.find("#searchResult").hide();
	let zipDataTable = zipSearchResult.find("table").DataTable({
		pageLength : 7,
		lengthChange : false,
		select : "single",
		columns : [ {
			data : "zipcode"
		}, {
			data : "address"
		} ]
	});
	let completeAddressBtn = $("#completeAddressBtn");
	completeAddressBtn.on("click", function() {
		let zipcode = zipSearchResult.find("#searchedZipCode").val();
		let address1 = zipSearchResult.find("#address1").val();
		let address2 = zipSearchResult.find("#address2").val();
		if (zipcode && address1 && address2) {
			zipCodeTag.val(zipcode);
			address1Tag.val(address1);
			address2Tag.val(address2);
			modalDiv.modal("hide");
		}
	});
	zipSearchResult.on("click", "tbody>tr", function() {
		// let zipData = $(this).data("zipdata");
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			zipDataTable.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
		let zipData = zipDataTable.row(".selected").data();
		if (zipData) {
			zipSearchResult.find("#searchedZipCode").val(zipData.zipcode);
			zipSearchResult.find("#address1").val(zipData.address);
		} else {
			zipSearchResult.find("#searchedZipCode").val("");
			zipSearchResult.find("#address1").val("");
		}
	});
	zipSearchResult.on('search.dt', function() {
		zipSearchResult.find("#searchedZipCode").val("");
		zipSearchResult.find("#address1").val("");
	});
	modalDiv.find("#zipSearchForm").on("submit", function(event) {
		event.preventDefault();
		zipSearchResult.find("tbody").empty();
		zipSearchResult.find("input").val("");
		zipDataTable.clear();
		zipSearchResult.hide();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		$.ajax({
			url : URL,
			method : method,
			data : data,
			dataType : "json",
			success : function(resp) {
				// let trTags = [];
				if (resp.length > 0) {
					$(resp).each(function(idx, zipData) {
						// datatable 사용하기 전 예제
						// let trTag = $("<tr class='zipdata'>").append(
						// $("<td>").html(zipData.zipcode),
						// $("<td>").html(zipData.address)
						// ).data("zipdata", zipData);
						// trTags.push(trTag);
						// ================================================
						console.log(zipData);
						zipDataTable.row.add(zipData);
					});
				} else {
					// datatable 사용하기 전 예제
					// trTags.push(
					// $("<tr>").html(
					// $("<td colspan='2'>").text("검색결과가 없음.")
					// )
					// );
				}
				// zipSearchResult.find("tbody").html(trTags);
				// ================================================
				zipDataTable.draw(false);
				zipSearchResult.show();
			},
			error : function(errResp) {
				console.log(errResp);
			}
		});
		return false;
	});
	return this;
}
let MODALDIVSRC = "";
MODALDIVSRC += '<div class="modal fade" id="zipSearchModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true"> ';
MODALDIVSRC += '  <div class="modal-dialog modal-lg">                                                                                                                            ';
MODALDIVSRC += '    <div class="modal-content">                                                                                                                                  ';
MODALDIVSRC += '      <div class="modal-header">                                                                                                                                 ';
MODALDIVSRC += '        <h5 class="modal-title" id="staticBackdropLabel">우편번호 검색</h5>                                                                                      ';
MODALDIVSRC += '        <button type="button" class="close" data-dismiss="modal" aria-label="Close">                                                                             ';
MODALDIVSRC += '          <span aria-hidden="true">&times;</span>                                                                                                                ';
MODALDIVSRC += '        </button>                                                                                                                                                ';
MODALDIVSRC += '      </div>                                                                                                                                                     ';
MODALDIVSRC += '      <div class="modal-body">                                                                                                                                   ';
MODALDIVSRC += '      <form id="zipSearchForm" method="get" class="form-inline">                                            ';
MODALDIVSRC += '       	<p>                                                                                                                                                     ';
MODALDIVSRC += '       		검색 키워드(동이름): <input type="text" required name="keyword" class="form-control" />                                                             ';
MODALDIVSRC += '       		<input type="submit" class="btn btn-dark" value="검색" />                                                                                           ';
MODALDIVSRC += '       	</p>                                                                                                                                                    ';
MODALDIVSRC += '      </form>                                                                                                                                                    ';
MODALDIVSRC += '       	<div id="searchResult">                                                                                                                                 ';
MODALDIVSRC += '	   		<table>                                                                                                                                                 ';
MODALDIVSRC += '	   			<thead>                                                                                                                                             ';
MODALDIVSRC += '	   				<tr>                                                                                                                                            ';
MODALDIVSRC += '	   					<th>우편번호</th>                                                                                                                           ';
MODALDIVSRC += '	   					<th>주소</th>                                                                                                                               ';
MODALDIVSRC += '	   				</tr>                                                                                                                                           ';
MODALDIVSRC += '	   			</thead>                                                                                                                                            ';
MODALDIVSRC += '	   			<tbody>                                                                                                                                             ';
MODALDIVSRC += '	   			                                                                                                                                                    ';
MODALDIVSRC += '	   			</tbody>                                                                                                                                            ';
MODALDIVSRC += '	   		</table>                                                                                                                                                ';
MODALDIVSRC += '	   		<div class="form-group mb-3  mr-3">                                                                                                                     ';
MODALDIVSRC += '	   		우편번호 : <input type="text" class="ml-3 form-control mr-3" readonly id="searchedZipCode" />                                                           ';
MODALDIVSRC += '	   		</div>                                                                                                                                                  ';
MODALDIVSRC += '	   		<div class="form-group mb-3 mr-3">                                                                                                                      ';
MODALDIVSRC += '	   		상위 주소 : <input type="text" class="ml-3 form-control mr-3" readonly id="address1" />                                                                 ';
MODALDIVSRC += '	   		</div>                                                                                                                                                  ';
MODALDIVSRC += '	   		<div class="form-group mr-3">                                                                                                                           ';
MODALDIVSRC += '	   		하위 주소 : <input type="text" class="ml-3 form-control mr-3" id="address2" />                                                                          ';
MODALDIVSRC += '	   		</div>                                                                                                                                                  ';
MODALDIVSRC += '   		</div>                                                                                                                                                  ';
MODALDIVSRC += '      </div>                                                                                                                                                     ';
MODALDIVSRC += '      <div class="modal-footer">                                                                                                                                 ';
MODALDIVSRC += '        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>                                                                       ';
MODALDIVSRC += '        <button type="button" class="btn btn-primary" id="completeAddressBtn">주소 입력</button>                                                                 ';
MODALDIVSRC += '      </div>                                                                                                                                                     ';
MODALDIVSRC += '    </div>                                                                                                                                                       ';
MODALDIVSRC += '  </div>                                                                                                                                                         ';
MODALDIVSRC += '</div>                                                                                                                                                           ';