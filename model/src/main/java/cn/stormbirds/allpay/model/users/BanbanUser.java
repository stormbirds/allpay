package cn.stormbirds.allpay.model.users;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "BanbanUser对象", description = "用户表")
public class BanbanUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "用户所在地详细住址")
    private String address;

    @ApiModelProperty(value = "用户所在城市")
    private String city;

    @ApiModelProperty(value = "用户所在国家")
    private String country;

    @ApiModelProperty(value = "用户电话")
    private String phone;

    @ApiModelProperty(value = "用户是否启用（默认1启用）")
    private Boolean enabled;

    @ApiModelProperty(value = "用户是否未锁定（默认1未锁定）")
    private Boolean accountNonLocked;

    @ApiModelProperty(value = "用户性别（）")
    private String sex;

    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户真实姓名")
    private String realname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户自我介绍")
    private String introduction;

    @ApiModelProperty(value = "用户声音描述")
    private String voiceprint;

    @ApiModelProperty(value = "用户创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "用户更新时间")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "用户生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "用户情感状态")
    private String emotion;

    @ApiModelProperty(value = "用户个人信息背景图")
    private String bgImage;

    @ApiModelProperty(value = "用户上次修改密码时间")
    private LocalDateTime lastPasswordResetDate;


}
