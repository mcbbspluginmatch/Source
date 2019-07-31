package cn.cs.our.player;


import cn.cs.our.main;

public class AdditionOrDecrease {

	int i = 0;
	public void jia() {
		if (main.INSTANCE.getConfig().getInt("servernumber") == 0) {
			main.INSTANCE.getConfig().set("servernumber", 1);
			main.INSTANCE.saveConfig();
		}else {
			main.INSTANCE.getConfig().set("servernumber", main.INSTANCE.getConfig().getInt("servernumber") + 1);
			main.INSTANCE.saveConfig();
		}
		

	}

	public void jian() {
		if (main.INSTANCE.getConfig().getInt("servernumber") == 1) {
			main.INSTANCE.getConfig().set("servernumber", 0);
			main.INSTANCE.saveConfig();
		} else {
			main.INSTANCE.getConfig().set("servernumber", main.INSTANCE.getConfig().getInt("servernumber") - 1);
			main.INSTANCE.saveConfig();
		}
	}


}
