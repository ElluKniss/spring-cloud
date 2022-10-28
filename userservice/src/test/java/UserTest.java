import com.dw.UserApplication;
import com.dw.domain.User;
import com.dw.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
//        User user = userService.getById(1);
        User user = userService.getUserById("2");
        System.out.println(user);
    }

    @Test
    public void testAdd(){
        User user = new User();
        user.setId("2");
        user.setAge(21);
        user.setName("戴伟");
        boolean save = userService.save(user);
        System.out.println(save);
    }
}
