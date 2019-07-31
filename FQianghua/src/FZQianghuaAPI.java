package com.FBinggun.QH;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FZQianghuaAPI {

	public int getlevel(ItemStack item) {
		Set<Integer> a = cg.level.keySet();
		List<String> lore = item.getItemMeta().getLore();
		if (lore == null) {
			return 0;
		}
		for (int x : a) {
			if (lore.contains(cg.level.get(x))) {
				return x;
			}
		}
		return 0;
	}

	@SuppressWarnings("deprecation")
	public boolean istype(ItemStack item) {
		Set<String> a = cg.Type.keySet();
		int Type = item.getTypeId();
		for (String x : a) {
			if (cg.Type.get(x).contains(Type)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public String gettype(ItemStack item) {
		Set<String> a = cg.Type.keySet();
		int Type = item.getTypeId();
		for (String x : a) {
			if (cg.Type.get(x).contains(Type)) {
				return x;
			}
		}
		return "";
	}

	public double getchance(ItemStack item, ItemStack qhs) {
		int level = getlevel(item);
		Set<String> key = cg.stone.keySet();
		for (String a : key) {
			if (qhs.isSimilar(cg.stone.get(a))) {
				return cg.chance.get(a).get(level);

			}
		}
		return 0;
	}

	public boolean isstone(ItemStack qhs) {
		Set<String> key = cg.stone.keySet();
		for (String a : key) {
			if (qhs.isSimilar(cg.stone.get(a))) {
				return true;

			}
		}
		return false;
	}

	public boolean ismax(ItemStack item, ItemStack qhs) {
		int level = getlevel(item);
		Set<String> key = cg.stone.keySet();
		for (String a : key) {
			if (qhs.isSimilar(cg.stone.get(a))) {
				if (cg.chance.get(a).get(level) != null) {
					return true;
				}

			}
		}
		return false;
	}

	public int getfail(ItemStack qhs) {
		Set<String> key = cg.stone.keySet();
		for (String a : key) {
			if (qhs.isSimilar(cg.stone.get(a))) {
				String f = cg.fail.get(a);
				String[] cc = f.split("-");
				int max = Integer.valueOf(cc[1]).intValue();
				int min = Integer.valueOf(cc[0]).intValue();
				Random random = new Random();
				return random.nextInt(max) % (max - min + 1) + min;
			}
		}
		return 0;
	}

	public void give(String name, Player p, int v) {
		if (cg.stone.get(name) != null) {
			ItemStack i = cg.stone.get(name);
			i.setAmount(v);
			p.getInventory().addItem(i);
		}
	}

	public Double getbroken(ItemStack qhs) {
		Set<String> key = cg.stone.keySet();
		for (String a : key) {
			if (qhs.isSimilar(cg.stone.get(a))) {
				return cg.broken.get(a);
			}
		}
		return (double) 0;
	}
}
