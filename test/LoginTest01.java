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
		
//		Intent intent = new Intent();
//		intent.setClassName("org.suirui.meet", LoginNewActivity.class.getName());
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		login=(LoginNewActivity)getInstrumentation().startActivitySync(intent);
		
		ins = getInstrumentation();
		login = (LoginNewActivity) getActivity();
		//获取相关组件
		username = (EditText)login.findViewById(org.suirui.meet.R.id.user_id);
		pwd = (EditText)login.findViewById(org.suirui.meet.R.id.user_pwd);
		btLogin = (Button)login.findViewById(org.suirui.meet.R.id.login_btn);
		setIp = (Button)login.findViewById(org.suirui.meet.R.id.right_btn);
//    	username.setText("1002");  
//    	pwd.setText("123456");  
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
          
        //ins.waitForIdleSync();
        //调用sendKeys方法，输入用户名  
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
        //调用sendKeys方法，输入密码  
        sendRepeatedKeys(4,KeyEvent.KEYCODE_FORWARD_DEL);
        sendKeys(KeyEvent.KEYCODE_1, KeyEvent.KEYCODE_2,  
                KeyEvent.KEYCODE_3, KeyEvent.KEYCODE_4,  
                KeyEvent.KEYCODE_5, KeyEvent.KEYCODE_6);  
        Log.e("hebe","hebe:input is over");
    }  
 
    public void testInput(){
    	//调用测试类的input方法，实现输入用户信息(sendKeys实现输入)  
        input(); 
      //测试验证用户信息的预期值是否等于实际值  
        assertTrue(btLogin.isClickable());
        Log.e("hebe","hebe:"+btLogin.isClickable());
        assertEquals("1002", username.getText().toString().trim());  
        //密码的预期值123与实际值1234不符，Failure;  
        assertEquals("123456", pwd.getText().toString().trim()); 
    	
    }
    /*
     * 由于UI线程负责事件的监听和绘图，因此，必须保证UI线程能够随时响应用户的需求，
       UI线程里的操作应该向中断事件那样短小，费时的操作（如网络连接）需要另开线程，
       否则，如果UI线程超过5s没有响应用户请求，会弹出对话框提醒用户终止应用程序（ANP）
       此处直接login.runOnUiThread(new Runnable(){});会出现错误
       Only the original thread that created a view hierarchy can touch its views
      最后找到原因是因为username.setText()和pwd.setText()的问题，之前一直以为是runOnUiThread的问题
      得出结论：不能在测试程序中使用setText等
       */
    public void testLogin() throws InterruptedException{
    	
    	Instrumentation ins01;
    	ins01 = getInstrumentation();
        ActivityMonitor am = ins01.addMonitor(
                "org.suirui.meet.ui.huijian.newui.MeetNewEnterActivity", null, false);

        //点击操作运行在待测应用的线程中
        login.runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	Log.e("hebe","hebe:begin to perform click loginbt");
            	 btLogin.requestFocus();  
                 btLogin.performClick();  
               
            }
        });
        ins.waitForIdleSync();
        //设定等待满足要求的活动创建成功，最多等待5s
        am.waitForActivityWithTimeout(5000);
        //活动创建成功，am.getHits()值为1，否则为0
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
//    	//跳转后的界面的Activity名为com.example.demo.OtherActivity
//    	Instrumentation ins01;
//    	ins01 = getInstrumentation();
//        ActivityMonitor am = ins01.addMonitor(
//                "org.suirui.meet.ui.huijian.newui.ServerSetupIpActivity", null, false);
//
//        //点击操作运行在待测应用的线程中
//        login.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//            	setIp.requestFocus(); 
//                setIp.performClick();
//            }
//        });
//
//        //设定等待满足要求的活动创建成功，最多等待5s
//        am.waitForActivityWithTimeout(5000);
//        //活动创建成功，am.getHits()值为1，否则为0
//        assertEquals(1, am.getHits());
//        ins01.removeMonitor(am);
//    }
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

}
