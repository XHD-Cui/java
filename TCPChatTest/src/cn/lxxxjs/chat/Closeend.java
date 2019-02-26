package cn.lxxxjs.chat;

/**
 * ������
 * �ͷ���Դ
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
