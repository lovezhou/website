/**
 * @(#)JsonView.java Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.support.spring.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.servlet.view.AbstractView;

import com.jessrun.common.support.spring.view.support.ResponseUtils;

/**
 * 自定义 JSON View
 * 
 * @author luoyifan
 * @version 1.0,2011-4-27
 */
public class JsonView extends AbstractView {

    public static final String DEFAULT_JSON_KEY = "default_json";

    protected String           jsonKey;
    protected Object           object;

    public JsonView(String jsonKey, Model model){
        this(jsonKey, model.asMap());
    }

    /**
     * @author luoyifan
     * @param jsonKey 对应json配置文件中item节点的name属性
     * @param object 需要转换的json的对象
     */
    public JsonView(String jsonKey, Object object){
        this.jsonKey = jsonKey;
        this.object = object;
    }

    /**
     * @author luoyifan
     * @param object 需要转换的json的对象
     */
    public JsonView(Object object){
        this(DEFAULT_JSON_KEY, object);
    }

    /**
     * @author luoyifan
     * @param success 是否成功
     * @param msg 消息 可为null
     */
    public JsonView(boolean success, String msg){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("success", success);
        model.put("msg", msg);
        this.jsonKey = DEFAULT_JSON_KEY;
        this.object = model;
    }

    /**
     * @author luoyifan
     * @param model spring model对象
     */
    public JsonView(Model model){
        this(model.asMap());
    }

    @Override
    protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        ResponseUtils.writeJSON(request, response, object, jsonKey);
    }

}
