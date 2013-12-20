package com.eaglec.plat.biz.business;

import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.business.BiddingService;
import com.eaglec.plat.domain.business.ResponseBidding;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.JSONData;


/**
 * 招标biz
 * 
 * @author Xiadi
 * @since 2013-9-29
 */
public interface BiddingServiceBiz {

	/**
	 * 新增或修改招标
	 * @author Xiadi
	 * @since 2013-9-29 
	 *
	 * @param biddingService
	 */
	public abstract BiddingService addOrModify(BiddingService biddingService);
	
	/**
	 * 根据Id查找招标
	 * @author Xiadi
	 * @since 2013-9-29 
	 *
	 * @param id
	 * @return
	 */
	public abstract BiddingService findBiddingServiceById(Integer id);
	
	
	public abstract JSONRows<BiddingService> findBiddingService(
			int uid, Integer categoryId, String name, String begintime, String endtime,
			String status, int page, int rows);
	
	public abstract BiddingService getBiddingServiceById(int id);

	/**
	 * 根据类别、服务名、时间、状态查找招标服务（带分页）
	 * @author pangyf
	 * @since 2013-9-29
	 * @param uid
	 * @param category
	 * @param name
	 * @param begintime
	 * @param endtime
	 * @param status
	 * @param page
	 * @param rows
	 * @return JSONRows
	 */
	public abstract JSONRows<BiddingService> findBiddingList(
			Integer uid,Integer categoryId, String name, String begintime, String endtime,
			String status, int page, int rows);

	/**
	 * 给我推荐服务(个人认证用户ucenter/index_5.jsp)
	 * @author xuwf
	 * @since 2013-11-02
	 * 
	 * @param uid	
	 * @param categoryId
	 * @param name
	 * @param begintime
	 * @param endtime
	 * @param status
	 * @param page
	 * @param rows
	 * @return
	 */
	public abstract JSONRows<BiddingService> findPersonalBiddingList(
			Integer uid,Integer categoryId, String name, String begintime, String endtime,
			String status, int page, int rows);
	
	/**
	 * 混合条件查询招标订单(支撑平台-标单管理)
	 * @author  xuwf
	 * @since 2013-10-8
	 * 
	 * @param name				服务名称
	 * @param status			招标单状态		
	 * @param startTime			创建时间
	 * @param endTime			结束时间
	 * @param start
	 * @param limit
	 * @return
	 */
	public JSONData<BiddingService> queryBidding(String bidNo,String name,String status,
			String startTime,String endTime,int start,int limit);

	/**
	 * 选择服务
	 * @author Xiadi
	 * @since 2013-10-11 
	 *
	 * @param bs 招标单
	 * @param rb 应标方
	 */
	public abstract void addSelectResponse(BiddingService bs, ResponseBidding rb);

	/**
	 * 发布或取消
	 * @author Xiadi
	 * @since 2013-10-11 
	 *
	 * @param bid
	 * @param status
	 */
	public abstract void updateIssueOrCancel(Integer bid, Integer status);

	/**
	 * 我的招标汇总(用户中心首页)
	 * @author Xiadi
	 * @since 2013-10-17 
	 *
	 * @param user
	 */
	public abstract int getCountOfBidding(User user);

}
