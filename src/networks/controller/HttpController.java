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

import networks.model.ConnectionType;
import networks.model.Formats;
import networks.model.TCPClient;
import networks.model.TCPServer;
import networks.view.MainServerView;
import networks.view.TCPClientView;

public class HttpController implements ActionListener, KeyListener, MouseListener {
	TCPServer server;
	LinkedList<TCPClient> clients;
	MainServerView serverView;
	LinkedList<TCPClientView> clientView;
	HttpController inst;

	public HttpController() {
		serverView = new MainServerView();
		serverView.getCreateServerBtn().addActionListener(this);
		serverView.getAddClientBtn().addActionListener(this);
		serverView.getServeRequests().addActionListener(this);
		clients = new LinkedList<TCPClient>();
		clientView = new LinkedList<TCPClientView>();
		inst = this;

	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() instanceof JButton && a.getSource().equals(serverView.getCreateServerBtn())) {
			Thread serverThread = new Thread(new Runnable() {
				public void run() {
					server = new TCPServer(6789, "Youssef", 6888);
					serverView.getQueueSizeV().setText(server.getRequests().size() + "");
				}
			});
			serverThread.start();
			serverView.getStatLbl1().setText("Online");
			serverView.getStatLbl1().setForeground(Color.GREEN);
			serverView.getServPortVLbl1().setText("Port: 6789");
			serverView.getAddClientBtn().setEnabled(true);
		} else if (a.getSource() instanceof JButton && a.getSource().equals(serverView.getAddClientBtn())) {
			Thread client = new Thread(new Runnable() {
				public void run() {
					try {
						TCPClient clientTemp = new TCPClient("Youssef", 6789);
						clients.add(clientTemp);
						TCPClientView clientViewTemp = new TCPClientView();
						clientViewTemp.getBtnRequest().addActionListener(inst);
						clientViewTemp.getFrmConversationWindow().setVisible(true);
						clientViewTemp.getFrmConversationWindow().setTitle("USER N=" + clientView.size());
						clientViewTemp.setServerPortLbl("6789");
						clientView.add(clientViewTemp);
						clientViewTemp.setClient(clientTemp);
						LinkedList<JButton> listBtns = loadDocRoot(clientViewTemp);
						for (JButton jButton : listBtns) {
							jButton.setBackground(Color.WHITE);
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

			for (int i = 0; i < clientView.size(); i++) {
				TCPClientView tcpClientView = clientView.get(i);
				System.out.println(temp.getParent().equals(tcpClientView.getChatPnl()));
				if (temp.getParent().equals(tcpClientView.getDocRootPanel())) {
					TCPClient current = clients.get(clientView.indexOf(tcpClientView));
					String tempStrReq = "--------REQUEST---------" + '\n'
							+ current.request(temp.getText(), tcpClientView.getComboBox().getSelectedItem()) + '\n';

					tcpClientView.getTextPane().setText(tcpClientView.getTextPane().getText() + tempStrReq);
					serverView.getServCons().setText(
							serverView.getServCons().getText() + "--------USER N=" + i + "-------" + '\n' + tempStrReq);
					serverView.getQueueSizeV().setText(server.getRequests().size() + "");
					Thread responseThread = new Thread(new Runnable() {
						public void run() {
							while (current.getCurrent() == null)
								;
							String tempStrRes = "--------RESPONSE-------" + '\n' + current.getCurrent().toStringCustom()
									+ '\n';
							tcpClientView.getTextPane().setText(tcpClientView.getTextPane().getText() + tempStrRes);
							serverView.getServCons().setText(serverView.getServCons().getText() + tempStrRes);
							if (current.getCurrent().getConnection().equals(ConnectionType.CLOSE)) {
								tcpClientView.getFrmConversationWindow().setVisible(false);
								tcpClientView.getFrmConversationWindow().revalidate();
								tcpClientView.revalidate();
								tcpClientView.getFrmConversationWindow().dispose();
								tcpClientView.dispose();
								clients.remove(current);
								clientView.remove(tcpClientView);

							}

						}
					});
					responseThread.start();
				} else if (temp.getParent().equals(tcpClientView.getChatPnl())) {
					TCPClient current = clients.get(clientView.indexOf(tcpClientView));
					String tempStrReq = "--------REQUEST---------" + '\n'
							+ current.request(tcpClientView.getInputField().getText(),
									tcpClientView.getComboBox().getSelectedItem())
							+ '\n';
					tcpClientView.getTextPane().setText(tcpClientView.getTextPane().getText() + tempStrReq);
					serverView.getServCons().setText(
							serverView.getServCons().getText() + "--------USER N=" + i + "-------" + '\n' + tempStrReq);
					serverView.getQueueSizeV().setText(server.getRequests().size() + "");
					Thread responseThread = new Thread(new Runnable() {
						public void run() {
							while (current.getCurrent() == null)
								;
							String tempStrRes = "--------RESPONSE-------" + '\n' + current.getCurrent().toStringCustom()
									+ '\n';
							tcpClientView.getTextPane().setText(tcpClientView.getTextPane().getText() + tempStrRes);
							if (current.getCurrent().getConnection().equals(ConnectionType.CLOSE)) {
								tcpClientView.getFrmConversationWindow().setVisible(false);
								tcpClientView.getFrmConversationWindow().dispose();
								clients.remove(current);
								clientView.remove(tcpClientView);
								tcpClientView.dispose();
							}

						}
					});
					responseThread.start();
				} else if (a.getSource() instanceof JButton && a.getSource().equals(serverView.getServeRequests())) {
					System.out.println("Yalahwiiii");
					try {
						server.serve();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					serverView.getQueueSizeV().setText(server.getRequests().size() + "");
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
