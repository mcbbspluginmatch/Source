package ersha.qq1508444061.youyi.Event;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import ersha.qq1508444061.youyi.Main;

public class Youyi_value {

	
	public static void addYouyiValue(Player p, Integer i){
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/С������.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
	    
	    String name = PLAYERDATA.getString(p.getName().toLowerCase());
	    
	    if (PLAYERDATA.get("YouyiValue") == null){
	    	PLAYERDATA.set("YouyiValue", i);
	    	p.sendMessage("��f���� ��b> ��f����ֵ������ ��c"+i+" ��f��,Ŀǰ���� ��c"+name+" ��f������ֵΪ ��c"+i);
	    	Bukkit.getPlayer(name).sendMessage("��f���� ��b> ��f����ֵ������ ��c"+i+" ��f��,Ŀǰ���� ��c"+name+" ��f������ֵΪ ��c"+i);
	    }else{
	    	PLAYERDATA.set("YouyiValue", Integer.valueOf(PLAYERDATA.getInt("YouyiValue"))+i);
	    	int value = PLAYERDATA.getInt("YouyiValue");
	    	p.sendMessage("��f���� ��b> ��f����ֵ������ ��c"+i+" ��f��,Ŀǰ���� ��c"+name+" ��f������ֵΪ ��c"+value);
	    	Bukkit.getPlayer(name).sendMessage("��f���� ��b> ��f����ֵ������ ��c"+i+" ��f��,Ŀǰ���� ��c"+name+" ��f������ֵΪ ��c"+value);
	    }
	    
	    try {
	    	PLAYERDATA.save(FS);
        } catch (IOException var8) {
            var8.printStackTrace();
        }
	}
	
	
}
