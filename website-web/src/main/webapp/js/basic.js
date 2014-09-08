var EasyUI = {
	  //获取id控件的值
      getValue : function(id){
    	  return $("#"+id).val();
      },
      //easyui datagrid id的值, 保持当前页面的值，load方法是第一页
      reload : function(id){
    	  $('#'+id).datagrid('reload');
      },
      //设置easyui datagrid id的值 ，设置查询参数
      setQueryParams :function(id,json){
    	  $('#'+id).datagrid({
    			queryParams: json
    		});
      },
      //设置datagrid的请求路径
      setUrl:function(id,url){
    	  $('#'+id).datagrid({
    		    url:url
    	  });
      }
};
