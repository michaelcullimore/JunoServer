package junoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Protocol {

	public Protocol(String username) throws IOException {
		Socket socket = new Socket();
		String address = "ec2-52-41-213-54.us-west-2.compute.amazonaws.com";
		BufferedReader input;
		PrintWriter output;
		
		int port = 8989;
		try {
			socket.connect(new InetSocketAddress(address, port),3000);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Socket Connection Failed");
		}
		
	
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(socket.getOutputStream());
	
		
		System.out.println("connected");
		output.println();
		
	}
	public static void main(String[] args) throws IOException {
		Protocol p = new Protocol("Ethan");
		
	}

}
