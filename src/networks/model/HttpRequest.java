package networks.model;

import java.io.Serializable;

public class HttpRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	String method;
	String url;
	String version;
	String host;
	Formats format;
	ConnectionType connection;

	public HttpRequest() {
		method = "GET";
		version = "HTTP/1.1";
		host = "Youssef";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

}
