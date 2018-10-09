public class ClassTestTwo {

    @Test()
    public void testOne() {
        System.out.println("Two. test - 5");
    }

    @Test(priority = 8)
    public void testTwo() {
        System.out.println("Two. test - 8");
    }

    @Test(priority = 3)
    public void testThree() {
        System.out.println("Two. test - 3");
    }

    @Test(priority = 2)
    public void testFour() {
        System.out.println("Two. test - 2");
    }

    @AfterSuite
    private void testAfter() {
        System.out.println("After Suite");
    }

    @BeforeSuite
    private void testBefore() {
        System.out.println("Before Suite");
    }

    @AfterSuite
    private void testAfter_2() {
        System.out.println("After Suite");
    }

}
