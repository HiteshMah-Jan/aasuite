package test;

import util.BeanUtil;
import util.Log;

public class TestJonelSubClass extends TestJonel {
	public static void main(String[] arg) {
		TestJonelSubClass j = new TestJonelSubClass();
		j.runNow();
		j.runNow(10);
		j.runNow("teka");
	}
	
	public void runNow() {
		super.runNow();
		Log.out(" 2");
	}

	public void runNow(int i) {
		runNow();
		runNow(BeanUtil.concat(i));
	}

	public void runNow(String i) {
		super.runNow();
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
		Log.out(i);
	}
}
