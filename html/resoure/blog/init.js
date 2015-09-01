
if(!window.slider) {
	var slider={};
}
var isNeedInit = true;
$(function(){
	$.get(basePath+"/doAction?method=q_initblog",{},function(data){
		data =  eval('('+data+')');
		slider.data =data.slider;
		slider.init();
		doInit(data);
	});
	//article
	$.get(basePath+"/doAction?method=q_initArticl",{},function(data){
		data =  eval('('+data+')');
		doInitArticle(data);
		//
		initPage(data.page);
	});
});
function initPage(maxPage){
	$("#Pagination").pagination(maxPage, {
		num_edge_entries: 2, //边缘页数
		num_display_entries: 4, //主体页数
		items_per_page: 1, //每页显示1项
		prev_text: "上一页",
		next_text: "下一页",
		callback: function(page_index,jq){
			if(!isNeedInit){//分页控件发起
				doQuery(page_index+1,true);
			}else{
				isNeedInit=false;
			}
			return false;
		}
	});
}
function doQuery(page){
	page = isNaN(page)?0:page;
	$.get(basePath+"/doAction?method=q_article",{"page":page,"pagesize":5},function(data){
		data =  eval('('+data+')');
		showData(data);
	});
}
function showData(data){
	var aa = $("#connntent");
	aa.empty();
	var atags = $('<span />',{"class":"lm f_l"});
	//var val = data.Rows;
	$(data.Rows).each(function(i,val) {
		var atags = $('<span />',{"class":"lm f_l"});
		$(val.tags).each(function(i,vl){
			atags.append($('<a />',{"href":vl.url,"text":vl.name}));
		});
		aa.append($('<div />',{"class":"blogs"}).append(
				$('<figure />').append($('<img />',{"src":val.thumb})),
				$('<ul />').append(
						$('<h3 />',{}).append($('<a />',{"href":val.url,"text":val.title,"target":"_blank"})),
						$('<p />',{"text":val.scont}),
						$('<p />',{"class":"autor"}).append(atags,
								$('<span />',{"class":"dtime f_l","text":val.time})
								)
						)
		));
	});
}

function doInit(data){
	//step1 slider
	//data =  eval('('+data+')');
	//slider.data =data.slider;
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

function doInitArticle(data){
	var aa = $("#connntent");
	var bb = $("#art-click");
	var cc = $("#art-new");
	var dd = $("#art-auto");
	var ee = $("#art-tuwen");
	$(data.article).each(function(i,val) {
		if(val.type==1){
			var atags = $('<span />',{"class":"lm f_l"});
			$(val.tags).each(function(i,vl){
				atags.append($('<a />',{"href":vl.url,"text":vl.name}));
			});
			aa.append($('<div />',{"class":"blogs"}).append(
					$('<figure />').append($('<img />',{"src":val.thumb})),
					$('<ul />').append(
							$('<h3 />',{}).append($('<a />',{"href":val.url,"text":val.title,"target":"_blank"})),
							$('<p />',{"text":val.scont}),
							$('<p />',{"class":"autor"}).append(atags,
									$('<span />',{"class":"dtime f_l","text":val.time})
									)
							)
			));
		}else if(val.type==2){
			bb.append($('<li />',{}).append($('<a />',{"href":val.url,"text":val.title,"target":"_blank"})));
		}else if(val.type==3){
			cc.append($('<li />',{}).append($('<a />',{"href":val.url,"text":val.title,"target":"_blank"})));
		}else if(val.type==4){
			dd.append($('<li />',{}).append($('<a />',{"href":val.url,"text":val.title,"target":"_blank"})));
		}else if(val.type==5){
			ee.append($('<li />',{}).append($('<a />',{"href":val.url}).append(
					$('<img />',{"src":val.thumb}),
					$('<b />',{"text":val.title})
				),
				$('<p />',{}).append($('<span />',{"class":"tulanmu"}),
						$('<span />',{"class":"tutime","text":val.time}
					)
			)));
		}
		
		//bb.prepend($('<a />',{"href":val.url,"target":"_blank"}).append($('<img />',{"src":val.picUrl,"id":val.id,"alt":""})));
	});
	
	//step2 tags
	var tt = $("#tags");
	$(data.tags).each(function(i,val) {
		tt.append($('<li />',{}).append($('<a />',{"href":val.url,"target":"_blank"}).append(val.name)));
	});
}