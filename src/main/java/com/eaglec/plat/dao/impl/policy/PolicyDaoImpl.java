package com.eaglec.plat.dao.impl.policy;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.policy.PolicyDao;
import com.eaglec.plat.domain.policy.Policy;

@Repository
public class PolicyDaoImpl extends BaseDaoImpl<Policy> implements PolicyDao {

}
