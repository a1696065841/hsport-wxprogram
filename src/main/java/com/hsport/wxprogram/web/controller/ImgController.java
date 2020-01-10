package com.hsport.wxprogram.web.controller;

import com.hsport.wxprogram.common.util.AjaxResult;
import com.hsport.wxprogram.common.util.DateUtil;
import com.hsport.wxprogram.common.util.UserContext;
import com.hsport.wxprogram.common.util.picUtil;
import com.hsport.wxprogram.domain.Foodimg;
import com.hsport.wxprogram.domain.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/img")
@CrossOrigin
public class ImgController {
    @Autowired
    HttpServletRequest request;

    @ApiOperation(value="用户上传图片")
    @RequestMapping(value = "/upLoad",method = RequestMethod.POST)
    public AjaxResult chuanTu(@RequestParam("multipartFile") MultipartFile multipartFile){
        String UPLOAD_FOLDER = "D:/images/imgggbond";
        Path path = Paths.get(UPLOAD_FOLDER + "/");
        //获取当前登录用户  需要修改
        try {
            String s = picUtil.singleFileUpload(multipartFile,path);
            if (s.equals("文件为空，请重新上传")){
                return AjaxResult.me().setMessage("文件为空，请重新上传！");
            }
            return AjaxResult.me().setSuccess(true).setResultObj("http://test.qinglizi.vip/imgggbond/"+s);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setMessage("上传图片失败！"+e.getMessage()).setSuccess(false);
        }
    }
}
