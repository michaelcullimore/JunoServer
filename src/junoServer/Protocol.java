package junoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Protocol {

	public Protocol() {
		Socket socket = new Socket();
		String address = "ec2-52-41-213-54.us-west-2.compute.amazonaws.com";
		int port = 8989;
		try {
			socket.connect(new InetSocketAddress(address, port),3000);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Socket Connection Failed");
		}
		
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter output = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("connected");
	}
	public static void main(String[] args) {
		Protocol p = new Protocol();
		
	}

}
