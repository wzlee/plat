package com.eaglec.plat.biz.impl.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.business.ResponseBiddingBiz;
import com.eaglec.plat.dao.business.ResponseBiddingDao;
import com.eaglec.plat.dao.user.StaffDao;
import com.eaglec.plat.domain.business.ResponseBidding;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;

@Service
public class ResponseBiddingBizImpl implements ResponseBiddingBiz{
	
	@Autowired
	private ResponseBiddingDao responseBiddingDao;
	@Autowired
	private StaffDao staffDao;
	
	@Override
	public ResponseBidding getResponseBiddingById(int id) {
		return responseBiddingDao.get(id);		
	}
	
	/**
	 * 应标申请
	 * @author pangyf
	 * @since 2013-9-29
	 * @param rb
	 * @return ResponseBidding.id
	 */
	@Override
	public int apply(ResponseBidding rb) {
		return responseBiddingDao.add(rb);
	}

	@Override
	public ResponseBidding addOrModify(ResponseBidding rb) {		
		return responseBiddingDao.saveOrUpdate(rb);
	}

	@Override
	public JSONData<ResponseBidding> getByBidService(Integer bidServiceId,
			Integer status,int start,int limit) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from ResponseBidding r where r.biddingService.id = :id and r.status = :status";
		params.put("id", bidServiceId);
		params.put("status", status);
		return responseBiddingDao.outJSONData(hql, params, start, limit);
	}

	@Override
	public JSONRows<ResponseBidding> findResponseBiddingByBid(Integer bid,
			Integer status, int start, int limit) {
		String hql = "from ResponseBidding where biddingService.id = :bid and status = :status";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bid", bid);
		params.put("status", status);
		return responseBiddingDao.outJSONRows(hql, params, start, limit);
	}
	
	@Override
	public List<ResponseBidding> findResponseBiddingByBid(Integer bid,Integer status){
		String hql = "from ResponseBidding where biddingService.id = :bid and status = :status";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bid", bid);
		params.put("status", status);
		return responseBiddingDao.findList(hql, params);
	};

	@Override
	public JSONRows<ResponseBidding> findResponseList(Integer uid,Integer bCategoryId, String bName, String begintime, String endtime,
			String bStatus, int page, int rows) {
		String sid_list = staffDao.getStaffIdsByParentId(uid);
		String hql = "from ResponseBidding where user.id = " + uid;		
		if(!sid_list.isEmpty()){
			hql = "from ResponseBidding where (user.id = " + uid + " or staff.id in (" + sid_list +"))";
		}
		if(bName != null){
			hql += " and biddingService.name like '%" + bName + "%'";
		}
		if(bCategoryId != null){
			hql += " and biddingService.category.id =" + bCategoryId;
		}		
		if(begintime != null && !begintime.isEmpty()){			
			hql += " and responseTime >='" + begintime + " 00:00:00'";
		}
		if(endtime != null && !endtime.isEmpty()){			
			hql += " and responseTime <='" + endtime + " 23:59:59'";
		}
		//招标状态 4、招标中 6、交易进行中 7、等待买家关闭 8、等待卖家关闭 9、申诉处理中 10、交易结束 11、招标取消
		//应标状态5、应标中  6、应标失败  7、交易进行中
		if(bStatus != null && !bStatus.isEmpty()){
			int _bStatus = Integer.parseInt(bStatus);
			
			//如果状态为（应标中）则招标中的状态为（招标中）
			if(_bStatus==Constant.RESPONSE_DOING){				
				hql += " and biddingService.status = " + Constant.BIDDING_DOING;
				
			//如果状态为（应标失败）则查询招标中的交易进行中状态并且userid != 该用户ID
			}else if(_bStatus==Constant.RESPONSE_FAILE){
				hql += " and status =" + _bStatus ;
			
			//如果状态为（交易进行中）则查询招标中的(交易结束 、招标取消)状态	
			}else if(_bStatus==Constant.RESPONSE_TRADE){
				bStatus = Constant.BIDDING_TRADE_END + "," + Constant.BIDDING_CANCEL;
				hql += " and status = " + _bStatus + " and biddingService.status not in (" + bStatus +")";
			
			//如果状态为（交易结束）则查询招标中的状态
			}else if(_bStatus==Constant.BIDDING_TRADE_END){
					
				hql += " and status = " +Constant.RESPONSE_TRADE +" and biddingService.status = " + bStatus;
			//如果状态为（招标取消）则查询招标中的状态	
			}else if(_bStatus==Constant.BIDDING_CANCEL){
				
				hql += " and status != " +Constant.RESPONSE_FAILE + " and biddingService.status = " + bStatus;
			}				
		}
		hql += " order by responseTime desc";
		return responseBiddingDao.outJSONRows(hql, page, rows);
	}

	@Override
	public long countResponseBiddingByBid(int bid) {
		String hql = "select count(*) from ResponseBidding where biddingService.id = " + bid;		
		return responseBiddingDao.count(hql);
	}
}
