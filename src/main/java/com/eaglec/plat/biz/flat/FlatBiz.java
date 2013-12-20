package com.eaglec.plat.biz.flat;

import java.util.List;

import com.eaglec.plat.domain.base.Flat;
import com.eaglec.plat.view.JSONData;


public interface FlatBiz {
	
	public void addOrUpdate(Flat flat);
	
	public JSONData<Flat> queryByFlatName(String flatName);
	
	public Flat queryByFlatCode(String flatCode);
	
	public Flat queryByIpAndHost(String ip,String host);
	
	public Flat queryByClient_id(String client_id);
	
	public Flat queryByClient_idAndCliet_secret(String client_id,String client_secret);
	
	public List<Flat> queryExClient_id(String client_id);
	
	/**
	 * 查找所有平台的部分信息
	 * */
	public List<Object> findFlatView();

	public List<Flat> loadAllFlat();
	
	/**
	 * 查询所有窗口平台信息
	 * @author liuliping
	 * @since 2013-12-04
	 * @return JSONData格式数据
	 * */
	public JSONData<Flat> getAllFlats();
}
