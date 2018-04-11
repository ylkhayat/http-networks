package networks.model;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.*;

public class CustomThread extends Thread {
	public boolean isServe() {
		return serve;
	}

	public static void setServe(boolean serve) {
		CustomThread.serve = serve;
	}

	Socket socket = null;
	TCPServer server = null;
	ObjectInputStream streamIn = null;
	ObjectOutputStream outToClient = null;
	Socket userS = null;
	boolean joined = false;
	boolean isHigher = false;
	Queue<HttpRequest> req = null;
	static boolean serve = false;
	Queue<HttpRequest> personalQueue = null;

	public CustomThread(Socket sock, Queue<HttpRequest> requests, boolean serve) {
		socket = sock;
		req = requests;
		this.serve = serve;
		personalQueue = new LinkedList<HttpRequest>();
		try {
			streamIn = new ObjectInputStream(socket.getInputStream());
			outToClient = new ObjectOutputStream(socket.getOutputStream());
			// outToClient.writeBytes("Successfully connected, type 'Commands'
			// for different features." + '\n');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			do {

				HttpRequest msg;
				msg = (HttpRequest) streamIn.readObject();
				req.add(msg);
				personalQueue.add(msg);
				System.out.println("Abl Obaa");
			} while (true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void respondCustom() throws IOException {
		Thread response = new Thread(new Runnable() {

			@Override
			public void run() {
				HttpRequest msg = null;
				if (personalQueue.size() > 0)
					msg = personalQueue.remove();
				if (msg != null) {
					System.out.println("Obaa");
					HttpResponse resp = respond(msg);
					req.remove();
					try {
						outToClient.writeObject(resp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// if(resp.getConnection().equals(ConnectionType.CLOSE))
					// close();
					if (resp.getStatus().equals("200 OK")) {
						File file = new File("docroot/" + msg.getUrl());
						System.out.println(msg.getUrl());
						sendfile(outToClient, file);
					}
					serve = false;
				}

			}
		});
		response.start();

	}

	public void listFilesForFolder(File folder, ArrayList<String> s) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, s);
			} else {
				s.add(fileEntry.getName());
			}
		}
	}

	public static String getExtension(String fileName) {
		char ch;
		int len;
		if (fileName == null || (len = fileName.length()) == 0 || (ch = fileName.charAt(len - 1)) == '/' || ch == '\\'
				|| // in the case of a directory
				ch == '.') // in the case of . or ..
			return "";
		int dotInd = fileName.lastIndexOf('.'),
				sepInd = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
		if (dotInd <= sepInd)
			return "";
		else
			return fileName.substring(dotInd + 1).toLowerCase();
	}

	public HttpResponse respond(HttpRequest h) {
		final File folder = new File("docroot");
		// listFilesForFolder(folder);
		ArrayList<String> s = new ArrayList<String>();

		listFilesForFolder(folder, s);
		System.out.println(s.toString());
		HttpResponse resp = new HttpResponse();
		String filename = h.getUrl();
		System.out.println(filename);
		int index = s.indexOf(filename);

		resp.setFormat(h.getFormat());
		resp.setConnection(h.getConnection());
		resp.setUrl(h.getUrl());
		if (index != -1) {
			resp.setStatus("200 OK");

		} else {
			resp.setStatus("404 Not Found");
		}
		return resp;

	}

	public void sendfile(ObjectOutputStream outToclient, File file) {
		try {
			byte[] content = Files.readAllBytes(file.toPath());
			outToclient.writeObject(content);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				System.out.println(fileEntry.getName());
			}
		}
	}

	public void close() throws IOException {
		if (socket != null)
			socket.close();
		if (streamIn != null)
			streamIn.close();
	}

}
