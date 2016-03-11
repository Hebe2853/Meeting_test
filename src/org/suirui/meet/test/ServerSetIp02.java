package org.suirui.meet.test;

import org.suirui.meet.ui.huijian.newui.ServerSetupIpActivity;

import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.TextView;
import junit.framework.TestCase;

public class ServerSetIp02 extends ActivityInstrumentationTestCase2 {

	private Instrumentation ins;
	private ServerSetupIpActivity serverSetIp;
	private TextView save;
	
	public ServerSetIp02() {
		super(ServerSetupIpActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		Intent intent = new Intent();  
		intent.setClassName("org.suirui.meet", ServerSetupIpActivity.class.getName());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		serverSetIp=(ServerSetupIpActivity)getInstrumentation().startActivitySync(intent);
		save =(TextView)serverSetIp.findViewById(org.suirui.meet.R.id.title_lable_right);
	}
	
	public void testSaveIp(){	
		Log.e("hebe", "test save started");
		ActivityMonitor am = getInstrumentation().addMonitor(
                "org.suirui.meet.ui.huijian.newui.LoginNewActivity", null, false);
		serverSetIp.runOnUiThread(new Runnable()   
        {  
              
            @Override  
            public void run()   
            {  
                save.requestFocus();  
                save.performClick();  
            }  
        }); 
		am.waitForActivityWithTimeout(10000);
		assertEquals(1, am.getHits());
		
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
