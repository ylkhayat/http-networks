package networks.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class MainServerView extends JFrame {

	public JFrame frame;
	public JTextField servInp1;
	private JButton createServerBtn;
	private JLabel servPortVLbl1;
	private JLabel statLbl;
	private JButton addClientBtn;
	private JTextPane servCons;
	private JLabel queueSize;
	private JLabel queueSizeV;
	private JButton serveRequests;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainServerView window = new MainServerView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainServerView() {
		super();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("Courier New", Font.PLAIN, 12));
		frame.setBounds(100, 100, 450, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPnl = new JPanel();
		frame.getContentPane().add(mainPnl, BorderLayout.CENTER);
		mainPnl.setLayout(null);

		JPanel toolPnl = new JPanel();
		toolPnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		toolPnl.setBounds(0, 0, 434, 65);
		mainPnl.add(toolPnl);
		toolPnl.setLayout(null);

		createServerBtn = new JButton("Create Server");
		createServerBtn.setFont(new Font("Tahoma", Font.PLAIN, 9));
		createServerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		createServerBtn.setBounds(243, 31, 105, 23);
		toolPnl.add(createServerBtn);

		JLabel lblServer1 = new JLabel("Server");
		lblServer1.setBounds(86, 6, 79, 14);
		toolPnl.add(lblServer1);

		servInp1 = new JTextField();
		servInp1.setEnabled(false);
		servInp1.setText("6789");
		servInp1.setBounds(86, 32, 76, 20);
		toolPnl.add(servInp1);
		servInp1.setColumns(10);

		statLbl = new JLabel("Offline");
		statLbl.setBounds(243, 6, 89, 14);
		toolPnl.add(statLbl);

		JPanel servPnl1 = new JPanel();
		servPnl1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		servPnl1.setBounds(0, 65, 434, 196);
		mainPnl.add(servPnl1);
		servPnl1.setLayout(null);

		JLabel servPortLbl1 = new JLabel("Server Port:");
		servPortLbl1.setBounds(10, 11, 118, 14);
		servPnl1.add(servPortLbl1);

		JLabel lblNewLabel_3 = new JLabel("Console:");
		lblNewLabel_3.setBounds(10, 36, 197, 14);
		servPnl1.add(lblNewLabel_3);

		servPortVLbl1 = new JLabel("");
		servPortVLbl1.setBounds(138, 11, 69, 14);
		servPnl1.add(servPortVLbl1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 414, 124);
		servPnl1.add(scrollPane);
		
		servCons = new JTextPane();
		scrollPane.setViewportView(servCons);
		
		queueSize = new JLabel("Queue Size:");
		queueSize.setBounds(217, 11, 118, 14);
		servPnl1.add(queueSize);
		
		queueSizeV = new JLabel("");
		queueSizeV.setBounds(355, 11, 69, 14);
		servPnl1.add(queueSizeV);
		
		serveRequests = new JButton("Server Requests");
		serveRequests.setFont(new Font("Tahoma", Font.PLAIN, 9));
		serveRequests.setBounds(242, 32, 112, 23);
		servPnl1.add(serveRequests);

		addClientBtn = new JButton("Add Client");
		addClientBtn.setBounds(147, 272, 142, 23);
		addClientBtn.setEnabled(false);
		mainPnl.add(addClientBtn);

		frame.setVisible(true);
	}

	public JButton getServeRequests() {
		return serveRequests;
	}

	public void setServeRequests(JButton serveRequests) {
		this.serveRequests = serveRequests;
	}

	public JLabel getQueueSizeV() {
		return queueSizeV;
	}

	public void setQueueSizeV(JLabel queueSizeV) {
		this.queueSizeV = queueSizeV;
	}

	public JTextPane getServCons() {
		return servCons;
	}

	public void setServCons(JTextPane servCons) {
		this.servCons = servCons;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getServInp1() {
		return servInp1;
	}

	public void setServInp1(JTextField servInp1) {
		this.servInp1 = servInp1;
	}

	public JButton getCreateServerBtn() {
		return createServerBtn;
	}

	public void setCreateServerBtn(JButton createServerBtn) {
		this.createServerBtn = createServerBtn;
	}

	public JLabel getServPortVLbl1() {
		return servPortVLbl1;
	}

	public void setServPortVLbl1(JLabel servPortVLbl1) {
		this.servPortVLbl1 = servPortVLbl1;
	}

	public JLabel getStatLbl1() {
		return statLbl;
	}

	public void setStatLbl1(JLabel statLbl1) {
		this.statLbl = statLbl1;
	}

	public JButton getAddClientBtn() {
		return addClientBtn;
	}

	public void setAddClientBtn(JButton addClientBtn) {
		this.addClientBtn = addClientBtn;
	}
}
