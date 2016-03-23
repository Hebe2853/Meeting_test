package org.suirui.meet.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.suirui.meet.contant.Configure;
import org.suirui.meet.ui.huijian.newui.LoginNewActivity;
import org.suirui.meet.ui.huijian.newui.LogoNewActivity;

import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class LogoTest01 extends ActivityInstrumentationTestCase2{

	public LogoTest01() {
		super(LogoNewActivity.class);
		
	}
	private LogoNewActivity logoActivity;
	private Instrumentation ins;
	
	@Before
	public void setUp() throws Exception {
		logoActivity = (LogoNewActivity) getActivity();
		ins = getInstrumentation();
		
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testLogoSuccess() {
    	Instrumentation ins01;
    	ins01 = getInstrumentation();
        ActivityMonitor am = ins01.addMonitor(
                "org.suirui.meet.ui.huijian.newui.LoginNewActivity", null, false);
        //�趨�ȴ�����Ҫ��Ļ�����ɹ������ȴ�5s
        am.waitForActivityWithTimeout(5000);
        //������ɹ���am.getHits()ֵΪ1������Ϊ0
       assertEquals(1, am.getHits());   
		
	}

}
