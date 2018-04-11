package networks.model;

import java.io.*;
import java.net.*;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class TCPServer extends Thread {
	ServerSocket server = null;
	Socket socket = null;
	static Queue<HttpRequest> requests = null;
	boolean serve = false;
	Hashtable<Integer, CustomThread> threads;
	int counter = 0;

	public Hashtable<Integer, CustomThread> getThreads() {
		return threads;
	}

	public void setThreads(Hashtable<Integer, CustomThread> threads) {
		this.threads = threads;
	}

	public Queue<HttpRequest> getRequests() {
		return requests;
	}

	public void setRequests(Queue<HttpRequest> requests) {
		this.requests = requests;
	}

	public boolean isServe() {
		return serve;
	}

	public void serve() throws IOException {
		for (Object thread : threads.keySet().toArray()) {
			threads.get(thread).respondCustom();
		}
	}

	public TCPServer(int port, String host, int serverPort) {
		threads = new Hashtable<Integer, CustomThread>();
		try {
			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
			requests = new LinkedList<HttpRequest>();
			// socket = new Socket(host, serverPort);
			System.out.println("Server started: " + server);
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							System.out.println("Waiting for a client ...");
							addThread(server.accept());
						} catch (IOException ie) {
							System.out.println("Acceptance Error: " + ie);
						}
					}

				}
			});
			thread.start();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	public void addThread(Socket socket) {
		System.out.println("Client accepted: " + socket);
		CustomThread client = new CustomThread(socket, requests, serve);
		threads.put(counter++, client);
		client.start();
	}

	public static void main(String argv[]) throws Exception {
		TCPServer server = new TCPServer(6789, "sobky", 6888);
	}

}
