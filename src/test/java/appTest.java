import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dao.CustomerDao;
import com.test.dao.UserDao;
import com.test.entity.Customer;
import com.test.entity.User;
import com.test.utils.WordUtils;
import com.test.service.IUserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring-redis.xml",
        "classpath:spring-service.xml",
        "classpath:spring-mail.xml",
        "classpath:spring-mongodb.xml"})
public class appTest {
    @Autowired(required = false)
    RedisTemplate redisTemplate;
    @Autowired(required = false)
    UserDao userDao;
    @Autowired(required = false)
    CustomerDao customerDao;
    @Autowired(required = false)
    IUserService userService;
    @Autowired(required = false)
    WordUtils wordUtils;
    @Autowired
    protected MongoTemplate mongoTemplate;

    private static Logger logger = Logger.getLogger(Test.class);
    //Test log4j

    @Test
    public void wordTest() {
        mongoTemplate.insert("testInsert");
    }

    @Test
    public void testLog4j() throws Exception {
        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录warn级别的信息
        logger.info("This is warn message.");
        // 记录error级别的信息
        logger.error("This is error message.");
    }

    @Test
    public void testUserService() throws Exception {
        System.out.println(userService.isLogin("test"));
    }

    @Test
    public void testCustomer() throws Exception {
//        Customer customer = new Customer();
//        customer.setName("test");
//        customer.setProfession("test");
//        customer.setProvince("test");
//        customer.setCorporation("test");
//        customer.setFunds(1000);
//        customer.setUptime(new Date());
//        System.out.println("添加数："+customerDao.insertCustomer(customer));
        List<Customer> changeCustomer = customerDao.findCustomerByName("test");
        ObjectMapper mapper = new ObjectMapper();
        String mapJakcson = mapper.writeValueAsString(changeCustomer);
        System.out.println(mapJakcson);
//        changeCustomer.get(0).setName("change");
//        customer = changeCustomer.get(0);
//        System.out.println("修改数："+customerDao.updateCustomer(customer));
//        System.out.println("删除数："+customer.getId());
    }

    @Test
    public void testFindBySid() throws Exception {
        User user = userDao.findByName("test");
        System.out.println(user);
    }


    @Autowired
    JavaMailSender mailSender;
    @Test
    public void testMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("541794749@qq.com");
        message.setTo("tornetwell@gmail.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }

    @Test
    public void testRedis() throws Exception {
        //stringRedisTemplate的操作
        // String读写
        redisTemplate.delete("test");
        redisTemplate.opsForList().rightPush("test", "user1");
        redisTemplate.opsForList().rightPush("test", "user2");
        redisTemplate.opsForList().rightPush("test", "user3");
        List<String> data = redisTemplate.opsForList().range("test", 0, -1);
        System.out.println(data);
        System.out.println(data.get(0));
        System.out.println("---------------");
        redisTemplate.delete("test");
    }
}