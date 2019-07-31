package com.FBinggun.QH;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ls  implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler
	public void qh1(PlayerInteractEvent e){
/*		if(e.getHand()!=EquipmentSlot.OFF_HAND){
			return;
		}*/
		FZQianghuaAPI api =new FZQianghuaAPI();
		Player p =e.getPlayer();
		if(e.getAction()!=Action.LEFT_CLICK_AIR){
			return;
		}
		ItemStack wq=e.getPlayer().getInventory().getItemInMainHand();
		ItemStack qhs=e.getPlayer().getInventory().getItemInOffHand();
		int level =api.getlevel(wq);
		if(!api.isstone(qhs)){
			return;
		}
		if(qhs==null){
			return;
		}
		if(wq==null){
			p.sendMessage(Lang.szwkqhwp);
			p.sendTitle(Lang.szwkqhwp,null);
			return;
		}
		if(!api.isstone(qhs)){
			return;
		}
		if(!api.istype(wq)){
			p.sendMessage(Lang.szwkqhwp);
			p.sendTitle(Lang.szwkqhwp,null);
			return;
		}
		if(!api.ismax(wq, qhs)){
			p.sendMessage(Lang.max);
			p.sendTitle(Lang.max,null);
			return;
		}
		
		 qhs.setAmount(qhs.getAmount()-1);
		qhs qh =new qhs();
		
		if(qh.chance(api.getchance(wq, qhs))){
			qh.cleanlore(wq);
			qh.setlevel(wq, level+1);
			p.sendMessage(Lang.qhcg+(level+1));
			p.sendTitle(Lang.qhcg+(level+1),null);
			return;
		}else {
			if(qh.chance(api.getbroken(qhs))){
				qh.cleanlore(wq);
				wq.setAmount(0);
				p.sendMessage(Lang.ps);
				p.sendTitle(Lang.ps,null);
				return;
			}
			qh.cleanlore(wq);
			int x =level-api.getfail(qhs);
			if(x<=0){
				qh.setlevel(wq, 1);
				p.sendMessage(Lang.qhsb+1);
				p.sendTitle(Lang.qhsb+1,null);
				return;
			}
			qh.cleanlore(wq);
			qh.setlevel(wq, x);
			p.sendMessage(Lang.qhsb+x);
			p.sendTitle(Lang.qhsb+x,null);
			return;
		}
		
	}

	
	
}
