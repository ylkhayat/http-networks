package networks.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;

import networks.model.TCPClient;
import networks.model.TCPServer;
import networks.view.MainServerView;
import networks.view.TCPClientView;

public class HttpController implements ActionListener, KeyListener, MouseListener {
	TCPServer server;
	LinkedList<TCPClient> clients;
	MainServerView serverView;
	LinkedList<TCPClientView> clientView;

	public HttpController() {
		serverView = new MainServerView();
		serverView.getCreateServerBtn().addActionListener(this);
		serverView.getAddClientBtn().addActionListener(this);
		clients = new LinkedList<TCPClient>();
		clientView = new LinkedList<TCPClientView>();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JButton && a.getSource().equals(serverView.getCreateServerBtn())) {
			Thread serverThread = new Thread(new Runnable() {
				public void run() {
					server = new TCPServer(6789, "Youssef", 6888);
				}
			});
			serverThread.start();
			serverView.getStatLbl1().setText("Online");
			serverView.getStatLbl1().setForeground(Color.GREEN);
			serverView.getServPortVLbl1().setText("Port: 6789");
			serverView.getAddClientBtn().setEnabled(true);
		} else if (a.getSource() instanceof JButton && a.getSource().equals(serverView.getAddClientBtn())) {
			System.out.println("hena");
			Thread client = new Thread(new Runnable() {
				public void run() {
					try {
						clients.add(new TCPClient("Youssef", 6789));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			client.start();
			clientView.add(new TCPClientView());
		}

	}

	public static void main(String[] args) {
		HttpController controller = new HttpController();
	}

}
