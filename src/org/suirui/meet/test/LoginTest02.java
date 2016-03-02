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
		  //创建Intent，通过Intent传递用户的登录信息  
        Intent intent = new Intent();  
        intent.putExtra("userName", "feixun");  
        intent.putExtra("passWord", "123");  
        //通过携带用户登录信息的intent启动FxResultActivity  
        login = launchActivityWithIntent("org.suirui.meet",   
                LoginNewActivity.class, intent);  
        //获取UI组件  
        
	}
	 //测试验证用户的登录信息  
    public void testLoginInfo()  
    {  
        //验证预期值是否等于实际值  
        assertEquals(MeetNewEnterActivity.class,getActivity());  
    } 
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
