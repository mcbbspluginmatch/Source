package com.FBinggun.QH;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class cg {
	public static Map<Integer, String>  level =new HashMap<>();
	public static Map<String, List<Integer>>  Type =new HashMap<>();
	public static Map<String,ItemStack>  stone =new HashMap<>();
	public static Map<String,Map<Integer,Double>>  chance =new HashMap<>();
	public static Map<String,String>  fail =new HashMap<>();
	public static Map<String,Double>  broken =new HashMap<>();
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
	public FileConfiguration load(File file) {
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
	public FileConfiguration cs() {
		String path = "config.yml";
		File file = new File("plugins/FBQianghua", path);
		FileConfiguration config = load(file);
		return config;
	}
	
	public void getlevel(){
		Set<String> a = cs().getConfigurationSection("level").getKeys(false);
		for(String b:a){
			int l =Integer.valueOf(b).intValue();
			String name =cs().getString("level."+b+".name");
			cg.level.put(l, name);
		}
	
	}
	
	public void gettype(){
		Set<String> a = cs().getConfigurationSection("itemType").getKeys(false);
		for(String b:a){
			cg.Type.put(b, cs().getIntegerList("itemType."+b));
		}
	}
	
	
	public void	 chance(){
		Set<String> a = cs().getConfigurationSection("stone").getKeys(false);
		for(String b:a){
			Map<Integer,Double> jl =new HashMap<>();
			Set<String> d = cs().getConfigurationSection("stone."+b+".chance").getKeys(false);
			for(String g:d){
				int gg =Integer.valueOf(g).intValue();
				jl	.put(gg,cs().getDouble("stone."+b+".chance."+g));
				}
			cg.chance.put(b, jl);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void	 stone(){
		Set<String> a = cs().getConfigurationSection("stone").getKeys(false);
		for(String b:a){
			String name = cs().getString("stone."+b+".name");
			int type = cs().getInt("stone."+b+".id");
			@SuppressWarnings("unused")
			int data = cs().getInt("stone."+b+".data");
			List<String> lore = cs().getStringList("stone."+b+".lore");
			ItemStack item = new ItemStack(Material.getMaterial(type));
			ItemMeta m =item.getItemMeta();
			m.setDisplayName(name);
			m.setLore(lore);
			m.addEnchant(Enchantment.ARROW_DAMAGE, 1,true);
			item.setItemMeta(m);
			cg.stone.put(b,item);
		}
	}
	
	public void	 fail(){
		Set<String> a = cs().getConfigurationSection("stone").getKeys(false);
		for(String b:a){
			cg.fail.put(b, cs().getString("stone."+b+".fail"));
		}
	}
	
	public void broken(){
		Set<String> a = cs().getConfigurationSection("stone").getKeys(false);
		for(String b:a){
			cg.broken.put(b, cs().getDouble("stone."+b+".broken"));
			
		}
	}
}
