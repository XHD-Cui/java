package cn.lxxxjs.chat;

/**
 * ��װʹ�ö��̣߳�chat
 * 
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TMultiChat {
	public static void main(String[] args) throws IOException {
		System.out.println("-----server----");
		ServerSocket server = new ServerSocket(8888);
		
		while(true) {
			Socket client = server.accept();
			System.out.println("һ���ͻ��˽���������");
			
			new Thread(new Channel(client)).start();
		}
		
		
	}
	
	//һ���ͻ�����һ��channel
	static class Channel implements Runnable
	{
		private DataInputStream dis; //������
		private DataOutputStream dos; //�����
		private Socket client; //�ͻ���
		private boolean isRunning;
		public Channel(Socket client) {
			this.client = client;
			this.isRunning = true;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				System.out.println("---dis,dos---");
				release();
				//e.printStackTrace();
			}
			
		}
		
		//�õ�����
		private String receive() {
			String meg = "";
			try {
				meg = dis.readUTF();
			} catch (IOException e) {
				System.out.println("---receive---");
				release();
			}
			return meg;
		}
		//��������
		private void send(String meg) {
			try {
				dos.writeUTF(meg);
				dos.flush();
			} catch (IOException e) {
				System.out.println("---send---");
				release();
			}
			
		}
		
		//�ͷ���Դ
		private void release() {
			this.isRunning = false;
			Closeend.close(dis,dos,client);
		}

		@Override
		public void run() {
			while(isRunning) {
				String meg = receive();
				if(!meg.equals("")) {
					send(meg);
				}
			}
			
		}
		
	}

}
