import com.kunge2013.spring.transaction.Application;
import com.kunge2013.spring.transaction.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testSimulateConnectionHolderIsNull() {
        userService.simulateConnectionHolderIsNull(); // 不在事务中执行
    }
}
