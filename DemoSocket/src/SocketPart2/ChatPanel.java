package SocketPart2;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * @author admin
 *
 * 
 */

public class ChatPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JSplitPane splitPane;
    private JButton btnSend;
    private JScrollPane scrollPane;
    private JTextArea txtMessage;
    private JLabel lblMessage;
    private JScrollPane scrollPane_1;
    private JTextArea txtMessages;

    /**
     * Create the panel.
     */

    Socket socket = null;
    String sender = null;
    BufferedWriter os = null;
    BufferedReader bf = null;
    OutputThread t = null;
    String receiver = null;

    public ChatPanel(Socket socket, String sender, String receiver) {
	this.socket = socket;
	this.sender = sender;
	this.receiver = receiver;
	initComponents();
	try {
	    bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	    t = new OutputThread(socket, txtMessages, sender, receiver);
	    t.start();
	} catch (Exception e) {
	    // TODO: handle exception
	}
    }

    private void initComponents() {
	this.splitPane = new JSplitPane();
	this.lblMessage = new JLabel("Message");
	this.lblMessage.setFont(new Font("Tahoma", Font.BOLD, 15));
	this.scrollPane_1 = new JScrollPane();
	GroupLayout groupLayout = new GroupLayout(this);
	groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
	                .createSequentialGroup().addContainerGap()
	                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
	                                .addComponent(this.scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 494,
	                                                Short.MAX_VALUE)
	                                .addComponent(this.lblMessage, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 76,
	                                                GroupLayout.PREFERRED_SIZE)
	                                .addComponent(this.splitPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 494,
	                                                Short.MAX_VALUE))
	                .addContainerGap()));
	groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
	                .addGroup(groupLayout.createSequentialGroup().addContainerGap()
	                                .addComponent(this.scrollPane_1, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
	                                .addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblMessage)
	                                .addPreferredGap(ComponentPlacement.RELATED)
	                                .addComponent(this.splitPane, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
	                                .addContainerGap()));
	{
	    this.txtMessages = new JTextArea();
	    this.scrollPane_1.setViewportView(this.txtMessages);
	}
	{
	    this.btnSend = new JButton("Send");
	    this.btnSend.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    btnSendActionPerformed(e);
		}
	    });
	    this.splitPane.setRightComponent(this.btnSend);
	}
	{
	    this.scrollPane = new JScrollPane();
	    this.splitPane.setLeftComponent(this.scrollPane);
	    {
		this.txtMessage = new JTextArea();
		this.scrollPane.setViewportView(this.txtMessage);
	    }
	}
	this.splitPane.setDividerLocation(350);
	setLayout(groupLayout);
    }

    public JTextArea getTxtMessages() {
	return txtMessages;
    }

    private void btnSendActionPerformed(ActionEvent e) {
	if (txtMessage.getText().trim().length() == 0) return;
	try {
	    os.write(txtMessage.getText());
	    os.newLine();
	    os.flush();
	    this.txtMessages.append("\n" + sender + ": " + txtMessage.getText());
	} catch (Exception e2) {
	    // TODO: handle exception
	}
	this.txtMessage.setText("");
    }
}
