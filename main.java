package up.coo.tp8;

public class main {

	public static void main(String[] args) {
		COOUnitTestCaseDemo unitTest = new COOUnitTestCaseDemo();
		COOUnit cooUnit = new COOUnit(unitTest);
		cooUnit.TestMethods();
		System.out.println("\n");
		System.out.println("********** Introspection ************");
		cooUnit.display();
		System.out.println("\n");
		System.out.println("********** Réflexivité ************");
		cooUnit.drive();
	}

}
