package com.eaglec.plat.dao.impl.user;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.user.OrgRegisterApprovalDao;
import com.eaglec.plat.domain.base.OrgRegisterApproval;

@Service
public class OrgRegisterApprovalDaoImpl extends BaseDaoImpl<OrgRegisterApproval> implements OrgRegisterApprovalDao  {
	@Override
	public int add(OrgRegisterApproval o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.add(o);
	}
	
	@Override
	public OrgRegisterApproval save(OrgRegisterApproval o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o);
	}
	
	@Override
	public Map<String, Object> save(OrgRegisterApproval o, Object obj) {
		o.setModifyTime(System.currentTimeMillis());
		return super.save(o, obj);
	}
	
	@Override
	public OrgRegisterApproval saveOrUpdate(OrgRegisterApproval o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.saveOrUpdate(o);
	}
	
	@Override
	public OrgRegisterApproval update(OrgRegisterApproval o) {
		o.setModifyTime(System.currentTimeMillis());
		return super.update(o);
	}
}
