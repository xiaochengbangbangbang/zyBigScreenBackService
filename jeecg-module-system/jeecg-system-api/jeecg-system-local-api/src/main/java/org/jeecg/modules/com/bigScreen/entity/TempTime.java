package org.jeecg.modules.com.bigScreen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

@Data
@TableName("tempTime")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tempTime", description="tempTime")
public class TempTime implements Serializable {

    private static final long serialVersionUID = 1L;

    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Integer id;
    /**billingId*/
//    @Excel(name = "billingId", width = 15)
    @ApiModelProperty(value = "time")
    private String time;

}
