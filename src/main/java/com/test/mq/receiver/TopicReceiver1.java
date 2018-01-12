package com.test.mq.receiver;

import org.apache.activemq.command.ActiveMQBytesMessage;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 *
 * @author liang
 * @description  Topic消息监听器
 *
 */
@Component("topicReceiver1")
public class TopicReceiver1 implements MessageListener{


    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ActiveMQBytesMessage) {
                System.out.println("聊天频道接收到消息:"+((ActiveMQBytesMessage)message).getContent().toString());
            }else{
                System.out.println("聊天频道接收到消息:"+((TextMessage)message).getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}