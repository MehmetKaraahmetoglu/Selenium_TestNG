package tests.day17_testNGFramework_assertions;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class C01_configurationPropertiesKullanimi {
    @Test(groups = {"smoke","e2e1","regression"})
    public void test01(){
        // amazon ana sayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));
        // parantez icin configuration.properties dosyasindan amazonUrl'i
        // bana getirecek birseyler yazmam lazim

        // arama kutusuna aranacak kelimeyi yazdirin ve aratin
        AmazonPage amazonPage=new AmazonPage();
        amazonPage.aramaKutusu.sendKeys(ConfigReader.getProperty("amazonAranacakKelime")+ Keys.ENTER);

        // arama sonuclarinin aranan kelimeyi icerdigini test edin
        String expectedIcerik=ConfigReader.getProperty("amazonAranacakKelime");
        String actualAramaSonucu = amazonPage.sonucYaziElementi.getText();
        Assert.assertTrue(actualAramaSonucu.contains(expectedIcerik));

        //fotografini cekin
        ReusableMethods.webElementFotografCek(amazonPage.sonucYaziElementi,"DinamikTestSayfasi");

        //sayfayi kapatin
        Driver.closeDriver();
    }
}
