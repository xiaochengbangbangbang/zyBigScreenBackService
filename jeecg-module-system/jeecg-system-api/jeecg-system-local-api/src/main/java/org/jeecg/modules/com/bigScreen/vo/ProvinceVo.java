package org.jeecg.modules.com.bigScreen.vo;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class ProvinceVo {
    String name;
    Long value;

    String percentage;


    public ProvinceVo(String name, Long value, String percentage) {
        this.name = name;
        this.value = value;
        this.percentage = percentage;
    }
}
