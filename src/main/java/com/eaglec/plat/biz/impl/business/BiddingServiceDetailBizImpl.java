package com.eaglec.plat.biz.impl.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.business.BiddingServiceDetailBiz;
import com.eaglec.plat.dao.business.BiddingServiceDetailDao;
import com.eaglec.plat.domain.business.BiddingServiceDetail;
import com.eaglec.plat.view.JSONRows;
import com.eaglec.plat.view.JSONData;

@Service
public class BiddingServiceDetailBizImpl implements BiddingServiceDetailBiz {
	
	@Autowired
	private BiddingServiceDetailDao biddingServiceDetailDao;
	
	@Override
	public void add(BiddingServiceDetail biddingServiceDetail) {
		biddingServiceDetailDao.save(biddingServiceDetail);
	}

	@Override
	public JSONRows<BiddingServiceDetail> findBiddingDetailByBid(Integer bid,
			int page, int rows) {
		String hql = "from BiddingServiceDetail where biddingService.id = " + bid;
		return biddingServiceDetailDao.outJSONRows(hql, page, rows);
	}

	@Override
	public JSONData<BiddingServiceDetail> getByBidId(Integer bidServiceId,
			int start, int limit) {
		String hql = "from BiddingServiceDetail d where d.biddingService.id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", bidServiceId);
		return biddingServiceDetailDao.outJSONData(hql, params, start, limit);
	}

}
