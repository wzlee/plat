package com.eaglec.plat.biz.impl.mall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.mall.MallCategoryBiz;
import com.eaglec.plat.dao.mall.MallCategoryDao;
import com.eaglec.plat.dao.service.ServiceDao;
import com.eaglec.plat.domain.mall.MallCategory;
import com.eaglec.plat.utils.Constant;

@Service
public class MallCategoryBizImpl implements MallCategoryBiz {

	@Autowired
	private MallCategoryDao mallCategoryDao;
	@Autowired
	private ServiceDao serviceDao;

	@Override
	public List<MallCategory> findAll() {
		String hql = "from MallCategory where pid is null";
		return mallCategoryDao.findList(hql);
	}

	@Override
	public List<MallCategory> findAllMallCategory() {
		String hql = "from MallCategory where pid =1 and clazz='SERVICE'";
		return mallCategoryDao.findList(hql);
	}

	@Override
	public List<MallCategory> findMallCategoryChildren(Integer id) {
		StringBuffer hql = new StringBuffer("from MallCategory where 1=1");
		if (null != id) {
			hql.append(" and pid = '" + id + "'");
		} else {
			hql.append(" and pid is null");
		}
		return mallCategoryDao.findList(hql.toString());
	}

	@Override
	public MallCategory addOrUpdate(MallCategory MallCategory) {
		return mallCategoryDao.saveOrUpdate(MallCategory);
	}

	@Override
	public void delete(MallCategory MallCategory) {
		mallCategoryDao.delete(MallCategory);
	}

	@Override
	public void deleteById(Integer id) {
		mallCategoryDao.delete(id);
	}

	@Override
	public MallCategory findById(String clazz, Integer id) {
		String hql = "from MallCategory where clazz=:clazz and id=:id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clazz", clazz);
		params.put("id", id);
		return mallCategoryDao.get(hql, params);
	}

	@Override
	public MallCategory findRootByClazz(String clazz) {
		String hql = "from MallCategory where pid is null and clazz=:clazz ";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clazz", clazz);
		return mallCategoryDao.get(hql, params);
	}

	@Override
	public String findServiceByMall(Integer mallId, int start, int limit) {
		StringBuffer html = new StringBuffer();
		// 获取所有的父类别
		List<MallCategory> c = findMallCategoryChildren(mallId);
		for (int i = 0; i < c.size(); i++) {
			MallCategory cy = c.get(i);
			if (!cy.getHide()) {
				/****************** 父类别的title****bengin *********************/
				html.append("<li id=\"" + cy.getId() + "\" pid=\""
						+ cy.getPid() + "\">");
				html.append("<div class=\"name-wrap\">");
				html.append("<div class=\"name-column\"><h4 class=\"s"
						+ (i + 1) + "\">");
				html.append("<ahref=\"mall/mallList?mallId=" + cy.getId()
						+ "\">" + cy.getText() + "</a>");
				html.append("</h4></div>");
				html.append("<i class=\"arrow\"></i>");
				html.append("</div>");
				/****************** end *********************/

				/******************* 子类别加载****bengin *****************/
				List<MallCategory> cc = cy.getChildren();
				if (!cc.isEmpty()) {
					html.append("<div class=\"mod-sub-cate\">");
					for (MallCategory ccy : cc) {
						if (!ccy.getHide()) {
							String hql = "from Service where mallId="
									+ ccy.getId() + " and currentStatus="
									+ Constant.SERVICE_STATUS_ADDED;
							List<com.eaglec.plat.domain.service.Service> s = serviceDao
									.findList(hql, 0, 3);
							if (!s.isEmpty()) {
								html.append("<dl>");
								html.append("<dt>");
								html.append(
										"<a target=\"_blank\" href=\"mall/mallList?mallId=")
										.append(ccy.getId());
								html.append("\">").append(ccy.getText())
										.append("</a>");
								html.append("</dt>");
								/******************* 特色服务加载****bengin *****************/

								if (s.size() > 0) {
									for (com.eaglec.plat.domain.service.Service se : s) {
										html.append("<dd>");
										html.append(
												"<a target='_blank' href=\"service/detail?op=mall&id=")
												.append(se.getId());
										String serviceName = se
												.getServiceName();
										if (serviceName.length() > 15) {
											serviceName = serviceName
													.substring(0, 15) + "...";
										}
										html.append("\">•" + serviceName
												+ "</a>");
										html.append("</dd>");
									}
								}
								html.append("</dl>");
								
							}
						}
					}

				}
				html.append("</div></li>");
			}

		}
		return html.toString();

	}

	@Override
	public List<MallCategory> findAllMallCatetoryByPid(Integer pid) {
		MallCategory mall = mallCategoryDao.get(pid);
		String hql = "from MallCategory where pid = ";
		if (null != mall.getPid()) {
			hql += mallCategoryDao.get(pid).getPid();
		} else {
			hql += pid;
		}

		return mallCategoryDao.findList(hql);
	}

	@Override
	public void update(MallCategory mallCategory) {
		mallCategoryDao.update(mallCategory);
	}

	@Override
	public List<MallCategory> findChildren(Integer pid) {
		String hql = "from MallCategory where 1=1 ";
		if (null == pid) {
			hql += " and pid in (select id from MallCategory where pid is null)";
		} else {
			if (pid == 14) {
				hql += "and pid =1";
			} else if (pid == 15) {
				hql += "and pid =4";
			} else if (pid == 16) {
				hql += "and pid =2";
			} else if (pid == 17) {
				hql += "and pid =3";
			}
		}
		return mallCategoryDao.findList(hql);
	}
}
