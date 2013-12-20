package com.eaglec.plat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.info.MessageBiz;
import com.eaglec.plat.biz.info.MessageClassBiz;
import com.eaglec.plat.biz.info.ReceiverMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderGroupMessageRelationshipBiz;
import com.eaglec.plat.biz.info.SenderMessageRelationshipBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.info.Message;
import com.eaglec.plat.domain.info.MessageClass;
import com.eaglec.plat.domain.info.ReceiverMessageRelationship;
import com.eaglec.plat.domain.info.SenderGroupMessageRelationship;
import com.eaglec.plat.domain.info.SenderMessageRelationship;
import com.eaglec.plat.utils.Dao;
import com.eaglec.plat.view.JSONResult;
/**
 * 站内消息
 * @author chens
 */
@Controller
@RequestMapping(value = "/info")
public class InfoController extends BaseController /*implements Runnable*/ {
	
	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);
	@Autowired
	private MessageBiz messageBiz;
	@Autowired
	private MessageClassBiz messageClassBiz;
	@Autowired
	private ReceiverMessageRelationshipBiz receiverMessageRelationshipBiz;
	@Autowired
	private SenderMessageRelationshipBiz senderMessageRelationshipBiz;
	@Autowired
	private SenderGroupMessageRelationshipBiz SenderGroupMessageRelationshipBiz;
	@Autowired
	private StaffBiz staffBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private Dao dao;
	
	
