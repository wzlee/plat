package com.eaglec.plat.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.publik.FileManagerBiz;
import com.eaglec.plat.biz.publik.SMEIdentifieBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.FileManager;
import com.eaglec.plat.domain.base.SMEIdentifie;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.JSONResult;


@Controller
@RequestMapping(value = "/sme")
public class SMEController extends BaseController {
	@Autowired
	SMEIdentifieBiz identifieBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private FileManagerBiz fileManagerBiz;
	private String[] str = { "applicationReport", "copiesOfDocuments",
			"socialSecurityDetails", "copyOfTheAuditReport",
			"otherSupportingDocuments", "applicationUndertaking",
			"tenderDocuments" };

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-11-23
	 * @description TODO actionPath eg:
	 * @param status
	 * @param companyName
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/queryIdentifie")
	public void queryIdentifie(
			Integer status,
			@RequestParam(value = "companyName", defaultValue = "", required = true) String companyName,
			@RequestParam(value = "start", defaultValue = "0", required = true) int start,
			@RequestParam(value = "limit", defaultValue = "30", required = true) int limit,
			HttpServletRequest request,
			HttpServletResponse response) {
		outJson(response,
				identifieBiz.findByStatus(status, companyName, start, limit));
	}

	/**
	 * 修改认定情况，通过or驳回
	 * 
	 * @author huyj
	 * @sicen 2013-11-25
	 * @param identifie
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/update")
	public void update(SMEIdentifie identifie, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			identifie.setApprovedTime(DateFormatUtils.format(new Date(),
					"yyyy-MM-dd HH:mm:ss"));
			Manager manager = (Manager) request.getSession().getAttribute(
					"manager");
			identifie.setManager(manager);
			identifieBiz.update(identifie);
			this.outJson(response, new JSONResult(true, "操作成功"));
		} catch (Exception e) {
			this.outJson(response,
					new JSONResult(false, "操作失败" + e.getLocalizedMessage()));
		}
	}

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-11-22
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/identifie")
	public String identifie(HttpServletRequest request,
			HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		request.getSession().setAttribute("user",
				userBiz.findUserById(user.getId()));
		List<SMEIdentifie> identifieList = identifieBiz.findByUserId(user
				.getId());
		request.setAttribute("identifieList", identifieList);
		return "ucenter/sme_index";
	}

	/**
	 * 中小微企业认定
	 * 
	 * @author huyj
	 * @sicen 2013-11-23
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/auth")
	public String auth(
			@RequestParam(value = "id", defaultValue = "", required = true) String id,
			HttpServletRequest request, HttpServletResponse response) {
		SMEIdentifie	identifie =  null;
		if (!StringUtils.isEmpty(id)) {
			identifie = identifieBiz.findById(Integer.parseInt(id));
		}
		request.setAttribute("identifie", identifie);
		return "ucenter/sme_auth";
	}


	/**
	 * 
	 * @author huyj
	 * @sicen 2013-11-26
	 * @description TODO actionPath eg:
	 * @param identifie
	 * @param request
	 * @param response
	 * @return
	 */
	@NeedSession(SessionType.USER)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(SMEIdentifie identifie,
			HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("user");
		request.getSession().setAttribute("user",
				userBiz.findUserById(user.getId()));
		String fileStr = request.getParameter("fileMap");
		String temp = "";
		try {
			temp = URLDecoder.decode(fileStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JSONObject fileMap = JSON.parseObject(temp);
		// 初始化User默认信息，及上传附件
		initUser(identifie, user, fileMap);

		// id不为空，表示是重新提交认证
		if (null != identifie.getId()) {
			identifie.setModifyTime(DateFormatUtils.format(new Date(),
					"yyyy-MM-dd HH:mm:ss"));
			identifieBiz.update(identifie);

		} else {
			// id为空时，还没有审批过，设置manager为null
			identifie.setManager(null);
			identifie.setApplicationTime(DateFormatUtils.format(new Date(),
					"yyyy-MM-dd HH:mm:ss"));
			identifieBiz.add(identifie);
		}

		// 添加或修改完数据后，加载列表
		List<SMEIdentifie> identifieList = identifieBiz.findByUserId(user
				.getId());
		request.setAttribute("identifieList", identifieList);
		return "ucenter/sme_index";
	}

	/**
	 * 设置User默认信息，及上传附件
	 * 
	 * @author huyj
	 * @sicen 2013-11-23
	 * @description TODO
	 * @param identifie
	 * @param user
	 * @param fileMap
	 */
	private void initUser(SMEIdentifie identifie, User user, JSONObject fileMap) {
		identifie.setApplicationReport(fileMap.getString("applicationReport"));
		identifie.setCopiesOfDocuments(fileMap.getString("copiesOfDocuments"));
		identifie.setSocialSecurityDetails(fileMap
				.getString("socialSecurityDetails"));
		identifie.setCopyOfTheAuditReport(fileMap
				.getString("copyOfTheAuditReport"));
		identifie.setApplicationUndertaking(fileMap
				.getString("otherSupportingDocuments"));
		identifie.setOtherSupportingDocuments(fileMap
				.getString("applicationUndertaking"));
		identifie.setTenderDocuments(fileMap.getString("tenderDocuments"));
		// 设置提交申请的用户--当前登录的用户
		identifie.setUser(user);
		// 修改状态为未处理
		identifie.setApproveStatus(Constant.SME_APPROVE_STATUS);
		if (null != identifie.getId()) {
			identifie.setModifyTime(DateFormatUtils.format(new Date(),
					"yyyy-MM-dd HH:mm:ss"));

		} else {
			// id为空时，还没有审批过，设置manager为null
			identifie.setManager(null);
			identifie.setApplicationTime(DateFormatUtils.format(new Date(),
					"yyyy-MM-dd HH:mm:ss"));
		}
	}

	/**
	 * 上传认定资料
	 * 
	 * @author huyj
	 * @sicen 2013-11-23
	 * @description TODO
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/uploadSmeFiles", method = RequestMethod.POST)
	public void uploadSmeFiles(MultipartHttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			for (String name : str) {
				List<MultipartFile> files = request.getFiles(name);
				String contextPath = request.getSession().getServletContext().getRealPath(Common.attachmentPath);
				StringBuffer sb = new StringBuffer();
				String savePath = "";
				for (MultipartFile file : files) {
					if (file.isEmpty())
						continue;
					String originalFilename = file.getOriginalFilename();
					int index = originalFilename.lastIndexOf(".");
					long timestamp = System.currentTimeMillis();
					String orgFileName = timestamp + originalFilename.substring(index);
//					long timestamp = System.currentTimeMillis();
//					String orgFileName = timestamp +"f"+file.getOriginalFilename();
					// 将文件存入硬盘
					File orgFile = new File(contextPath,orgFileName);
					file.transferTo(orgFile);
					// 记录文件名
					sb.append("," + orgFile.getName());
					// 保存文件对象信息
					FileManager fileManager = new FileManager();
					String date = StrUtils.formateDate("yyyy-MM-dd HH:mm:ss",new Date());
					fileManager.setFname(orgFile.getName());
					fileManager.setDate(date);
					fileManagerBiz.saveFileManager(fileManager);
					savePath += timestamp+"f"+originalFilename + "|";
				}
				if (!StringUtils.isEmpty(savePath)) {
					savePath = savePath.substring(0, savePath.length() - 1);
					map.put(name, savePath);
				}
			}
			super.outJson(response, map);
		} catch (Exception e) {
			super.outJson(response, new JSONResult(false, "上传失败"));
		}
	}
	
/*	@RequestMapping("word")
	public void wordcontent(HttpServletRequest request,String filename,HttpServletResponse response){
		String contextPath = request.getSession().getServletContext().getRealPath(Common.attachmentPath);
		String content = "";
		try {
			content = WordExcelToHtml.getWordAndStyle(contextPath+File.separator+filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.outJson(response, new JSONResult(true, content));
	}*/
}
