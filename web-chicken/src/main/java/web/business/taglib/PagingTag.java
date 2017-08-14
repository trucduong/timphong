package web.business.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import core.service.invoke.ServiceBuilder;
import web.business.utils.AppConst;

public class PagingTag extends AbstractAppTag {

	public static final String TYPE_NAVIGATION = "nav";
	public static final String TYPE_BUTTON = "btn";
			
	private PagingTagInfo info;
	private String type;
	
	public PagingTag() {
		type = TYPE_NAVIGATION;
	}
	
	public PagingTagInfo getInfo() {
		return info;
	}
	
	public void setInfo(PagingTagInfo info) {
		this.info = info;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		if (info == null || info.isEmpty() || type == null) {
			return;
		}
		
		if (info.getTotalPage() < 2) {
		    return;
		}
		
		JspWriter out = getJspContext().getOut();
		getJspBody().invoke(out);
		
		if (TYPE_NAVIGATION.equals(type)) {
			out.println("<ul class='pager'>");
			if (info.hasPrevious()) {
				String url = ServiceBuilder.newService(info.getRequestUrl())
				        .addParams(info.getParams())
						.addParam(AppConst.PAGING_PAGE_PARAM, info.getPreviousPage())
						.buildUrlWithParams();
				out.println("<li class='previous'><a href='"+ url +"'>&larr; Older</a></li>");
			} else {
				out.println("<li class='previous disabled'><a href='#'>&larr; Older</a></li>");
			}
			
			if (info.hasNext()) {
				String url = ServiceBuilder.newService(info.getRequestUrl())
				        .addParams(info.getParams())
						.addParam(AppConst.PAGING_PAGE_PARAM, info.getNextPage())
						.buildUrlWithParams();
				out.println("<li class='next'><a href='"+url+"'>Newer &rarr;</a></li>");
			} else {
				out.println("<li class='next disabled'><a href='#'>Newer &rarr;</a></li>");
			}
			out.println("</ul>");
			
		} else {
			// another paging cmp type
		}
	}
	
	
}
