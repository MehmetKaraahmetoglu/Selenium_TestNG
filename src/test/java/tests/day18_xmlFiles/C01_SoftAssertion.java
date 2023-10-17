package tests.day18_xmlFiles;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ZeroAppPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C01_SoftAssertion {
    @Test
    public void zeroAppTesti() {
        //. “http://zero.webappsecurity.com/” Adresine gidin
        Driver.getDriver().get(ConfigReader.getProperty("zeroAppUrl"));

        // 2. Sign in butonuna basin
        ZeroAppPage zeroAppPage = new ZeroAppPage();
        zeroAppPage.ilkSignInButonu.click();

        // 3. Login kutusuna “username” yazin
        zeroAppPage.loginKutusu.sendKeys(ConfigReader.getProperty("zeroUserName"));

        // 4. Password kutusuna “password” yazin
        zeroAppPage.passwordKutusu.sendKeys(ConfigReader.getProperty("zeroPassword"));

        // 5. Sign in tusuna basin ve sonra bir kere back aypinn(sayfa acilmiyor yoksa)
        zeroAppPage.signInButonu.click();
        Driver.getDriver().navigate().back();

        // 6. Online banking menusu icinde Pay Bills sayfasina gidin
        zeroAppPage.onlineBankingLinki.click();
        zeroAppPage.payBillsLinki.click();

        // 7. “Purchase Foreign Currency” tusuna basin
        zeroAppPage.purchaseFCurrencyElementi.click();

        // 8. “Currency” drop down menusunden Eurozone’u secin
        Select select = new Select(zeroAppPage.pcCurrencyDropDown);
        select.selectByVisibleText("Eurozone (euro)");

        // 9. soft assert kullanarak "Eurozone (euro)" secildigini test edin
        SoftAssert softAssert = new SoftAssert();
        String actualSecilmisOpsiyon = select.getFirstSelectedOption().getText(); //ilk secileni getirdi
        String expectedSecilecekOpsiyon = "Eurozone (euro)";

        softAssert.assertEquals(actualSecilmisOpsiyon, expectedSecilecekOpsiyon, "secilen opsiyon Eurozone degil");

        // 10. soft assert kullanarak DropDown listesinin su secenekleri oldugunu test edin "Select One", "Australia (dollar)", "Canada (dollar)","Switzerland (franc)","China (yuan)","Denmark (krone)","Eurozone (euro)","Great Britain (pound)","Hong Kong (dollar)","Japan (yen)","Mexico (peso)","Norway (krone)","New Zealand (dollar)","Sweden (krona)","Singapore (dollar)","Thailand (baht)"
        String[] dropdownElementleriArr = {"Select One", "Australia (dollar)", "Canada (dollar)", "Switzerland (franc)", "China (yuan)", "Denmark (krone)", "Eurozone (euro)", "Great Britain (pound)", "Hong Kong (dollar)", "Japan (yen)", "Mexico (peso)", "Norway (krone)", "New Zealand (dollar)", "Sweden (krona)", "Singapore (dollar)", "Thailand (baht)"};
        List<String> expectedDropdownElementleriList = Arrays.asList(dropdownElementleriArr);

        List<WebElement> dropdownElementleriList = select.getOptions(); //WebElementlerden olusan bir liste
        List<String> actualdropdownOpsiyonlariList = ReusableMethods.stringListeCevir(dropdownElementleriList);

        /*
        for (WebElement each : dropdownElementleriList
        ) {
            actualdropdownOpsiyonlariList.add(each.getText());
        }

         */


        softAssert.assertEquals(actualdropdownOpsiyonlariList, expectedDropdownElementleriList, "dropdown opsiyonlari istenen sekilde degil");


        softAssert.assertAll(); //soft assert kullanildiginda bu yapilmak zorunda

        Driver.closeDriver();
    }
}
