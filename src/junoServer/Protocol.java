package junoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import org.json.JSONObject;

public class Protocol {

	public Protocol() {
		// TODO Auto-generated constructor stub
	}

	private class Reader implements Runnable {
		private Socket socket;
		private BufferedReader input;
		boolean running;

		private Reader(Socket s, BufferedReader in){
			socket = s;
			input = in;
			running = true;
		}

		@Override
		public void run() {
			String reply;
			JSONObject message;
			try {
				while(running){
					message = new JSONObject(input.readLine());
					if (message.getString("type").equals("acknowledge")){
						System.out.println("ack recv'd");
					}
					else{
						System.out.println("deny recv'd");
						running = false;
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
