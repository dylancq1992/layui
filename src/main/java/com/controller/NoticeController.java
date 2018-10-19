package com.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.service.NoticeService;
import com.utils.GridModel;
import com.utils.PageUtil;
import com.vo.NoticeVO;
import com.vo.SessionConstants;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Resource
	private NoticeService noticeService;
	
	
	@RequestMapping("/getAllNoticeList")
	@ResponseBody
	public GridModel getAllNoticeList(HttpServletRequest request,NoticeVO vo) throws Exception{
		
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
			
		Map<String, String> map = PageUtil.putPageParam(request);
		
		GridModel result=noticeService.getAllNoticeList(map, vo);
		
		return result;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Map add(HttpServletRequest request,NoticeVO vo) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		vo.setCreator((String)request.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR_ID));
		try {
			noticeService.add(vo);
			map.put("success",true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("message", e.getMessage());
		}
		return map;
	}
	
	
	
	
	
	@RequestMapping("/uploadImg")
	@ResponseBody
	public Map uploadImg(HttpServletRequest request,@RequestParam(value = "imgFile", required = true) MultipartFile imgFile) throws Exception{
		
		  Map<String, Object> result = new HashMap<String, Object>();
        
        //文件保存目录路径
        String savePath = request.getRealPath("/") + "upload/";

        //文件保存目录URL
        String saveUrl  = request.getContextPath() + "/upload/";
        
        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        
        // 定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        
        // 检查扩展名

        String fileExt = imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

        if (!Arrays.asList(extMap.get(dirName).split(",")).contains(fileExt)) {
            
            result.put("error", 1);
            result.put("message", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
            return result;
        }
        
     // 判断文件是否为空  
        if (!imgFile.isEmpty()) {  
            try {  
            	 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                 String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                 
                File filepath = new File(savePath, newFileName);
                if (!filepath.exists()) 
                    filepath.mkdirs();
                // 文件保存路径  
               
                // 转存文件  
                imgFile.transferTo(filepath); 
                result.put("error", 0);
                result.put("url", saveUrl + newFileName);
                return result;  
            } catch (Exception e) {  
            	 result.put("error", 1);
                 result.put("message", e.getMessage());
                 return result;  
            }  
        }  
        
        result.put("error", 1);
        result.put("message", "未知错误！");
        return result;  
		
	}

}
