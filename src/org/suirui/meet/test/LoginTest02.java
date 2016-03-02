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
import android.widget.TextView;

public class LoginTest02 extends ActivityInstrumentationTestCase2 {

	private LoginNewActivity login;
	private TextView loginInfo;
 	public LoginTest02() {
		super(LoginNewActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		  //����Intent��ͨ��Intent�����û��ĵ�¼��Ϣ  
        Intent intent = new Intent();  
        intent.putExtra("userName", "feixun");  
        intent.putExtra("passWord", "123");  
        //ͨ��Я���û���¼��Ϣ��intent����FxResultActivity  
        login = launchActivityWithIntent("org.suirui.meet",   
                LoginNewActivity.class, intent);  
        //��ȡUI���  
        
	}
	 //������֤�û��ĵ�¼��Ϣ  
    public void testLoginInfo()  
    {  
        //��֤Ԥ��ֵ�Ƿ����ʵ��ֵ  
        assertEquals(MeetNewEnterActivity.class,getActivity());  
    } 
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
