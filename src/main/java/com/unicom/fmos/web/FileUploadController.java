package com.unicom.fmos.web;

import com.unicom.fmos.dao.business.FileDao;
import com.unicom.fmos.dto.FormModelDto;
import com.unicom.fmos.entity.business.File;
import com.unicom.fmos.entity.sys.User;
import com.unicom.fmos.service.sys.FileService;
import com.unicom.fmos.utils.UtilFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by unicom on 2017/2/8.
 */
@Controller
@RequestMapping(value = "/user/businiess/fileupload")
public class FileUploadController {

    @Autowired
    private FileService fileService;
    @Autowired
    private FileDao fileDao;

    @RequestMapping(value = "/modelfileupload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String modelfileupload(@RequestPart("modelfiles") byte[] modelFiles, @RequestParam("lineId") Integer lineId, @RequestParam("fileName") String fileName, @RequestParam("fileExt") String fileExt, @RequestParam("fileSize") Integer fileSize) {
        File insertFile = new File();
        insertFile.setFileBody(modelFiles);
        insertFile.setFileSource(0);
        insertFile.setSourceLineId(lineId);
        insertFile.setFileName(fileName);
        insertFile.setFileExt(fileExt);
        insertFile.setFileSize(fileSize);
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        insertFile.setCreateTime(new Timestamp(System.currentTimeMillis()));
        insertFile.setCreateUser(user.getUserName());
        return fileService.insert(insertFile);
    }

    @RequestMapping(value = "/Instancefileupload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String instancefileupload(@RequestPart("Instancefiles") byte[] Instancefiles, @RequestParam("lineId") Integer lineId, @RequestParam("fileName") String fileName, @RequestParam("fileExt") String fileExt, @RequestParam("fileSize") Integer fileSize) {
        File insertFile = new File();
        insertFile.setFileBody(Instancefiles);
        insertFile.setFileSource(1);
        insertFile.setSourceLineId(lineId);
        insertFile.setFileName(fileName);
        insertFile.setFileExt(fileExt);
        insertFile.setFileSize(fileSize);
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        insertFile.setCreateTime(new Timestamp(System.currentTimeMillis()));
        insertFile.setCreateUser(user.getUserName());
        return fileService.insert(insertFile);
    }

    @RequestMapping(value = "/Instancepreviewupload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String instancepreviewupload(@RequestPart("InstancePreviews") byte[] InstancePreviews, @RequestParam("lineId") Integer lineId, @RequestParam("fileName") String fileName, @RequestParam("fileExt") String fileExt, @RequestParam("fileSize") Integer fileSize) {
        File insertFile = new File();
        insertFile.setFileBody(InstancePreviews);
        insertFile.setFileSource(2);
        insertFile.setSourceLineId(lineId);
        insertFile.setFileName(fileName);
        insertFile.setFileExt(fileExt);
        insertFile.setFileSize(fileSize);
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        insertFile.setCreateTime(new Timestamp(System.currentTimeMillis()));
        insertFile.setCreateUser(user.getUserName());
        return fileService.insertOfUpdate(insertFile);
    }

    @RequestMapping(value = "/infos", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String infos(@RequestBody List<File> files) {
        return fileService.infos(files.get(0));
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String delRole(@RequestParam(required = false, value = "idArr[]") List<Integer> list, HttpServletRequest request, HttpSession session) {
        return fileService.del(list);
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    @ResponseBody
        public void generateFormModel(File fileCondition, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        File file = fileDao.selectByPrimaryKey(fileCondition.getLineId()).get(0);
        try {
            response.setHeader("Content-disposition", "attachment; filename="+new String(file.getFileName().getBytes("GB2312"),"iso8859-1"));// 设定输出文件头
            response.setContentType("application/octet-stream");// 定义输出类型
            OutputStream os=response.getOutputStream();
            byte[] contentbytes = file.getFileBody();
            InputStream is = new ByteArrayInputStream(contentbytes);

            int read = 0;
            byte[] bytes = new byte[1024];

            while((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();
            os.close();
        } catch (UnsupportedEncodingException ex) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/preivew", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void preivew(File file, HttpServletResponse response) {
        List<File> fileList = fileDao.selectWithBLOBs(file);
        BufferedImage img = new BufferedImage(300, 150, BufferedImage.TYPE_INT_RGB);
        if(fileList.isEmpty()) {
            return;
        } else {
            try {
                OutputStream os=response.getOutputStream();
                byte[] contentbytes = fileList.get(0).getFileBody();
                InputStream is = new ByteArrayInputStream(contentbytes);

                int read = 0;
                byte[] bytes = new byte[1024];

                while((read = is.read(bytes)) != -1) {
                    os.write(bytes, 0, read);
                }
                ImageIO.write(img, "JPEG", os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
