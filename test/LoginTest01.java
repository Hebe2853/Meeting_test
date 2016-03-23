package org.suirui.meet.test;

import junit.framework.TestCase;

import org.suirui.meet.ui.huijian.newui.LoginNewActivity;
import org.suirui.meet.ui.huijian.newui.MeetNewEnterActivity;
import org.suirui.meet.ui.huijian.newui.ServerSetupIpActivity;

import junit.framework.TestCase;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.os.Handler;
import android.test.InstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;

public class LoginTest01 extends ActivityInstrumentationTestCase2 {
	public LoginTest01() {
		super(LoginNewActivity.class);
		// TODO Auto-generated constructor stub
	}
	private Instrumentation ins;
	private LoginNewActivity login;
	private EditText username;
	private EditText pwd;
	private Button btLogin;
	private Button setIp;
	
	protected void setUp() {
		/**�����������Ҫ�����û���Ϣ�����룬Ҳ����˵��Ҫ����key�¼��� 
         * ���ԣ������ڵ���getActivity֮ǰ����������ķ������ر� 
         * touchģʽ������key�¼��ᱻ���� 
         */  
        //�ر�touchģʽ 
		setActivityInitialTouchMode(false); //�������Ǳ���ԭ����extends�̳е���InstrumentationTestCase
		
		try {
			super.setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Intent intent = new Intent();
//		intent.setClassName("org.suirui.meet", LoginNewActivity.class.getName());
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		login=(LoginNewActivity)getInstrumentation().startActivitySync(intent);
		
		ins = getInstrumentation();
		login = (LoginNewActivity) getActivity();
		//��ȡ������
		username = (EditText)login.findViewById(org.suirui.meet.R.id.user_id);
		pwd = (EditText)login.findViewById(org.suirui.meet.R.id.user_pwd);
		btLogin = (Button)login.findViewById(org.suirui.meet.R.id.login_btn);
		setIp = (Button)login.findViewById(org.suirui.meet.R.id.right_btn);
//    	username.setText("1002");  
//    	pwd.setText("123456");  
	}

	  //�ò�������ʵ���ڲ�����������֮ǰ������ȷ����ȡ�������Ϊ��  
    public void testPreConditions()  
    {  
        assertNotNull(username);  
        assertNotNull(pwd);  
        assertNotNull(btLogin);  
        assertNotNull(setIp);  
    } 
    /**�÷���ʵ���ڵ�¼������������صĵ�¼��Ϣ������UI����� 
     * ��ش�����˴�������۽�����Ҫ��UI�߳���ʵ�֣� 
     * ���������Activity��runOnUiThread����ʵ�֡� 
     */ 
    
    public void input()  
    {  
    	
        login.runOnUiThread(new Runnable()   
        {  
              
            @Override  
            public void run()   
            {  
                // TODO Auto-generated method stub  
                username.requestFocus();  
                username.performClick();  
            }  
        });  
        /*���ڲ��������ڵ������߳���ִ�У����Դ˴���Ҫͬ��application�� 
         * ����waitForIdleSync�ȴ������̺߳�UI�߳�ͬ�������ܽ������������ 
         * waitForIdleSync��sendKeys��������UI�߳������� 
         */  
          
        //ins.waitForIdleSync();
        //����sendKeys�����������û���  
        sendRepeatedKeys(4,KeyEvent.KEYCODE_FORWARD_DEL);
        sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_0,  
                KeyEvent.KEYCODE_0, KeyEvent.KEYCODE_2);         
          
        login.runOnUiThread(new Runnable()   
        {  
              
            @Override  
            public void run()   
            {  
                // TODO Auto-generated method stub  
                pwd.requestFocus();  
                pwd.performClick();  
            }  
        });  
          
        //ins.waitForIdleSync();
        //����sendKeys��������������  
        sendRepeatedKeys(4,KeyEvent.KEYCODE_FORWARD_DEL);
        sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_2,  
                KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_4,  
                KeyEvent.KEYCODE_5, KeyEvent.KEYCODE_6);  
        Log.e("hebe","hebe:input is over");
    }  
 
    public void testInput(){
    	//���ò������input������ʵ�������û���Ϣ(sendKeysʵ������)  
        input(); 
      //������֤�û���Ϣ��Ԥ��ֵ�Ƿ����ʵ��ֵ  
        assertTrue(btLogin.isClickable());
        Log.e("hebe","hebe:"+btLogin.isClickable());
        assertEquals("1002", username.getText().toString().trim());  
        //�����Ԥ��ֵ123��ʵ��ֵ1234������Failure;  
        assertEquals("123456", pwd.getText().toString().trim()); 
    	
    }
    /*
     * ����UI�̸߳����¼��ļ����ͻ�ͼ����ˣ����뱣֤UI�߳��ܹ���ʱ��Ӧ�û�������
       UI�߳���Ĳ���Ӧ�����ж��¼�������С����ʱ�Ĳ��������������ӣ���Ҫ���̣߳�
       �������UI�̳߳���5sû����Ӧ�û����󣬻ᵯ���Ի��������û���ֹӦ�ó���ANP��
       �˴�ֱ��login.runOnUiThread(new Runnable(){});����ִ���
       Only the original thread that created a view hierarchy can touch its views
      ����ҵ�ԭ������Ϊusername.setText()��pwd.setText()�����⣬֮ǰһֱ��Ϊ��runOnUiThread������
      �ó����ۣ������ڲ��Գ�����ʹ��setText��
       */
    public void testLogin() throws InterruptedException{
    	
    	Instrumentation ins01;
    	ins01 = getInstrumentation();
        ActivityMonitor am = ins01.addMonitor(
                "org.suirui.meet.ui.huijian.newui.MeetNewEnterActivity", null, false);

        //������������ڴ���Ӧ�õ��߳���
        login.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	Log.e("hebe","hebe:begin to perform click loginbt");
            	 btLogin.requestFocus();  
                 btLogin.performClick();  
               
            }
        });
        ins.waitForIdleSync();
        //�趨�ȴ�����Ҫ��Ļ�����ɹ������ȴ�5s
        am.waitForActivityWithTimeout(5000);
        //������ɹ���am.getHits()ֵΪ1������Ϊ0
        assertEquals(1, am.getHits());    	
    }
    /*
	  public void testLogin(){
		//username.setText("1002");  
		//pwd.setText("123456"); 
		final Handler cwjHandler = new Handler();
		final Instrumentation ins01;
		ins01 = getInstrumentation();
	    final ActivityMonitor am = ins01.addMonitor(
	            "org.suirui.meet.ui.huijian.newui.MeetNewEnterActivity", null, false);
	    final Runnable clickLoginBt = new Runnable() {
	        public void run() {
	        	btLogin.requestFocus();  
	            btLogin.performClick();
	        	assertEquals(1, am.getHits());
	    	    ins01.removeMonitor(am);
	        }
	    };
	    try{
	    	login.runOnUiThread(clickLoginBt);
	    }
	    catch(Exception e){
	    	e.printStackTrace();	    	
	    }   	
	}
	*/
//    public void testSetIp(){
//    	//��ת��Ľ����Activity��Ϊcom.example.demo.OtherActivity
//    	Instrumentation ins01;
//    	ins01 = getInstrumentation();
//        ActivityMonitor am = ins01.addMonitor(
//                "org.suirui.meet.ui.huijian.newui.ServerSetupIpActivity", null, false);
//
//        //������������ڴ���Ӧ�õ��߳���
//        login.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//            	setIp.requestFocus(); 
//                setIp.performClick();
//            }
//        });
//
//        //�趨�ȴ�����Ҫ��Ļ�����ɹ������ȴ�5s
//        am.waitForActivityWithTimeout(5000);
//        //������ɹ���am.getHits()ֵΪ1������Ϊ0
//        assertEquals(1, am.getHits());
//        ins01.removeMonitor(am);
//    }
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

}
