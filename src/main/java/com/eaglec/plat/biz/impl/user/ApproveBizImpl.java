package com.eaglec.plat.biz.impl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.user.ApproveBiz;
import com.eaglec.plat.dao.user.ApproveDao;
import com.eaglec.plat.dao.user.EnterpriseDao;
import com.eaglec.plat.dao.user.OrgRegisterApprovalDao;
import com.eaglec.plat.dao.user.UserDao;
import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.base.ApprovedDetail;
import com.eaglec.plat.domain.base.Enterprise;
import com.eaglec.plat.domain.base.OrgRegisterApproval;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.service.ServiceOrgRelation;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.MD5;
import com.eaglec.plat.utils.RandomUtils;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.JSONData;

@Service
public class ApproveBizImpl implements ApproveBiz {
	
	@Autowired
	private ApproveDao approveDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EnterpriseDao enterpriseDao;
	
	@Autowired
	private OrgRegisterApprovalDao orgRegisterApprovalDao;

	@Override
	public JSONData<ApprovedDetail> findAll(String username,
			String enterpriseName, String applyTimeBegin, String applyTimeEnd,
			Integer applyType, int start, int limit) {
		// TODO Auto-generated method stub
		String hql = "from ApprovedDetail where approveStatus=2 and applyType = :applyType";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyType", applyType);
		if(username != null && !"".equals(username)){
			hql += " and user.userName like :username";
			map.put("username", "%"+username+"%");
		}
		if(enterpriseName != null && !"".equals(enterpriseName)){
			if(applyType == Constant.APPROVED_APPLYTYPE_REALNAME){
				hql += " and name like :enterpriseName";
			}else{
				hql += " and enterprise.name like :enterpriseName";
			}
			map.put("enterpriseName", "%"+enterpriseName+"%");
		}
		if(applyTimeBegin != null && !"".equals(applyTimeBegin)){
			applyTimeBegin = applyTimeBegin.substring(0, applyTimeBegin.indexOf("T"));
			hql += " and applyTime >= :applyTimeBegin";
			map.put("applyTimeBegin", applyTimeBegin + " 00:00:00");
		}
		if(applyTimeEnd != null && !"".equals(applyTimeEnd)){
			applyTimeEnd = applyTimeEnd.substring(0, applyTimeEnd.indexOf("T"));
			hql += " and applyTime <= :applyTimeEnd";
			map.put("applyTimeEnd", applyTimeEnd + " 23:59:59");
		}
		return approveDao.outJSONData(hql, map, start, limit);
	}

	@Override
	public ApprovedDetail addApprove(ApprovedDetail approvedDetail) {
		// TODO Auto-generated method stub
		ApprovedDetail real = approveDao.get(approvedDetail.getId());
		real.setApproveStatus(approvedDetail.getApproveStatus());
		real.setApprovedContext(approvedDetail.getApprovedContext());
		// 审核人 时间
		real.setManager(approvedDetail.getManager());
		real.setApproveTime(StrUtils.formateDate("yyyy-MM-dd HH:mm:ss",
				new Date()));
		return approveDao.saveOrUpdate(real);
	}

	@Override
	public List<ServiceOrgRelation> findRelationListByEid(Integer eid) {
		// TODO Auto-generated method stub
		String hql = "from ServiceOrgRelation where enterpriseId = " + eid;
		List<Object> list = approveDao.findObjects(hql);
		List<ServiceOrgRelation> ret = new ArrayList<ServiceOrgRelation>();
		for (Object obj : list) {
			ret.add((ServiceOrgRelation) obj);
		}
		return ret;
	}

	@Override
	public List<ApprovedDetail> findApprListByUid(Integer uid) {
		// TODO Auto-generated method stub
		return approveDao.findList("from ApprovedDetail where user.id = " + uid + " order by applyTime");
	}

