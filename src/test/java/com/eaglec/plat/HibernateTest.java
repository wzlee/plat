package com.eaglec.plat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.eaglec.plat.biz.auth.ManagerBiz;
import com.eaglec.plat.biz.auth.MenuBiz;
import com.eaglec.plat.biz.auth.RoleBiz;
import com.eaglec.plat.biz.business.AppealBiz;
import com.eaglec.plat.biz.user.StaffBiz;
import com.eaglec.plat.biz.user.StaffMenuBiz;
import com.eaglec.plat.biz.user.StaffRoleBiz;
import com.eaglec.plat.biz.user.UserBiz;
import com.eaglec.plat.domain.auth.Menu;
import com.eaglec.plat.domain.base.Staff;
import com.eaglec.plat.domain.base.StaffMenu;
import com.eaglec.plat.domain.base.StaffRole;
import com.eaglec.plat.utils.MD5;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={
//		"file:src/main/resources/shiro.xml", 
        "file:src/main/webapp/WEB-INF/spring/*.xml", 
        "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml"})
public class HibernateTest {

	@Test
	public void test() {
		System.out.println(MD5.toMD5("chens"));
	}

//	@Autowired
//	HibernateTemplate hibernateTemplate;
	
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private ManagerBiz managerBiz;
	@Autowired
	private RoleBiz roleBiz;
	@Autowired
	private MenuBiz rightsBiz;
	@Autowired
	private AppealBiz appealBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private StaffBiz staffBiz;
	@Autowired
	private StaffMenuBiz staffMenuBiz;
	@Autowired
	private StaffRoleBiz staffRoleBiz;
	
	@Test
	public void testStaff(){
		System.out.println(staffMenuBiz.findMenus("1,2,3").size());
	}
	
