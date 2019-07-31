package ersha.qq1508444061.youyi.Event;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import ersha.qq1508444061.youyi.Main;
import ersha.qq1508444061.youyi.Util.Economys;
import ersha.qq1508444061.youyi.Util.Points;

public class Youyi_ck implements Listener {
	
	public static HashMap<String, Boolean> c_money = new HashMap<>();
	public static HashMap<String, Boolean> q_money = new HashMap<>();
	public static HashMap<String, Boolean> c_points = new HashMap<>();
	public static HashMap<String, Boolean> q_points = new HashMap<>();
	
	@EventHandler
    public void InventoryCloseEvent(InventoryCloseEvent e) {
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
            if(e.getView().getTitle() != null && e.getView().getTitle().equals(Main.getInstance().getConfig().getString("Warehouse.Name").replace("&", "§"))) {
                    Player p = (Player)e.getPlayer();
                    
                    File pcFile = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/仓库数据.yml");
                    YamlConfiguration ck = YamlConfiguration.loadConfiguration(pcFile);
                    ck.set(sj.get(p.getName().toLowerCase())+"", "");
                    
                    int size = Main.getInstance().getConfig().getInt("Warehouse.size");

                    for(int slotinv = 0; slotinv < size; ++slotinv) {
                            if(e.getInventory().getItem(slotinv) != null) {
                            	ck.set(sj.get(p.getName().toLowerCase()) + "." + slotinv, e.getInventory().getItem(slotinv));
                            }
                    }

                    try {
                            ck.save(pcFile);
                    } catch (IOException var8) {
                            var8.printStackTrace();
                    }

            }
    }
	
