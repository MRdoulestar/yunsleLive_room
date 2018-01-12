package com.test.mq.receiver;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 监听消息队列
 */
@Component("queueReceiver1")
public class QueueReceiver1 implements MessageListener{


    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ActiveMQBytesMessage) {
                System.out.println("QueueReceiver1接收到消息:"+((ActiveMQBytesMessage)message).getContent().toString());
            }else{
                System.out.println("QueueReceiver1接收到消息:"+((TextMessage)message).getText());
            }
            message.acknowledge();//手动向broker确认接收成功，如果发生异常，就不反回ack
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}