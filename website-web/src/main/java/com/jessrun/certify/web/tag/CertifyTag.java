package com.jessrun.certify.web.tag;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.jessrun.certify.constant.ParamKeys;
import com.jessrun.certify.vo.ResourceItem;

@SuppressWarnings("serial")
public class CertifyTag extends TagSupport {
	
	private Integer resourceId;

	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		if(resourceId == null){
			return SKIP_BODY;
		}

		ServletRequest request = pageContext.getRequest();
		
		Object obj = request.getAttribute(ParamKeys.RESOURCE_IDS_KEY);
		
		if(obj == null){
			return SKIP_BODY;
		}
		
		for(ResourceItem item : (List<ResourceItem>)obj){
			if(item.getId().intValue() == resourceId.intValue()){
				return EVAL_BODY_INCLUDE;
			}
		}

		return SKIP_BODY;

	}

	@Override
	public int doEndTag() throws JspException {

		return EVAL_PAGE;

	}

	@Override
	public void release() {

		super.release();

	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	
	
}
