package SocketPart1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author admin
 *
 * 
 */

public class ServiceThread extends Thread {
    private int clientNumber;
    private Socket socketOfServer;

    public ServiceThread(Socket socketOfServer, int clientNumber) {
	this.clientNumber = clientNumber;
	this.socketOfServer = socketOfServer;
	// Log
	log("New connection with client# " + this.clientNumber + " at " + socketOfServer);
    }

    private static void log(String message) {
	System.out.println(message);
    }

    @Override
    public void run() {
	try {
	    // Mở luồng vào ra trên Socket tại Server.
	    BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
	    BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));

	    while (true) {
		// Đọc dữ liệu do client gửi tới.
		String line = is.readLine();
		System.out.println("" + clientNumber + ":" + line);

		// Ghi vào luồng đầu ra của Socket tại Server.
		// (Nghĩa là gửi tới Client).
		os.write("" + clientNumber + ">> " + line);
		// Kết thúc dòng
		os.newLine();
		// Đẩy dữ liệu đi
		os.flush();

		// Nếu người dùng gửi tới QUIT (Muốn kết thúc trò chuyện).
		if (line.equals("QUIT")) {
		    os.write("" + clientNumber + ">> OK");
		    os.newLine();
		    os.flush();
		    break;
		}
	    }

	} catch (IOException e) {
	    System.out.println(e);
	    e.printStackTrace();
	}
    }

}
