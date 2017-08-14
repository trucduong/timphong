package web.business.taglib;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class AbstractAppTag extends SimpleTagSupport {
    protected String loadingImg() {
        return loadingImg(null);
    }

    protected String loadingImg(String id) {
        PageContext pageContext = (PageContext) getJspContext();  
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        if (id == null) {
            return "<img class='img-responsive' style='margin: 0 auto;' alt='Loading...' src='"+request.getContextPath()+"/resources/web/images/loading.gif'>";
        }
        
        return "<img id='"+id+"' class='img-responsive' style='margin: 0 auto;' alt='Loading...' src='"+request.getContextPath()+"/resources/web/images/loading.gif'>";
    }

    protected String getBodyAsString() throws JspException, IOException {
        StringWriter strWriter = new StringWriter();
        getJspBody().invoke(strWriter);
        return strWriter.toString();
    }
    
    protected HttpServletRequest getRequest() {
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        return request;
    }
}
