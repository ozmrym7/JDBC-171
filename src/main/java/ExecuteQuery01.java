import java.sql.*;
import java.util.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","060512020");
        Statement statement = connection.createStatement();

        //DB Navigator eklentisi sayesinde artık pgAdmini açmamıza gerek kalmıyor, pgAdminin yaptığı işleri artık burda yapıyoruz ama burda yapılan işlemler
        //bizim java classlarımızdan bağımsız bu ayrı bir işlem farklı bir app çalışıyormuş gibi , database ekleme çıkarma vb silme  işlemleri artık burdan yapabileceğiz
        // ==> main nin yanındaki kısmı puplic seçmemiz gerek tabloyu görmek için

        //1. Örnek:  region id'si 1 olan satırları  çağırın.
        System.out.println("\n======================================== örnek 1 ========================================================================\n");
        String query = "select * from countries  where region_id = 1";
        boolean r1 = statement.execute(query);
        System.out.println(r1); //true döner , çünkü select komutu ile data çağırdık



        //ama biz bu datayı örmek istiyoruz  o yüzden ==> executeQuery() kullanmamız gerek
        // not : SQL query ile çağrılan datayı görebilmek için executeQuery() methodu kullanmalıyoz,

        /*
        executeQuery() metodu çağrılan datayı ResultSet datası olarak döner
        default olarak ResultSet bir pointer ile döner ve bu pointer sütun isimlerini gösterir
        datayı okuyabilmek için next() metodu ile pointeri sıradaki satıra taşımalıyız
        --> next() methodu pointerdan sonra  veya sırada satır varsa "true" döner yoksa "false" döner ve pointeri bir sonraki satıra taşır
        */


        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next(); //pointeri bir sonraki satıra atar
        System.out.println(resultSet.getString("country_name")); //Belgium
        System.out.println(resultSet.getString("country_id")); //BE
        System.out.println(resultSet.getString("region_id")); //1
        System.out.println(resultSet.getString(1)); //BE
        System.out.println(resultSet.getString(3)); //1

        while (resultSet.next()) {

            String country_id = resultSet.getString("country_id");
            String country_name = resultSet.getString("country_name");
            int region_id = resultSet.getInt("region_id");

            System.out.println("country_id: " + country_id + "|| country_name: " + country_name+ " ||  region_id: " + region_id);
        }


        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 = "select country_id , country_name from countries where region_id > 2 ";
        ResultSet resultSet2 = statement.executeQuery(sql2);


        System.out.println("\n======================================== örnek 2 ========================================================================\n");
        while (resultSet2.next()) {
                System.out.println(resultSet2.getString(1) +"|| "+ resultSet2.getString(2));
        }


        System.out.println("\n======================================== örnek 3 ========================================================================\n");
        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 = "select  * from companies3 where  number_of_employees = ( select  min(number_of_employees)  from companies3 )";
        ResultSet resultSet3 = statement.executeQuery(sql3);
        resultSet3.next();
        System.out.println(resultSet3.getString(1) + " " + resultSet3.getString(2) +" " + resultSet3.getString(3));







//        while (tablo.next()) {
//            int company_id = tablo.getInt("company_id");
//            String company = tablo.getString("company");
//            int number_of_employees = tablo.getInt("number_of_employees");
//
//            System.out.println("ID: " + company_id + ", company: " + company+ ", number_of_employees: " + number_of_employees);
//        }



        connection.close();
        statement.close();

    }



}
