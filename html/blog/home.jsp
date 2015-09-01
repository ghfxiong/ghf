<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>GHF个人博客</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="<%=request.getContextPath()%>/resoure/blog/css/base.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resoure/blog/css/index.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/resoure/blog/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resoure/blog/js/sliders.js"></script>
<script src="<%=request.getContextPath() %>/resoure/plug/pagination/jquery.pagination.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resoure/plug/pagination/pagination.css"/>
<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/resoure/blog/js/modernizr.js"></script>
<![endif]-->
<!-- 返回顶部调用 begin -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resoure/blog/js/up/up.js"></script>
<!-- 返回顶部调用 end-->
</head>
<script type="text/javascript">
var basePath = "<%=request.getContextPath()%>";
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resoure/blog/init.js"></script>
<body>
<header>
  <div class="logo f_l"> <a href="/"><img src="<%=request.getContextPath()%>/resoure/blog/images/logo.png"></a> </div>
  <nav id="topnav" class="f_r">
    <ul>
      <a href="<%=request.getContextPath()%>/jump?method=p_10005" target="">首页</a> <a href="news.html" target="_blank">关于我</a> <a href="<%=request.getContextPath()%>/jump?method=p_10006&id=1" target="_blank">文章</a> <a href="a.html" target="_blank">心情</a> <a href="c.html" target="_blank">相册</a> <a href="b.html" target="_blank">留言</a>
    </ul>
    <script src="<%=request.getContextPath()%>/resoure/blog/js/nav.js"></script> 
  </nav>
</header>
<article>
  <div class="l_box f_l">
    <div class="banner">
      <div id="slide-holder">
        <div id="slide-runner"> 
          <div id="slide-controls">
            <p id="slide-client" class="text"><strong></strong><span></span></p>
            <p id="slide-desc" class="text"></p>
            <p id="slide-nav"></p>
          </div>
        </div>
      </div>
    </div>
    <!-- banner代码 结束 -->
    <div class="topnews">
      <h2><span><a href="/" target="_blank">栏目标题</a><a href="/" target="_blank">栏目标题</a><a href="/" target="_blank">栏目标题</a></span><b>文章</b>推荐</h2>
     <!-- article list -->
     <div id="connntent">
      <!-- load new article list -->
     <!-- article list end -->
    </div>
  </div>
  <!-- 分页  -->
  <div style="text-align:center">		
	<div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
  </div>
  </div>
  <div class="r_box f_r">
    <div class="tit01">
      <h3>关注我</h3>
      <div class="gzwm">
        <ul>
          <li><a class="xlwb" href="http://www.weibo.com/ghfxiong" target="_blank">新浪微博</a></li>
          <li><a class="txwb" href="#" target="_blank">腾讯微博</a></li>
          <li><a class="rss" href="portal.php?mod=rss" target="_blank">RSS</a></li>
          <li><a class="wx" href="mailto:841567545@qq.com">邮箱</a></li>
        </ul>
      </div>
    </div>
    <!--tit01 end-->
    <div class="ad300x100"> <img src="<%=request.getContextPath()%>/resoure/blog/images/ad300x100.jpg"> </div>
    <div class="moreSelect" id="lp_right_select"> 
      <script>
window.onload = function ()
{
	var oLi = document.getElementById("tab").getElementsByTagName("li");
	var oUl = document.getElementById("ms-main").getElementsByTagName("div");
	
	for(var i = 0; i < oLi.length; i++)
	{
		oLi[i].index = i;
		oLi[i].onmouseover = function ()
		{
			for(var n = 0; n < oLi.length; n++) oLi[n].className="";
			this.className = "cur";
			for(var n = 0; n < oUl.length; n++) oUl[n].style.display = "none";
			oUl[this.index].style.display = "block"
		};
	};
};
</script>
      <div class="ms-top">
        <ul class="hd" id="tab">
          <li class="cur"><a href="/">最新文章</a></li>
          <li><a href="/">站长推荐</a></li>
          <li><a href="/">点击排行</a></li>
        </ul>
      </div>
      <div class="ms-main" id="ms-main">
        <div style="display: block;" class="bd bd-news" >
          <ul id="art-new">
          </ul>
        </div>
        <div  class="bd bd-news">
          <ul id="art-auto">
          </ul>
        </div>
        <div class="bd bd-news">
          <ul id="art-click">
          </ul>
        </div>
      </div>
      <!--ms-main end --> 
    </div>
    <!--切换卡 moreSelect end -->
    
    <div class="cloud">
      <h3>标签云</h3>
      <ul id="tags">
      </ul>
    </div>
    <div class="tuwen">
      <h3>图文推荐</h3>
      <ul id="art-tuwen">
        
      </ul>
    </div>
    <div class="ad"> <img src="<%=request.getContextPath()%>/resoure/blog/images/03.jpg"> </div>
    <div class="links">
      <h3><span>[<a href="/">申请友情链接</a>]</span>友情链接</h3>
      <ul>
        <li><a href="/">ghf个人博客</a></li>
        <li><a href="/">web开发</a></li>
        <li><a href="/">前端设计</a></li>
        <li><a href="/">Html</a></li>
        <li><a href="/">CSS3</a></li>
        <li><a href="/">Html5+css3</a></li>
        <li><a href="/">百度</a></li>
      </ul>
    </div>
  </div>
  <!--r_box end --> 
</article>
<footer>
  <p class="ft-copyright">ghf博客 Design by DanceSmile <a href="<%=request.getContextPath()%>/jump?method=p_10007" target="_blank">发布</a></p>
  <div id="tbox"> <a id="togbook" href="/"></a> <a id="gotop" href="javascript:void(0)"></a> </div>
</footer>
</body>
</html>
