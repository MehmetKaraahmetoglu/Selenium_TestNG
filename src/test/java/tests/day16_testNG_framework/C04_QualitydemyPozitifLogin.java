package tests.day16_testNG_framework;

import org.testng.annotations.Test;
import pages.QualitydemyPage;
import utilities.Driver;
import utilities.ReusableMethods;

public class C04_QualitydemyPozitifLogin {
    @Test
    public void pozitifLoginTesti(){
        //qdGecerliUsername = anevzatcelik@gmail.com
        //qdGecerliPassword = Nevzat152032

        //1- https://www.qualitydemy.com/ anasayfasina gidin
        Driver.getDriver().get("https://www.qualitydemy.com/");

        //2- login linkine basin
        QualitydemyPage qualitydemyPage=new QualitydemyPage();
        qualitydemyPage.ilkLoginLinki.click();

        // 3- Kullanici email’i olarak valid email girin
        qualitydemyPage.emailKutusu.sendKeys("anevzatcelik@gmail.com");

        // 4- Kullanici sifresi olarak valid sifre girin
        qualitydemyPage.passwordKutusu.sendKeys("Nevzat152032");
        ReusableMethods.bekle(2);

        // 5- Login butonuna basarak login olun
        qualitydemyPage.acceptCookies.click();
        ReusableMethods.bekle(2);

        qualitydemyPage.loginButonu.click();

        // 6- Basarili olarak giris yapilabildigini test edin
    }
}
