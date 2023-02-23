package anhtester.com.projects.website.crm.models;

import lombok.Data;

@Data
public class ItemsDataModel {

    public static int row;

    public static String testCaseName = "TESTCASENAME";

    public static String email = "EMAIL";

    public static String username = "USERNAME";

    public static String country = "COUNTRY";

    public static String item = "ITEM";

    public static String password = "PASSWORD";

    public static String expectedTitle = "EXPECTED_TITLE";

    public static String expectedError = "EXPECTED_ERROR";

    public static String expectedUrl = "EXPECTED_URL";

    public static String language = "Language";

    public static int getRow() {
        return row;
    }


}
