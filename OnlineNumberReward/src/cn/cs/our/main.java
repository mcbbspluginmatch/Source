package cn.cs.our;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import cn.cs.our.API.API;
import cn.cs.our.listener.playerListener;
import cn.cs.our.player.AdditionOrDecrease;

public class main extends JavaPlugin {
	public static main INSTANCE;

	public main() {
		main.INSTANCE = this;
	}



	@Override
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(new playerListener(), this);
		new timeUtil().DeleteFiles(getConfig().getInt("shi"), getConfig().getInt("fen"), getConfig().getInt("miao"), "");
		new API().getConfig();
		new timeUtil().DeleteFiles(23, 59, 59, "isget");
		Bukkit.getConsoleSender().sendMessage("§6[" + "OnlineNumberReward" + "] §a插件加载成功");
	}

}
