package com.eaglec.plat.biz.impl.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.user.UserApproveBiz;
import com.eaglec.plat.dao.user.UserApproveDao;
import com.eaglec.plat.domain.base.UserApprovedDetail;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.JSONData;

@Service
public class UserApproveBizImpl implements UserApproveBiz {
	
	@Autowired
	private UserApproveDao userapproveDao;

	@Override
	public JSONData<UserApprovedDetail> findAll(String username,String realName,
			String applyTimeBegin, String applyTimeEnd, Integer applyType,
			int start, int limit) {
		String hql = "from UserApprovedDetail where approveStatus=2 and applyType = :applyType";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applyType", applyType);
		if(!StringUtils.isEmpty(username)){
			hql += " and user.userName like :username";
			map.put("username", "%"+username+"%");
		}
		if(!StringUtils.isEmpty(realName)){
			hql += " and name like :username";
			map.put("username", "%"+username+"%");
		}
		if(!StringUtils.isEmpty(applyTimeBegin)){
			applyTimeBegin = applyTimeBegin.substring(0, applyTimeBegin.indexOf("T"));
			hql += " and applyTime >= :applyTimeBegin";
			map.put("applyTimeBegin", applyTimeBegin + " 00:00:00");
		}
		if(!StringUtils.isEmpty(applyTimeEnd)){
			applyTimeEnd = applyTimeEnd.substring(0, applyTimeEnd.indexOf("T"));
			hql += " and applyTime <= :applyTimeEnd";
			map.put("applyTimeEnd", applyTimeEnd + " 23:59:59");
		}
		return userapproveDao.outJSONData(hql, map, start, limit);
	}

	@Override
	public UserApprovedDetail addApprove(UserApprovedDetail userapprovedDetail) {
		UserApprovedDetail real = userapproveDao.get(userapprovedDetail.getId());
		real.setApproveStatus(userapprovedDetail.getApproveStatus());
		real.setApprovedContext(userapprovedDetail.getApprovedContext());
		// 审核人 时间
		real.setManager(userapprovedDetail.getManager());
		real.setApproveTime(StrUtils.formateDate("yyyy-MM-dd HH:mm:ss",
				new Date()));
		return userapproveDao.saveOrUpdate(real);
	}

	@Override
	public List<UserApprovedDetail> findApprListByUid(Integer uid) {
		return userapproveDao.findList("from UserApprovedDetail where user.id = " + uid + " order by applyTime");
	}
	
	@Override
	public boolean addApply(UserApprovedDetail userapprovedDetail) {
		// STEP 1 查询是否有该用户未处理的申请
		String hql = "select count(*) from UserApprovedDetail where approveStatus = 2 "
				+ "and user.id = " + userapprovedDetail.getUser().getId();
		Long num = (Long) userapproveDao.uniqueResult(hql);
		if (num > 0)
			return false;
		// STEP 2 添加申请
		userapprovedDetail.setSerialId(Long.toString(new Date().getTime()));
		userapprovedDetail.setApplyTime(StrUtils.formateDate("yyyy-MM-dd HH:mm:ss",
				new Date()));
		userapproveDao.saveOrUpdate(userapprovedDetail);
		return true;
	}
	
}
