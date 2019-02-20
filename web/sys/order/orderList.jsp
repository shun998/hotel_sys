<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- 包含公共的JSP代码片段 -->

<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/sys/style/js/jquery.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/sys/style/js/page_common.js"></script>
<link
	href="${pageContext.request.contextPath }/sys/style/css/common_style_blue.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/sys/style/css/index_1.css" />
<script type="text/javascript">
	/* setInterval(function() {
		window.location.href = "/wirelessplatform/client.html?method=list";
	}, 1000 * 50); */
</script>
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath }/sys/style/images/title_arrow.gif" />
				餐厅订单列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0"
			cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>订单编号</td>
					<td>餐桌名</td>
					<td>下单日期</td>
					<td>总金额</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData">
				<c:forEach items="${requestScope.pageBean.pageData}" var="o">
					<tr height="60">
						<td>${o.id}</td>
						<c:forEach  items="${applicationScope.table }" var="table">
							<c:if test="${o.table_id==table.id }">
								<c:set var="tableName" value="${table.tableName }"/>
							</c:if>
						</c:forEach>
						<td><c:out value="${tableName}"/></td>
						<td>${o.orderDate}</td>
						<td>${o.totalPrice}</td>
						<c:choose >
				 			<c:when test="${o.orderStatus==0}">
				 				<td>未结账</td>
				 			</c:when>
				 			<c:otherwise>
				 				<td>已结账</td>
				 			</c:otherwise>
				 		</c:choose>
						<td><a href="${pageContext.request.contextPath }/order?method=getOrderDetail&orderId=${o.id}" class="FunctionButton">详细</a>

							<a href="${pageContext.request.contextPath }/order?method=pay&tableId=${o.table_id}&orderId=${o.id}" class="FunctionButton">结账</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
		当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/order?method=getOrderList&currentPage=1">首页</a>
		<a href="${pageContext.request.contextPath }/order?method=getOrderList&currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>
		<a href="${pageContext.request.contextPath }/order?method=getOrderList&currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>
		<a href="${pageContext.request.contextPath }/order?method=getOrderList&currentPage=${requestScope.pageBean.totalPage}">末页</a>
		</div>
	</div>
</body>
</html>
