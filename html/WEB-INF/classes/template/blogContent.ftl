<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<#--<link href="${rc.contextPath}/resoure/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
<link href="${rc.contextPath}/resoure/plug/prism/prism.css" rel="stylesheet">
<script type="text/javascript" src="${rc.contextPath}/resoure/plug/prism/prism.js"></script>
<title>Insert title here</title>  
</head>  
<body>  
<div style="font-size: 14px">
	
		
			<h3><p> ${artv.title}</p></h3>
			<hr></hr>
			<p>发布时间：${artv.time} &nbsp &nbsp &nbsp 作者 ${artv.artname}</p>
			<#if artv.btag? size gt 0 >
			标签：
			<#list artv.btag as tag>
			&nbsp ${tag.name}
			</#list>
			</#if>
			<hr><hr>
			<div id="ucotent" style="word-wrap:break-word">
			<p class="">
			${artv.content}
			</p>
			
			</div>
</div>

</body>

</html>
<script type="text/javascript" src="${rc.contextPath}/resoure/plug/ueditor1_4_3/ueditor.parse.min.js"></script>
<script >uParse('#ucotent', {
    rootPath: '${rc.contextPath}/resoure/plug/ueditor1_4_3/'
})</script>