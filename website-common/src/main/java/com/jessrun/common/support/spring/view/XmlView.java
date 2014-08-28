/**
 * @(#)XmlView.java Copyright 2011 jointown, Inc. All rights reserved.
 */
package com.jessrun.common.support.spring.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.springframework.ui.Model;

import com.jessrun.common.support.spring.view.support.JSONHandler;
//import com.opensymphony.xwork2.util.TextUtils;

/**
 * description
 * 
 * @author luoyifan
 * @version 1.0,2008-8-17
 */
public class XmlView extends JsonView {

    private String              xmlRootName;

    private static final String XML_DEFAULT_ROOT_NAME = "response";
    private boolean             escape                = false;

    public XmlView(boolean success, String msg){
        super(success, msg);

    }

    /**
     * 返回的数据格式根节点为<br/>
     * &ltresponse&gt..&ltresponse&gt
     * 
     * @author luoyifan
     * @return
     */
    public static XmlView newXmlViewInResponse(Object object) {

        XmlView xmlView = new XmlView(object);
        xmlView.xmlRootName = XML_DEFAULT_ROOT_NAME;
        return xmlView;
    }

    /**
     * 返回的数据格式根节点为<br/>
     * &ltresponse&gt..&ltresponse&gt
     * 
     * @author luoyifan
     * @return
     */
    public static XmlView newXmlViewInResponse(String jsonKey, Object object) {
        XmlView xmlView = new XmlView(jsonKey, object);
        xmlView.xmlRootName = XML_DEFAULT_ROOT_NAME;
        return xmlView;
    }

    public XmlView(Model model){
        super(model);

    }

    public XmlView(Object object){
        super(object);

    }

    public XmlView(String jsonKey, Model model){
        super(jsonKey, model);

    }

    public XmlView(String jsonKey, Object object){
        super(jsonKey, object);

    }

//    @Override
//    protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model, HttpServletRequest request,
//                                           HttpServletResponse response) throws Exception {
//        String jsonString = JSONHandler.serialize(object, jsonKey);
//        jsonString = jsonString == null ? "{}" : jsonString;
//        String xml = createXmlString(jsonString);
//        response.setContentType("text/xml;charset=GBK");
//        response.getWriter().write(xml);
//    }

//    public String createXmlString(String jsonString) {
//        JSON jsonObj = createJSONObj(jsonString);
//        XMLSerializer xmlSerializer = createXMLSerializer();
//        return escape ? TextUtils.htmlEncode(xmlSerializer.write(jsonObj)) : xmlSerializer.write(jsonObj);
//    }

    private JSON createJSONObj(String jsonString) {
        if (jsonString.trim().startsWith("[")) return JSONArray.fromObject(jsonString);
        return JSONObject.fromObject(jsonString);
    }

    private XMLSerializer createXMLSerializer() {
        XMLSerializer xml = new XMLSerializer();
        xml.setObjectName(xmlRootName == null ? jsonKey : xmlRootName);
        xml.setArrayName(xmlRootName == null ? jsonKey : xmlRootName);
        xml.setElementName("item");
        xml.setTypeHintsEnabled(false);
        xml.setTypeHintsCompatibility(true);
        xml.setNamespaceLenient(true);
        xml.setSkipNamespaces(true);
        xml.setRemoveNamespacePrefixFromElements(true);
        xml.setTrimSpaces(false);
        return xml;
    }

}
