package TestCases;

import Pages.LoginPage;
import Pages.NextPage;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class BasicTest {

    @BeforeMethod
    public void before() {

        System.setProperty("webdriver.chrome.driver", "src\\test\\Resources\\chromedriver.exe");
        System.setProperty("selenide.browser", "Chrome");
        System.setProperty("chromeoptions.args", "--start-maximized");

        //System.setProperty("webdriver.chrome.driver", "src\\test\\Resources\\IEDriverServer.exe");
        //  System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");

    }

    //obiekty dla każdego Page'a

    LoginPage log = new LoginPage();
    NextPage np = new NextPage();

// kod do obsługi wejsciowego xls

    public String[][] getExcelData(String xlsName, String sheetName) {

        String[][] arrayExcelData = null;
        try {
            FileInputStream fs = new FileInputStream(xlsName);
            WorkbookSettings ws = new WorkbookSettings();
            ws.setEncoding("Cp1252");
            Workbook wb = Workbook.getWorkbook(fs, ws);
            Sheet sh = wb.getSheet(sheetName);

            int totalNoOfCols = sh.getColumns();
            int totalNoOfRows = sh.getRows();

            arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];

            for (int i = 1; i < totalNoOfRows; i++) {

                for (int j = 0; j < totalNoOfCols; j++) {
                    arrayExcelData[i - 1][j] = sh.getCell(j, i).getContents();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return arrayExcelData;


    }

    //----data providers

    @DataProvider
    public Object[][] inputFile() {
        Object[][] arrayObject = getExcelData(this.getClass().getClassLoader().getResource("Inputs.xls").getPath(), "Test");
        return arrayObject;
    }

//kod dla pliku wynikowego
    // file output, possibly will be used later, do not delete
    // FileWriter writer = new FileWriter("C:\\gcm_files\\GCM\\Inputs\\wynik.csv");


}
