package org.jeecg.modules.com.bigScreen.Domain;

import lombok.Data;

@Data
public class ChannelLineDomain {

    private String count;

    private String shortSms;

    public ChannelLineDomain(String count,String shortSms) {
        this.count = count;
        this.shortSms = shortSms;
    }
}
