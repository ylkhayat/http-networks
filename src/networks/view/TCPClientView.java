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
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class TCPClientView extends JFrame {

	private JFrame frmConversationWindow;
	private TCPClient client;
	private JScrollPane scrollPane;
	private JScrollPane msgPnl;
	private JButton btnRequest;
	private JLabel lblNewLabel_1;
	private JLabel serverPortLbl;
	private JScrollPane scrollPnlCustom;

	private JPanel docRootPanel;
	private JTextField inputField;
	private JScrollPane containerCustom;
	private JPanel chatPnl;
	private JTextPane textPane;
	private JComboBox comboBox;

	public JScrollPane getScrollPane_1() {
		return scrollPnlCustom;
	}

	public void setScrollPane_1(JScrollPane scrollPane_1) {
		this.scrollPnlCustom = scrollPane_1;
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
		frmConversationWindow.setTitle("Http Window");
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
		scrollPane.setBounds(0, 24, 111, 178);
		usersPnl.add(scrollPane);
		
				docRootPanel = new JPanel();
				scrollPane.setViewportView(docRootPanel);
				docRootPanel.setLayout(new BoxLayout(docRootPanel, BoxLayout.Y_AXIS));

		JLabel lblNewLabel = new JLabel("Doc-Root");
		lblNewLabel.setBounds(2, 0, 109, 25);
		usersPnl.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Keep Alive", "Close"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(10, 213, 91, 20);
		usersPnl.add(comboBox);

		chatPnl = new JPanel();
		chatPnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		chatPnl.setBounds(0, 0, 323, 261);
		mainPnl.add(chatPnl);
		chatPnl.setLayout(null);

		msgPnl = new JScrollPane();
		msgPnl.setViewportBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		msgPnl.setBounds(0, 30, 323, 171);
		chatPnl.add(msgPnl);

		scrollPnlCustom = new JScrollPane();
		msgPnl.setViewportView(scrollPnlCustom);

		textPane = new JTextPane();
		textPane.setFont(new Font("MS Gothic", Font.PLAIN, 11));
		scrollPnlCustom.setViewportView(textPane);

		serverPortLbl = new JLabel("");
		serverPortLbl.setBounds(224, 8, 89, 14);
		chatPnl.add(serverPortLbl);

		lblNewLabel_1 = new JLabel("Server:");
		lblNewLabel_1.setBounds(145, 8, 71, 14);
		chatPnl.add(lblNewLabel_1);

		containerCustom = new JScrollPane();
		containerCustom.setBounds(10, 212, 181, 38);
		chatPnl.add(containerCustom);

		inputField = new JTextField();
		containerCustom.setViewportView(inputField);
		inputField.setColumns(10);

		btnRequest = new JButton("Custom Request");
		btnRequest.setBounds(201, 212, 112, 38);
		chatPnl.add(btnRequest);
		btnRequest.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRequest.setToolTipText("Type the name you'd like in the console then click this.");
	}

	// public Hashtable<String, JTextArea> getAreas() {
	// return areas;
	// }
	//
	// public void setAreas(Hashtable<String, JTextArea> areas) {
	// this.areas = areas;
	// }

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JScrollPane getScrollPnlCustom() {
		return scrollPnlCustom;
	}

	public JTextPane getTextPane() {
		return textPane;
	}

	public void setTextPane(JTextPane textPane) {
		this.textPane = textPane;
	}

	public void setScrollPnlCustom(JScrollPane scrollPnlCustom) {
		this.scrollPnlCustom = scrollPnlCustom;
	}

	public JPanel getChatPnl() {
		return chatPnl;
	}

	public void setChatPnl(JPanel chatPnl) {
		this.chatPnl = chatPnl;
	}

	public JScrollPane getContainerCustom() {
		return containerCustom;
	}

	public void setContainerCustom(JScrollPane containerCustom) {
		this.containerCustom = containerCustom;
	}

	public JPanel getDocRootPanel() {
		return docRootPanel;
	}

	public JTextField getInputField() {
		return inputField;
	}

	public void setInputField(JTextField inputField) {
		this.inputField = inputField;
	}

	public void setDocRootPanel(JPanel panel) {
		this.docRootPanel = panel;
	}

	public JLabel getServerPortLbl() {
		return serverPortLbl;
	}

	public void setServerPortLbl(String serverPortLbl) {
		this.serverPortLbl.setText(serverPortLbl);
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

	public JButton getBtnRequest() {
		return btnRequest;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnRequest = btnNewButton;
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
