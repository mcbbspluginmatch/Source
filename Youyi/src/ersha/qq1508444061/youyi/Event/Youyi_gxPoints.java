package ersha.qq1508444061.youyi.Event;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import ersha.qq1508444061.youyi.Main;
import ersha.qq1508444061.youyi.Util.Points;

public class Youyi_gxPoints implements Listener {
 
	
	public static void tobu(Player p, Player player){
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
		
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/����Ʋ�����.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);
	    
	    File FSa = new File(Main.getInstance().getDataFolder() + "/data/all_data.yml");
	    YamlConfiguration svaule = YamlConfiguration.loadConfiguration(FSa);
		
	    int A = Points.points.getAPI().look(p.getUniqueId());
	    int B = Points.points.getAPI().look(player.getUniqueId());

	    PLAYERDATA_CC.set("Points", Integer.valueOf(A)+Integer.valueOf(B));
	     
	    int cost = PLAYERDATA_CC.getInt("Points");
	    
	    
	    Points.points.getAPI().set(p.getUniqueId(), cost);
	    Points.points.getAPI().set(player.getUniqueId(), cost);
	    
	    p.sendMessage("��f���� ��b> ��a��ͬ�⹲����");
	    player.sendMessage("��f���� ��b> ��a�Է���ͬ�⹲����");

	    p.sendMessage("��f���� ��b> ��aĿǰ���ǹ���ĵ��Ʋ�ʣ�� ��c"+PLAYERDATA_CC.getInt("Points"));
	    player.sendMessage("��f���� ��b> ��aĿǰ���ǹ���ĵ��Ʋ�ʣ�� ��c"+PLAYERDATA_CC.getInt("Points"));
	    
	    int vaule = Main.getInstance().getConfig().getInt("YouyiValue.gx_Points.vaule");
	    Youyi_value.addYouyiValue(p, vaule);
	    
	    try {
	    	PLAYERDATA_CC.save(FS);
        } catch (IOException var8) {
            var8.printStackTrace();
        }
	}
	
	
	public static void gxPointss(String p){
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
		
	    File FSs= new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.toLowerCase())+"/С������.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FSs);
	    
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.toLowerCase())+"/����Ʋ�����.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);

	    String p2 = PLAYERDATA.getString(p);
	    
	    int A = Points.points.getAPI().look(Bukkit.getOfflinePlayer(p).getUniqueId());

	    try {
	    	PLAYERDATA_CC.save(FS);
        } catch (IOException var8) {
            var8.printStackTrace();
        }
	    
	    if (A == PLAYERDATA_CC.getInt("Points")){
	    	return;
	    }
	    
	    if (A > PLAYERDATA_CC.getInt("Points")){
	    	int money = Integer.valueOf(Integer.valueOf(A)-PLAYERDATA_CC.getInt("Points"));
	    	PLAYERDATA_CC.set("Points", Integer.valueOf(PLAYERDATA_CC.getInt("Points")+Integer.valueOf(money)));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    }else{
	    	int money = Integer.valueOf(PLAYERDATA_CC.getInt("Points")-Integer.valueOf(A));
	    	PLAYERDATA_CC.set("Points", Integer.valueOf(PLAYERDATA_CC.getInt("Points")-Integer.valueOf(money)));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    }
	    
	    if (!Bukkit.getOfflinePlayer(p2).isOnline()){
	    	Points.points.getAPI().set(Bukkit.getOfflinePlayer(p).getUniqueId(), PLAYERDATA_CC.getInt("Points"));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    	return;
	    }else{
	    	Points.points.getAPI().set(Bukkit.getOfflinePlayer(p).getUniqueId(), PLAYERDATA_CC.getInt("Points"));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    }
	    
	    
	    
	    if (Bukkit.getOfflinePlayer(p2).isOnline()){
	    	Points.points.getAPI().set(Bukkit.getOfflinePlayer(p).getUniqueId(), PLAYERDATA_CC.getInt("Points"));
	    	Points.points.getAPI().set(Bukkit.getOfflinePlayer(p2).getUniqueId(), PLAYERDATA_CC.getInt("Points"));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    	return;
	    }else{
	    	Points.points.getAPI().set(Bukkit.getOfflinePlayer(p).getUniqueId(), PLAYERDATA_CC.getInt("Points"));
	    	Points.points.getAPI().set(Bukkit.getOfflinePlayer(p2).getUniqueId(), PLAYERDATA_CC.getInt("Points"));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    }
	}
}
