<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/includee/preScript.jsp" />

<title>buyer/buyerModifyForm.jsp</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<form id="buyerRegistFrom" action="${pageContext.request.contextPath}/buyer/buyerModify.do" method="post">
				<div class="form-group">
					<label>거래처코드</label>
					<input class="form-control" type="text" name="buyer_id" value="${buyer.buyer_id}"
						maxlength="6" required readonly />
				</div>
				<div class="form-group">
					<label>거래처명</label>
					<input class="form-control" type="text" name="buyer_name" value="${buyer.buyer_name}" 
						maxlength="40" required />
				</div>
				<div class="form-group">
					<label>거래처분류</label>
					<input type="hidden" name="buyer_lgu" value="${buyer.buyer_lgu}" />
					<input class="form-control" type="text" value="${buyer.lprod_name}" 
						maxlength="4" required />
				</div>
				<div class="form-group">
					<label>은행</label>
					<input class="form-control" type="text" name="buyer_bank" value="${buyer.buyer_bank}" 
						maxlength="40" />
				</div>
				<div class="form-group">
					<label>계좌</label>
					<input class="form-control" type="text" name="buyer_bankno" value="${buyer.buyer_bankno}" 
						maxlength="40" pattern="[0-9]{3}-[0-9]+-[0-9]+" />
				</div>
				<div class="form-group">
					<label>예금주</label>
					<input class="form-control" type="text" name="buyer_bankname" value="${buyer.buyer_bankname}" 
						maxlength="15" />
				</div>
				<div class="form-group">
					<label>우편번호</label>
					<input class="form-control" type="text" name="buyer_zip" value="${buyer.buyer_zip}" 
						maxlength="7" readonly />
					<input class="btn btn-dark" type="button" value="우편번호 검색" data-toggle="modal" data-target="#zipSearchModal" />
				</div>
				<div class="form-group">
					<label>주소</label>
					<input class="form-control" type="text" name="buyer_add1" value="${buyer.buyer_add1}" 
						maxlength="100" readonly />
				</div>
				<div class="form-group">
					<label>상세주소</label>
					<input class="form-control" type="text" name="buyer_add2" value="${buyer.buyer_add2}" 
						maxlength="80" />
				</div>
				<div class="form-group">
					<label>회사 전화</label>
					<input class="form-control" type="text" name="buyer_comtel" value="${buyer.buyer_comtel}" 
						maxlength="14" pattern="[0-9]{2,3}-[0-9]{3}-[0-9]{4}" required />
				</div>
				<div class="form-group">
					<label>팩스</label>
					<input class="form-control" type="text" name="buyer_fax" value="${buyer.buyer_fax}" 
						maxlength="20" pattern="[0-9]{2,3}-[0-9]{3}-[0-9]{4}" required />
				</div>
				<div class="form-group">
					<label>메일</label>
					<input class="form-control" type="email" name="buyer_mail" value="${buyer.buyer_mail}" 
						maxlength="40" required />
				</div>
				<div class="form-group">
					<label>담당자</label>
					<input class="form-control" type="text" name="buyer_charger" value="${buyer.buyer_charger}" 
						maxlength="10" />
				</div>
				<div class="form-group">
					<label>내선번호</label>
					<input class="form-control" type="email" name="buyer_telext" value="${buyer.buyer_telext}" 
						maxlength="2" />
				</div>
				<div class="form-group">
					<input class="btn btn-info" type="submit" value="등록" />
					<input class="btn btn-info" type="reset" value="취소" />
				</div>
			</form>
			<div id="modalDiv">
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/zip.js"></script>
	<script type="text/javascript">
		let modalDiv = $("#modalDiv");
		let zipcode = $("[name='buyer_zip']");
		let addr1 = $("[name='buyer_add1']");
		let addr2 = $("[name='buyer_add2']");
		
		let tag = {};
		tag.modalDiv = modalDiv;
		tag.zipcode = zipcode;
		tag.addr1 = addr1;
		tag.addr2 = addr2;
		
		$("buyerRegistFrom").zipSearch({
			tag : tag,
			command : "modify",
			url : "${pageContext.request.contextPath}/dataTable.do",
		});
	</script>
</body>
</html>