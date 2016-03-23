package org.suirui.meet.uiaotomator;

import java.io.File;

import android.os.Environment;
import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import de.mindpipe.android.logging.log4j.LogConfigurator;

import org.apache.log4j.*;
/*
 * uiautomator不能在eclipse中直接进行编译，因此需要到所在的sdk的tools下运行如下命令去生成编译文件
 * 1.android create uitest-project -n Meeting_test -t 1 -p d:\github\Meeting_test
 * -n 后面跟的是projectname
 * -t 后面跟的是设备编号
 * -p跟的是项目所在的根目录
 * 运行后生成build。xml，在eclipse中刷新就会出现
 * 2.设置ANDROID_HOME
 * set ANDROID_HOME = D:\adt-bundle-windows-x86_64-20140321\sdk
 * 3.在项目的根目录下运行ant build
 * 4.编译文件中的注释或者代码包含中文的，需要设置项目的编码格式为utf-8，properties-resources中修改
 * 5.将生成的jar推送到手机端
 * adb push xxx.jar /data/local/tmp
 * 6.在手机端运行自动化脚本中的测试用例,运行时，必须要制定到具体的类名，不能是包名
 * adb shell uiautomator runtest xxx.jar -c org.suirui.meet.testpackage.testclass
 * 本例子中是
 * adb shell uiautomator runtest Meeting_test.jar -c org.suirui.meet.uiaotomator.UiTest01
 * 测试中总是出现didn't find class，原因是因为本项目中uiaotomator写错了
 * */
public class UiTest01 extends UiAutomatorTestCase {
	String testCmp = "org.suirui.meet/.FirstLoadActivity";
	String serverIP = "192.168.63.18";
	String username = "1002";
	String password = "123456";
	String meetingId = "1201115";
	String storePath = "/data/local/tmp/Meeting.jpg";
	private static Logger logger;// = Logger.getLogger(UiTest01.class); 
	
	public UiTest01() {
		super();
	}


	public void setUp() throws Exception {
		super.setUp();
		//BasicConfigurator.configure();
		//PropertyConfigurator.configure("log4j.properties");
    	//startApp(testCmp);
		configLog();
	}
	/*
	 * 书上的例子讲怎么启动activity，但是实际中总是找不到class
	private int startApp(String componentName){
		StringBuffer meet_buffer = new StringBuffer();
		//UIAutomator的启动命令为
		//am start -n  "com.xxx.xxx/.MainActivity"
		meet_buffer.append("am start -n ");
		meet_buffer.append(componentName);
		int ret = -1;
		try{
			Process process = Runtime.getRuntime().exec(meet_buffer.toString());
			ret = process.waitFor();
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
		return ret;
	}
	*/
	public void testApp() throws UiObjectNotFoundException{
		getUiDevice().pressHome();
		UiObject huijianApp = new UiObject(new UiSelector().text("会见"));
		huijianApp.clickAndWaitForNewWindow();
		logger.debug("find huijian app and start up APP");
		setIp();
		login();
		joinMeeting();
		quitMeeting();
		logout();
		
	}

	private void configLog(){
		final LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(Environment.getDataDirectory() + File.separator + "crifanli_log4j.log");
        System.out.println("file path is:"+Environment.getDataDirectory() + File.separator + "crifanli_log4j.log");
        // Set the root log level
        logConfigurator.setRootLevel(Level.DEBUG);
        // Set log level of a specific logger
        logConfigurator.setLevel("org.apache", Level.DEBUG);
        logConfigurator.configure();
 
        //gLogger = Logger.getLogger(this.getClass());
        logger = Logger.getLogger(UiTest01.class);
		
	}
	public void tearDown() throws Exception {
		super.tearDown();
		getUiDevice().pressRecentApps();
		UiObject exitApp = new UiObject(new UiSelector()
										.className("android.widget.ImageView")
										.index(0));
		exitApp.swipeRight(20);
	}


