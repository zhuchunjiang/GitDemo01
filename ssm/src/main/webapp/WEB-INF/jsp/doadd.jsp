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
<title>商品添加</title>
</head>
<body>
	<div class="container">
	<form action="${basePath}items/additems.action"  method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>价格</td>
				<td><input type="text" name="price" /></td>
			</tr>
			<tr>
				<td>上传图片</td>
				<td>
				<input type="file" name="pictureFile" />
				</td>
			</tr>
			<tr>
				<td>详细信息</td>
				<td><input type="text" name="detail" /></td>
			</tr>
			<tr> 
				<td>创建时间</td>
				 
				<td><input type="text" name="createtime" value="2000-1-1 00:00:00"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="确认添加">
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>