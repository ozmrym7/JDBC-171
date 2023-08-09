import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException {


        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","060512020");
        Statement statement = connection.createStatement();

       //1. Örnek: companies3 tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        System.out.println("===1.yol====");
       // 1.yol offset ve limit kullanarak
        String query = "select * from companies3 order by number_of_employees  desc offset 1 limit 1 ";
        ResultSet resultSet1 = statement.executeQuery(query);  // şuan burda resultsetin içerisinde  >>   1   101	 GOOGLE	  18000  var
                                                                // resultset tablo şeklinde getiriyor  !! unutmaa





          ResultSetMetaData data = resultSet1.getMetaData();
          System.out.println(data.getColumnName(1) + data.getColumnName(2) + data.getColumnName(3) );
          System.out.println(data.getTableName(2));

         System.out.println(data.getColumnCount());
         System.out.println(data.getColumnClassName(2));

        System.out.println(data.getColumnDisplaySize(2));
        //System.out.println(data.getColumnLabel(3));





        while (resultSet1.next()) {
            System.out.println(resultSet1.getInt(1) + " " + resultSet1.getString(2)+ " " + resultSet1.getInt(3));
        }







//        //2.yol : subquery kullanarak
//        System.out.println("====2.yol=====");
//        String query2 = "select * from companies3 where number_of_employees = (select max(number_of_employees) from companies3 where number_of_employees < (select max(number_of_employees) from companies3))";
//        ResultSet resultSet2 = statement.executeQuery(query);
//        while (resultSet2.next()) {
//            System.out.println(resultSet2.getInt(1) + " " + resultSet2.getString(2)+ " " + resultSet2.getInt(3));
//        }







    }
}
