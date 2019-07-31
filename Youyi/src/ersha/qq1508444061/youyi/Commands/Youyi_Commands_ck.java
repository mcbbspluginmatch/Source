package ersha.qq1508444061.youyi.Commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

import ersha.qq1508444061.youyi.Main;
import ersha.qq1508444061.youyi.Util.Economys;

public class Youyi_Commands_ck implements CommandExecutor {

	public static boolean apivault = false;
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		Player p = (Player) sender;
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/玩家数据.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/小船数据.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
		
	    File Fss = new File(Main.getInstance().getDataFolder() + "/已建立小船的玩家.yml");
	    YamlConfiguration yilist = YamlConfiguration.loadConfiguration(Fss);
	    
	    
		File FSsss = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/共享财产数据.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FSsss);
	    
	    double MoneyCost = Economys.econ.getBalance(p);
	    
	    if(args.length == 0) {
			p.sendMessage("§6Yoiyi - 友谊小船   §6§o§lBy.二傻");
			p.sendMessage("§7§o插件版本: §c§o1.0");
			p.sendMessage("§b/yyck §7- §3仓库指令详细");
			p.sendMessage("§b/yyck kaitong §7- §3开通友谊共享仓库");
			p.sendMessage("§b/yyck open §7- §3打开友谊共享仓库");
			p.sendMessage(" ");
			p.sendMessage("§7§o注意事项:");
			p.sendMessage(" §c§o1.§7§o无法双方同时打开共享仓库");
			return false;
		}else if (args.length == 1) {
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
				p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
				return false;
			}
			
			if (args[0].equalsIgnoreCase("open")) {
				String name = PLAYERDATA.getString(p.getName().toLowerCase());
				
				if (Bukkit.getOfflinePlayer(name).isOnline()){
				if (Bukkit.getOfflinePlayer(name).getPlayer().getOpenInventory().getTitle().equals(Main.getInstance().getConfig().getString("Warehouse.Name").replace("&", "§"))){
					p.sendMessage("§f友谊 §b> §c"+Bukkit.getOfflinePlayer(name).getName()+" §7正在使用 §6§l共享仓库 §7请等他使用完吧");
					return false;
				}
				}
				
				if (apivault == true){
				if (!PLAYERDATA.getBoolean("KaiTong.共享仓库")){
				p.sendMessage(Main.getInstance().getConfig().getString("Warehouse.Message.wKaiTong").replace("&", "§"));
				return false;
				}
				}
				
				File pcFile = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/仓库数据.yml");
                YamlConfiguration ck = YamlConfiguration.loadConfiguration(pcFile);
                
                int size = Main.getInstance().getConfig().getInt("Warehouse.size");
                
                Inventory inv = Bukkit.createInventory((InventoryHolder)null, size, Main.getInstance().getConfig().getString("Warehouse.Name").replace("&", "§"));
                if(!pcFile.exists()) {
                    try {
                            pcFile.createNewFile();
                    } catch (IOException var11) {
                            var11.printStackTrace();
                    }
                }
                
                
                for(int islot = 0; islot < 54; ++islot) {
                	
                    if(ck.getItemStack(sj.get(p.getName().toLowerCase()) + "." + islot) != null) {
                        inv.setItem(islot, ck.getItemStack(sj.get(p.getName().toLowerCase()) + "." + islot));
                    }
                    
                    ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1, (short) 0);
                    ItemMeta id = item.getItemMeta();
        			ArrayList<String> lore = new ArrayList<String>();
        			id.setDisplayName("§6§l存储 §e§l金币  §7(§a左键存入 §3右键取出§7)");
        			lore = new ArrayList<String>();
        			for(String line:Main.getInstance().getConfig().getStringList("Warehouse.item.Money")){
        				lore.add(line.replace("%money%", PLAYERDATA_CC.getInt("仓库存放金币数量")+"").replace("&", "§"));
        			}
        			id.setLore(lore);
        			item.setItemMeta(id);
        			inv.setItem(size-2, item);
        			
        			
        			item = new ItemStack(Material.DIAMOND, 1, (short) 0);
        			id.setDisplayName("§6§l存储 §b§l点卷  §7(§a左键存入 §3右键取出§7)");
        			lore = new ArrayList<String>();
        			for(String line:Main.getInstance().getConfig().getStringList("Warehouse.item.Points")){
        				lore.add(line.replace("%points%", PLAYERDATA_CC.getInt("仓库存放点卷数量")+"").replace("&", "§"));
        			}
        			id.setLore(lore);
        			item.setItemMeta(id);
        			inv.setItem(size-1, item);
        			
                }


                p.closeInventory();
                p.openInventory(inv);
                p.sendMessage(Main.getInstance().getConfig().getString("Warehouse.Message.Open").replace("&", "§"));

				return false;
			    }
			
			if (args[0].equalsIgnoreCase("kaitong")) {
				
				if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
					p.sendMessage("§f友谊 §b> §c你还未与任何一位玩家建立友谊小船呢");
					p.sendMessage("§f友谊 §b> §a请输入 §e/yyi yq <玩家> §a来邀请建立友谊小船");
					return false;
				}
				
				if (apivault == false){
					p.sendMessage("§f友谊 §b> §7未兼容Vault插件,无需开通仓库即可使用共享仓库功能");
					return false;
				}
				
				if (PLAYERDATA.getBoolean("KaiTong.共享仓库")){
					p.sendMessage("§f友谊 §b> §7已开通共享仓库功能,无需重新开通!");
					return false;
				}
				
				if (MoneyCost < Main.getInstance().getConfig().getInt("Warehouse.Kaitong.Money")){
					p.sendMessage(Main.getInstance().getConfig().getString("Warehouse.Message.sbKaiTong").replace("%money%", Main.getInstance().getConfig().getInt("Warehouse.Kaitong.Money")+"").replace("&", "§"));
					return false;
				}
				
				p.sendMessage(Main.getInstance().getConfig().getString("Warehouse.Message.cgKaiTong").replace("%money%", Main.getInstance().getConfig().getInt("Warehouse.Kaitong.Money")+"").replace("&", "§"));
				
				PLAYERDATA.set("KaiTong.共享仓库", Boolean.valueOf(true));
				
				try {
					PLAYERDATA.save(FS);
				} catch (IOException es) {
				  es.printStackTrace();
				}
				
				Economys.paymoney(p, Main.getInstance().getConfig().getInt("Warehouse.Kaitong.Money"));
			}
		}
		return false;
	}
}
