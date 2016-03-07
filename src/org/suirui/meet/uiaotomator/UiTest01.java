package org.suirui.meet.uiaotomator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
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
 * adb shell uiautomator runtest xxx.jar -c org.suirui.meet.ServerSetIpActivity
 * */
public class UiTest01 extends UiAutomatorTestCase {
	String testCmp = "org.suirui.meet/.ServerSetupIpActivity";
	String serverIP = "192.168.63.18";
	public UiTest01() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		startApp(testCmp);
	}
	private int startApp(String componentName){
		StringBuffer meet_buffer = new StringBuffer();
		//UIAutomator的启动命令为
		//am start -n  "com.xxx.xxx/.MainActivity"
		meet_buffer.append("am start -n");
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

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test01() throws UiObjectNotFoundException {
		Log.v("test save serverip", "edit serverIp and save");
		UiObject serverIp_et = new UiObject(new UiSelector().text("10.12.0.10"));
		if(serverIp_et.exists()&&serverIp_et.isEnabled()){
			serverIp_et.click();
			serverIp_et.setText(serverIP);
		}
		else{
			Log.e("serverIP_et error","can not find serverIp_et");
			
		}
		UiObject saveIp_bt = new UiObject(new UiSelector().text("保存"));
		if(saveIp_bt.exists()&&saveIp_bt.isEnabled()){
			saveIp_bt.clickAndWaitForNewWindow();
		}
		else{
			Log.e("saveIp_bt error","can not find saveIp_bt");
		}
		//注意index()与instance()的区别，因为直接根据text去查找，可能不准确，因此验证的是最好用index或者instance
		UiObject login_bt = new UiObject(new UiSelector()
										.className("android.widget.Button")
										.instance(0));
		assertTrue(login_bt.exists());
	}
	
}
