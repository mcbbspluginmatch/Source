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
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/С������.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
		
	    File Fss = new File(Main.getInstance().getDataFolder() + "/�ѽ���С�������.yml");
	    YamlConfiguration yilist = YamlConfiguration.loadConfiguration(Fss);
	    
	    
		File FSsss = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/����Ʋ�����.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FSsss);
	    
	    double MoneyCost = Economys.econ.getBalance(p);
	    
	    if(args.length == 0) {
			p.sendMessage("��6Yoiyi - ����С��   ��6��o��lBy.��ɵ");
			p.sendMessage("��7��o����汾: ��c��o1.0");
			p.sendMessage("��b/yyck ��7- ��3�ֿ�ָ����ϸ");
			p.sendMessage("��b/yyck kaitong ��7- ��3��ͨ���깲��ֿ�");
			p.sendMessage("��b/yyck open ��7- ��3�����깲��ֿ�");
			p.sendMessage(" ");
			p.sendMessage("��7��oע������:");
			p.sendMessage(" ��c��o1.��7��o�޷�˫��ͬʱ�򿪹���ֿ�");
			return false;
		}else if (args.length == 1) {
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
				p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
				return false;
			}
			
			if (args[0].equalsIgnoreCase("open")) {
				String name = PLAYERDATA.getString(p.getName().toLowerCase());
				
				if (Bukkit.getOfflinePlayer(name).isOnline()){
				if (Bukkit.getOfflinePlayer(name).getPlayer().getOpenInventory().getTitle().equals(Main.getInstance().getConfig().getString("Warehouse.Name").replace("&", "��"))){
					p.sendMessage("��f���� ��b> ��c"+Bukkit.getOfflinePlayer(name).getName()+" ��7����ʹ�� ��6��l����ֿ� ��7�����ʹ�����");
					return false;
				}
				}
				
				if (apivault == true){
				if (!PLAYERDATA.getBoolean("KaiTong.����ֿ�")){
				p.sendMessage(Main.getInstance().getConfig().getString("Warehouse.Message.wKaiTong").replace("&", "��"));
				return false;
				}
				}
				
				File pcFile = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/�ֿ�����.yml");
                YamlConfiguration ck = YamlConfiguration.loadConfiguration(pcFile);
                
                int size = Main.getInstance().getConfig().getInt("Warehouse.size");
                
                Inventory inv = Bukkit.createInventory((InventoryHolder)null, size, Main.getInstance().getConfig().getString("Warehouse.Name").replace("&", "��"));
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
        			id.setDisplayName("��6��l�洢 ��e��l���  ��7(��a������� ��3�Ҽ�ȡ����7)");
        			lore = new ArrayList<String>();
        			for(String line:Main.getInstance().getConfig().getStringList("Warehouse.item.Money")){
        				lore.add(line.replace("%money%", PLAYERDATA_CC.getInt("�ֿ��Ž������")+"").replace("&", "��"));
        			}
        			id.setLore(lore);
        			item.setItemMeta(id);
        			inv.setItem(size-2, item);
        			
        			
        			item = new ItemStack(Material.DIAMOND, 1, (short) 0);
        			id.setDisplayName("��6��l�洢 ��b��l���  ��7(��a������� ��3�Ҽ�ȡ����7)");
        			lore = new ArrayList<String>();
        			for(String line:Main.getInstance().getConfig().getStringList("Warehouse.item.Points")){
        				lore.add(line.replace("%points%", PLAYERDATA_CC.getInt("�ֿ��ŵ������")+"").replace("&", "��"));
        			}
        			id.setLore(lore);
        			item.setItemMeta(id);
        			inv.setItem(size-1, item);
        			
                }


                p.closeInventory();
                p.openInventory(inv);
                p.sendMessage(Main.getInstance().getConfig().getString("Warehouse.Message.Open").replace("&", "��"));

				return false;
			    }
			
			if (args[0].equalsIgnoreCase("kaitong")) {
				
				if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
					p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
					p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
					return false;
				}
				
				if (apivault == false){
					p.sendMessage("��f���� ��b> ��7δ����Vault���,���迪ͨ�ֿ⼴��ʹ�ù���ֿ⹦��");
					return false;
				}
				
				if (PLAYERDATA.getBoolean("KaiTong.����ֿ�")){
					p.sendMessage("��f���� ��b> ��7�ѿ�ͨ����ֿ⹦��,�������¿�ͨ!");
					return false;
				}
				
				if (MoneyCost < Main.getInstance().getConfig().getInt("Warehouse.Kaitong.Money")){
					p.sendMessage(Main.getInstance().getConfig().getString("Warehouse.Message.sbKaiTong").replace("%money%", Main.getInstance().getConfig().getInt("Warehouse.Kaitong.Money")+"").replace("&", "��"));
					return false;
				}
				
				p.sendMessage(Main.getInstance().getConfig().getString("Warehouse.Message.cgKaiTong").replace("%money%", Main.getInstance().getConfig().getInt("Warehouse.Kaitong.Money")+"").replace("&", "��"));
				
				PLAYERDATA.set("KaiTong.����ֿ�", Boolean.valueOf(true));
				
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
