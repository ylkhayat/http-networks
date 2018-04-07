package networks.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class HttpResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String status;
	String version;
	Timestamp timestamp;
	Formats format;
	ConnectionType connection;
	String Url ; 

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public HttpResponse() {
		version = "HTTP/1.1";
		timestamp = new Timestamp(0);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Formats getFormat() {
		return format;
	}

	public void setFormat(Formats format) {
		this.format = format;
	}

	public ConnectionType getConnection() {
		return connection;
	}

	public void setConnection(ConnectionType connection) {
		this.connection = connection;
	}
	
	public String toString(){
		String s = getStatus() +" "  + getVersion() + "\n" + getTimestamp() + "\n" + getFormat() + " \n" + getConnection() ;
		return s ;
	}
}
