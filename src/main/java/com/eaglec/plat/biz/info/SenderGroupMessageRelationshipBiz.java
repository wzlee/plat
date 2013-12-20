package com.eaglec.plat.biz.info;

import java.util.List;

import com.eaglec.plat.domain.auth.Manager;
import com.eaglec.plat.domain.info.SenderGroupMessageRelationship;
import com.eaglec.plat.view.JSONData;


public interface SenderGroupMessageRelationshipBiz {
	//添加
	public SenderGroupMessageRelationship save(SenderGroupMessageRelationship sendergroupmessagerelationship);
	//查询
	public List<SenderGroupMessageRelationship> find();
	//修改
	public SenderGroupMessageRelationship update(SenderGroupMessageRelationship sendergroupmessagerelationship);
	//删除
	public void delete(SenderGroupMessageRelationship sendergroupmessagerelationship);
	//查找消息根据发送人
	public JSONData<Object> find(Manager manger,Integer type,int start,int limit);
	//根据发送人，要删除的已发送消息更新成已删除
	public int update(Manager manager ,String ids);
	
}
