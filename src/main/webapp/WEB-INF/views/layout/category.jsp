<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="main-cate">
<ul id="frist_list" class="mod-cate">
  <li pid="105" id="106">
    <div class="name-wrap">
      <div class="name-column">
        <h4 class=""> <a href="">政策法规</a> </h4>
        <h6><a href="">财政引导</a> <a href="">产业扶持</a> <a href="">专项资金</a></h6>
      </div>
      <i class="arrow"></i> </div>
    <div style="display: none;" class="mod-sub-cate">
      <div class="sub-item">
        <dl class="list">
          <dt class="p"><a href="#">财政引导</a></dt>
          <dd class="c"><a href="">中小企业健康发展意见</a><a href="">贸易发展"十二五"规划</a></dd>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">产业扶持</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">专项资金</a></dt>
          <dd class="c"><a href="">小微企业培育资助通知</a><a href="">信息化建设资助通知</a></dd>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">人才激励</a></dt>
          <dd class="c"><a href="">留学人员回国服务建设</a><a href="">支持留学人员回国创业</a></dd>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">税收优惠</a></dt>
          <dd class="c"><a href="">小微企业可享税收优惠 </a><a href="">进出口税收新政策</a></dd>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">金融支持</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">知识产权</a></dt>
          <dd class="c"><a href="">知识产权战略纲要通知</a><a href="">从五方面调整知识产权</a></dd>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">土地解决</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">其他政策</a></dt>
        </dl>
      </div>
      <div class="sub-special">
        <h3>特色服务</h3>
        <ul class="special-list">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul>
      </div>
    </div>
  </li>
  <li pid="105" id="107">
    <div class="name-wrap">
      <div class="name-column">
        <h4 class=""> <a href="">创业指导</a> </h4>
        <h6><a href="">选定创业项目</a> <a href="">拟定创业计划</a></h6>
      </div>
      <i class="arrow"></i> </div>
    <div style="display: none;" class="mod-sub-cate">
      <div class="sub-item">
        <dl class="list">
          <dt class="p"><a href="#">选定创业项目</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">拟定创业计划</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">筹集创业资金</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">办理创业的法律手续</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">创业经营的基本策略</a></dt>
        </dl>
      </div>
      <div class="sub-special">
        <h3>特色服务</h3>
        <ul class="special-list">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul>
      </div>
    </div>
  </li>
  <li pid="105" id="108">
    <div class="name-wrap">
      <div class="name-column">
        <h4 class=""> <a href="">人才培育</a> </h4>
        <h6><a href="">技能培训</a> <a href="">户外拓展</a></h6>
      </div>
      <i class="arrow"></i> </div>
    <div style="display: none;" class="mod-sub-cate">
      <div class="sub-item">
        <dl class="list">
          <dt class="p"><a href="#">技能培训</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">户外拓展</a></dt>
        </dl>
      </div>
      <div class="sub-special">
        <h3>特色服务</h3>
        <ul class="special-list">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul>
      </div>
    </div>
  </li>
  <li pid="105" id="109">
    <div class="name-wrap">
      <div class="name-column">
        <h4 class=""> <a href="">信息服务</a> </h4>
        <h6><a href="">信息检索</a> <a href="">信息咨询</a> <a href="">网络信息</a></h6>
      </div>
      <i class="arrow"></i> </div>
    <div style="display: none;" class="mod-sub-cate">
      <div class="sub-item">
        <dl class="list">
          <dt class="p"><a href="#">信息检索</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">信息报道与发布</a></dt>
          <dd class="c"><a href="">中小企业私募债试点待出</a><a href="">支持企业创新创业</a></dd>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">信息咨询</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">网络信息</a></dt>
        </dl>
      </div>
      <div class="sub-special">
        <h3>特色服务</h3>
        <ul class="special-list">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul>
      </div>
    </div>
  </li>
  <li pid="105" id="110">
    <div class="name-wrap">
      <div class="name-column">
        <h4 class=""> <a href="">管理咨询</a> </h4>
        <h6><a href="">战略咨询</a> <a href="">信息化咨询</a></h6>
      </div>
      <i class="arrow"></i> </div>
    <div style="display: none;" class="mod-sub-cate">
      <div class="sub-item">
        <dl class="list">
          <dt class="p"><a href="#">战略咨询</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">企业管理咨询</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">信息化咨询</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">专项咨询</a></dt>
        </dl>
      </div>
      <div class="sub-special">
        <h3>特色服务</h3>
        <ul class="special-list">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul>
      </div>
    </div>
  </li>
  <li pid="105" id="111">
    <div class="name-wrap">
      <div class="name-column">
        <h4 class=""> <a href="">法律维权</a> </h4>
        <h6><a href="">争议解决</a> <a href="">涉外诉讼</a> <a href="">商业秘密</a></h6>
      </div>
      <i class="arrow"></i> </div>
    <div style="display: none;" class="mod-sub-cate">
      <div class="sub-item">
        <dl class="list">
          <dt class="p"><a href="#">争议解决</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">涉外诉讼</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">商业秘密</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">专利</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">商标</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">劳动争议及解决</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">境内外商帐追收</a></dt>
        </dl>
      </div>
      <div class="sub-special">
        <h3>特色服务</h3>
        <ul class="special-list">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul>
      </div>
    </div>
  </li>
  <li pid="105" id="112">
    <div class="name-wrap">
      <div class="name-column">
        <h4 class=""> <a href="">市场开拓</a> </h4>
        <h6><a href="">市场现状分析</a> <a href="">企业现状分析</a></h6>
      </div>
      <i class="arrow"></i> </div>
    <div style="display: none;" class="mod-sub-cate">
      <div class="sub-item">
        <dl class="list">
          <dt class="p"><a href="#">市场现状分析</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">企业现状分析</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">目标市场分析</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">市场推广组合策略</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">具体行动计划</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">损益分析</a></dt>
        </dl>
      </div>
      <div class="sub-special">
        <h3>特色服务</h3>
        <ul class="special-list">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul>
      </div>
    </div>
  </li>
  <li pid="105" id="113">
    <div class="name-wrap">
      <div class="name-column">
        <h4 class=""> <a href="">融资担保</a> </h4>
        <h6><a href="">银行贷款担保</a> <a href="">项目融资担保</a></h6>
      </div>
      <i class="arrow"></i> </div>
    <div style="display: none;" class="mod-sub-cate">
      <div class="sub-item">
        <dl class="list">
          <dt class="p"><a href="#">直接融资与间接融资</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">银行贷款担保</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">项目融资担保</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">保付代理与担保</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">国际融资担保</a></dt>
        </dl>
      </div>
      <div class="sub-special">
        <h3>特色服务</h3>
        <ul class="special-list">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul>
      </div>
    </div>
  </li>
  <li pid="105" id="114">
    <div class="name-wrap">
      <div class="name-column">
        <h4 class=""> <a href="">兼并重组</a> </h4>
        <h6><a href="">银行贷款担保</a> <a href="">项目融资担保</a></h6>
      </div>
      <i class="arrow"></i> </div>
    <div style="display: none;" class="mod-sub-cate">
      <div class="sub-item"></div>
      <div class="sub-special">
        <h3>特色服务</h3>
        <ul class="special-list">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul>
      </div>
    </div>
  </li>
  <li pid="105" id="115">
    <div class="name-wrap">
      <div class="name-column">
        <h4 class=""> <a href="">技术专利</a> </h4>
        <h6><a href="">发明专利</a> <a href="">实用新型专利</a></h6>
      </div>
      <i class="arrow"></i> </div>
    <div style="display: none;" class="mod-sub-cate">
      <div class="sub-item">
        <dl class="list">
          <dt class="p"><a href="#">发明专利</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">实用新型专利</a></dt>
        </dl>
        <dl class="list">
          <dt class="p"><a href="#">外观设计专利</a></dt>
        </dl>
      </div>
      <div class="sub-special">
        <h3>特色服务</h3>
        <ul class="special-list">
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
          <li><a href=""></a></li>
        </ul>
      </div>
    </div>
  </li>
</ul>
