package web.business.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang3.StringUtils;

public class EditorTag extends AbstractAppTag {

    private String value;
    private String name;

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    @Override
    public void doTag() throws JspException, IOException {
        if (StringUtils.isBlank(name)) {
            return;
        }
        
        if (value == null) {
            value = "";
        }

        JspWriter out = getJspContext().getOut();
        out.println("<input id='" + name + "' type='hidden' name='" + name + "' value='" + value + "'>");
        out.println("<script type='text/javascript'>");
        out.println("$(function() {");
        out.println("APP_JS.buildEditor('"+name+"');");
        out.println("});");
        out.println("</script>");
    }
}
