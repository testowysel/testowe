package TestCases;

import org.testng.annotations.Test;

public class TC extends BasicTest {

    public TC() {
    }

    @Test(dataProvider = "inputFile")
    public void Test1(
            String login,
            String pwd

    ) {
        log.loginProcess(login, pwd)
                .meth1();

    }
}
