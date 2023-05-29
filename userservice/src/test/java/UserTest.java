import com.dw.UserApplication;
import com.dw.dao.UserMapper;
import com.dw.domain.User;
import com.dw.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserTest {

    Logger log = LoggerFactory.getLogger(UserTest.class);
    @Autowired
    private UserService userService;

    @Value("${spring.pool.coreSize}")
    private String coreSize;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

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

    @Test
    public void testPro(){
        System.out.println(coreSize);

    }

    @Test
    public void testCache(){

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User userById = mapper.getUserById("1");
        log.info("1");
        User userById2 = mapper.getUserById("1");
        log.info("2");
        Assert.assertNotEquals(userById, userById2);
        log.info("3");
//        System.out.println(sqlSession);
    }
}
