package cn.lxxxjs.chat;

/**
 * 封装使用多线程：chat
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
			System.out.println("一个客户端建立了链接");
			
			new Thread(new Channel(client)).start();
		}
		
		
	}
	
	//一个客户代表一个channel
	static class Channel implements Runnable
	{
		private DataInputStream dis; //输入流
		private DataOutputStream dos; //输出流
		private Socket client; //客户端
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
		
		//得到数据
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
		//发送数据
		private void send(String meg) {
			try {
				dos.writeUTF(meg);
				dos.flush();
			} catch (IOException e) {
				System.out.println("---send---");
				release();
			}
			
		}
		
		//释放资源
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
