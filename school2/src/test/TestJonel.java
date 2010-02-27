package test;

public class TestJonel {
	public TestJonel() {
		System.out.print("OLD");
	}

	public TestJonel(String str) {
		System.out.print("NEW");
	}
	
	public void runNow() {
		System.out.print("RUN NOW");
	}
	
	public static void main(String[] arg) {
		TestJonel j = new TestJonel();
		
		String str = "";
		str.length();
	}
}
