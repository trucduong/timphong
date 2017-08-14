package web.business.taglib;

import java.util.HashMap;
import java.util.Map;

public class PagingTagInfo {
    private String requestUrl;
    private int maxRecord;
    private int currentPage;
    private int totalPage;
    private int totalRecords;
    private Map<String, Object> params;

    public PagingTagInfo(String requestUrl, int totalRecords, int maxRecord) {
        this.params = new HashMap<String, Object>();
        this.totalRecords = totalRecords;
        this.requestUrl = requestUrl;
        this.maxRecord = maxRecord;
        this.currentPage = 1;
        this.totalPage = (int) Math.ceil(((double) totalRecords) / maxRecord);
    }

    public PagingTagInfo addParam(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * zero base
     * 
     * @return
     */
    public int getStartIndex() {
        int start = (currentPage - 1) * maxRecord;
        if (start > 0) {
            return start;
        }
        return 0;
    }

    public int getEndIndex() {
        int end = currentPage*maxRecord;
        if (end <= totalRecords) {
            return end;
        }
        return totalRecords;
    }

    // public boolean next() {
    // if (currentPage < totalPage) {
    // currentPage ++;
    // return true;
    // }
    // return false;
    // }
    //
    // public boolean previous() {
    // if (currentPage > 1) {
    // currentPage--;
    // return true;
    // }
    // return false;
    // }

    public boolean gotoFrist() {
        currentPage = 1;
        return true;
    }

    public boolean gotoLast() {
        currentPage = totalPage;
        return true;
    }

    public boolean gotoPage(int page) {
        if (page < 1) {
            currentPage = 1;
            return false;
        }

        if (page > totalPage) {
            currentPage = totalPage;
            return false;
        }

        currentPage = page;
        return true;
    }

    // public boolean execute(String action, Object... params) {
    // if (action == null) {
    // return false;
    // }
    //
    // if (AppConst.PAGING_NEXT.equals(action)) {
    // next();
    // } else if (AppConst.PAGING_PREVIOUS.equals(action)) {
    // previous();
    // }
    //
    // return true;
    // }

    public boolean isEmpty() {
        return totalPage <= 0;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public boolean hasNext() {
        return currentPage < totalPage;
    }

    public int getNextPage() {
        return currentPage + 1;
    }

    public boolean hasPrevious() {
        return currentPage > 1;
    }

    public int getPreviousPage() {
        return currentPage - 1;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