	private void setIp() throws UiObjectNotFoundException {
		logger.debug("test setip and save functions");
		Log.v("hebe", "test setIp");
		System.out.println("test setip and save functions");
		//resourceId查找方式在android的4.2.2的api17中不支持，api18才支持该方式
		UiObject setting_bt = new UiObject(new UiSelector()
											.className("android.widget.Button")
											.index(1));
		setting_bt.clickAndWaitForNewWindow();
		/*
		 * 示例，可以加个判断，看起来太乱后边都去掉了
		if(setting_bt.exists()&&setting_bt.isEnabled()){
			setting_bt.clickAndWaitForNewWindow();
		}
		else{
			Log.e("serverIP_et error","can not find serverIp_et");
			
		}
		*/
		
		//UiObject serverIp_et = new UiObject(new UiSelector().resourceId("org.suirui.meet:id/title_lable_right"));
		
		UiObject serverIp_et = new UiObject(new UiSelector().className("android.widget.EditText").index(0));
		serverIp_et.click();
		serverIp_et.setText(serverIP);
		
		//assertEquals("192.168.63.18",serverIp_et.getText().toString().trim());
		//UiObject saveIp_bt = new UiObject(new UiSelector().text("保存"));
		/*由于app的问题总是在设置页面会自动去注册服务器，导致未填写正确ip的时候会提示连接服务器异常,暂时解决不了
		UiObject warn_text = new UiObject(new UiSelector().text("服务器连接异常"));
		warn_text.waitUntilGone(2000);
		在应用中去掉了此处的注册提示
		*/
		UiObject saveIp_tv = new UiObject(new UiSelector().className("android.widget.TextView").index(2));
		saveIp_tv.clickAndWaitForNewWindow();
		//注意index()与instance()的区别，因为直接根据text去查找，可能不准确，因此验证的是最好用index或者instance
		
		UiObject login_bt = new UiObject(new UiSelector()
										.className("android.widget.Button")
										.instance(0));
		assertTrue(login_bt.exists());
		
	}
	private void login() throws UiObjectNotFoundException{
		logger.info("test login function");
		UiObject user_et = new UiObject(new UiSelector()
											.className("android.widget.EditText")
											.index(0));
		clearEditText(getUiDevice(),user_et,username);
		//user_et.clearTextField();网上说是在api18不能用
		//obj.clearTextField() method is clearing a text field when "selectall" option is available. 
		//if "selectall" option is hiding at the top unable to clear the text field
		user_et.setText(username);
		UiObject pwd_et = new UiObject(new UiSelector()
											.className("android.widget.EditText")
											.index(1));
		pwd_et.click();
		pwd_et.setText(password);
		UiObject login_bt = new UiObject(new UiSelector()
											.className("android.widget.Button")
											.index(0));
		login_bt.clickAndWaitForNewWindow();
		UiObject joinMeeting_bt = new UiObject(new UiSelector().text("加入会议"));
		assertTrue(joinMeeting_bt.exists());
	}
	private void joinMeeting() throws UiObjectNotFoundException{
		logger.info("test joinMeeting,meetingid is :"+meetingId);
		UiObject meetingId_et = new UiObject(new UiSelector()
												.className("android.widget.EditText")
												.index(0));
		meetingId_et.click();
		meetingId_et.setText(meetingId);
		UiObject joinMmeeting_bt = new UiObject(new UiSelector()
												.className("android.widget.Button")
												.index(0));
		joinMmeeting_bt.clickAndWaitForNewWindow();
		
		UiObject assertMeetingId_tv = new UiObject(new UiSelector().text("喇叭"));
		assertMeetingId_tv.waitForExists(5000);
		assertTrue(assertMeetingId_tv.exists());
		File meetingJPG = new File(storePath);
		Boolean snapshot = UiDevice.getInstance().takeScreenshot(meetingJPG);
		assertTrue(snapshot);
		logger.info("take snapshot,the store path is"+storePath);
	}
	private void quitMeeting() throws UiObjectNotFoundException{
		logger.info("test quitMeeting");
		UiObject quit_iw = new UiObject(new UiSelector()
											.className("android.widget.ImageView")
											.index(2));
		quit_iw.clickAndWaitForNewWindow();
		UiObject quitEsc_iw = new UiObject(new UiSelector().text("取消"));
		quitEsc_iw.click();
		quit_iw.clickAndWaitForNewWindow();
		UiObject leave_bt = new UiObject(new UiSelector().text("离开"));
		leave_bt.clickAndWaitForNewWindow();
		UiObject joinMeeting_bt = new UiObject(new UiSelector().text("加入会议"));
		assertTrue(joinMeeting_bt.exists());
	}
	private void logout() throws UiObjectNotFoundException{
		logger.info("test logout function;");
		UiObject setting_bt = new UiObject(new UiSelector().className("android.widget.Button").index(1));
		setting_bt.clickAndWaitForNewWindow();
		UiObject logout_bt = new UiObject(new UiSelector().text("注销当前帐号"));
		logout_bt.clickAndWaitForNewWindow();
		UiObject cancel_bt = new UiObject(new UiSelector().text("取消"));
		cancel_bt.click();
		logout_bt.clickAndWaitForNewWindow();
		UiObject ok_bt = new UiObject(new UiSelector().text("确定"));
		ok_bt.clickAndWaitForNewWindow();
		UiObject login_bt = new UiObject(new UiSelector().text("登录"));
		assertTrue(login_bt.exists());
		UiObject user_et = new UiObject(new UiSelector().className("android.widget.EditText").index(0));
		//判断注销的时候账号存在，密码为空
		assertEquals(username,user_et.getText().toString().trim());
		UiObject pwd_et = new UiObject(new UiSelector().className("android.widget.EditText").index(1));
		assertEquals("",pwd_et.getText().toString().trim());
	}
	private void clearEditText(UiDevice uiDevice, UiObject textBox,String username) throws UiObjectNotFoundException{
	    int estimatedLength = username.length();
	    textBox.clickBottomRight();
	    for (int i = 0; i < estimatedLength; i++) {
	        uiDevice.pressDelete();
	    }
		
	}

}
