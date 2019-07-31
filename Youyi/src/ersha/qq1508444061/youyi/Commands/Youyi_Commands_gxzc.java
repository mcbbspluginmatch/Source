package ersha.qq1508444061.youyi.Commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import ersha.qq1508444061.youyi.Main;
import ersha.qq1508444061.youyi.Event.Youyi_gxPoints;
import ersha.qq1508444061.youyi.Event.Youyi_gxVault;
import ersha.qq1508444061.youyi.Util.Economys;

public class Youyi_Commands_gxzc implements CommandExecutor {

	public static boolean apivault = false;
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		Player p = (Player) sender;
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/小船数据.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
	    
		File F = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/金币共享申请.yml");
	    YamlConfiguration jbsq = YamlConfiguration.loadConfiguration(F);
	    
	    File Fsa = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/点卷共享申请.yml");
	    YamlConfiguration djsq = YamlConfiguration.loadConfiguration(Fsa);
	    
	    File Fss = new File(Main.getInstance().getDataFolder() + "/已建立小船的玩家.yml");
	    YamlConfiguration yilist = YamlConfiguration.loadConfiguration(Fss);
		
	    double MoneyCost = Economys.econ.getBalance(p);
	    
	    if(args.length == 0) {
	    	
	    	if (!Main.getInstance().getConfig().getBoolean("Shared.Money") && !Main.getInstance().getConfig().getBoolean("Shared.Points")){
				p.sendMessage("§f友谊 §b> §7此服务器无法使用这个功能");
				return false;
			}
	    	
			p.sendMessage("§6Yoiyi - 友谊小船   §6§o§lBy.二傻");
			p.sendMessage("§7§o插件版本: §c§o1.0");
			p.sendMessage("§b/yygx §7- §3共享财产详细");
			p.sendMessage("§b/yygx vault §7- §3给另一方发送共享金币邀请");
			p.sendMessage("§b/yygx points §7- §3给另一方发送共享点卷邀请");
			p.sendMessage("§b/yygx <vault/points> accept §7- §3同意共享");
			p.sendMessage(" ");
			p.sendMessage("§7§o注意事项:");
			p.sendMessage(" §c§o1.§7§o发出邀请方§3§o需要等待另一方同意§7§o才会共享");
			p.sendMessage(" §c§o2.§7§o一旦共享完毕后,如§c§o友谊小船翻船后§7§o共享财");
			p.sendMessage(" §7§o产将§c§o平均分给双方 §f§o(100/50)");
			p.sendMessage(" §c§o3.§7§o财产共享后所出现纠纷请自行解决");
			return false;
		}else if (args.length == 1) {
			String name = (String) PLAYERDATA.get(p.getName().toLowerCase());
			Player ps = Bukkit.getPlayer(name);
			
			if (args[0].equalsIgnoreCase("vault")) {
				
				if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
					p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
					p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
					return false;
				}
				
				if (!Main.getInstance().getConfig().getBoolean("Shared.Money")){
					p.sendMessage("§f友谊 §b> §7无法使用此功能");
					return false;
				}
				
				if (!Bukkit.getOfflinePlayer(name).isOnline()){
					p.sendMessage("§f友谊 §b> §7对方目前不在线");
					return false;
				}
				
				if (jbsq.getBoolean("是否已同意申请")){
					p.sendMessage("§f友谊 §b> §c你跟你的好朋友已是共享金币状态,无需重新共享");
					return false;
				}
				
				if (jbsq.get(p.getName().toLowerCase()) != null){
					p.sendMessage("§f友谊 §b> §c对方已邀请过你共享金币");
					p.sendMessage("§f友谊 §b> §a请输入 §e/yygx vault accept §a同意共享");
					return false;
				}
				
				if (jbsq.get(p.getName().toLowerCase()) != null){
					p.sendMessage("§f友谊 §b> §c你已发送过共享金币邀请,赶快去提醒他同意申请吧");
					return false;
				}
				
				jbsq.set(ps.getName().toLowerCase(), p.getName().toLowerCase());
				jbsq.set("是否已同意申请", Boolean.valueOf(false));
				
				p.sendMessage("§f友谊 §b> §a已发送申请,请等待对方同意");
				
				ps.sendMessage("§f友谊 §b> §a玩家 §c"+p.getName()+" §a对你发出§e§l共享金币财产§a邀请");
				ps.sendMessage("§f友谊 §b> §a请输入 §e/yygx vault accept §a同意共享");
				ps.sendMessage("§f友谊 §b> §c拒绝共享 §a请无视");
				
				try {
					jbsq.save(F);
				} catch (IOException es) {
				  es.printStackTrace();
				}
				
				return false;
			    }
			
			if (args[0].equalsIgnoreCase("points")) {
				
				if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
					p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
					p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
					return false;
				}
				
				if (!Main.getInstance().getConfig().getBoolean("Shared.Points")){
					p.sendMessage("§f友谊 §b> §7无法使用此功能");
					return false;
				}
				
