package com.xuegao.springboot_tool.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestCtxtUtil {
    private RequestCtxtUtil() {}

    public static HttpServletRequest getHttpRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest();
        }
        return null;
    }

    public static HttpSession getHttpSession() {
        HttpServletRequest request = getHttpRequest();
        if (request != null) {
            return request.getSession();
        }
        return null;
    }

    public static HttpSession getHttpSession(boolean create) {
        HttpServletRequest request = getHttpRequest();
        if (request != null) {
        	//System.out.println(request.getSession(false));
            return request.getSession(create);
        }
        return null;
    }

    public static HttpSession getNewHttpSessionIfNotExist() {
        HttpServletRequest request = getHttpRequest();
        HttpSession session = null;
        if (request != null) {
            // 先尝试取原来的session 根据cookie的session来获取
            session = request.getSession(false);
            if (session == null) {
                // 若取不到 再新建session
                session = request.getSession(true);
            }
        }
        return session;
    }

    /**
     * 获取请求参数，尝试从路径参数、header、cookie中获取.
     *
     * @param name
     *            the name
     * @return the param
     */
    public static String getParam(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        HttpServletRequest request = getHttpRequest();
        if (request != null) {
            String parameter = request.getParameter(name);
            if (StringUtils.isNotBlank(parameter)) {
                return parameter;
            }

            String attribute = getParamFromAttr(name, request);
            if (StringUtils.isNotBlank(attribute)) {
                return attribute;
            }

            String header = getParamFromHeader(name, request);
            if (StringUtils.isNotBlank(header)) {
                return header;
            }

            String cookie = getParamFromCookie(name, request);
            if (StringUtils.isNotBlank(cookie)) {
                return cookie;
            }
        }
        return null;
    }

    private static String getParamFromCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private static String getParamFromHeader(String name, HttpServletRequest request) {
        String header = request.getHeader(name);
        if (StringUtils.isNotBlank(header)) {
            return header;
        }
        return null;
    }

    private static String getParamFromAttr(String name, HttpServletRequest request) {
        Object attribute = request.getAttribute(name);
        if (attribute != null) {
            return attribute.toString();
        }
        return null;
    }

    /**
     * 新的sessionId设置到请求参数中，方便zuul转发后续解析拿到新的session
     * 
     * @param sessionId
     */
    // public static void setNewSessionId(String sessionId, ServletRequest request) {
    //     RequestContext currentContext = RequestContext.getCurrentContext();
    //
    //     Map<String, List<String>> requestQueryParams = currentContext.getRequestQueryParams();
    //     if (log.isDebugEnabled()) {
    //         log.debug("requestQueryParams:{}", requestQueryParams);
    //     }
    //
    //     if (requestQueryParams == null) {
    //         requestQueryParams = new HashMap<>();
    //     }
    //
    //     if (request != null) {
    //         Map<String, String[]> parameterMap = request.getParameterMap();
    //         for (Entry<String, String[]> entry : parameterMap.entrySet()) {
    //             requestQueryParams.put(entry.getKey(), Arrays.asList(entry.getValue()));
    //         }
    //     }
    //
    //     requestQueryParams.put(SessionConsts.KEY_PARAM, Arrays.asList(sessionId));
    //     currentContext.setRequestQueryParams(requestQueryParams);
    // }

    /**
     * 设置traceId到请求参数中，方便zuul转发后续解析拿到
     * 
     * @param traceId
     */
    // public static void setTraceId(String traceId, ServletRequest request) {
    //     RequestContext currentContext = RequestContext.getCurrentContext();
    //
    //     Map<String, List<String>> requestQueryParams = currentContext.getRequestQueryParams();
    //     if (log.isDebugEnabled()) {
    //         log.debug("requestQueryParams:{}", requestQueryParams);
    //     }
    //
    //     if (requestQueryParams == null) {
    //         requestQueryParams = new HashMap<>();
    //     }
    //
    //     if (request != null) {
    //         Map<String, String[]> parameterMap = request.getParameterMap();
    //         for (Entry<String, String[]> entry : parameterMap.entrySet()) {
    //             requestQueryParams.put(entry.getKey(), Arrays.asList(entry.getValue()));
    //         }
    //     }
    //
    //     requestQueryParams.putIfAbsent(TraceIdUtil.HTTP_KEY_TRACE_ID, Arrays.asList(traceId));
    //
    //     currentContext.setRequestQueryParams(requestQueryParams);
    // }
}
