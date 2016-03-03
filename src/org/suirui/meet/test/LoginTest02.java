package org.suirui.meet.test;

import junit.framework.TestCase;
import org.suirui.meet.ui.huijian.newui.LoginNewActivity;
import org.suirui.meet.ui.huijian.newui.MeetNewEnterActivity;
import org.suirui.meet.ui.huijian.newui.ServerSetupIpActivity;

import junit.framework.TestCase;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.InstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginTest02 extends ActivityInstrumentationTestCase2 {

	private LoginNewActivity login;
	private EditText usernameEt,pwdEt;
	private Button btLogin,setIp;
 	public LoginTest02() {
		super(LoginNewActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		  //����Intent��ͨ��Intent�����û��ĵ�¼��Ϣ  
        Intent intent = new Intent();  
        //putExtra("A",B)�У�ABΪ��ֵ�ԣ���һ������Ϊ�������ڶ�������Ϊ����Ӧ��ֵ��
//        intent.putExtra("userName", "feixun");  
//        intent.putExtra("passWord", "123");  
        //ͨ��Я���û���¼��Ϣ��intent����FxResultActivity  
//        login = launchActivityWithIntent("",   
//                LoginNewActivity.class, intent);  
		intent.setClassName("org.suirui.meet.ui.huijian.newui", LoginNewActivity.class.getName());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		login=(LoginNewActivity)getInstrumentation().startActivitySync(intent);
        usernameEt = (EditText)login.findViewById(org.suirui.meet.R.id.user_id);
        pwdEt = (EditText)login.findViewById(org.suirui.meet.R.id.user_pwd);
        btLogin = (Button)login.findViewById(org.suirui.meet.R.id.login_btn);
		setIp = (Button)login.findViewById(org.suirui.meet.R.id.right_btn);
        //��ȡUI���  
        
	}
	
	 //������֤�û��ĵ�¼��Ϣ  
    public void testLoginInfo()  
    {  
        //��֤Ԥ��ֵ�Ƿ����ʵ��ֵ  
    	assertEquals("feixun", usernameEt.getText().toString());
        assertEquals("123", pwdEt.getText().toString());
    } 
    public void testSetIpButton(){
    	//��ת��Ľ����Activity��Ϊcom.example.demo.OtherActivity
        ActivityMonitor am = getInstrumentation().addMonitor(
                "com.example.demo.OtherActivity", null, false);

        //������������ڴ���Ӧ�õ��߳���
        login.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setIp.performClick();
            }
        });

        //�趨�ȴ�����Ҫ��Ļ�����ɹ������ȴ�5s
        am.waitForActivityWithTimeout(5000);
        //������ɹ���am.getHits()ֵΪ1������Ϊ0
        assertEquals(1, am.getHits());
    }
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
