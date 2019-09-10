package com.jun.controller;

import com.jun.converter.entity.Attachment;
import com.jun.domain.User;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
public class FileUploadController {

    // 上传文件会自动绑定到MultipartFile中
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest request, @RequestParam("description") String description,
                         @RequestParam("file") MultipartFile file) throws Exception {

        System.out.println(description);
        // 如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            // 上传文件路径
            String path = request.getServletContext().getRealPath("/images/");
            // 上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            // 将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            return "success";
        } else {
            return "error";
        }
    }


    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request,
                           User user, Model model) throws Exception {
        System.out.println(user.getUsername());
        //如果文件不为空，写入上传路径
        if (!user.getImage().isEmpty()) {
            //上传文件路径
            String path = request.getServletContext().getRealPath("/images/");
            //上传文件名
            String filename = user.getImage().getOriginalFilename();
            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            user.getImage().transferTo(new File(path + File.separator + filename));
            //将用户添加到model
            model.addAttribute("user", user);
            return "userInfo";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
                                           @RequestParam("filename") String filename,
                                           Model model) throws Exception {
        //下载文件路径
        String path = request.getServletContext().getRealPath("/images/");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    @RequestMapping("testPathVariable/{name}")
    @ResponseBody
    public User testPathVariable(@PathVariable(value = "name") String name) {
        User user = new User();
        user.setUsername("Ezio");
        return user;
    }

    @RequestMapping("test")
    @ResponseBody
    public User test() {
        System.out.println("test");
        User user = new User();
        user.setUsername("Ezio");
        return user;
    }

    @RequestMapping("testMvc")
    public String testMvc(@RequestParam("name") String name, Map<String, Object> map, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("testMvc: " + name);
        map.put("name", name);
        return "forward:WEB-INF/content/success.jsp";
    }

    @RequestMapping("testRedirect")
    public String testRedirect(@RequestParam("name") String name) {
        System.out.println(name);
        return "redirect:registerForm.jsp";
    }

    @RequestMapping("testRedis")
    @ResponseBody
    public String testRedis() {
        Jedis jedis = new Jedis("127.0.0.1");
        String string = null;
        try {
            string = jedis.get("key");
            if (StringUtils.isEmpty(string)) {
                jedis.set("key", "valuuuuuuuuue");
                jedis.expire("key", 5);
            }
        } finally {
            jedis.close();
        }
        return string;
    }

    @RequestMapping("testRequestBody")
    @ResponseBody
    public String testRequestBody(@RequestBody Attachment attach) {
        System.out.println(attach);
        return "success";
    }

    public static void main(String[] args) {
        System.out.println(1);
    }

}
