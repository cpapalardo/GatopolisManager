package br.com.farofa.gm.database;

import java.io.IOException;
import java.net.InetAddress;

public class PersistenceUnitFactory {
	private static final String TEST_SERVER_ADDRESS = "192.168.0.177";
	private static final String TEST_SERVER_UNIT_NAME = "banco_teste";
	private static final String PRODUCTION_SERVER_UNIT_NAME = "gatopolis_v2_db";
	
	private static String persistenceUnit;
	
	public static String getPersisteceUnit () {
		if (persistenceUnit == null || persistenceUnit.equals("")) {
			if (isTestServeOn ())
				persistenceUnit = TEST_SERVER_UNIT_NAME;
			else
				persistenceUnit = PRODUCTION_SERVER_UNIT_NAME;
		}
		return persistenceUnit;
	}
	
	private static boolean isTestServeOn() {
		try {
			InetAddress inet = InetAddress.getByName(TEST_SERVER_ADDRESS);
			if (inet.isReachable(1000))
				return true;
			else
				return false;
		} catch (IOException e) {
			return false;
		}
	}
}