	@Test
	public void hibernamteTest(){
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		
		//首页
		StaffMenu m1 = new StaffMenu();
			m1.setAuthCode("ucenter/index");
			m1.setText("首页");
			
		//认证管理	
		StaffMenu m2 = new StaffMenu();
			m2.setAuthCode("ucenter/auth");
			m2.setText("认证管理");
			
		//账号管理
		StaffMenu m3 = new StaffMenu();
			m3.setAuthCode("javascript:void(0);");
			m3.setText("账号管理");
			m3.setLeaf(false);
			
			StaffMenu m4 = new StaffMenu();
				m4.setAuthCode("ucenter/user_manage?uid=${user.id }");
				m4.setText("主账号管理");
				m4.setParent(m3);
				m3.getChildren().add(m4);
			StaffMenu m5 = new StaffMenu();
				m5.setAuthCode("ucenter/security");
				m5.setText("安全中心");
				m5.setParent(m3);
				m3.getChildren().add(m5);
			StaffMenu m6 = new StaffMenu();
				m6.setAuthCode("#");
				m6.setText("子账号管理");
				m6.setParent(m3);
				m3.getChildren().add(m6);
			
		//服务管理		
		StaffMenu m7 = new StaffMenu();
			m7.setAuthCode("ucenter/service_list");
			m7.setText("服务管理");
		
		//买家管理中心		
		StaffMenu m8 = new StaffMenu();
			m8.setAuthCode("javascript:void(0);");
			m8.setText("买家管理中心");
			m8.setLeaf(false);
			
			StaffMenu m9 = new StaffMenu();
				m9.setAuthCode("ucenter/buyer_order");
				m9.setText("订单管理");
				m9.setParent(m8);
				m8.getChildren().add(m9);
			StaffMenu m10 = new StaffMenu();
				m10.setAuthCode("ucenter/buyer_appeal");
				m10.setText("申诉管理");
				m10.setParent(m8);
				m8.getChildren().add(m10);
				
		//卖家管理中心		
		StaffMenu m11 = new StaffMenu();
			m11.setAuthCode("javascript:void(0);");
			m11.setText("卖家管理中心");
			m11.setLeaf(false);
				
			StaffMenu m12 = new StaffMenu();
				m12.setAuthCode("ucenter/seller_order");
				m12.setText("订单管理");
				m12.setParent(m11);
				m11.getChildren().add(m12);
			StaffMenu m13 = new StaffMenu();
				m13.setAuthCode("ucenter/seller_appeal");
				m13.setText("申诉管理");
				m13.setParent(m11);
				m11.getChildren().add(m13);
				
		//招标管理		
		StaffMenu m14 = new StaffMenu();
			m14.setAuthCode("javascript:void(0);");
			m14.setText("招标管理");
			m14.setLeaf(false);
				
			StaffMenu m15 = new StaffMenu();
				m15.setAuthCode("bidding/toAdd");
				m15.setText("招标申请");
				m15.setParent(m14);
				m14.getChildren().add(m15);
			StaffMenu m16 = new StaffMenu();
				m16.setAuthCode("bidding/toDeallist");
				m16.setText("待我处理");
				m16.setParent(m14);
				m14.getChildren().add(m16);
			StaffMenu m17 = new StaffMenu();
				m17.setAuthCode("bidding/toBiddingList");
				m17.setText("我的招标");
				m17.setParent(m14);
				m14.getChildren().add(m17);
				
		//应标管理	
		StaffMenu m18 = new StaffMenu();
			m18.setAuthCode("javascript:void(0);");
			m18.setText("应标管理");
			m18.setLeaf(false);
				
			StaffMenu m19 = new StaffMenu();
				m19.setAuthCode("ucenter/response_push");
				m19.setText("给我推送");
				m19.setParent(m18);
				m18.getChildren().add(m19);
			StaffMenu m20 = new StaffMenu();
				m20.setAuthCode("ucenter/my_response");
				m20.setText("我的应标");
				m20.setParent(m18);
				m18.getChildren().add(m20);
				
		//我的社区
		StaffMenu m21 = new StaffMenu();
			m21.setAuthCode("javascript:void(0);");
			m21.setText("我的社区");
			m21.setLeaf(false);
			
			StaffMenu m22 = new StaffMenu();
				m22.setAuthCode("#");
				m22.setText("社交关系");
				m22.setParent(m21);
				m21.getChildren().add(m22);
			StaffMenu m23 = new StaffMenu();
				m23.setAuthCode("#");
				m23.setText("社区动态");
				m23.setParent(m21);
				m21.getChildren().add(m23);
			StaffMenu m24 = new StaffMenu();
				m24.setAuthCode("#");
				m24.setText("官方应用");
				m24.setParent(m21);
				m21.getChildren().add(m24);
			StaffMenu m25 = new StaffMenu();
				m25.setAuthCode("#");
				m25.setText("社区管理");
				m25.setParent(m21);
				m21.getChildren().add(m25);
				
		//我的百科
		StaffMenu m26 = new StaffMenu();
			m26.setAuthCode("javascript:void(0);");
			m26.setText("我的百科");
		
		//平台服务
		StaffMenu m27 = new StaffMenu();
			m27.setAuthCode("javascript:void(0);");
			m27.setText("平台服务");
		
		//平台消息
		StaffMenu m28 = new StaffMenu();
			m28.setAuthCode("javascript:void(0);");
			m28.setText("平台消息");
		
		//平台帮助
		StaffMenu m29 = new StaffMenu();
			m29.setAuthCode("javascript:void(0);");
			m29.setText("平台帮助");
			
		s.save(m1);
		s.save(m2);
		s.save(m3);
		s.save(m7);
		s.save(m8);
		s.save(m11);
		s.save(m14);
		s.save(m18);
		s.save(m21);
		s.save(m26);
		s.save(m27);
		s.save(m28);
		s.save(m29);
		s.getTransaction().commit();
		
	}
	@Test
	public void testStaffRole(){
		Session s = sessionFactory.openSession();
		s.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<StaffMenu> list = s.createQuery("from StaffMenu").list();
		List<Integer> menuid = new ArrayList<Integer>();
		for(StaffMenu sm:list){
			menuid.add(sm.getId());
		}
		System.out.println(menuid.toString().replaceAll("\\[|\\]", ""));
		
		StaffRole staffRole = new StaffRole();
		staffRole.setRoledesc("你是第一个子账号角色");
		staffRole.setRolename("管理员子账号");
		staffRole.setMenuIds(menuid.toString().replaceAll("\\[|\\]", ""));
		s.save(staffRole);
		
		Staff staff = (Staff) s.load(Staff.class, 65);
		System.out.println(staff);
//		System.out.println(staff.getStaffRole());
		staff.setStaffRole(staffRole);
		s.update(staff);
		s.getTransaction().commit();
		
	}
	
