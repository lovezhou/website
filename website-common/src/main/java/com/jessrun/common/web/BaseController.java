package com.jessrun.common.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.common.pagination.Pagination;
import com.jessrun.common.support.spring.monitor.JessrunMonitor;
import com.jessrun.platform.util.SerializeUtils;
import com.jessrun.platform.util.StringUtils;


public abstract class BaseController {
    
	private final String  FORMDATA = "_jsonData" ;
	private final String TITLE = "提示";
	
	protected Class<? extends ValueObject> clazz; //vo对于的class
	
    private  String   defaultView ="index" ;
    
    protected String  listView  ;
    
    public BaseController(){
    	init();
    	
    }
    
    /**
     * controller 初始化方法
     * 用来初始化BaseController中的成员属性
     */
    public abstract void init();
   
    /**
     * 
     * @return
     */
    public abstract JessrunMonitor getService();
    
    @RequestMapping(value = "/toListView.do", method = RequestMethod.GET)
    public ModelAndView toListView(HttpServletRequest req)  throws Exception {
        if(StringUtils.isNullOrEmpty(listView)){
            listView = defaultView ;
        }
        return new ModelAndView(listView);
    }
 
    
    @ResponseBody
    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    public String save(HttpServletRequest req,HttpServletResponse response)  {
        
        String json = req.getParameter(FORMDATA);
        try{
            ValueObject vo = SerializeUtils.jsonToObj(json, clazz);
            if(vo!=null){
                if(StringUtils.isNullOrEmpty(vo.getId())){
                    vo.setId(UUID.randomUUID().toString());
                    this.getService().saveObject(vo);
                }else{
                    this.getService().updateObject(vo);
                }
            }
            return SerializeUtils.toJson(Message.newInstance());
        }catch(Exception e){
            return SerializeUtils.toJson(Message.newInstance(TITLE,e.getMessage()));
        }
    }
    
    @SuppressWarnings("all")
    @ResponseBody
    @RequestMapping(value = "/query.json", method = RequestMethod.POST)
    public String query(HttpServletRequest req, @RequestParam(value = "rows", defaultValue = "10", required = false) int pageSize,
                        @RequestParam(value = "page", defaultValue = "1", required = false) int pageNumber,HttpServletResponse response)  {
        try{
            //ValueObject vo = SerializeUtils.jsonToObj(json, clazz);
            //Map<String,Object> model = JavaBeanUtil.convertBean(vo);
            Map map = filterNullValue(req.getParameterMap());
            Pagination pagination = new Pagination(pageSize, pageNumber);
            map.put("pagination", pagination);
            List list= this.getService().selectListByPage(map);
            Model model = new  Model(list,pagination.getCount()); 
            return SerializeUtils.toJson(model);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return SerializeUtils.toJson(Message.newInstance(TITLE,e.getMessage()));
        }
    }

	
    
    
    
	public Class<? extends ValueObject> getClazz() {
	
		return clazz;
	}

	
	public void setClazz(Class<? extends ValueObject> clazz) {
		this.clazz = clazz;
	}

	
	public String getListView() {
		return listView;
	}

	
	public void setListView(String listView) {
		this.listView = listView;
	}

  
	class Model{
	    public List<? extends ValueObject> rows ; //easyui 分页 模型包装
	    public int  total;
	    
	    public Model(List<? extends ValueObject> rows ,int total){
	        this.rows=rows;
	        this.total=total;
	    }
	}
   
	@SuppressWarnings("all")
    private Map filterNullValue(Map map){
        Map<String,String> param = new HashMap<String,String>();
        if(map!=null){
            if(!map.isEmpty()){
                Set<Entry> set =map.entrySet();
                Iterator<Entry> itr = set.iterator();
                while(itr.hasNext()){
                    Entry<String,String[]> entry  = itr.next();
                    String key = entry.getKey();
                    String[] val = entry.getValue();
                    if(val!=null&& !"".equals(val[0])){
                        param.put(key, val[0]);
                    }
                }
            }
        }
        return param;
    }
    
}
