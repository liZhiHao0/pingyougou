package com.pinyougou.page.service.impl;

import com.pinyougou.page.service.ItemPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class PageDeleteListener implements MessageListener {
    @Autowired
    private ItemPageService itemPageService;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage= (ObjectMessage) message;
        try {
            Long goodsId[]= (Long[])objectMessage.getObject();
            boolean b = itemPageService.deleteItemHtml(goodsId);
            System.out.println("删除结果"+b);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
