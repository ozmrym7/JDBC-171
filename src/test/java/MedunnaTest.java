import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

public class MedunnaTest {

/*
   Given
     User connects to the database
     (Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))

   When
     User sends the query to get the created room
     (Kullanıcı, oluşturulan odayı getirmek için sorgu gönderir)

   Then
     Assert that room is created properly
     (Odanın düzgün kaydedildiğini doğrular)

   And
     User closes the connection
  */



    @Test
    public void medunnaTest() throws SQLException {
        //User connects to the database

        //User sends the query to get the created room
        String sql = "select * from  room where room_number = 32654654";
        ResultSet resultSet = JDBCUtils.executeQuery(sql);

        // Assert that room is created properly
        resultSet.next();
        String description  = resultSet.getString("description");
        System.out.println(description);
        assertEquals("Database Test İçin Oluşturuldu" , description);

        //close connection
        JDBCUtils.closeConnetion();


    }
}
