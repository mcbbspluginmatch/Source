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
		
		File F = new File(Main.getInstance().getDataFolder() + "/�����б�.yml");
	    YamlConfiguration yq = YamlConfiguration.loadConfiguration(F);
	    
	    File Fss = new File(Main.getInstance().getDataFolder() + "/�ѽ���С�������.yml");
	    YamlConfiguration yilist = YamlConfiguration.loadConfiguration(Fss);
	    
	    //player��player
	    File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/С������.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
	    
	    File FSsss = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/����Ʋ�����.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FSsss);
		
	    File jiesans = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/��ɢ.yml");
	    YamlConfiguration jiesan = YamlConfiguration.loadConfiguration(jiesans);
	    
	    File Fas = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/��ҹ�������.yml");
	    YamlConfiguration jbsq = YamlConfiguration.loadConfiguration(Fas);
	    
	    File Fsa = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/���������.yml");
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
		
			
			p.sendMessage("��6Yoiyi - ����С��   ��6��o��lBy.��ɵ");
			p.sendMessage("��7��o����汾: ��c��o1.0");
			if (PLAYERDATA.get(p.getName().toLowerCase()) != null){
			p.sendMessage("��3������ ��c"+PLAYERDATA.getString(p.getName().toLowerCase())+" ��3������6��l����С��");
			p.sendMessage("��3���� ��c"+PLAYERDATA.getString(p.getName().toLowerCase())+" ��3Ŀǰ������ֵ: ��c"+PLAYERDATA.getInt("YouyiValue"));
			}else{
			p.sendMessage("��3�㻹δ���κ�һλ��ҽ�����6��l����С��");
			}
			p.sendMessage("��b/yyi ��7- ��3ָ����ϸ");
			p.sendMessage("��b/yyi yq <player> ��7- ��3������ҽ���С��");
			p.sendMessage("��b/yyi accept ��7- ��3������������");
			p.sendMessage("��b/yyi unaccept ��7- ��3�ܾ���������");
			p.sendMessage("��b/yyi info ��7- ��3����Ϣ���(����ֵ����)");
			p.sendMessage("��b/yyi chat ��7- ��3�л���С������ģʽ");
			p.sendMessage("��c/yyi jiesan ��7- ��3��ɢС����ϸ˵��");
			p.sendMessage("��6/yyi reload ��7- ��3���ز������");
			p.sendMessage("��e/yyi exp ��7- ��3�鿴�����鹦����ϸ");
			p.sendMessage("��e/yyi baohu ��7- ��3�鿴˫���໥�������˹�����ϸ");
			p.sendMessage("��e/yyi switch <exp/baohu>");
			p.sendMessage("  ��7- ��3exp ��7˫����þ��������");
			p.sendMessage("  ��7- ��3baohu ��7˫�����๥�����˹���");
			p.sendMessage("��e/yyck ��7- ��3���òֿ⹦����ϸ");
			p.sendMessage("��e/yygx ��7- ��3����Ʋ�������ϸ");
			return false;
		}
		
		if (args.length == 2 && args[0].equals("yq")) {
			Player player = Bukkit.getPlayer(args[1]);
			if (player == p){
				p.sendMessage("��f���� ��b> ��c���������Լ�!");
				return false;
			}
			
			for (String list : yilist.getStringList("List"))
			if (list.contains(p.getName().toLowerCase())){
				p.sendMessage("��f���� ��b> ��c���Ѿ�����һλ��ҽ���С����Ŷ");
				p.sendMessage("��f���� ��b> ��c�޷��ٴ�����һλ��ҽ���");
				return false;
			}
			
			for (String list : yilist.getStringList("List"))
			if (list.contains(player.getName().toLowerCase())){
				p.sendMessage("��f���� ��b> ��c�Է�����˽���С����,��ѡ����һλ��Ұ�");
				return false;
			}
			
			if (yq.get(p.getName().toLowerCase()) != null){
				p.sendMessage("��f���� ��b> ��7��ո��Ѿ���ĳһ����ҷ���������");
				p.sendMessage("��f���� ��b> ��7�����ĵȴ��Է�����");
				return false;
			}
			
			if (!Bukkit.getOfflinePlayer(args[1]).isOnline()){
				p.sendMessage("��f���� ��b> ��7��������Ķ��󲻴��ڻ��߲�����!");
				return false;
			}
			
			yq.set(player.getName().toLowerCase(), p.getName().toLowerCase());
			
			p.sendMessage("��f���� ��b> ��a�ѷ��ͽ�������С������,��ȴ��Է�����!");
			
			player.sendMessage("��f���� ��b> ��a��� ��c"+p.getName()+" ��a���㷢����������С������");
			player.sendMessage("��f���� ��b> ��a������ ��e/yyi accept ��a�������� ��7(���� ��c20 ��7�뽫�Զ��ܾ�)");
			player.sendMessage("��f���� ��b> ��a������ ��e/yyi unaccept ��c�ܾ����� ��7(���� ��c20 ��7�뽫�Զ��ܾ�)");
			
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
				p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
				p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
				return false;
			}
			
			Inventory inv = Bukkit.createInventory((InventoryHolder)null, 9, Main.getInstance().getConfig().getString("Set.Info.Name").replace("&", "��"));
			
			ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1, (short) 0);
            ItemMeta id = item.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			id.setDisplayName(Main.getInstance().getConfig().getString("Set.Info.sjName").replace("&", "��"));
			lore = new ArrayList<String>();
			for(String line:Main.getInstance().getConfig().getStringList("Set.Info.Lore")){
				lore.add(line
						.replace("%player%", PLAYERDATA.getString(p.getName().toLowerCase()))
						.replace("%value%", PLAYERDATA.getInt("YouyiValue")+"")
						.replace("%money_ck%", PLAYERDATA_CC.getInt("�ֿ��Ž������")+"")
						.replace("%points_ck%", PLAYERDATA_CC.getInt("�ֿ��ŵ������")+"")
						.replace("%money_gx%", jbsq.getBoolean("�Ƿ���ͬ������") ? "�ѹ���" : "δ����")
						.replace("%points_gx%", djsq.getBoolean("�Ƿ���ͬ������") ? "�ѹ���" : "δ����")
						.replace("%exp_switch%", PLAYERDATA.getBoolean("���������") ? "�ѿ���" : "δ����")
						.replace("%baohu_switch%", PLAYERDATA.getBoolean("˫�����˹���") ? "�ѿ���" : "δ����")
						.replace("&", "��")
						);
			}
			id.setLore(lore);
			item.setItemMeta(id);
			inv.setItem(0, item);
			
			item = new ItemStack(Material.CHEST, 1, (short) 0);
            id = item.getItemMeta();
			id.setDisplayName(Main.getInstance().getConfig().getString("Set.Info.YouyiValue.Name").replace("&", "��"));
			lore = new ArrayList<String>();
			for(String line:Main.getInstance().getConfig().getStringList("Set.Info.YouyiValue.Lore")){
				lore.add(line
						.replace("%player%", PLAYERDATA.getString(p.getName().toLowerCase()))
						.replace("%value%", PLAYERDATA.getInt("YouyiValue")+"")
						.replace("%money_ck%", PLAYERDATA_CC.getInt("�ֿ��Ž������")+"")
						.replace("%points_ck%", PLAYERDATA_CC.getInt("�ֿ��ŵ������")+"")
						.replace("%money_gx%", jbsq.getBoolean("�Ƿ���ͬ������") ? "�ѹ���" : "δ����")
						.replace("%points_gx%", djsq.getBoolean("�Ƿ���ͬ������") ? "�ѹ���" : "δ����")
						.replace("%exp_switch%", PLAYERDATA.getBoolean("���������") ? "�ѿ���" : "δ����")
						.replace("%baohu_switch%", PLAYERDATA.getBoolean("˫�����˹���") ? "�ѿ���" : "δ����")
						.replace("&", "��")
						);
			}
			id.setLore(lore);
			item.setItemMeta(id);
			inv.setItem(8, item);
			
			item = new ItemStack(Material.SIGN, 1, (short) 0);
            id = item.getItemMeta();
			id.setDisplayName(Main.getInstance().getConfig().getString("Set.Info.Task.Name").replace("&", "��"));
			lore = new ArrayList<String>();
			for(String line:Main.getInstance().getConfig().getStringList("Set.Info.Task.Lore")){
				lore.add(line
						.replace("%player%", PLAYERDATA.getString(p.getName().toLowerCase()))
						.replace("%value%", PLAYERDATA.getInt("YouyiValue")+"")
						
						.replace("%exp%", svaule.getInt(sj.getString(p.getName().toLowerCase())+".Expshare")+"")
						.replace("%chat%", svaule.getInt(sj.getString(p.getName().toLowerCase())+".Chat")+"")
						
						.replace("%jl%", PLAYERDATA.get(p.getName().toLowerCase()) != null ? "�����" : "δ���")
						.replace("%gx_Money%", jbsq.getBoolean("�Ƿ���ͬ������") ? "�����" : "δ���")
						.replace("%gx_Points%", djsq.getBoolean("�Ƿ���ͬ������") ? "�����" : "δ���")
						.replace("&", "��")
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
    	    	p.sendMessage("��f���� ��b> ��7��δ�յ��κ���ҵ�����");
    	    	return false;
    	    }
			
			String name = (String) yq.get(p.getName().toLowerCase());
            Player player = Bukkit.getOfflinePlayer(name).getPlayer();
    	    
            
            File FS2 = new File(Main.getInstance().getDataFolder() + "/data/"+player.getName().toLowerCase()+"��"+p.getName().toLowerCase()+"/С������.yml");
    	    YamlConfiguration PLAYERDATA2 = YamlConfiguration.loadConfiguration(FS2);
    	    
			if (!player.isOnline()){
				p.sendMessage("��f���� ��b> ��7�Է�Ŀǰ������");
				return false;
			}

			p.sendMessage("��f���� ��b> ��a��ͬ�� ��c"+player.getName()+" ��a��ҵ�����");
			player.sendMessage("��f���� ��b> ��a��� ��c"+p.getName()+" ��a��ͬ�����㽨������С��");
			
			
			
			yq.set(p.getName().toLowerCase(), null);
			
			Object list = yilist.getStringList("List");
			((List)list).add(player.getName().toLowerCase());
			((List)list).add(p.getName().toLowerCase());
			yilist.set("List", list);
			
			PLAYERDATA2.set("A", player.getName().toLowerCase());
			PLAYERDATA2.set("B", p.getName().toLowerCase());
			
			PLAYERDATA2.set(p.getName().toLowerCase(), player.getName().toLowerCase());
			PLAYERDATA2.set(player.getName().toLowerCase(), p.getName().toLowerCase());
			
			sj.set(p.getName().toLowerCase(), player.getName().toLowerCase()+"��"+p.getName().toLowerCase());
			sj.set(player.getName().toLowerCase(), player.getName().toLowerCase()+"��"+p.getName().toLowerCase());
			
			PLAYERDATA2.set("KaiTong.����ֿ�", Boolean.valueOf(false));
			PLAYERDATA2.set("���������", Boolean.valueOf(false));
			PLAYERDATA2.set("˫�����˹���", Boolean.valueOf(false));
			
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
    	    	p.sendMessage("��f���� ��b> ��7��δ�յ��κ���ҵ�����");
    	    	return false;
    	    }
			
			String name = (String) yq.get(p.getName().toLowerCase());
            Player player = Bukkit.getPlayer(name);
			
			p.sendMessage("��f���� ��b> ��a�Ѿܾ� ��c"+player.getName()+" ��a��ҵ�����");
			player.sendMessage("��f���� ��b> ��7��� ��c"+p.getName()+" ��c��l�ܾ���7���㽨������С��");
			
			yq.set(p.getName().toLowerCase(), null);
			
			try {
				yq.save(F);
			} catch (IOException es) {
			  es.printStackTrace();
			}
		}else if (args.length == 1 && args[0].equals("chat")) {
			String name = PLAYERDATA.getString(p.getName().toLowerCase());
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
				p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
				return false;
			}
			
			if (!Bukkit.getOfflinePlayer(name).isOnline()){
				p.sendMessage("��f���� ��b> ��3��ĺ��� ��c"+PLAYERDATA.getString(p.getName().toLowerCase())+" ��3�������޷�������ģʽ");
				return false;
			}
			
			if (Youyi_Chat.youyichat.get(p.getName().toLowerCase()) == false){
			Youyi_Chat.youyichat.put(p.getName().toLowerCase(), Boolean.valueOf(true));
			p.sendMessage("��f���� ��b> ��3�ѽ��� ��6С������ģʽ ��3������ҽ���������������Ϣ,����ĺ��� ��c"+PLAYERDATA.getString(p.getName().toLowerCase())+" ��3�ɼ�");
			p.sendMessage("��f���� ��b> ��3�ٴ����� ��e/yyi chat ��3�����˳���ģʽ");
			if (Bukkit.getOfflinePlayer(name).isOnline()){
			if (Youyi_Chat.youyichat.get(name) == false){
			Bukkit.getOfflinePlayer(name).getPlayer().sendMessage("��f���� ��b> ��3��ĺ��� ��c"+p.getName()+" ��3�ѽ��� ��6С������ģʽ");
			}
			String names = sj.getString(p.getName().toLowerCase());
			if (svaule.getInt(names+".Chat") > 0){
	            int Time = Main.getInstance().getConfig().getInt("YouyiValue.Chat.time");
	            int vaule = Main.getInstance().getConfig().getInt("YouyiValue.Chat.vaule");
	            p.sendMessage("��fС������ģʽ ��b> ��aʹ�ô�����ģʽ�� ��c"+name+" ��a������ ��c"+Time+" ��a����,������������ֵ ��c"+vaule+" ��a��");
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
			p.sendMessage("��f���� ��b> ��3���˳� ��6С������ģʽ");
			Youyi_Chat.youyichat.put(p.getName().toLowerCase(), Boolean.valueOf(false));
			}
		}else if (args.length == 2 && args[0].equals("switch")) {
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
				p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
				return false;
			}
			
			if (args[1].equalsIgnoreCase("exp")) {
				
				if (!Main.getInstance().getConfig().getBoolean("Expshare.enable")){
	        		p.sendMessage("��f���� ��b> ��7������δ�����˹���");
	        		return false;
	        	}
				
				if (PLAYERDATA.getBoolean("���������") == false){
					PLAYERDATA.set("���������", Boolean.valueOf(true));
					p.sendMessage("��f���� ��b> ��6��������� ��a�ѿ���");
					p.sendMessage("��f���� ��b> ��3���� ��e/yyi exp ��3�鿴�����������ϸ");
					p.sendMessage("��f���� ��b> ��3�ٴ����� ��e/yyi switch exp ��3��رմ˹���");
				}else{
					PLAYERDATA.set("���������", Boolean.valueOf(false));
					p.sendMessage("��f���� ��b> ��6��������� ��7�ѹر�");
					p.sendMessage("��f���� ��b> ��3�ٴ����� ��e/yyi switch exp ��3�����˹���");
				}
				try {
					PLAYERDATA.save(FS);
				} catch (IOException es) {
				  es.printStackTrace();
				}
			}
			if (args[1].equalsIgnoreCase("baohu")) {
				
				if (!Main.getInstance().getConfig().getBoolean("BaoHu.enable") && !Main.getInstance().getConfig().getBoolean("BaoHu.Flan")){
	        		p.sendMessage("��f���� ��b> ��7������δ�����˹���");
	        		return false;
	        	}
				
				if (PLAYERDATA.getBoolean("˫�����˹���") == false){
					PLAYERDATA.set("˫�����˹���", Boolean.valueOf(true));
					p.sendMessage("��f���� ��b> ��6˫�����˹��� ��a�ѿ���");
					p.sendMessage("��f���� ��b> ��3���� ��e/yyi baohu ��3�鿴˫���໥�������˹�����ϸ");
					p.sendMessage("��f���� ��b> ��3�ٴ����� ��e/yyi switch baohu ��3��رմ˹���");
				}else{
					PLAYERDATA.set("˫�����˹���", Boolean.valueOf(false));
					p.sendMessage("��f���� ��b> ��6˫�����˹��� ��7�ѹر�");
					p.sendMessage("��f���� ��b> ��3�ٴ����� ��e/yyi switch baohu ��3�����˹���");
				}
				try {
					PLAYERDATA.save(FS);
				} catch (IOException es) {
				  es.printStackTrace();
				}
			}
		}else if (args.length == 1 && args[0].equals("baohu")) {
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
				p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
				return false;
			}
			
			if (!Main.getInstance().getConfig().getBoolean("BaoHu.enable") && !Main.getInstance().getConfig().getBoolean("BaoHu.Flan")){
        		p.sendMessage("��f���� ��b> ��7������δ�����˹���");
        		return false;
        	}
			
			String is = PLAYERDATA.getString("˫�����˹���");
			
			p.sendMessage("��6Yoiyi - ����С��   ��6��o��lBy.��ɵ");
			p.sendMessage("��7��o����汾: ��c��o1.0");
			p.sendMessage(" ");
			
			if (!PLAYERDATA.getBoolean("˫�����˹���")){
				p.sendMessage("��3С���Ƿ��ѿ����˹���: "+is.replace("true", "��a��l�ѿ���").replace("false", "��7��lδ����"));
				p.sendMessage("��3���� ��e/yyi switch baohu ��3����a������3�˹���");
				p.sendMessage(" ");
				p.sendMessage("��7��o˵��:");
				p.sendMessage(" ��7��o�˹��ܿ�����,˫���໥�����������ܵ��˺�");
				return false;
			}
			
			p.sendMessage("��3С���Ƿ��ѿ����˹���: "+is.replace("true", "��a��l�ѿ���").replace("false", "��7��lδ����"));
			p.sendMessage("��3���� ��e/yyi switch baohu ��3����c�رա�3�˹���");
			p.sendMessage(" ");
			p.sendMessage("��7��o˵��:");
			p.sendMessage(" ��7��o�˹��ܿ�����,˫���໥�����������ܵ��˺�");
			
        }else if (args.length == 1 && args[0].equals("exp")) {
        	
        	if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
				p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
				return false;
			}
        	
        	if (!Main.getInstance().getConfig().getBoolean("Expshare.enable")){
        		p.sendMessage("��f���� ��b> ��7������δ�����˹���");
        		return false;
        	}
        	
        	String name = PLAYERDATA.getString(p.getName().toLowerCase());
        	int bj = Main.getInstance().getConfig().getInt("Expshare.radius");
        	int bl = Main.getInstance().getConfig().getInt("Expshare.percent");
        	int sy = 100-bl;
        	String is = PLAYERDATA.getString("���������");
        	
        	p.sendMessage("��6Yoiyi - ����С��   ��6��o��lBy.��ɵ");
			p.sendMessage("��7��o����汾: ��c��o1.0");
			p.sendMessage(" ");
			
			if (!PLAYERDATA.getBoolean("���������")){
			p.sendMessage("��3С���Ƿ��ѿ����˹���: "+is.replace("true", "��a��l�ѿ���").replace("false", "��7��lδ����"));
			p.sendMessage("��3���� ��e/yyi switch exp ��3����a������3�˹���");
			p.sendMessage(" ");
			p.sendMessage("��7��o˵��:");
			p.sendMessage(" ��7��o�������3��oǰ��Ҫ��˫�������c��o"+bj+"��3��o��뾶�ڡ�7��o�Ż��7��o���С�a��o�������");
			p.sendMessage(" ��7��o����ʽ: ��c��o����%�ٷֱ�=������Է�����");
			p.sendMessage(" ��7��oҲ���Ƿ���ٷ�֮��c��o"+bl+"��7��o������Է�,ʣ��ٷ�֮��c��o"+sy+"��3��o������Լ�");
			return false;
            }
			
			p.sendMessage("��3С���Ƿ��ѿ����˹���: "+is.replace("true", "��a��l�ѿ���").replace("false", "��7��lδ����"));
			p.sendMessage("��3���� ��e/yyi switch exp ��3����c�رա�3�˹���");
			p.sendMessage(" ");
			p.sendMessage("��3�����: ��c"+name);
			p.sendMessage("��3�������뾶: ��c"+bj);
			p.sendMessage("��3�������ٷֱ�: ��c"+bl);
			p.sendMessage(" ");
			p.sendMessage("��7��o˵��:");
			p.sendMessage(" ��7��o�������3��oǰ��Ҫ��˫�������c��o"+bj+"��3��o��뾶�ڡ�7��o�Ż��7��o���С�a��o�������");
			p.sendMessage(" ��7��o����ʽ: ��c��o����%�ٷֱ�=������Է�����");
			p.sendMessage(" ��7��oҲ���Ƿ���ٷ�֮��c��o"+bl+"��7��o������Է�,ʣ��ٷ�֮��c��o"+sy+"��3��o������Լ�");
			
        }else if (args.length == 1 && args[0].equals("jiesan")) {
        	if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
				p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
				return false;
			}
        	p.sendMessage("��6Yoiyi - ����С��   ��6��o��lBy.��ɵ");
			p.sendMessage("��7��o����汾: ��c��o1.0");
			p.sendMessage(" ");
        	p.sendMessage("��7��o��ɢС��˵��:");
        	p.sendMessage("  ��c��o1.��7��oС����ɢ���������ݶ���ȫ��c��o���7��o(����ֵ/�ֿ�/��)");
        	p.sendMessage("  ��c��o2.��7��oС����ɢ���ѹ���ġ�3��o��Ҽ����ƽ��");
        	p.sendMessage("  ��c��o3.��7��o����ֵ��������ȡ���ġ�c��o�޷����´��ؽ�С�����ٴ���ȡ");
        	p.sendMessage("  ��c��o4.��7��o�����ɢ����ȴ���һ��ͬ���ɢ�Ż��ɢ");
        	p.sendMessage("  ��7��o(�����������ϵ����Ա����ǿ�ƽ�ɢ)");
        	p.sendMessage(" ");
        	p.sendMessage("��3���� ��e/yyi jiesan 1 ��3���ͽ�ɢ����");
        	p.sendMessage("��3���� ��e/yyi jiesan 2 ��3ͬ���ɢ");
        }else if (args.length == 2 && args[0].equals("jiesan")) {
        	if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
				p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
				return false;
			}
        	String name = PLAYERDATA.getString(p.getName().toLowerCase());
        	if (args[1].equalsIgnoreCase("1")) {
        		
        		if (!Bukkit.getOfflinePlayer(name).isOnline()){
        			p.sendMessage("��f���� ��b> ��7�Է�������");
        			p.sendMessage("��7��o(�����������ϵ����Ա����ǿ�ƽ�ɢ)");
        			return false;
        		}
        		
        		
        		p.sendMessage("��f���� ��b> ��c�ѷ��ͽ�ɢС������� "+name);
        		p.sendMessage("��7��o(�����������ϵ����Ա����ǿ�ƽ�ɢ)");
        		
        		Bukkit.getPlayer(name).sendMessage("��f���� ��b> ��c��� "+p.getName()+" ���ͽ�ɢС���������");
        		Bukkit.getPlayer(name).sendMessage("��f���� ��b> ��c���� ��e/yyi jiesan ��c�鿴��ɢ˵��");
        		
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
        			p.sendMessage("��f���� ��b> ��7�Է�������");
        			p.sendMessage("��7��o(�����������ϵ����Ա����ǿ�ƽ�ɢ)");
        			return false;
        		}
        		
        		if (jiesan.get(p.getName().toLowerCase()) == null){
        			p.sendMessage("��f���� ��b> ��a����  ��c"+name+" ��a����ܺ�,��δ�ӵ� ��c"+name+" ��a�Ľ�ɢ����Ŷ~");
        			return false;
        		}
        		
        		p.sendMessage("��f���� ��b> ��c����С���ɹ���ɢ,�����С��˵���ͷ�");
        		if (jbsq.getBoolean("�Ƿ���ͬ������")){
        		p.sendMessage("��f���� ��b> ��6ƽ�ֹ���Ʋ���ý��: ��c"+vault);
        		Economys.paymoney(p, PLAYERDATA_CC.getDouble("Vault"));
        		Economys.econ.depositPlayer(p, vault);
        		}
        		if (djsq.getBoolean("�Ƿ���ͬ������")){
        		p.sendMessage("��f���� ��b> ��6ƽ�ֹ���Ʋ���õ��: ��c"+points);
        		Points.points.getAPI().set(p.getUniqueId(), points);
        		}
        		
        		
        		Bukkit.getPlayer(name).sendMessage("��f���� ��b> ��c�Է�ͬ������Ľ�ɢ����,�����С��˵���ͷ�");
        		if (jbsq.getBoolean("�Ƿ���ͬ������")){
        		Bukkit.getPlayer(name).sendMessage("��f���� ��b> ��6ƽ�ֹ���Ʋ���ý��: ��c"+vault);
        		Economys.paymoney(Bukkit.getPlayer(name), PLAYERDATA_CC.getDouble("Vault"));
        		Economys.econ.depositPlayer(Bukkit.getPlayer(name), vault);
        		}
        		if (djsq.getBoolean("�Ƿ���ͬ������")){
        		Bukkit.getPlayer(name).sendMessage("��f���� ��b> ��6ƽ�ֹ���Ʋ���õ��: ��c"+points);
        		 Points.points.getAPI().set(Bukkit.getPlayer(name).getUniqueId(), points);
        		}
        		
        		
        		Bukkit.broadcastMessage("��f���� ��b> ��a��� "+p.getName()+" �� "+ Bukkit.getPlayer(name).getName() +" ��a������С��˵���ͷ� ��c(��ɢ)");
        		
        		
        		Main.DelYml("/data/"+sj.getString(p.getName().toLowerCase())+"/", "С������");
        		Main.DelYml("/data/"+sj.getString(p.getName().toLowerCase())+"/", "����Ʋ�����");
        		Main.DelYml("/data/"+sj.getString(p.getName().toLowerCase())+"/", "��ɢ");
        		Main.DelYml("/data/"+sj.getString(p.getName().toLowerCase())+"/", "��ҹ�������");
        		Main.DelYml("/data/"+sj.getString(p.getName().toLowerCase())+"/", "���������");
        		
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
				p.sendMessage("��f���� ��b> ��c��Ȩ��");
				return false;
			}
			p.sendMessage("��f���� ��b> ��6Config.yml ��a���������سɹ�");
			p.sendMessage("��f���� ��b> ��7�����������û��Զ�����");
			Main.getInstance().reloadConfig();
		}
		return false;
	}
}
