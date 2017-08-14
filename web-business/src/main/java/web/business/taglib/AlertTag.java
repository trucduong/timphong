package web.business.taglib;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import core.web.navigation.WebResult;

public class AlertTag extends AbstractAppTag {

	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();  
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest(); 
		AlertTagInfo info = (AlertTagInfo) request.getAttribute(WebResult.ALERT_PARAM_NAME);
		if (info == null) {
			return;
		}
		
		JspWriter out = getJspContext().getOut();
		String alertId = info.getType() + UUID.randomUUID().toString();
		if (info.isCloseable()) {
			out.println("<div id='"+alertId+"' class='alert alert-"+info.getType()+" alert-dismissable fade in'>");
			out.println("<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>");
		} else {
			out.println("<div id='"+alertId+"' class='alert alert-"+info.getType()+" fade in'>");
		}
		out.println("<strong>"+info.getTitle()+"</strong> ");
		out.println(info.getMessage());
		if (getJspBody() != null) {
			getJspBody().invoke(out);
		}
		out.println("</div>");
		
		if (info.isAutoClose()) {
			out.println("<script>");
			out.println("$(function() {");
			
			out.println("setTimeout(function(){"); 
			out.println("$('#"+alertId+"').hide();");
			out.println("}, "+info.getAutoCloseTime()+");");
			
			out.println("});");
			out.println("</script>");
		}
	}
}
