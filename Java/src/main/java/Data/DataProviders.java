package Data;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "loginData")
    public  Object[][] loginData(){
        return new Object[][]{
                new Object[]{"epam_homework","helloepam123"}
        };
    }

    @DataProvider(name = "createData")
    public Object[][] createData(){
        return new Object[][]{
                new Object[]{"epam_homework", "homework", "This is my homework"},
                new Object[]{"epam_homework", "homework", "This is my test"}
        };
    }

}
