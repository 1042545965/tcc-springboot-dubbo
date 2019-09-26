import com.alibaba.fastjson.JSON;
import com.tcc.redis.CacheProviderApplication;
import com.tcc.redis.config.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheProviderApplication.class)
public class RedisCacheTest {

    @Autowired
    private RedisUtil redisUtil;
    @Value("${apolloTest}")
    private String testApolloValue;


    @Test
    public void testRedisTransaction() {
        Jedis redis = redisUtil.getRedis();
        Transaction multi = redis.multi();
        try {
            Map multiMap = new HashMap();
            multiMap.put("java", "spring");
            multiMap.put("jdk", "mybatis");
            multi.set("multi1", JSON.toJSONString(multiMap));
            multiMap.put("mysql", "mybatisPlus");
            multi.set("multi2", JSON.toJSONString(multiMap));
            //这里引发了异常，用0作为被除数
//            int i = 100 / 0;
            //没有问题提交事务 ， 利用了redis的原子性 ， 全部成功全部失败
            multi.exec();
        } catch (Exception e) {
            e.printStackTrace();
            //如果出现异常，回滚
            multi.discard();
        } finally {
            redis.close();
        }

    }

    @Test
    public void testRedisGetMulti() {
        Jedis redis = redisUtil.getRedis();
        String multi1 = redis.get("multi1");
        System.out.println(multi1);
    }


    @Test
    public void testApolloValue() {
        System.out.println("testApolloValue ==>"+testApolloValue);
    }

}