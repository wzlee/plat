package com.eaglec.plat.biz.impl.business;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.eaglec.plat.biz.business.BiddingServiceBiz;
import com.eaglec.plat.dao.business.BiddingServiceDao;
import com.eaglec.plat.dao.business.ResponseBiddingDao;
import com.eaglec.plat.dao.user.StaffDao;
import com.eaglec.plat.dao.user.UserDao;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.base.User;
import com.eaglec.plat.domain.business.BiddingService;
import com.eaglec.plat.domain.business.ResponseBidding;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.view.JSONData;
import com.eaglec.plat.view.JSONRows;

@Service
public class BiddingServiceBizImpl implements BiddingServiceBiz{
	
	@Autowired
	private BiddingServiceDao biddingServiceDao;
	@Autowired
	private ResponseBiddingDao responseBiddingDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private StaffDao staffDao;
	
	
	
	public BiddingService addOrModify(BiddingService biddingService) {
		// TODO Auto-generated method stub
		return biddingServiceDao.saveOrUpdate(biddingService);
	}

	@Override
	public BiddingService findBiddingServiceById(Integer id) {
		// TODO Auto-generated method stub
		return biddingServiceDao.get(id);
	}

	@Override
	public JSONRows<BiddingService> findBiddingService(int uid, Integer categoryId, 
			String name, String begintime, String endtime, String status, int page, int rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer(); 
		String sids = staffDao.getStaffIdsByParentId(uid);
		if ("".equals(sids)) {
			sb.append("from BiddingService where user.id = :uid ");
		} else {
			sb.append("from BiddingService where (user.id = :uid or staff.id in (" + sids + ")) ");
		}
		params.put("uid", uid);
		if(categoryId != null){
			sb.append("and category.id = :categoryId ");
			params.put("categoryId", categoryId);
		}		
		if(name != null && !name.isEmpty()){
			sb.append("and name like :name ");
			params.put("name", "%" + name + "%");
		}
		if(begintime != null && !begintime.isEmpty()){
			sb.append("and createTime >= :begintime ");
			params.put("begintime", begintime);
		}
		if(endtime != null && !endtime.isEmpty()){
			sb.append("and createTime <= :endtime ");
			params.put("endtime", endtime + " 23:59:59");
		}
		if(status != null && !status.isEmpty()){
			sb.append("and status in (" + status + ") ");
		}
		sb.append("order by createTime desc");
		return biddingServiceDao.outJSONRows(sb.toString(), params, page, rows);
	}

	@Override
	public BiddingService getBiddingServiceById(int id) {		
		return biddingServiceDao.get(id);
	}

	@Override
	public JSONRows<BiddingService> findBiddingList(Integer uid,
			Integer categoryId, String name, String begintime, String endtime,
			String status, int page, int rows) {
		String hql="",bid_list = "",cid_list = "",sid_list=staffDao.getStaffIdsByParentId(uid);
		hql = "from ResponseBidding where user.id=" + uid;
		if(!sid_list.isEmpty()){
			 hql += " or staff.id in(" + sid_list +")";
		}
		List<ResponseBidding> list = responseBiddingDao.executeHql(hql);
		//把该用户已经应过标的招标ID放到id_list			
		for(int i=0;i<list.size();i++){
			int id = list.get(i).getBiddingService().getId();
			if((i+1)==list.size()){
				bid_list = bid_list + id;
			}else {
				bid_list = bid_list + id + ",";
			}			
		}
		//把该用户的服务领域的类别ID放到cid_list
		List<Category> _list = userDao.get(uid).getEnterprise().getMyServices();			
		for(int i=0;i<_list.size();i++){
			int id = _list.get(i).getId();
			if((i+1)==_list.size()){
				cid_list = cid_list + id;
			}else {
				cid_list = cid_list + id + ",";
			}			
		}
		hql = "from BiddingService where user.id !="+ uid;
		if(!sid_list.isEmpty()){
			hql = "from BiddingService where (user.id != "+ uid + " or staff.id not in (" + sid_list +"))";
		}
		if(!cid_list.isEmpty()){
			hql += " and category.id in (" + cid_list + ")";
		}else {
			hql += " and 1 != 1";
		}
		if(!bid_list.isEmpty()){
			hql += " and id not in (" + bid_list + ")";
		}
		if(name != null){
			hql += " and name like '%" + name + "%'";
		}
		if(categoryId != null){
			hql += " and category.id =" + categoryId;
		}
		if(begintime != null && !begintime.isEmpty()){
			hql += " and createTime >=" + begintime + "and createTime <=" + begintime;
		}
		if(status != null && !status.isEmpty()){
			hql += " and status in (" + status + ")";
		}
		hql += " order by createTime desc";		
		return biddingServiceDao.outJSONRows(hql, page, rows);
	}
	
