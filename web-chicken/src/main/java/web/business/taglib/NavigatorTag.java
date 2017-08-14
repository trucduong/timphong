package web.business.taglib;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.google.gson.reflect.TypeToken;

import core.common.format.json.JsonFormatter;
import web.business.taglib.NavigatorTagInfo.UrlInfo;

public class NavigatorTag extends AbstractAppTag {
    private String activeItem;

    public String getActiveItem() {
        return activeItem;
    }
    public void setActiveItem(String activeItem) {
        this.activeItem = activeItem;
    }
    
    private NavigatorTagInfo load() throws IOException {
        final Type typeOfT = new TypeToken<NavigatorTagInfo>() {}.getType();
        String path = getClass().getResource("/navigation.json").getPath();
        NavigatorTagInfo info = JsonFormatter.fromJsonFile(path, typeOfT);
        if (info == null) {
            throw new IOException("Can not load file: " + path);
        }
        return info;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        activeItem = getBodyAsString();

        NavigatorTagInfo info = load();
        JspWriter out = getJspContext().getOut();
        out.println("<nav class='navbar navbar-inverse navbar-fixed-top' role='navigation'>");
        out.println("<div class='container'>");
        out.println("<div class='navbar-header'>");
        out.println("<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='#bs-example-navbar-collapse-1'>");
        out.println("<span class='sr-only'>Toggle navigation</span>");
        out.println("<span class='icon-bar'></span>");
        out.println("<span class='icon-bar'></span>");
        out.println("<span class='icon-bar'></span>");
        out.println("</button>");
        out.println("<a class='navbar-brand' href='"+ getRequest().getContextPath() + info.getRoot().getUrl()+"'>"+info.getRoot().getName()+"</a>");
        out.println("</div>");
        out.println("<div class='collapse navbar-collapse' id='bs-example-navbar-collapse-1'>");
        out.println("<ul class='nav navbar-nav navbar-right'>");
        for (UrlInfo item : info.getItems()) {
            out.println(buildItem(item));
        }
        out.println("</ul>");
        out.println("</div>");
        out.println("</div>");
        out.println("</nav>");
    }

    private String buildItem(UrlInfo item) {
        StringBuilder builder = new StringBuilder();
        String itemClass = item.getId().replaceAll(".", "-");
        if (item.hasChild()) {
            itemClass += " dropdown";
        }
        if (item.getId().equals(activeItem)) {
            itemClass += " active";
        }
        
        builder.append("<li class='"+itemClass+"'>");
        if (item.hasChild()) {
            builder.append("<a href='#' class='dropdown-toggle' data-toggle='dropdown'>").append(item.getName()).append(" <b class='caret'></b></a>");
            builder.append("<ul class='dropdown-menu'>");
            for (UrlInfo child : item.getChilds()) {
                builder.append("<li><a href='").append(getRequest().getContextPath()).append(child.getUrl()).append("'>").append(child.getName()).append("</a></li>");
            }
            builder.append("</ul>");
        } else {
            builder.append("<a href='").append(getRequest().getContextPath()).append(item.getUrl()).append("'>").append(item.getName()).append("</a>");
        }
        builder.append("</li>");
        return builder.toString();
    }
}
