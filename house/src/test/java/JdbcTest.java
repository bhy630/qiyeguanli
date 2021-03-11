import com.donkee.house.util.JDBCUtil2;
import org.junit.Test;

import java.sql.Connection;

public class JdbcTest {
    @Test
    public void util1(){
        Connection conn = new JDBCUtil2().getConn();
        System.out.println(conn);
    }
}
