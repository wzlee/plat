package com.eaglec.plat.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eaglec.plat.aop.NeedSession;
import com.eaglec.plat.aop.SessionType;
import com.eaglec.plat.biz.policy.FiltrateRuleBiz;
import com.eaglec.plat.biz.policy.PolicyBiz;
import com.eaglec.plat.biz.policy.PolicyCategoryBiz;
import com.eaglec.plat.domain.policy.FiltrateRule;
import com.eaglec.plat.domain.policy.Policy;
import com.eaglec.plat.domain.policy.PolicyCategory;
import com.eaglec.plat.utils.Constant;
import com.eaglec.plat.utils.StrUtils;
import com.eaglec.plat.view.JSONResult;
import com.eaglec.plat.view.PolicyParam;
import com.eaglec.plat.view.PolicyView;
import com.eaglec.plat.view.ReportingView;
import com.eaglec.plat.view.ReporttingInfoView;

@Controller
@RequestMapping(value = "/policy")
public class PolicyController extends BaseController {

	@Autowired
	PolicyCategoryBiz policyCategoryBiz;

	@Autowired
	PolicyBiz policyBiz;
	


	@Autowired
	FiltrateRuleBiz ruleBiz;

	/**
	 * 政策指引首页
	 * 
	 * @author huyj
	 * @sicen 2013-10-21
	 * @param name
	 *            名称
	 * @param reques
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(String name,HttpServletRequest reques,
			HttpServletResponse response) {
		// 加载类别
		List<PolicyCategory> cList = policyCategoryBiz.findAll(0);
		List<PolicyView> viewList = new ArrayList<PolicyView>();
		String keyword = null;
		if(!StrUtils.isNull(name)){
			keyword = name.trim();
		}
		for (PolicyCategory policyCategoru : cList) {
			PolicyView policyView = new PolicyView(policyCategoru, policyBiz.findByCid(policyCategoru.getId(), keyword, 0, 5));
			if(!StrUtils.isNull(keyword)){
				if(policyView.getPolicyList()!=null && policyView.getPolicyList().size() != 0){
					for(Policy p:policyView.getPolicyList())
					p.setTitle((p.getTitle().replaceAll(keyword,"<font color='red'>" + keyword + "</font>")));
				}
			}
			viewList.add(policyView);
		}
		reques.setAttribute("viewList", viewList);
		return "policy/policy_index";
	}

	/**
	 * 添加或者修改政策指引
	 * @author liuliping
	 * @since 2013-10-19
	 * @param policyCategory 政策指引对象属性
	 * */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(Policy policy,
			HttpServletRequest reques, HttpServletResponse response) {
		if (policy.getId() == null) {    //新增
			policy.setTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
			policy.setPv(0);
			policyBiz.add(policy);
		} else {	//修改
			policyBiz.update(policy);
		}
		outJson(response, new JSONResult(true, "操作成功"));
	}
	
	/**
	 * 删除政策指引
	 * @author liuliping
	 * @since 2013-10-19
	 * @param  id 政策指引对象id
	 * */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/delete")
	public void delete(Integer id,
			HttpServletRequest reques, HttpServletResponse response) {
		//删除政策指引,并且删除文件	#FIXME
		policyBiz.delete(id);
		outJson(response, new JSONResult(true, "删除成功"));
	}

