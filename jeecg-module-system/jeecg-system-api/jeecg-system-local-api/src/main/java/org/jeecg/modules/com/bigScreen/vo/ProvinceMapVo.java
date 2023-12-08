package org.jeecg.modules.com.bigScreen.vo;

import lombok.Data;

@Data
public class ProvinceMapVo {
    String name;

    Long value;

    String percentage;

    Long value2;

    String percentage2;

    Long value3;

    String percentage3;

    public ProvinceMapVo(String name, Long value, String percentage, Long value2, String percentage2, Long value3, String percentage3) {
        this.name = name;
        this.value = value;
        this.percentage = percentage;
        this.value2 = value2;
        this.percentage2 = percentage2;
        this.value3 = value3;
        this.percentage3 = percentage3;
    }

    public ProvinceMapVo() {
    }
}
