public class Test01 {

    public static void main(String[] args) {

        //test beklene data ve asıl datanın karşılaştırılmasıdır

        String expectedData = "Hello";   //expectedData (beklenen)

        String actualData = "Hellox";   //actualData (gerçek data)

        if (expectedData.equalsIgnoreCase(actualData)) {
            System.out.println("Test PASSED");

        } else  {
            System.out.println("Test FAILED");
        }


        // otomasyon testi yapmak için bir "Test Framework üne ihtiyacımız var (JUnit, Test NG , Cucumber..)

    }
}
