package com.eaglec.plat.biz.impl.mall;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.mall.AdvertisementBiz;
import com.eaglec.plat.dao.mall.AdvertisementDao;
import com.eaglec.plat.domain.mall.Advertisement;
import com.eaglec.plat.view.JSONData;

@Service
public class AdvertisementBizImpl implements AdvertisementBiz {
	@Autowired
	AdvertisementDao advertisementDao;

	@Override
	public JSONData<Advertisement> findAll(String position, String advNo,
			int start, int limit) {

		StringBuffer hql = new StringBuffer("from Advertisement where 1=1");
		if (null != position) {

			hql.append(" and position in (" + position + ")");
		}

		if (!StringUtils.isEmpty(advNo)) {
			hql.append(" and advertisementNo like '%" + advNo + "%'");
		}

		hql.append(" order by sort ASC");
		return advertisementDao.outJSONData(hql.toString(), start, limit);
	}

	@Override
	public Advertisement add(Advertisement advertisement) {
		return advertisementDao.saveOrUpdate(advertisement);
	}

	@Override
	public void delete(Integer id) {
		advertisementDao.delete(id);
	}

	@Override
	public Advertisement update(Advertisement advertisement) {
		return advertisementDao.update(advertisement);
	}

	@Override
	public List<Advertisement> findByParam(Integer channlId, Integer position,
			String No, int start, int limit) {
		StringBuffer hql = new StringBuffer("from Advertisement where1=1");
		if (null != position) {
			hql.append(" and position =" + position);
		}
		if (StringUtils.isEmpty(No)) {
			hql.append(" and advertisementNo like '%" + No + "%'");
		}
		if (null != channlId) {
			hql.append(" and channel.id =" + channlId);
		}
		hql.append(" order by sort ASC");
		return advertisementDao.findList(hql.toString(), start, limit);
	}

	@Override
	public Advertisement findById(Integer id) {
		return advertisementDao.get(id);
	}

	@Override
	public Long findLastAdvNo(Integer channelId) {
		String hql = "select count(*) from Advertisement where channel.id="
				+ channelId;
		return advertisementDao.count(hql);
	}

	@Override
	public List<Advertisement> getTopAdList(Integer mallId, Integer position,
			int start, int limit) {
		StringBuffer hql = new StringBuffer("from Advertisement where 1=1 ");
		hql.append(" and position=" + position);

		hql.append(" and mallCategory.id in (select id from  MallCategory where pid ="
				+ mallId + ") order by sort ASC");
		return advertisementDao.findList(hql.toString(), start, limit);
	}

	@Override
	public List<Advertisement> getRecomAdList(Integer mallId, Integer position,
			int start, int limit) {
		StringBuffer hql = new StringBuffer("from Advertisement where 1=1 ");
		hql.append(" and position=" + position);

		hql.append(" and mallCategory.id =" + mallId + " order by sort ASC");

		return advertisementDao.findList(hql.toString(), start, limit);
	}
}
