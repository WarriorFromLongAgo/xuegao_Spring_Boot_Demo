package com.xuegao.springboot_tool.controller;

import com.xuegao.springboot_tool.model.po.UserInfo;
import io.swagger.annotations.Api;
import org.apache.poi.util.IOUtils;
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
public class IndexController {

    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView uploadFile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload");
        return modelAndView;
    }

    @GetMapping("/login")
    public String logout() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/users/password")
    public String updatePassword() {
        return "users/update_password";
    }

    @GetMapping("/users/info")
    public String userDetail(Model model) {
        model.addAttribute("flagType", "edit");
        return "users/user_edit";
    }

    @GetMapping("/menus")
    public String menusList() {

        return "menus/menu_list";
    }

    @GetMapping("/roles")
    public String roleList() {
        return "roles/role_list";
    }

    @GetMapping("/users")
    public String userList() {
        return "users/user_list";
    }

    @GetMapping("/logs")
    public String logList() {
        return "logs/log_list";
    }

    @GetMapping("/depts")
    public String deptList() {
        return "depts/dept_list";
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

    @GetMapping("/main")
    public String indexHome() {
        return "main";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/build")
    public String build() {
        return "build";
    }

    @GetMapping("/systemInfo")
    public String systemInfo(Model model) {
        return "systemInfo";
    }

    @GetMapping("/sysContent")
    public String sysContent() {
        return "syscontent/list";
    }

    @GetMapping("/sysDict")
    public String sysDict() {
        return "sysdict/list";
    }

    @GetMapping("/sysGenerator")
    public String sysGenerator() {
        return "generator/list";
    }

    @GetMapping("/sysJob")
    public String sysJob() {
        return "sysjob/list";
    }

    @GetMapping("/sysJobLog")
    public String sysJobLog() {
        return "sysjoblog/list";
    }

    @PostMapping("/sysFiles")
    public String sysFiles(UserInfo userInfo) {
        return "sysfiles/list";
    }

    @PostMapping("/postTest")
    public String postTest(@RequestBody UserInfo userInfo) {
        return "sysfiles/list";
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
    public @ResponseBody byte[] getImage() throws IOException {
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
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/com/baeldung/produceimage/image.jpg");
        return IOUtils.toByteArray(in);
    }
}
