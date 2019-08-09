package cn.stormbirds.allpay.common.service;

import cn.stormbirds.allpay.common.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * cn.stormbirds.allpay.api.user
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/6 15:26
 */


public interface UploadService {
    FileInfo saveFile(MultipartFile uploadfile, String prefix) throws IOException;
}