				if (!Bukkit.getOfflinePlayer(name).isOnline()){
					p.sendMessage("§f友谊 §b> §7对方目前不在线");
					return false;
				}
				
				if (djsq.getBoolean("是否已同意申请")){
					p.sendMessage("§f友谊 §b> §c你跟你的好朋友已是共享点卷状态,无需重新共享");
					return false;
				}
				
				if (djsq.get(ps.getName().toLowerCase()) != null){
					p.sendMessage("§f友谊 §b> §c对方已邀请过你共享点卷");
					p.sendMessage("§f友谊 §b> §a请输入 §e/yygx points accept §a同意共享");
					return false;
				}
				
				if (djsq.get(p.getName().toLowerCase()) != null){
					p.sendMessage("§f友谊 §b> §c你已发送过共享点卷邀请,赶快去提醒他同意申请吧");
					return false;
				}
				
				djsq.set(ps.getName().toLowerCase(), p.getName().toLowerCase());
				djsq.set("是否已同意申请", Boolean.valueOf(false));
				
				p.sendMessage("§f友谊 §b> §a已发送申请,请等待对方同意");
				
				ps.sendMessage("§f友谊 §b> §a玩家 §c"+p.getName()+" §a对你发出§b§l共享点卷财产§a邀请");
				ps.sendMessage("§f友谊 §b> §a请输入 §e/yygx points accept §a同意共享");
				ps.sendMessage("§f友谊 §b> §c拒绝共享 §a请无视");
				
				try {
					djsq.save(Fsa);
				} catch (IOException es) {
				  es.printStackTrace();
				}
				
				return false;
		     }
		}else if (args.length == 2) {
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
				p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
				return false;
			}
			
			if (args[0].equalsIgnoreCase("vault")) {
				if (args[1].equalsIgnoreCase("accept")) {
					String name = (String) jbsq.get(p.getName().toLowerCase());
					
					if (!Main.getInstance().getConfig().getBoolean("Shared.Money")){
						p.sendMessage("§f友谊 §b> §7无法使用此功能");
						return false;
					}
					
					if (jbsq.getBoolean("是否已同意申请")){
						p.sendMessage("§f友谊 §b> §c你跟你的好朋友已是共享金币状态,无需重新共享");
						return false;
					}
					
					if (jbsq.get(p.getName().toLowerCase()) == null){
		    	    	p.sendMessage("§f友谊 §b> §7未接到对方的共享金币邀请");
		    	    	return false;
		    	    }
					
					if (!Bukkit.getOfflinePlayer(name).isOnline()){
						p.sendMessage("§f友谊 §b> §7对方目前不在线");
						return false;
					}
				
					
					if (jbsq.get(p.getName().toLowerCase()) == null){
		    	    	p.sendMessage("§f友谊 §b> §7未接到对方的金币共享申请");
		    	    	p.sendMessage("§f友谊 §b> §a输入 §e/yygx vault §a发送共享金币申请给对方吧");
		    	    	return false;
		    	    }
					
					Youyi_gxVault.tobu(p.getName().toLowerCase(), name.toLowerCase());
					
					jbsq.set("是否已同意申请", Boolean.valueOf(true));
					
					try {
						jbsq.save(F);
					} catch (IOException es) {
					  es.printStackTrace();
					}
				}
			}
			
			if (args[0].equalsIgnoreCase("points")) {
				if (args[1].equalsIgnoreCase("accept")) {
					String name = (String) djsq.get(p.getName().toLowerCase());
					
					if (!Main.getInstance().getConfig().getBoolean("Shared.Points")){
						p.sendMessage("§f友谊 §b> §7无法使用此功能");
						return false;
					}
					
					if (djsq.getBoolean("是否已同意申请")){
						p.sendMessage("§f友谊 §b> §c你跟你的好朋友已是共享金币状态,无需重新共享");
						return false;
					}
					
					if (djsq.get(p.getName().toLowerCase()) == null){
						p.sendMessage("§f友谊 §b> §7未接到对方的共享点卷邀请");
		    	    	return false;
		    	    }
					
					if (!Bukkit.getOfflinePlayer(name).isOnline()){
						p.sendMessage("§f友谊 §b> §7对方目前不在线");
						return false;
					}
					
					
					if (djsq.get(p.getName().toLowerCase()) == null){
		    	    	p.sendMessage("§f友谊 §b> §7未接到对方的点卷共享申请");
		    	    	p.sendMessage("§f友谊 §b> §a输入 §e/yygx points §a发送共享点卷申请给对方吧");
		    	    	return false;
		    	    }
					
					
					Youyi_gxPoints.tobu(p, Bukkit.getPlayer(name));

					djsq.set("是否已同意申请", Boolean.valueOf(true));
					
					try {
						djsq.save(Fsa);
					} catch (IOException es) {
					  es.printStackTrace();
					}
				}
			}
		}
		return false;
	}
}