	@Override
	public boolean addApply(ApprovedDetail approvedDetail) {
		// TODO Auto-generated method stub
		// STEP 1 查询是否有该用户未处理的申请
		String hql = "select count(*) from ApprovedDetail where approveStatus = 2 " +
				"and user.id = " + approvedDetail.getUser().getId();
		Long num = (Long)approveDao.uniqueResult(hql);
		if(num > 0) return false;
		// STEP 2 添加申请
		approvedDetail.setSerialId(Long.toString(new Date().getTime()));
		approvedDetail.setApplyTime(StrUtils.formateDate("yyyy-MM-dd HH:mm:ss", new Date()));
		approveDao.saveOrUpdate(approvedDetail);
		return true;
	}

	@Override
	public OrgRegisterApproval addOrgRegister(OrgRegisterApproval registerApproval) {
		return orgRegisterApprovalDao.saveOrUpdate(registerApproval);
	}
	
	@Override
	public OrgRegisterApproval getOrgRegister(Integer rid) {
		return orgRegisterApprovalDao.get(rid);
	}

	@Override
	public JSONData<OrgRegisterApproval> findOrgRegisterlist(String username,
			String enterpriseName, String applyTimeBegin, String applyTimeEnd,
			Integer start, Integer limit) {
		String hql = "from OrgRegisterApproval where approveStatus=2 ";
		Map<String, Object> map = new HashMap<String, Object>();
		if(username != null && !"".equals(username)){
			hql += " and userName like :username";
			map.put("username", "%"+username+"%");
		}
		if(enterpriseName != null && !"".equals(enterpriseName)){
			hql += " and orgName like :enterpriseName";
			map.put("enterpriseName", "%"+enterpriseName+"%");
		}
		if(applyTimeBegin != null && !"".equals(applyTimeBegin)){
			applyTimeBegin = applyTimeBegin.substring(0, applyTimeBegin.indexOf("T"));
			hql += " and regTime >= :applyTimeBegin";
			map.put("applyTimeBegin", applyTimeBegin + " 00:00:00");
		}
		if(applyTimeEnd != null && !"".equals(applyTimeEnd)){
			applyTimeEnd = applyTimeEnd.substring(0, applyTimeEnd.indexOf("T"));
			hql += " and regTime <= :applyTimeEnd";
			map.put("applyTimeEnd", applyTimeEnd + " 23:59:59");
		}
		return orgRegisterApprovalDao.outJSONData(hql, map, start, limit);
	}

