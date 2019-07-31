package ersha.qq1508444061.youyi.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ersha.qq1508444061.youyi.Main;

public class Info implements Listener {

	@EventHandler
	public final void onInventoryClick(InventoryClickEvent e){
		Player p = (Player)e.getWhoClicked();
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
		
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/小船数据.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
		
		if (e.getWhoClicked() instanceof Player){
		if(e.getView().getTitle() != null && e.getView().getTitle().equals(Main.getInstance().getConfig().getString("Set.Info.Name").replace("&", "§"))) {
			if(e.getCurrentItem() == null)
				return;
			if(e.getCurrentItem().getType() == Material.AIR  || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName())
				return;
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.getInstance().getConfig().getString("Set.Info.YouyiValue.Name").replace("&", "§"))){
				if (e.getClick() == ClickType.NUMBER_KEY){
					e.setCancelled(true);
					return;
				}
				if (e.getClick() == ClickType.LEFT){
					Inventory inv = Bukkit.createInventory((InventoryHolder)null, 18, Main.jls.getString("Interface.Name").replace("&", "§"));

					if (Main.jls.get("Reward.0") != null)
					inv.setItem(0, item(p, "0"));
					if (Main.jls.get("Reward.1") != null)
					inv.setItem(1, item(p, "1"));
					if (Main.jls.get("Reward.2") != null)
					inv.setItem(2, item(p, "2"));
					if (Main.jls.get("Reward.3") != null)
					inv.setItem(3, item(p, "3"));
					if (Main.jls.get("Reward.4") != null)
					inv.setItem(4, item(p, "4"));
					if (Main.jls.get("Reward.5") != null)
					inv.setItem(5, item(p, "5"));
					if (Main.jls.get("Reward.6") != null)
					inv.setItem(6, item(p, "6"));
					if (Main.jls.get("Reward.7") != null)
					inv.setItem(7, item(p, "7"));
					if (Main.jls.get("Reward.8") != null)
					inv.setItem(8, item(p, "8"));
					if (Main.jls.get("Reward.9") != null)
					inv.setItem(8, item(p, "9"));
					if (Main.jls.get("Reward.10") != null)
					inv.setItem(8, item(p, "10"));
					if (Main.jls.get("Reward.11") != null)
					inv.setItem(8, item(p, "11"));
					if (Main.jls.get("Reward.12") != null)
					inv.setItem(8, item(p, "12"));
					if (Main.jls.get("Reward.13") != null)
					inv.setItem(8, item(p, "13"));
					if (Main.jls.get("Reward.14") != null)
					inv.setItem(8, item(p, "14"));
					if (Main.jls.get("Reward.15") != null)
					inv.setItem(8, item(p, "15"));
					if (Main.jls.get("Reward.16") != null)
					inv.setItem(8, item(p, "16"));
					if (Main.jls.get("Reward.17") != null)
					inv.setItem(8, item(p, "17"));
					
							
					p.closeInventory();
					p.openInventory(inv);
					e.setCancelled(true);
					return;
			    }
			  }
			if (e.getClick() == ClickType.NUMBER_KEY){
				e.setCancelled(true);
				return;
		    }
		    if (e.getClick() == ClickType.SHIFT_LEFT){
				e.setCancelled(true);
				return;
		    }
		    if (e.getClick() == ClickType.RIGHT){
				e.setCancelled(true);
				return;
		    }
		    if (e.getClick() == ClickType.LEFT){
				e.setCancelled(true);
				return;
		    }
		    if (e.getClick() == ClickType.SHIFT_RIGHT){
				e.setCancelled(true);
				return;
		    }
			}
		    }
		}
	
	
	public ItemStack item(Player p,String reward){
			
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
		
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/小船数据.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
	    
	    File jls = new File(Main.getInstance().getDataFolder() + "/友谊值奖励-领取记录.yml");
	    YamlConfiguration jl = YamlConfiguration.loadConfiguration(jls);

			int ids = Main.jls.getInt("Reward."+reward+".Id");
			int vv = Main.jls.getInt("Reward."+reward+".Value");
			
			ItemStack item = new ItemStack(Material.getMaterial(ids), 1, (short) 0);
            ItemMeta id = item.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			id.setDisplayName(Main.jls.getString("Reward."+reward+".Name").replace("&", "§"));
			lore = new ArrayList<String>();
			for(String line:Main.jls.getStringList("Reward."+reward+".Lore")){
				lore.add(line
						.replace("%player%", PLAYERDATA.getString(p.getName().toLowerCase()))
						.replace("%value%", PLAYERDATA.getInt("YouyiValue")+"")
						.replace("%is_value%", vv+"")
						.replace("%dc_value%", PLAYERDATA.getInt("YouyiValue") >= vv ? "§a§l已达成" : "§7§l未达成")
						.replace("%is_lq%", jl.getStringList("List."+reward).contains(p.getName().toLowerCase()) ? "§a§l已领取过" : "§c§l未领取过")
						.replace("&", "§")
						);
			}
			id.setLore(lore);
			item.setItemMeta(id);
			
			return item;
	}
}
