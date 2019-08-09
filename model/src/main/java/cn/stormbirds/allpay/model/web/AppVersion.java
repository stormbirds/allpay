package cn.stormbirds.allpay.model.web;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * APP版本历史记录
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AppVersion对象", description = "APP版本历史记录")
public class AppVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty(value = "id主键")
    private Long appId;

    @ApiModelProperty(value = "App名")
    private String appName;

    @ApiModelProperty(value = "版本号")
    private String versionCode;

    @ApiModelProperty(value = "版本名")
    private String versionName;

    @ApiModelProperty(value = "下载链接")
    private String appUrl;

    @ApiModelProperty(value = "改动日志")
    private String changeLog;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "是否强制升级")
    private Boolean forceUpgrade;

    @ApiModelProperty(value = "文件大小")
    private Integer fileSize;

    @ApiModelProperty(value = "APP平台：0-android、1-ios")
    private Integer appOs;


}
