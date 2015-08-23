package com.jessrun.codegenerator.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jessrun.codegenerator.domain.ColumnAndType;
import com.jessrun.codegenerator.service.CodeGeneratorService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * 代码生成器
 * @author zmy 2014-8-6 下午10:31:38
 */
@Controller
@RequestMapping(value="/codegenerator")
public class CodeGeneratorController {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @RequestMapping(value="/codegenerator.do", method = RequestMethod.GET)
    public ModelAndView menuXtgn(HttpServletRequest arg0) throws Exception {

        ModelAndView mav = new ModelAndView("/codegenerator/codegenerator");

        return mav;
    }
    
    @RequestMapping(value="/toListView.do", method = RequestMethod.POST)
    public ModelAndView toListView(String tableName ,HttpServletRequest req) throws Exception {
        List<ColumnAndType> list = codeGeneratorService.getListColumnAndType(tableName.toUpperCase());
        ModelAndView mav = new ModelAndView("/codegenerator/codegenerator");
        mav.addObject("voList", list);
        mav.addObject("tableName",tableName);
        mav.addObject("className",codeGeneratorService.convertTableNameToClassName(tableName));
        return mav;
    }
    
    
    // @ResponseBody
    @RequestMapping(value="/codeGenerator.do", method = RequestMethod.POST)
    public ModelAndView codeGenerator(HttpServletRequest req) throws Exception {
        @SuppressWarnings("all")
        Map<String,String[]> map =( Map<String,String[]>)  req.getParameterMap();
      
        Map<String,Object> data = codeGeneratorService.codeGenerator(map);
        ModelAndView mav = new ModelAndView("showMessage.ftl");
        
        // 1. 创建freemarker配置实例
        Configuration conf = new Configuration();
        conf.setDirectoryForTemplateLoading(new File("D:\\github_source\\website\\website-web\\src\\main\\webapp\\WEB-INF\\ftl"));
        //conf.setClassForTemplateLoading(clazz, pathPrefix);
        
        // 3.加载模板文件
        Template domain = conf.getTemplate("domain.ftl");
        Template controller = conf.getTemplate("controller.ftl");
        Template service = conf.getTemplate("service.ftl");
        Template serviceImpl = conf.getTemplate("serviceImpl.ftl");
        Template mapper = conf.getTemplate("mapper.ftl");
        Template mapperXml = conf.getTemplate("mapperXml.ftl");
        Template jsp = conf.getTemplate("jsp.ftl");
        Template js = conf.getTemplate("js.ftl");

        String className = (String)data.get("className");
        String javaSrcPath = (String)data.get("javaSrcPath");
        String jspSrcPath = (String)data.get("jspPath");
        String jsSrcPath = (String)data.get("jsPath");
        String packageName = (String)data.get("packageName");
        String[]  packs = packageName.split("\\.");
        for (int i = 0; i < packs.length; i++) {
            javaSrcPath =javaSrcPath+"\\"+packs[i];
        }
        File  basePath = new File(javaSrcPath);
        if(basePath.exists()){
            basePath.mkdirs();
        }
        //domain
        File domainPath= new File(javaSrcPath+"\\"+"domain");
        if(!domainPath.exists()){
            domainPath.mkdirs();
        }
        FileOutputStream outStream =  new FileOutputStream(domainPath+"\\"+className+"VO.java");
        Writer out = new OutputStreamWriter(outStream);
        domain.process(data, out);// 4.输出数据文件
        //controller
        if("1".equals(data.get("isController"))){
            File controllerPath= new File(javaSrcPath+"\\"+"controller");
            if(!controllerPath.exists()){
                controllerPath.mkdirs();
            }
            outStream =  new FileOutputStream(controllerPath+"\\"+className+"Controller.java");
            out = new OutputStreamWriter(outStream);
            controller.process(data, out);// 4.输出数据文件
        }
        if("1".equals(data.get("isService"))){
            //service
            File servicePath= new File(javaSrcPath+"\\"+"service");
            if(!servicePath.exists()){
                servicePath.mkdirs();
            }
            outStream =  new FileOutputStream(servicePath+"\\"+className+"Service.java");
            out = new OutputStreamWriter(outStream);
            service.process(data, out);
            //service impl
            File serviceImplPath= new File(javaSrcPath+"\\"+"service\\impl");
            if(!serviceImplPath.exists()){
                serviceImplPath.mkdir();
            }
            outStream =  new FileOutputStream(serviceImplPath+"\\"+className+"ServiceImpl.java");
            out = new OutputStreamWriter(outStream);
            serviceImpl.process(data, out);
        }
        //dao
        if("1".equals(data.get("isDao"))){
            File daoPath= new File(javaSrcPath+"\\"+"dao");
            if(!daoPath.exists()){
                daoPath.mkdirs();
            }
            outStream =  new FileOutputStream(daoPath+"\\"+className+"Mapper.java");
            out = new OutputStreamWriter(outStream);
            mapper.process(data, out);
            //conf
            File confPath= new File(javaSrcPath+"\\"+"dao\\conf");
            if(!confPath.exists()){
                confPath.mkdirs();
            }
            outStream =  new FileOutputStream(confPath+"\\"+className+"Mapper.xml");
            out = new OutputStreamWriter(outStream);
            mapperXml.process(data, out);
        }
        //jsp,js
        if("1".equals(data.get("isJsp"))){
            File jspPath= new File(jspSrcPath);
            if(!jspPath.exists()){
                jspPath.mkdirs();
            }
            className= className.substring(0, 1).toLowerCase()+className.substring(1);
            outStream =  new FileOutputStream(jspPath+"\\"+className+"_list.jsp");
            out = new OutputStreamWriter(outStream);
            jsp.process(data, out);
            
            
            File jsPath= new File(jsSrcPath);
            if(!jsPath.exists()){
                jsPath.mkdirs();
            }
            outStream =  new FileOutputStream(jsPath+"\\"+className+"_list.js");
            out = new OutputStreamWriter(outStream);
            js.process(data, out);
        }
        outStream.flush();
        outStream.close();
        out.flush();
        out.close();
        
        //视图名称
        mav.addAllObjects(data);
        return mav;
    }


}
