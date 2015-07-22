package br.com.farofa.gm.test;

import java.io.IOException;
import java.net.InetAddress;

public class ServerTest {
	public static void main(String[] args) {
		try {
			testPing();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testPing() throws IOException {
		InetAddress inet = InetAddress.getByName("192.168.0.177");
		//inet = InetAddress.getByAddress(new byte[] {(byte)192, (byte)168, 0, (byte)180});
		System.out.println("Sending Ping Request to " + inet);
		System.out.println(inet.isReachable(1000) ? "Host is reachable" : "Host is NOT reachable");
		
	}
}
