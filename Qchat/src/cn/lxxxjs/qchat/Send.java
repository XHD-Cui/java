package cn.lxxxjs.qchat;


/**
 * 封装使用多线程：发送端
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable{
	private BufferedReader console;
	private DataOutputStream dos;
	private Socket client; //客户端
	private boolean isRunning;
	private String name;
	public Send(Socket client,String name) {
		this.client = client;
		this.isRunning = true;
		console = new BufferedReader(new InputStreamReader(System.in));
		try {
			dos = new DataOutputStream(client.getOutputStream());
			this.name = name;
			send(name);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			System.out.println("===s===");
			release();
		}
	}
	
	//发送消息
	private void send(String meg) {
		try {
			dos.writeUTF(meg);
			dos.flush();
		} catch (IOException e) {
			System.out.println("====ss===");
			release();
		}
		
	}
	
	
	//释放资源
	private void release() {
		this.isRunning = false;
		Closeend.close(dos,client);
	}
	
	//从控制台获取消息
	private String getStrFormConlose() {
		try {
			return console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@Override
	public void run() {
		while(isRunning) {
			String meg = getStrFormConlose();
			if(!meg.equals("")) {
				send(meg);
			}
		}
		
	}

}
