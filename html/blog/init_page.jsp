<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/resoure/blog/js/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
var basePath = "<%=request.getContextPath()%>";
if(!window.slider) {
	var slider={};
}
$(function(){
	$.post(basePath+"/doAction?method=q_initblog",{},function(data){
		doInit(data);
	});
});
//slider,tags
function doInit(data){
	//step1 slider
	slider.data = data.slider.slider.data;
	var bb = $("#slide-runner");
	$(slider.data).each(function(i,val) {
		bb.prepend($('<a />',{"href":val.url,"target":"_blank"}).append($('<img />',{"src":val.picUrl,"id":val.id,"alt":""})));
	});
	
	//step2 tags
	var tt = $("#tags");
	$(data.tags).each(function(i,val) {
		tt.append($('<li />',{}).append($('<a />',{"href":val.url,"target":"_blank"}).append(val.name)));
	});
}
</script>



</body>
</html>
