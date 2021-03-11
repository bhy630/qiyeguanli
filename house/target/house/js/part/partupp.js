$(function(){
 
  init();
  //数据验证
  checkItem();
  //提交表单位
   commitItem();
});
function init() {
	var pid=$.getParameter("pid");
	$.ajax({
		url:'http://localhost:8080/DeptController?type=toUpdate',
		data:{pid:pid},
		dataType:'json',
		type:'post',
		success:function(mydata)
		{
			$("#pname").val(mydata.pname);
			$("#premark").val(mydata.premark);
			$("#pid").val(mydata.pid);
			$("#pflag").val(mydata.pflag);
		}
	});
	
};
/*****************************************/
/******************失去焦点事件****************************/
function checkItem()
{
	$("#pname").focusout(function(){
		var pname=$("#pname").val();
		if(pname.length==0)
			{
			   layer.tips('部门名称不能为空！','#pname',{tips:[2,'red']});
			}
		else
			{
			   $.ajax({
				   url:'part_getAllName.action',
				   dataType:'json',
				   type:'post',
				   data:{pname:pname},
				   async : true,
				   success:function(mydata)
				   {
					   if(mydata==0)
						   {
						      $("#pname").addClass("newsuccess");
					          $("#pname").removeClass("newerror");
						   }
					   else
						   {
						       layer.tips('对不起部门已存在！','#pname',{tips:[2,'red']});
						   }
					   $("#botao").val(mydata);
				   }
			   });
			}
	});
}



/******************************提交表单********************************/
function commitItem()
{
	$(".btn").bind("click",function(){	
	var pname = $("#pname").val();
	var premark = $("#premark").val();
	var pid=$("#pid").val();
	var pflag=$("#pflag").val();
	if(pname.length==0)
		{
		   layer.tips('部门名称不能为空！','#pname',{tips:[2,'red']});
		   $("#pname").focus();
		   return false;
		}
	else if($("#botao").val()>0)
		{
		  layer.tips('对不起部门已存在！','#pname',{tips:[2,'red']});
		  $("#pname").focus();
		  return false;
		}
	else
		{
		   var mypart = "dept.pname=" + pname + "&dept.premark=" + premark+ "&dept.pid="+ pid + "&dept.pflag=" + pflag;
		   var i = layer.load(0);
		   $.post('http://localhost:8080/DeptController?type=update',mypart,function(mydata){
			 layer.close(i);
			 if(mydata==1)
				 {
				   parent.layer.msg('修改成功！', {icon : 6,time : 3000});
				   var index = parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
				   parent.layer.close(index);
				 }
			 else
				 {
				     parent.layer.msg('修改失败', 2, 9);
				 }
		   },'json');
		}
	});
}

$.extend({
	getParameter: function (name) {
	var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href);
	if (!results) { return nu11; }
	return unescape(results [1])||nu1l;
	}
});