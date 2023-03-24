package com.chsi.svd.webapp.controller;

import com.chsi.svd.service.ComponentService;
import com.chsi.svd.service.CveService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Controller
@RequestMapping("detect")
public class DetectController {

    @Resource
    private ComponentService componentService;
    @Resource
    private CveService cveService;
    private static final String UPLOAD_FOLDER = "D:\\uploadFiles";

    @RequestMapping("index")
    public String index(){
        return "detect";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){
        if (!file.isEmpty()){
            try {
                String fileName = file.getOriginalFilename();
                ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
                ZipEntry zipEntry = null;
                while ((zipEntry = zipInputStream.getNextEntry()) != null){
                    String zipFileName = zipEntry.getName();
                    String filePath = UPLOAD_FOLDER + File.separator + zipFileName;
                    File zipFile = new File(filePath);
                    if (zipEntry.isDirectory()){
                        if(!zipFile.exists()){
                            zipFile.mkdirs();
                        }
                    } else {
                        File parent = zipFile.getParentFile();
                        if (!parent.exists()){
                            parent.mkdirs();
                        }
                        OutputStream outputStream = new FileOutputStream(zipFile);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len=zipInputStream.read(buffer)) > 0){
                            outputStream.write(buffer,0,len);
                        }
                        outputStream.close();
                    }
                }
                System.out.println();
                zipInputStream.closeEntry();
                zipInputStream.close();

                // File dest = new File(filePath);
                // file.transferTo(dest);
                return "success";
            } catch (Exception e){
                e.printStackTrace();
                return "fail";
            }
        } else {
            return "empty file";
        }
    }


}
