package com.FBinggun.QH;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Lang {
	public	static String reload;
	public static String offplayre;
	public 	static String giveqhs;
	public static String set;
	public static String szwkqhwp;
	public static String wfqh;
	public static String max;
	public static String qhcg ;
	public static String qhsb;
	public static String ps;
			public FileConfiguration load(String path) {
		// 配置文件加载
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return YamlConfiguration.loadConfiguration(new File(path));
	}
	public static FileConfiguration load(File file) {
		// 配置文件加载
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return YamlConfiguration.loadConfiguration(file);
	}
	
	public static FileConfiguration cs() {
		String path = "Lang.yml";
		File file = new File("plugins/FBQianghua", path);
		FileConfiguration config = load(file);
		return config;
	}
	
	public static void Lnegreload() {
		Lang.reload=	cs().getString("reload");
		Lang.offplayre=	cs().getString("offplayre");
		Lang.giveqhs=	cs().getString("giveqhs");
		Lang.set=	cs().getString("set");
		Lang.szwkqhwp=	cs().getString("szwkqhwp");
		Lang.wfqh=	cs().getString("wfqh");
		Lang.max=	cs().getString("max");
		Lang.qhcg=	cs().getString("qhcg");
		Lang.qhsb=	cs().getString("qhsb");
		Lang.ps=	cs().getString("ps");
	}
	
}
