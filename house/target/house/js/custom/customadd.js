$(function(){
	//init();
	//数据验证
	//checkItem();
	//提交表单位
	commitItem();
});
/****************获得焦点同时得到两个下拉框的内容********************/
function init() {
	$("#erealname").focus();
	//得到下拉框的值 
	$.ajax({
		url:'emp_allx.action',
		dataType:'json',
		type:'post',
		data:'',
		async : true,
		success:function(mydata)
		{
		   $.each(mydata,function(index,xx){
			   $("#pid").append("<option value="+xx.pid+">"+xx.pname+"</option>");
		   });
		}
	});
	
	$.ajax({
		url:'emp_ally.action',
		dataType:'json',
		type:'post',
		data:'',
		async : true,
		success:function(mydata)
		{

		   $.each(mydata,function(index,xx){
			   $("#jid").append("<option value="+xx.jid+">"+xx.jname+"</option>");
		   });
		}
	});
};
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
	
	var cname = $("#cname").val();
	var csex =  $('input:radio[name="csex"]:checked').val();
	
	var ctel = $("#ctel").val();
	var ctel1 = $("#ctel1").val();
	var ccard = $("#ccard").val();
	
	if(cname.length==0)
		{
		   layer.tips('客户姓名不能为空！','#cname',{tips:[2,'red']});
		   $("#cname").focus();
		   return false;
		}
	else if(ctel.length==0)
		{
		   layer.tips('客户电话不能为空！','#ctel',{tips:[2,'red']});
		   $("#ctel").focus();
		   return false;
		}
	else if(ccard.length==0)
		{
		   layer.tips('客户身份证不能为空！','#ccard',{tips:[2,'red']});
		   $("#ccard").focus();
		   return false;
		}
	else
		{
		   var mypart = "cus.cname=" + cname + "&cus.csex=" + csex+ "&cus.ctel="+ctel+"&cus.ctel1="+ctel1+"&cus.ccard="+ccard+"";
			// var mypart = "serviceName=add&cus.cname=" + cname + "&cus.csex=" + csex+ "&cus.ctel="+ctel+"&cus.ctel1="+ctel1+"&cus.ccard="+ccard+"";

			var i = layer.load(0);
		   // $.post('http://localhost:8089/house_system/customController',mypart,function(mydata){
			   $.post('http://localhost:8080/CustomController?type=add',mypart,function(mydata){
				   layer.close(i);
			 if(mydata=="1")
				 {
                   parent.layer.msg('增加成功！', {icon : 6,time : 3000});
				   var index = parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
				   parent.layer.close(index);
				 }
			 else
				 {
				     parent.layer.msg('增加失败', 2, 9);
				 }
		   },'json');
		}
	});
}