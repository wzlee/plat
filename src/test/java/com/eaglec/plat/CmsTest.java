package com.eaglec.plat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eaglec.plat.biz.cms.ChannelBiz;
import com.eaglec.plat.biz.cms.ModuleBiz;
import com.eaglec.plat.biz.cms.ModuleTemplateBiz;
import com.eaglec.plat.biz.publik.CategoryBiz;
import com.eaglec.plat.domain.base.Category;
import com.eaglec.plat.domain.cms.Channel;
import com.eaglec.plat.domain.cms.ModuleTemplate;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(
	locations={
        "file:src/main/webapp/WEB-INF/spring/*.xml", 
        "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml"
    }
)
public class CmsTest {
	
	@Autowired
	private ModuleBiz moduleBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private ModuleTemplateBiz moduleTemplateBiz;
	
	@Autowired
	private CategoryBiz categoryBiz;
	
	@Test
	public void serviceTest(){
		List<ModuleTemplate> mt = moduleTemplateBiz.findAll();
		System.out.println(mt);
	}
	
	@Test
	public void addChannel(){
		Channel c = new Channel();
		c.setCdesc("描述");
		c.setMtcode("template-1");
	}

	@Test
	public void findAllServerParent(){
		StringBuffer html = new StringBuffer();
		List<Category> c = (List<Category>) categoryBiz.findCategoryParent();
		for (Category cy : c) {
			html.append("<li id=\""+ cy.getId() +"\" pid=\""+ cy.getPid() +"\">");
			html.append("<div class=\"name-wrap\">");
			html.append("<h4 class=\"\">");
			html.append("<a href=\"\">"+ cy.getText() +"</a>");
			html.append("</h4>");
			html.append("</div></li>");
		}
		System.out.println(html);
	}
	
	@Test
	public void findChnnelByCtype(){
		try {
			List<Channel> c = channelBiz.findChnnelByCtype(1);
			for (Channel cl : c) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findCategoryChildren(){
		try {
			List<Category> c = categoryBiz.findCategoryChildren(106);
			for (Category cy : c) {
				System.out.println(cy.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void replaceAll(){
		String html = "<div id=\"titiles\" class=\"heads\" style=\"padding:2px;\"><ul width='999'>sssss</ul>aaaa</div>";
		html = html.replaceAll("<[^>]+>", "");
		System.out.println(html);
		
	}
}
