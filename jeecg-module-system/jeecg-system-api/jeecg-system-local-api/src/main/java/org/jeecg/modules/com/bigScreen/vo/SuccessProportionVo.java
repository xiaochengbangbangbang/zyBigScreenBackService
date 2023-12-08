package org.jeecg.modules.com.bigScreen.vo;

import lombok.Data;

@Data
public class SuccessProportionVo {
    String name;
    Double value;
    Double space;

    Long success;

    Long fail;

    public SuccessProportionVo(String name, Double value, Double space, Long success, Long fail) {
        this.name = name;
        this.value = value;
        this.space = space;
        this.success = success;
        this.fail = fail;
    }
}
