package com.eaglec.plat.biz.mall;

import java.util.List;

import com.eaglec.plat.domain.mall.Advertisement;
import com.eaglec.plat.view.JSONData;

/**
 * 服务商城，推荐服务广告服务
 * 
 * @author huyj
 * @since 2013-10-12
 */
public interface AdvertisementBiz {

	/**
	 * 根据所属位置查找广告或推荐服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-12
	 * @param position
	 * @param advNo
	 * @return
	 */
	public JSONData<Advertisement> findAll(String position, String advNo,
			int start, int limit);

	/**
	 * 添加广告或推荐服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-12
	 * @description TODO actionPath eg:
	 * @param advertisement
	 * @return
	 */
	public Advertisement add(Advertisement advertisement);

	/**
	 * 删除广告或推荐服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-12
	 * @description TODO actionPath eg:
	 * @param id
	 * @return
	 */
	public void delete(Integer id);

	/**
	 * 修改广告或推荐服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-12
	 * @description TODO actionPath eg:
	 * @param advertisement
	 * @return
	 */
	public Advertisement update(Advertisement advertisement);

	/**
	 * 根据条件查询广告或推荐服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-12
	 * @description TODO actionPath eg:
	 * @param channelId
	 * @param position
	 * @param No
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<Advertisement> findByParam(Integer channelId, Integer position,
			String No, int start, int limit);

	/**
	 * 根据id获取相应的广告或推荐服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-12
	 * @description TODO actionPath eg:
	 * @param id
	 * @return
	 */
	public Advertisement findById(Integer id);

	/**
	 * 查找最后一个编号
	 * 
	 * @author huyj
	 * @sicen 2013-10-15
	 * @description TODO actionPath eg:
	 * @param channelId
	 * @return
	 */
	public Long findLastAdvNo(Integer channelId);

	/**
	 * 根据商城查询 轮播广告
	 * 
	 * @author huyj
	 * @sicen 2013-10-15
	 * @description TODO actionPath eg:
	 * @param mallId
	 * @return
	 */
	public List<Advertisement> getTopAdList(Integer mallId, Integer position,
			int start, int limit);

	/**
	 * 根据商城查询 轮播广告
	 * 
	 * @author huyj
	 * @sicen 2013-10-15
	 * @description TODO actionPath eg:
	 * @param mallId
	 * @return
	 */
	public List<Advertisement> getRecomAdList(Integer mallId, Integer position,
			int start, int limit);

}
