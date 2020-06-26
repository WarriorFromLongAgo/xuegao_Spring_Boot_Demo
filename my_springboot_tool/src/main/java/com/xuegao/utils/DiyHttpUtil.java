package com.xuegao.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * http请求调用类<br>
 *
 * @author luo_zm
 * @create 2019/12/20 17:50
 */
@Component
public class DiyHttpUtil {

    @Autowired
    private HttpServletRequest httpServletRequest;

    private static String AUTHORIZATION = "Authorization";

    private static String UMS_DATA = "data";
    private static String UMS_CODE = "code";
    private static String UMS_MESSAGE = "message";
    private static String UMS_MSG = "msg";

    private static String UMS_PAGE_CURRENT = "current";
    private static String UMS_PAGE_TOTAL = "total";
    private static String UMS_PAGE_SIZE = "size";
    private static String UMS_PAGE_RECORDS = "records";

    private static String SUCCESS = "success";

//     /**
//      * http无参数get请求,url为接口的地址
//      *
//      * @param url
//      * @return
//      */
//     public WrappedResponse getWithoutParams(String url) {
//         HttpRequest get = HttpUtil.createGet(pmsRequestConfig.getRequestAddr() + url);
//         get.header(AUTHORIZATION, httpServletRequest.getHeader(AUTHORIZATION));
//         WrappedResult wrappedResult = this.getPmsResult(get.execute());
//         return wrappedResult;
//     }
//
//     /**
//      * http有参数get请求
//      *
//      * @param url 为接口的地址
//      * @param map 请求需要的参数
//      * @return
//      */
//     public WrappedResult getWithParams(String url, Map<String, Object> map) {
//         HttpRequest get = HttpUtil.createGet(pmsRequestConfig.getRequestAddr() + url);
//         get.header(AUTHORIZATION, httpServletRequest.getHeader(AUTHORIZATION));
//         map.entrySet().stream().forEach(entry -> {
//             get.form(entry.getKey(), entry.getValue());
//         });
//         WrappedResult wrappedResult = this.getPmsResult(get.execute());
//         return wrappedResult;
//     }
//
//     /**
//      * post,delete,put等无参请求的发送
//      *
//      * @param method 请求方式
//      * @param url    请求地址
//      * @return
//      */
//     public WrappedResult requestWithoutParams(Method method, String url) {
//         HttpRequest request = HttpUtil.createRequest(method, pmsRequestConfig.getRequestAddr() + url);
//         request.header(AUTHORIZATION, httpServletRequest.getHeader(AUTHORIZATION));
//         // request.header(AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwcm92aWRlciI6ImplcnJ5Y2hpciIsInVzZXJfaWQiOiIxMDIwMzUyOTY1NDEwODE2IiwidXNlcl9uYW1lIjoic2dzIiwic2NvcGUiOlsiYWxsIl0sIm5hbWUiOiLnnIHlhazlj7giLCJleHAiOjE1Nzc2NzUxNjIsImF1dGhvcml0aWVzIjpbInVzZXIiXSwianRpIjoiMTRhYjc0NTItMjdkOS00MTllLWJmMWUtZTg5MjVlMzVjMGNjIiwiY2xpZW50X2lkIjoiZWljIn0.biwtzwf-DUD8g5Zirn4kCANFqTxLXuLoao00UPj6ar4");
//         WrappedResult wrappedResult = this.getPmsResult(request.execute());
//         return wrappedResult;
//     }
//
//     /**
//      * post,delete,put等有参请求的发送
//      *
//      * @param method 请求方式
//      * @param url    请求地址
//      * @param map    请求参数
//      * @return
//      */
//     public WrappedResult requestWithParams(Method method, String url, Map<String, Object> map) {
//         HttpRequest request = HttpUtil.createRequest(method, pmsRequestConfig.getRequestAddr() + url);
//         request.header(AUTHORIZATION, httpServletRequest.getHeader(AUTHORIZATION));
//         map.entrySet().stream().forEach(entry -> {
//             request.form(entry.getKey(), entry.getValue());
//         });
//         HttpResponse execute = request.execute();
//         WrappedResult wrappedResult = getPmsResult(execute);
//         return this.toTeamPageData(wrappedResult);
//
//     }
//
//     /**
//      * 批量删除
//      *
//      * @param url
//      * @param list id数组
//      * @return
//      */
//     public WrappedResult deletWithBatch(String url, List<Long> list) {
//         StringBuilder sburl = new StringBuilder(url + "?");
//         for (int i = 0; i <= list.size() - 1; i++) {
//             if (i == 0) {
//                 sburl.append("ids=" + list.get(i));
//                 continue;
//             }
//             sburl.append("&ids=" + list.get(i));
//         }
//         HttpRequest request = HttpUtil.createRequest(Method.DELETE, pmsRequestConfig.getRequestAddr() + sburl);
//         request.header(AUTHORIZATION, httpServletRequest.getHeader(AUTHORIZATION));
//         HttpResponse execute = request.execute();
//         WrappedResult wrappedResult = getPmsResult(execute);
//         return this.toTeamPageData(wrappedResult);
//     }
}

