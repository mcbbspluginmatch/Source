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
		
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
	    
	    File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/С������.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FS);
	    
		File F = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/��ҹ�������.yml");
	    YamlConfiguration jbsq = YamlConfiguration.loadConfiguration(F);
	    
	    File Fsa = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(p.getName().toLowerCase())+"/���������.yml");
	    YamlConfiguration djsq = YamlConfiguration.loadConfiguration(Fsa);
	    
	    File Fss = new File(Main.getInstance().getDataFolder() + "/�ѽ���С�������.yml");
	    YamlConfiguration yilist = YamlConfiguration.loadConfiguration(Fss);
		
	    double MoneyCost = Economys.econ.getBalance(p);
	    
	    if(args.length == 0) {
	    	
	    	if (!Main.getInstance().getConfig().getBoolean("Shared.Money") && !Main.getInstance().getConfig().getBoolean("Shared.Points")){
				p.sendMessage("��f���� ��b> ��7�˷������޷�ʹ���������");
				return false;
			}
	    	
			p.sendMessage("��6Yoiyi - ����С��   ��6��o��lBy.��ɵ");
			p.sendMessage("��7��o����汾: ��c��o1.0");
			p.sendMessage("��b/yygx ��7- ��3����Ʋ���ϸ");
			p.sendMessage("��b/yygx vault ��7- ��3����һ�����͹���������");
			p.sendMessage("��b/yygx points ��7- ��3����һ�����͹���������");
			p.sendMessage("��b/yygx <vault/points> accept ��7- ��3ͬ�⹲��");
			p.sendMessage(" ");
			p.sendMessage("��7��oע������:");
			p.sendMessage(" ��c��o1.��7��o�������뷽��3��o��Ҫ�ȴ���һ��ͬ���7��o�ŻṲ��");
			p.sendMessage(" ��c��o2.��7��oһ��������Ϻ�,���c��o����С���������7��o�����");
			p.sendMessage(" ��7��o������c��oƽ���ָ�˫�� ��f��o(100/50)");
			p.sendMessage(" ��c��o3.��7��o�Ʋ�����������־��������н��");
			return false;
		}else if (args.length == 1) {
			String name = (String) PLAYERDATA.get(p.getName().toLowerCase());
			Player ps = Bukkit.getPlayer(name);
			
			if (args[0].equalsIgnoreCase("vault")) {
				
				if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
					p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
					p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
					return false;
				}
				
				if (!Main.getInstance().getConfig().getBoolean("Shared.Money")){
					p.sendMessage("��f���� ��b> ��7�޷�ʹ�ô˹���");
					return false;
				}
				
				if (!Bukkit.getOfflinePlayer(name).isOnline()){
					p.sendMessage("��f���� ��b> ��7�Է�Ŀǰ������");
					return false;
				}
				
				if (jbsq.getBoolean("�Ƿ���ͬ������")){
					p.sendMessage("��f���� ��b> ��c�����ĺ��������ǹ�����״̬,�������¹���");
					return false;
				}
				
				if (jbsq.get(p.getName().toLowerCase()) != null){
					p.sendMessage("��f���� ��b> ��c�Է���������㹲����");
					p.sendMessage("��f���� ��b> ��a������ ��e/yygx vault accept ��aͬ�⹲��");
					return false;
				}
				
				if (jbsq.get(p.getName().toLowerCase()) != null){
					p.sendMessage("��f���� ��b> ��c���ѷ��͹�����������,�Ͽ�ȥ������ͬ�������");
					return false;
				}
				
				jbsq.set(ps.getName().toLowerCase(), p.getName().toLowerCase());
				jbsq.set("�Ƿ���ͬ������", Boolean.valueOf(false));
				
				p.sendMessage("��f���� ��b> ��a�ѷ�������,��ȴ��Է�ͬ��");
				
				ps.sendMessage("��f���� ��b> ��a��� ��c"+p.getName()+" ��a���㷢����e��l�����ҲƲ���a����");
				ps.sendMessage("��f���� ��b> ��a������ ��e/yygx vault accept ��aͬ�⹲��");
				ps.sendMessage("��f���� ��b> ��c�ܾ����� ��a������");
				
				try {
					jbsq.save(F);
				} catch (IOException es) {
				  es.printStackTrace();
				}
				
				return false;
			    }
			
			if (args[0].equalsIgnoreCase("points")) {
				
				if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
					p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
					p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
					return false;
				}
				
				if (!Main.getInstance().getConfig().getBoolean("Shared.Points")){
					p.sendMessage("��f���� ��b> ��7�޷�ʹ�ô˹���");
					return false;
				}
				
				if (!Bukkit.getOfflinePlayer(name).isOnline()){
					p.sendMessage("��f���� ��b> ��7�Է�Ŀǰ������");
					return false;
				}
				
				if (djsq.getBoolean("�Ƿ���ͬ������")){
					p.sendMessage("��f���� ��b> ��c�����ĺ��������ǹ�����״̬,�������¹���");
					return false;
				}
				
				if (djsq.get(ps.getName().toLowerCase()) != null){
					p.sendMessage("��f���� ��b> ��c�Է���������㹲����");
					p.sendMessage("��f���� ��b> ��a������ ��e/yygx points accept ��aͬ�⹲��");
					return false;
				}
				
				if (djsq.get(p.getName().toLowerCase()) != null){
					p.sendMessage("��f���� ��b> ��c���ѷ��͹�����������,�Ͽ�ȥ������ͬ�������");
					return false;
				}
				
				djsq.set(ps.getName().toLowerCase(), p.getName().toLowerCase());
				djsq.set("�Ƿ���ͬ������", Boolean.valueOf(false));
				
				p.sendMessage("��f���� ��b> ��a�ѷ�������,��ȴ��Է�ͬ��");
				
				ps.sendMessage("��f���� ��b> ��a��� ��c"+p.getName()+" ��a���㷢����b��l������Ʋ���a����");
				ps.sendMessage("��f���� ��b> ��a������ ��e/yygx points accept ��aͬ�⹲��");
				ps.sendMessage("��f���� ��b> ��c�ܾ����� ��a������");
				
				try {
					djsq.save(Fsa);
				} catch (IOException es) {
				  es.printStackTrace();
				}
				
				return false;
		     }
		}else if (args.length == 2) {
			
			if (!yilist.getStringList("List").contains(p.getName().toLowerCase())){
				p.sendMessage("��f���� ��b> ��c�㻹δ���κ�һλ��ҽ�������С����");
				p.sendMessage("��f���� ��b> ��a������ ��e/yyi yq <���> ��a�����뽨������С��");
				return false;
			}
			
			if (args[0].equalsIgnoreCase("vault")) {
				if (args[1].equalsIgnoreCase("accept")) {
					String name = (String) jbsq.get(p.getName().toLowerCase());
					
					if (!Main.getInstance().getConfig().getBoolean("Shared.Money")){
						p.sendMessage("��f���� ��b> ��7�޷�ʹ�ô˹���");
						return false;
					}
					
					if (jbsq.getBoolean("�Ƿ���ͬ������")){
						p.sendMessage("��f���� ��b> ��c�����ĺ��������ǹ�����״̬,�������¹���");
						return false;
					}
					
					if (jbsq.get(p.getName().toLowerCase()) == null){
		    	    	p.sendMessage("��f���� ��b> ��7δ�ӵ��Է��Ĺ���������");
		    	    	return false;
		    	    }
					
					if (!Bukkit.getOfflinePlayer(name).isOnline()){
						p.sendMessage("��f���� ��b> ��7�Է�Ŀǰ������");
						return false;
					}
				
					
					if (jbsq.get(p.getName().toLowerCase()) == null){
		    	    	p.sendMessage("��f���� ��b> ��7δ�ӵ��Է��Ľ�ҹ�������");
		    	    	p.sendMessage("��f���� ��b> ��a���� ��e/yygx vault ��a���͹�����������Է���");
		    	    	return false;
		    	    }
					
					Youyi_gxVault.tobu(p.getName().toLowerCase(), name.toLowerCase());
					
					jbsq.set("�Ƿ���ͬ������", Boolean.valueOf(true));
					
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
						p.sendMessage("��f���� ��b> ��7�޷�ʹ�ô˹���");
						return false;
					}
					
					if (djsq.getBoolean("�Ƿ���ͬ������")){
						p.sendMessage("��f���� ��b> ��c�����ĺ��������ǹ�����״̬,�������¹���");
						return false;
					}
					
					if (djsq.get(p.getName().toLowerCase()) == null){
						p.sendMessage("��f���� ��b> ��7δ�ӵ��Է��Ĺ���������");
		    	    	return false;
		    	    }
					
					if (!Bukkit.getOfflinePlayer(name).isOnline()){
						p.sendMessage("��f���� ��b> ��7�Է�Ŀǰ������");
						return false;
					}
					
					
					if (djsq.get(p.getName().toLowerCase()) == null){
		    	    	p.sendMessage("��f���� ��b> ��7δ�ӵ��Է��ĵ��������");
		    	    	p.sendMessage("��f���� ��b> ��a���� ��e/yygx points ��a���͹�����������Է���");
		    	    	return false;
		    	    }
					
					
					Youyi_gxPoints.tobu(p, Bukkit.getPlayer(name));

					djsq.set("�Ƿ���ͬ������", Boolean.valueOf(true));
					
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
