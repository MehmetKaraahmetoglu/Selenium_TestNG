package tests.day15_testNG_framework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class C01_Priority {
    int a = 20;
    int b = 10;

    @Test (groups = {"smoke","e2e1","regression"}) // bu test methodu hangi testlerde calisacaksa böyle seceriz
    public void carpmaTesti() {
        // sayilarin carpiminin 100'den buyuk oldugunu test edin
        Assert.assertTrue(a * b > 100);
    }

    @Test(priority = -22)
    public void toplamaTesti() {
        // sayilarin toplaminin pozitif bir sayi oldugunu test edin

        Assert.assertTrue((a + b) > 0);
    }


    @Test (priority = 53)
    public void cikarmaTesti() {

        // a ile b'nin farkinin mutlak degerinin 100'den kucuk oldugunu test edin

        Assert.assertTrue((a - b) > -100 && (a - b) < 100);
    }
}
/*
 TestNG hicbir mudahale olmazsa
 testleri isim sirasina göre calistirir

 testlerin calisma siralamasini istedigimiz sekilde düzenleyebiliriz

 Testlere verecegimiz priority degerlerinden
 kücükten büyüge dogru calistirir

 priority atamazsak TestNG default olarak priority=0 alir

 Ayni priorty degerine sahip, birden fazla test methodu varsa
 bunlar kendi icerisinde isim sirasina göre calisir
 */
