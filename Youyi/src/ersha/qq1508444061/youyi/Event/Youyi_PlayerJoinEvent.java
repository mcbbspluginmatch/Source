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
		
		File Fss = new File(Main.getInstance().getDataFolder() + "/�ѽ���С�������.yml");
	    YamlConfiguration yilist = YamlConfiguration.loadConfiguration(Fss);
	    
	    
	    if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
	    	return;
	    }
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File F = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/��ҹ�������.yml");
	    YamlConfiguration jbsq = YamlConfiguration.loadConfiguration(F);
	    
	    File Fssss = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/���������.yml");
	    YamlConfiguration djsq = YamlConfiguration.loadConfiguration(Fssss);
	    
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/����Ʋ�����.yml");
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
        
        //����
	    if (Main.getInstance().getConfig().getBoolean("Shared.Money")){
	    if (jbsq.get("�Ƿ���ͬ������") != null){
	    if (jbsq.getBoolean("�Ƿ���ͬ������")){
		double money = PLAYERDATA_CC.getDouble("Vault");
		double A = Economys.econ.getBalance(p);
		Economys.paymoney(p, A);
		Economys.econ.depositPlayer(p, money);
		
		p.sendMessage("��f���깲�� ��b> ��f��o���ڶ�ȡ��ͬ��ʣ�๲���e��o��ҡ�f��o�Ʋ�");
		p.sendMessage("��f���깲�� ��b> ��f��oĿǰʣ��: ��c"+money);
	    }
	    }
	    }
	    
	    if (Main.getInstance().getConfig().getBoolean("Shared.Points")){
	    if (djsq.get("�Ƿ���ͬ������") != null){
	    if (djsq.getBoolean("�Ƿ���ͬ������")){
		int cost = PLAYERDATA_CC.getInt("Points");
		Points.points.getAPI().set(p.getUniqueId(), cost);
		
		p.sendMessage("��f���깲�� ��b> ��f��o���ڶ�ȡ��ͬ��ʣ�๲���b��o����f��o�Ʋ�");
		p.sendMessage("��f���깲�� ��b> ��f��oĿǰʣ��: ��c"+cost);
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
    			if (jbsq.get("�Ƿ���ͬ������") != null){
    			if (jbsq.getBoolean("�Ƿ���ͬ������")){
    			Youyi_gxVault.gxVaults(p.getName().toLowerCase());
    			}
    			}
    			}
    			
    			if (Main.getInstance().getConfig().getBoolean("Shared.Points")){
    			if (djsq.get("�Ƿ���ͬ������") != null){
    			if (djsq.getBoolean("�Ƿ���ͬ������")){
    			Youyi_gxPoints.gxPointss(p.getName().toLowerCase());
    			}
    			}
    			}
    		  }
    		}, 20L, 20L);
	}

}
