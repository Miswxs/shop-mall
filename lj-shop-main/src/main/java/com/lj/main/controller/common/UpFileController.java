package com.lj.main.controller.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lj.common.enums.ErrorCode;
import com.lj.common.resp.RespMessage;
import com.lj.common.util.spring.PropertiesUtil;

@RestController
@RequestMapping("/UpFile")
public class UpFileController {

	private static final Logger logger = LoggerFactory.getLogger(UpFileController.class);

	/**
	 * 上传单个文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/upOneFile", method = RequestMethod.POST)
	public RespMessage upOneFile(MultipartFile file, HttpServletRequest request) throws IOException {
		logger.info("UpFileController.upOneFile is start");
		String sep = File.separator;
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multiRequest.getFileNames();
		if (iterator == null) {
			return new RespMessage(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage(), "");

		} else {
			// 将上传的图片保存到服务器，并且返回图片的相对路径到前端页面
			String extension = PropertiesUtil.getString("uploadFileUrl");
			String sqlUrl=sep +"images" + sep + "upload"+ sep +new SimpleDateFormat("yyyyMMdd").format(new Date());
			String path = extension+ sqlUrl;
			File dir = new File(path);  
			if (!dir.exists()) {
				try {
					boolean result=dir.mkdirs();
				} catch (Exception e) {
					logger.info("UpFileController.upOneFile create path is fail {}",path);
				}
				 
					
			}

			// 获取客户端文件名
			String fileName = file.getOriginalFilename();
			String names[] = fileName.split(sep + ".");
			String newFileName = "";
			if (names.length >= 1) {
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				newFileName = uuid + "." + names[names.length - 1];
			}
			logger.info("UpFileController.upOneFile path={}|file={}", dir.getPath(), fileName);
			FileOutputStream imgOut = new FileOutputStream(new File(dir, newFileName));
			imgOut.write(file.getBytes());// 返回一个字节数组文件的内容
			imgOut.close();
			String url = sqlUrl + sep + newFileName;
			Map<String, String> map = new HashMap<String, String>();
			map.put("url", url);
			JSONObject jsonObject=JSONObject.parseObject(JSON.toJSONString(map));
			logger.info("UpFileController.upOneFile is success|res={}", map.toString());
			return RespMessage.successResp("上传成功", url);

		}

	}

	/* 获取一条随机字符串 */
	public String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	
	public static void main(String[] args) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("url", "\\upLoad\\images\\20180721\\c799f547ab504e6cb46d7a8389e8d4e3.png");
		JSONObject jsonObject=JSONObject.parseObject((JSON.toJSONString(map)));
        System.out.println(jsonObject.toString());
			
	}
}
