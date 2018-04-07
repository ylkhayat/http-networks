package networks.model;

import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.lang.Object;
import javax.net.ssl.HttpsURLConnection;

import networks.model.TCPClient;

public class TCPClient {
	String name = "";
	Socket socket = null;
	BufferedReader console = null;
	ObjectOutputStream streamOut = null;
	ObjectInputStream inFromServer = null;
	boolean joined;

	public TCPClient(String host, int serverPort) throws UnknownHostException, IOException {
		socket = new Socket(host, serverPort);
		System.out.println("Connected: " + socket);
		console = new BufferedReader(new InputStreamReader(System.in));
		streamOut = new ObjectOutputStream(socket.getOutputStream());
		inFromServer = new ObjectInputStream(socket.getInputStream());
		Thread write = new Thread(new Runnable() {
			public void run() {
				String line = "";
				do {
					HttpRequest con = new HttpRequest();
//					con.setFormat(Formats.PNG);
//					con.setConnection(ConnectionType.KEEP_ALIVE);
//					con.setUrl("hihi.html");
					boolean passed = false;
					try {
						System.out.println("Enter file name: ");
						line = console.readLine();
						con.setUrl(line);
						System.out.println("Enter file type: ");
						do {
							line = console.readLine();
							switch (line) {
							case "png":
								con.setFormat(Formats.png);
								passed = true;
								break;
							case "jpg":
								con.setFormat(Formats.jpg);
								passed = true;
								break;
							case "mp4":
								con.setFormat(Formats.mp4);
								passed = true;
								break;
							case "txt":
								con.setFormat(Formats.txt);
								passed = true;
								break;
							default:
								System.out.println("Please enter a valid format.");
							}
						} while (!passed);
						passed = false;
						System.out.println("Enter connection type: ");
						do {
							line = console.readLine();
							switch (line) {
							case "alive":
								con.setConnection(ConnectionType.KEEP_ALIVE);
								passed = true;
								break;
							case "close":
								con.setConnection(ConnectionType.CLOSE);
								passed = true;
								break;
							default:
								System.out.println("Please enter a valid connection.");
							}
						} while (!passed);

						streamOut.writeObject(con);
						System.out.println("Request sent successfully.");
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				} while (!line.equals("Bye"));
				console = null;
				return;
			}
		});
		write.start();

		Thread read = new Thread(new Runnable() {
			public void run() {
				HttpResponse lineBack;
				while (true) {
					try {
						lineBack = (HttpResponse) inFromServer.readObject();
						System.out.println(lineBack.getStatus());
						if(lineBack.getStatus().equals("200 OK")){
							String filename = lineBack.getUrl()+"."+lineBack.getFormat();
							File fileBack = new File("C:/Users/omar elsobky/Desktop/HttpNetworks/ClientRecFiles/"+filename);
							byte[] content = (byte[]) inFromServer.readObject();
							Files.write(fileBack.toPath(), content);
							Desktop desktop = Desktop.getDesktop();
							desktop.open(fileBack);
						}
						
					}catch( OptionalDataException e){
						System.out.println(e.eof+"    "+ e.length);
						e.printStackTrace();
					}
					catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						System.out.println(e.getMessage());
					}
					
				}

			}
		});
		read.start();

	}

	public void stop() {
		try {
			if (console != null)
				console.close();
			if (streamOut != null)
				streamOut.close();
			if (socket != null)
				socket.close();
		} catch (IOException ioe) {
			System.out.println("Error closing ...");
		}
	}

	public static void main(String argv[]) throws Exception {
		TCPClient client = new TCPClient("sobky", 6789);

	}

}
