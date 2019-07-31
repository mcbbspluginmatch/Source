package cn.cs.our;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import cn.cs.our.API.API;

//时间工具类
public class timeUtil {

	public static String getNetworkTime(String webUrl) {
		try {
			URL url = new URL(webUrl);
			URLConnection conn = url.openConnection();
			conn.connect();
			long dateL = conn.getDate();
			Date date = new Date(dateL);
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd-HH:mm");
			return dateFormat.format(date);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}

	//到某个时间段跑run方法
	public void DeleteFiles(int shi, int fen, int miao, String str) {
		API config = new API();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, shi);
		calendar.set(Calendar.MINUTE, fen);
		calendar.set(Calendar.SECOND, miao);
		HashMap<String, Integer> isRunHashMap = config.getIsRunHashMap();
		Date time = calendar.getTime();

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				//遍历isRunHashMap
				for (String key1 : isRunHashMap.keySet()) {
					Integer integer = isRunHashMap.get(key1);
					//找到isRun不等于0的给他设置成1
					if (integer != null && integer != 0) {
						main.INSTANCE.getConfig().set(key1, 1);
						main.INSTANCE.saveConfig();
					}
				}
			}
		}, time, 1000 * 60 * 60 * 24);
	}
}
