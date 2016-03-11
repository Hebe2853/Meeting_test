package org.suirui.meet.test;

import org.suirui.meet.ui.huijian.newui.LoginNewActivity;
import org.suirui.meet.ui.huijian.newui.ServerSetupIpActivity;

import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServerSetIp01 extends ActivityInstrumentationTestCase2{
	private Instrumentation ins;
	private ServerSetupIpActivity serverSetIp;
	private EditText ip;
	private TextView save;
	public ServerSetIp01() {
		super(ServerSetupIpActivity.class);
	}

	public void setUp() throws Exception {
		try {
			super.setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ins = getInstrumentation();
		//setIp = (ServerSetupIpActivity) getActivity();
		//上面的启动方式出现input未执行的问题
		Intent intent = new Intent();  
		intent.setClassName("org.suirui.meet", ServerSetupIpActivity.class.getName());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		serverSetIp=(ServerSetupIpActivity)getInstrumentation().startActivitySync(intent);
		ip = (EditText)serverSetIp.findViewById(org.suirui.meet.R.id.user_server_ip);
		save =(TextView)serverSetIp.findViewById(org.suirui.meet.R.id.title_lable_right);
	}


	 public void input()  
	    {  
	    	
		 serverSetIp.runOnUiThread(new Runnable()   
	        {  
	              
	            @Override  
	            public void run()   
	            {  
	                // TODO Auto-generated method stub  
	            	ip.requestFocus();  
	            	ip.performClick();  
	            }  
	        });  
	        /*由于测试用例在单独的线程上执行，所以此处需要同步application， 
	         * 调用waitForIdleSync等待测试线程和UI线程同步，才能进行输入操作。 
	         * waitForIdleSync和sendKeys不允许在UI线程里运行 
	         */  
	          
	        //ins.waitForIdleSync();
	        //调用sendKeys方法，输入用户名  
	       // sendRepeatedKeys(8,KeyEvent.KEYCODE_SOFT_RIGHT);
	        //sendRepeatedKeys(1,KeyEvent.KEYCODE_DEL);
		 try{
			 sendRepeatedKeys(13,KeyEvent.KEYCODE_FORWARD_DEL);
		        
		        sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_9, KeyEvent.KEYCODE_2,KeyEvent.KEYCODE_PERIOD, 
		        		KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_6, KeyEvent.KEYCODE_8,KeyEvent.KEYCODE_PERIOD,
		        		KeyEvent.KEYCODE_6, KeyEvent.KEYCODE_3,KeyEvent.KEYCODE_PERIOD,
		        		KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_8);
		 	}
		 catch(Exception e){
			 e.printStackTrace();
		 }
	        
	        		
	    }
	public void testInputIp() {
		input();
		assertEquals("192.168.63.18",ip.getText().toString().trim());
		Log.e("hebe", "test input finished");
	}
//	public void testSaveIp(){	
//		Log.e("hebe", "test save started");
//		ActivityMonitor am = getInstrumentation().addMonitor(
//                "org.suirui.meet.ui.huijian.newui。LoginNewActivity", null, false);
//		ins.runOnMainSync(new Runnable()   
//        {  
//              
//            @Override  
//            public void run()   
//            {  
//                save.requestFocus();  
//                save.performClick();  
//            }  
//        }); 
//		am.waitForActivityWithTimeout(5000);
//		assertEquals(1, am.getHits());	
//	}
	public void tearDown() throws Exception {
		super.tearDown();
	}
}
