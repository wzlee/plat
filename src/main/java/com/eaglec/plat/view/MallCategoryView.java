package com.eaglec.plat.view;

import java.util.List;

import com.eaglec.plat.domain.mall.Advertisement;
import com.eaglec.plat.domain.mall.MallCategory;

public class MallCategoryView {
	private MallCategory category;
	private List<Advertisement> recomServiceList;
	private List<Advertisement> serviceAdList;

	public MallCategoryView() {
	}

	public MallCategory getCategory() {
		return category;
	}

	public void setCategory(MallCategory category) {
		this.category = category;
	}

	public List<Advertisement> getRecomServiceList() {
		return recomServiceList;
	}

	public void setRecomServiceList(List<Advertisement> recomServiceList) {
		this.recomServiceList = recomServiceList;
	}

	public List<Advertisement> getServiceAdList() {
		return serviceAdList;
	}

	public void setServiceAdList(List<Advertisement> serviceAdList) {
		this.serviceAdList = serviceAdList;
	}

}
