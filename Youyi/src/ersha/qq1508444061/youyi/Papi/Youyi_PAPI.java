package ersha.qq1508444061.youyi.Papi;

import java.io.File;
import java.text.DecimalFormat;

import me.clip.placeholderapi.external.EZPlaceholderHook;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import ersha.qq1508444061.youyi.Main;

public class Youyi_PAPI extends EZPlaceholderHook {

	@SuppressWarnings("unused")
	private Main main;

	public Youyi_PAPI(Main ourPlugin) {
		super(ourPlugin, "youyi");
		this.main = ourPlugin;
	}

	@Override
	public String onPlaceholderRequest(Player player, String string) {
		DecimalFormat df = new DecimalFormat("#");
		File Fsss = new File(Main.getInstance().getDataFolder() + "/�������.yml");
	    YamlConfiguration sj = YamlConfiguration.loadConfiguration(Fsss);
		
	    File FSs= new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(player.getName().toLowerCase())+"/С������.yml");
	    YamlConfiguration PLAYERDATA = YamlConfiguration.loadConfiguration(FSs);
	    
		File FS = new File(Main.getInstance().getDataFolder() + "/data/"+sj.get(player.getName().toLowerCase())+"/����Ʋ�����.yml");
	    YamlConfiguration PLAYERDATA_CC = YamlConfiguration.loadConfiguration(FS);

		if (string.equals("ck_money")) {
			return PLAYERDATA_CC.get("�ֿ��Ž������") == null ? "0" : df.format(PLAYERDATA_CC.getInt("�ֿ��Ž������"));
		}else if (string.equals("ck_points")) {
			return PLAYERDATA_CC.get("�ֿ��ŵ������") == null ? "0" : df.format(PLAYERDATA_CC.getInt("�ֿ��ŵ������"));
		}else if (string.equals("gx_money")) {
			return PLAYERDATA_CC.get("Vault") == null ? "0" : df.format(PLAYERDATA_CC.getInt("Vault"));
		}else if (string.equals("gx_points")) {
			return PLAYERDATA_CC.get("Points") == null ? "0" : df.format(PLAYERDATA_CC.getInt("Points"));
		}else if (string.equals("ta")) {
			return PLAYERDATA.get(player.getName().toLowerCase()) == null ? "��" : PLAYERDATA.getString(player.getName().toLowerCase());
		}else if (string.equals("is")) {
			return PLAYERDATA.get(player.getName().toLowerCase()) != null ? "�ѽ���" :"δ����";
		}
		return "��";
		
	}
}