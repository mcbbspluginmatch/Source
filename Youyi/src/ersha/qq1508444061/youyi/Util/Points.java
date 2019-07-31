package ersha.qq1508444061.youyi.Util;

import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.plugin.Plugin;

import ersha.qq1508444061.youyi.Main;

public class Points {
	
	public static PlayerPoints points;
	
	public static boolean hookPlayerPoints() {
	    Plugin plugin = Main.getInstance().getServer().getPluginManager().getPlugin("PlayerPoints");
	    points = ((PlayerPoints)PlayerPoints.class.cast(plugin));
	    return (points != null);
	  }
}
