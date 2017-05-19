package com.gs.thread;

import com.gs.common.message.IndustrySMS;

import java.util.List;

/**
 * Created by Administrator on 2017-05-19. 发送短信的线程
 */
public class SendMessageThread implements Runnable  {

    private List<String> phones; // 要发送的手机号

    private String content; // 短信的内容

    public SendMessageThread(List<String> phones, String content) {
        this.phones = phones;
        this.content = content;
    }

    @Override
    public void run() {
        int len = phones.size();
        for (int i = 0; i < len; i++) {
            String to = phones.get(i);
            String smsContent = content;
            IndustrySMS is = new IndustrySMS(to, smsContent);
            is.execute();
        }
    }
}
