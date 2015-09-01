<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta charset="gb2312">
<title>GHF个人博客</title>
<!-- <meta name="keywords" content="" />
<meta name="description" content="" /> -->
<script type="text/javascript">var basePath="<%=request.getContextPath() %>";</script>
<link href="<%=request.getContextPath()%>/resoure/blog/css/base.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/resoure/blog/css/index.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resoure/blog/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resoure/blog/js/sliders.js"></script>
<!--[if lt IE 9]>
<script src="js/modernizr.js"></script>
<![endif]-->
<!-- 返回顶部调用 begin -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resoure/blog/js/up/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resoure/blog/js/up/js.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resoure/blog/slideData.js"/>
<!-- 返回顶部调用 end-->
</head>
<body>
<header>
  <div class="logo f_l"> <a href="http://www.baidu.com"><img src="<%=request.getContextPath() %>/resoure/blog/images/logo.png"></a> </div>
  <nav id="topnav" class="f_r">
    <ul>
      <a href="<%=request.getContextPath() %>/index.jsp" target="_blank">首页</a> 
      <a href="news.html" target="_blank">关于我</a> 
      <a href="p.html" target="_blank">文章</a> 
      <a href="a.html" target="_blank">心情</a> 
      <a href="c.html" target="_blank">相册</a> 
      <a href="b.html" target="_blank">留言</a>
    </ul>
    <script src="<%=request.getContextPath() %>/resoure/blog/js/nav.js"></script> 
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
      <h2>
      	<span>
	      	<a href="/www.baidu.com" target="_blank">栏目标题</a>
	      	<a href="/www.baidu.com" target="_blank">栏目标题</a>
	      	<a href="/www.baidu.com" target="_blank">栏目标题</a>
      	</span><b>文章</b>推荐</h2>
      <div class="blogs">
        <figure><img src="<%=request.getContextPath() %>/resoure/blog/images/01.jpg"></figure>
        <ul>
          <h3><a href="/www.baidu.com">住在手机里的朋友</a></h3>
          <p>通信时代，无论是初次相见还是老友重逢，交换联系方式，常常是彼此交换名片，然后郑重或是出于礼貌用手机记下对方的电话号码。在快节奏的生活里，我们不知不觉中就成为住在别人手机里的朋友。又因某些意外，变成了别人手机里匆忙的过客，这种快餐式的友谊 ...</p>
          <p class="autor"><span class="lm f_l"><a href="/www.baidu.com">个人博客</a></span><span class="dtime f_l">2014-02-19</span><span class="viewnum f_r">浏览（<a href="/www.baidu.com">459</a>）</span><span class="pingl f_r">评论（<a href="/www.baidu.com">30</a>）</span></p>
        </ul>
      </div>
      <div class="blogs">
        <figure><img src="<%=request.getContextPath() %>/resoure/blog/images/02.jpg"></figure>
        <ul>
          <h3><a href="/www.baidu.com">教你怎样用欠费手机拨打电话</a></h3>
          <p>初次相识的喜悦，让你觉得似乎找到了知音。于是，对于投缘的人，开始了较频繁的交往。渐渐地，初识的喜悦退尽，接下来就是仅仅保持着联系，平淡到偶尔在节假曰发短信互致问候...</p>
          <p class="autor"><span class="lm f_l"><a href="/www.baidu.com">个人博客</a></span><span class="dtime f_l">2014-02-19</span><span class="viewnum f_r">浏览（<a href="/www.baidu.com">459</a>）</span><span class="pingl f_r">评论（<a href="/www.baidu.com">30</a>）</span></p>
        </ul>
      </div>
      <div class="blogs">
        <figure><img src="<%=request.getContextPath() %>/resoure/blog/images/03.jpg"></figure>
        <ul>
          <h3><a href="/www.baidu.com">原来以为，一个人的勇敢是，删掉他的手机号码...</a></h3>
          <p>原来以为，一个人的勇敢是，删掉他的手机号码、QQ号码等等一切，努力和他保持距离。等着有一天，习惯不想念他，习惯他不在身边,习惯时间把他在我记忆里的身影磨蚀干净... </p>
          <p class="autor"><span class="lm f_l"><a href="/www.baidu.com">个人博客</a></span><span class="dtime f_l">2014-02-19</span><span class="viewnum f_r">浏览（<a href="/www.baidu.com">459</a>）</span><span class="pingl f_r">评论（<a href="/www.baidu.com">30</a>）</span></p>
        </ul>
      </div>
      <div class="blogs">
        <figure><img src="<%=request.getContextPath() %>/resoure/blog/<%=request.getContextPath() %>/resoure/blog/images/04.jpg"></figure>
        <ul>
          <h3><a href="/www.baidu.com">手机的16个惊人小秘密，据说99.999%的人都不知</a></h3>
          <p>引导语：知道么，手机有备用电池，手机拨号码12593+电话号码=陷阱……手机具有很多你不知道的小秘密，说出来一定很惊奇！不信的话就来看看吧！...</p>
          <p class="autor"><span class="lm f_l"><a href="/www.baidu.com">个人博客</a></span><span class="dtime f_l">2014-02-19</span><span class="viewnum f_r">浏览（<a href="/www.baidu.com">459</a>）</span><span class="pingl f_r">评论（<a href="/www.baidu.com">30</a>）</span></p>
        </ul>
      </div>
      <div class="blogs">
        <figure><img src="<%=request.getContextPath() %>/resoure/blog/images/05.jpg"></figure>
        <ul>
          <h3><a href="/www.baidu.com">你面对的是生活而不是手机</a></h3>
          <p>每一次与别人吃饭，总会有人会拿出手机。以为他们在打电话或者有紧急的短信，但用余光瞟了一眼之后发现无非就两件事：1、看小说，2、上人人或者QQ...</p>
          <p class="autor"><span class="lm f_l"><a href="/www.baidu.com">个人博客</a></span><span class="dtime f_l">2014-02-19</span><span class="viewnum f_r">浏览（<a href="/www.baidu.com">459</a>）</span><span class="pingl f_r">评论（<a href="/www.baidu.com">30</a>）</span></p>
        </ul>
      </div>
      <div class="blogs">
        <figure><img src="<%=request.getContextPath() %>/resoure/blog/images/06.jpg"></figure>
        <ul>
          <h3><a href="/www.baidu.com">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></h3>
          <p>现在跨界联姻，时尚、汽车以及运动品牌联合手机制造商联合发布手机产品在行业里已经不再新鲜，上周我们给大家报道过著名手表制造商瑞士泰格·豪雅（Tag Heuer） 联合法国的手机制造商Modelabs发布的一款奢华手机的部分谍照，而近日该手机终于被正式发布了...</p>
          <p class="autor"><span class="lm f_l"><a href="/www.baidu.com">个人博客</a></span><span class="dtime f_l">2014-02-19</span><span class="viewnum f_r">浏览（<a href="/www.baidu.com">459</a>）</span><span class="pingl f_r">评论（<a href="/www.baidu.com">30</a>）</span></p>
        </ul>
      </div>
      <div class="blogs">
        <figure><img src="<%=request.getContextPath() %>/resoure/blog/images/04.jpg"></figure>
        <ul>
          <h3><a href="/www.baidu.com">手机的16个惊人小秘密，据说99.999%的人都不知</a></h3>
          <p>引导语：知道么，手机有备用电池，手机拨号码12593+电话号码=陷阱……手机具有很多你不知道的小秘密，说出来一定很惊奇！不信的话就来看看吧！...</p>
          <p class="autor"><span class="lm f_l"><a href="/www.baidu.com">个人博客</a></span><span class="dtime f_l">2014-02-19</span><span class="viewnum f_r">浏览（<a href="/www.baidu.com">459</a>）</span><span class="pingl f_r">评论（<a href="/www.baidu.com">30</a>）</span></p>
        </ul>
      </div>
    </div>
  </div>
  <div class="r_box f_r">
    <div class="tit01">
      <h3>关注我</h3>
      <div class="gzwm">
        <ul>
          <li><a class="xlwb" href="www.weibo.com/ghfxiong" target="_blank">新浪微博</a></li>
          <li><a class="txwb" href="#" target="_blank">腾讯微博</a></li>
          <li><a class="rss" href="portal.php?mod=rss" target="_blank">RSS</a></li>
          <li><a class="wx" href="mailto:841567545@qq.com">邮箱</a></li>
        </ul>
      </div>
    </div>
    <!--tit01 end-->
    <div class="ad300x100"> <img src="<%=request.getContextPath() %>/resoure/blog/images/ad300x100.jpg"> </div>
    <div class="moreSelect" id="lp_right_select"> 
      <script>
