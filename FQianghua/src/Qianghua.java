package com.FBinggun.QH;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Qianghua extends JavaPlugin{
	public void onEnable() {
		getServer().getPluginManager().registerEvents( new ls(), this);
	    if (!getDataFolder().exists()){
	        getDataFolder().mkdir();}
        File file=new File(getDataFolder(),"config.yml");
        File file1=new File(getDataFolder(),"Lang.yml");
        if (!(file.exists())) {saveDefaultConfig();}
        if (!(file1.exists())) {saveResource("Lang.yml",true);}
      cg cg =new cg();
      cg.getlevel();
      cg.gettype();
       cg.chance();
       cg.fail();
       cg.stone();
       cg.broken();
       Lang.Lnegreload();
		getLogger().info("§a（疯子强化）插件启动成功感谢使用此插件 版本：1.0");
		getLogger().info("§a作者:疯子冰棍(F_Binggun)");
		getLogger().info("§a作者QQ:1728482805");
		getLogger().info("§c接定制插件 技术接单");
		getLogger().info("§c如有对此插件任何疑问或建议 请联系作者");	
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
	if(label.equalsIgnoreCase("qh")){
		Player p =	(Player) sender;
		if(p.hasPermission("op")){
		FZQianghuaAPI api =new FZQianghuaAPI();
		if(args.length==1){
		if(args[0].equalsIgnoreCase("reload")){
		      cg cg =new cg();
		      cg.getlevel();
		      cg.gettype();
		       cg.chance();
		       cg.fail();
		       cg.stone();
		       cg.broken();
		       Lang.Lnegreload();
				sender.sendMessage(Lang.reload);
				return false;
		}}
		//qh give ID xxx x
		if(args.length==4){
		if(args[0].equalsIgnoreCase("give")){
			Player p1 =getServer().getPlayer(args[1]);
			if(p1==null){
				sender.sendMessage(Lang.offplayre);
				return false;
			}
			sender.sendMessage(Lang.giveqhs);
			api.give(args[2], p1, Integer.valueOf(args[3]));
			return false;
		}}
		//qh set x
		if(args.length==2){
		if(args[0].equalsIgnoreCase("set")){
			if(sender instanceof Player){
				ItemStack i=	p.getInventory().getItemInMainHand();
				qhs qh =new qhs();
				qh.cleanlore(i);
				qh.setlevel(i, Integer.valueOf(args[1]));
				sender.sendMessage(Lang.set);
				return false;
			}
		}}
		
		
		
		}
	}
	
	
	
	
	
		return false;
		
	}
}
