<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<#--<link href="${rc.contextPath}/resoure/bootstrap/css/bootstrap.min.css" rel="stylesheet">-->
<link href="${rc.contextPath}/resoure/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${rc.contextPath}/resoure/blog/css/style.css" rel="stylesheet">
<script type="text/javascript" src="${rc.contextPath}/resoure/plug/prism/prism.js"></script>
<title>Insert title here</title>  
</head>  
<body>  

<div class="blogs">
<div class="content">
<header class="article-header">
<h1 class="article-title">
<a href="">${artv.title}</a>
</h1>
<div class="muted">
<span id="mute-category" class="muted">
		<i class="fa fa-list-alt"></i><a href=""> 
			<#if artv.btag? size gt 0 >
			<#list artv.btag as tag>
			&nbsp ${tag.name}
			</#list>
			</#if></a>
</span>
<span class="muted">
	<i class="fa fa-user"></i> 
	<a href="">${artv.artname}</a>
</span>
<time class="muted"><i class="fa fa-clock-o"></i> ${artv.time}</time>
<span class="muted"><i class="fa fa-eye"></i> 111</span>
<span class="muted"><i class="fa fa-comments-o"></i> <a href="#comments">0评论</a></span>

</div>
</header>

<article class="article-content">
${artv.content}

<p class="hey">代码：</p>
			<pre><code class="language-java">
			int i=1;
			for(i=0;i<5;i++){
			}</code></pre>

</article>

</div>



</div>



</body>

</html>