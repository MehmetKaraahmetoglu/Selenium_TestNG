package tests.day17_testNGFramework_assertions;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C05_SoftAssert {
    /*
     TestNG assertion konusunda da bize bir alternatif sunar.

     TestNG iki farkli Assertion class'ina sahiptir.
     1- Assert
        Bu JUnit'de ki assertion ile birebir aynidir.
        sadece isimlendirirkendiger alternatif softAssert oldugundan
        Assert class'ina da HARD ASSERT denir.
        hard assert karsilastigi ilk failed'da
        calismayi durdurur, dolayisiyla geriye kalan assertion'larin
        passed veya failed sonuclarindan hangisini alacagini bilemeyiz

     2- Soft Assert
        Soft Assert biz raporla diyene kadar
        yaptigi tum testlerin sonuclarini kendisi tutar
        test passed de olsa, failed de olsa calismaya devam eder.

        Ne zaman raporla dersek tum failed olanlari bize rapor eder
        ve calismayi durdurur.

        Eger hic failed olan yoksa
        class calismaya devam eder.

        A- softAssert objesi olustur
        B- softAssert objesi ile testleri yap, aciklama eklemekte fayda var(message)
        C- softAssert.assertAll() diyerek tum assertionlari raporla
           bu satiri yazmazsak, assertion'lar FAILED olsa bile test PASSED olarak gözükür.
     */
    @Test
    public void softAssertionTesti() {

        //amazon ana sayfaya gidelim
        Driver.getDriver().get(ConfigReader.getProperty("amazonUrl"));

        //url'in amazon icerdigini test edelim
        String expectedIcerik = "Ramazon";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        SoftAssert softAssert=new SoftAssert(); //Assert den farki soft da obje olusturmamiz lazim
        softAssert.assertTrue(actualUrl.contains(expectedIcerik),"URL icerigi amazon icermiyor");


        //aranacak kelimeyi aratalim
        AmazonPage amazonPage = new AmazonPage();
        amazonPage.aramaKutusu.sendKeys(ConfigReader.getProperty("amazonAranacakKelime") + Keys.ENTER);

        //sonuclarin aranacak kelimeyi icerdigini test edelim
        String expectedSonucIcerik = ConfigReader.getProperty("amazonAranacakKelime2");
        String actualSonucYazisi = amazonPage.sonucYaziElementi.getText();

        softAssert.assertTrue(actualSonucYazisi.contains(expectedSonucIcerik),"arama sonuclari aranacak kelimeyi icermiyor");
        //once sart sonra mesaj yazmak gerekli
        // equals'da da ayni kural soz konusu

        //ilk urune tiklayalim
        amazonPage.ilkUrunElementi.click();

        //ilk urun isminde aranacak kelime bulundugunu test edelim
        String expectedUrunIcerik = ConfigReader.getProperty("amazonAranacakKelime2");
        String actualIsim = amazonPage.ilkUrunIsimElementi.getText();

        softAssert.assertTrue(actualIsim.contains(expectedUrunIcerik),"ilk urun isminde aranan kelime yok");


        //raporlama yapiyorum
        softAssert.assertAll(); // ne var yok bana raporla
        System.out.println("Failed olan varsa bu satir calismaz");


        //sayfayi kapatalim
        Driver.closeDriver();
    }

}

