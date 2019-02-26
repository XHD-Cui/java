package cn.lxxxjs.qschat;

/**
 * 封装使用多线程：接受端
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{
	private DataInputStream dis;
	private Socket client; //客户端
	private boolean isRunning;
	
	public Receive(Socket client) {
		this.client = client;
		this.isRunning = true;
		try {
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			System.out.println("===r===");
			release();
		}
	}
	
	//得到数据
	private String receive() {
		String meg = "";
		try {
			meg = dis.readUTF();
		} catch (IOException e) {
			System.out.println("---rr---");
			release();
		}
		return meg;
	}
	
	
	//释放资源
	private void release() {
		this.isRunning = false;
		Closeend.close(dis,client);
	}
	
	@Override
	public void run() {
		while(isRunning) {
			//输出流
			String requ = receive();
			System.out.println(requ);
		}
		
	}

}