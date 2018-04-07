package networks.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
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
						TCPClient clientTemp = new TCPClient("Youssef", 6789);
						clients.add(clientTemp);
						TCPClientView clientViewTemp = new TCPClientView();
						clientViewTemp.getFrmConversationWindow().setVisible(true);
						clientViewTemp.setServerPortLbl("6789");
						clientView.add(clientViewTemp);
						clientViewTemp.setClient(clientTemp);
						LinkedList<JButton> listBtns = loadDocRoot(clientViewTemp);
						for (JButton jButton : listBtns) {
							clientViewTemp.getDocRootPanel().add(jButton);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});
			client.start();
		} else if (a.getSource() instanceof JButton) {
			JButton temp = (JButton) a.getSource();
			for (TCPClientView tcpClientView : clientView) {
				if (temp.getParent().equals(tcpClientView.getDocRootPanel())) {
					TCPClient current = clients.get(clientView.indexOf(tcpClientView));
					current.request(temp.getText());
					System.out.println(temp.getText());
				}
			}
		}

	}

	public void listFilesForFolder(File folder, LinkedList<JButton> s) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, s);
			} else {
				JButton tempBtn = new JButton(fileEntry.getName());
				tempBtn.addActionListener(this);
				s.add(tempBtn);
			}
		}
	}

	public LinkedList<JButton> loadDocRoot(TCPClientView view) {
		LinkedList<JButton> s = new LinkedList<JButton>();
		listFilesForFolder(new File("docroot"), s);
		System.out.println(s.toString());
		return s;
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

	public static void main(String[] args) {
		HttpController controller = new HttpController();
	}

}
