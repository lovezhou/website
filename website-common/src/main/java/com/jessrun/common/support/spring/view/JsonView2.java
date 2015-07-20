package com.jessrun.common.support.spring.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;

import com.jessrun.common.support.spring.view.support.ResponseUtils;
import com.jessrun.platform.util.SerializeUtils;

public class JsonView2 extends JsonView {

    private static final Log LOG = LogFactory.getLog(JsonView2.class);

    protected Object         object;

    public JsonView2(String jsonKey, Model model){
        super(jsonKey, model);
    }

    public JsonView2(Object object){
        super(DEFAULT_JSON_KEY, object);
    }

    public JsonView2(boolean success, String msg){
        super(success, msg);
    }

    @Override
    protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String jsonString = SerializeUtils.toJson(super.object);
        jsonString = jsonString == null ? "{}" : jsonString;

        boolean useGzip = ResponseUtils.isSupportGzip(request);
        try {
            ResponseUtils.writeJSONToResponse(response, ResponseUtils.DEFAULT_ENCODING, jsonString, useGzip, false);
        } catch (IOException e) {
            try {
                LOG.error(e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException e1) {
                throw new RuntimeException("system error.", e1);
            }
        }
    }

}
