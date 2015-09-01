<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="gb2312">
<title>GHF个人博客</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="<%=request.getContextPath()%>/resoure/blog/css/base.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resoure/blog/css/index.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/resoure/blog/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resoure/blog/js/sliders.js"></script>

<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/resoure/blog/js/modernizr.js"></script>
<![endif]-->
<!-- 返回顶部调用 begin -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resoure/blog/js/up/up.js"></script>
<!-- 返回顶部调用 end-->

<% String id = request.getParameter("id"); %>
</head>
<script type="text/javascript">
var basePath = "<%=request.getContextPath()%>";
if(!window.slider) {
	var slider={};
}
$(function(){
	//slider,tags
	$.get(basePath+"/doAction?method=q_initblog",{},function(data){
		doInit(data);
	});
	//article
	$.get(basePath+"/doAction?method=q_initArticl",{},function(data){
		doInitArticle(data);
	});
	
	//content
	$("#connntent").load("<%=request.getContextPath()%>/cpage/index?id=<%=id%>");
});
function doInit(data){
	//step1 slider
	data =  eval('('+data+')');
	/* slider.data =data.slider;
	var bb = $("#slide-runner");
	$(slider.data).each(function(i,val) {
		bb.prepend($('<a />',{"href":val.url,"target":"_blank"}).append($('<img />',{"src":val.picUrl,"id":val.id,"alt":""})));
	}); */
	
	//step2 tags
	var tt = $("#tags");
	$(data.tags).each(function(i,val) {
		tt.append($('<li />',{}).append($('<a />',{"href":val.url,"target":"_blank"}).append(val.name)));
	});
}

function doInitArticle(data){
	data =  eval('('+data+')');
	var aa = $("#connntent");
	var bb = $("#art-click");
	var cc = $("#art-new");
	var dd = $("#art-auto");
	var ee = $("#art-tuwen");
	$(data.article).each(function(i,val) {
		if(val.type==1){
			/* var atags = $('<span />',{"class":"lm f_l"});
			$(val.tags).each(function(i,vl){
				atags.append($('<a />',{"href":"/","text":vl.name}));
			});
			aa.append($('<div />',{"class":"blogs"}).append(
					$('<figure />').append($('<img />',{"src":val.thumb})),
					$('<ul />').append(
							$('<h3 />',{}).append($('<a />',{"href":"/","text":val.title})),
							$('<p />',{"text":val.scont}),
							$('<p />',{"class":"autor"}).append(atags,
									$('<span />',{"class":"dtime f_l","text":val.time})
									)
							)
			)); */
		}else if(val.type==2){
			bb.append($('<li />',{}).append($('<a />',{"href":"/","text":val.title})));
		}else if(val.type==3){
			cc.append($('<li />',{}).append($('<a />',{"href":"/","text":val.title})));
		}else if(val.type==4){
			dd.append($('<li />',{}).append($('<a />',{"href":"/","text":val.title})));
		}else if(val.type==5){
			ee.append($('<li />',{}).append($('<a />',{"href":"/"}).append(
					$('<img />',{"src":val.thumb}),
					$('<b />',{"text":val.title})
				),
				$('<p />',{}).append($('<span />',{"class":"tulanmu"}),
						$('<span />',{"class":"tutime","text":val.time}
					)
			)));
		}
		bb.prepend($('<a />',{"href":val.url,"target":"_blank"}).append($('<img />',{"src":val.picUrl,"id":val.id,"alt":""})));
	});
	
	//step2 tags
	var tt = $("#tags");
	$(data.tags).each(function(i,val) {
		tt.append($('<li />',{}).append($('<a />',{"href":val.url,"target":"_blank"}).append(val.name)));
	});
}
</script>
<body>
<header>
  <div class="logo f_l"> <a href="/"><img src="<%=request.getContextPath()%>/resoure/blog/images/logo.png"></a> </div>
  <nav id="topnav" class="f_r">
    <ul>
      <a href="<%=request.getContextPath()%>/jump?method=p_10005" >首页</a> <a href="home.jsp" target="_blank">关于我</a> <a href="p.html" target="_blank">文章</a> <a href="a.html" target="_blank">心情</a> <a href="c.html" target="_blank">相册</a> <a href="b.html" target="_blank">留言</a>
    </ul>
    <script src="<%=request.getContextPath()%>/resoure/blog/js/nav.js"></script> 
  </nav>
</header>
<article>
  <div class="l_box f_l">
    <div class="topnews">
      <h2><span></span><b></b></h2>
     <!-- article list -->
     <div id="connntent">
      <!-- load new article list -->
     <!-- article list end -->
    </div>
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
    <%-- <div class="ad"> <img src="<%=request.getContextPath()%>/resoure/blog/images/03.jpg"> </div> --%>
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
  <p class="ft-copyright">ghf博客 Design by ghf 蜀ICP备11002373号-1</p>
  <div id="tbox"> <a id="togbook" href="/"></a> <a id="gotop" href="javascript:void(0)"></a> </div>
</footer>
</body>
</html>