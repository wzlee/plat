package com.eaglec.plat.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.eaglec.plat.domain.base.StaffMenu;

public class StaffTree {
	
	//递归树方法
	public static  List<StaffMenu> getResult(List<StaffMenu> src) {
		List<StaffMenu> parents = new ArrayList<StaffMenu>();
		List<StaffMenu> last = new ArrayList<StaffMenu>();
		for (StaffMenu ent : src) {
			if (ent.getParent()==null) {
				ent.setChildren(new ArrayList<StaffMenu>());
				parents.add(ent);
			}else{
				last.add(ent);
			}
		}
		buildTree(parents, last);
		return parents;
	}
		
	//主要用于递归
	public static void buildTree(List<StaffMenu> parents, List<StaffMenu> others) {
		List<StaffMenu> record = new ArrayList<StaffMenu>();
		for (Iterator<StaffMenu> it = parents.iterator(); it.hasNext();) {
			StaffMenu vi = it.next();
			if (vi.getId() != null) {
				for (Iterator<StaffMenu> otherIt = others.iterator(); otherIt.hasNext();) {
					StaffMenu inVi = otherIt.next();
					if (vi.getId().equals(inVi.getParent().getId())) {
						if (null == vi.getChildren()) {
							vi.setChildren(new ArrayList<StaffMenu>());
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
