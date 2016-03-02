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
		/**这个程序中需要输入用户信息和密码，也就是说需要发送key事件， 
         * 所以，必须在调用getActivity之前，调用下面的方法来关闭 
         * touch模式，否则key事件会被忽略 
         */  
        //关闭touch模式 
		setActivityInitialTouchMode(false); //该行总是报错，原因是extends继承的是InstrumentationTestCase
		
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
		//获取相关组件
		username = (EditText)login.findViewById(org.suirui.meet.R.id.user_id);
		pwd = (EditText)login.findViewById(org.suirui.meet.R.id.user_pwd);
		btLogin = (Button)login.findViewById(org.suirui.meet.R.id.login_btn);
		setIp = (Button)login.findViewById(org.suirui.meet.R.id.right_btn);
			
	}

	  //该测试用例实现在测试其他用例之前，测试确保获取的组件不为空  
    public void testPreConditions()  
    {  
        assertNotNull(username);  
        assertNotNull(pwd);  
        assertNotNull(btLogin);  
        assertNotNull(setIp);  
    } 
    /**该方法实现在登录界面上输入相关的登录信息。由于UI组件的 
     * 相关处理（如此处的请求聚焦）需要在UI线程上实现， 
     * 所以需调用Activity的runOnUiThread方法实现。 
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
        /*由于测试用例在单独的线程上执行，所以此处需要同步application， 
         * 调用waitForIdleSync等待测试线程和UI线程同步，才能进行输入操作。 
         * waitForIdleSync和sendKeys不允许在UI线程里运行 
         */  
        ins.waitForIdleSync();  
          
        //调用sendKeys方法，输入用户名  
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
          
        //调用sendKeys方法，输入密码  
        sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_2,   
                KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_4);  
    }  
    public void testInput(){
    	//调用测试类的input方法，实现输入用户信息(sendKeys实现输入)  
        input(); 
      //测试验证用户信息的预期值是否等于实际值  
        assertEquals("phicomm", username.getText().toString());  
        //密码的预期值123与实际值1234不符，Failure;  
        assertEquals("123", pwd.getText().toString()); 
    	
    }
    public void testLogin(){
    	 input();  
         //开新线程，并通过该线程在实现在UI线程上执行操作  
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
                //点击按钮  
                setIp.performClick();  
            }  
        });  
        //验证点击后界面是否跳转
        //assertEquals("", userName.getText().toString());  
        assertEquals(ServerSetupIpActivity.class, getActivity());  
    }
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

}
