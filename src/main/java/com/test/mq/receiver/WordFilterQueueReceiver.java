package com.test.mq.receiver;

import net.sf.json.JSONObject;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Doublestar on 2017/12/28 16:13).
 */
@Component("wordFilterQueueReceiver")
public class WordFilterQueueReceiver implements MessageListener {

    @Autowired
    protected MongoTemplate mongoTemplate;
    //日志记录
    private static Logger logger = Logger.getLogger(WordFilterQueueReceiver.class);

    @Override
    public void onMessage(Message message) {
        String words = "";
        try {
            //类型区分
            if (message instanceof ActiveMQBytesMessage) {
                words = ((ActiveMQBytesMessage)message).getContent().toString();
            }else{
                words = ((TextMessage)message).getText();
            }
            //获取日期，进行日志和数据库记录
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(d);
            //日志
            logger.error(date + "---弹幕或聊天句中检测到敏感词:"+words);
            //记录到mongoDB数据库
            mongoTemplate.insert(JSONObject.fromObject("{date:\""+ date +"\",words:\"" + words + "\"}"));
            message.acknowledge();//手动向broker确认接收成功，如果发生异常，就不返回ack
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
            logger.error("mongoDB数据库插入失败");
        }
    }

}
