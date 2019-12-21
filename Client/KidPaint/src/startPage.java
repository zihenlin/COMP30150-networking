import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.swing.*;

public class startPage extends JFrame {
	boolean end;
	String server_ip;
	int server_port;

	public startPage() {
		end = false;
		this.setTitle("Start Page");
		this.setSize(200, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JButton btn = new JButton("Start");
		Label lb = new Label("Username");
		JTextField textField = new JTextField(10);
		p1.add(btn);
		p2.add(lb);
		p2.add(textField);
		container.add(p2);
		container.add(p1, BorderLayout.SOUTH);
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(textField.getText());
				btn.setEnabled(false);
				try {
					req(textField.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	void req(String username) throws IOException {
		DatagramSocket socket = new DatagramSocket(1111);
		DatagramPacket packet = new DatagramPacket(username.getBytes(), username.length(),
				InetAddress.getByName("255.255.255.255"), 5555);
		socket.send(packet);
		DatagramPacket receivedPacket = new DatagramPacket(new byte[1024], 1024);
		while (!end) {
			System.out.println("Listening...");

			socket.receive(receivedPacket);
			String content = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
			if (content.equals("roger that")) {
				server_ip = receivedPacket.getAddress().toString();
				server_port = receivedPacket.getPort();
				System.out.println(server_ip);
				System.out.println(server_port);
				this.setVisible(false);
				UI ui = UI.getInstance();
				ui.setData(new int[50][50], 20);
				ui.setVisible(true);
				end = true;
			}

		}
	}

}
