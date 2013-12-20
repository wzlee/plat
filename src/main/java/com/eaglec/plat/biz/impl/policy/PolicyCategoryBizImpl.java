package com.eaglec.plat.biz.impl.policy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.policy.PolicyCategoryBiz;
import com.eaglec.plat.dao.policy.PolicyCategoryDao;
import com.eaglec.plat.domain.policy.PolicyCategory;
import com.eaglec.plat.view.JSONResult;

@Service
public class PolicyCategoryBizImpl implements PolicyCategoryBiz {
	@Autowired
	private PolicyCategoryDao policyCategoryDao;

	
	@Override
	public List<PolicyCategory> findAll(Integer type) {
		String hql = "from PolicyCategory where 1=1 AND pid IS NULL";
		if (null != type) {
			hql += " and  type = " + type;
		}
		return policyCategoryDao.findList(hql);
	}

	@Override
	public PolicyCategory findById(Integer id) {
		return policyCategoryDao.get(id);
	}

	@Override
	public PolicyCategory add(PolicyCategory policyCategory) {
		return policyCategoryDao.save(policyCategory);
	}

	@Override
	public PolicyCategory update(PolicyCategory policyCategory) {
		return policyCategoryDao.update(policyCategory);
	}

	/**
	 * 删除政策指引类别
	 * @author liuliping
	 * @since 2013-10-19
	 * @param id 政策指引类别id
	 * @return jr JSONResult对象
	 * */
	@Override
	public JSONResult delete(Integer id) {
		/* 删除前,必须先确认数据库中没有属于该类别的政策指引数据;
		 * 否则,不执行删除操作 FIXME*/
		/*String hql = "FROM Policy WHERE policyCategory.id = " + id;
		List<Policy> policys = policyDao.findList(hql);
		if ((policys != null) && (policys.size() > 0)) {
			return new JSONResult(false, "未执行删除(存在该类别的政策指引)");
		}*/
		policyCategoryDao.delete(id);
		return new JSONResult(true, "删除成功");
	}
	@Override
	public List<PolicyCategory> findReportCategory() {
		String hql = "from PolicyCategory where 1=1 and type = 1 and pid is null";
		return policyCategoryDao.findList(hql);
	}

	@Override
	public List<PolicyCategory> findPolicyCategoryByPid(Integer pid) {
		String hql = "FROM PolicyCategory WHERE pid = " + pid;
		return policyCategoryDao.findList(hql);
	}


}
