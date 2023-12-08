package org.jeecg.modules.com.bigScreen.Domain;

import lombok.Data;

@Data
public class MapDomain {

    private String province;

    private String isp;

    private String count;

    public MapDomain(String province, String isp, String count) {
        this.province = province;
        this.isp = isp;
        this.count = count;
    }
}
