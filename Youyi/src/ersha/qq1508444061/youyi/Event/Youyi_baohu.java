package ersha.qq1508444061.youyi.Event;

import java.io.File;

import net.minecraftforge.cauldron.entity.CraftCustomEntity;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import ersha.qq1508444061.youyi.Main;
import ersha.qq1508444061.youyi.Util.FlansModUtils;

public class Youyi_baohu implements Listener {

	@EventHandler
	void onDamage(EntityDamageByEntityEvent e) {
		
		Player p = (Player)e.getEntity();
		
	    File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/小船数据.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
		
	    if (PLAYERDATA.getBoolean("双方无伤功能") != true){
        	return;
        }
	    
	    if (Main.getInstance().getConfig().getBoolean("BaoHu.Flan")){
			return;
		}
	    
		if (!Main.getInstance().getConfig().getBoolean("BaoHu.enable")){
			return;
		}
		
		
		if ( (e.getEntity() instanceof Player) && (e.getDamager() instanceof Projectile) && (((Projectile) e.getDamager()).getShooter() instanceof Player) ) {
		Player player = (Player)e.getEntity();
        Player damager = ((Player) ((Projectile) e.getDamager()).getShooter());
		
        if (damager == null){
        	return;
        }
        
        if ( sj.get(player.getName().toLowerCase()) != null ) {
        	if(PLAYERDATA.getString(player.getName().toLowerCase()).contains(damager.getName().toLowerCase())) {
        		damager.sendMessage("§f友谊 §b> §f你不能攻击你的好友");
        		e.setCancelled(true);
        		return;
        	}
        }
		}else if ( e.getEntity() instanceof Player && e.getDamager() instanceof Player ) {
			Player player = (Player) e.getEntity();
			Player damager = (Player) e.getDamager();
			
			if ( sj.get(player.getName().toLowerCase()) != null ) {
	        	if(PLAYERDATA.getString(player.getName().toLowerCase()).contains(damager.getName().toLowerCase())) {
	        		damager.sendMessage("§f友谊 §b> §f你不能攻击你的好友");
	        		e.setCancelled(true);
	        		return;
	        	}
	        }
		}
	}
	
	@EventHandler
	void onFlanDamage(EntityDamageByEntityEvent e) {
		
		Player p = (Player)e.getEntity();
		
		 File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
		 YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
		    
		 File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/小船数据.yml");
		 YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
		 
		 if (PLAYERDATA.getBoolean("双方无伤功能") != true){
			 return;
		 }
		 
		if (!Main.getInstance().getConfig().getBoolean("BaoHu.Flan")){
			return;
		}
		
		if(e.getEntity() instanceof Player) {
		Player player = (Player)e.getEntity();
        Player damager = this.getDamager(e.getDamager());
		
        if (damager == null){
        	return;
        }
        
        if (sj.get(player.getName().toLowerCase()) != null ) {
        	if(PLAYERDATA.getString(player.getName().toLowerCase()).contains(damager.getName().toLowerCase())) {
        		damager.sendMessage("§f友谊 §b> §f你不能攻击你的好友");
        		e.setCancelled(true);
        		return;
        	}
        }
        }
	}
	
    public static Player getDamager(Entity entity) {
        Player damager = null;
        if(entity instanceof Player) {
                damager = (Player)entity;
        } else if(entity instanceof CraftCustomEntity) {
        	damager = FlansModUtils.getShooter(entity);
        } else if(entity instanceof Projectile && ((Projectile)entity).getShooter() instanceof Player) {
                damager = (Player)((Projectile)entity).getShooter();
        }

        return damager;
}
}
