package networks.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import networks.model.TCPClient;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.Font;

public class TCPClientView extends JFrame {

	private JFrame frmConversationWindow;
	private TCPClient client;
	private JLabel clientName;
	private JScrollPane scrollPane;
	private JScrollPane msgPnl;
	private JButton btnJoinBtn;
	private JLabel lblNewLabel_1;
	private JLabel serverPortLbl;
	private JScrollPane scrollPane_1;
	private LinkedList<String> usernames = new LinkedList<String>();
	private LinkedList<JTextArea> areas = new LinkedList<JTextArea>();
public LinkedList<JTextArea> getAreas() {
		return areas;
	}

	public void setAreas(LinkedList<JTextArea> areas) {
		this.areas = areas;
	}

	//	private Hashtable<String, JTextArea> areas = new Hashtable<String, JTextArea>();
	private JPanel panel;
	public JScrollPane getScrollPane_1() {
		return scrollPane_1;
	}

	public void setScrollPane_1(JScrollPane scrollPane_1) {
		this.scrollPane_1 = scrollPane_1;
	}

	public JFrame getFrmConversationWindow() {
		return frmConversationWindow;
	}

	public void setFrmConversationWindow(JFrame frmConversationWindow) {
		this.frmConversationWindow = frmConversationWindow;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// EventQueue.invokeLater(new Runnable() {
		// public void run() {
		// try {
		TCPClientView window = new TCPClientView();
		window.frmConversationWindow.setVisible(true);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	// });}

	/**
	 * Create the application.
	 */
	public TCPClientView() {
		super();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConversationWindow = new JFrame();
		frmConversationWindow.setTitle("Conversation Window");
		frmConversationWindow.setBounds(100, 100, 450, 300);
		frmConversationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPnl = new JPanel();
		mainPnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmConversationWindow.getContentPane().add(mainPnl, BorderLayout.CENTER);
		mainPnl.setLayout(null);

		JPanel usersPnl = new JPanel();
		usersPnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		usersPnl.setBounds(323, 0, 111, 261);
		mainPnl.add(usersPnl);
		usersPnl.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(0, 24, 111, 213);
		usersPnl.add(scrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		btnJoinBtn = new JButton("Request");
		btnJoinBtn.setToolTipText("Type the name you'd like in the console then click this.");
		btnJoinBtn.setBounds(0, 236, 112, 25);
		usersPnl.add(btnJoinBtn);

		JLabel lblNewLabel = new JLabel("Doc-Root");
		lblNewLabel.setBounds(2, 0, 109, 25);
		usersPnl.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel chatPnl = new JPanel();
		chatPnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		chatPnl.setBounds(0, 0, 323, 261);
		mainPnl.add(chatPnl);
		chatPnl.setLayout(null);

		msgPnl = new JScrollPane();
		msgPnl.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		msgPnl.setBounds(0, 30, 323, 171);
		chatPnl.add(msgPnl);
		
		scrollPane_1 = new JScrollPane();
		msgPnl.setViewportView(scrollPane_1);

		clientName = new JLabel("Hamada");
		clientName.setBounds(10, 5, 125, 21);
		chatPnl.add(clientName);

		serverPortLbl = new JLabel("");
		serverPortLbl.setBounds(224, 8, 89, 14);
		chatPnl.add(serverPortLbl);

		lblNewLabel_1 = new JLabel("Server:");
		lblNewLabel_1.setBounds(145, 8, 71, 14);
		chatPnl.add(lblNewLabel_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 212, 303, 38);
		chatPnl.add(scrollPane_2);
	}

	public JButton getBtnJoinBtn() {
		return btnJoinBtn;
	}

	public void setBtnJoinBtn(JButton btnJoinBtn) {
		this.btnJoinBtn = btnJoinBtn;
	}

//	public Hashtable<String, JTextArea> getAreas() {
//		return areas;
//	}
//
//	public void setAreas(Hashtable<String, JTextArea> areas) {
//		this.areas = areas;
//	}

	public JPanel getPanel() {
		return panel;
	}

	public LinkedList<String> getUsernames() {
		return usernames;
	}

	public void setUsernames(LinkedList<String> usernames) {
		this.usernames = usernames;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getServerPortLbl() {
		return serverPortLbl;
	}

	public void setServerPortLbl(String serverPortLbl) {
		this.serverPortLbl.setText(serverPortLbl);
	}

	public JLabel getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName.setText(clientName);
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JScrollPane getMsgPnl() {
		return msgPnl;
	}

	public void setMsgPnl(JScrollPane msgPnl) {
		this.msgPnl = msgPnl;
	}

	public JButton getJoinBtn() {
		return btnJoinBtn;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnJoinBtn = btnNewButton;
	}

	public TCPClient getClient() {
		return client;
	}

	public void setClient(TCPClient client) {
		this.client = client;
	}

	public void setTCPClient(TCPClient tcpClient) {
		this.client = tcpClient;

	}
}
