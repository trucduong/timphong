package web.business.utils;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

public class AppUtils {

    public static String getShortContent(String content, int maxLength) {
        return StringUtils.substring(html2text(content), 0, maxLength) + "...";
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
    
    public static int count(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {

            lastIndex = str.indexOf(findStr, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }

        return count;
    }
}
