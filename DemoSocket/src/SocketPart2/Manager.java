package SocketPart2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/**
 * @author admin
 *
 * 
 */

public class Manager extends JFrame implements Runnable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel;
    private JLabel lblManagerPort;
    private JTextField txtSrvPort;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    Manager frame = new Manager();
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

    ServerSocket srvSocket = null;
    BufferedReader br = null;
    Thread t;
    private JTabbedPane tabbedPane;
    private JButton btnReset;
    private JButton btnCreate;

    public Manager() {
	setTitle("Manager");
	initComponents();
    }

    private void initComponents() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 737, 536);
	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(this.contentPane);
	this.panel = new JPanel();
	this.panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
	gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
	                .createSequentialGroup().addContainerGap()
	                .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
	                                .addComponent(this.tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 685,
	                                                Short.MAX_VALUE)
	                                .addComponent(this.panel, GroupLayout.PREFERRED_SIZE, 687, GroupLayout.PREFERRED_SIZE))
	                .addContainerGap()));
	gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
	                                .addComponent(this.panel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(ComponentPlacement.RELATED)
	                                .addComponent(this.tabbedPane, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
	                                .addContainerGap()));
	{
	    this.lblManagerPort = new JLabel("Manager Port:");
	    this.lblManagerPort.setHorizontalAlignment(SwingConstants.RIGHT);
	    this.lblManagerPort.setFont(new Font("Tahoma", Font.BOLD, 20));
	}
	this.txtSrvPort = new JTextField();
	this.txtSrvPort.setHorizontalAlignment(SwingConstants.CENTER);
	this.txtSrvPort.setColumns(10);

	this.btnReset = new JButton("Reset");
	this.btnReset.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnResetActionPerformed(e);
	    }
	});

	this.btnCreate = new JButton("Create");
	this.btnCreate.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnCreateActionPerformed(e);
	    }
	});

	GroupLayout gl_panel = new GroupLayout(this.panel);
	gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(this.lblManagerPort, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addComponent(this.txtSrvPort, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addComponent(this.btnReset, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
	                .addPreferredGap(ComponentPlacement.UNRELATED)
	                .addComponent(this.btnCreate, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE).addContainerGap()));
	gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_panel
	                .createSequentialGroup().addContainerGap()
	                .addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
	                                .addComponent(this.btnReset, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 35,
	                                                Short.MAX_VALUE)
	                                .addComponent(this.btnCreate, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 35,
	                                                GroupLayout.PREFERRED_SIZE)
	                                .addGroup(Alignment.LEADING, gl_panel.createParallelGroup(Alignment.BASELINE)
	                                                .addComponent(this.lblManagerPort, GroupLayout.PREFERRED_SIZE, 31,
	                                                                GroupLayout.PREFERRED_SIZE)
	                                                .addComponent(this.txtSrvPort, GroupLayout.DEFAULT_SIZE, 33,
	                                                                Short.MAX_VALUE)))
	                .addContainerGap()));
	this.panel.setLayout(gl_panel);
	this.contentPane.setLayout(gl_contentPane);
    }

    @Override
    public void run() {
	// TODO Auto-generated method stub
	while (true) {
	    try {
		Socket aStaffSocket = srvSocket.accept();
		if (aStaffSocket != null) {
		    br = new BufferedReader(new InputStreamReader(aStaffSocket.getInputStream()));
		    String s = br.readLine();
		    int pos = s.indexOf(":");
		    String staffName = s.substring(pos + 2);
		    ChatPanel p = new ChatPanel(aStaffSocket, "Manager", staffName);
		    tabbedPane.add(staffName, p);
		    p.updateUI();
		}
		Thread.sleep(1000);
	    } catch (Exception e) {
		// TODO: handle exception

	    }
	}
    }

    private void btnResetActionPerformed(ActionEvent e) {
	this.lblManagerPort.setText("Manager Port:");
	this.txtSrvPort.setText("");
    }

    private void btnCreateActionPerformed(ActionEvent e) {
	int serverPort = Integer.parseInt(this.txtSrvPort.getText());
	try {
	    srvSocket = new ServerSocket(serverPort);
	    this.lblManagerPort.setText("Mng. Server is running at the Port: ");

	} catch (Exception e2) {
	    // TODO: handle exception
	}
	t = new Thread(this);
	t.start();
    }

}