	@Override
	public JSONRows<BiddingService> findPersonalBiddingList(Integer uid,
			Integer categoryId, String name, String begintime, String endtime,
			String status, int page, int rows) {
		String hql="",bid_list = "",cid_list = "",sid_list=staffDao.getStaffIdsByParentId(uid);
		hql = "from ResponseBidding where user.id=" + uid;
		if(!sid_list.isEmpty()){
			 hql += " or staff.id in(" + sid_list +")";
		}
		List<ResponseBidding> list = responseBiddingDao.executeHql(hql);
		//把该用户已经应过标的招标ID放到id_list			
		for(int i=0;i<list.size();i++){
			int id = list.get(i).getBiddingService().getId();
			if((i+1)==list.size()){
				bid_list = bid_list + id;
			}else {
				bid_list = bid_list + id + ",";
			}			
		}
		//把该用户的服务领域的类别ID放到cid_list
		List<Category> _list = userDao.get(uid).getUserServices();			
		for(int i=0;i<_list.size();i++){
			int id = _list.get(i).getId();
			if((i+1)==_list.size()){
				cid_list = cid_list + id;
			}else {
				cid_list = cid_list + id + ",";
			}			
		}
		hql = "from BiddingService where user.id !="+ uid;
		if(!sid_list.isEmpty()){
			hql = "from BiddingService where (user.id != "+ uid + " or staff.id not in (" + sid_list +"))";
		}
		if(!cid_list.isEmpty()){
			hql += " and category.id in (" + cid_list + ")";
		}else {
			hql += " and 1 != 1";
		}
		if(!bid_list.isEmpty()){
			hql += " and id not in (" + bid_list + ")";
		}
		if(name != null){
			hql += " and name like '%" + name + "%'";
		}
		if(categoryId != null){
			hql += " and category.id =" + categoryId;
		}
		if(begintime != null && !begintime.isEmpty()){
			hql += " and createTime >=" + begintime + "and createTime <=" + begintime;
		}
		if(status != null && !status.isEmpty()){
			hql += " and status in (" + status + ")";
		}
		hql += " order by createTime desc";		
		return biddingServiceDao.outJSONRows(hql, page, rows);
	}

	@Override
	public JSONData<BiddingService> queryBidding(String bidNo,String name, String status,
			String startTime, String endTime, int start, int limit) {
		String hql = "from BiddingService b where 1=1";
		Map<String, Object> params = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(bidNo)){
			hql += " and b.bidNo like :bidNo";
			params.put("bidNo", "%"+bidNo+"%");
		}
		if(!StringUtils.isEmpty(name)){
			hql += " and b.name like :name";
			params.put("name", "%"+name+"%");
		}
		if(!StringUtils.isEmpty(status)){
			hql += " and b.status in (" + status + ")";
		}
		if(!StringUtils.isEmpty(startTime)){
			startTime = startTime.substring(0, startTime.indexOf("T"));
			hql += " and b.createTime >= :startTime";
			params.put("startTime", startTime+ " 00:00:00");
		}
		if(!StringUtils.isEmpty(endTime)){
			endTime = endTime.substring(0, endTime.indexOf("T"));
			hql += " and b.createTime <= :endTime";
			params.put("endTime", endTime+ " 23:59:59");
		}
		hql += " order by b.createTime desc";
		return biddingServiceDao.outJSONData(hql, params, start, limit);
	}

	@Override
	public void addSelectResponse(BiddingService bs, ResponseBidding rb) {
		// 其他未选中的应标为失败
		String hql = "update ResponseBidding set status = :targetStatus where status = :currentStatus " +
				"and biddingService.id = :bid and id != :exceptRid ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("targetStatus", Constant.RESPONSE_FAILE);
		params.put("currentStatus", Constant.RESPONSE_DOING);
		params.put("bid", bs.getId());
		params.put("exceptRid", rb.getId());
		responseBiddingDao.executeHql(hql, params);
		// 状态更改
		rb.setStatus(Constant.RESPONSE_TRADE);
		if(rb != null) {
			bs.setRid(rb.getId());
			bs.setRname(rb.getUser().getEnterprise().getName());
		}
		bs.setStatus(Constant.BIDDING_TRADE_ING);
		this.addOrModify(bs);
	}

	@Override
	public void updateIssueOrCancel(Integer bid, Integer status) {
		BiddingService bs = this.getBiddingServiceById(bid);
		bs.setStatus(status);
		this.addOrModify(bs);
	}

	@Override
	public int getCountOfBidding(User user) {
		long l = biddingServiceDao.count("select count(id) from BiddingService " +
				"where status=" + Constant.BIDDING_DOING + " and user.id = " + user.getId());
		return (int)l;
	}
}
