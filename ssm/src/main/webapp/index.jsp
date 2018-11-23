<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="basePath">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${basePath}items/vo.action">绑定包装pojo</a>
	<h3>动态页面跳转演示</h3>
	<a href="${basePath}items/demo01.action">demo01</a><br/>
	<a href="${basePath}items/demo02.action">demo02</a><br/>
	<a href="${basePath}items/demo03.action">demo03</a><br/>
	<a href="${basePath}items/itemList.action">查询所有商品信息</a>
	<h3>批量修改</h3>
	<a href="${basePath}items/dobatchEdit.action">查询所有商品信息</a>
	<h3>post请求</h3>
	<form action="${basePath}demo/demo01.action" method="post">
		<input type="submit" value="post请求">
	</form>
	<h4>REST风格请求</h4>
	/uid/{usersId}/uname/{uname}<br/>
	<a href="${basePath}demo/uid/110/uname/zhangsan">REST风格请求<</a>
</body>
</html>
