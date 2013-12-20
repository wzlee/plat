package com.eaglec.plat.biz.impl.flat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eaglec.plat.biz.flat.FlatBiz;
import com.eaglec.plat.dao.flat.FlatDao;
import com.eaglec.plat.domain.base.Flat;
import com.eaglec.plat.view.JSONData;

@Service
public class FlatBizImpl implements FlatBiz {

	@Autowired
	private FlatDao flatDao;
	
	/**
	 * 添加或者修改平台信息
	 * @author liuliping
	 * @since 2013-10-12
	 * @param flat 平台信息对象
	 * @return JSONResult 返回json格式的结果信息
	 * */
	public void addOrUpdate(Flat flat) {
		flatDao.saveOrUpdate(flat);
	}

	/**
	 * 通过平台名称查询平台信息
	 * @author liuliping
	 * @since 2013-10-12
	 * @param flatName 平台名称
	 * @return JSONData 返回json格式数据
	 * */
	public JSONData<Flat> queryByFlatName(String flatName) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM Flat WHERE 1=1 ");
		if (!StringUtils.isEmpty(flatName)) {
			sb.append(" AND flatName = '").append(flatName).append("'");
		}
		return flatDao.outJSONData(sb.toString(), 0, 16);    //平台数量为1+15
	}

	/**
	 * 根据平台编码查找平台
	 * @author liuliping
	 * @since 2013-10-17
	 * @param flatCode 平台编码
	 * @return result 平台对象
	 * */
	@Override
	public Flat queryByFlatCode(String flatCode) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM Flat WHERE flatCode = '").append(flatCode).append("'");
		List<Flat> list = flatDao.findList(sb.toString());
		return (list.size() > 0) ? list.get(0) : null;
	}

	@Override
	public Flat queryByIpAndHost(String ip,String host) {
		StringBuilder sb = new StringBuilder();
		sb.append("FROM Flat WHERE ip = '").append(ip).append("' and website ='").append(host).append("'");
		List<Flat> list = flatDao.findList(sb.toString());
		return (list.size() > 0) ? list.get(0) : null;
	}
	
	public List<Object> findFlatView() {
		String hql = "SELECT NEW com.eaglec.plat.view.FlatView(f.id, f.flatName, f.website) FROM Flat f";
		return flatDao.findObjects(hql);
	}

	@Override
	public JSONData<Flat> getAllFlats() {
		// TODO Auto-generated method stub
		String hql = "FROM Flat WHERE flatCode != 'snpt'";
		JSONData<Flat> jsondata = new JSONData<Flat>();
		List<Flat> results = flatDao.findList(hql);
		jsondata.setData(results);
		return jsondata;
	}

	@Override
	public List<Flat> loadAllFlat() {
		// TODO Auto-generated method stub
		return flatDao.findList("FROM Flat WHERE 1=1");
	}

	@Override
	public Flat queryByClient_id(String client_id) {
		List<Flat> flats = flatDao.findList("FROM Flat WHERE 1=1 and client_id = '" + client_id + "'");
		return flats.size()==0?null:flats.get(0);
	}

	@Override
	public List<Flat> queryExClient_id(String client_id) {
		return flatDao.findList("FROM Flat WHERE 1=1 and client_id <> '" + client_id + "' and sync_switch = 1");
	}

	@Override
	public Flat queryByClient_idAndCliet_secret(String client_id,
			String client_secret) {
		List<Flat> flats = flatDao.findList("FROM Flat WHERE 1=1 and client_id = '" + client_id + "' and client_secret = '" + client_secret + "'");
		return flats.size()==0?null:flats.get(0);
	}
}
