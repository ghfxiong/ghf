if(!window.slider) {
	var slider={};
}
$(function(){
	bulidSlider();
});
var ddd = {
    "slide": [
        {
            "id": "slide-img-1",
            "url": basePath+"/jump?method=p_10001&tagid=1",
            "picUrl": basePath+"/resoure/blog/images/a1.jpg",
            "client": "标题1",
            "desc": "这里修改描述 这里修改描述 这里修改描述"
        },{
            "id": "slide-img-2",
            "url": basePath+"/doAction?method=q_&tagid=1",
            "picUrl": basePath+"/resoure/blog/images/a2.jpg",
            "client": "标题1",
            "desc": "这里修改描述 这里修改描述 这里修改描述"
        },{
            "id": "slide-img-3",
            "url": basePath+"/doAction?method=q_&tagid=1",
            "picUrl": basePath+"/resoure/blog/images/a3.jpg",
            "client": "标题1",
            "desc": "这里修改描述 这里修改描述 这里修改描述"
        },{
            "id": "slide-img-4",
            "url": "/doAction?method=q_&tagid=1",
            "picUrl": basePath+"/resoure/blog/images/a4.jpg",
            "client": "标题1",
            "desc": "这里修改描述 这里修改描述 这里修改描述"
        }
    ]
};

/*slider.data= [
  {
      "id":"slide-img-1", // 与slide-runner中的img标签id对应
      "client":"标题1",
      "desc":"这里修改描述 这里修改描述 这里修改描述" //这里修改描述
  },
  {
      "id":"slide-img-2",
      "client":"标题2",
      "desc":"add your description here"
  },
  {
      "id":"slide-img-3",
      "client":"标题3",
      "desc":"add your description here"
  },
  {
      "id":"slide-img-4",
      "client":"标题4",
      "desc":"add your description here"
  } 
];*/
slider.data = ddd.slide;

//ajax 获取slide

function bulidSlider(){
	//$("#slide-runner")
	//var bb = $("#slide-controls");
	var bb = $("#slide-runner");
	$(ddd.slide).each(function(i,val) {
		//var cc = $('<a />',{"href":val.url});
		//cc.append($('<img />',{"src":val.picUrl,"id":val.id}));
		//bb.before(bb);
		//bb.before($('<a />',{"href":val.url,"target":"_blank"}).append($('<img />',{"src":val.picUrl,"id":val.id,"alt":""})));
		bb.prepend($('<a />',{"href":val.url,"target":"_blank"}).append($('<img />',{"src":val.picUrl,"id":val.id,"alt":""})));
	});
	
}