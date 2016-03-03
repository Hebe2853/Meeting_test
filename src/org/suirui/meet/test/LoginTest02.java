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
		  //创建Intent，通过Intent传递用户的登录信息  
        Intent intent = new Intent();  
        //putExtra("A",B)中，AB为键值对，第一个参数为键名，第二个参数为键对应的值。
//        intent.putExtra("userName", "feixun");  
//        intent.putExtra("passWord", "123");  
        //通过携带用户登录信息的intent启动FxResultActivity  
//        login = launchActivityWithIntent("",   
//                LoginNewActivity.class, intent);  
		intent.setClassName("org.suirui.meet.ui.huijian.newui", LoginNewActivity.class.getName());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		login=(LoginNewActivity)getInstrumentation().startActivitySync(intent);
        usernameEt = (EditText)login.findViewById(org.suirui.meet.R.id.user_id);
        pwdEt = (EditText)login.findViewById(org.suirui.meet.R.id.user_pwd);
        btLogin = (Button)login.findViewById(org.suirui.meet.R.id.login_btn);
		setIp = (Button)login.findViewById(org.suirui.meet.R.id.right_btn);
        //获取UI组件  
        
	}
	
	 //测试验证用户的登录信息  
    public void testLoginInfo()  
    {  
        //验证预期值是否等于实际值  
    	assertEquals("feixun", usernameEt.getText().toString());
        assertEquals("123", pwdEt.getText().toString());
    } 
    public void testSetIpButton(){
    	//跳转后的界面的Activity名为com.example.demo.OtherActivity
        ActivityMonitor am = getInstrumentation().addMonitor(
                "com.example.demo.OtherActivity", null, false);

        //点击操作运行在待测应用的线程中
        login.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setIp.performClick();
            }
        });

        //设定等待满足要求的活动创建成功，最多等待5s
        am.waitForActivityWithTimeout(5000);
        //活动创建成功，am.getHits()值为1，否则为0
        assertEquals(1, am.getHits());
    }
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
