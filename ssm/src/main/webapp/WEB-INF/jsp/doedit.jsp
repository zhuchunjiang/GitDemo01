<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品修改</title>
</head>
<body>
	<div class="container">
	<form action="${basePath}items/edit.action"  method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${item.id}"/>
		<table>
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="name" value="${item.name}"/></td>
			</tr>
			<tr>
				<td>价格</td>
				<td><input type="text" name="price" value="${item.price}"/></td>
			</tr>
			<tr>
				<td>上传图片</td>
				<td>
				<img alt="商品图片" src="/pic/${item.pic}" width="100" height="100"><br/>
				<input type="file" name="pictureFile" value="${item.pic}"/>
					
				</td>
			</tr>
			<tr>
				<td>详细信息</td>
				<td><input type="text" name="detail" value="${item.detail}"/></td>
			</tr>
			<tr> 
				<td>创建时间</td>
				 
				<td><input type="text" name="createtime" value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="确认修改">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>