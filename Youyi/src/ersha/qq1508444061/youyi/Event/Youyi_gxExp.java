package ersha.qq1508444061.youyi.Event;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import ersha.qq1508444061.youyi.Main;

public class Youyi_gxExp implements Listener {
	
	@EventHandler (priority = EventPriority.HIGH)
	void onKill(PlayerExpChangeEvent  e) {
			Player p = e.getPlayer();
			
		    File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
		    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
		    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/С������.yml");
		    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
		    
		    if (!PLAYERDATA.getBoolean("���������")){
		    	return;
		    }
			
			if (sj.get(p.getName().toLowerCase()) == null) {
			 return;
			}
		   if(Main.getInstance().getConfig().getBoolean("Expshare.enable")) {
				shareExp(p, e);
			}
		}

	void shareExp(Player p, PlayerExpChangeEvent event) {
	    File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/С������.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
	    
	    File FSa = new File(Main.getInstance().getDataFolder() + "/data/all_data.yml");
	    YamlConfiguration svaule = YamlConfiguration.loadConfiguration(FSa);
		
	    String name = (String) PLAYERDATA.get(p.getName().toLowerCase());
	    
		FileConfiguration f = Main.getInstance().getConfig();
		List<Entity> es = p.getNearbyEntities(f.getInt("Expshare.radius"), f.getInt("Expshare.radius"),
				f.getInt("Expshare.radius"));
		int count = 0;
		String s = "";
		for (Entity e : es) {
			if (e instanceof Player) {
				Player op = (Player) e;
				if (sj.get(p.getName().toLowerCase()) != null) {
					if (PLAYERDATA.getString(p.getName().toLowerCase()).contains(name)) {
						s = s + op.getName() + " ";
						int share = (int) (event.getAmount() * (f.getInt("Expshare.percent") * 0.01));
						op.sendMessage("��f���깲�� ��b> ��e���� ��3" + p.getName() + " ��e������ ��c" + share + " ��e�㾭��ֵ����!");
						op.giveExp(share);
						count++;
					}
				}
			}
		}
		int share = (int) (event.getAmount() * (f.getInt("Expshare.percent") * 0.01 * count));
		int exp = (int) (event.getAmount() * (1.00 - f.getInt("Expshare.percent") * 0.01 * count));
		int exps = event.getAmount();
		event.setAmount(exp);
		if (!s.equals("")) {
				p.sendMessage("��f���깲�� ��b> ��e���ε��� ��c" + exps + "��e �㾭��,��Ϊ ��3" + name + " ��e������ ��c" + share + " ��e�㾭��,�������� ��c"+exp+"��e �㾭��!");
				if (svaule.getInt(sj.get(p.getName().toLowerCase())+".Expshare") > 0){
				    String names = sj.getString(p.getName().toLowerCase());
				    Youyi_value.addYouyiValue(p, Main.getInstance().getConfig().getInt("YouyiValue.Expshare.vaule"));
				    svaule.set(names+".Expshare", svaule.getInt(names+".Expshare")-Integer.valueOf(1));
				    try {
				    	svaule.save(FSa);
			        } catch (IOException var8) {
			            var8.printStackTrace();
			        }
					}
		}
	}
}
