package ersha.qq1508444061.youyi.Event;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import ersha.qq1508444061.youyi.Main;

public class Youyi_value {

	
	public static void addYouyiValue(Player p, Integer i){
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/小船数据.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
	    
	    String name = PLAYERDATA.getString(p.getName().toLowerCase());
	    
	    if (PLAYERDATA.get("YouyiValue") == null){
	    	PLAYERDATA.set("YouyiValue", i);
	    	p.sendMessage("§f友谊 §b> §f友谊值提升了 §c"+i+" §f点,目前你与 §c"+name+" §f的友谊值为 §c"+i);
	    	Bukkit.getPlayer(name).sendMessage("§f友谊 §b> §f友谊值提升了 §c"+i+" §f点,目前你与 §c"+name+" §f的友谊值为 §c"+i);
	    }else{
	    	PLAYERDATA.set("YouyiValue", Integer.valueOf(PLAYERDATA.getInt("YouyiValue"))+i);
	    	int value = PLAYERDATA.getInt("YouyiValue");
	    	p.sendMessage("§f友谊 §b> §f友谊值提升了 §c"+i+" §f点,目前你与 §c"+name+" §f的友谊值为 §c"+value);
	    	Bukkit.getPlayer(name).sendMessage("§f友谊 §b> §f友谊值提升了 §c"+i+" §f点,目前你与 §c"+name+" §f的友谊值为 §c"+value);
	    }
	    
	    try {
	    	PLAYERDATA.save(FS);
        } catch (IOException var8) {
            var8.printStackTrace();
        }
	}
	
	
}
