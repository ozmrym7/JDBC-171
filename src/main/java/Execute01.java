import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
        // 1.Adım : Driver a kaydol ma işlemi ==> JDBS 4 sonrası gerekli değil >>> Class.forName() yöntemi, Java programına dışarıdan bir sınıf eklemeyi mümkün kılar.
        Class.forName("org.postgresql.Driver");  // burada başka kütüphaneden data aldığımız için java
                                                          // eğer class ı bulamazsam diye endişeleniyor , yani  ya path (adres) yanlışsa
                                                          // o yüzden exception ile düzeltiyoruz

         */


        //1.Adım : Database bağlanma adımı ==> "connection"
        //DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgress", "postgres","060512020");


        //String url = "jdbc:postgresql://localhost:5432/postgres";



        //getConnection()  un return type "Connection" olduğu için bunu bir Connection sepetine koyuyoruz bu bize bir connection datası dönecek
        Connection connection =  DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","060512020");   // burada host ismine ,database ismine kullanıcı adı şifre  ismine ihtiyacımız var
        //url kısmı sıralaması ==>  jdbc:postgresql:// + "host ismi: + port no/ + database ismi                                                        // biz burda local database bağlanacağız onun için bu bilgilere ihtiyacım var postgresql i açıyoruz
        //url kısmına bu yazılıyor burası fix --> jdbc:postgresql




        //2.Adım : Statement oluşturma
        //connection objesini kullanarak statement oluşturuyoruz
        //connection.createStatement();
        //createStatement() metodu bize bir "Statement"  döndüğü için onu Statement sepetine koymamız gerek
        Statement statement = connection.createStatement();


        //3.Adım : query çalıştırma --> resultSet
        //statement.execute();   //===> execute() bize boolean return eder , eğer biz data çağırıyorsak true , data çağırmıyorsak false döner
                                 // yani biz bir table oluştururken data çağırıyormuyuz hayır o zaman false döner
                                 // eğer "select" komutu kullanırsam true döner çünkü data çağırıyorum
                                 // özet >> execute() metodu içindeki query çalıştırır true veya false döner


        //1.Örnek :  workers adında bir table oluşturunuz
        //statement.execute("CREATE TABLE workers (worker_id varchar(20), worker_name varchar(20), worker_salary int)"); // execute komutu sayesinde table oluşturuyoruz

        // execute () metodu true veya false döneceği için bunu bir boolean seperine koyarsak ==>



        boolean sql1 = statement.execute("CREATE TABLE workers (worker_id varchar(20), worker_name varchar(20), worker_salary int)"); // bu bize "false" döner çünkü data çağırmıyoruz
        //execute () metodu parentez içerisinde belirtilen String sql komutunu databese de uygular

        System.out.println("sql1 = " + sql1);  //false

        /*
        - execute() metodu DDL (create, drop , alter, truncate) ile kullanıldığında data dönmeyeceği için herzaman "false" döner
        - execute() metodu DQL (Select) ile kullanıldığında data çağırırsa "true" çağıramazsa "false" döner
         */


        //2.örnek : workers table ina worker_adress adında bir sütun ekleyiniz
        //statement.execute("alter table workers add worker_adress  varchar (100)"); // execute komutu sayesinde table oluşturuyoruz
        boolean sql2 = statement.execute("alter table workers add worker_adress  varchar (100)");
        System.out.println("sql2 = " + sql2); // false ,  çünkü bir data çağırmadık



        //3.örnek : workers table sinı siliniz
        //statement.execute("drop table workers");
        boolean sql3 = statement.execute("drop table workers");
        System.out.println("sql3 = " + sql3);


        // 4.Adım :  bağlantıyı kapat
        connection.close(); // bağlantı açık kaldığı zaman gereksiz veri sevkiyatı , databasei gereksiz  kullanma gibi şeyler olabilir
        statement.close();





    }
}
