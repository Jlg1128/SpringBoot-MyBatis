package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.Responese.LoginInterceptor;
import com.example.mybatisdemo.Responese.MyResponese;
import com.example.mybatisdemo.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.*;

@RestController
public class FileController {
    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @RequestMapping("/api/upload")
    public ResultVO uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                MyResponese.error("文件为空");
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            logger.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "/Users/jlg1128/Downloads/";
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return MyResponese.success("上传成功");
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return MyResponese.success("上传失败");
    }
    @GetMapping("/api/download")
    public String download(@RequestParam("fileName") @Valid @NotNull(message = "文件名不能为空") String fileName,
    HttpServletRequest request, HttpServletResponse response
    ) {
        System.out.println(fileName);
        File file = new File( "/Users/jlg1128/Downloads/"+ fileName);
        if(!file.exists()){
            return "下载文件不存在";
        }
        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + "xixi.txt" );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            logger.error("{}",e);
            return "下载失败";
        }
        return "下载成功";
    }
}
