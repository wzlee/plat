package com.eaglec.plat.biz.business;


import java.util.List;

import com.eaglec.plat.domain.business.ResponseBidding;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;



/**
 * 应标Biz
 * 
 * @author Xiadi
 * @since 2013-9-29
 */
public interface ResponseBiddingBiz {
	/**
	 * 根据id查找ResponseBidding对象
	 * @author PYF
	 * @since 2013-9-29
	 * @param id
	 * @return ResponseBidding对象
	 */
	public abstract ResponseBidding getResponseBiddingById(int id);
	
	/**
	 * 应标申请
	 * @author pangyf
	 * @since 2013-9-29
	 * @param rb
	 * @return ResponseBidding.id
	 */
	public abstract int apply(ResponseBidding rb);
	
	public abstract ResponseBidding addOrModify(ResponseBidding rb);
	
	/**
	 * 根据招标方和应标状态得到某张招标单的应标信息
	 * @author xuwf
	 * @since 2013-10-09
	 * 
	 * @param bidServiceId	招标单id
	 * @param status		应标状态
	 * @return
	 */
	public abstract JSONData<ResponseBidding> getByBidService(Integer bidServiceId,Integer status,int start,int limit);
	
	public abstract JSONRows<ResponseBidding> findResponseBiddingByBid(Integer bid, Integer status, int start,int limit);
	
	/**
	 * 根据状态和招标单查找所有应标该招标服务的应标信息集合
	 * @author xuwf
	 * @since 2013-11-07
	 * 
	 * @param bid		招标id
	 * @param status	状态：应标中
	 * @return
	 */
	public abstract List<ResponseBidding> findResponseBiddingByBid(Integer bid,Integer status);
	/**
	 * 多条件查询应标
	 * @author pangyf
	 * @since 2013-10-10
	 * @param uid
	 * @param bCategoryId
	 * @param bName
	 * @param begintime
	 * @param endtime
	 * @param bStatus
	 * @param page
	 * @param rows
	 * @return
	 */
	public abstract JSONRows<ResponseBidding> findResponseList(
			Integer uid,Integer bCategoryId, String bName, String begintime, String endtime,
			String bStatus, int page, int rows);
	
	/**
	 * 统计当前该招标的应标人数
	 * @author pangyf
	 * @since 2013-10-12
	 * @param bid
	 * @return
	 */
	public abstract long countResponseBiddingByBid(int bid);

}
