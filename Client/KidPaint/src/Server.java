import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

class Client {
	String name;
	Socket socket;
}

public class Server {
	ServerSocket srvSocket;
	ArrayList<Client> list = new ArrayList<Client>();

	public Server() throws IOException {
		String msg = "roger that";
		DatagramSocket socket = new DatagramSocket(5555);
		DatagramPacket packet;
		DatagramPacket receivedPacket = new DatagramPacket(new byte[1024], 1024);

		srvSocket = new ServerSocket(6666);

		while (true) {
			System.out.println("Listening...");

			socket.receive(receivedPacket);
			String content = new String(receivedPacket.getData(), 0, receivedPacket.getLength());

			if (content != null) {
				int port = receivedPacket.getPort();
				packet = new DatagramPacket(msg.getBytes(), msg.length(), InetAddress.getByName("255.255.255.255"),
						port);
				System.out.println(receivedPacket.getAddress());
				socket.send(packet);
				Socket cSocket = srvSocket.accept();
				Client client = new Client();
				client.name = content;
				client.socket = cSocket;

				synchronized (list) {
					list.add(client);
					System.out.printf("Total %d clients are connected.\n", list.size());
				}

				Thread t = new Thread(() -> {
					try {
						serve(client);
					} catch (IOException e) {
						System.err.println("connection dropped.");
					}
					synchronized (list) {
						list.remove(client);
					}
				});
				t.start();
			}
		}
	}

	private void serve(Client client) throws IOException {
		Socket clientSocket = client.socket;
		byte[] buffer = new byte[1024];
		int len;
		System.out.printf("Established a connection to host %s:%d\n\n", clientSocket.getInetAddress(),
				clientSocket.getPort());

		DataInputStream in = new DataInputStream(clientSocket.getInputStream());
		
		while (true) {
			len = in.readInt();
			in.read(buffer, 0, len);
			forward(buffer, len, client.name);
		}
	}

	private void forward(byte[] data, int len, String username) {
		synchronized (list) {
			for (int i = 0; i < list.size(); i++) {
				try {
					Client client = list.get(i);
					Socket socket = client.socket;
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeInt(len);
					out.write(data, 0, len);
				} catch (IOException e) {
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			System.err.println("System error: " + e.getMessage());
		}
	}

}
