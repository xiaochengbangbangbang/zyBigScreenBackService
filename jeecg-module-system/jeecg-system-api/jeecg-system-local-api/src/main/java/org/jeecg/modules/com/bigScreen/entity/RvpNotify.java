package org.jeecg.modules.com.bigScreen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: rvp_notify
 * @Author: jeecg-boot
 * @Date:   2023-05-21
 * @Version: V1.0
 */
@Data
@TableName("rvp_notify")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="rvp_notify对象", description="rvp_notify")
public class RvpNotify implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private Integer id;
	/**billingId*/
	@Excel(name = "billingId", width = 15)
    @ApiModelProperty(value = "billingId")
    private String billingId;
	/**callInNumber*/
	@Excel(name = "callInNumber", width = 15)
    @ApiModelProperty(value = "callInNumber")
    private String callInNumber;
	/**callOutNumber*/
	@Excel(name = "callOutNumber", width = 15)
    @ApiModelProperty(value = "callOutNumber")
    private String callOutNumber;
	/**sendTime*/
	@Excel(name = "sendTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "sendTime")
    private Date sendTime;
	/**deliverCode*/
	@Excel(name = "deliverCode", width = 15)
    @ApiModelProperty(value = "deliverCode")
    private String deliverCode;
	/**responseTime*/
	@Excel(name = "responseTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "responseTime")
    private Date responseTime;
	/**mpInfo*/
	@Excel(name = "mpInfo", width = 15)
    @ApiModelProperty(value = "mpInfo")
    private String mpInfo;
	/**userCode*/
	@Excel(name = "userCode", width = 15)
    @ApiModelProperty(value = "userCode")
    private String userCode;
	/**returnTime*/
	@Excel(name = "returnTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "returnTime")
    private Date returnTime;
	/**memo*/
	@Excel(name = "memo", width = 15)
    @ApiModelProperty(value = "memo")
    private String memo;
	/**memo1*/
	@Excel(name = "memo1", width = 15)
    @ApiModelProperty(value = "memo1")
    private String memo1;
	/**token*/
	@Excel(name = "token", width = 15)
    @ApiModelProperty(value = "token")
    private String token;
	/**shortSms*/
	@Excel(name = "shortSms", width = 15)
    @ApiModelProperty(value = "shortSms")
    private String shortSms;
	/**reciverTime*/
	@Excel(name = "reciverTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "reciverTime")
    private Date reciverTime;
	/**ischeck*/
	@Excel(name = "ischeck", width = 15)
    @ApiModelProperty(value = "ischeck")
    private String ischeck;
	/**url*/
	@Excel(name = "url", width = 15)
    @ApiModelProperty(value = "url")
    private String url;
	/**realno*/
	@Excel(name = "realno", width = 15)
    @ApiModelProperty(value = "realno")
    private String realno;
	/**msgid*/
	@Excel(name = "msgid", width = 15)
    @ApiModelProperty(value = "msgid")
    private String msgid;
	/**sign*/
	@Excel(name = "sign", width = 15)
    @ApiModelProperty(value = "sign")
    private String sign;
	/**province*/
	@Excel(name = "province", width = 15)
    @ApiModelProperty(value = "province")
    private String province;
	/**city*/
	@Excel(name = "city", width = 15)
    @ApiModelProperty(value = "city")
    private String city;
	/**isp*/
	@Excel(name = "isp", width = 15)
    @ApiModelProperty(value = "isp")
    private String isp;
	/**submitCode*/
	@Excel(name = "submitCode", width = 15)
    @ApiModelProperty(value = "submitCode")
    private String submitCode;
	/**deliverTimeStr*/
	@Excel(name = "deliverTimeStr", width = 15)
    @ApiModelProperty(value = "deliverTimeStr")
    private String deliverTimeStr;
	/**deliverReciverTime*/
	@Excel(name = "deliverReciverTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "deliverReciverTime")
    private Date deliverReciverTime;
	/**sNumber*/
	@Excel(name = "sNumber", width = 15)
    @ApiModelProperty(value = "sNumber")
    private String sNumber;
	/**channelName*/
	@Excel(name = "channelName", width = 15)
    @ApiModelProperty(value = "channelName")
    private String channelName;
	/**realDeliverCode*/
	@Excel(name = "realDeliverCode", width = 15)
    @ApiModelProperty(value = "realDeliverCode")
    private String realDeliverCode;
	/**realNumber*/
	@Excel(name = "realNumber", width = 15)
    @ApiModelProperty(value = "realNumber")
    private String realNumber;
	/**templateName*/
	@Excel(name = "templateName", width = 15)
    @ApiModelProperty(value = "templateName")
    private String templateName;
	/**receiptMsg*/
	@Excel(name = "receiptMsg", width = 15)
    @ApiModelProperty(value = "receiptMsg")
    private String receiptMsg;
	/**createTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
    private Date createTime;
}
