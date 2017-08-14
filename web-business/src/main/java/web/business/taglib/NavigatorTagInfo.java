package web.business.taglib;

import java.util.ArrayList;
import java.util.List;

public class NavigatorTagInfo {

    private UrlInfo root;
    private List<UrlInfo> items;
    
    public NavigatorTagInfo() {
        items = new ArrayList<UrlInfo>();
    }

    public void setRoot(String id, String name, String url) {
        root = new UrlInfo(id, name, url);
    }

    public UrlInfo addItem(String id, String name, String url) {
        UrlInfo item = new UrlInfo(id, name, url);
        if (items == null) {
            items = new ArrayList<UrlInfo>();
        }
        items.add(item);
        return item;
    }
    
    public UrlInfo getRoot() {
        return root;
    }

    public void setRoot(UrlInfo root) {
        this.root = root;
    }

    public List<UrlInfo> getItems() {
        return items;
    }

    public void setItems(List<UrlInfo> items) {
        this.items = items;
    }

    public class UrlInfo {
        private String id;
        private String name;
        private String url;
        private List<UrlInfo> childs;
        
        public UrlInfo() {
        }

        public UrlInfo(String id, String name, String url) {
            this.id = id;
            this.name = name;
            this.url = url;
        }

        public void addChild(String id, String name, String url) {
            UrlInfo child = new UrlInfo(id, name, url);
            if (childs == null) {
                childs = new ArrayList<UrlInfo>();
            }
            childs.add(child);
        }

        public boolean hasChild() {
            return childs != null && childs.size() > 0;
        }

        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<UrlInfo> getChilds() {
            return childs;
        }

        public void setChilds(List<UrlInfo> childs) {
            this.childs = childs;
        }
    }
}