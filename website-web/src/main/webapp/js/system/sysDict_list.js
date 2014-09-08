/**
 * 查询按钮
 */
function  search(){
  var dictName = $("#txt_dictName").val();
  var dictCode = $("#txt_dictCode").val();
  var params = {
		  dictName:dictName,
		  dictCode:dictCode
  };
  grid.reload();
}
