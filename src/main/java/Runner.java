import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    //==> DRY code  ==> Dont repeat Yourself  ==> kodumuzun tekrarlı olmaması gerek
    //==> WET code  ==> Write Everything Twice (herşeyi iki kez yaz) )==> aynı şeyi tekrarlaması


    public static void main(String[] args) throws SQLException {


         // JDBCUtils.connectionToDatabase();  ===>   eğer bu şekilde connection kullanmadan statement kullanırsak "null pointer exception" alırız
                                                     //çünkü utils classta connection veriablsesini assigment ediyoruz
                                                     //eğer bunu devre dışı bırakırsak "private static Connection connection" değeri null olduğu için herhangi biri değer ataması yapılmıyor ona
                                                     // yani null bir data üzerinde işlem yapıldığı için

//        JDBCUtils.createStatement();

//        JDBCUtils.createStatement().execute("select * from companies ");  // bu şekilde her defasında connection metodunu ayrı olarak çağırmaktan kurtuluyoruz
//        JDBCUtils.createStatement().execute("select * from companies ");  ---> artık bunada gerek kalmadı

        //execute methodu çalıştırdık
        JDBCUtils.execute("select * from companies"); // sadece execute methodu sayesinde diğer metodlarıda çağırmış oluyoruz böylece
        System.out.println(JDBCUtils.execute("select * from companies"));  // true

        //companies methodunun tüm sutunlarını yazdırdık
        //System.out.println(JDBCUtils.createStatement().executeQuery("")); --> artık böyle yapmıyoruz
        JDBCUtils.executeQuery("select * from companies"); //---> bu bize bir resultset dönüyor bunu resultset içine koyabiliriz
        ResultSet resultSet = JDBCUtils.executeQuery("select * from companies"); //---> bu bize bir resultset dönüyor bunu resultset içine koyabiliriz

        //List <String> list = new ArrayList<>();  //==> bu şekilde bir list oluşturup  aldığımız verileri bir listin içerisine ekleyebiliriz
        while (resultSet.next()) {
          //  list.add(resultSet.getString(2));
            System.out.println(resultSet.getString(2)); // bu şekilde index kullanmadan ziyade tablo adı yazmak daha doğru
        }


        //company sütununun değerlerini bir list içerisine aldık sonrada yazdırdık
        //JDBCUtils.getColumnList("companies" , "number_of_employees");
        System.out.println(JDBCUtils.getColumnList("companies", "number_of_employees"));


        //buda bağlantıyı kapatıyor
        JDBCUtils.closeConnetion();









        /*

        null pointer exceprtion örnek
        String str = null
        str.contains("a");
         */

    }
}
