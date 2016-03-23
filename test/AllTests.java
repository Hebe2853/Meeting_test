package org.suirui.meet.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(LogoTest01.class);
		suite.addTestSuite(LoginTest02.class);
		suite.addTestSuite(ServerSetIp01.class);
		//suite.addTestSuite(ServerSetIp02.class);
		suite.addTestSuite(LoginTest01.class);
		//$JUnit-END$
		return suite;
	}

}
