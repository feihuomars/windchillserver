package com.bizvpm.windchill.server;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.ws.Endpoint;

public class Starter {
	private static String httproot;
	
	public static void main(String[] args) {
		System.out.println("开始启动服务----->>>>>");
		loadProperties();
		Endpoint endpoint = Endpoint.create(new TegWCService());
		endpoint.publish(httproot);
		System.out.println("服务启动完成----->>>>>");
	}

	private static void loadProperties() {
		InputStream is = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir") //$NON-NLS-1$
					+ "/configuration/windchill.properties"); //$NON-NLS-1$
			is = new BufferedInputStream(fis);
			Properties props = new Properties();
			props.load(is);
			httproot = props.getProperty("server.httproot");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
				}
		}
	}
}
