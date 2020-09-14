package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.constant.common.WrappedResponse;
import com.xuegao.springboot_tool.model.doo.Product;
import com.xuegao.springboot_tool.model.po.UserInfo;
import io.swagger.annotations.Api;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;

/**
 * 视图
 * @author wenbin
 * @version V1.0
 */
@Api(tags = "视图")
@Controller
@RequestMapping("/index")
public class IndexController<T> extends BaseController<T> {
    private final static Logger log = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView uploadFile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload");
        return modelAndView;
    }

    @RequestMapping(path = "/get1", method = RequestMethod.GET)
    public WrappedResponse<T> get1Test() {
        return success("get1");
    }

    @RequestMapping(path = "/get2", method = RequestMethod.GET)
    public WrappedResponse<T> get2Test(@RequestParam String get2) {
        return success("get2 = " + get2);
    }

    @RequestMapping(path = "/post1", method = RequestMethod.POST)
    public WrappedResponse<T> post1Test(@RequestBody UserInfo userInfo) {
        return success("post1 = " + userInfo);
    }

    @RequestMapping(path = "/post2", method = RequestMethod.POST)
    public WrappedResponse<T> post2Test(UserInfo userInfo) {
        return success("post2 = " + userInfo);
    }

    @RequestMapping(path = "/post3", method = RequestMethod.POST)
    public WrappedResponse<T> post3Test(@RequestParam String post3) {
        return success("post3 = " + post3);
    }

    @RequestMapping(path = "/post4", method = RequestMethod.POST)
    public WrappedResponse<T> post4Test(@RequestBody UserInfo userInfo, @RequestBody Product product) {
        log.info("post4 = " + userInfo.toString());
        log.info("post4 = " + product.toString());
        return success("post4 = " + product);
    }

    @RequestMapping(path = "/post5", method = RequestMethod.POST)
    public WrappedResponse<T> post5Test(@RequestBody UserInfo userInfo, @RequestParam String post3) {
        log.info("post5 = " + userInfo.toString());
        log.info("post5 = " + post3);
        return success("post5 = " + post3);
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("/404")
    public String error404() {
        return "error/404";
    }

    @GetMapping("/500")
    public String error405() {
        return "error/500";
    }

    // Using produces for Returning Raw Data
    @GetMapping(
            value = "/get-file",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    @ResponseBody
    public byte[] getFile() throws IOException {
        InputStream in = getClass().getResourceAsStream("/com/baeldung/produceimage/data.txt");
        return IOUtils.toByteArray(in);
    }

    // 返回字节数组使我们几乎可以返回任何内容，例如图像或文件：
    @GetMapping(value = "/image")
    public @ResponseBody
    byte[] getImage() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/com/baeldung/produceimage/image.jpg");
        return IOUtils.toByteArray(in);
    }

    // 在这里，我们没有定义返回的字节数组是图像。因此，客户端将无法将其作为图像处理-而且浏览器很可能仅会显示图像的实际字节。
    // 要定义返回的字节数组对应于图像，我们可以设置产生所述的属性@GetMapping注释精确MIME类型返回对象的：
    @GetMapping(
            value = "/get-image-with-media-type",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImageWithMediaType() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/com/baeldung/produceimage/image.jpg");
        return IOUtils.toByteArray(in);
    }
}
