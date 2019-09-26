import com.tcc.constants.IndexNameAndTypeConstant;
import com.tcc.facede.RabbitFacede;
import com.tcc.message.MessageProviderApplication;
import com.tcc.message.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageProviderApplication.class)
public class QueueMessageTest {

    private final Logger logger = LoggerFactory.getLogger(QueueMessageTest.class);

    @Resource
    private RabbitFacede rabbitFacede;

    @Test
    public void testRabbitSendMessage(){
        String message = "我是dkz发送的消息";
        for (int i = 0; i < 10 ; i++) {
            rabbitFacede.sendProducerMessage(message + i , RabbitConfig.QUEUE_A);
        }
    }

    @Test
    public void testRabbitSendMessageToTest(){
        String message = "我是dkz发送的消息";
        rabbitFacede.sendProducerMessage(message , RabbitConfig.QUEUE_A);
    }

}