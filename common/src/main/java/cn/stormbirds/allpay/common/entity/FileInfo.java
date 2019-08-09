package cn.stormbirds.allpay.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * cn.stormbirds.allpay.model
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/6 15:27
 */

@Data @ApiModel(value = "文件信息", description = "上传文件返回的信息")
public class FileInfo {
    @ApiModelProperty(value ="文件大小", example = "100")
    private Long size ;
    @ApiModelProperty(value ="文件后缀", example = ".png")
    private String ext;
    @ApiModelProperty(value ="文件路径", example = "/upload/20190601/xxxxxxxx.png")
    private String path;
    @ApiModelProperty(value ="缩略图", example = "/upload/20190601/xxxxxxxx.thumbnail.png")
    private String thumbImg;
    @ApiModelProperty(value ="文件原名", example = "xxxxxxxx.png")
    private String oriFileName;
    @ApiModelProperty(value ="文件保存名", example = "xxxxxxxx.png")
    private String saveFileName;
    @ApiModelProperty(value ="文件类型", example = "jpeg")
    private String mimeType;
}
