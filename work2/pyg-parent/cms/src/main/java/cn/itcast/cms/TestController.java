package cn.itcast.cms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ConnectionFactory;
import java.util.HashMap;

@RestController
public class TestController {

    @Bean
    ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory();
    }
    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPriority(999);
        return jmsTemplate;
    }
    @Bean(value="jmsMessagingTemplate")
    JmsMessagingTemplate jmsMessagingTemplate(JmsTemplate jmsTemplate) {
        JmsMessagingTemplate messagingTemplate = new JmsMessagingTemplate(jmsTemplate);
        return messagingTemplate;
    }

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("send")
    public void sendCode() {
        // 生成6位随机数
        final String code = (long) (Math.random() * 1000000) + "";
        System.out.println("验证码：" + code);
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("mobile", "15138666194");//手机号
        map.put("template_code", "SMS_123672285");//模板编号
        map.put("sign_name", "品优taotao");//签名
        map.put("param", "{number:"+code+"}");
        jmsMessagingTemplate.convertAndSend("sms", map);
    }

}
