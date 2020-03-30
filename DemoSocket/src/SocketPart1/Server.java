package SocketPart1;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author admin
 *
 * 
 */

public class Server {
    public static void main(String[] args) throws IOException {
	ServerSocket listener = null;
	int clientNumber = 0;
	// Mở một ServerSocket tại cổng 7777.
	// Chú ý bạn không thể chọn cổng nhỏ hơn 1023 nếu không là người dùng đặc quyền
	// (privileged users (root)).
	try {
	    listener = new ServerSocket(7777);
	    System.out.println("Server is waiting to accept user...");
	} catch (IOException e) {
	    System.out.println(e);
	    System.exit(1);
	}
	try {
	    while (true) {
		// Chấp nhận một yêu cầu kết nối từ phía Client.
		// Đồng thời nhận được một đối tượng Socket tại server.
		ServiceThread st = new ServiceThread(listener.accept(), clientNumber++);
		st.start();
	    }
	} finally {
	    listener.close();
	}
    }
}
