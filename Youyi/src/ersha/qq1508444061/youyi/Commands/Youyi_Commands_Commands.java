package ersha.qq1508444061.youyi.Commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import ersha.qq1508444061.youyi.Main;
import ersha.qq1508444061.youyi.Event.Youyi_Chat;
import ersha.qq1508444061.youyi.Event.Youyi_value;
import ersha.qq1508444061.youyi.Util.Economys;
import ersha.qq1508444061.youyi.Util.Points;

public class Youyi_Commands_Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		Player p = (Player) sender;
		
		File F = new File(Main.getInstance().getDataFolder() + "/邀请列表.yml");
	    YamlConfiguration yq = YamlConfiguration.loadConfiguration(F);
	    
	    File Fss = new File(Main.getInstance().getDataFolder() + "/已建立小船的玩家.yml");
	    YamlConfiguration yilist = YamlConfiguration.loadConfiguration(Fss);
	    
	    //player和player
	    File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/小船数据.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
	    
	    File FSsss = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/共享财产数据.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FSsss);
		
	    File jiesans = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/解散.yml");
	    YamlConfiguration jiesan = YamlConfiguration.loadConfiguration(jiesans);
	    
	    File Fas = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/金币共享申请.yml");
	    YamlConfiguration jbsq = YamlConfiguration.loadConfiguration(Fas);
	    
	    File Fsa = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/点卷共享申请.yml");
	    YamlConfiguration djsq = YamlConfiguration.loadConfiguration(Fsa);
	    
	    File FSa = new File(Main.getInstance().getDataFolder() + "/data/all_data.yml");
	    YamlConfiguration svaule = YamlConfiguration.loadConfiguration(FSa);
	    
	    try {
			yq.save(F);
			yilist.save(Fss);
			sj.save(Fsss);
		} catch (IOException es) {
		  es.printStackTrace();
		}
		
		if(args.length == 0) {
		
			
			p.sendMessage("§6Yoiyi - 友谊小船   §6§o§lBy.二傻");
			p.sendMessage("§7§o插件版本: §c§o1.0");
			if (PLAYERDATA.get(p.getName().toLowerCase()) != null){
			p.sendMessage("§3你已与 §c"+PLAYERDATA.getString(p.getName().toLowerCase())+" §3建立§6§l友谊小船");
			p.sendMessage("§3你与 §c"+PLAYERDATA.getString(p.getName().toLowerCase())+" §3目前的友谊值: §c"+PLAYERDATA.getInt("YouyiValue"));
			}else{
			p.sendMessage("§3你还未与任何一位玩家建立§6§l友谊小船");
			}
			p.sendMessage("§b/yyi §7- §3指令详细");
			p.sendMessage("§b/yyi yq <player> §7- §3邀请玩家建立小船");
			p.sendMessage("§b/yyi accept §7- §3接受他人邀请");
			p.sendMessage("§b/yyi unaccept §7- §3拒绝他人邀请");
			p.sendMessage("§b/yyi info §7- §3打开信息面板(友谊值奖励)");
			p.sendMessage("§b/yyi chat §7- §3切换至小船聊天模式");
			p.sendMessage("§c/yyi jiesan §7- §3解散小船详细说明");
			p.sendMessage("§6/yyi reload §7- §3重载插件配置");
			p.sendMessage("§e/yyi exp §7- §3查看分享经验功能详细");
			p.sendMessage("§e/yyi baohu §7- §3查看双方相互攻击无伤功能详细");
			p.sendMessage("§e/yyi switch <exp/baohu>");
			p.sendMessage("  §7- §3exp §7双方获得经验分享功能");
			p.sendMessage("  §7- §3baohu §7双方互相攻击无伤功能");
			p.sendMessage("§e/yyck §7- §3共用仓库功能详细");
			p.sendMessage("§e/yygx §7- §3共享财产功能详细");
			return false;
		}
		
		if (args.length == 2 && args[0].equals("yq")) {
			Player player = Bukkit.getPlayer(args[1]);
			if (player == p){
				p.sendMessage("§f友谊 §b> §c不能邀请自己!");
				return false;
			}
			
			for (String list : yilist.getStringList("List"))
			if (list.contains(p.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c你已经与另一位玩家建立小船了哦");
				p.sendMessage("§f友谊 §b> §c无法再次与另一位玩家建立");
				return false;
			}
			
			for (String list : yilist.getStringList("List"))
			if (list.contains(player.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c对方与别人建立小船咯,请选择另一位玩家吧");
				return false;
			}
			
			if (yq.get(p.getName().toLowerCase()) != null){
				p.sendMessage("§f友谊 §b> §7你刚刚已经向某一名玩家发出邀请了");
				p.sendMessage("§f友谊 §b> §7请耐心等待对方接受");
				return false;
			}
			
			if (!Bukkit.getOfflinePlayer(args[1]).isOnline()){
				p.sendMessage("§f友谊 §b> §7你所邀请的对象不存在或者不在线!");
				return false;
			}
			
			yq.set(player.getName().toLowerCase(), p.getName().toLowerCase());
			
			p.sendMessage("§f友谊 §b> §a已发送建立友谊小船邀请,请等待对方接受!");
			
			player.sendMessage("§f友谊 §b> §a玩家 §c"+p.getName()+" §a对你发出建立友谊小船邀请");
			player.sendMessage("§f友谊 §b> §a请输入 §e/yyi accept §a接受邀请 §7(超过 §c20 §7秒将自动拒绝)");
			player.sendMessage("§f友谊 §b> §a请输入 §e/yyi unaccept §c拒绝邀请 §7(超过 §c20 §7秒将自动拒绝)");
			
			try {
				yq.save(F);
			} catch (IOException es) {
			  es.printStackTrace();
			}
			
			new BukkitRunnable() {
				@Override
				public void run() {
				yq.set(player.getName().toLowerCase(), null);
				try {
					yq.save(F);
				} catch (IOException es) {
				  es.printStackTrace();
				}
				}
			}.runTaskLater(Main.getInstance(), 400);
			
			return false;
		}else if (args.length == 1 && args[0].equals("info")) {
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
				p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
				return false;
			}
			
			Inventory inv = Bukkit.createInventory((InventoryHolder)null, 9, Main.getInstance().getConfig().getString("Set.Info.Name").replace("&", "§"));
			
			ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1, (short) 0);
            ItemMeta id = item.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			id.setDisplayName(Main.getInstance().getConfig().getString("Set.Info.sjName").replace("&", "§"));
			lore = new ArrayList<String>();
			for(String line:Main.getInstance().getConfig().getStringList("Set.Info.Lore")){
				lore.add(line
						.replace("%player%", PLAYERDATA.getString(p.getName().toLowerCase()))
						.replace("%value%", PLAYERDATA.getInt("YouyiValue")+"")
						.replace("%money_ck%", PLAYERDATA_CC.getInt("仓库存放金币数量")+"")
						.replace("%points_ck%", PLAYERDATA_CC.getInt("仓库存放点卷数量")+"")
						.replace("%money_gx%", jbsq.getBoolean("是否已同意申请") ? "已共享" : "未共享")
						.replace("%points_gx%", djsq.getBoolean("是否已同意申请") ? "已共享" : "未共享")
						.replace("%exp_switch%", PLAYERDATA.getBoolean("经验分享功能") ? "已开启" : "未开启")
						.replace("%baohu_switch%", PLAYERDATA.getBoolean("双方无伤功能") ? "已开启" : "未开启")
						.replace("&", "§")
						);
			}
			id.setLore(lore);
			item.setItemMeta(id);
			inv.setItem(0, item);
			
			item = new ItemStack(Material.CHEST, 1, (short) 0);
            id = item.getItemMeta();
			id.setDisplayName(Main.getInstance().getConfig().getString("Set.Info.YouyiValue.Name").replace("&", "§"));
			lore = new ArrayList<String>();
			for(String line:Main.getInstance().getConfig().getStringList("Set.Info.YouyiValue.Lore")){
				lore.add(line
						.replace("%player%", PLAYERDATA.getString(p.getName().toLowerCase()))
						.replace("%value%", PLAYERDATA.getInt("YouyiValue")+"")
						.replace("%money_ck%", PLAYERDATA_CC.getInt("仓库存放金币数量")+"")
						.replace("%points_ck%", PLAYERDATA_CC.getInt("仓库存放点卷数量")+"")
						.replace("%money_gx%", jbsq.getBoolean("是否已同意申请") ? "已共享" : "未共享")
						.replace("%points_gx%", djsq.getBoolean("是否已同意申请") ? "已共享" : "未共享")
						.replace("%exp_switch%", PLAYERDATA.getBoolean("经验分享功能") ? "已开启" : "未开启")
						.replace("%baohu_switch%", PLAYERDATA.getBoolean("双方无伤功能") ? "已开启" : "未开启")
						.replace("&", "§")
						);
			}
			id.setLore(lore);
			item.setItemMeta(id);
			inv.setItem(8, item);
			
			item = new ItemStack(Material.SIGN, 1, (short) 0);
            id = item.getItemMeta();
			id.setDisplayName(Main.getInstance().getConfig().getString("Set.Info.Task.Name").replace("&", "§"));
			lore = new ArrayList<String>();
			for(String line:Main.getInstance().getConfig().getStringList("Set.Info.Task.Lore")){
				lore.add(line
						.replace("%player%", PLAYERDATA.getString(p.getName().toLowerCase()))
						.replace("%value%", PLAYERDATA.getInt("YouyiValue")+"")
						
						.replace("%exp%", svaule.getInt(sj.getString(p.getName().toLowerCase())+".Expshare")+"")
						.replace("%chat%", svaule.getInt(sj.getString(p.getName().toLowerCase())+".Chat")+"")
						
						.replace("%jl%", PLAYERDATA.get(p.getName().toLowerCase()) != null ? "已完成" : "未完成")
						.replace("%gx_Money%", jbsq.getBoolean("是否已同意申请") ? "已完成" : "未完成")
						.replace("%gx_Points%", djsq.getBoolean("是否已同意申请") ? "已完成" : "未完成")
						.replace("&", "§")
						);
			}
			id.setLore(lore);
			item.setItemMeta(id);
			inv.setItem(4, item);
			
			p.closeInventory();
			p.openInventory(inv);
			return false;
		}else if (args.length == 1 && args[0].equals("accept")) {
			if (yq.get(p.getName().toLowerCase()) == null){
    	    	p.sendMessage("§f友谊 §b> §7暂未收到任何玩家的邀请");
    	    	return false;
    	    }
			
			String name = (String) yq.get(p.getName().toLowerCase());
            Player player = Bukkit.getOfflinePlayer(name).getPlayer();
    	    
            
            File FS2 = new File(Main.getInstance().getDataFolder() + "/data/"+player.getName().toLowerCase()+"和"+p.getName().toLowerCase()+"/小船数据.yml");
    	    YamlConfiguration PLAYERDATA2 = YamlConfiguration.loadConfiguration(FS2);
    	    
			if (!player.isOnline()){
				p.sendMessage("§f友谊 §b> §7对方目前不在线");
				return false;
			}

			p.sendMessage("§f友谊 §b> §a已同意 §c"+player.getName()+" §a玩家的邀请");
			player.sendMessage("§f友谊 §b> §a玩家 §c"+p.getName()+" §a已同意与你建立友谊小船");
			
			
			
			yq.set(p.getName().toLowerCase(), null);
			
			Object list = yilist.getStringList("List");
			((List)list).add(player.getName().toLowerCase());
			((List)list).add(p.getName().toLowerCase());
			yilist.set("List", list);
			
			PLAYERDATA2.set("A", player.getName().toLowerCase());
			PLAYERDATA2.set("B", p.getName().toLowerCase());
			
			PLAYERDATA2.set(p.getName().toLowerCase(), player.getName().toLowerCase());
			PLAYERDATA2.set(player.getName().toLowerCase(), p.getName().toLowerCase());
			
			sj.set(p.getName().toLowerCase(), player.getName().toLowerCase()+"和"+p.getName().toLowerCase());
			sj.set(player.getName().toLowerCase(), player.getName().toLowerCase()+"和"+p.getName().toLowerCase());
			
			PLAYERDATA2.set("KaiTong.共享仓库", Boolean.valueOf(false));
			PLAYERDATA2.set("经验分享功能", Boolean.valueOf(false));
			PLAYERDATA2.set("双方无伤功能", Boolean.valueOf(false));
			
			try {
				yq.save(F);
				PLAYERDATA2.save(FS2);
				yilist.save(Fss);
				sj.save(Fsss);
			} catch (IOException es) {
			  es.printStackTrace();
			}
			
			
			Youyi_value.addYouyiValue(p, Main.getInstance().getConfig().getInt("YouyiValue.Establish.vaule"));
			
			return false;
		}else if (args.length == 1 && args[0].equals("unaccept")) {
			
			if (yq.get(p.getName().toLowerCase()) == null){
    	    	p.sendMessage("§f友谊 §b> §7暂未收到任何玩家的邀请");
    	    	return false;
    	    }
			
			String name = (String) yq.get(p.getName().toLowerCase());
            Player player = Bukkit.getPlayer(name);
			
			p.sendMessage("§f友谊 §b> §a已拒绝 §c"+player.getName()+" §a玩家的邀请");
			player.sendMessage("§f友谊 §b> §7玩家 §c"+p.getName()+" §c§l拒绝§7与你建立友谊小船");
			
			yq.set(p.getName().toLowerCase(), null);
			
			try {
				yq.save(F);
			} catch (IOException es) {
			  es.printStackTrace();
			}
		}else if (args.length == 1 && args[0].equals("chat")) {
			String name = PLAYERDATA.getString(p.getName().toLowerCase());
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
				p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
				return false;
			}
			
			if (!Bukkit.getOfflinePlayer(name).isOnline()){
				p.sendMessage("§f友谊 §b> §3你的好友 §c"+PLAYERDATA.getString(p.getName().toLowerCase())+" §3不在线无法开启此模式");
				return false;
			}
			
			if (Youyi_Chat.youyichat.get(p.getName().toLowerCase()) == false){
			Youyi_Chat.youyichat.put(p.getName().toLowerCase(), Boolean.valueOf(true));
			p.sendMessage("§f友谊 §b> §3已进入 §6小船聊天模式 §3其他玩家将看不见你所发消息,仅你的好友 §c"+PLAYERDATA.getString(p.getName().toLowerCase())+" §3可见");
			p.sendMessage("§f友谊 §b> §3再次输入 §e/yyi chat §3即可退出此模式");
			if (Bukkit.getOfflinePlayer(name).isOnline()){
			if (Youyi_Chat.youyichat.get(name) == false){
			Bukkit.getOfflinePlayer(name).getPlayer().sendMessage("§f友谊 §b> §3你的好友 §c"+p.getName()+" §3已进入 §6小船聊天模式");
			}
			String names = sj.getString(p.getName().toLowerCase());
			if (svaule.getInt(names+".Chat") > 0){
	            int Time = Main.getInstance().getConfig().getInt("YouyiValue.Chat.time");
	            int vaule = Main.getInstance().getConfig().getInt("YouyiValue.Chat.vaule");
	            p.sendMessage("§f小船聊天模式 §b> §a使用此聊天模式与 §c"+name+" §a聊天满 §c"+Time+" §a分钟,即可增加友谊值 §c"+vaule+" §a点");
	            new BukkitRunnable() {
	        		@Override
	        		public void run() {
	        			if (!p.isOnline()){
	        				return;
	        			}
	        			if (svaule.getInt(names+".Chat") > 0){
	        			if (Youyi_Chat.youyichat.get(p.getName().toLowerCase()) != false){
	        			   Youyi_value.addYouyiValue(p, vaule);
	        			   svaule.set(names+".Chat", svaule.getInt(names+".Chat")-Integer.valueOf(1));
	       			       try {
	       			    	  svaule.save(FSa);
	       		           } catch (IOException var8) {
	       		              var8.printStackTrace();
	       		           }
	        			}
	        		  }
	        		}
	            }.runTaskLater(Main.getInstance(), (20*60)*Time);
			}
			}
			}else{
			p.sendMessage("§f友谊 §b> §3已退出 §6小船聊天模式");
			Youyi_Chat.youyichat.put(p.getName().toLowerCase(), Boolean.valueOf(false));
			}
		}else if (args.length == 2 && args[0].equals("switch")) {
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
				p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
				return false;
			}
			
			if (args[1].equalsIgnoreCase("exp")) {
				
				if (!Main.getInstance().getConfig().getBoolean("Expshare.enable")){
	        		p.sendMessage("§f友谊 §b> §7服务器未开启此功能");
	        		return false;
	        	}
				
				if (PLAYERDATA.getBoolean("经验分享功能") == false){
					PLAYERDATA.set("经验分享功能", Boolean.valueOf(true));
					p.sendMessage("§f友谊 §b> §6经验分享功能 §a已开启");
					p.sendMessage("§f友谊 §b> §3输入 §e/yyi exp §3查看经验分享功能详细");
					p.sendMessage("§f友谊 §b> §3再次输入 §e/yyi switch exp §3则关闭此功能");
				}else{
					PLAYERDATA.set("经验分享功能", Boolean.valueOf(false));
					p.sendMessage("§f友谊 §b> §6经验分享功能 §7已关闭");
					p.sendMessage("§f友谊 §b> §3再次输入 §e/yyi switch exp §3则开启此功能");
				}
				try {
					PLAYERDATA.save(FS);
				} catch (IOException es) {
				  es.printStackTrace();
				}
			}
			if (args[1].equalsIgnoreCase("baohu")) {
				
				if (!Main.getInstance().getConfig().getBoolean("BaoHu.enable") && !Main.getInstance().getConfig().getBoolean("BaoHu.Flan")){
	        		p.sendMessage("§f友谊 §b> §7服务器未开启此功能");
	        		return false;
	        	}
				
				if (PLAYERDATA.getBoolean("双方无伤功能") == false){
					PLAYERDATA.set("双方无伤功能", Boolean.valueOf(true));
					p.sendMessage("§f友谊 §b> §6双方无伤功能 §a已开启");
					p.sendMessage("§f友谊 §b> §3输入 §e/yyi baohu §3查看双方相互攻击无伤功能详细");
					p.sendMessage("§f友谊 §b> §3再次输入 §e/yyi switch baohu §3则关闭此功能");
				}else{
					PLAYERDATA.set("双方无伤功能", Boolean.valueOf(false));
					p.sendMessage("§f友谊 §b> §6双方无伤功能 §7已关闭");
					p.sendMessage("§f友谊 §b> §3再次输入 §e/yyi switch baohu §3则开启此功能");
				}
				try {
					PLAYERDATA.save(FS);
				} catch (IOException es) {
				  es.printStackTrace();
				}
			}
		}else if (args.length == 1 && args[0].equals("baohu")) {
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
				p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
				return false;
			}
			
			if (!Main.getInstance().getConfig().getBoolean("BaoHu.enable") && !Main.getInstance().getConfig().getBoolean("BaoHu.Flan")){
        		p.sendMessage("§f友谊 §b> §7服务器未开启此功能");
        		return false;
        	}
			
			String is = PLAYERDATA.getString("双方无伤功能");
			
			p.sendMessage("§6Yoiyi - 友谊小船   §6§o§lBy.二傻");
			p.sendMessage("§7§o插件版本: §c§o1.0");
			p.sendMessage(" ");
			
			if (!PLAYERDATA.getBoolean("双方无伤功能")){
				p.sendMessage("§3小船是否已开启此功能: "+is.replace("true", "§a§l已开启").replace("false", "§7§l未开启"));
				p.sendMessage("§3输入 §e/yyi switch baohu §3来§a开启§3此功能");
				p.sendMessage(" ");
				p.sendMessage("§7§o说明:");
				p.sendMessage(" §7§o此功能开启后,双方相互攻击都不会受到伤害");
				return false;
			}
			
			p.sendMessage("§3小船是否已开启此功能: "+is.replace("true", "§a§l已开启").replace("false", "§7§l未开启"));
			p.sendMessage("§3输入 §e/yyi switch baohu §3来§c关闭§3此功能");
			p.sendMessage(" ");
			p.sendMessage("§7§o说明:");
			p.sendMessage(" §7§o此功能开启后,双方相互攻击都不会受到伤害");
			
        }else if (args.length == 1 && args[0].equals("exp")) {
        	
        	if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
				p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
				return false;
			}
        	
        	if (!Main.getInstance().getConfig().getBoolean("Expshare.enable")){
        		p.sendMessage("§f友谊 §b> §7服务器未开启此功能");
        		return false;
        	}
        	
        	String name = PLAYERDATA.getString(p.getName().toLowerCase());
        	int bj = Main.getInstance().getConfig().getInt("Expshare.radius");
        	int bl = Main.getInstance().getConfig().getInt("Expshare.percent");
        	int sy = 100-bl;
        	String is = PLAYERDATA.getString("经验分享功能");
        	
        	p.sendMessage("§6Yoiyi - 友谊小船   §6§o§lBy.二傻");
			p.sendMessage("§7§o插件版本: §c§o1.0");
			p.sendMessage(" ");
			
			if (!PLAYERDATA.getBoolean("经验分享功能")){
			p.sendMessage("§3小船是否已开启此功能: "+is.replace("true", "§a§l已开启").replace("false", "§7§l未开启"));
			p.sendMessage("§3输入 §e/yyi switch exp §3来§a开启§3此功能");
			p.sendMessage(" ");
			p.sendMessage("§7§o说明:");
			p.sendMessage(" §7§o分享经验§3§o前提要求双方必须§c§o"+bj+"§3§o格半径内§7§o才会§7§o进行§a§o经验分享");
			p.sendMessage(" §7§o分享公式: §c§o经验%百分比=分享给对方经验");
			p.sendMessage(" §7§o也就是分享百分之§c§o"+bl+"§7§o经验给对方,剩余百分之§c§o"+sy+"§3§o经验归自己");
			return false;
            }
			
			p.sendMessage("§3小船是否已开启此功能: "+is.replace("true", "§a§l已开启").replace("false", "§7§l未开启"));
			p.sendMessage("§3输入 §e/yyi switch exp §3来§c关闭§3此功能");
			p.sendMessage(" ");
			p.sendMessage("§3分享给: §c"+name);
			p.sendMessage("§3经验分享半径: §c"+bj);
			p.sendMessage("§3经验分享百分比: §c"+bl);
			p.sendMessage(" ");
			p.sendMessage("§7§o说明:");
			p.sendMessage(" §7§o分享经验§3§o前提要求双方必须§c§o"+bj+"§3§o格半径内§7§o才会§7§o进行§a§o经验分享");
			p.sendMessage(" §7§o分享公式: §c§o经验%百分比=分享给对方经验");
			p.sendMessage(" §7§o也就是分享百分之§c§o"+bl+"§7§o经验给对方,剩余百分之§c§o"+sy+"§3§o经验归自己");
			
        }else if (args.length == 1 && args[0].equals("jiesan")) {
        	if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
				p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
				return false;
			}
        	p.sendMessage("§6Yoiyi - 友谊小船   §6§o§lBy.二傻");
			p.sendMessage("§7§o插件版本: §c§o1.0");
			p.sendMessage(" ");
        	p.sendMessage("§7§o解散小船说明:");
        	p.sendMessage("  §c§o1.§7§o小船解散后所有数据都将全§c§o零§7§o(友谊值/仓库/等)");
        	p.sendMessage("  §c§o2.§7§o小船解散后已共享的§3§o金币及点卷将平分");
        	p.sendMessage("  §c§o3.§7§o友谊值奖励已领取过的§c§o无法再下次重建小船后再次领取");
        	p.sendMessage("  §c§o4.§7§o发起解散后需等待另一方同意解散才会解散");
        	p.sendMessage("  §7§o(特殊情况请联系管理员进行强制解散)");
        	p.sendMessage(" ");
        	p.sendMessage("§3输入 §e/yyi jiesan 1 §3发送解散请求");
        	p.sendMessage("§3输入 §e/yyi jiesan 2 §3同意解散");
        }else if (args.length == 2 && args[0].equals("jiesan")) {
        	if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
				p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
				return false;
			}
        	String name = PLAYERDATA.getString(p.getName().toLowerCase());
        	if (args[1].equalsIgnoreCase("1")) {
        		
        		if (!Bukkit.getOfflinePlayer(name).isOnline()){
        			p.sendMessage("§f友谊 §b> §7对方不在线");
        			p.sendMessage("§7§o(特殊情况请联系管理员进行强制解散)");
        			return false;
        		}
        		
        		
        		p.sendMessage("§f友谊 §b> §c已发送解散小船申请给 "+name);
        		p.sendMessage("§7§o(特殊情况请联系管理员进行强制解散)");
        		
        		Bukkit.getPlayer(name).sendMessage("§f友谊 §b> §c玩家 "+p.getName()+" 发送解散小船请求给你");
        		Bukkit.getPlayer(name).sendMessage("§f友谊 §b> §c输入 §e/yyi jiesan §c查看解散说明");
        		
        		jiesan.set(name, p.getName().toLowerCase());
        		
        		try {
        			jiesan.save(jiesans);
				} catch (IOException es) {
				  es.printStackTrace();
				}
        	}
        	if (args[1].equalsIgnoreCase("2")) {
        		
        		double vault = Double.valueOf(PLAYERDATA_CC.getDouble("Vault"))/2;
        		int points = Integer.valueOf(PLAYERDATA_CC.getInt("Points"))/2;
        		
        		if (!Bukkit.getOfflinePlayer(name).isOnline()){
        			p.sendMessage("§f友谊 §b> §7对方不在线");
        			p.sendMessage("§7§o(特殊情况请联系管理员进行强制解散)");
        			return false;
        		}
        		
        		if (jiesan.get(p.getName().toLowerCase()) == null){
        			p.sendMessage("§f友谊 §b> §a你与  §c"+name+" §a友谊很好,还未接到 §c"+name+" §a的解散请求哦~");
        			return false;
        		}
        		
        		p.sendMessage("§f友谊 §b> §c友谊小船成功解散,友谊的小船说翻就翻");
        		if (jbsq.getBoolean("是否已同意申请")){
        		p.sendMessage("§f友谊 §b> §6平分共享财产获得金币: §c"+vault);
        		Economys.paymoney(p, PLAYERDATA_CC.getDouble("Vault"));
        		Economys.econ.depositPlayer(p, vault);
        		}
        		if (djsq.getBoolean("是否已同意申请")){
        		p.sendMessage("§f友谊 §b> §6平分共享财产获得点卷: §c"+points);
        		Points.points.getAPI().set(p.getUniqueId(), points);
        		}
        		
        		
        		Bukkit.getPlayer(name).sendMessage("§f友谊 §b> §c对方同意了你的解散请求,友谊的小船说翻就翻");
        		if (jbsq.getBoolean("是否已同意申请")){
        		Bukkit.getPlayer(name).sendMessage("§f友谊 §b> §6平分共享财产获得金币: §c"+vault);
        		Economys.paymoney(Bukkit.getPlayer(name), PLAYERDATA_CC.getDouble("Vault"));
        		Economys.econ.depositPlayer(Bukkit.getPlayer(name), vault);
        		}
        		if (djsq.getBoolean("是否已同意申请")){
        		Bukkit.getPlayer(name).sendMessage("§f友谊 §b> §6平分共享财产获得点卷: §c"+points);
        		 Points.points.getAPI().set(Bukkit.getPlayer(name).getUniqueId(), points);
        		}
        		
        		
        		Bukkit.broadcastMessage("§f友谊 §b> §a玩家 "+p.getName()+" 与 "+ Bukkit.getPlayer(name).getName() +" §a的友谊小船说翻就翻 §c(解散)");
        		
        		
        		Main.DelYml("/data/"+sj.getString(p.getName().toLowerCase())+"/", "小船数据");
        		Main.DelYml("/data/"+sj.getString(p.getName().toLowerCase())+"/", "共享财产数据");
        		Main.DelYml("/data/"+sj.getString(p.getName().toLowerCase())+"/", "解散");
        		Main.DelYml("/data/"+sj.getString(p.getName().toLowerCase())+"/", "金币共享申请");
        		Main.DelYml("/data/"+sj.getString(p.getName().toLowerCase())+"/", "点卷共享申请");
        		
        		File dirFile = new File(Main.getInstance().getDataFolder()+"/data/"+sj.getString(p.getName().toLowerCase()));
        		dirFile.delete();
        		
        		
        		Object list = yilist.getStringList("List");
    			((List)list).remove(Bukkit.getPlayer(name).getName().toLowerCase());
    			((List)list).remove(p.getName().toLowerCase());
    			yilist.set("List", list);
    			
    			sj.set(p.getName().toLowerCase(), null);
    			sj.set(name, null);
    			
		        
		        try {
					yq.save(F);
					yilist.save(Fss);
					sj.save(Fsss);
				} catch (IOException es) {
				  es.printStackTrace();
				}
        	}
		}else if (args.length == 1 && args[0].equals("reload")) {
			if (!p.isOp()){
				p.sendMessage("§f友谊 §b> §c无权限");
				return false;
			}
			p.sendMessage("§f友谊 §b> §6Config.yml §a配置已重载成功");
			p.sendMessage("§f友谊 §b> §7其余数据配置会自动重载");
			Main.getInstance().reloadConfig();
		}
		return false;
	}
}
