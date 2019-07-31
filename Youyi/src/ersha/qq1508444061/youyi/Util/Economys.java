package ersha.qq1508444061.youyi.Util;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import ersha.qq1508444061.youyi.Main;
import net.milkbowl.vault.economy.Economy;

public class Economys {

	public static Economy econ = null;
	
	@SuppressWarnings("rawtypes")
	public static boolean setupEconomy() {
	    RegisteredServiceProvider economyProvider = Main.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
	    econ = (Economy) economyProvider.getProvider();
	    return (econ != null);}
    
	  @SuppressWarnings("deprecation")
	  public static boolean paymoney(Player player, double money) {
	    if (!(econ.hasAccount(player.getName())))
	      return false;

	    if (econ.getBalance(player.getName()) < money)
	      return false;

	    econ.withdrawPlayer(player.getName(), money);
	    return true;
	}
}
