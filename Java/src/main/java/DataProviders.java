import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "createData")
    public Object[][] createData(){
        return new Object[][]{
                new Object[]{"epam_homework", "homework", "This is my homework"}
        };
    }

}
