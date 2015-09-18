package br.com.farofa.gm.tests;

import br.com.farofa.gm.util.SyncCodeGeneratorUtil;

public class SyncCodeTests {
	public static void main(String[] args) {
		testResult();
	}
	
	public static void testResult() {
		String code = SyncCodeGeneratorUtil.generate();
		System.out.println(code);
	}
}
