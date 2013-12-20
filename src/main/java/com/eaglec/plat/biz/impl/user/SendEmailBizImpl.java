package com.eaglec.plat.biz.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.user.SendEmailBiz;
import com.eaglec.plat.dao.user.SendEmailDao;
import com.eaglec.plat.domain.base.SendEmail;

@Service
public class SendEmailBizImpl implements SendEmailBiz {

	@Autowired
	private SendEmailDao sendEmailDao;
	
	@Override
	public SendEmail saveOrUpdate(SendEmail sendEmail) {
		return sendEmailDao.saveOrUpdate(sendEmail);
	}
}
