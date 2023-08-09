import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;

public class JUnitTest01 {  //tüm classı çalıştırmak istiyorsak buradan run ediyoruz

  //bundan sonra main method açmıyoruz

    //bu test annotationunu jUnit ten almamız gerekiyor
    @Test //====> test methodları public ve void olmalıdır parametresi olmaz ==> bu test annotationu sayesinde run butonu geliyor
    public  void  test1() {
        //test geçti veya kaldı gibi dönüt alabilmek için  doğrulama (assertion) yapmamız gerekir
            //Assertion türleri:
        String expectedData = "Hello";   //expectedData (beklenen)
        String actualData = "Hello";   //actualData (gerçek data)

           //Assert.assertEquals(1,1);   //===> assertArrayEquals() metodunda birinci parametre ile ikinci parametre eşitse test geçer değilse kalır
                                                      //test geçti
            assertEquals(1,1); //statik method olduğu için class ismi yazmaya gerek yok import edebiliyoruz
            assertEquals(expectedData,actualData);   //statik method olduğu için class ismi yazmaya gerek yok import edebiliyoruz

            assertTrue(actualData.contains("lo"));  //assertTrue() içerisindeki değer boolean yani true veya false ise ona göre geçip kalıyor true ise geçiyor
            //expectedData sı "lo" içeriyormu

            assertTrue(actualData.length() >1);

            //Negatif test için assertFalse  ===> false dönmesini istediğim durumlarda bunu kullanıyoruz ---> parentez içi false olursa test geçer
            assertFalse(actualData.contains("x"));

            assertNotEquals(1,2); // ==>  assertNotEquals() birbirine eşit olmayan değerler için test geçer  --> parantez içi parametreleri eşit değilse test geçer



    }

    @Test
    public  void  test2() {


    }


    @Test
    public  void  test3() {


    }







}
