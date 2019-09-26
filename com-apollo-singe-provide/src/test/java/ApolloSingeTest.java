import com.tcc.apollo.singe.ApolloSingeProviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApolloSingeProviderApplication.class)
public class ApolloSingeTest {

    @Value("${server.port}")
    private String port;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.driver-type}")
    private String driverType;


    @Test
    public void testApolloValue() {
        System.out.println("port ==>" + port);
        System.out.println("userName ==>" + userName);
        System.out.println("password ==>" + password);
        System.out.println("url ==>" + url);
        System.out.println("driverClassName ==>" + driverClassName);
        System.out.println("driverType ==>" + driverType);


    }

}