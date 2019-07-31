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
	
	//玩家离开服务器
	@EventHandler
	public void playerQuit(PlayerQuitEvent p) {
		//人数+1 servernumber
		new AdditionOrDecrease().jian();

	}
	//玩家进入服务器
	@EventHandler
	public void playerJoin(PlayerJoinEvent p) {
		API config = new API();
		config.getConfig();
		//人数+1 servernumber
		new AdditionOrDecrease().jia();

		HashMap<String, Integer> numberHashMap = config.getNumberHashMap();
		HashMap<String, Integer> isRunHashMap = config.getIsRunHashMap();
		HashMap<String, List<String>> consoleHashMap = config.getConsoleHashMap();
		HashMap<String, List<String>> opCmdHashMap = config.getOpCmdHashMap();
		HashMap<String, String> msgHashMap = config.getMsgHashMap();

		// 获取 每个a的isrun
		for (String key : isRunHashMap.keySet()) {
			// 判断is run 真假，为真则获取number
			if (isRunHashMap.get(key) == 1) {
				// 获取 每个a的number
				for (String key1 : numberHashMap.keySet()) {
					// 判断没执行过的a1的number等不等于 在线人数 等于执行奖励指令
					if (numberHashMap.get(key1) == main.INSTANCE.getConfig().get("servernumber")) {
						// 获取控制台指令
						List<String> list = consoleHashMap.get(key1.replaceAll(".number", "") + ".console");
						// 符合的OP指令集
						List<String> list2 = opCmdHashMap.get(key1.replaceAll(".number", "") + ".opcmd");
						//判断a1 等 里面number的值和在线人数相等
						if (msgHashMap.get(key1.replaceAll(".number", "") + ".msg") != null) {
							//遍历所有玩家
							for (Player playersss : Bukkit.getOnlinePlayers()) {
								//提示信息
								playersss.sendMessage(msgHashMap.get(key1.replaceAll(".number", "") + ".msg"));
							}
						}
						boolean conRun = conRun(list);
						boolean opRun = opRun(list2);
						if (conRun || opRun) {
							//把isrun，是否执行过a1等 设置为0  设置为0表示执行过了
							main.INSTANCE.getConfig().set(key1.replaceAll(".number", "") + ".isrun", 0);
							main.INSTANCE.saveConfig();
						}
					}
				}
			}
		}
	}

	
	
	//执行控制台指令
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

	//执行OP指令
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
