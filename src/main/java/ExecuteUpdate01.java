import org.postgresql.Driver;

import java.sql.*;
import java.util.Arrays;

public class ExecuteUpdate01 {


    public static void main(String[] args) throws SQLException {

        // 1.adım connection
       Connection connection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","060512020");
       // 2.adım
       Statement statement =  connection.createStatement();

      //1. Örnek: companies table ından number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.
      //statement.executeUpdate("update companies set number_of_employees = 16000 where  number_of_employees < (select avg (number_of_employees) from companies) ");
      //burda executeUpdate kullanmam gerek çünkü update işlemi yapıyoruz ,  executeUpdate bize int döndürür >> bu bize kaç tane satırda göncelleme olduğunu söyler o yüzden int sepetine koyuyoruz
//      int updateEdilenSatırSayısı =  statement.executeUpdate("update companies set number_of_employees = 16000 where  number_of_employees < (select avg (number_of_employees) from companies) ");
//      System.out.println("updateEdilenSatırSayısı :" + updateEdilenSatırSayısı); //2


      //method parantezlerini mümkün olduğunca sade tutuyoruz o yüzden bu şekilde yazmak daha doğru
        String query = "update companies set number_of_employees = 16000 where  number_of_employees < (select avg (number_of_employees) from companies)";
      int updateEdilenSatırSayısı =  statement.executeUpdate(query);
      System.out.println("updateEdilenSatırSayısı :" + updateEdilenSatırSayısı); //2


      connection.close();
      statement.close();







//      ResultSet tablo = statement.executeQuery("select * from companies");
//        while (tablo.next()) {
//            int company_id = tablo.getInt("company_id");
//            String company = tablo.getString("company");
//            int number_of_employees = tablo.getInt("number_of_employees");
//
//            System.out.println("ID: " + company_id + ", company: " + company+ ", number_of_employees: " + number_of_employees);
//        }




        /*
        "UPDATE companies SET number_of_employees = 16000
         WHERE  number_of_employees < (SELECT AVG (number_of_employees) FROM companies)
         */


    }
}



