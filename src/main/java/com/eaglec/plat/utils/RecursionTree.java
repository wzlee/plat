package com.eaglec.plat.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.eaglec.plat.domain.auth.Menu;

public class RecursionTree {
	//递归树方法
	public static List<Menu> getResult(List<Menu> src) {
		List<Menu> parents = new ArrayList<Menu>();
		List<Menu> last = new ArrayList<Menu>();
		for (Menu ent : src) {
			if (ent.getPid()==1000||ent.getPid()==1001||ent.getPid()==1002||ent.getPid()==1003 ||
					ent.getPid()==1004||ent.getPid()==1005) {
				ent.setChildren(new ArrayList<Menu>());
				parents.add(ent);
			}else{
				last.add(ent);
			}
		}
		buildTree(parents, last);
		return parents;
	}
		
	//主要用于递归
	public static void buildTree(List<Menu> parents, List<Menu> others) {
		List<Menu> record = new ArrayList<Menu>();
		for (Iterator<Menu> it = parents.iterator(); it.hasNext();) {
			Menu vi = it.next();
			if (vi.getId() != null) {
				for (Iterator<Menu> otherIt = others.iterator(); otherIt.hasNext();) {
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
}
