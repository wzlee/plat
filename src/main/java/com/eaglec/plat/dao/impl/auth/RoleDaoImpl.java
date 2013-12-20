package com.eaglec.plat.dao.impl.auth;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.auth.RoleDao;
import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.domain.auth.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

}
