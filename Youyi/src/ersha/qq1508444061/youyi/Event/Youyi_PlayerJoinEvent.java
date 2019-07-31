package ersha.qq1508444061.youyi.Event;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ersha.qq1508444061.youyi.Main;
import ersha.qq1508444061.youyi.Util.Economys;
import ersha.qq1508444061.youyi.Util.Points;

public class Youyi_PlayerJoinEvent implements Listener{

	@EventHandler
	void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		File Fss = new File(Main.getInstance().getDataFolder() + "/已建立小船的玩家.yml");
	    YamlConfiguration yilist = YamlConfiguration.loadConfiguration(Fss);
	    
	    
	    if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
	    	return;
	    }
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File F = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/金币共享申请.yml");
	    YamlConfiguration jbsq = YamlConfiguration.loadConfiguration(F);
	    
	    File Fssss = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/点卷共享申请.yml");
	    YamlConfiguration djsq = YamlConfiguration.loadConfiguration(Fssss);
	    
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/共享财产数据.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);
	    
	    File FSa = new File(Main.getInstance().getDataFolder() + "/data/all_data.yml");
	    YamlConfiguration svaule = YamlConfiguration.loadConfiguration(FSa);
		
        Youyi_ck.c_money.put(p.getName().toLowerCase(), Boolean.valueOf(false));
        Youyi_ck.q_money.put(p.getName().toLowerCase(), Boolean.valueOf(false));
        Youyi_ck.c_points.put(p.getName().toLowerCase(), Boolean.valueOf(false));
        Youyi_ck.q_points.put(p.getName().toLowerCase(), Boolean.valueOf(false));
        
        Youyi_Chat.youyichat.put(p.getName().toLowerCase(), Boolean.valueOf(false));
        
        if (svaule.get(sj.get(p.getName().toLowerCase())+".Expshare") == null){
        svaule.set(sj.get(p.getName().toLowerCase())+".Expshare", Main.getInstance().getConfig().getInt("YouyiValue.Expshare.number"));
        svaule.set(sj.get(p.getName().toLowerCase())+".Chat", Main.getInstance().getConfig().getInt("YouyiValue.Chat.number"));
        try {
	    	svaule.save(FSa);
        } catch (IOException var8) {
            var8.printStackTrace();
        }
        }
        
        //共享
	    if (Main.getInstance().getConfig().getBoolean("Shared.Money")){
	    if (jbsq.get("是否已同意申请") != null){
	    if (jbsq.getBoolean("是否已同意申请")){
		double money = PLAYERDATA_CC.getDouble("Vault");
		double A = Economys.econ.getBalance(p);
		Economys.paymoney(p, A);
		Economys.econ.depositPlayer(p, money);
		
		p.sendMessage("§f友谊共享 §b> §f§o正在读取并同步剩余共享§e§o金币§f§o财产");
		p.sendMessage("§f友谊共享 §b> §f§o目前剩余: §c"+money);
	    }
	    }
	    }
	    
	    if (Main.getInstance().getConfig().getBoolean("Shared.Points")){
	    if (djsq.get("是否已同意申请") != null){
	    if (djsq.getBoolean("是否已同意申请")){
		int cost = PLAYERDATA_CC.getInt("Points");
		Points.points.getAPI().set(p.getUniqueId(), cost);
		
		p.sendMessage("§f友谊共享 §b> §f§o正在读取并同步剩余共享§b§o点卷§f§o财产");
		p.sendMessage("§f友谊共享 §b> §f§o目前剩余: §c"+cost);
	    }
	    }
	    }
		
		Bukkit.getServer().getScheduler().runTaskTimer(Main.getInstance(), new Runnable(){
    		@Override
    		public void run() {
    			if (!p.isOnline()){
    				return;
    			}

    			if (Main.getInstance().getConfig().getBoolean("Shared.Money")){
    			if (jbsq.get("是否已同意申请") != null){
    			if (jbsq.getBoolean("是否已同意申请")){
    			Youyi_gxVault.gxVaults(p.getName().toLowerCase());
    			}
    			}
    			}
    			
    			if (Main.getInstance().getConfig().getBoolean("Shared.Points")){
    			if (djsq.get("是否已同意申请") != null){
    			if (djsq.getBoolean("是否已同意申请")){
    			Youyi_gxPoints.gxPointss(p.getName().toLowerCase());
    			}
    			}
    			}
    		  }
    		}, 20L, 20L);
	}

}
