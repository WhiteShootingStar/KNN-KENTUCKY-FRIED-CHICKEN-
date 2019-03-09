package ai;

public class Main {

	public static void main(String[] args) {
		AI ai = new AI();
		ai.learn("train.txt");
		ai.test("test.txt");
	}

}
