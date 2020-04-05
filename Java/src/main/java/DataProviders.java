import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="loginData")
    public Object[][] loginInfo(){
        return  new Object[][]{
                new Object[]{"epam_homework", "helloepam123"}
        };
    }

    @DataProvider(name = "createData")
    public Object[][] createData(){
        return new Object[][]{
                new Object[]{"epam_homework", "homework", "This is my homework"}
              //  new Object[]{"epam_homework", "Homework 2", "This is addition to the task"}
        };
    }

}
