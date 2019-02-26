package cn.lxxxjs.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TMultiClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//�����ͻ���
		System.out.println("-----Client-----");
		//1���������ӣ�ָ���˿�Socket�����ͻ���+����ĵ�ַ�Ͷ˿�
		Socket client = new Socket("localhost",8888);
		//2���������������������
		new Thread(new Send(client)).start();
		new Thread(new Receive(client)).start();
		
	}
}
