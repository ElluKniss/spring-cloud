import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dw.UserApplication;
import com.dw.dao.UniboxMapper;
import com.dw.domain.UniboxDevice;
import com.dw.domain.User;
import com.dw.service.UserService;
import com.dw.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class UserTest {

    @Autowired
    private UserServiceImpl userService;

    @Resource
    private UniboxMapper uniboxMapper;

    @Resource
    private JedisPool jedisPool;

    @Resource
    private ThreadPoolTaskExecutor threadPool;

    @Test
    public void test(){
//        User user = userService.getUserById("0");
        User user = userService.getById("3");
        System.out.println(user);
    }

    @Test
    public void testRedis(){
        Jedis resource = jedisPool.getResource();
        resource.set("name_d", "21");
        System.out.println(resource.get("name_d"));
    }

    @Test
    public void testAdd(){
        User user = new User();
        user.setId("2");
        user.setPhone("1234567");
        user.setName("戴伟");
        boolean save = userService.save(user);
        System.out.println(save);
    }

    @Test
    public void testunibox(){

        //https://www.cnblogs.com/javagg/p/12654305.html 构造条件
        List<UniboxDevice> re = uniboxMapper.selectList(new LambdaQueryWrapper<UniboxDevice>().
                ge(UniboxDevice::getId, 4).eq(UniboxDevice::getCuei, "2000ZZ020000005"));
        System.out.println(re);

        //新增
        UniboxDevice ud2 = new UniboxDevice();
        ud2.setCuei("12314567890");
        ud2.setSecret("asdfefeqrgffeqwefqwf123");
        ud2.setCreateTime(LocalDate.now().toString());
//        int insert = uniboxMapper.insert(ud2);
//        System.out.println(insert);
        //修改
        UniboxDevice ud = new UniboxDevice();
        ud.setId(6);
        ud.setCreateTime(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int i = uniboxMapper.updateById(ud);
        System.out.println(i);
    }

    public static void main(String[] args) {
        String s = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println(s);
        String s1 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(s1);
        double random = Math.random();
        System.out.println(random*20);
    }

    @Test
    public void testThreadPool() throws InterruptedException {
        userService.asyncQuery();
        System.out.println("main thread");
        TimeUnit.SECONDS.sleep(5);
    }
}
