import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtils {  // bu classta tekrar tekrar kullanacağımız metodlar olacak

    //javada her class bir data tipidir ama her data tipi bir class değildir  !!! ---> her interface her class bir data tipidir
    //biz abc diye bir class da açsak onu data tipi olarak kullanabiliriz ,


    private static Connection connection;
    private static Statement statement;

    //bu method database ile bağlantı kurup connection data döner
//    public static Connection connectionToDatabase() {   //eğer burda throws ile handle edersek bu methodu kullandığımız yerlerde de handle etmemizz gerekecek
//                                                        // o yüzden try catch kullanıyoruz
//        try {
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "060512020");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return connection;
//    }


    public static Connection connectionToDatabase() {   //eğer burda throws ile handle edersek bu methodu kullandığımız yerlerde de handle etmemizz gerekecek
        // o yüzden try catch kullanıyoruz
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }




    //biz herzaman statementten önce connection methodunu çağormak zorunda değiliz

//bu method connectionToDatabase() methodunu içinde çağırarak bir statement objesi oluşturup return yapar
    public static Statement createStatement() {   //eğer burda throws ile handle edersek bu methodu kullandığımız yerlerde de handle etmemizz gerekecek
        try {
           // statement =  connection.createStatement();
            statement =  connectionToDatabase().createStatement();  //burada biz direk connectionToDatabase metodunu çağırırsak connectionu null olmaktan kurtarırız
                                                                    //yani biz createStatement çağırdığımızda ilk önce connection metodu çağrılacak o connection döneceği için onunla statement oluşturabilirim
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return statement;
    }

    //Statement statement =  connection.createStatement();


    //execute()  için bir method oluşturuyoruz  --> bize true veya false return edecek
    //bu method bir sql query i çalıştırıp data dönüyorsa true dönmüyorsa false verir
        public static  boolean execute (String sql){

            try {
                return createStatement().execute(sql);  // true veya false retur eder  hemde bu sayede createStatement() çalışır
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        //ödev: execute update methodu için bir reusble method oluşturunuz


        //bu method bir sql query i çalıştırarak sonucu resultset olarak döner
    public static ResultSet executeQuery (String sql){

        try {
            return createStatement().executeQuery(sql);  // true veya false retur eder  hemde bu sayede createStatement() çalışır
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //bu method istediğimiz bir table nin  istediğimiz bir sütununu list olarak döner
    //listin hangi data tipini döneceğini bilmiorsak obje olması daha mantıklı olursa tüm data tiplerini kabul eder
    public static List <Object> getColumnList ( String tableName , String columName ) throws SQLException {  //şu tablodaki şu sütunu bana ver diyoruz
        List <Object> list = new ArrayList<>();
        ResultSet resultSet = executeQuery("select " + columName + " from "  + tableName );

        while (resultSet.next()) {
            list.add(resultSet.getObject(1));
        }

        return  list;
    }


    //bu method bağlantıyı kapatır
    public static void closeConnetion(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }






}














