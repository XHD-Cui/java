package cn.lxxxjs.chat;

/**
 * ��װʹ�ö��̣߳����ܶ�
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{
	private DataInputStream dis;
	private Socket client; //�ͻ���
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
	
	//�õ�����
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
	
	
	//�ͷ���Դ
	private void release() {
		this.isRunning = false;
		Closeend.close(dis,client);
	}
	
	@Override
	public void run() {
		while(isRunning) {
			//�����
			String requ = receive();
			System.out.println(requ);
		}
		
	}

}
