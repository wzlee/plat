package com.eaglec.plat.biz.business;

import com.eaglec.plat.domain.business.BiddingServiceDetail;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.JSONData;

/**
 * 招标流水biz
 * 
 * @author xuwf
 * @since 2013-10-08
 */
public interface BiddingServiceDetailBiz {

	/**
	 * 新增招标流水信息
	 * @author Xiadi
	 * @since 2013-10-08
	 *
	 * @param biddingService
	 */
	public abstract void add(BiddingServiceDetail biddingServiceDetail);

	/**
	 * 分页查询指定招标的流水
	 * @author Xiadi
	 * @since 2013-10-9 
	 *
	 * @param bid 招标Id
	 * @param page
	 * @param rows
	 * @return
	 */
	public abstract JSONRows<BiddingServiceDetail> findBiddingDetailByBid(Integer bid, int page, int rows);
	
	/**
	 * 根据找单id查询该招单的所有流水信息
	 * @author xuwf
	 * @since 2013-10-09
	 * 
	 * @param bidServiceId	找单id
	 * @param start
	 * @param limit
	 * @return
	 */
	public abstract JSONData<BiddingServiceDetail> getByBidId(Integer bidServiceId,int start,int limit);
	
}
