package networks.model;

import java.io.*;
import java.net.*;
import java.util.*;

public class CustomThread extends Thread {
	Socket socket = null;
	TCPServer server = null;
	ObjectInputStream streamIn = null;
	ObjectOutputStream outToClient = null;
	Socket userS = null;
	boolean joined = false;
	boolean isHigher = false;
	Queue<HttpRequest> req = null;

	public CustomThread(Socket sock, Queue<HttpRequest> requests) {
		socket = sock;
		req = requests;
		try {
			streamIn = new ObjectInputStream(socket.getInputStream());
			outToClient = new ObjectOutputStream(socket.getOutputStream());
			//outToClient.writeBytes("Successfully connected, type 'Commands' for different features." + '\n');
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
				HttpResponse resp = respond(msg);
				outToClient.writeObject(resp);
				
			} while (true);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void listFilesForFolder(File folder, LinkedList<String> s) {
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
		File folder = new File("docroot");
		LinkedList<String> s = new LinkedList<String>();
		listFilesForFolder(folder, s);
		HttpResponse resp = new HttpResponse();
		int index = s.indexOf(h.getUrl());
		resp.setFormat(h.getFormat());
		resp.setConnection(h.getConnection());
		if (index != -1 && getExtension(s.get(index)).equals(getExtension(h.getUrl()))) {
			resp.setStatus("200 OK");
		} else {
			resp.setStatus("404 Not Found");
		}
		return resp;

	}

	public void close() throws IOException {
		if (socket != null)
			socket.close();
		if (streamIn != null)
			streamIn.close();
	}

}
