
// here we are learning connection with using JDBC
// HERE ARE SOME STEPS WE HAVE TO DO WHILE CONNECTIVITY.
// 1. REGISTER THE DRIVER
// 2. CRETAE THE CONNECTION
// 3. CRETAE SATETEMENT
// 4. EXECUTE QUERY
// 5. CLOSE CONNECTION

package atm.system;
import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    public Conn(){
    
        try{
            
//            Class.forName(com.mysql.cj.jdbc.Driver);

            c = DriverManager.getConnection("jdbc:mysql:///atmsystem","root" ,"1234");
            s=c.createStatement();
        }catch(Exception e){
        
           System.out.println(e);
        }
        
    }
    
}
