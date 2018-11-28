package com.work.zuulservice;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.work.general.dicts.Dict;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.*;

@Component
public class MyZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


        public Object run() {

            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();

            try {
//                InputStream in = ctx.getRequest().getInputStream();
//                String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
//                System.out.println("编码前body:" + body);
//                String bodyDecode = URLDecoder.decode(body, "UTF-8");
//                System.out.println("编码后body:" + bodyDecode);
//                if (StringUtils.isBlank(bodyDecode)) {
//                    bodyDecode = "{}";
//                }
//                JSONObject jsonObject = JSONObject.fromObject(bodyDecode.substring(0,bodyDecode.length()-1));
//                String newBody = jsonObject.toString();

                JSONObject json = new JSONObject();
                Enumeration enu = request.getParameterNames();
                while (enu.hasMoreElements()) {
                    String paraName = (String) enu.nextElement();
                    json.put(paraName, request.getParameter(paraName));
                }

                final byte[] reqBodyBytes = json.toString().getBytes("UTF-8");
                ctx.setRequest(new HttpServletRequestWrapper(request) {
                    @Override
                    public ServletInputStream getInputStream() throws IOException {
                        return new ServletInputStreamWrapper(reqBodyBytes);
                    }

                    @Override
                    public int getContentLength() {
                        return reqBodyBytes.length;
                    }

                    @Override
                    public long getContentLengthLong() {
                        return reqBodyBytes.length;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        String url = String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString());
//        System.out.println(url);
//        Object accessToken = request.getParameter("token");
//        if(accessToken != null) {
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            try {
//                ctx.getResponse().getWriter().write("token is empty");
//            }catch (Exception e){
//
//            }
//            return null;
//        }
//        return null;
//    }


//    public Object run() {
//
//        // 获取到request
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//
//        try {
//            InputStream in = ctx.getRequest().getInputStream();
//            String body11 = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
//            System.out.println( "body11:"+body11);
//            String body = URLDecoder.decode(body11, "UTF-8");
//            System.out.println( "body:"+body);
//            if(StringUtils.isBlank(body)){
//                body = "{}";
//            }
//            JSONObject jsonObject = JSONObject.fromObject(body.replace("=",""));
//            jsonObject.put("userId", 666);
//            String newBody = jsonObject.toString();
//
////            JSONObject json = new JSONObject();
////            json.put("id", "123");
//            System.out.println( newBody);
//            final byte[] reqBodyBytes = newBody.getBytes();
//            ctx.setRequest(new HttpServletRequestWrapper(request){
//                @Override
//                public ServletInputStream getInputStream() throws IOException {
//                    return new ServletInputStreamWrapper(reqBodyBytes);
//                }
//                @Override
//                public int getContentLength() {
//                    return reqBodyBytes.length;
//                }
//                @Override
//                public long getContentLengthLong() {
//                    return reqBodyBytes.length;
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
////        // 获取请求参数name
////        String name = "";
////        try {
////
////            JSONObject body = new JSONObject();
////            body.put("userId", 666);
//////            RequestContext context = RequestContext.getCurrentContext();
//////
//////            InputStream in = (InputStream) context.get("requestEntity");
//////            if (in == null) {
//////                in = context.getRequest().getInputStream();
//////            }
//////            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
//////            System.out.println("body:" + body);
//////            body = StringUtils.replace(body, PARAM_TOKEN + "=" + token, PARAM_TOKEN + "=" + authResult.getToken());
//////            System.out.println("转换后的body：" + body);
//////            // context.set("requestEntity", new
//////            // ByteArrayInputStream(body.getBytes("UTF-8")));
////            String method = request.getMethod();
////           System.out.println(String.format("%s >>> %s", method, request.getRequestURL().toString()));
////
////            System.out.println(body.toString());
////            final byte[] reqBodyBytes = body.toString().getBytes();
////            ctx.setRequest(new HttpServletRequestWrapper(request) {
////                @Override
////                public ServletInputStream getInputStream() throws IOException {
////                    return new ServletInputStreamWrapper(reqBodyBytes);
////                }
////
////                @Override
////                public int getContentLength() {
////                    return reqBodyBytes.length;
////                }
////
////                @Override
////                public long getContentLengthLong() {
////                    return reqBodyBytes.length;
////                }
////            });
////
//////            ctx.setSendZuulResponse(false);
////
//////            InputStream in = ctx.getRequest().getInputStream();
//////            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
//////            if(StringUtils.isBlank(body)){
//////                body = "{}";
//////            }
//////            JSONObject jsonObject = new JSONObject();
////////            JSONObject jsonObject = JSON.parseObject(body);
//////            jsonObject.put("userId", 666);
//////            String newBody = jsonObject.toString();
//////            final byte[] reqBodyBytes = newBody.getBytes();
//////            ctx.setRequest(new HttpServletRequestWrapper(request){
//////                @Override
//////                public ServletInputStream getInputStream() throws IOException {
//////                    return new ServletInputStreamWrapper(reqBodyBytes);
//////                }
//////                @Override
//////                public int getContentLength() {
//////                    return reqBodyBytes.length;
//////                }
//////                @Override
//////                public long getContentLengthLong() {
//////                    return reqBodyBytes.length;
//////                }
//////            });
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        return null;
//    }
//    public Object run() {
//
//        // 获取到request
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        // 获取请求参数name
//        String name = "";
//        try {
//
//            // 请求方法
//            String method = request.getMethod();
//            System.out.println(String.format("%s >>> %s", method, request.getRequestURL().toString()));
//
//
//            InputStream in = ctx.getRequest().getInputStream();
//            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
//            System.out.println("body:" + body);
//            JSONObject json = JSONObject.fromObject(body);
//
////            Map<String, String> map = new HashMap<>();
////            Enumeration enu = request.getParameterNames();
////            while (enu.hasMoreElements()) {
////                String paraName = (String) enu.nextElement();
////                map.put(paraName, request.getParameter(paraName));
////            }
//
//            // 获取请求的输入流
////            InputStream in = request.getInputStream();
////            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
////            // 如果body为空初始化为空json
////            if (StringUtils.isBlank(body)) {
////                body = "{}";
////            }
////            System.out.println("body" + body);
////            // 转化成json
////            JSONObject json = JSONObject.fromObject(body);
//
//            // get方法和post、put方法处理方式不同
//            if ("GET".equals(method)) {
//
//                // 获取请求参数name
//                name = request.getParameter("name");
//
//                if (name != null) {
//                    // 关键步骤，一定要get一下,下面才能取到值requestQueryParams
//                    request.getParameterMap();
//                    Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
//                    if (requestQueryParams == null) {
//                        requestQueryParams = new HashMap<>();
//                    }
//                    List<String> arrayList = new ArrayList<>();
//                    arrayList.add("ssdfsdf");
//                    requestQueryParams.put(Dict.general, arrayList);
//                    ctx.setRequestQueryParams(requestQueryParams);
//                }
//            }// post和put需重写HttpServletRequestWrapper
//            else if ("POST".equals(method) || "PUT".equals(method)) {
//
////                Map map1 = new HashMap();
////                map1.put("abcd","sdfsdf");
////                JSONObject json = new JSONObject();
//                json.put("abcd","sdfsdf");
//
//                final byte[] reqBodyBytes = json.toString().getBytes();
//
//                // 重写上下文的HttpServletRequestWrapper
//                ctx.setRequest(new HttpServletRequestWrapper(request) {
//                    @Override
//                    public ServletInputStream getInputStream() throws IOException {
//                        return new ServletInputStreamWrapper(reqBodyBytes);
//                    }
//
//                    @Override
//                    public int getContentLength() {
//                        return reqBodyBytes.length;
//                    }
//
//                    @Override
//                    public long getContentLengthLong() {
//                        return reqBodyBytes.length;
//                    }
//                });
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        request.getParameterMap();
//        Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
//
//        if (requestQueryParams==null) {
//            requestQueryParams=new HashMap<>();
//        }
//
//        ctx.setRequestQueryParams(requestQueryParams);
//
//        String url = String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString());
//        System.out.println(url);
////        Object accessToken = request.getParameter("token");
////        if(accessToken != null) {
////            ctx.setSendZuulResponse(false);
////            ctx.setResponseStatusCode(401);
////            try {
////                ctx.getResponse().getWriter().write("token is empty");
////            }catch (Exception e){
////
////            }
////            return null;
////        }
//        return null;
//    }
}