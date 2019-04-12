package com.work.general.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

    public static Map<String,String> getMapFromRequest(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            map.put(paraName, request.getParameter(paraName));
        }
        return map;

    }

}
