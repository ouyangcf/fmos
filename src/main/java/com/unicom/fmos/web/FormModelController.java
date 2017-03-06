package com.unicom.fmos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.unicom.fmos.dto.FormModelDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by zhaojb on 2017/1/1.
 */
@Controller
@RequestMapping(value = "/user/businiess/form")
public class FormModelController {
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    public void generateFormModel(FormModelDto formModelDto, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        String fileName = formModelDto.getFormNumber();
        try {
            response.setHeader("Content-disposition", "attachment; filename="+new String(fileName.getBytes("GB2312"),"iso8859-1")+".html");// 设定输出文件头
            response.setContentType("application/octet-stream");// 定义输出类型
            OutputStream os=response.getOutputStream();
            byte[] contentbytes = formModelDto.getFormContent().getBytes();
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
}
