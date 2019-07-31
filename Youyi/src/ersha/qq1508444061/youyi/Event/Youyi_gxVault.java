package ersha.qq1508444061.youyi.Event;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import ersha.qq1508444061.youyi.Main;
import ersha.qq1508444061.youyi.Util.Economys;

public class Youyi_gxVault {
 
	
	public static void tobu(String p, String player){
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
		
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.toLowerCase())+"/共享财产数据.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);
		
	    double A = Economys.econ.getBalance(p);
	    double B = Economys.econ.getBalance(player);

	    PLAYERDATA_CC.set("Vault", Double.valueOf(A)+Double.valueOf(B));
	     
	    
	    Player as = Bukkit.getPlayer(p);
	    Player bs = Bukkit.getPlayer(player);
	    
	    Economys.paymoney(as, A);
	    Economys.paymoney(bs, B);
	    
	    Economys.econ.depositPlayer(p, PLAYERDATA_CC.getDouble("Vault"));
	    Economys.econ.depositPlayer(player, PLAYERDATA_CC.getDouble("Vault"));
	    
	    as.sendMessage("§f友谊 §b> §a已同意共享金币");
	    bs.sendMessage("§f友谊 §b> §a对方已同意共享金币");

	    as.sendMessage("§f友谊 §b> §a目前你们共享的金币财产剩余 §c"+PLAYERDATA_CC.getDouble("Vault"));
	    bs.sendMessage("§f友谊 §b> §a目前你们共享的金币财产剩余 §c"+PLAYERDATA_CC.getDouble("Vault"));
	    
	    int vaule = Main.getInstance().getConfig().getInt("YouyiValue.gx_Money.vaule");
	    Youyi_value.addYouyiValue(Bukkit.getPlayer(p), vaule);
	    
	    try {
	    	PLAYERDATA_CC.save(FS);
        } catch (IOException var8) {
            var8.printStackTrace();
        }
	}
	
	public static void gxVaults(String p){
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
		
	    File FSs= new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.toLowerCase())+"/小船数据.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FSs);
	    
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.toLowerCase())+"/共享财产数据.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);

	    String p2 = PLAYERDATA.getString(p);
	    
	    double A = Economys.econ.getBalance(p);
	    double B = Economys.econ.getBalance(p2);
	    
	    try {
	    	PLAYERDATA_CC.save(FS);
        } catch (IOException var8) {
            var8.printStackTrace();
        }
	    
	    if (A == PLAYERDATA_CC.getDouble("Vault")){
	    	return;
	    }
	    
	    if (A > PLAYERDATA_CC.getDouble("Vault")){
	    	double money = A-Double.valueOf(PLAYERDATA_CC.getDouble("Vault"));
	    	PLAYERDATA_CC.set("Vault", PLAYERDATA_CC.getDouble("Vault")+Double.valueOf(money));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    }else{
	    	double money = Double.valueOf(PLAYERDATA_CC.getDouble("Vault")-A);
	    	PLAYERDATA_CC.set("Vault", PLAYERDATA_CC.getDouble("Vault")-Double.valueOf(money));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    }
	    
	    if (!Bukkit.getOfflinePlayer(p2).isOnline()){
	    	Economys.paymoney(Bukkit.getPlayer(p), A);
	    	Economys.econ.depositPlayer(Bukkit.getOfflinePlayer(p), PLAYERDATA_CC.getDouble("Vault"));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    }else{
	    	Economys.paymoney(Bukkit.getPlayer(p), A);
	    	Economys.econ.depositPlayer(Bukkit.getOfflinePlayer(p), PLAYERDATA_CC.getDouble("Vault"));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    }
	    
	    
	    
	    if (Bukkit.getOfflinePlayer(p2).isOnline()){
	    	Economys.paymoney(Bukkit.getPlayer(p), A);
	    	Economys.econ.depositPlayer(Bukkit.getOfflinePlayer(p), PLAYERDATA_CC.getDouble("Vault"));
	    	Economys.paymoney(Bukkit.getPlayer(p2), B);
	    	Economys.econ.depositPlayer(Bukkit.getOfflinePlayer(p2), PLAYERDATA_CC.getDouble("Vault"));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    }else{
	    	Economys.paymoney(Bukkit.getPlayer(p), A);
	    	Economys.econ.depositPlayer(Bukkit.getOfflinePlayer(p), PLAYERDATA_CC.getDouble("Vault"));
	    	try {
		    	PLAYERDATA_CC.save(FS);
	        } catch (IOException var8) {
	            var8.printStackTrace();
	        }
	    }
	}
}
