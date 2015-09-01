if(!window.slider) {
	var slider={};
}

var ddd = {
    "slide": [
        {
            "id": "11",
            "url": "/doAction?method=q_&tagid=1",
            "picUrl": "/resoure/blog/images/a1.jpg",
            "client": "标题1",
            "desc": "这里修改描述 这里修改描述 这里修改描述"
        },{
            "id": "11",
            "url": "/doAction?method=q_&tagid=1",
            "picUrl": "/resoure/blog/images/a1.jpg",
            "client": "标题1",
            "desc": "这里修改描述 这里修改描述 这里修改描述"
        },{
            "id": "11",
            "url": "/doAction?method=q_&tagid=1",
            "picUrl": "/resoure/blog/images/a1.jpg",
            "client": "标题1",
            "desc": "这里修改描述 这里修改描述 这里修改描述"
        },{
            "id": "11",
            "url": "/doAction?method=q_&tagid=1",
            "picUrl": "/resoure/blog/images/a1.jpg",
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
	var bb = $("#slide-controls");
	$(ddd.slide).each(function(i,val) {
		bb.prepend($('<a />',{"href":val.url}).append($('<img />'),{"src":val.picUrl,"id":val.id}));
	});
	
}