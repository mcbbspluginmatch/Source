package ersha.qq1508444061.youyi.Event;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import ersha.qq1508444061.youyi.Main;

public class Youyi_Chat implements Listener {
	
	public static HashMap<String, Boolean>  youyichat = new HashMap<>();
	
	@EventHandler
	public void onYouyiChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String msg = e.getMessage().trim();
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/小船数据.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);
		
	    File FSa = new File(Main.getInstance().getDataFolder() + "/data/all_data.yml");
	    YamlConfiguration svaule = YamlConfiguration.loadConfiguration(FSa);
	    
		if (youyichat.get(p.getName().toLowerCase()) != false){
			e.setCancelled(true);
			
			String name = PLAYERDATA_CC.getString(p.getName().toLowerCase());
			
            if (!Bukkit.getOfflinePlayer(name).isOnline()){
            	p.sendMessage("§f小船聊天模式 §b> §c"+name+" 目前不在线,收不到你的消息");
            }
            
            p.sendMessage("§f小船聊天模式 §b>  §f"+p.getName()+" §8§l: §f"+msg);
            Bukkit.getPlayer(name).sendMessage("§f小船聊天模式 §b>  §f"+p.getName()+" §8§l: §f"+msg);

		}
	}
}
