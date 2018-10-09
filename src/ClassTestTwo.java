public class ClassTestTwo {

    @Test()
    public void testOne() {
        System.out.println("5");
    }

    @Test(priority = 7)
    public void testTwo() {
        System.out.println("7");
    }

    @Test(priority = 3)
    public void testThree() {
        System.out.println("3");
    }

    @Test(priority = 5)
    public void testFour() {
        System.out.println("5");
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
