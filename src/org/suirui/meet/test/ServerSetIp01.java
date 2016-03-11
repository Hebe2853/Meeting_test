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
		//�����������ʽ����inputδִ�е�����
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
	        /*���ڲ��������ڵ������߳���ִ�У����Դ˴���Ҫͬ��application�� 
	         * ����waitForIdleSync�ȴ������̺߳�UI�߳�ͬ�������ܽ������������ 
	         * waitForIdleSync��sendKeys��������UI�߳������� 
	         */  
	          
	        //ins.waitForIdleSync();
	        //����sendKeys�����������û���  
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
//                "org.suirui.meet.ui.huijian.newui��LoginNewActivity", null, false);
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
