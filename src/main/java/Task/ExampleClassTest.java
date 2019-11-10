package Task;


public class ExampleClassTest {

    @BeforeSuite
    public void startTest(){
        System.out.println("begin class test.");
    }

    @Test
    public int calculate(int a, int b){
        return a + b;
    }

    @Test(10)
    public double div(int a, int b){
        return a / b;
    }

    @AfterSuite
    public void endTest(){
        System.out.println("end class test.");
    }

}