window.onload = function ()
{
	bulidSlider();
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
			oUl[this.index].style.display = "block";
		};
	}
};
</script>
      <div class="ms-top">
        <ul class="hd" id="tab">
          <li class="cur"><a href="/www.baidu.com">点击排行</a></li>
          <li><a href="/www.baidu.com">最新文章</a></li>
          <li><a href="/www.baidu.com">站长推荐</a></li>
        </ul>
      </div>
      <div class="ms-main" id="ms-main">
        <div style="display: block;" class="bd bd-news" >
          <ul>
            <li><a href="www.baidu.com" target="_blank">住在手机里的朋友</a></li>
            <li><a href="/www.baidu.com" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="/www.baidu.com" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
            <li><a href="/www.baidu.com" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="/www.baidu.com" target="_blank">你面对的是生活而不是手机</a></li>
            <li><a href="/www.baidu.com" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
          </ul>
        </div>
        <div  class="bd bd-news">
          <ul>
            <li><a href="/www.baidu.com" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
            <li><a href="/www.baidu.com" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="/www.baidu.com" target="_blank">住在手机里的朋友</a></li>
            <li><a href="/www.baidu.com" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="/www.baidu.com" target="_blank">你面对的是生活而不是手机</a></li>
            <li><a href="/www.baidu.com" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
          </ul>
        </div>
        <div class="bd bd-news">
          <ul>
            <li><a href="/www.baidu.com" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
            <li><a href="/www.baidu.com" target="_blank">你面对的是生活而不是手机</a></li>
            <li><a href="/www.baidu.com" target="_blank">住在手机里的朋友</a></li>
            <li><a href="/www.baidu.com" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
            <li><a href="/www.baidu.com" target="_blank">教你怎样用欠费手机拨打电话</a></li>
            <li><a href="/www.baidu.com" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
          </ul>
        </div>
      </div>
      <!--ms-main end --> 
    </div>
    <!--切换卡 moreSelect end -->
    
    <div class="cloud">
      <h3>标签云</h3>
      <ul>
        <li><a href="/www.baidu.com">个人博客</a></li>
        <li><a href="/www.baidu.com">web开发</a></li>
        <li><a href="/www.baidu.com">前端设计</a></li>
        <li><a href="/www.baidu.com">Html</a></li>
        <li><a href="/www.baidu.com">CSS3</a></li>
        <li><a href="/www.baidu.com">Html5+css3</a></li>
        <li><a href="/www.baidu.com">百度</a></li>
        <li><a href="/www.baidu.com">Javasript</a></li>
        <li><a href="/www.baidu.com">web开发</a></li>
        <li><a href="/www.baidu.com">前端设计</a></li>
        <li><a href="/www.baidu.com">Html</a></li>
        <li><a href="/www.baidu.com">CSS3</a></li>
        <li><a href="/www.baidu.com">Html5+css3</a></li>
        <li><a href="/www.baidu.com">百度</a></li>
      </ul>
    </div>
    <div class="tuwen">
      <h3>图文推荐</h3>
      <ul>
        <li><a href="/www.baidu.com"><img src="<%=request.getContextPath() %>/resoure/blog/images/01.jpg"><b>住在手机里的朋友</b></a>
          <p><span class="tulanmu"><a href="/www.baidu.com">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/www.baidu.com"><img src="<%=request.getContextPath() %>/resoure/blog/images/02.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
          <p><span class="tulanmu"><a href="/www.baidu.com">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/www.baidu.com" title="手机的16个惊人小秘密，据说99.999%的人都不知"><img src="images/03.jpg"><b>手机的16个惊人小秘密，据说...</b></a>
          <p><span class="tulanmu"><a href="/www.baidu.com">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/www.baidu.com"><img src="<%=request.getContextPath() %>/resoure/blog/images/06.jpg"><b>住在手机里的朋友</b></a>
          <p><span class="tulanmu"><a href="/www.baidu.com">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
        <li><a href="/www.baidu.com"><img src="<%=request.getContextPath() %>/resoure/blog/images/04.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
          <p><span class="tulanmu"><a href="/www.baidu.com">手机配件</a></span><span class="tutime">2015-02-15</span></p>
        </li>
      </ul>
    </div>
    <div class="ad"> <img src="<%=request.getContextPath() %>/resoure/blog/images/03.jpg"> </div>
    <div class="links">
      <h3><span>[<a href="/www.baidu.com">申请友情链接</a>]</span>友情链接</h3>
      <ul>
        <li><a href="/www.baidu.com">ghf个人博客</a></li>
        <li><a href="/www.baidu.com">web开发</a></li>
        <li><a href="/www.baidu.com">前端设计</a></li>
        <li><a href="/www.baidu.com">Html</a></li>
        <li><a href="/www.baidu.com">CSS3</a></li>
        <li><a href="/www.baidu.com">Html5+css3</a></li>
        <li><a href="/www.baidu.com">百度</a></li>
      </ul>
    </div>
  </div>
  <!--r_box end --> 
</article>
<footer>
  <p class="ft-copyright">ghf博客 Design by ghf 蜀ICP备11002373号-1</p>
  <div id="tbox"> <a id="togbook" href="/www.baidu.com"></a> <a id="gotop" href="javascript:void(0)"></a> </div>
</footer>
</body>
</html>
