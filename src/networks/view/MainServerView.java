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

public class MainServerView extends JFrame {

	public JFrame frame;
	public JTextField servInp1;
	private JButton addBtn1;
	private JLabel servPortVLbl1;
	private JLabel servSocketVLbl1;
	private JLabel servCapVLbl1;
	private JLabel statLbl1;
	private JButton addClient;
	private JTextArea servCons1;

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

		addBtn1 = new JButton("Create Server");
		addBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		addBtn1.setBounds(243, 31, 105, 23);
		toolPnl.add(addBtn1);

		JLabel lblServer1 = new JLabel("Server");
		lblServer1.setBounds(86, 6, 79, 14);
		toolPnl.add(lblServer1);

		servInp1 = new JTextField();
		servInp1.setEnabled(false);
		servInp1.setText("6789");
		servInp1.setBounds(86, 32, 76, 20);
		toolPnl.add(servInp1);
		servInp1.setColumns(10);

		statLbl1 = new JLabel("Offline");
		statLbl1.setBounds(243, 6, 89, 14);
		toolPnl.add(statLbl1);

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

		servSocketVLbl1 = new JLabel("");
		servSocketVLbl1.setBounds(138, 36, 69, 14);
		servPnl1.add(servSocketVLbl1);

		servCapVLbl1 = new JLabel("");
		servCapVLbl1.setBounds(138, 61, 69, 14);
		servPnl1.add(servCapVLbl1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 414, 124);
		servPnl1.add(scrollPane);
		
				servCons1 = new JTextArea();
				scrollPane.setViewportView(servCons1);
				servCons1.setEditable(false);

		addClient = new JButton("Add Client");
		addClient.setBounds(147, 272, 142, 23);
		addClient.setEnabled(false);
		mainPnl.add(addClient);

		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getAddClient() {
		return addClient;
	}

	public void setAddClient(JButton addClient) {
		this.addClient = addClient;
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

	public JButton getAddBtn1() {
		return addBtn1;
	}

	public void setAddBtn1(JButton addBtn1) {
		this.addBtn1 = addBtn1;
	}

	public JLabel getServPortVLbl1() {
		return servPortVLbl1;
	}

	public void setServPortVLbl1(JLabel servPortVLbl1) {
		this.servPortVLbl1 = servPortVLbl1;
	}

	public JLabel getServSocketVLbl1() {
		return servSocketVLbl1;
	}

	public void setServSocketVLbl1(JLabel servSocketVLbl1) {
		this.servSocketVLbl1 = servSocketVLbl1;
	}

	public JLabel getServCapVLbl1() {
		return servCapVLbl1;
	}

	public void setServCapVLbl1(JLabel servCapVLbl1) {
		this.servCapVLbl1 = servCapVLbl1;
	}

	public JTextArea getServCons1() {
		return servCons1;
	}

	public void setServCons1(JTextArea servCons1) {
		this.servCons1 = servCons1;
	}

	public JLabel getStatLbl1() {
		return statLbl1;
	}

	public void setStatLbl1(JLabel statLbl1) {
		this.statLbl1 = statLbl1;
	}
}
