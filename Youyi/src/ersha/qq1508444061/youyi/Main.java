package ersha.qq1508444061.youyi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;

import ersha.qq1508444061.youyi.Commands.Youyi_Commands_Commands;
import ersha.qq1508444061.youyi.Commands.Youyi_Commands_ck;
import ersha.qq1508444061.youyi.Commands.Youyi_Commands_gxzc;
import ersha.qq1508444061.youyi.Event.Youyi_PlayerJoinEvent;
import ersha.qq1508444061.youyi.Event.Youyi_Reward;
import ersha.qq1508444061.youyi.Event.Youyi_gxExp;
import ersha.qq1508444061.youyi.Event.Youyi_gxPoints;
import ersha.qq1508444061.youyi.Event.Youyi_Chat;
import ersha.qq1508444061.youyi.Event.Youyi_baohu;
import ersha.qq1508444061.youyi.Event.Youyi_ck;
import ersha.qq1508444061.youyi.Papi.Youyi_PAPI;
import ersha.qq1508444061.youyi.Util.Economys;
import ersha.qq1508444061.youyi.Util.Info;
import ersha.qq1508444061.youyi.Util.Points;
import ersha.qq1508444061.youyi.Util.Times;

public class Main extends JavaPlugin implements Listener {

	    public static Main instance;
	    private boolean ppcost;
	    
	    File jl;
        public static FileConfiguration jls;

        public static Main getInstance() {
                return instance;
        }

        public void onEnable() {
                instance = this;
                
                Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable(){
        		@Override
        		public void run() {
        			onTimes();
        		  }
        		}, 20L, 20L);
                
                Main.getInstance().saveDefaultConfig();
                Main.getInstance().reloadConfig();
                Bukkit.getPluginManager().registerEvents(this, this);
                Bukkit.getPluginManager().registerEvents(new Info(), this);
                Bukkit.getPluginManager().registerEvents(new Youyi_baohu(), this);
                Bukkit.getPluginManager().registerEvents(new Youyi_ck(), this);
                Bukkit.getPluginManager().registerEvents(new Youyi_Chat(), this);
                Bukkit.getPluginManager().registerEvents(new Youyi_PlayerJoinEvent(), this);
                Bukkit.getPluginManager().registerEvents(new Youyi_gxPoints(), this);
                Bukkit.getPluginManager().registerEvents(new Youyi_gxExp(), this);
                Bukkit.getPluginManager().registerEvents(new Youyi_Reward(), this);
                
                
                this.getCommand("yyi").setExecutor(new Youyi_Commands_Commands());
                this.getCommand("yyck").setExecutor(new Youyi_Commands_ck());
                this.getCommand("yygx").setExecutor(new Youyi_Commands_gxzc());
                
                jl = new File (getDataFolder(), "Reward.yml");
                if (!jl.exists()){
                	saveResource("Reward.yml", true);
                }
                jls = YamlConfiguration.loadConfiguration(jl);
                
                Bukkit.getConsoleSender().sendMessage("Youyi > ���ڼ���");
                Bukkit.getConsoleSender().sendMessage("Youyi >���ڼ���Ƿ���ڿɼ��ݲ��");
                
                if (Bukkit.getPluginManager().isPluginEnabled("Vault")){
                	Bukkit.getConsoleSender().sendMessage(" ��a> Vault �ѳɹ����ݣ�");
                    Economys.setupEconomy();
                    Youyi_Commands_ck.apivault = true;
                }else{
                	Bukkit.getConsoleSender().sendMessage(" ��7> Vault δ�ҵ��˲����");
                }
                
                if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")){
                	Bukkit.getConsoleSender().sendMessage(" ��a> PlayerPoints �ѳɹ����ݣ�");
                	this.ppcost = Points.hookPlayerPoints();
                }else{
                	Bukkit.getConsoleSender().sendMessage(" ��7> PlayerPoints δ�ҵ��˲����");
                }
                
                if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
                	Bukkit.getConsoleSender().sendMessage(" ��a> PlaceholderAPI �ѳɹ����ݣ�");
                	new Youyi_PAPI(this).hook();
                }else{
                	Bukkit.getConsoleSender().sendMessage(" ��7> PlaceholderAPI δ�ҵ��˲����");
                }
                
                Bukkit.getConsoleSender().sendMessage("Youyi > �������,ף��ʹ�����");
                
        }
        
        
        public void onDisable() {
                Bukkit.getConsoleSender().sendMessage("��c���ж��");
        }
        
        
        public static void onTimes() {
        	File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
    	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
        	
    		if (get((Times) (new Gson()).fromJson("{  startTime: '"+Main.getInstance().getConfig().getString("YouyiValue.ResetTime")+":00',endTime: '"+Main.getInstance().getConfig().getString("YouyiValue.ResetTime")+":02'  }", Times.class))) {
    			
    			
    		    File f = new File(Main.getInstance().getDataFolder() + "/data/all_data.yml");
    		        if(f.exists()) {
    		        f.delete();
    		    }
    			
    			
    			Bukkit.broadcastMessage("��f���� ��b> ��aÿ������С������������");
    			
    			File FSa = new File(Main.getInstance().getDataFolder() + "/data/all_data.yml");
        	    YamlConfiguration svaule = YamlConfiguration.loadConfiguration(FSa);
    			
    			for (Player p : Bukkit.getOnlinePlayers()){
    				if (svaule.get(sj.get(p.getName().toLowerCase())+".Expshare") == null){
    			        svaule.set(sj.get(p.getName().toLowerCase())+".Expshare", Main.getInstance().getConfig().getInt("YouyiValue.Expshare.number"));
    			        svaule.set(sj.get(p.getName().toLowerCase())+".Chat", Main.getInstance().getConfig().getInt("YouyiValue.Chat.number"));
    			        try {
    				    	svaule.save(FSa);
    			        } catch (IOException var8) {
    			            var8.printStackTrace();
    			        }
    			        }
    			}
    		}
    	}
        
        public static boolean get(Times time) {
            try {
                Date e = new Date();
                e = (new SimpleDateFormat("HH:mm:ss")).parse((new SimpleDateFormat("HH:mm:ss")).format(e));
                Date startTime = (new SimpleDateFormat("HH:mm:ss")).parse(time.getStartTime());
                Date endTime = (new SimpleDateFormat("HH:mm:ss")).parse(time.getEndTime());
                if(startTime.after(endTime)) {
                        endTime.setDate(startTime.getDate() + 1);
                }

                return startTime.before(e) && endTime.after(e);
        } catch (Exception var4) {
                var4.printStackTrace();
                return false;
        }
    } 
        
        public static void DelYml(String files,String FileName) {
            Date e = new Date();
			File f = new File(Main.getInstance().getDataFolder()+files);
			
			if(f.exists()) {
			        f.delete();
			}

			File SaveTo = new File(f, FileName+".yml");
			if(SaveTo.exists()) {
			        SaveTo.delete();
			}
}
}