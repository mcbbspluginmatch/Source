package com.FBinggun.QH;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class qhs extends FZQianghuaAPI{

	
	public void  cleanlore(ItemStack item){
		ItemMeta mm =item.getItemMeta();
		List<String> a= mm.getLore();
		if(a==null){
			return;
		}
		int level =getlevel(item);
		cg cc = new cg();
		FileConfiguration c =cc.cs();
		String file ="level."+level+"."+gettype(item)+".lore";
		List<String> lore =c.getStringList(file);
		a.removeAll(lore);
		mm.setLore(a);
		item.setItemMeta(mm);
	}
	public void setlevel(ItemStack item,int level){
		ItemMeta mm =item.getItemMeta();
		List<String> a= mm.getLore();
		cg cc = new cg();
		FileConfiguration c =cc.cs();
		String file ="level."+level+"."+gettype(item)+".lore";
		if(a==null){
			ItemMeta m=	item.getItemMeta();
			m.setLore(c.getStringList(file));	
			item.setItemMeta(m);
		}else {
			
			a.addAll(c.getStringList(file));
			ItemMeta m=	item.getItemMeta();
			m.setLore(a);	
			item.setItemMeta(m);	
		}

	}
	
	
	public boolean chance(Double c){
		
    double rand = Math.random();
      if (c>rand*100) {
    	  return true;
      }
	return false;

	}
}
