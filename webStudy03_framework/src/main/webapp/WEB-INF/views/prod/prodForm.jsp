<%@page import="java.util.Map.Entry"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<jsp:useBean id="errors" class="java.util.LinkedHashMap" scope="request"/>
	<form method="post">
		<table>
			<tr>
				<th>상품코드</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="text" readonly name="prod_id" 
							value="${prod.prod_id }" maxLength="10" />
						<span class="error"><%=errors.get("prod_id") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>상품명</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="text" required name="prod_name"
							value="${prod.prod_name }" maxLength="40" />
						<span class="error"><%=errors.get("prod_name") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>상품분류</th>
				<td>
					<div class="form-group form-inline">
						<select name="prod_lgu">
							<option>상품분류</option>
							<%
								List<Map<String, Object>> lprodList = (List) request.getAttribute("lprodList");
								if (lprodList != null && lprodList.size() != 0) {
									for (int i = 0; i < lprodList.size(); i ++) {
										Map<String, Object> lprodMap = lprodList.get(i);
										String lprod_gu = (String) lprodMap.get("lprod_gu");
										String lprod_nm = (String) lprodMap.get("lprod_nm");
							%>
							<option value="<%= lprod_gu %>"><%= lprod_nm %></option>
							<%				
									}
								}
							%>
						</select>
						<span class="error"><%=errors.get("prod_lgu") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>거래처코드</th>
				<td>
					<div class="form-group form-inline">
<!-- 						<input class="form-control mr-2" type="text" required name="prod_buyer" -->
<%-- 							value="${prod.prod_buyer }" maxLength="6" /> --%>
						<select name="prod_buyer">
							<option>거래처</option>
						<%
							List<BuyerVO> buyerList = (List) request.getAttribute("buyerList");
							if (buyerList != null && buyerList.size() != 0) {
								for (int i = 0; i < buyerList.size(); i ++) {
									BuyerVO buyer = buyerList.get(i);
						%>
							<option value="<%= buyer.getBuyer_id() %>"><%= buyer.getBuyer_name() %></option>
						<%
								}
							}
						%>	
						</select>
						<span class="error"><%=errors.get("prod_buyer") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>구매가</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="number" required
							name="prod_cost" value="${prod.prod_cost }" maxLength="22" />
						<span class="error"><%=errors.get("prod_cost") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>판매가</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="number" required
							name="prod_price" value="${prod.prod_price }" maxLength="22" />
						<span class="error"><%=errors.get("prod_price") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>세일가</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="number" required
							name="prod_sale" value="${prod.prod_sale }" maxLength="22" />
						<span class="error"><%=errors.get("prod_sale") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>상품개요</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="text" required
							name="prod_outline" value="${prod.prod_outline }" maxLength="100" />
						<span class="error"><%=errors.get("prod_outline") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>상세정보</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="text" name="prod_detail"
							value="${prod.prod_detail }" maxLength="4000" />
						<span class="error"><%=errors.get("prod_detail") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>상품이미지경로</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="text" required name="prod_img"
							value="${prod.prod_img }" maxLength="40" />
						<span class="error"><%=errors.get("prod_img") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>총재고</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="number" required
							name="prod_totalstock" value="${prod.prod_totalstock }"
							maxLength="22" />
						<span class="error"><%=errors.get("prod_totalstock") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>입고일</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="date" name="prod_insdate" readonly
							value="${prod.prod_insdate }" maxLength="7" />
						<span class="error"><%=errors.get("prod_insdate") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>적정재고</th>
				<td>	
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="number" required
							name="prod_properstock" value="${prod.prod_properstock }"
							maxLength="22" />
						<span class="error"><%=errors.get("prod_properstock") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>크기</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="text" name="prod_size"
							value="${prod.prod_size }" maxLength="20" />
						<span class="error"><%=errors.get("prod_size") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>색상</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="text" name="prod_color"
							value="${prod.prod_color }" maxLength="20" />
						<span class="error"><%=errors.get("prod_color") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>배송방법</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="text" name="prod_delivery"
							value="${prod.prod_delivery }" maxLength="255" />
						<span class="error"><%=errors.get("prod_delivery") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>단위</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="text" name="prod_unit"
							value="${prod.prod_unit }" maxLength="6" />
						<span class="error"><%=errors.get("prod_unit") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>입고량</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="number" name="prod_qtyin"
							value="${prod.prod_qtyin }" maxLength="22" />
						<span class="error"><%=errors.get("prod_qtyin") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>판매량</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="number" name="prod_qtysale"
							value="${prod.prod_qtysale }" maxLength="22" />
						<span class="error"><%=errors.get("prod_qtysale") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td>
					<div class="form-group form-inline">
						<input class="form-control mr-2" type="number" name="prod_mileage"
							value="${prod.prod_mileage }" maxLength="22" />
						<span class="error"><%=errors.get("prod_mileage") %></span>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit" class="btn btn-primary">전송</button>
					<button type="reset" class="btn btn-warning">취소</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>