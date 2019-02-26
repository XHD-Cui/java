package cn.lxxxjs.chat;

/**
 * 工具类
 * 释放资源
 */

import java.io.Closeable;


public class Closeend {

		static public void close(Closeable... targets) {
			for(Closeable target:targets) {
				try {
					if(null != target) {
						target.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
}
