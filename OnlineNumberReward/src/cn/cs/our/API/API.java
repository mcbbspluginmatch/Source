package cn.cs.our.API;

import java.util.HashMap;
import java.util.List;

import cn.cs.our.main;

public class API {
	public HashMap<String, Integer> numberHashMap = new HashMap<String, Integer>();
	public HashMap<String, String> msgHashMap = new HashMap<String, String>();
	public HashMap<String, Integer> isRunHashMap = new HashMap<String, Integer>();
	public HashMap<String, List<String>> consoleHashMap = new HashMap<String, List<String>>();
	public HashMap<String, List<String>> opCmdHashMap = new HashMap<String, List<String>>();
	// 共有多少个a1 a2 a..x
	public int confignumber = 0;

	public void getConfig() {
		int i = 1;
		while (main.INSTANCE.getConfig().get("a" + i) != null) {
			// 获取number 所有的
			int number = main.INSTANCE.getConfig().getInt("a" + i + ".number");
			numberHashMap.put("a" + i + ".number", Integer.valueOf(number));
			// 获取所有的 msg
			String msg = main.INSTANCE.getConfig().getString("a" + i + ".msg");
			if (msg.contains("&")) {

				msgHashMap.put("a" + i + ".msg", msg.replaceAll("&", "§"));
			} else {
				msgHashMap.put("a" + i + ".msg", msg);
			}
			// 获取所以的isrun
			int isrun = main.INSTANCE.getConfig().getInt("a" + i + ".isrun");
			isRunHashMap.put("a" + i + ".isrun", Integer.valueOf(isrun));

			// 获取所有的console
			List<String> console = main.INSTANCE.getConfig().getStringList("a" + i + ".console");
			consoleHashMap.put("a" + i + ".console", console);

			// 获取所有的opcmd
			List<String> opcmd = main.INSTANCE.getConfig().getStringList("a" + i + ".opcmd");
			opCmdHashMap.put("a" + i + ".opcmd", opcmd);
			confignumber = i++;
		}

	}

	public HashMap<String, Integer> getNumberHashMap() {
		return this.numberHashMap;
	}

	public HashMap<String, String> getMsgHashMap() {
		return this.msgHashMap;
	}

	public HashMap<String, Integer> getIsRunHashMap() {
		return this.isRunHashMap;
	}

	public HashMap<String, List<String>> getConsoleHashMap() {
		return this.consoleHashMap;
	}

	public HashMap<String, List<String>> getOpCmdHashMap() {
		return this.opCmdHashMap;
	}
	
	public int getConfignumber() {
		return this.confignumber;
	}
}
