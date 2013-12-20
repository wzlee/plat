package com.eaglec.plat.biz.info;

import java.util.List;

import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.info.ReceiverMessageRelationship;
import com.eaglec.plat.view.JSONRows;



public interface ReceiverMessageRelationshipBiz {
	//添加
	public ReceiverMessageRelationship save(ReceiverMessageRelationship receivermessagerelationship);
	//查询
	public List<ReceiverMessageRelationship> find();
	//修改
	public ReceiverMessageRelationship update(ReceiverMessageRelationship receivermessagerelationship);
	//删除
	public void delete(ReceiverMessageRelationship receivermessagerelationship);
	//查找消息根据接收人
//	public JSONData<Object> find(Manager manger,Integer type,int start,int limit);
	public JSONRows<Object> find(User user,Integer type,int start,int limit);
	public JSONRows<Object> find(Staff staff,Integer type,int start,int limit);
	public JSONRows<Object> findtype(User user,Integer type,Integer messageType,int start,int limit);
	public JSONRows<Object> findtype(Staff staff,Integer type,Integer messageType,int start,int limit);
	//根据接收人，要删除的已发送消息更新成已删除
//	public int update(Manager manager ,String ids);
	public int update(User user ,String ids);
	public int update(Staff staff ,String ids);
	//设置为已读
	public int updateyetRead(User user,Integer id);
	public int updateyetRead(Staff staff,Integer id);
	
	/**
	 * 根据消息类型查找各种类型的消息数据集合
	 * @author xuwf
	 * @since 2013-11-13
	 * 
	 * @param userId
	 * @loginType		用户类型(1、主账号 2、子账号)
	 * @return
	 */
	public abstract int[] findMyMessage(Integer userId,Integer loginType);
	
	/**
	 * 用户未读消息数
	 * @author liuliping
	 * @since 2013-11-22
	 * 
	 * @param user 个人用户或者企业用户
	 * */
	int countUnreadMessage(User user);
	
	/**
	 * 子账号未读消息数
	 * @author liuliping
	 * @since 2013-11-22
	 * 
	 * @param staff 子账号
	 * */
	int countUnreadMessage(Staff staff);
}
