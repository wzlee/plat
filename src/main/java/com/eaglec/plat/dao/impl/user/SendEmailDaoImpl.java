package com.eaglec.plat.dao.impl.user;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.user.SendEmailDao;
import com.eaglec.plat.domain.base.SendEmail;

@Repository
public class SendEmailDaoImpl extends BaseDaoImpl<SendEmail> implements SendEmailDao {

}