//	private List<User> list;
/*	read:'info/queryCategory',  
    create:'info/addOrUpdateCategory',  
    destroy:'info/deleteCategory',  
    update:'info/addOrUpdateCategory' */
	
	
	/**
	 * 加载消息类别分页
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping("queryCategorypage")
	public void queryCategorypage(HttpServletRequest request,HttpServletResponse response,int start,int limit){
		this.outJson(response, messageClassBiz.findAll(start, limit));
	}
	
	/**
	 * 加载消息类别
	 * @param request
	 * @param response
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping("queryCategory")
	public void queryCategory(HttpServletRequest request,HttpServletResponse response){
		this.outJson(response, messageClassBiz.find());
	}
	
	/**
	 * 更新或者增加消息类别
	 * @param request
	 * @param response
	 * @param messageClass
	 * @throws InterruptedException 
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping("addOrUpdateCategory")
	public void addOrUpdateCategory(HttpServletRequest request,HttpServletResponse response,MessageClass messageClass) throws InterruptedException{
		//修改
		if(messageClass.getId() != null && !"".equals(messageClass.getId())){
			MessageClass message = messageClassBiz.find(messageClass.getId());
			if(message.getMessageType().equals(messageClass.getMessageType())){
				message.setMessageType(messageClass.getMessageType());
				message.setRemark(messageClass.getRemark());
				messageClassBiz.update(message);
				this.outJson(response, new JSONResult(true, "修改成功"));
			}else{
				if(!messageClassBiz.exist(messageClass.getMessageType())){
					message.setMessageType(messageClass.getMessageType());
					message.setRemark(messageClass.getRemark());
					messageClassBiz.update(message);
					this.outJson(response, new JSONResult(true, "修改成功"));
				}else{
					this.outJson(response, new JSONResult(false, "类别名称已经存在，不得修改成同样的消息类别！"));
				}
			}
		//增加
		}else{
			if(messageClass.getMessageType()!=null && !messageClass.getMessageType().equals("")){
				if(!messageClassBiz.exist(messageClass.getMessageType())){
					messageClassBiz.save(messageClass);
					logger.info("添加消息类别成功！" );
					this.outJson(response, new JSONResult(true, "添加成功"));
				}else{
					this.outJson(response, new JSONResult(false, "类别名称已经存在，请更换类别名称！"));
				}
			}else{
				this.outJson(response, new JSONResult(false, "类别名称不能为空！"));
			}
		}
	}
	
	/**
	 * 删除消息类别，如果被消息引用，则删除不了
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping(value="/deleteCategory",method=RequestMethod.POST)
	public void deleteCategory(HttpServletRequest request,HttpServletResponse response,Integer id){
		try{
			messageClassBiz.delete(id);
			logger.info("删除消息类别成功！" );
			this.outJson(response, new JSONResult(true, "删除成功"));
		}catch(Exception e ){
			logger.info("删除消息类别失败！" );
			this.outJson(response, new JSONResult(false, "删除失败，请确保该消息类别没有被引用"));
		}
	}
	
	/**
	 * admin后台根据type加载消息，当type=0.表示加载未删除，为1的时候加载已删除
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping("infolist")
	public void infolist(HttpServletRequest request,HttpServletResponse response,Integer type,int start ,int limit){
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		this.outJson(response, senderMessageRelationshipBiz.find(manager,type,start,limit));
	}
	
	
	/**
	 * admin后台根据type加载消息，当type=0.表示加载未删除，为1的时候加载已删除
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping("infogrouplist")
	public void infogrouplist(HttpServletRequest request,HttpServletResponse response,Integer type,int start ,int limit){
		Manager manager = (Manager)request.getSession().getAttribute("manager");
		this.outJson(response, SenderGroupMessageRelationshipBiz.find(manager,type,start,limit));
	}
	
	/**
	 * 再已发送tab中，删除消息
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping("updategrouplist")
	public void updategrouplist(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="ids",required=true)String ids){
		if(ids!=null&&!"".equals(ids)){
			try{
				Manager manager = (Manager)request.getSession().getAttribute("manager");
				this.outJson(response,new JSONResult(true, String.valueOf(SenderGroupMessageRelationshipBiz.update(manager, ids))));
			}catch(Exception e){
				this.outJson(response,new JSONResult(false,"删除失败"));
			}
		}else{
			this.outJson(response,new JSONResult(false,"删除失败"));
		}
	}
	/**
	 * 再已发送tab中，删除消息
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping("updatelist")
	public void updatelist(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="ids",required=true)String ids){
		if(ids!=null&&!"".equals(ids)){
			try{
				Manager manager = (Manager)request.getSession().getAttribute("manager");
				this.outJson(response,new JSONResult(true, String.valueOf(senderMessageRelationshipBiz.update(manager, ids))));
			}catch(Exception e){
				this.outJson(response,new JSONResult(false,"删除失败"));
			}
		}else{
			this.outJson(response,new JSONResult(false,"删除失败"));
		}
	}
	
	/**
	 * 加载所有的会员，包括子账号
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.OR)
	@RequestMapping("allUser")
	public void allUser(HttpServletRequest request,HttpServletResponse response,String ids){
		List<Object> list = new ArrayList<Object>();
		list.addAll(userBiz.find());
		list.addAll(staffBiz.find());
		this.outJson(response, list);
	}
	
	/**
	 * 发送消息
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response,String username,Integer messageclassid,String content){
		if("".equals(username) || "".equals(content) || messageclassid == null){
			this.outJson(response,new JSONResult(false,"参数不正确！"));
		}else{
			try{
				Manager manager = (Manager)request.getSession().getAttribute("manager");
				if(userBiz.findUserByName(username)!=null && userBiz.findUserByName(username).size() != 0){
					User user = userBiz.findUserByName(username).get(0);
					
					MessageClass messageClass = messageClassBiz.find(messageclassid);
					
					Message message = new Message();
					message.setContent(content);
					message.setMessageClass(messageClass);
					
					ReceiverMessageRelationship r = new ReceiverMessageRelationship();
					r.setMessage(message);
					r.setSender(manager.getUsername());
					r.setReceiverUserId(user.getId());
					
					SenderMessageRelationship s = new SenderMessageRelationship();
					s.setMessage(message);
					s.setReceiver(user.getUserName());
					s.setSendManagerId(manager.getId());
					
					messageBiz.save(message);
					receiverMessageRelationshipBiz.save(r);
					senderMessageRelationshipBiz.save(s);
					this.outJson(response,new JSONResult(true,"发送成功"));
				}else if(staffBiz.findByUserName(username)!=null){
					Staff staff = staffBiz.findByUserName(username);
					
					MessageClass messageClass = messageClassBiz.find(messageclassid);
					
					Message message = new Message();
					message.setContent(content);
					message.setMessageClass(messageClass);
					
					ReceiverMessageRelationship r = new ReceiverMessageRelationship();
					r.setMessage(message);
					r.setSender(manager.getUsername());
					r.setReceiverStaffId(staff.getId());
					
					SenderMessageRelationship s = new SenderMessageRelationship();
					s.setMessage(message);
					s.setReceiver(staff.getUserName());
					s.setSendManagerId(manager.getId());
					
					messageBiz.save(message);
					receiverMessageRelationshipBiz.save(r);
					senderMessageRelationshipBiz.save(s);
					this.outJson(response,new JSONResult(true,"发送成功"));
				}else{
					this.outJson(response,new JSONResult(false,"找不到收信人！"));
				}
			}catch(Exception e){
				this.outJson(response,new JSONResult(false,"信息发送异常，请稍后再试！"));
			}
		}
	}
	/**
	 * 发送群组消息
	 * @param request
	 * @param response
	 * @param id
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value="/savegroup" ,method=RequestMethod.POST)
	public void savegroup(HttpServletRequest request,HttpServletResponse response,Integer usertype,Integer messageclassid,String content){
		Long l1 = System.currentTimeMillis();
		if("".equals(usertype) || "".equals(content) || messageclassid == null){
			this.outJson(response,new JSONResult(false,"参数不正确！"));
		}else{
			try{
				Manager manager = (Manager)request.getSession().getAttribute("manager");
				
				MessageClass messageClass = messageClassBiz.find(messageclassid);
				
				Message message = new Message();
				message.setContent(content);
				message.setMessageClass(messageClass);
				
				SenderGroupMessageRelationship s = new SenderGroupMessageRelationship();
				s.setMessage(message);
				s.setReceiver(usertype);
				s.setSendManagerId(manager.getId());
				
				messageBiz.save(message);
				SenderGroupMessageRelationshipBiz.save(s);
				
//				List<User> list = userBiz.find();
//				ReceiverMessageRelationship r = new ReceiverMessageRelationship();
//				r.setMessage(message);
				
				String queryusersql = " select id from user where userStatus=1 ";
				String querystaffsql = " select id from staff where staffStatus=1 ";
				List<Map<String, Object>> listu = new ArrayList<Map<String,Object>>(); 
				List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>(); 
				//全部
				if(usertype == 1){
					listu = dao.find(queryusersql);
					lists = dao.find(querystaffsql);
				}
				//企业
				if(usertype == 2){
					listu = dao.find(queryusersql+" and enterprise_id IN(SELECT id FROM  enterprise WHERE TYPE in(1,2))");
					lists = dao.find(querystaffsql+" AND parent_id IN(SELECT id FROM USER WHERE userStatus=1 AND enterprise_id IN(SELECT id FROM  enterprise WHERE TYPE IN(1,2)))");
				}
				//机构
				if(usertype == 3){
					listu = dao.find(queryusersql+" and enterprise_id IN(SELECT id FROM  enterprise WHERE TYPE=3)");
					lists = dao.find(querystaffsql+" AND parent_id IN(SELECT id FROM USER WHERE userStatus=1 AND enterprise_id IN(SELECT id FROM  enterprise WHERE TYPE =3))");
				}
				//个人
				if(usertype == 4){
					listu = dao.find(queryusersql+" and isPersonal=1");
				}
				
				//获取所有user的所有id
			    Object[][] sucobju = new Object[listu.size()][1];
			    Object[][] sucobjs = new Object[lists.size()][1];
			    
			    String insertusersql   = "INSERT INTO receivermessagerelationship  (deleteSign,readSign,receiverUserId,message_id,sender) VALUES(0,0,?,"+message.getId()+", '"+manager.getUsername()+"')";
			    String insertstaffsql = "INSERT INTO receivermessagerelationship  (deleteSign,readSign,receiverStaffId,message_id,sender) VALUES(0,0,?,"+message.getId()+", '"+manager.getUsername()+"')";
			    
			    for(int i = 0 ;i< listu.size();i++){
			    	Map<String, Object> map =listu.get(i);
			    	sucobju[i][0] = map.get("id");
			    }
			    for(int i = 0 ;i< lists.size();i++){
			    	Map<String, Object> map =lists.get(i);
			    	sucobjs[i][0] = map.get("id");
			    }
			    dao.batchUpdate(insertusersql, sucobju);
			    dao.batchUpdate(insertstaffsql, sucobjs);
			    
			    
			    
			    
//			    for(Map<String,Object> map : list){
//			    	List<String> parame = new ArrayList<String>();
//			    	parame.add((String)map.get("id"));
//			    	dao.update(insertsql, parame.toArray());
//			    }
			    
				
//				if(list.size()<=1000){
//					this.list = list.subList(0, list.size());
//					new Thread(this).start();
//				}else{
//					int n = list.size() % 1000 == 0 ? list.size() / 1000 : list.size() / 1000 + 1;
//					for(int i = 0;i<n ;i++){
//						this.list = list.subList(i*1000, (i*1000+1000)>list.size() ? list.size() : i*1000+1000);
//						new Thread(this).start();
//					}
//				}
				logger.info("方法耗时："+(System.currentTimeMillis()-l1));
				this.outJson(response,new JSONResult(true,"信息发送成功"));
			}catch(Exception e){
				this.outJson(response,new JSONResult(false,"信息发送异常，请稍后再试！"));
			}
		}
	}

/*	@Override
	public void run() {
		for(User u :list ){
			r.setReceiverUserId(u.getId());
			receiverMessageRelationshipBiz.save(r);
		}
	}*/
}
