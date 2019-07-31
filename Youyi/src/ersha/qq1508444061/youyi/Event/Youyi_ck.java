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
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
            if(e.getView().getTitle() != null && e.getView().getTitle().equals(Main.getInstance().getConfig().getString("Warehouse.Name").replace("&", "��"))) {
                    Player p = (Player)e.getPlayer();
                    
                    File pcFile = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/�ֿ�����.yml");
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
		if(e.getView().getTitle() != null && e.getView().getTitle().equals(Main.getInstance().getConfig().getString("Warehouse.Name").replace("&", "��"))) {
			if(e.getCurrentItem() == null)
				return;
			if(e.getCurrentItem().getType() == Material.AIR  || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName())
				return;
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("��6��l�洢 ��e��l���  ��7(��a������� ��3�Ҽ�ȡ����7)")){
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
					p.sendMessage("��f����ֿ� ��b> ��3���ڽ������� ��c10 ��3����������Ҫ��6�����3�ġ�e�������");
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
					p.sendMessage("��f����ֿ� ��b> ��3���ڽ������� ��c10 ��3����������Ҫ��6ȡ����3�ġ�e�������");
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
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("��6��l�洢 ��b��l���  ��7(��a������� ��3�Ҽ�ȡ����7)")){
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
					p.sendMessage("��f����ֿ� ��b> ��3���ڽ������� ��c10 ��3����������Ҫ��6�����3�ġ�b�������");
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
					p.sendMessage("��f����ֿ� ��b> ��3���ڽ������� ��c10 ��3����������Ҫ��6ȡ����3�ġ�b�������");
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
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/����Ʋ�����.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);
		
		if (c_money.get(p.getName().toLowerCase()) == true){
			c_money.put(p.getName().toLowerCase(), Boolean.valueOf(false));
			e.setCancelled(true);
			
			if (Integer.valueOf(msg) < 1){
				p.sendMessage("��f����ֿ� ��b> ��c���С�� 0 �޷�����");
				return;
			}
			if (Economys.econ.getBalance(p) < Integer.valueOf(msg)){
				p.sendMessage("��f����ֿ� ��b> ��c��û����ô����");
				return;
			}
			
			if (PLAYERDATA_CC.get("�ֿ��Ž������") == null){
			PLAYERDATA_CC.set("�ֿ��Ž������", Integer.valueOf(msg));
			}else{
			PLAYERDATA_CC.set("�ֿ��Ž������", Integer.valueOf(PLAYERDATA_CC.getInt("�ֿ��Ž������")+Integer.valueOf(msg)));
			}
			
			try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
			
			Economys.paymoney(p, Integer.valueOf(msg));
			p.sendMessage("��f����ֿ� ��b> ��a�ɹ����������� ��c"+ Integer.valueOf(msg) +" ��aĿǰ�ֿⴢ�������� ��c"+PLAYERDATA_CC.getInt("�ֿ��Ž������"));
		}
		
		if (q_money.get(p.getName().toLowerCase()) == true){
			q_money.put(p.getName().toLowerCase(), Boolean.valueOf(false));
			e.setCancelled(true);
			
			if (Integer.valueOf(msg) < 1){
				p.sendMessage("��f����ֿ� ��b> ��c���С�� 0 �޷�ȡ��");
				return;
			}
			if (PLAYERDATA_CC.get("�ֿ��Ž������") == null){
				p.sendMessage("��f����ֿ� ��b> ��c�ֿ���û�д�����");
				return;
			}
			if (PLAYERDATA_CC.getInt("�ֿ��Ž������") < Integer.valueOf(msg)){
				p.sendMessage("��f����ֿ� ��b> ��c�ֿ���û�д�����ô��Ľ��,�ֿ�ʣ���� ��c"+PLAYERDATA_CC.getInt("�ֿ��Ž������"));
				return;
			}
			
			PLAYERDATA_CC.set("�ֿ��Ž������", Integer.valueOf(PLAYERDATA_CC.getInt("�ֿ��Ž������")-Integer.valueOf(msg)));
			
			try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
			
			Economys.econ.depositPlayer(p, Integer.valueOf(msg));
			p.sendMessage("��f����ֿ� ��b> ��a�ɹ�ȡ��������� ��c"+ Integer.valueOf(msg) +" ��aĿǰ�ֿ�ʣ�ഢ�������� ��c"+PLAYERDATA_CC.getInt("�ֿ��Ž������"));
		}
	}
	
	
	@EventHandler
	public void onPointsChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String msg = e.getMessage().trim();
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/����Ʋ�����.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);
		
		if (c_points.get(p.getName().toLowerCase()) == true){
			c_points.put(p.getName().toLowerCase(), Boolean.valueOf(false));
			e.setCancelled(true);
			
			if (Integer.valueOf(msg) < 1){
				p.sendMessage("��f����ֿ� ��b> ��c���С�� 0 �޷�����");
				return;
			}
			if (Points.points.getAPI().look(p.getUniqueId()) < Integer.valueOf(msg)){
				p.sendMessage("��f����ֿ� ��b> ��c��û����ô����");
				return;
			}
			
			if (PLAYERDATA_CC.get("�ֿ��ŵ������") == null){
			PLAYERDATA_CC.set("�ֿ��ŵ������", Integer.valueOf(msg));
			}else{
			PLAYERDATA_CC.set("�ֿ��ŵ������", Integer.valueOf(PLAYERDATA_CC.getInt("�ֿ��ŵ������")+Integer.valueOf(msg)));
			}
			
			try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
			
			Points.points.getAPI().take(p.getName().toLowerCase(), Integer.valueOf(msg));
			p.sendMessage("��f����ֿ� ��b> ��a�ɹ����������� ��c"+ Integer.valueOf(msg) +" ��aĿǰ�ֿⴢ�������� ��c"+PLAYERDATA_CC.getInt("�ֿ��ŵ������"));
		}
		
		if (q_points.get(p.getName().toLowerCase()) == true){
			q_points.put(p.getName().toLowerCase(), Boolean.valueOf(false));
			e.setCancelled(true);
			
			if (Integer.valueOf(msg) < 1){
				p.sendMessage("��f����ֿ� ��b> ��c���С�� 0 �޷�ȡ��");
				return;
			}
			if (PLAYERDATA_CC.get("�ֿ��ŵ������") == null){
				p.sendMessage("��f����ֿ� ��b> ��c�ֿ���û�д�����");
				return;
			}
			if (PLAYERDATA_CC.getInt("�ֿ��ŵ������") < Integer.valueOf(msg)){
				p.sendMessage("��f����ֿ� ��b> ��c�ֿ���û�д�����ô��Ľ��,�ֿ�ʣ���� ��c"+PLAYERDATA_CC.getInt("�ֿ��ŵ������"));
				return;
			}
			
			PLAYERDATA_CC.set("�ֿ��ŵ������", Integer.valueOf(PLAYERDATA_CC.getInt("�ֿ��ŵ������")-Integer.valueOf(msg)));
			
			try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
			
			Points.points.getAPI().give(p.getName().toLowerCase(), Integer.valueOf(msg));
			p.sendMessage("��f����ֿ� ��b> ��a�ɹ�ȡ��������� ��c"+ Integer.valueOf(msg) +" ��aĿǰ�ֿ�ʣ�ഢ�������� ��c"+PLAYERDATA_CC.getInt("�ֿ��ŵ������"));
		}
	}
}
