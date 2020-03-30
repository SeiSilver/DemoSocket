package SocketPart2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author admin
 *
 * 
 */

public class Client extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel;
    private JLabel lblStaff;
    private JLabel lblManager_1;
    private JLabel lblPort;
    private JTextField txtStaff;
    private JTextField txtServerIP;
    private JTextField txtPort;
    private JButton btnConnect;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    Client frame = new Client();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */

    public Client() {
	initComponents();
    }

    private void initComponents() {
	setTitle("Client");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 748, 548);
	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(this.contentPane);
	this.panel = new JPanel();
	this.panel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));

	this.panel_1 = new JPanel();
	this.panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
	gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	                .addGroup(gl_contentPane.createSequentialGroup().addGap(5)
	                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	                                                .addGroup(gl_contentPane.createSequentialGroup()
	                                                                .addComponent(this.panel_1, GroupLayout.DEFAULT_SIZE, 692,
	                                                                                Short.MAX_VALUE)
	                                                                .addGap(5))
	                                                .addComponent(this.panel, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
	                                .addContainerGap()));
	gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
	                                .addComponent(this.panel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
	                                .addGap(5).addComponent(this.panel_1, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
	                                .addGap(5)));
	this.panel_1.setLayout(new BorderLayout(0, 0));
	this.lblStaff = new JLabel("Staff:");
	this.lblStaff.setFont(new Font("Arial", Font.BOLD, 18));
	this.lblManager_1 = new JLabel("Manager IP:");
	this.lblManager_1.setFont(new Font("Arial", Font.BOLD, 18));
	this.lblPort = new JLabel("Port:");
	this.lblPort.setFont(new Font("Arial", Font.BOLD, 18));
	this.txtStaff = new JTextField();
	this.txtStaff.setColumns(10);
	this.txtServerIP = new JTextField();
	this.txtServerIP.setColumns(10);
	this.txtPort = new JTextField();
	this.txtPort.setColumns(10);
	this.btnConnect = new JButton("Connect");
	this.btnConnect.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnConnectActionPerformed(e);
	    }
	});
	GroupLayout gl_panel = new GroupLayout(this.panel);
	gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
	                .addGap(49).addComponent(this.lblStaff, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addComponent(this.txtStaff, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE).addGap(43)
	                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	                                .addComponent(this.btnConnect, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 241,
	                                                Short.MAX_VALUE)
	                                .addGroup(gl_panel.createSequentialGroup()
	                                                .addComponent(this.lblManager_1, GroupLayout.DEFAULT_SIZE,
	                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                .addPreferredGap(ComponentPlacement.RELATED)
	                                                .addComponent(this.txtServerIP, GroupLayout.PREFERRED_SIZE, 132,
	                                                                GroupLayout.PREFERRED_SIZE)))
	                .addGap(33).addComponent(this.lblPort, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addComponent(this.txtPort, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE).addGap(51)));
	gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	                                                .addComponent(this.txtStaff, GroupLayout.PREFERRED_SIZE,
	                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                                                .addComponent(this.txtServerIP, GroupLayout.PREFERRED_SIZE,
	                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                                                .addComponent(this.lblManager_1, GroupLayout.PREFERRED_SIZE, 22,
	                                                                GroupLayout.PREFERRED_SIZE))
	                                .addComponent(this.lblStaff)
	                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
	                                                .addComponent(this.txtPort, GroupLayout.PREFERRED_SIZE,
	                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                                                .addComponent(this.lblPort, GroupLayout.PREFERRED_SIZE, 22,
	                                                                GroupLayout.PREFERRED_SIZE)))
	                .addGap(18).addComponent(this.btnConnect, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	this.panel.setLayout(gl_panel);
	this.contentPane.setLayout(gl_contentPane);
    }

    Socket mngsocket = null;
    String mngIP = "localhost";
    int mngPort = 7777;
    String staffName = "";
    BufferedReader bf = null;
    BufferedWriter os = null;
    OutputThread t = null;
    private JPanel panel_1;

    private void btnConnectActionPerformed(ActionEvent e) {
	mngIP = this.txtServerIP.getText();
	mngPort = Integer.parseInt(this.txtPort.getText());
	staffName = this.txtStaff.getText();
	try {
	    mngsocket = new Socket(mngIP, mngPort);
	    if (mngsocket != null) {
		ChatPanel p = new ChatPanel(mngsocket, staffName, "Manager");
		panel_1.add(p);
		p.getTxtMessages().append("Manager is running\n");
		p.updateUI();
		bf = new BufferedReader(new InputStreamReader(mngsocket.getInputStream()));
		os = new BufferedWriter(new OutputStreamWriter(mngsocket.getOutputStream()));
		os.write("Staff: " + staffName);
		os.newLine();
		os.flush();
	    }
	} catch (Exception e2) {
	    // TODO: handle exception
	    JOptionPane.showMessageDialog(this, "Manager is not running.");
	    System.exit(0);
	}
    }
}
