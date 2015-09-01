<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<#--<link href="${rc.contextPath}/resoure/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
<link href="${rc.contextPath}/resoure/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${rc.contextPath}/resoure/plug/prism/prism.css" rel="stylesheet">
<script type="text/javascript" src="${rc.contextPath}/resoure/plug/prism/prism.js"></script>
<title>Insert title here</title>  
</head>  
<body>  
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
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
			<p class="text-muted">
			${artv.content}
			</p>
			<p class="hey">代码：</p>
			<pre><code class="language-java">
			int i=1;
			for(i=0;i<5;i++){
			}</code></pre>
		</div>
	</div>
</div>

</body>

</html>