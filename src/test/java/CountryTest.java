import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static junit.framework.TestCase.assertEquals;

public class CountryTest {

      /*
        Given
          User connects to the database
          (Kullanıcı veritabanına bağlanır)

        When
          User sends the query to get the region ids from "countries" table
          (Kullanıcı, 'countries' table'dan 'region id' almak üzere query gönderir )

        Then
          Assert that the number of region ids greater than 1 is 17.
          (1'den büyük region id'lerin sayısının 17 olduğunu doğrula )

        And
          User closes the connection
        */


    //select * from countries where region_id > 1
    //select count (region_id) from countries where region_id > 1

  //1.yol : record(satır) sayısını sql query ile alarak yapıyoruz
    @Test
    public void countrTest() throws SQLException {
        String sql = "select count (region_id) from countries where region_id > 1 ";
        ResultSet resultSet = JDBCUtils.executeQuery(sql);
        resultSet.next();
            System.out.println(resultSet.getInt(1));
            int numberOfRow = resultSet.getInt(1); // 17

        assertEquals(17,numberOfRow);  //test geçti

        //User closes the connection
        JDBCUtils.closeConnetion();
    }

    /*
    soru:
     Assert that the number of region ids greater than 1 is 17.
          (1'den büyük region id'lerin sayısının 17 olduğunu doğrula )
     */

   //2. yol : record(satır) sayısını list içine alarak yapıyoruz
    @Test
    public void countrTest2() throws SQLException {
        String sql = "select count (region_id) from countries where region_id > 1 ";
        List<Object> list = JDBCUtils.getColumnList("countries", "region_id");
        System.out.println("list = " + list);

        int numberOfRow = 0;
        for (Object w : list ) {
            if ((int) w > 1 ) {  //obje ile > < işaretleri kullanılamadığı için objeyi inte çevirdik
                numberOfRow ++;
            }
        }

        System.out.println("numberOfRow = " + numberOfRow);
        Assert.assertEquals(17, numberOfRow);

        //User closes the connection
        JDBCUtils.closeConnetion();
    }






}
