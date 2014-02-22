package com.appengine.myblog.util;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>Title: ServletUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: NO</p>
 *
 * @author ThinkPadT420i
 * @version 1.0
 * @date 2013年12月20日
 */
public class ServletUtil {

    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        //处理BAE的IP，获取到当前访问的真实IP
        if (ip != null) {
            String[] baeIP = ip.split(",");
            ip = baeIP[0];
        }
        return ip;
    }

    public static String[] organizeDate(Date date) {
        if(date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = calendar.get(Calendar.MONTH) > 9 ?  String.valueOf(calendar.get(Calendar.MONTH) +1) : "0" + (calendar.get(Calendar.MONTH) +1);
        String day = calendar.get(Calendar.DAY_OF_MONTH) > 9 ? String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) : "0" + calendar.get(Calendar.DAY_OF_MONTH);
        return new String[] {year, month, day};
    }


    public static String formatDate(String date) {
        DateFormat df = new SimpleDateFormat("YYYY年MM月");
        String strdate = df.format(parseString(date));
        return strdate;
    }

    public static Date parseString(String strdate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String parseURL(String url) {
        if(url.length() == 4 ) return url;
        StringBuilder sb =  new StringBuilder();
        switch (url.length()) {
            case 6:
                sb.append(url.substring(0,4));
                sb.append("-");
                sb.append(url.substring(4,6));
                break;
            case 8:
                sb.append(url.substring(0,4));
                sb.append("-");
                sb.append(url.substring(4,6));
                sb.append("-");
                sb.append(url.substring(6,8));
                break;
        }
        return sb.toString();
    }
}
