package ersha.qq1508444061.youyi.Event;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import ersha.qq1508444061.youyi.Main;

public class Youyi_Reward implements Listener {

	@EventHandler
	public final void onInventoryClick(InventoryClickEvent e){
		Player p = (Player)e.getWhoClicked();
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
		
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/С������.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
		
	    File jls = new File(Main.getInstance().getDataFolder() + "/����ֵ����-��ȡ��¼.yml");
	    YamlConfiguration jl = YamlConfiguration.loadConfiguration(jls);
	    
	    Iterator var6 = Main.jls.getConfigurationSection("Reward").getKeys(false).iterator();
        while(var6.hasNext()) {
        String reward = (String)var6.next();
	    
		if (e.getWhoClicked() instanceof Player){
		if(e.getView().getTitle() != null && e.getView().getTitle().equals(Main.jls.getString("Interface.Name").replace("&", "��"))) {
			if(e.getCurrentItem() == null)
				return;
			if(e.getCurrentItem().getType() == Material.AIR  || e.getCurrentItem().getType() == null || !e.getCurrentItem().hasItemMeta() || !e.getCurrentItem().getItemMeta().hasDisplayName())
				return;
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals(Main.jls.getString("Reward."+reward+".Name").replace("&", "��"))){
				if (e.getClick() == ClickType.NUMBER_KEY){
					e.setCancelled(true);
					return;
				}
			    if (e.getClick() == ClickType.LEFT){
			    	e.setCancelled(true);
			    	String name = Main.jls.getString("Reward."+reward+".Name").replace("&", "��");
			    	int value = Main.jls.getInt("Reward."+reward+".Value");
			    	
			    	if (PLAYERDATA.getInt("YouyiValue") < value){
                    	p.sendMessage("��f���� ��b> ��c��ȡʧ��,��δ�ﵽ��ȡ����");
                    	p.closeInventory();
                    	return;
			    	}
			    	
                    if (jl.getStringList("List."+reward).contains(p.getName().toLowerCase())){
                    	p.sendMessage("��f���� ��b> ��c��ȡʧ��,ÿ���˺Ž�����ȡһ��");
                    	p.closeInventory();
                    	return;
                    }
					
			    	for (String cmd : Main.jls.getStringList("Reward."+reward+".Commands"))
					if (p.isOp()){
					p.chat("/"+cmd.replace("%player%", p.getName()).replace("&", "��"));
					}else{
					p.setOp(true);
					p.chat("/"+cmd.replace("%player%", p.getName()).replace("&", "��"));
					p.setOp(false);
					}
					
			    	p.sendMessage("��f���� ��b> ��a��� ��f"+name+" ��a��ȡ�ɹ�");
			    	
			    	Object list = jl.getStringList("List");
					((List)list).add(p.getName().toLowerCase());
					jl.set("List."+reward, list);
			    	
					
					try {
						jl.save(jls);
					} catch (IOException es) {
					  es.printStackTrace();
					}
					
					p.closeInventory();
					return;
			    }
			  }
			if (e.getClick() == ClickType.RIGHT){
				e.setCancelled(true);
				return;
			}
		   }
		}
		}
	}
}
