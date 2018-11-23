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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品展示页面</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="resources/bootstrap-3.3.7-dist/jquery/jquery.min.js"></script>
<script type="text/javascript">
	//多选删除
	/* function doMoreDel(){
		//console.log($("input[name=ids]"));
		var ids
		$.each($("input[name=ids]"),function(i,ele){//i下标   ele当前元素
			console.log($(ele).val());
			console.log($(ele).attr("checked"));
		
			if($(ele).attr("checked")=="checked"){
				$(ele);
			}
		});
	} */
	function doSubmit() {
		var flag = confirm("是否确认删除？");
		return flag
	}
	function doDel(id){
		var flag = confirm("是否确认删除？");
		if(flag){
			window.location.href="${basePath}items/delitems.action?id="+id;
		}
	}
	function dodetails(id){
		$.ajax({
			//url:"${basePath}items/dodetails.action?id="+id,
		    //  http://localhost:8088/ssm/items/dodetails/1
			url:"${basePath}items/dodetails/"+id,
			type:"get",
			dataType:"json", //响应的类型
			success:function(data){ //响应回来的内容
				console.log(data);
				$("#myModal .modal-body>table tr:gt(0)").remove();
				var str="<tr><td>"+data.name+"</td><td>"+data.price+"</td><td><img src='/pic/"+data.pic+"' width='200' height='200' /></td><td>"+data.detail+"</td><td>"+data.createtime+"</td></tr>";
				$("#myModal .modal-body>table tr:eq(0)").after(str);
			}
		});
	}
</script>
</head>
<body>
	<div class="container">
		<form action="${basePath}items/delmore.action" method="post"
			onsubmit="return doSubmit()">
			<table class="table table-bordered table-hover" name="mytable">
				<tr>
					<th>删除选中</th>
					<th>名称</th>
					<th>价格</th>
					<th>图片</th>
					<th>详细信息</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${itemList}" var="items">
					<tr>
						<td><input name="ids" value="${items.id}" type="checkbox"></td>
						<td>${items.name }</td>
						<td>${items.price }</td>
						<td><img src="/pic/${items.pic}" width="100" height="100" /></td>
						<td>${items.detail }</td>
						<td><fmt:formatDate value="${items.createtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a href="items/doedit.action?id=${items.id}"
							class="btn btn-default">修改</a> <a href="javascript:void(0)"
							onclick="doDel(${items.id})" class="btn btn-default">删除当前</a>
							<a href="javascript:void(0)"
							onclick="dodetails(${items.id})" class="btn btn-default" data-toggle="modal" data-target="#myModal">详情</a>
							</td>
					</tr>
				</c:forEach>
			</table>
			<input type="submit" value="删除" class="btn btn-default" />&nbsp;&nbsp;&nbsp;<a
				href="${pagePath}items/doadd.action" class="btn btn-default">添加</a>
		</form>
	</div>
	<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">Modal title</h4>
					</div>
					<div class="modal-body">
						<table class="table table-bordered">
							<tr>
								<th>名称</th>
								<th>价格</th>
								<th>图片</th>
								<th>详细信息</th>
								<th>创建时间</th>
							</tr>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save changes</button>
					</div>
				</div>
			</div>
		</div>
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script
		src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>