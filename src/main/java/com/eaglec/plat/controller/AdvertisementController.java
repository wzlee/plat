package com.eaglec.plat.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.biz.cms.ChannelBiz;
import com.eaglec.plat.biz.mall.AdvertisementBiz;
import com.eaglec.plat.biz.mall.MallCategoryBiz;
import com.eaglec.plat.biz.service.ServiceBiz;
import com.eaglec.plat.domain.cms.Channel;
import com.eaglec.plat.domain.mall.Advertisement;
import com.eaglec.plat.domain.mall.MallCategory;
import com.eaglec.plat.domain.service.Service;
import com.eaglec.plat.view.JSONResult;

/**
 * 服务商城推荐服务及广告配置controller
 * 
 * @author huyj
 * 
 */
@Controller
@RequestMapping(value = "/ad")
public class AdvertisementController extends BaseController {
	@Autowired
	AdvertisementBiz advertisementBiz;
	@Autowired
	ServiceBiz serviceBiz;
	@Autowired
	MallCategoryBiz mallCategoryBiz;
	@Autowired
	ChannelBiz channelBiz;

	/**
	 * 点击左边菜单加载相关广告及推荐服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-12
	 * @param position
	 * @param start
	 * @param limit
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/findAll")
	public void findAll(
			String position,
			String advNo,
			@RequestParam(value = "start", defaultValue = "0", required = false) int start,
			@RequestParam(value = "limit", defaultValue = "25", required = false) int limit,
			HttpServletRequest request, HttpServletResponse response) {
		this.outMallJson(response,
				advertisementBiz.findAll(position, advNo, start, limit));
	}

	/**
	 * 添加推荐服务或服务广告
	 * 
	 * @author huyj
	 * @sicen 2013-10-14
	 * @param advertisement
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/addAd")
	public void addAd(Advertisement advertisement, HttpServletRequest request,
			HttpServletResponse response) {
		String msg = "添加";
		if (null != advertisement.getId()) {
			msg = "修改";
		}
		try {

			Service service = serviceBiz.findServiceById(advertisement
					.getService().getId());
			MallCategory mallCategory = mallCategoryBiz.findById("service",
					advertisement.getMallCategory().getId());
			Channel channel = channelBiz.findById(advertisement.getChannel()
					.getId());
			advertisement.setChannel(channel);
			advertisement.setMallCategory(mallCategory);
			// 如果UploadImage为空则说明为推荐服务，使用对应服务的图片作为显示图片
			if (StringUtils.isEmpty(advertisement.getUploadImage())) {
				advertisement.setUploadImage(service.getPicture());
			}
			advertisement.setService(service);
			String advNo = getAdvNo(channel);
			advertisement.setAdvertisementNo(advNo);
			advertisement.setPageLink("service/detail?id=" + service.getId()
					+ "&op=mall");
			advertisement.setLastEditTime(DateFormatUtils.format(new Date(),
					"yyyy-MM-dd HH:mm:ss"));
			advertisementBiz.add(advertisement);
			super.outJson(response, new JSONResult(true, msg + "成功"));
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, msg + "失败"));
		}
	}

	/**
	 * 生成编码
	 * 
	 * @author huyj
	 * @sicen 2013-10-14
	 * @param channel
	 * @return
	 */
	private String getAdvNo(Channel channel) {
		String advNo = "SC-";
		if (channel.getId() == 14) {
			advNo += "gy-";
		} else if (channel.getId() == 15) {
			advNo += "wl-";
		} else if (channel.getId() == 16) {
			advNo += "rj-";
		} else {
			advNo += "wlw-";
		}
		Long count = advertisementBiz.findLastAdvNo(channel.getId());
		if (count < 9) {
			advNo += "0" + (count + 1);
		} else {
			advNo += (count + 1);
		}
		return advNo;
	}

	/**
	 * 编辑广告或推荐服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-12
	 * @param advertisement
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/editAd")
	public void editAd(Advertisement advertisement, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			advertisement.setLastEditTime(DateFormatUtils.format(new Date(),
					"yyyy-MM-dd HH:mm:ss"));
			advertisementBiz.update(advertisement);
			super.outJson(response, new JSONResult(true, "修改成功"));
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, "修改失败"));
		}
	}

	/**
	 * 删除单个广告或推荐服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-12
	 * @param adIds
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/deleteAd")
	public void deleteAd(Advertisement advertisement,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			advertisementBiz.delete(advertisement.getId());
			super.outJson(response, new JSONResult(true, "删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, "删除失败"));
		}
	}

	/**
	 * 服务商城,添加推荐服务 查找对应服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-14
	 * @param mallId
	 *            商城类别
	 * @param serviceName
	 *            服务名称
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/findRecomService")
	public void findRecomService(@RequestParam("mallId") Integer mallId,
			@RequestParam("serviceName") String serviceName,
			HttpServletRequest request, HttpServletResponse response) {
		this.outJson(response, serviceBiz.findRecomService(serviceName, mallId));
	}

	/**
	 * 批量删除广告或推荐服务
	 * 
	 * @author huyj
	 * @sicen 2013-10-14
	 * @param advIds
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delecteAdvs")
	public void delecteAdvs(String advIds, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String[] ids = advIds.split(",");
			for (String id : ids) {
				advertisementBiz.delete(Integer.parseInt(id));
			}
			super.outJson(response, new JSONResult(true, "删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, "删除失败"));
		}
	}
}
