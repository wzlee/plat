package com.eaglec.plat.dao.impl.info;

import org.springframework.stereotype.Repository;

import com.eaglec.plat.dao.impl.BaseDaoImpl;
import com.eaglec.plat.dao.info.MessageDao;
import com.eaglec.plat.domain.info.Message;

@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {

}
