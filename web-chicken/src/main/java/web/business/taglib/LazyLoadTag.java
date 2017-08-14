package web.business.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang3.StringUtils;

import core.web.widget.HtmlTagBuilder;
import core.web.widget.HtmlTagBuilder.TagClosingType;
import core.web.widget.JSTagBuilder;

public class LazyLoadTag extends AbstractAppTag {

    private String name;
    private String template;
    private String data;
    private String cssclass;
    private boolean autoLoad = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCssclass() {
        return cssclass;
    }

    public void setCssclass(String cssclass) {
        this.cssclass = cssclass;
    }

    public boolean isAutoLoad() {
        return autoLoad;
    }

    public void setAutoLoad(boolean autoLoad) {
        this.autoLoad = autoLoad;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (StringUtils.isBlank(template) || StringUtils.isBlank(name)) {
            return;
        }

        if (StringUtils.isBlank(data)) {
            data = "{}";
        }

        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        JspWriter out = getJspContext().getOut();

        HtmlTagBuilder htmlBuilder = HtmlTagBuilder.newTag("div")
                .set("id", name)
                .set("data-url", request.getContextPath() + "/template/" + template)
                .set("class", cssclass);
        
        String loadingImg = name+"loading";
        htmlBuilder.add(loadingImg(loadingImg));
        
        JSTagBuilder jsBuilder = JSTagBuilder.newTag();
        if (autoLoad) {
            jsBuilder.append("load('" + name + "', " + data + ", {showloader:false});");
        } else {
            // action button id='...action'
            htmlBuilder.add(getBodyAsString());
            jsBuilder.append("$('#"+loadingImg+"').hide();");
            
            String actionBtn = name + "action";
            jsBuilder.append("$('#"+actionBtn+"').on('click', function() {");
            jsBuilder.append("$('#"+actionBtn+"').hide();");
            jsBuilder.append("$('#"+loadingImg+"').show();");
            jsBuilder.append("load('" + name + "', " + data + ", {showloader:false});");
            jsBuilder.append("});");
        }

        out.println(htmlBuilder.build(TagClosingType.FULL));
        out.println(jsBuilder.build());
    }
}
