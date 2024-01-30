package org.jeecg.modules.com.bigScreen.vo;

import lombok.Data;
import org.jeecg.modules.com.bigScreen.Domain.MapDomain;
import org.jetbrains.annotations.NotNull;

@Data
public class ProvinceMapVo implements Comparable<ProvinceMapVo> {
    String name;

    Long value;

    String percentage;

    Long value2;

    String percentage2;

    Long value3;

    String percentage3;

    Long allCnt;

    public ProvinceMapVo(String name, Long value, String percentage, Long value2, String percentage2, Long value3, String percentage3, Long allCnt) {
        this.name = name;
        this.value = value;
        this.percentage = percentage;
        this.value2 = value2;
        this.percentage2 = percentage2;
        this.value3 = value3;
        this.percentage3 = percentage3;
        this.allCnt = allCnt;
    }

    public ProvinceMapVo() {
    }

    @Override
    public int compareTo(@NotNull ProvinceMapVo o) {
        return (int) (o.getAllCnt() - this.getAllCnt());
    }
}
