/*
 * Copyright (c) 2022. Anh Tester
 * Automation Framework Selenium
 */

package anhtester.com.dataprovider;

import anhtester.com.constants.FrameworkConstants;
import anhtester.com.helpers.ExcelHelpers;
import anhtester.com.helpers.Helpers;
import anhtester.com.helpers.PropertiesHelpers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;
import java.util.Iterator;

public final class DataProviderManager {

    private DataProviderManager() {
        super();
        PropertiesHelpers.loadAllFiles();
    }


    @DataProvider(name = "getItemsDataHashTable")
    public static Object[][] getItemsData() {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        Object[][] data = excelHelpers.getDataHashTable(Helpers.getCurrentDir() + FrameworkConstants.EXCEL_DATA_FILE_PATH, "SignIn", 1, 2);
        System.out.println("getItemsData: " + data);
        return data;
    }

}
