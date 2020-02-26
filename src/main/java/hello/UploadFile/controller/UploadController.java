package hello.UploadFile.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @创建人 sgwang
 * @name UploadController
 * @user 91119
 * @创建时间 2019/7/27
 * @描述
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, methods = {RequestMethod.GET, RequestMethod.POST})
public class UploadController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/index02")
    public String index02() {
        return "index_post";
    }

    @PostMapping("/upload/simple")
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("getOriginalFilename: " + file.getOriginalFilename());

        File tmpPath = new File("D:\\tmp\\");
        File tmpFile = new File(tmpPath, file.getOriginalFilename());
        if (!tmpFile.exists()) {
            tmpFile.createNewFile();
        } else {
            tmpFile.delete();
            tmpFile.createNewFile();
        }

        file.transferTo(tmpFile);

        return "单文件上传！";
    }

    @RequestMapping("/download")
    public String fileDownload(@RequestParam("fileName") String fileName, HttpServletResponse resp) throws IOException {
        System.out.println(fileName);

        File tmpPath = new File("D:\\tmp\\");
        File downFIle = new File(tmpPath, fileName);
        if (downFIle.exists()) {
            resp.setHeader("content-type", "application/octet-stream");
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            byte[] buff = new byte[5120];
            BufferedInputStream bis = null;
            OutputStream respOs = null;

            respOs = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(downFIle));

            int i = 0;
            while (bis.read(buff) != -1) {
                respOs.write(buff, 0, buff.length);
                respOs.flush();
            }
            bis.close();
        }

        return "下载成功！";
    }
}
