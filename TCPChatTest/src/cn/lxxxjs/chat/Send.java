package cn.lxxxjs.chat;

/**
 * ��װʹ�ö��̣߳����Ͷ�
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Send implements Runnable{
	private BufferedReader console;
	private DataOutputStream dos;
	private Socket client; //�ͻ���
	private boolean isRunning;
	
	public Send(Socket client) {
		this.client = client;
		this.isRunning = true;
		console = new BufferedReader(new InputStreamReader(System.in));
		try {
			dos = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("===s===");
			release();
		}
	}
	
	//������Ϣ
	private void send(String meg) {
		try {
			dos.writeUTF(meg);
			dos.flush();
		} catch (IOException e) {
			System.out.println("====ss===");
			release();
		}
		
	}
	
	
	//�ͷ���Դ
	private void release() {
		this.isRunning = false;
		Closeend.close(dos,client);
	}
	
	//�ӿ���̨��ȡ��Ϣ
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
