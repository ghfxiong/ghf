<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<title></title>
<link href="<%=request.getContextPath() %>/resoure/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/resoure/jsFile/jquery/jquery-1.11.3.min.js" type="text/javascript"></script> 
<script src="<%=request.getContextPath() %>/resoure/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/resoure/plug/pagination/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resoure/plug/pagination/pagination.css"/>
<!-- <style>
body{text-align:center}
.div{margin:0 auto;}
</style> -->
<body>
<script type="text/javascript">
var isBtn = true; //查询按钮发起标识
$(function(){
	$("#qryBtn").bind("click",function(){
		isBtn=true;
		queryCount();
		queryInfo(0,false);
	});	
});
function initPagination(maxPage){
	$("#Pagination").pagination(maxPage, {
		num_edge_entries: 2, //边缘页数
		num_display_entries: 4, //主体页数
		callback: pageselectCallback,
		items_per_page: 1, //每页显示1项
		prev_text: "前一页",
		next_text: "后一页"
	});
}
function pageselectCallback(page_index, jq){
	if(!isBtn){//分页控件发起
		queryInfo(page_index+1,true);
	}else{
		isBtn=false;
	}
	return false;
}
function queryCount(page,flag){
	var cond = $("#name").val();
	page = isNaN(page)?0:page;
	$.ajax({
		url: '<%=request.getContextPath() %>/doAction?method=q_kfinfocount&cond='+encodeURIComponent(encodeURIComponent(cond))+'&page='+page,
		//url: '/doAction?method=q_kfinfocount&cond='+encodeURIComponent(encodeURIComponent(cond))+'&page='+page,
		dataType: 'json',
		success: function(data) {
			initPagination(data.MaxPage);
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) { 
			alert("fuck! queryCount is error!");
		}
	});
}
function queryInfo(page,flag){
	var cond = $("#name").val();
	page = isNaN(page)?0:page;
	$.ajax({
		url: '<%=request.getContextPath() %>/doAction?method=q_kfinfo&cond='+encodeURIComponent(encodeURIComponent(cond))+'&page='+page,
		//url: '/doAction?method=q_kfinfo&cond='+encodeURIComponent(encodeURIComponent(cond))+'&page='+page,
		dataType: 'json',
		success: function(data) {
			showData(data);
		},
		error: function (XMLHttpRequest, textStatus, errorThrown) { 
			alert("fuck! queryInfo is error!");
		}
	});
}


function showData(data){
	var details = $("#content");
	details.empty();
	$(data.Rows).each(function(i,val) {
		var content = '<tr  class="success">'
			+'<td>'+val.name+'</td>'
			+'<td>'+val.ctfid+'</td>'
			+'<td>'+val.version+'</td>'
			+'<td>'+val.address+'</td>'
			+'<td>'+val.email+'</td>'
			+'<td>'+val.mobile+'</td></tr>';
		details.append(content);
	});
	//callback(events);
}
</script>

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<h3 class="text-center">
				社工库查询
			</h3>
		</div>
		
		<div class="span12">
			<form class="form-search">
				<input id="name" class="input-medium search-query" type="text" /> <button type="button" id="qryBtn" class="btn">查找</button>
			</form>
		</div>
		
		<table class="table">
			<thead>
				<tr>
					<th>姓名</th>
					<th>证件</th>
					<th>时间</th>
					<th>地点</th>
					<th>邮箱</th>
					<th>手机</th>
				</tr>
			</thead>
			<tbody id="content">
			</tbody>			
		</table>
	</div>
	<!--分页  -->
		<div style="text-align:center">		
			<div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
		</div>
</div>
</body>
</html>