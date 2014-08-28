
var i=0;//记录开户信息数目
var stateId=0;
$(document).ready(function(){
	if($('#txt_bankNumber').val()>0){
		i=$('#txt_bankNumber').val();
	}
	 stateId=$('#txt_stateId').val();
});
function addBank(){
	$.get("../dict/getAllBank.json", function(data){
		data=eval("(" + data + ")");
		if(data!=null){
			var option='';
			 $.each(data, function(j,item){
				 option+="<option value='"+item.code+"'>"+item.name+"</option>";
			 });
			var obj=$("<tr id='bank"+i+"'>" +
					"<th>站点开户银行：</th>" +
					"<td >" +
					"<span><select class='jessrun-select {required:true}' name='bicStateBankLists["+i+"].stateBankId' id='ddl_bank"+i+"' onchange=changeBank("+i+") ><option value=''>--请选择--</option>"+option+"</select><span class='requried'>*</span></span><input type='hidden' name='bicStateBankLists["+i+"].stateBankName' id='txt_stateBankName"+i+"' value=''  />" +
			       		"<input type='hidden' name='bicStateBankLists["+i+"].stateId' value='"+stateId+"'  />" +
		       		"</td>"+
		           	"<th>站点开户名：</th>" +
		           	"<td ><span><input type=text' name='bicStateBankLists["+i+"].stateBankManName' class='jessrun-txt  {required:true}'/><span class='requried'>*</span></span></td>"+
		           	"<th>站点开户账号：</th>" +
		           	"<td ><span><input type='text' name='bicStateBankLists["+i+"].stateBankManCode'  class='jessrun-txt  {required:true}'/><span class='requried'>*</span></span></td>"+
		           	"<td><a href='javascript:void(0)' onclick='delBank("+i+")' class='jessrun-btn'><span>删除</span></a></td></tr>");
			obj.insertBefore("#tr_bank0");
			i++;
		}
	} );
}

function delBank(id){
	$("#bank"+id).remove();
}

function changeBank(id){
	$("#txt_stateBankName"+id).attr("value",$("#ddl_bank"+id+" option:selected").text());
}

$(function(){
	jessrun.formSubmit("stateEditBtn","stateEditForm","loadFormSubmit","站点信息保存中");
});