	@Override
	public User addOrgRegisterApprove(OrgRegisterApproval orgRegisterApproval, Integer status, Manager manager) {
		orgRegisterApproval.setManager(manager);
		orgRegisterApproval.setApproveStatus(status);
		orgRegisterApproval.setApproveTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		if(orgRegisterApproval.getApproveStatus() == Constant.SERVICE_AUDIT_PASS){
			User user = new User();
			user.setUserName(orgRegisterApproval.getUserName());
			user.setPassword(MD5.toMD5(orgRegisterApproval.getPassword()));
			user.setEmail(orgRegisterApproval.getEmail());
			user.setUserStatus(Constant.EFFECTIVE);
			user.setSourceId(Constant.USER_FROM_REGISTER);	//来源:平台注册
			
			String s = RandomUtils.generateRandomNumber();
			String check = s+"1|"+System.currentTimeMillis();
			user.setCheckcode(check);	//bug#248 缺少checkcode属性值验证邮箱
			
			Enterprise enterprise = new Enterprise();
			enterprise.setName(orgRegisterApproval.getOrgName());
			enterprise.setIcRegNumber(orgRegisterApproval.getIcRegNumber());
			enterprise.setIndustryType(orgRegisterApproval.getIndustryType());
//			enterprise.setBusinessLetter(orgRegisterApproval.getBusinessLetter());
//			enterprise.setLicenceDuplicate(orgRegisterApproval.getLicenceDuplicate());
			enterprise.setType(Constant.ENTERPRISE_TYPE_ORG);
			enterprise.setIsApproved(true);
			enterprise.setTel(orgRegisterApproval.getOrgPhone());	//机构电话
			enterprise.setEmail(orgRegisterApproval.getLegalRepresentativeEmail());	//Email
			enterprise.setAddress(orgRegisterApproval.getOrgAddress());		//机构地址
			enterprise.setOpenedTime(orgRegisterApproval.getOpenedTime());	//成立时间
			enterprise.setProfile(orgRegisterApproval.getProfile());		//企业简介
			enterprise.setStaffNumber(orgRegisterApproval.getStaffNumber());	//员工人数
			enterprise.setSalesRevenue(orgRegisterApproval.getTurnover());		//年营业额
			enterprise.setOrgIndustry(orgRegisterApproval.getOrgIndustry());	//机构行业
			enterprise.setOrgProperty(orgRegisterApproval.getOrgProperty());	//机构性质
			enterprise.setBusiness(orgRegisterApproval.getBusiness());		//经营范围
			//优势服务领域
			enterprise.setAdvantageServiceAreas(orgRegisterApproval.getAdvantageServiceAreas());
			enterprise.setTotalAssets(orgRegisterApproval.getTotalAssets());	//总资产
			enterprise.setProfit(orgRegisterApproval.getProfit());				//利润或净收入
			enterprise.setHonorSociety(orgRegisterApproval.getHonorSociety());	//社会荣誉
			//专业资质
			enterprise.setProfessionalQualifications(orgRegisterApproval.getProfessionalQualifications());
			enterprise.setLegalPerson(orgRegisterApproval.getLegalRepresentative());	//法定代表人
			//手机
			enterprise.setLegalRepresentativeMobile(orgRegisterApproval.getLegalRepresentativeMobile());
			enterprise.setGeneralName(orgRegisterApproval.getGeneralName());	//总经理姓名
			enterprise.setGeneralManagerMobile(orgRegisterApproval.getGeneralManagerMobile()); //总经理手机
			enterprise.setGeneralManagerEmail(orgRegisterApproval.getGeneralManagerEmail());	//总经理email
			enterprise.setLinkman(orgRegisterApproval.getContactName());		//联系人姓名
			enterprise.setContactNameMobile(orgRegisterApproval.getContactNameMobile());	//联系人手机
			enterprise.setContactNameEmail(orgRegisterApproval.getContactNameEmail());		//联系人email
			enterprise.setOrgFax(orgRegisterApproval.getOrgFax());		//机构传真
			enterprise.setOrgWebsite(orgRegisterApproval.getOrgWebsite());	//公司网址
			
			enterpriseDao.save(enterprise);
			user.setEnterprise(enterprise);
			userDao.save(user);
			
			//保存社区一些表
			String tsusersql = "INSERT INTO ts_user(uid,login,uname,email,PASSWORD,openid) VALUES("+
								user.getId()+",'"+user.getEmail()+"','"+user.getUserName()+"','"+
								user.getEmail()+"','"+user.getPassword()+"','"+user.getUid()+"')";
			
			String tsgroupsql = "INSERT INTO ts_user_group_link (uid,user_group_id) VALUES("+user.getId()+", 10)";
			userDao.executeSql(tsusersql);
			userDao.executeSql(tsgroupsql);
			return user;
		}
		orgRegisterApprovalDao.saveOrUpdate(orgRegisterApproval);
		return null;
	}

	@Override
	public List<OrgRegisterApproval> findOrgRegisterlistByUsername(
			String username) {
		String hql = "from OrgRegisterApproval where approveStatus=2 ";
		Map<String, Object> map = new HashMap<String, Object>();
		if(username != null && !"".equals(username)){
			hql += " and userName=:username";
			map.put("username", username);
		}
		return orgRegisterApprovalDao.findList(hql, map);
	}

	@Override
	public ApprovedDetail findById(Integer id) {
		return approveDao.get(id);
	}
}
