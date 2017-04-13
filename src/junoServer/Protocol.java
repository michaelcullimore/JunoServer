package junoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.json.JSONObject;

public class Protocol {
	private Receivable client;
	private PrintWriter output;

	public Protocol(String username, Receivable client) throws IOException {
		this.client = client;
		Socket socket = new Socket();
		String address = "ec2-52-41-213-54.us-west-2.compute.amazonaws.com";
		BufferedReader input;

		int port = 8989;
		try {
			socket.connect(new InetSocketAddress(address, port), 3000);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Socket Connection Failed");
		}

		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(socket.getOutputStream());

		System.out.println("connected");
		output.println("{\"type\":\"login\",\"message\":{\"username\":\"" + username + "\"}}");
		output.flush();
		System.out.println(input.readLine());

	}

	private void sendMessage(JSONObject message) {
		output.println(message);
		output.flush();
	}

	private class Reader implements Runnable {
		private Socket socket;
		private BufferedReader input;
		private Receivable client;
		boolean running;

		private Reader(Socket s, BufferedReader in, Receivable cli) {
			socket = s;
			input = in;
			running = true;
			client = cli;
		}

		@Override
		public void run() {
			String reply;
			JSONObject message;
			try {
				while (running) {
					message = new JSONObject(input.readLine());
					if (message.getString("type").equals("acknowledge")) {
						client.giveMessage(message);
					} else {
						System.out.println("deny recv'd");
						running = false;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// Protocol protocol = new Protocol("Ethan", ));
	}

}
