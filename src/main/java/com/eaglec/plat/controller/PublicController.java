package com.eaglec.plat.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eaglec.plat.biz.auth.AccessOauthBiz;
import com.eaglec.plat.biz.auth.ManagerBiz;
import com.eaglec.plat.biz.auth.MenuBiz;
import com.eaglec.plat.biz.auth.RoleBiz;
import com.eaglec.plat.biz.flat.FlatBiz;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.biz.publik.FileManagerBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.biz.user.EnterpriseBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.auth.Menu;
import com.eaglec.plat.domain.base.AccessOauth;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.FileManager;
import com.eaglec.plat.domain.base.Flat;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.utils.Common;
import com.eaglec.plat.utils.ImageUtil;
import com.eaglec.plat.utils.RecursionTree;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.APIResult;
import com.eaglec.plat.view.AuthInfo;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.JSONRows;

@Controller
@RequestMapping(value = "/public")
public class PublicController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PublicController.class);
	
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private FlatBiz flatBiz;
	@Autowired
	private AccessOauthBiz accessOauthBiz;
	@Autowired
	private ManagerBiz managerBiz;
	@Autowired
	private MenuBiz menuMangerBiz;
	@Autowired
	private RoleBiz roleManagerBiz;
	@Autowired
	private FileManagerBiz fileManagerBiz;
	@Autowired
	private ServiceBiz serviceBiz;
	@Autowired
	private CategoryBiz categoryBiz;
	@Autowired
	private EnterpriseBiz enterpriseBiz;
	
	/**
	 * 验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/authcode", method = RequestMethod.GET)
	public void validateCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ImageUtil.randCaptcha(request, response);
	}
	
	/**
	 * ajax检查验证码
	 * @param checkcode
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/authcode", method = RequestMethod.POST)
	public void passportLogin(@RequestParam(required = true) String checkcode, 
			HttpServletRequest request, HttpServletResponse response) {
		if(checkcode.equals(request.getSession().getAttribute("authcode").toString())){
			this.outJson(response, new JSONResult(true, "验证码正确"));
		}else{
			this.outJson(response, new JSONResult(false, "验证码输入错误"));
		}
	}
	
	@RequestMapping("check")
	public void validatelogin(HttpServletResponse response,HttpServletRequest request){
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if(manager == null){
			this.outJson(response,new JSONResult(false,"会话未创建或已过期!"));
		}else{
			this.outJson(response,new JSONResult(true,"会话验证成功!"));
		}
	}

	@RequestMapping("login")
	public void managerlogin(HttpServletResponse response,
			HttpServletRequest request, String password, String username) {
		String msg = "用户名或密码不正确，请重新输入";
		if (!StringUtils.isEmpty(username)) {
			Manager manager = managerBiz.findUserByUsername(username);
			if (manager != null) {
				if (manager.getUserStatus() != 1) {
					msg = "该用户无权限登录!";
				} else if (null == manager.getRole()) {
					msg = "该用户未分配任何角色，请先为此用户分配角色!";
				} else {
					List<Menu> list = RecursionTree.getResult(menuMangerBiz
							.findMenus(manager.getRole().getMenuIds()));
					if (manager.getPassword().equals(password)) {
						request.getSession().setAttribute("manager", manager);
						request.getSession().setAttribute(
								manager.getUsername(),
								JSON.toJSONString(list.toArray()));
						this.outJson(response, new JSONResult(true,"登陆成功!页面即将跳转..."));
					}
				}
			}
		}
		this.outJson(response, new JSONResult(false, msg));
	}

	@RequestMapping("userlogout")
	public void userlogout(HttpServletResponse response,HttpServletRequest request,Manager manager){
		try {
			request.getSession().removeAttribute("manager");
			this.outJson(response, new JSONResult(true, "退出登录成功!"));
		} catch (Exception e) {
			this.outJson(response, new JSONResult(false, "退出登录失败!"));
		}
	}
	
	/**
	 * @date: 2013-11-6
	 * @author：lwch
	 * @description：在线客服功能的所有文件上传
	 */
	@RequestMapping(value = "/chatUploadFile", method = RequestMethod.POST)
	public void chatUploadFile(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response){
		String contextPath = request.getSession().getServletContext().getRealPath(Common.chatUploadPath);
		this.fileUpload(contextPath, file, response);
	}

	/**
	 * 异步文件上传处理，返回文件名
	 * @author XiaDi
	 * @since 2013-7-23
	 * @param file 文件
	 * @eg admin/channel.jsp 
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		String contextPath = request.getSession().getServletContext().getRealPath(Common.uploadPath);
		this.fileUpload(contextPath, file, response);
	}

	private void fileUpload(String contextPath, MultipartFile file, HttpServletResponse response){
		File filepath = new File(contextPath);
		FileManager fileManager = new FileManager();
		
		if (!filepath.exists()) {
			filepath.mkdir();
			logger.info("[" + filepath.getAbsolutePath() + "]创建成功!");
		}
		try {
			int index = file.getOriginalFilename().lastIndexOf(".");
			long timestamp = System.currentTimeMillis();
			String orgFileName = timestamp + file.getOriginalFilename().substring(index);
			File orgFile = new File(contextPath, orgFileName);
			file.transferTo(orgFile);
			
			/*保存文件对象信息*/
			String date = StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date());
			fileManager.setFname(Common.CENTER_WEBSITE + "/" + Common.uploadPath + "/" + orgFileName);
			fileManager.setDate(date);
			fileManagerBiz.saveFileManager(fileManager);
			super.outJson(response, new JSONResult(true, Common.CENTER_WEBSITE + "/" + Common.uploadPath + "/" + orgFile.getName()));
		} catch (Exception e) {
			logger.info("Exception异常:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "上传失败"));
		}
	}
	
	/**
	 * kindeditor异步文件上传处理
	 * @author liuliping
	 * @since 2013-10-29
	 * 
	 * @param imgFile 文件
	 * @eg 支撑平台中的htmleditor
	 */
	@RequestMapping(value = "/uploadByKindedior", method = RequestMethod.POST)
	public void uploadByKindeditor(@RequestParam MultipartFile imgFile, HttpServletRequest request, HttpServletResponse response) {
		String contextPath = request.getSession().getServletContext().getRealPath(Common.uploadPath);
		File filepath = new File(contextPath);
		FileManager fileManager = new FileManager();
		
		if (!filepath.exists()) {
			filepath.mkdir();
			logger.info("[" + filepath.getAbsolutePath() + "]创建成功!");
		}
		try {
			int index = imgFile.getOriginalFilename().lastIndexOf(".");
			long timestamp = System.currentTimeMillis();
			String orgFileName = timestamp + imgFile.getOriginalFilename().substring(index);
			File orgFile = new File(contextPath, orgFileName);
			imgFile.transferTo(orgFile);
			
			/*保存文件对象信息*/
			String date = StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date());
			fileManager.setFname(orgFileName);
			fileManager.setDate(date);
			fileManagerBiz.saveFileManager(fileManager);
			
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url",  request.getContextPath() + "/upload/" + orgFile.getName());
			super.outJson(response, obj);
		} catch (Exception e) {
			logger.info("Exception异常:" + e.getLocalizedMessage());
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("message", "上传失败");
			super.outJson(response, obj);
		}
	}
	
	/**
	 * 异步多附件上传处理，返回文件名（多文件以逗号分隔：'123.jpg,qwe.jpg'）
	 * @author Xiadi
	 * @since 2013-10-11 
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/uploadAttachments", method = RequestMethod.POST)
	public void handleRequestInternal(MultipartHttpServletRequest request, HttpServletResponse response) {
		try {
			List<MultipartFile> files = request.getFiles("file");
			String contextPath = request.getSession().getServletContext().getRealPath(Common.attachmentPath);
			StringBuffer sb = new StringBuffer();
			
			for (MultipartFile file : files) {
				if (file.isEmpty())
					continue;
				// 将文件存入硬盘
				int index = file.getOriginalFilename().lastIndexOf(".");
				long timestamp = System.currentTimeMillis();
				String orgFileName = timestamp + file.getOriginalFilename().substring(index);
				File orgFile = new File(contextPath, orgFileName);
				file.transferTo(orgFile);
				// 记录文件名
				sb.append("," + orgFileName);
				// 保存文件对象信息
				FileManager fileManager = new FileManager();
				String date = StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date());
				fileManager.setFname(orgFileName);
				fileManager.setDate(date);
				fileManagerBiz.saveFileManager(fileManager);
			}
			logger.info("成功上传多个文件:" + sb.toString().substring(1));
			super.outJson(response, new JSONResult(true, sb.toString().substring(1)));
		} catch (Exception e) {
			logger.info("上传多文件异常:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "上传失败"));
		}
	}  
	
	/**
	 * 异步删除文件，返回状态（true or false）
	 * @author XiaDi
	 * @since 2013-7-24
	 * 
	 * @param fileName 文件名
	 * @param response
	 */
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public void deleteFile(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response){
		String contextPath = request.getSession().getServletContext().getRealPath(Common.uploadPath);
		File orgFile = new File(contextPath, fileName);
		boolean b = false;
		if (orgFile.exists()) {
			b = orgFile.delete();
		}
		super.outJson(response, new JSONResult(true, b + ""));
	}
	
	/**
	 * 提交申诉上传附件(目录：upload/attachment/*.doc)
	 * @author xuwf
	 * @since 2013-9-17
	 * 
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/uploadAttachment", method = RequestMethod.POST)
	public void uploadAttachment(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		String contextPath = request.getSession().getServletContext().getRealPath(Common.attachmentPath);
		File filepath = new File(contextPath);
		FileManager fileManager = new FileManager();
			
		if (!filepath.exists()) {
			filepath.mkdir();
			logger.info("[" + filepath.getAbsolutePath() + "]创建成功!");
		}
		try {
			int index = file.getOriginalFilename().lastIndexOf(".");
			long timestamp = System.currentTimeMillis();
			String orgFileName = timestamp + file.getOriginalFilename().substring(index);
			File orgFile = new File(contextPath, orgFileName);
			file.transferTo(orgFile);
				
			/*保存文件对象信息*/
			String date = StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date());
			fileManager.setFname(orgFileName);
			fileManager.setDate(date);
			fileManagerBiz.saveFileManager(fileManager);
			super.outJson(response, new JSONResult(true, orgFile.getName()));
		} catch (Exception e) {
			logger.info("Exception异常:" + e.getLocalizedMessage());
			super.outJson(response, new JSONResult(false, "上传失败"));
		}
	}
	
	/**
	 * @author wzlee
	 * 第三方平台申请api调用授权验证
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/oauth")
	public void apiOauth(@RequestParam(required=true) String client_id,@RequestParam(required=true) String client_secret,HttpServletRequest request,HttpServletResponse response) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String client_ip = request.getRemoteAddr();
		String client_host = request.getRemoteHost();
		Flat flat = flatBiz.queryByIpAndHost(client_ip,client_host);
		if(flat == null){
			this.outJson(response, new APIResult(false,-1,"请求地址或主机未通过授权!")); 
			logger.info("客户端:"+client_host+"["+client_ip+"]请求api未授权或已过期!错误信息:请求地址或主机未通过授权!");
		}else{
			if(!flat.getClient_id().equals(client_id)){
				this.outJson(response, new APIResult(false,1,"client_id匹配失败!")); 
			}else if(!flat.getClient_secret().equals(client_secret)){
				this.outJson(response, new APIResult(false,1,"client_secret匹配失败!")); 
			}else{
				Random rand = new Random();
				byte[] salt = new byte[12];
				rand.nextBytes(salt);
				// 计算消息摘要
				MessageDigest m = MessageDigest.getInstance("MD5");
				m.update(salt);
				m.update(client_secret.getBytes("UTF8"));
				byte s[] = m.digest();
				String accessToken = "";
				for (int i = 0; i < s.length; i++) {
					accessToken += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
				}
				AccessOauth accessOauth = new AccessOauth("api_token",accessToken);
				accessOauthBiz.addOrUpdate(accessOauth);
//				request.getSession().setAttribute("ACCESS_API",accessOauth);
				logger.info("客户端:"+client_host+"["+client_ip+"]请求api授权验证成功!");
				this.outJson(response, accessOauth); 
			}
		}
	}
	
	/**
	 * @author wzlee
	 * 调用api用户接口获取用户信息
	 */
	@RequestMapping(value = "/user")
	public void userInfo(@RequestParam String access_token,@RequestParam String openid,HttpServletRequest request,HttpServletResponse response) {
		String client_ip = request.getRemoteAddr();
		String client_host = request.getRemoteHost();
		if(access_token == null || access_token.isEmpty()){
			this.outJson(response, new APIResult(false,10001,"access_token不能为空!"));
		}else if(openid == null || openid.isEmpty()){
			this.outJson(response, new APIResult(false,10002,"openid不能为空!"));
		}else{
			List<AccessOauth> accessOauths = accessOauthBiz.findAccessOauthByAccessToken(access_token);
			if(accessOauths.size() == 0){
				this.outJson(response, new APIResult(true,10003,"access_token匹配失败"));
			}else{
				AccessOauth _accessToken = accessOauths.get(0);
				if(_accessToken.getAccessExpire() <= new Date().getTime()){
					this.outJson(response, new APIResult(true,-1,"accessToken已过期!"));
				}else{
					User user = userBiz.findUserByUid(openid);
					if(user == null){
						this.outJson(response, new APIResult(true,10000,"openid匹配失败!"));
					}else{
						this.outJson(response, new AuthInfo(user.getUserName(),user.getSex(),user.getUserStatus(),user.getEnterprise())); 
						logger.info(client_host+"["+client_ip+"]通过api接口获取用户："+user.getUserName()+"的信息成功!");
					}
				}
			}
		}
	}
	
	/**
	 * @author wzlee
	 * 调用api用户接口获取用户信息
	 */
	@RequestMapping(value = "/users")
	public void usersInfo(@RequestParam String access_token,@RequestParam String openids,HttpServletRequest request,HttpServletResponse response) {
		String client_ip = request.getRemoteAddr();
		String client_host = request.getRemoteHost();
		if(access_token == null || access_token.isEmpty()){
			this.outJson(response, new APIResult(false,10001,"access_token不能为空!"));
		}else if(openids == null || openids.isEmpty()){
			this.outJson(response, new APIResult(false,10002,"openids不能为空!"));
		}else{
			List<AccessOauth> accessOauths = accessOauthBiz.findAccessOauthByAccessToken(access_token);
			if(accessOauths.size() == 0){
				this.outJson(response, new APIResult(true,10003,"access_token匹配失败"));
			}else{
				AccessOauth _accessToken = accessOauths.get(0);
				if(_accessToken.getAccessExpire() <= new Date().getTime()){
					this.outJson(response, new APIResult(true,-1,"accessToken已过期!"));
				}else{
					String[] _openids = openids.split(",");
					if(_openids.length == 0){
						this.outJson(response, new APIResult(true,10000,"传入参数有误!"));
					}else{
						List<AuthInfo> authInfos = new ArrayList<AuthInfo>();
						for(int i=0;i<_openids.length;i++ ){
							User user = userBiz.findUserByUid(_openids[i]);
							if(user != null){
								AuthInfo authInfo = new AuthInfo(user.getUserName(),user.getSex(),user.getUserStatus(),user.getEnterprise());
								authInfos.add(authInfo);
							}
						}
						this.outJson(response,new JSONRows<AuthInfo>(true, authInfos, authInfos.size())); 
						logger.info(client_host+"["+client_ip+"]通过api接口获取["+authInfos.size()+"]条用户信息成功!");
					}
				}
			}
		}
	}
	
	/**
	 * @author wzlee
	 * 调用api用户接口获取用户所属企业名称
	 * @throws IOException 
	 */
	@RequestMapping(value = "/user/epname")
	public void epName(@RequestParam String access_token,@RequestParam String openid,@RequestParam(defaultValue="json") String type,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String client_ip = request.getRemoteAddr();
		String client_host = request.getRemoteHost();
		if(access_token == null || access_token.isEmpty()){
			this.outJson(response, new APIResult(false,10001,"access_token不能为空!"));
		}else if(openid == null || openid.isEmpty()){
			this.outJson(response, new APIResult(false,10002,"openid不能为空!"));
		}else{
			List<AccessOauth> accessOauths = accessOauthBiz.findAccessOauthByAccessToken(access_token);
			if(accessOauths.size() == 0){
				this.outJson(response, new APIResult(true,10003,"access_token匹配失败"));
			}else{
				AccessOauth _accessToken = accessOauths.get(0);
				if(_accessToken.getAccessExpire() <= new Date().getTime()){
					this.outJson(response, new APIResult(true,-1,"accessToken已过期!"));
				}else{
					User user = userBiz.findUserByUid(openid);
					if(user == null){
						this.outJson(response, new APIResult(false,10000,"传入参数有误!"));
					}else{
						if(type.equals("json")){
							this.outJson(response, new APIResult(true,00000,user.getEnterprise().getName()));
						}else{
							this.outHTML(response, user.getEnterprise().getName());
						}
						logger.info(client_host+"["+client_ip+"]通过api接口获取用户："+user.getUserName()+"的企业名称成功!");
					}
				}
			}
		}
	}
	
	/**
	 * @author wzlee
	 * 调用api用户接口获取用户信息
	 * @throws IOException 
	 */
	@RequestMapping(value = "/user/avatar")
	public void userAvatar(@RequestParam String access_token,@RequestParam String openid,@RequestParam(defaultValue="jpg") String f,@RequestParam(defaultValue="100")int w,@RequestParam(defaultValue="100")int h,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String client_ip = request.getRemoteAddr();
		String client_host = request.getRemoteHost();
		if(access_token == null || access_token.isEmpty()){
			this.outJson(response, new APIResult(false,10001,"access_token不能为空!"));
		}else if(openid == null || openid.isEmpty()){
			this.outJson(response, new APIResult(false,10002,"openid不能为空!"));
		}else{
			List<AccessOauth> accessOauths = accessOauthBiz.findAccessOauthByAccessToken(access_token);
			if(accessOauths.size() == 0){
				this.outJson(response, new APIResult(true,10003,"access_token匹配失败"));
			}else{
				AccessOauth _accessToken = accessOauths.get(0);
				if(_accessToken.getAccessExpire() <= new Date().getTime()){
					this.outJson(response, new APIResult(true,-1,"accessToken已过期!"));
				}else{
					User user = userBiz.findUserByUid(openid);
					if(user == null){
						this.outJson(response, new APIResult(true,10000,"传入参数有误!"));
					}else{
						String realPath = request.getSession().getServletContext().getRealPath("upload/");
						String filePath = realPath+"/"+user.getEnterprise().getPhoto();
						File photo = new File(filePath);
						if(!photo.exists()){
							logger.info("企业Photo文件["+filePath+"]不存在!");
						}else{
							ServletOutputStream baos = response.getOutputStream();
							try{  
								BufferedImage image = ImageIO.read(photo);
								
								int old_w = image.getWidth();  
						        int old_h = image.getHeight();  
						        BufferedImage tempImg = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);  
						        Graphics2D g = tempImg.createGraphics();  
						        g.setColor(Color.white);  
						        g.fillRect(0, 0, old_w, old_h);  
						        g.drawImage(image, 0, 0, old_w, old_h, Color.white, null);  
						        g.dispose();  
						        BufferedImage newImg = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB);  
						        newImg.getGraphics().drawImage(tempImg.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0,0, null);  
								ImageIO.write(newImg, f, baos);  
								logger.info(client_host+"["+client_ip+"]通过api接口获取用户："+user.getUserName()+"的企业logo成功!");
							}catch(Exception e) {  
								e.printStackTrace();  
							}finally {
								if (baos != null) {
									baos.flush();
									baos.close();
								}
							}  
						}
					}
				}
			}
		}
	}
	
	/**
	 * 窗口平台服务数据同步功能,考虑到是代码层面的访问接口,暂未做授权验证
	 * @param service
	 * @param request
	 * @param response
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/service/sync")
	public void serviceSync(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, UnsupportedEncodingException{
		Service service = JSON.parseObject(UriUtils.decode(request.getQueryString(),"utf-8"), Service.class);
		if(service.getId() == null){
			/********枢纽中企业的ID不同，故根据IcRegNumber查找出在枢纽中的该服务机构***********/
			Enterprise enterprise = enterpriseBiz.findByIcRegNumber(service.getEnterprise().getIcRegNumber());
			service.setEnterprise(enterprise);			
			serviceBiz.addBySync(service);			
		}else {			
			Service _service = serviceBiz.findServiceBySno(service.getServiceNo());
			/*******枢纽与窗口中服务ID、企业ID、流水ID不一致，窗口中没有totalScore、mallId属性，故忽略复制***********/
			String[] ignoreProperties={"id","enterprise","detailsId","totalScore","mallId"};
			BeanUtils.copyProperties(service, _service, ignoreProperties);
			serviceBiz.update(_service);
		}
		this.outJson(response, new JSONResult(true, "sync service successful!"));
		logger.info(service.getServiceName()+"["+service.getServiceNo()+"]同步添加成功!");
	}
	
	/**
	 * 窗口平台根据icRegNumber加载该服务机构的服务类别
	 * @author pangyf
	 * @since 2013-10-28
	 * @param icRegNumber
	 * @param request
	 * @param response
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/userCategory")
	public void userCategory(@RequestParam(value = "icRegNumber", defaultValue = "", required = false) String icRegNumber, HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, UnsupportedEncodingException{
		icRegNumber = request.getParameter("icRegNumber");
		String callback = request.getParameter("callback");
		logger.info("组织机构号：" + icRegNumber);
		if(!icRegNumber.isEmpty()){			
				List<Enterprise> list = enterpriseBiz.findEnterpriseByIcRegNumber(icRegNumber);
				if(!list.isEmpty()){
					Enterprise enterprise = list.get(0);
					List<Category> _list = enterprise.getMyServices();
					if(!list.isEmpty()){
						this.outJsonP(response, callback, _list);
						logger.info(enterprise.getMyServices().toString());
					}else {						
						try {
							response.setContentType("text/html;charset=utf-8");
							response.getWriter().write(callback+"([{}])");
							response.getWriter().flush();
							response.getWriter().close();
						} catch (Exception e) {			
							e.printStackTrace();
						}
					}
					
					
				}else {
					try {
						response.setContentType("text/html;charset=utf-8");
						response.getWriter().write(callback+"([{}])");
						response.getWriter().flush();
						response.getWriter().close();
					} catch (IOException e) {				
						e.printStackTrace();
					}
				}
		}else {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(callback+"([{}])");
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {				
				e.printStackTrace();
			}			
		}
	}
	
	/**
	 * 检查该服务的类别是否在该企业的服务领域范围内
	 * @author pangyf
	 * @since 2013-10-28
	 * @param sno
	 * @param request
	 * @param response
	 */	
	@RequestMapping(value = "/checkService")
	public void checkService(@RequestParam("sno")String sno,HttpServletRequest request, HttpServletResponse response){		
		String callback = request.getParameter("callback");
		Service service = serviceBiz.findServiceBySno(sno);
		List<Category> list = service.getEnterprise().getMyServices();		
		Integer cid = service.getCategory().getId();
		try {
			if(!list.isEmpty()){
				for(int i = 0;i<list.size();i++){
					if(cid == list.get(i).getId()){
						this.outJsonP(response, callback, new JSONResult(true,"该服务在服务领域内"));						
						break;
					}else if(i == list.size()-1){
						this.outJsonP(response, callback, new JSONResult(false,"该服务不在服务领域内"));						
					}
				}
			}else {
				this.outJsonP(response, callback, new JSONResult(false,"该服务机构还没有添加服务领域"));				
			}
		} catch  (Exception e) {
			this.outJsonP(response, callback, new JSONResult(false,"服务器产生异常，请联系管理员"));
		}
	}	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, FileNotFoundException{
		// 读入账号口令
		String name = "wzlee";
		String passwd = "letwego1314";
		// 生成盐
		Random rand = new Random();
		byte[] salt = new byte[12];
		rand.nextBytes(salt);
		// 计算消息摘要
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(salt);
		m.update(passwd.getBytes("UTF8"));
		byte s[] = m.digest();
		String result = "";
		for (int i = 0; i < s.length; i++) {
			result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
		}
		// 保存账号、盐和消息摘要
		PrintWriter out = new PrintWriter(new FileOutputStream("d:/passwdsalt.txt"));

		out.println(name);
		for (int i = 0; i < salt.length; i++) {
			out.print(salt[i] + ",");
		}
		out.println("");

		out.println(result);
		out.close();
	}	
	
	/**
	 * 用户同步
	 * @author pangyf
	 * @since 2013-11-1
	 * @param request
	 * @param response
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/user/sync")
	public void userSync(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, UnsupportedEncodingException{
		User user = JSON.parseObject(UriUtils.decode(request.getQueryString(),"utf-8"), User.class) , _user = new User();
		Enterprise enterprise = user.getEnterprise() , _enterprise = new Enterprise();
		if(user.getId() == null){
			String[] ignoreProperties={"id"};
			BeanUtils.copyProperties(enterprise, _enterprise, ignoreProperties);		
			_enterprise = enterpriseBiz.save(_enterprise);		
			BeanUtils.copyProperties(user, _user, ignoreProperties);	
			_user.setEnterprise(_enterprise);
			userBiz.add(_user);
		}else {
			String icRegNumber = user.getEnterprise().getIcRegNumber();
			_user  = userBiz.findUserByIcRegNumber(icRegNumber);
			_enterprise = _user.getEnterprise();
			/*******枢纽与窗口中服务ID、企业ID、流水ID不一致，窗口中没有totalScore、mallId属性，故忽略复制***********/
			String[] properties = {"forShort","name","enterpriseProperty","businessPattern","industryType","legalPerson","openedTime","salesRevenue","staffNumber","linkman",
					"tel","email","photo","address","mainRemark","type","licenceDuplicate","businessLetter","icRegNumber"};			
			Common.copyProperties(enterprise, _enterprise, properties);			
			enterpriseBiz.update(_enterprise);
			String[] _properties = {"password","userStatus"};
			Common.copyProperties(user, _user, _properties);
			userBiz.update(_user);
		}
		this.outJson(response, new JSONResult(true, "sync service successful!"));
		logger.info(user.getUserName()+"["+user.getUserName()+"]同步添加成功!");
	}
	
	/**
	 * 用于窗口跨域请求检验用户名是否重复
	 * @author pangyf
	 * @since 2013-11-1
	 * @param name
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/validateUserName")
	public void validateUserName(String userName, HttpServletRequest request, HttpServletResponse response){
		String callback = request.getParameter("callback");
		userName = request.getParameter("userName");
		try {					
			List<User> list = userBiz.findUserByName(userName);
			if(list.isEmpty()){
				this.outJsonP(response, callback, new JSONResult(true,"该用户名可以添加"));				
			}else {
				this.outJsonP(response, callback, new JSONResult(false,"该用户名已存在!"));				
			}
		} catch (Exception e) {			
			logger.info("验证公司实名！异常信息:"+e.getLocalizedMessage());
			this.outJsonP(response, callback, new JSONResult(false,"服务器异常！"));			
		}
	}
	
	/**
	 * 正在建设中的跳转地址
	 * 
	 * @author huyj
	 * @sicen 2013-11-27
	 * @description TODO actionPath eg:
	 */
	@RequestMapping(value = "/comming")
	public String comming(HttpServletRequest request,
			HttpServletResponse response) {
		return "error/coming";
	}
	/**
	 * 企业信息化
	 * 
	 * @author huyj
	 * @sicen 2013-11-29
	 * @description TODO actionPath eg:
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/information")
	public String information(HttpServletRequest request,
			HttpServletResponse response) {
		return "information/information_index";
	}
	
	/**
	 * 企业信息化-一体化企业信息化利器-查看详情
	 * @author xuwf
	 * @since 2013-12-12
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/information_detail")
	public String information_detail(HttpServletRequest request,
			HttpServletResponse response) {
		return "information/information_detail";
	}
	
	/**
	 * 企业信息化-园区物业管理系统-查看详情
	 * @author xuwf
	 * @since 2013-12-12
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/information_property")
	public String information_property(HttpServletRequest request,
			HttpServletResponse response) {
		return "information/information_property";
	}
	
	/**
	 * 企业信息化-专业市场管理系统-查看详情
	 * @author xuwf
	 * @since 2013-12-12
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/information_market")
	public String information_market(HttpServletRequest request,
			HttpServletResponse response) {
		return "information/information_market";
	}
	
	/**
	 * 企业信息化-移动APP信息化建设-查看详情
	 * @author xuwf
	 * @since 2013-12-12
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/information_app")
	public String information_app(HttpServletRequest request,
			HttpServletResponse response) {
		return "information/information_app";
	}
	
	/**
	 * 企业信息化-展会通-查看详情
	 * @author xuwf
	 * @since 2013-12-12
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/information_exhibition")
	public String information_exhibition(HttpServletRequest request,
			HttpServletResponse response) {
		return "information/information_exhibition";
	}
	
	/**
	 * 企业信息化-微信公共账号信息化建设-查看详情
	 * @author xuwf
	 * @since 2013-12-12
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/information_weixin")
	public String information_weixin(HttpServletRequest request,
			HttpServletResponse response) {
		return "information/information_weixin";
	}
	
}