package com.eaglec.plat.sync;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.eaglec.plat.utils.Dao;

/**
 * 同步前执行类
 * 
 * @author Xiadi
 * @since 2013-12-2
 */
@Component
@Lazy(false)
public class BeforeSync implements InitializingBean{

	@Autowired
	private Dao dao;
	@Autowired
	private WindowDao fzDao;
	@Autowired
	private WindowDao dzzbDao;
	@Autowired
	private WindowDao gysjDao;
	@Autowired
	private WindowDao gazyDao;
	@Autowired
	private WindowDao jjDao;
	@Autowired
	private WindowDao jxDao;
	@Autowired
	private WindowDao ledDao;
	@Autowired
	private WindowDao rjDao;
	@Autowired
	private WindowDao wlDao;
	@Autowired
	private WindowDao wlwDao;
	@Autowired
	private WindowDao xclDao;
	@Autowired
	private WindowDao zbDao;
	@Autowired
	private WindowDao zbaoDao;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		SyncFactory.setDao(dao);
		SyncFactory.putWindowDao(1, dzzbDao);
		SyncFactory.putWindowDao(2, fzDao);
		
		SyncFactory.putWindowDao(4, gysjDao);
		SyncFactory.putWindowDao(3, gazyDao);
		SyncFactory.putWindowDao(5, jxDao);
		SyncFactory.putWindowDao(6, jjDao);
		SyncFactory.putWindowDao(7, ledDao);
		SyncFactory.putWindowDao(8, rjDao);
		SyncFactory.putWindowDao(9, wlDao);
		SyncFactory.putWindowDao(10, wlwDao);
		SyncFactory.putWindowDao(11, xclDao);
		SyncFactory.putWindowDao(13, zbDao);
		SyncFactory.putWindowDao(14, zbaoDao);
		
		SyncFactory.putWindowName(1, "电子装备");
		SyncFactory.putWindowName(2, "服装");
		SyncFactory.putWindowName(3, "港澳资源");
		SyncFactory.putWindowName(4, "工业设计");
		SyncFactory.putWindowName(5, "机械");
		SyncFactory.putWindowName(6, "家具");
		SyncFactory.putWindowName(7, "LED");
		SyncFactory.putWindowName(8, "软件");
		SyncFactory.putWindowName(9, "物流");
		SyncFactory.putWindowName(10, "物联网");
		SyncFactory.putWindowName(11, "新材料");
		SyncFactory.putWindowName(13, "钟表");
		SyncFactory.putWindowName(14, "珠宝");
	}

}
