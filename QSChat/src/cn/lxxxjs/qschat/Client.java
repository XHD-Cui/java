package cn.lxxxjs.qschat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//创建客户端
		System.out.println("-----Client-----");
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入姓名：");
		String name = console.readLine();
		//1、建立链接：指定端口Socket创建客户端+服务的地址和端口
		Socket client = new Socket("localhost",8888);
		//2、操作：输入输出流操作
		new Thread(new Send(client,name)).start();
		new Thread(new Receive(client)).start();
		
	}
}
