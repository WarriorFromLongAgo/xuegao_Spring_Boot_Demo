package com.xuegao.springboot_tool.config;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

/**
 * restTemplate使用工具类.
 * <p>
 * 使用{@link RestTemplateUtilConfig}注入restTemplate使用,否则将使用默认的restTemplate
 * </p>
 * 
 * @author 80003093/tanquanfang
 * @date 2019/05/13
 */
@Slf4j
public class RestTemplateUtil {
    private static final RestTemplateUtil instance = new RestTemplateUtil();
    private static RestTemplate restTemplate;

    private RestTemplateUtil() {}

    public static RestTemplateUtil getInstance() {

        return instance;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        if (RestTemplateUtil.restTemplate == null) {
            RestTemplateUtil.restTemplate = restTemplate;
        }
    }

    private static RestTemplate getRestTemplate() {
        // 如果获取时还没有restTemplate 初始一个默认的
        if (RestTemplateUtil.restTemplate == null) {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000);
            factory.setReadTimeout(10000);
            RestTemplateUtil.restTemplate = new RestTemplate(factory);
        }
        // if (RestTemplateUtil.restTemplate == null) {
        // throw new BizException("请自行设置redisTemplate或扫描使用RedisUtilConfig配置后，才可正常使用");
        // }
        return RestTemplateUtil.restTemplate;
    }

    /**
     * Send get.
     *
     * @param <T>
     *            the generic type
     * @param url
     *            the url
     * @param responseType
     *            the response type 响应类型
     * @param uriVariables
     *            the uri variables 路径变量map
     * @return the t
     */
    public static <T> T sendGet(String url, Class<T> responseType, Map<String, ?> uriVariables) {
        // restTemplate.getForObject("/{name}/details", Details.class, name);
        return getRestTemplate().getForObject(url, responseType, uriVariables);
    }

    /**
     * Send get.
     *
     * @param <T>
     *            the generic type
     * @param url
     *            the url
     * @param responseType
     *            the response type
     * @param uriVariables
     *            the uri variables
     * @return the t
     */
    public static <T> T sendGet(String url, Class<T> responseType, Object... uriVariables) {
        // restTemplate.getForObject("/{name}/details", Details.class, name);
        return getRestTemplate().getForObject(url, responseType, uriVariables);
    }

    /**
     * Send post.
     *
     * @param <T>
     *            the generic type
     * @param url
     *            the url
     * @param request
     *            the request 可直接设置json类型 或使用httpEntity封装请求头，请求体
     * @param responseType
     *            the response type 响应类型
     * @param uriVariables
     *            the uri variables 路径变量map
     * @return the t
     */
    public static <T> T sendPost(String url, @Nullable Object request, Class<T> responseType,
        Map<String, ?> uriVariables) {
    	
        return getRestTemplate().postForObject(url, request, responseType, uriVariables);
    }

    public static ResponseEntity exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity, Class responseType, Object... uriVariables) {

        return getRestTemplate().exchange(url, method, requestEntity, responseType);
    }

    
    
    /**
     * Send post.
     *
     * @param <T>
     *            the generic type
     * @param url
     *            the url
     * @param request
     *            the request 可直接设置json类型 或使用httpEntity封装请求头，请求体
     * @param responseType
     *            the response type 响应类型
     * @param uriVariables
     *            the uri variables
     * @return the t
     */
    public static <T> T sendPost(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) {
        return getRestTemplate().postForObject(url, request, responseType, uriVariables);
    }

    /**
     * Send json post.
     *
     * @param <T>
     *            the generic type
     * @param url
     *            the url
     * @param request
     *            the request
     * @param responseType
     *            the response type
     * @param uriVariables
     *            the uri variables
     * @return the t
     */
    public static <T> T sendJsonPost(String url, @Nullable T request, Class<T> responseType, Object... uriVariables) {
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<>(request, headers);

        return getRestTemplate().postForObject(url, entity, responseType, uriVariables);
    }

    public static File getFile(String url, String targetFileAbsolutePath) {
        HttpHeaders header = new HttpHeaders();
        List<MediaType> list = new ArrayList<>();
        // 指定下载文件类型
        list.add(MediaType.APPLICATION_OCTET_STREAM);
        header.setAccept(list);

        HttpEntity<byte[]> request = new HttpEntity<>(header);

        File targetFile = new File(targetFileAbsolutePath);
        if (targetFile.exists()) {
            FileUtils.deleteQuietly(targetFile);
        }
        try (FileOutputStream out = FileUtils.openOutputStream(targetFile)) {
            ResponseEntity<byte[]> response = getRestTemplate().exchange(url, HttpMethod.POST, request, byte[].class);
            // 取得文件字节 写入目标文件
            IOUtils.write(response.getBody(), out);
            return targetFile;
        } catch (Exception e) {
            log.error("下载文件失败 url:{},targetFilePath:{}", url, targetFileAbsolutePath, e);
        }
        return null;
    }

    public static void main(String[] args) {
        // // 调试时 手动使用连接工厂
        // HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        // factory.setConnectTimeout(5000);
        // factory.setReadTimeout(3000);
        // RestTemplateUtil.getInstance().setRestTemplate(new RestTemplate(factory));
        // getRestTemplate().postForEntity(url, request, responseType)
        /*RestTemplateUtil.getFile(
            "http://eas.zhaopin.com/CompanyPlatform/RedirectToReport.ashx?eid=3550d564e4754b22884dc335fb5df3f6&eaid=13698869&spid=3577&type=6",
            "E:/tmp.pdf");*/
    	
    	 HttpHeaders headers = new HttpHeaders();
    	  
         // 设置请求数据
         Map<String, Object> data = new HashMap<>();

         // 发起请求
         String responseStr = RestTemplateUtil.sendPost("http://cemp.sit.sf-express.com/sst-activitywebapp/packet/outerPacketDeliver", new HttpEntity<>(data, headers), String.class);
         
         System.out.println("result:" + responseStr);
    }
    
    /**
     * 获取客户端Ip
     * @param request
     * @return
     */
    public static String getClientIp() {
    	HttpServletRequest request = RequestCtxtUtil.getHttpRequest();
    	if (request == null) {
			return "";
		}
    	
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) { // "***.***.***.***".length() = 15
            if (ip.indexOf(',') > 0) {
                ip = ip.substring(0, ip.indexOf(','));
            }
        }
        return ip;
    }
    
}
