import java.lang.reflect.Type;
import java.sql.*;

public class CallableStatement01 {

    public static void main(String[] args) throws SQLException {
        //CallableStatement ı function çağırırken kullanıyoruz







        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","060512020");
        Statement statement = connection.createStatement();

        //1.örnek : selamlama yapan bir function oluşturup çalıştırınız
        //CallableStatement adımları :
        //1. Adım function kodunu yazınız
        String sql = "create or replace function selamlama (x text) returns text as $$ begin return 'Merhaba' || x || ', Nasılsın ?'; end; $$ language plpgsql; ";

        //2.Adım function kodunu çalıştır
         statement.execute(sql);

        //3.Adım: Function u çağır ---> bu normal çağırma yöntemi biz birde CallableStatement kullanarak çağıracağız
//        String sqlFunction = "select  selamlama(' Ali')";
//        ResultSet resultSet = statement.executeQuery(sqlFunction);
//        resultSet.next();
//        System.out.println(resultSet.getString(1)); // getString yerine getObject de denebilir



        //2.örnek : selamlama yapan bir function oluşturup CallableStatement ile çalıştırınız
        CallableStatement callableStatement = connection.prepareCall("{? = call selamlama(?)}"); //prepareCall ile parametrelendirme yapıyoruz
                                                                        //burda 1. soru işareti return typte 2. si
        //soru işaretleri yerine atama yap
        callableStatement.registerOutParameter(1, Types.VARCHAR);  // ---> burası yani 1. soru işareti text kısmı ama javada onu varchar olarak alıyoruz
                                                                                //registerOutParameter --> parametre dışı demek

        callableStatement.setString(2,"Ali");  // ---> burası yani 2. soru işareti de paremetre kısmı

        //5. Adım callableStatement ı çalıştır
        callableStatement.execute();

        //6. Adım: Datayı callableStatement dan çağır
        System.out.println(callableStatement.getObject(1)); //MerhabaAli, Nasılsın ?
                                                                        //artık Ali yi değiştirip istediğimizi yazabiliriz otomatik olarak değişir





        //2.örnek : iki parametreyi toplayan  bir function oluşturup CallableStatement ile çağırınız
        String sql2 = "create or replace function toplama (x int, y int) returns numeric as $$ begin return x+y; end; $$ language plpgsql; ";  //int in  sql karşılığı numeric
                                                                                                                                               //int de yazılabilir fakat bazen uyuşmuyormuş
                                                                                                                                               // o yüzden numeric yazdık hepsini kapsıyor
        statement.execute(sql2);

        CallableStatement callableStatement2 = connection.prepareCall("{? = call toplama(?,?)}");
        callableStatement2.registerOutParameter(1, Types.NUMERIC);
        callableStatement2.setInt(2, 6);
        callableStatement2.setInt(3, 4);

        callableStatement2.execute();
        System.out.println(callableStatement2.getObject(1)); //10










    }
}
