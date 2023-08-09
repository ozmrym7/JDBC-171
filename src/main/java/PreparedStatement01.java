import java.sql.*;

public class PreparedStatement01 {

    //PreparedStatement statement ile  CallableStatement işin deteyı ilerde ihtiyacımız olduğu zaman açıp bakabiliriz
    //ExecuteQuery öğrenmemiz bizim için yeterli


    public static void main(String[] args) throws SQLException {
        /*

        PreparedStatement sorguların yürütülmesini hızlandırır,
        SQL enjeksiyonu gibi güvenlik risklerini azaltır ve parametreleri dinamik olarak değiştirerek daha esnek sorgular oluşturmanıza olanak tanır.
        Bu nedenle, özellikle güvenlik ve performans açısından kritik olan veritabanı işlemleri için tercih edilir.
        */

        //aslında sorguyu tekrar yazmıyoruz sadece parametre değiştiriyoruz PreparedStatement sayesinde

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","060512020");
        //Statement statement = connection.createStatement();  ---> PreparedStatement için stetamente ihtiyacımız yok, executequery yaparken ihtiyacımız var
        Statement statement = connection.createStatement();



        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        // Prepared statement oluşturmak için

        //1. Adım :  PreparedStatementquery'sini oluştur
        //String sql1 = "update companies set number_of_employees = 9999 where company = 'IBM'";  >>> buradaki değerlerin yerine soru işareti koyuyoruz
         String sql1 = "update companies set number_of_employees = ? where company = ? ";  //---> ? işareti parametrelendirme yapıyor

        //2.Adım: PreparedStatement objesi oluştur ----> burda connection kullanarak oluşturuyoruz artık statement e gerek kalmıyor
        PreparedStatement  preparedStatement = connection.prepareStatement(sql1);

        //3.Adım : Soru işaretleri yerine atamalar yapıyoruz
        preparedStatement.setInt(1,9999);  // 1. parametreye 9999 geldi  yani 1. soru işareti yerine
        preparedStatement.setString(2,"IBM");  //2. paremetreye 'IBM' geldi

        //4.Adım : Query i çalıştır
        int guncellenenSAtırSayısı = preparedStatement.executeUpdate();  //uptade işlemi yaptığımız için executeUpdate() kullanmak daha mantıklı
        System.out.println("güncellenen satır sayısı = " + guncellenenSAtırSayısı);  //bu bize kaç tane satırda update olduğunu veya güncelleme olduğunu  dönecek yani int dönecek

        //güncellenen table yi okumak için statement ile executeQuery kullanıyoruz
        String sql2 = "select * from companies";
        ResultSet result = statement.executeQuery(sql2);

        while (result.next()) {  // bu loop sayesinde tüm tabloyu okuduk ekrana yazdırdık
            System.out.println(result.getObject(1) +" "+ result.getObject(2)+ " " +result.getObject(3)); //getObject() ile tüm data tiplerini çağırabiliyoruz eğer işlem yapmayacaksak getObject() kullanabiliriz, objelerle matematiksel işlem yapamıyoruz
            //getObject() metodu, ResultSet nesnesinin bir sütundaki değeri almak için kullanılır.
            // Bu metot, JDBC sürücüsü tarafından sütunun veri türüne uygun bir Java nesnesi olarak geri döndürülür.
            // Yani, veritabanında tutulan değeri, uygun Java veri türüne dönüştürmek için bu metodu kullanabilirsiniz.
        }

        System.out.println("=======================================================================");

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5000 olarak güncelleyin.
        //String sql3 = "update companies set number_of_employees = ? where number_of_employees = ? ";  >>> bunları tekrardan oluşturmaya gerek yok birkere oluşturup parametre değiştiriyoruz
        //PreparedStatement preparedStatement2 =  connection.prepareStatement(sql3);
        preparedStatement.setInt(1, 5000);
        preparedStatement.setString(2, "GOOGLE");

        int guncellenenSAtırSayısı2 = preparedStatement.executeUpdate();
        System.out.println("güncellenen satır sayısı = " + guncellenenSAtırSayısı2);


        //güncellenmiş tabloyu okuyalım
        ResultSet resultSet2 = statement.executeQuery(sql2); // ---> burası sadece databaseden datayı okumak için preparedStatement ile alakası yok

       // resultSet2 = statement.executeQuery(sql2); -->  isterse bu şekilde tekrar resultset oluşturmadan da kullanabiliriz
        //                                                 result setimiz var yukarda aynısını kulanarak tekrar assigmnet ederek kullanıyoruz

        while (resultSet2.next()) {  // bu loop sayesinde tüm tabloyu okuduk ekrana yazdırdık
            System.out.println(resultSet2.getObject(1) +" "+ resultSet2.getObject(2)+ " " +resultSet2.getObject(3));
        }
















    }
}
