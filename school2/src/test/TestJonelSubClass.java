package test;

public class TestJonelSubClass extends TestJonel {
	public static void main(String[] arg) {
		TestJonelSubClass j = new TestJonelSubClass();
		j.runNow();
		j.runNow(10);
		j.runNow("teka");
	}
	
	public void runNow() {
		super.runNow();
		System.out.println(" 2");
	}

	public void runNow(int i) {
		runNow();
		runNow(i+"");
	}

	public void runNow(String i) {
		super.runNow();
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
		System.out.println(i);
	}
}
