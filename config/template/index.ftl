<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<link href="${rc.contextPath}/resoure/plug/prism/prism.css" rel="stylesheet">
<script type="text/javascript" src="${rc.contextPath}/resoure/plug/prism/prism.js"></script>
<title>Insert title here</title>  
</head>  
<body>  
<#list users as user>  
username : ${user.userName}<br/>  
password : ${user.passWord}  
<p class="hey">Type some code here</p>
<pre><code class="language-java">
int i=1;
for(i=0;i<5;i++){
}</code></pre>

</#list>  
</body>

</html>