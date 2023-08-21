import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import org.example.Man;
import org.example.Woman;
import org.example.automation.utils.CSVFileReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;


public class DZ_12_Tests {

    private enum TestType {
        FIRST_NAME_SETTER_AND_GETTER,
        LAST_NAME_SETTER_AND_GETTER,
        RETIRED,
        AGE_SETTER_AND_GETTER,
        REGISTER_PARTNERSHIP,
        DEREGISTER_PARTNERSHIP,
    }

    static public class DataTestManWoman extends CsvToBean {
        @CsvBindByName(column = "test_type")
        private String testType;

        @CsvBindByName(column = "man_first_name")
        private String manFirstName;

        @CsvBindByName(column = "man_last_name")
        private String manLastName;

        @CsvBindByName(column = "man_age")
        private Integer manAge;

        @CsvBindByName(column = "woman_first_name")
        private String womanFirstName;

        @CsvBindByName(column = "woman_last_name")
        private String womanLastName;

        @CsvBindByName(column = "woman_age")
        private Integer womanAge;

        @CsvBindByName(column = "man_expected_value")
        private String manExpectedValue;
        @CsvBindByName(column = "woman_expected_value")
        private String womanExpectedValue;
    }


    @DataProvider
    public Object[][] parseData() {
        List<DataTestManWoman> list = CSVFileReader
                .csvToDataProvider(DataTestManWoman.class, "./src/test/resources/ManWoman.csv");
        return list.stream()
                .map(item -> new Object[]{
                        TestType.valueOf(item.testType),
                        item.manFirstName,
                        item.manLastName,
                        item.manAge,
                        item.womanFirstName,
                        item.womanLastName,
                        item.womanAge,
                        item.manExpectedValue,
                        item.womanExpectedValue
                })
                .toArray(Object[][]::new);
    }

    @Test(testName = "Test man and woman", dataProvider = "parseData")
    public void testClassGetters(
            TestType testType,
            String manFirstName,
            String manLastName,
            Integer manAge,
            String womanFirstName,
            String womanLastName,
            Integer womanAge,
            String manExpectedValue,
            String womanExpectedValue
    ) {
        Man testMan = new Man(manFirstName, manLastName, manAge);
        Woman testWoman = new Woman(womanFirstName, womanLastName, womanAge);

        if (testType == TestType.FIRST_NAME_SETTER_AND_GETTER) {
            Assert.assertEquals(testMan.getFirstName(), manExpectedValue, "First name is not as expected");
            Assert.assertEquals(testWoman.getFirstName(), womanExpectedValue, "First name is not as expected");
        }else if(testType == TestType.LAST_NAME_SETTER_AND_GETTER) {
            Assert.assertEquals(testMan.getLastName(), manExpectedValue, "Last name is not as expected");
            Assert.assertEquals(testWoman.getLastName(), womanExpectedValue, "Last name is not as expected");
        }else if(testType == TestType.AGE_SETTER_AND_GETTER){
            Assert.assertEquals(testMan.getAge().toString(),  manExpectedValue, "Age is not as expected");
            Assert.assertEquals(testWoman.getAge().toString(), womanExpectedValue, "Age is not as expected");
        }else if(testType == TestType.RETIRED) {
            Assert.assertEquals(
                    String.valueOf(testMan.isRetired()),
                    manExpectedValue,
                    "Man shouldn't be Retired"
            );
            Assert.assertEquals(
                    String.valueOf(testWoman.isRetired()),
                    womanExpectedValue,
                    "Woman shouldn't be Retired"
            );
        }else if(testType == TestType.REGISTER_PARTNERSHIP) {
            testMan.registerPartnership(testWoman);
            Assert.assertEquals(
                    testWoman.getLastName(),
                    womanExpectedValue,
                    "Woman gets mans last name by register partnership"
            );
        }else if (testType == TestType.DEREGISTER_PARTNERSHIP) {
            Assert.assertEquals(
                    testWoman.getMaidenName(),
                    womanExpectedValue,
                    "Woman last name returned to maiden name by deregister partnership");
        }

    }

}


