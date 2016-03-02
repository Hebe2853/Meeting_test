package org.suirui.meet.test;

import junit.framework.TestCase;
import org.suirui.meet.ui.huijian.newui.LoginNewActivity;
import org.suirui.meet.ui.huijian.newui.MeetNewEnterActivity;
import org.suirui.meet.ui.huijian.newui.ServerSetupIpActivity;

import junit.framework.TestCase;
import android.app.Instrumentation;
import android.content.Intent;
import android.test.InstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
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
		/*
		Intent intent = new Intent();
		intent.setClassName("org.suirui.meet.ui.huijian.newui", LoginNewActivity.class.getName());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		login=(LoginNewActivity)getInstrumentation().startActivitySync(intent);
		*/
		ins = getInstrumentation();
		login = (LoginNewActivity) getActivity();
		//��ȡ������
		username = (EditText)login.findViewById(org.suirui.meet.R.id.user_id);
		pwd = (EditText)login.findViewById(org.suirui.meet.R.id.user_pwd);
		btLogin = (Button)login.findViewById(org.suirui.meet.R.id.login_btn);
		setIp = (Button)login.findViewById(org.suirui.meet.R.id.right_btn);
			
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
        ins.waitForIdleSync();  
          
        //����sendKeys�����������û���  
        sendKeys(KeyEvent.KEYCODE_P, KeyEvent.KEYCODE_H,  
                KeyEvent.KEYCODE_I, KeyEvent.KEYCODE_C,  
                KeyEvent.KEYCODE_O, KeyEvent.KEYCODE_M,  
                KeyEvent.KEYCODE_M);  
          
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
          
        //����sendKeys��������������  
        sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_2,   
                KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_4);  
    }  
    public void testInput(){
    	//���ò������input������ʵ�������û���Ϣ(sendKeysʵ������)  
        input(); 
      //������֤�û���Ϣ��Ԥ��ֵ�Ƿ����ʵ��ֵ  
        assertEquals("phicomm", username.getText().toString());  
        //�����Ԥ��ֵ123��ʵ��ֵ1234������Failure;  
        assertEquals("123", pwd.getText().toString()); 
    	
    }
    public void testLogin(){
    	 input();  
         //�����̣߳���ͨ�����߳���ʵ����UI�߳���ִ�в���  
         ins.runOnMainSync(new Runnable()   
         {  
               
             @Override  
             public void run()   
             {  
                 // TODO Auto-generated method stub  
                 btLogin.requestFocus();  
                 btLogin.performClick();  
             }  
         });  
         assertEquals(MeetNewEnterActivity.class,getActivity());
    	
    }
    public void testSetIp(){
    	input();  
        ins.runOnMainSync(new Runnable()   
        {  
              
            @Override  
            public void run()   
            {  
                // TODO Auto-generated method stub  
                setIp.requestFocus();  
                //�����ť  
                setIp.performClick();  
            }  
        });  
        //��֤���������Ƿ���ת
        //assertEquals("", userName.getText().toString());  
        assertEquals(ServerSetupIpActivity.class, getActivity());  
    }
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

}
