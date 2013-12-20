package com.eaglec.plat.biz.impl.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eaglec.plat.biz.user.StaffMenuBiz;
import com.eaglec.plat.dao.user.StaffMenuDao;
import com.eaglec.plat.domain.base.StaffMenu;

@Service
public class StaffMenuBizImpl implements StaffMenuBiz {
	
	@Autowired
	private StaffMenuDao menuDao;

	@Override
	public StaffMenu saveMenu(StaffMenu rights) {
		// TODO Auto-generated method stub
		return menuDao.save(rights);
	}

	@Override
	public List<StaffMenu> findAll(Integer type) {
		// TODO Auto-generated method stub
			return menuDao.findList("from StaffMenu where menuType="+type);
	}

	@Override
	public void deleteRights(StaffMenu rights) {
		// TODO Auto-generated method stub
		menuDao.delete(rights);
	}

	@Override
	public List<StaffMenu> findMenus(String menuids) {
		// TODO Auto-generated method stub
		return menuDao.executeSqlQuerys("select * from staffmenu where id in("+menuids+")");
	}

	@Override
	public List<String> findAllCode(Integer type) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		List<StaffMenu> listm =  findAll(type);
		for(StaffMenu sm : listm){
			list.add(sm.getAuthCode());
		}
		return list;
	}
	
	

}