	@EventHandler
	public final void onInventoryClick(InventoryClickEvent e){
		Player p = (Player)e.getWhoClicked();
		if (e.getWhoClicked() instanceof Player){
		if(e.getView().getTitle() != null && e.getView().getTitle().equals(Main.getInstance().getConfig().getString("Warehouse.Name").replace("&", "§"))) {
			if(e.getCurrentItem() == null)
				return;
			if(e.getCurrentItem().getType() == Material.AIR  || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName())
				return;
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§l存储 §e§l金币  §7(§a左键存入 §3右键取出§7)")){
				if (e.getClick() == ClickType.NUMBER_KEY){
					e.setCancelled(true);
					return;
				}
				if (e.getClick() == ClickType.SHIFT_LEFT){
					e.setCancelled(true);
					return;
			    }
			    if (e.getClick() == ClickType.SHIFT_RIGHT){
					e.setCancelled(true);
					return;
			    }
				if (e.getClick() == ClickType.LEFT){
					p.sendMessage("§f友谊仓库 §b> §3请在接下来的 §c10 §3秒内输入所要§6存入§3的§e金币数量");
					c_money.put(p.getName().toLowerCase(), Boolean.valueOf(true));
					e.setCancelled(true);
					p.closeInventory();
					new BukkitRunnable() {
						@Override
						public void run() {
							if (p.isOnline()){
								if (c_money.get(p.getName().toLowerCase()) == true){
									c_money.put(p.getName().toLowerCase(), Boolean.valueOf(false));
								}
							}
						}
					}.runTaskLater(Main.getInstance(), 200);
					return;
				}
				if (e.getClick() == ClickType.RIGHT){
					p.sendMessage("§f友谊仓库 §b> §3请在接下来的 §c10 §3秒内输入所要§6取出§3的§e金币数量");
					q_money.put(p.getName().toLowerCase(), Boolean.valueOf(true));
					e.setCancelled(true);
					p.closeInventory();
					new BukkitRunnable() {
						@Override
						public void run() {
							if (p.isOnline()){
								if (q_money.get(p.getName().toLowerCase()) == true){
									q_money.put(p.getName().toLowerCase(), Boolean.valueOf(false));
								}
							}
						}
					}.runTaskLater(Main.getInstance(), 200);
					return;
				}
				return;
			}
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§l存储 §b§l点卷  §7(§a左键存入 §3右键取出§7)")){
				if (e.getClick() == ClickType.NUMBER_KEY){
					e.setCancelled(true);
					return;
				}
				if (e.getClick() == ClickType.SHIFT_LEFT){
					e.setCancelled(true);
					return;
			    }
			    if (e.getClick() == ClickType.SHIFT_RIGHT){
					e.setCancelled(true);
					return;
			    }
				if (e.getClick() == ClickType.LEFT){
					p.sendMessage("§f友谊仓库 §b> §3请在接下来的 §c10 §3秒内输入所要§6存入§3的§b点卷数量");
					c_points.put(p.getName().toLowerCase(), Boolean.valueOf(true));
					e.setCancelled(true);
					p.closeInventory();
					new BukkitRunnable() {
						@Override
						public void run() {
							if (p.isOnline()){
								if (c_points.get(p.getName().toLowerCase()) == true){
									c_points.put(p.getName().toLowerCase(), Boolean.valueOf(false));
								}
							}
						}
					}.runTaskLater(Main.getInstance(), 200);
					return;
				}
				if (e.getClick() == ClickType.RIGHT){
					p.sendMessage("§f友谊仓库 §b> §3请在接下来的 §c10 §3秒内输入所要§6取出§3的§b点卷数量");
					q_points.put(p.getName().toLowerCase(), Boolean.valueOf(true));
					e.setCancelled(true);
					p.closeInventory();
					new BukkitRunnable() {
						@Override
						public void run() {
							if (p.isOnline()){
								if (q_points.get(p.getName().toLowerCase()) == true){
									q_points.put(p.getName().toLowerCase(), Boolean.valueOf(false));
								}
							}
						}
					}.runTaskLater(Main.getInstance(), 200);
					return;
				}
				return;
			}
		}
		}
	}
	
	@EventHandler
	public void onVaultChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String msg = e.getMessage().trim();
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/共享财产数据.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);
		
		if (c_money.get(p.getName().toLowerCase()) == true){
			c_money.put(p.getName().toLowerCase(), Boolean.valueOf(false));
			e.setCancelled(true);
			
			if (Integer.valueOf(msg) < 1){
				p.sendMessage("§f友谊仓库 §b> §c金额小于 0 无法存入");
				return;
			}
			if (Economys.econ.getBalance(p) < Integer.valueOf(msg)){
				p.sendMessage("§f友谊仓库 §b> §c你没有那么多金币");
				return;
			}
			
			if (PLAYERDATA_CC.get("仓库存放金币数量") == null){
			PLAYERDATA_CC.set("仓库存放金币数量", Integer.valueOf(msg));
			}else{
			PLAYERDATA_CC.set("仓库存放金币数量", Integer.valueOf(PLAYERDATA_CC.getInt("仓库存放金币数量")+Integer.valueOf(msg)));
			}
			
			try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
			
			Economys.paymoney(p, Integer.valueOf(msg));
			p.sendMessage("§f友谊仓库 §b> §a成功存入金币数量 §c"+ Integer.valueOf(msg) +" §a目前仓库储存金币数量 §c"+PLAYERDATA_CC.getInt("仓库存放金币数量"));
		}
		
		if (q_money.get(p.getName().toLowerCase()) == true){
			q_money.put(p.getName().toLowerCase(), Boolean.valueOf(false));
			e.setCancelled(true);
			
			if (Integer.valueOf(msg) < 1){
				p.sendMessage("§f友谊仓库 §b> §c金额小于 0 无法取入");
				return;
			}
			if (PLAYERDATA_CC.get("仓库存放金币数量") == null){
				p.sendMessage("§f友谊仓库 §b> §c仓库内没有储存金币");
				return;
			}
			if (PLAYERDATA_CC.getInt("仓库存放金币数量") < Integer.valueOf(msg)){
				p.sendMessage("§f友谊仓库 §b> §c仓库内没有储存那么多的金币,仓库剩余金币 §c"+PLAYERDATA_CC.getInt("仓库存放金币数量"));
				return;
			}
			
			PLAYERDATA_CC.set("仓库存放金币数量", Integer.valueOf(PLAYERDATA_CC.getInt("仓库存放金币数量")-Integer.valueOf(msg)));
			
			try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
			
			Economys.econ.depositPlayer(p, Integer.valueOf(msg));
			p.sendMessage("§f友谊仓库 §b> §a成功取出金币数量 §c"+ Integer.valueOf(msg) +" §a目前仓库剩余储存金币数量 §c"+PLAYERDATA_CC.getInt("仓库存放金币数量"));
		}
	}
	
	
	@EventHandler
	public void onPointsChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String msg = e.getMessage().trim();
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/共享财产数据.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);
		
		if (c_points.get(p.getName().toLowerCase()) == true){
			c_points.put(p.getName().toLowerCase(), Boolean.valueOf(false));
			e.setCancelled(true);
			
			if (Integer.valueOf(msg) < 1){
				p.sendMessage("§f友谊仓库 §b> §c金额小于 0 无法存入");
				return;
			}
			if (Points.points.getAPI().look(p.getUniqueId()) < Integer.valueOf(msg)){
				p.sendMessage("§f友谊仓库 §b> §c你没有那么多点卷");
				return;
			}
			
			if (PLAYERDATA_CC.get("仓库存放点卷数量") == null){
			PLAYERDATA_CC.set("仓库存放点卷数量", Integer.valueOf(msg));
			}else{
			PLAYERDATA_CC.set("仓库存放点卷数量", Integer.valueOf(PLAYERDATA_CC.getInt("仓库存放点卷数量")+Integer.valueOf(msg)));
			}
			
			try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
			
			Points.points.getAPI().take(p.getName().toLowerCase(), Integer.valueOf(msg));
			p.sendMessage("§f友谊仓库 §b> §a成功存入点卷数量 §c"+ Integer.valueOf(msg) +" §a目前仓库储存点卷数量 §c"+PLAYERDATA_CC.getInt("仓库存放点卷数量"));
		}
		
		if (q_points.get(p.getName().toLowerCase()) == true){
			q_points.put(p.getName().toLowerCase(), Boolean.valueOf(false));
			e.setCancelled(true);
			
			if (Integer.valueOf(msg) < 1){
				p.sendMessage("§f友谊仓库 §b> §c金额小于 0 无法取入");
				return;
			}
			if (PLAYERDATA_CC.get("仓库存放点卷数量") == null){
				p.sendMessage("§f友谊仓库 §b> §c仓库内没有储存金币");
				return;
			}
			if (PLAYERDATA_CC.getInt("仓库存放点卷数量") < Integer.valueOf(msg)){
				p.sendMessage("§f友谊仓库 §b> §c仓库内没有储存那么多的金币,仓库剩余金币 §c"+PLAYERDATA_CC.getInt("仓库存放点卷数量"));
				return;
			}
			
			PLAYERDATA_CC.set("仓库存放点卷数量", Integer.valueOf(PLAYERDATA_CC.getInt("仓库存放点卷数量")-Integer.valueOf(msg)));
			
			try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
			
			Points.points.getAPI().give(p.getName().toLowerCase(), Integer.valueOf(msg));
			p.sendMessage("§f友谊仓库 §b> §a成功取出点卷数量 §c"+ Integer.valueOf(msg) +" §a目前仓库剩余储存点卷数量 §c"+PLAYERDATA_CC.getInt("仓库存放点卷数量"));
		}
	}
}
