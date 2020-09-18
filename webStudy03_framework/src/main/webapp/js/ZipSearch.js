/**
 * 
 */
$.fn.zipSearch = function() {
	function makeZipSearchArea() {
		let modalFade = $("<div>").addClass("modal-fade");
		let modalDialog = $("<div>").addClass("modal-dialog modal-lg");
		let modalHeader = $("<div>").addClass("modal-header");
		let modalBody = $("<div>").addClass("modal-body");
		let modalFooter = $("<div>").addClass("modal-footer");
		let modalContent = $("<div>").addClass("modal-content").append(modalHeader, modalBody, modalFooter);
		
		let modalTitle = $("<h5>").addClass("modal-title");
		let modalClose = $("<button>").addClass("close");
		let modalSpanHidden = $("<span>").attr('aria-hidden', 'true');
		
		// modalHeader 안의 태그 선언 및 속성 추가
		modalTitle.prop("staticBackdropLabel")
		modalTitle.text("우편번호 검색");
		
		modalClose.attr({
			'data-dismiss' : 'modal',
			'aria-label' : 'Close'
		});
		
		modalSpanHidden.text("&times;");
		// modal close 버튼에 span태그 추가
		modalClose.append(modalSpanHidden);
		
		// modalHeader에 h5 태그, button 태그 추가
		modalHeader.append(modalTitle, modalClose);
		
		// modalFade 속성들 선언
		modalFade.prop("zipSearchModal");
		modalFade.attr({
			'data-backdrop' : 'static',
			'data-keyboard' : 'false', 
			'tabindex' : -1,
			'aria-labelledby' : 'staticBackdropLabel',
			'aria-hidden' : 'true'
		});
		
		
		/*<div id="searchZipModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="text-align: center;">
					<h4 class="modal-title">우편번호 검색</h4>
				</div>
				<div class="modal-body">
					<form id="zipSearchForm"  class="form-inline" action="<%= request.getContextPath() %>/zipSearch.do" method="get">
						<p>
							동을 입력하세요.
							<input class="form-control" type="text" name="dong" required /> 
							<input id="search" class="btn btn-dark" type="submit" value="검색">
						</p>
					</form>
					<div id="searchResult">
						<table>
				   			<thead>
				   				<tr>
				   					<th>우편번호</th>
				   					<th>주소</th>
				   					<th>번지</th>
				   				</tr>
				   			</thead>
				   			<tbody>
				   			
				   			</tbody>
				   		</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>*/
		/*<div class="modal fade" id="zipSearchModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="staticBackdropLabel">우편번호 검색</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		      <form id="zipSearchForm" action="<%=request.getContextPath() %>/zipSearch.do" method="get" class="form-inline">
		       	<p>
		       		검색 키워드(동이름): <input type="text" required name="keyword" class="form-control" />
		       		<input type="submit" class="btn btn-dark" value="검색" />
		       	</p>
		      </form>
		       	<div id="searchResult">
			   		<table>
			   			<thead>
			   				<tr>
			   					<th>우편번호</th>
			   					<th>주소</th>
			   				</tr>
			   			</thead>
			   			<tbody>
			   			
			   			</tbody>
			   		</table>
			   		<div class="form-group mb-3  mr-3">
			   		우편번호 : <input type="text" class="ml-3 form-control mr-3" readonly id="searchedZipCode" />
			   		</div>
			   		<div class="form-group mb-3 mr-3">
			   		상위 주소 : <input type="text" class="ml-3 form-control mr-3" readonly id="address1" />
			   		</div>
			   		<div class="form-group mr-3">
			   		하위 주소 : <input type="text" class="ml-3 form-control mr-3" id="address2" />
			   		</div>
		   		</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
		        <button type="button" class="btn btn-primary" id="completeAddressBtn">주소 입력</button>
		      </div>
		    </div>
		  </div>
		</div>*/
	}
}