	/**
	 * 政策指引资讯列表页
	 * 
	 * @author huyj
	 * @sicen 2013-10-21
	 * @param policyName
	 * @param policyId
	 * @param start
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(
			Integer policyId,
			String policyName,
			@RequestParam(value = "pager.offset", defaultValue = "0", required = true) int start,
			HttpServletRequest request, HttpServletResponse response) {
		PolicyCategory policyCategory = policyCategoryBiz.findById(policyId);
		List<PolicyCategory> cList = policyCategoryBiz.findAll(0);
		request.setAttribute("policyCategory", policyCategory);
		if (null != policyCategory.getPid()) {
			request.setAttribute("policyPCategory",
					policyCategoryBiz.findById(policyCategory.getPid()));
		} else {
			request.setAttribute("policyPCategory", null);
		}
		request.setAttribute("cList", cList);
		if (policyCategory.getType() != 1) {
			List<Policy> policyList = policyBiz.findByCid(
					policyCategory.getId(),
					policyName, start, 25);
	
			long total = policyBiz.findCountByCid(policyCategory.getId(),
					policyName);
			request.setAttribute("total", total);
			request.setAttribute("policyList", policyList);
			return "policy/policy_list";
		} else {
			// 第一层跟类别
			List<ReportingView> reportingViewList = new ArrayList<ReportingView>();
			for (PolicyCategory pCategory : policyCategory.getChildren()) {
				ReportingView view = new ReportingView();
				view.setPolicyCategory(pCategory);
				List<ReporttingInfoView> infoList = new ArrayList<ReporttingInfoView>();
				for (PolicyCategory p : pCategory.getChildren()) {
					ReporttingInfoView info = new ReporttingInfoView();
					info.setPolicyCategory(p);
					info.setFinancialReportings(policyBiz.findByCid(p.getId(),
							policyName, 0, 0));
					infoList.add(info);
				}
				view.setInfoList(infoList);
				reportingViewList.add(view);
			}
			request.setAttribute("reportingViewList", reportingViewList);
			return "policy/policy_list_reporting";
		}
		
	}

	/**
	 * 资讯详情
	 * 
	 * @author huyj
	 * @sicen 2013-10-21
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Integer id, HttpServletRequest request,
			HttpServletResponse response) {
		Policy policy = policyBiz.findById(id);
		policy.setPv(policy.getPv() + 1);
		policyBiz.update(policy);
		PolicyCategory policyCategory = policyCategoryBiz.findById(policy
				.getPolicyCategory().getId());
		List<Policy> hotPolicy = policyBiz.findHotPolicy();
		request.setAttribute("policy", policy);
		request.setAttribute("policyCategory", policyCategory);
		request.setAttribute("hotPolicy", hotPolicy);
		return "policy/policy_detail";

	}

	/**
	 * 
	 *  添加或者修改政策指引分类
	 * @author liuliping
	 * @since 2013-10-19
	 * @param policyCategory
	 *            政策指引对象属性
	 * */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/addOrUpdateCategory")
	public void addOrUpdateCategory(PolicyCategory policyCategory,
			HttpServletRequest reques, HttpServletResponse response) {
		if (policyCategory.getId() == null) { // 新增分类
			policyCategoryBiz.add(policyCategory);
		} else { // 修改分类
			policyCategoryBiz.update(policyCategory);
		}
		outJson(response, new JSONResult(true, "操作成功"));
	}

	/**
	 * 删除政策指引分类
	 * 
	 * @author liuliping
	 * @since 2013-10-19
	 * @param 政策指引对象属性
	 * */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/deleteCategory")
	public void deleteCategory(Integer id, HttpServletRequest reques,
			HttpServletResponse response) {
		outJson(response, policyCategoryBiz.delete(id));
	}

	/**
	 * 查询政策指引类别
	 * 
	 * @author liuliping
	 * @since 2013-10-19
	 * */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/queryCategory")
	public void queryCategory(Integer type, HttpServletRequest reques,
			HttpServletResponse response) {
		List<PolicyCategory> list = policyCategoryBiz.findAll(type);
		outJson(response, list);
	}
	