	@Test
	public void testLoad() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<StaffMenu> list = session.createQuery("from StaffMenu").list();
		for(StaffMenu sm:list){
			if(sm.getParent()==null)
				print(sm,0);
		}
		session.getTransaction().commit();
		session.close();
		
	}
void	print(StaffMenu o,int fetch){
		String sj="";
		for(int i=1;i<=fetch;i++){
			sj+="****";
		}
		System.out.println(sj+o.getText());
		for(StaffMenu o1:o.getChildren()){
			print(o1,fetch+1);
		}
	}
	
	
	@Test
	public void Json(){
		List<Menu> list = new ArrayList<Menu>();
		Menu r1 = new Menu();
		r1.setId(1);
		r1.setLeaf(false);
		r1.setCreateTime(new Date());
		r1.setText("权限1");
		r1.setRemark("菜单");
		r1.setPid(0);
		list.add(r1);
		Menu r2 = new Menu();
		r2.setId(2);
		r2.setLeaf(true);
		r2.setCreateTime(new Date());
		r2.setText("权限2");
		r2.setRemark("菜单");
		r2.setPid(1);
		/*List<Menu> l = new ArrayList<Menu>();
		l.add(r2);
		r1.setChildren(l);*/
		list.add(r1);
		list.add(r2);
		
		List<Menu> xxx = new ArrayList<Menu>();
		for(int i = 0 ;i<list.size();i++){
			for(int t =i+1;t<list.size();t++){
				if(list.get(i).getId()==list.get(t).getPid()){
					list.get(i).setLeaf(false);
					if(list.get(i).getChildren()==null){
						List<Menu> tt = new ArrayList<Menu>();
						tt.add(list.get(t));
						list.get(i).setChildren(tt);
					}else{
						list.get(i).getChildren().add(list.get(t));
					}
					xxx.add(list.get(i));
				}
			}
		}
		
		System.out.println(JSON.toJSONString(xxx.toArray()));
	}
	

	@Test
	public void authTest(){
//		Manager u = new Manager();
//		u.setManagername("chens");
//		u.setPassword(MD5.toMD5("chens"));
//		System.out.println(userBiz.findManagerByRoleId(1));
//		System.out.println(userBiz.findManagerByManagername("chens"));
//		System.out.println(userBiz.findManager().get(0).getManagername());
//		System.out.println(roleBiz.queryRole().get(1).getMenu().get(0).getText());
//		System.out.println(JSON.toJSONString(roleBiz.queryRole().toArray(),SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.PrettyFormat));
		//System.out.println(getResult(rightsBiz.queryMenu()).size());
		//System.out.println(JSON.toJSONString(getResult(rightsBiz.queryMenu()),SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.PrettyFormat));
		System.out.println(userBiz.findUserByEnterprise(1).toString());
	}
	
	
	

	public static List<Menu> getResult(List<Menu> src) {
		List<Menu> parents = new ArrayList<Menu>();
//		for (Menu ent : src) {
//			if (!ent.isLeaf()) {
//				Menu result = ent;
//				result.setChildren(new ArrayList<Menu>());
//				parents.add(result);
//			}
//		}
		List<Menu> last = new ArrayList<Menu>();
		for (Menu ent : src) {
			if (ent.getPid()==0||ent.getPid()==1000||ent.getPid()==1001||ent.getPid()==1002||ent.getPid()==1003) {
				ent.setChildren(new ArrayList<Menu>());
				parents.add(ent);
			}else{
				last.add(ent);
			}
		}
		buildTree(parents, last);
		return parents;
	}

	private static void buildTree(List<Menu> parents, List<Menu> others) {
		List<Menu> record = new ArrayList<Menu>();
		for (Iterator<Menu> it = parents.iterator(); it.hasNext();) {
			Menu vi = it.next();
			if (vi.getId() != null) {
				for (Iterator<Menu> otherIt = others.iterator(); otherIt
						.hasNext();) {
					Menu inVi = otherIt.next();
					if (vi.getId().equals(inVi.getPid())) {
						if (null == vi.getChildren()) {
							vi.setChildren(new ArrayList<Menu>());
						}
						vi.getChildren().add(inVi);
						record.add(inVi);
						otherIt.remove();
					}
				}
			}
		}
		if (others.size() == 0) {
			return;
		} else {
			buildTree(record, others);
		}
	}
	
	public void oauthCodeAndOpenID(){
	}
}
