package cn.cs.our.listener;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cn.cs.our.main;
import cn.cs.our.API.API;
import cn.cs.our.player.AdditionOrDecrease;

public class playerListener implements Listener {
	
	//����뿪������
	@EventHandler
	public void playerQuit(PlayerQuitEvent p) {
		//����+1 servernumber
		new AdditionOrDecrease().jian();

	}
	//��ҽ��������
	@EventHandler
	public void playerJoin(PlayerJoinEvent p) {
		API config = new API();
		config.getConfig();
		//����+1 servernumber
		new AdditionOrDecrease().jia();

		HashMap<String, Integer> numberHashMap = config.getNumberHashMap();
		HashMap<String, Integer> isRunHashMap = config.getIsRunHashMap();
		HashMap<String, List<String>> consoleHashMap = config.getConsoleHashMap();
		HashMap<String, List<String>> opCmdHashMap = config.getOpCmdHashMap();
		HashMap<String, String> msgHashMap = config.getMsgHashMap();

		// ��ȡ ÿ��a��isrun
		for (String key : isRunHashMap.keySet()) {
			// �ж�is run ��٣�Ϊ�����ȡnumber
			if (isRunHashMap.get(key) == 1) {
				// ��ȡ ÿ��a��number
				for (String key1 : numberHashMap.keySet()) {
					// �ж�ûִ�й���a1��number�Ȳ����� �������� ����ִ�н���ָ��
					if (numberHashMap.get(key1) == main.INSTANCE.getConfig().get("servernumber")) {
						// ��ȡ����ָ̨��
						List<String> list = consoleHashMap.get(key1.replaceAll(".number", "") + ".console");
						// ���ϵ�OPָ�
						List<String> list2 = opCmdHashMap.get(key1.replaceAll(".number", "") + ".opcmd");
						//�ж�a1 �� ����number��ֵ�������������
						if (msgHashMap.get(key1.replaceAll(".number", "") + ".msg") != null) {
							//�����������
							for (Player playersss : Bukkit.getOnlinePlayers()) {
								//��ʾ��Ϣ
								playersss.sendMessage(msgHashMap.get(key1.replaceAll(".number", "") + ".msg"));
							}
						}
						boolean conRun = conRun(list);
						boolean opRun = opRun(list2);
						if (conRun || opRun) {
							//��isrun���Ƿ�ִ�й�a1�� ����Ϊ0  ����Ϊ0��ʾִ�й���
							main.INSTANCE.getConfig().set(key1.replaceAll(".number", "") + ".isrun", 0);
							main.INSTANCE.saveConfig();
						}
					}
				}
			}
		}
	}

	
	
	//ִ�п���ָ̨��
	private boolean conRun(List<String> list) {
		if (list != null) {
			for (String string : list) {
				if (string.contains("%player%")) {
					for (Player players : Bukkit.getOnlinePlayers()) {
						String strplayer = string.replaceAll("%player%", players.getName());
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), strplayer);
					}
				} else {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), string);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	//ִ��OPָ��
	private boolean opRun(List<String> list) {
		if (list != null) {
			for (String str1 : list) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.isOp()) {
						if (str1.contains("%player%")) {
							Bukkit.dispatchCommand(player, str1.replaceAll("%player%", player.getName()));
						} else {
							Bukkit.dispatchCommand(player, str1);
						}

					} else {
						if (str1.contains("%player%")) {
							player.setOp(true);
							Bukkit.dispatchCommand(player, str1.replaceAll("%player%", player.getName()));
							player.setOp(false);
						} else {
							player.setOp(true);
							Bukkit.dispatchCommand(player, str1);
							player.setOp(false);
						}

					}

				}
			}
			return true;

		} else {
			return false;
		}
	}

}