	/**
	 * 查询政策指引类别
	 * 
	 * @author liulipingo
	 * @since 2013-10-21
	 * @param
	 * */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/queryByParams")
	public void queryByParams(
			String title,
			Integer pcid,
			@RequestParam(value = "type", defaultValue = "0", required = true) Integer type,
			@RequestParam(value = "start", defaultValue = "0", required = true) int start,
			@RequestParam(value = "limit", defaultValue = "15", required = true) int limit,
			HttpServletRequest reques, HttpServletResponse response) {
		outJson(response,
				policyBiz.queryByParams(title, pcid, type, start, limit));
	}
	/**
	 * 查找所有资金资助信息
	 * 
	 * @author huyj
	 * @sicen 2013-10-23
	 * @param title
	 * @param pcid
	 * @param start
	 * @param limit
	 * @param reques
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/getAllFinacial")
	public void findAllFincial(String title, Integer pcid,
			@RequestParam(value = "start", defaultValue = "0", required = true) int start,
			@RequestParam(value = "limit", defaultValue = "15", required = true) int limit,
			HttpServletRequest reques, HttpServletResponse response) {
		this.outMallJson(response,
				policyBiz.findByCid(pcid, title, start, limit));
	}

	/**
	 * 查找资金资助类别
	 * 
	 * @author huyj
	 * @sicen 2013-10-25
	 * @param reques
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findReportCategory")
	public void findReportCategory(HttpServletRequest reques,
			HttpServletResponse response) {
		outJson(response, policyCategoryBiz.findReportCategory());
	}

	/**
	 * 获取还没有资金资助的政策
	 * 
	 * @author huyj
	 * @sicen 2013-10-25
	 * @param reques
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value="/findPolicyNoReport")
	public void findPolicyNoReport(HttpServletRequest reques,
			HttpServletResponse response) {
		outJson(response, policyBiz.findPolicyNoReport());
		
	}

	/**
	 * 添加或修改
	 * 
	 * @author huyj
	 * @sicen 2013-10-25
	 * @param financialReporting
	 * @param reques
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/addReport")
	public void addReport(Policy policy,
			HttpServletRequest reques,
			HttpServletResponse response) {
		try {
			if (null != policy.getId()) {
				policyBiz.update(policy);

			}else{
				policyBiz.add(policy);
			}
			outJson(response, new JSONResult(true, "添加成功"));
		} catch (Exception e) {
			outJson(response, new JSONResult(false, "添加失败"));
		}
	}

	/**
	 * 删除
	 * 
	 * @author huyj
	 * @sicen 2013-10-25
	 * @param f
	 * @param reques
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/deleteReporting")
	public void deleteReporting(Policy f,
			HttpServletRequest reques, HttpServletResponse response) {
		try {
			policyBiz.delete(f.getId());
			super.outJson(response, new JSONResult(true, "删除成功!"));
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, "操作失败!"));
		}
	}
	
	/**
	 * 匹配规则
	 * 
	 * @author huyj
	 * @sicen 2013-10-26
	 * @param ruleId
	 * @param reportingIds
	 * @param reques
	 * @param response
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/addReporingRule")
	public void addReporingRule(String ruleId, String reportingIds,
			HttpServletRequest reques, HttpServletResponse response) {
		try {
			String[] repotIds = reportingIds.split(",");
			ruleBiz.deleteRuleByRuleId(ruleId);
			for (String id : repotIds) {
				FiltrateRule rule = new FiltrateRule();
				rule.setRuleId(ruleId);
				String conditionField = ruleId.substring(0, 1);
				rule.setConditionField(conditionField);
				rule.setPolicy(policyBiz.findById(Integer.parseInt(id)));
				if (!isConstains(ruleId, Integer.parseInt(id))) {
					ruleBiz.add(rule);
				}
			}
			super.outJson(response, new JSONResult(true, "操作成功!"));
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, "操作失败!"));
		}
	}

	/**
	 * 判断该规则下是否存在此政策信息
	 * 
	 * @author huyj
	 * @sicen 2013-10-28
	 * @param ruleId
	 * @return
	 */
	private boolean isConstains(String ruleId, Integer id) {
		List<FiltrateRule> list = ruleBiz.findReporingId(ruleId);
		for (FiltrateRule o : list) {
			if (o.getPolicy().getId() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 资助一键通首页
	 * 
	 * @author huyj
	 * @sicen 2013-10-28
	 * @param reques
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/reportingIndex")
	public String reporingList(PolicyParam param, HttpServletRequest request,
			HttpServletResponse response) {
		// 查找数据
		List<FiltrateRule> list = ruleBiz.findRuleByParam(configParam(param));
		List<Policy> reportingList = new ArrayList<Policy>();
		if (!paramNull(param)) {
			for (FiltrateRule ruleId : list) {
				reportingList.add(policyBiz
						.findById(ruleId.getPolicy().getId()));
			}
		} else {
			reportingList = policyBiz.findAllReportingPolicy();
		}

		List<ReportingView> reportingViewList = new ArrayList<ReportingView>();
		for (PolicyCategory pCategory : policyCategoryBiz.findReportCategory()
				.get(0).getChildren()) {
			ReportingView view = new ReportingView();
			view.setPolicyCategory(pCategory);
			List<ReporttingInfoView> infoList = new ArrayList<ReporttingInfoView>();
			for (PolicyCategory p : pCategory.getChildren()) {
				ReporttingInfoView info = new ReporttingInfoView();
				info.setPolicyCategory(p);
				info.setFinancialReportings(findReporings(p, reportingList));
				infoList.add(info);
			}
			view.setInfoList(infoList);
			reportingViewList.add(view);
		}
		request.setAttribute("reportingViewList", reportingViewList);
	 return "policy/reporting_index";
	 }


	private boolean paramNull(PolicyParam param) {
		if (!StringUtils.isEmpty( param.getTime())) {
			return false;
		}
		if (!StringUtils.isEmpty(param.getAddr())) {
			return false;
		}
		if (!StringUtils.isEmpty(param.getBusinessIncome())) {
			return false;
		}
		if (!StringUtils.isEmpty(param.getEnterprise())) {
			return false;
		}
		if (!StringUtils.isEmpty(param.getTotalIncome())) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-10-30
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	 @RequestMapping(value = "/reportingInfo")
	 public String reportingInfo(Integer id, HttpServletRequest request,
	 HttpServletResponse response) {
		Policy currentReporting = policyBiz.findById(id);
		PolicyCategory currentCategory = currentReporting.getPolicyCategory();
		//List<FiltrateRule> list = ruleBiz.findRuleByParam(new PolicyParam());
		List<Policy> reportingList = new ArrayList<Policy>();
	//	for (FiltrateRule ruleId : list) {
		reportingList = policyBiz.findAllReportingPolicy();
		//}
		List<ReportingView> reportingViewList = new ArrayList<ReportingView>();
		for (PolicyCategory pCategory : policyCategoryBiz.findReportCategory()
				.get(0).getChildren()) {
			ReportingView view = new ReportingView();
			view.setPolicyCategory(pCategory);
			List<ReporttingInfoView> infoList = new ArrayList<ReporttingInfoView>();
			for (PolicyCategory p : pCategory.getChildren()) {
				ReporttingInfoView info = new ReporttingInfoView();
				info.setPolicyCategory(p);
				info.setFinancialReportings(findReporings(p, reportingList));
				infoList.add(info);
			}
			view.setInfoList(infoList);
			reportingViewList.add(view);
		}

		request.setAttribute("currentReporting", currentReporting);
		request.setAttribute("currentCategory", currentCategory);
		request.setAttribute("reportingViewList", reportingViewList);
	 return "policy/reporting_info";
	 }

	private PolicyParam configParam(PolicyParam param) {
		// 设置时间
		if (!StringUtils.isEmpty( param.getTime())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = null;
			try {
				d1 = formatter.parse(param.getTime());
				Date d2 = new Date();
				long diff = d2.getTime() - d1.getTime();
				long day = (diff / (1000 * 60 * 60 * 24));
				if (day >= 365) {
					param.setTime("101");
				} else {
					param.setTime("102");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			param.setTime(null);
		}
		// 设置地址
		if (StringUtils.isEmpty(param.getAddr())) {
			param.setAddr(null);
		} else {
			if (param.getAddr().equals("11")) {
				param.setAddr("201");
			} else {
				param.setAddr("202");
			}
		}
		// 设置行业
		if (null != param.getEnterprise() && param.getEnterprise().equals("0")) {
			param.setEnterprise(null);
		}
		// 上年度主营业务收入
		 if(null!=param.getBusinessIncome()){
			 if(param.getBusinessIncome()<=300){
				param.setBusinessIncome(401);
			 }
			 else if(param.getBusinessIncome()>300&&param.getBusinessIncome()<10000){
				param.setBusinessIncome(402);
			 }
			 else{
				param.setBusinessIncome(403);
			 }
		 }

		// 上年度销售总收入
		if(null!=param.getTotalIncome()){
			 
			 if(param.getTotalIncome()<=20000){
				param.setTotalIncome(501);
			 }else{
				param.setTotalIncome(502);
			 }
		 }

		return param;
	}

	/**
	 * 讲对应的reporting添加到类别下
	 * @author huyj
	 * @sicen 2013-10-29
	 * @description TODO
	 * actionPath eg:
	 * @param p
	 * @param reportingList
	 * @return
	 */
	private List<Policy> findReporings(PolicyCategory p,
			List<Policy> reportingList) {
		List<Policy> tempList = new ArrayList<Policy>();
		for (Policy reporing : reportingList) {
			if (reporing.getPolicyCategory().getId() == p.getId()) {
				tempList.add(reporing);
			}
		}
		return tempList;
	}

	/**
	 * 查找改规则下的reporting
	 * 
	 * @author huyj
	 * @sicen 2013-10-28
	 * @param ruleId
	 * @param reques
	 * @param response
	 *            Query.setHint(”org.hibernate.cacheable”, true)
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/findeSelectRule")
	public void findeSelectRule(String ruleId, HttpServletRequest reques,
			HttpServletResponse response) {
		List<FiltrateRule> list = ruleBiz.findReporingId(ruleId);
		outMallJson(response, list);
	}

	/**
	 * 
	 * @author huyj
	 * @sicen 2013-11-26
	 * @description TODO actionPath eg:
	 */
	@NeedSession(SessionType.MANAGER)
	@RequestMapping(value = "/configTop")
	public void configTop(Integer id, HttpServletRequest reques,
			HttpServletResponse response) {
		try {
			Policy newTop = policyBiz.findById(id);
			Policy oldTop = policyBiz.findTopPlicy(newTop.getPolicyCategory()
					.getId());
			if (null != oldTop) {
				oldTop.setTop(null);
				policyBiz.update(oldTop);
			}
			newTop.setTop(Constant.POLICY_TOP);
			policyBiz.update(newTop);
			super.outJson(response, new JSONResult(true, "设置置顶成功!"));
		} catch (Exception e) {
			e.printStackTrace();
			super.outJson(response, new JSONResult(false, "设置置顶失败!"));
		}
		Map< String, String> a = new HashMap<String, String>();
		a.remove("1");

	}
}

