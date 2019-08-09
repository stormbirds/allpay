package cn.stormbirds.allpay.common.service.impl;

import cn.stormbirds.allpay.common.service.IdCenterService;
import cn.stormbirds.allpay.common.service.UploadService;
import cn.stormbirds.allpay.common.entity.FileInfo;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * cn.stormbirds.allpay.service
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/6 15:31
 */

@Service
public class UploadServiceImpl implements UploadService {

    @Value(value = "${web.uploadPath:/Users/xbj/Desktop/temp/web}")
    private String uploadPath;
    @Autowired
    private IdCenterService idCenterService;

    @Override
    public FileInfo saveFile(MultipartFile uploadfile, String prefix) throws IOException {
        return saveFile(uploadfile, uploadPath, prefix);
    }

    private FileInfo saveFile(MultipartFile uploadFile, String uploadPath, String prefix) throws IOException {
        FileInfo fileInfo = new FileInfo();
        if (uploadFile.isEmpty()) {
            return fileInfo;
        }
        String datePathStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if (prefix.startsWith("/")) {
            uploadPath = uploadPath + prefix;
        } else {
            uploadPath = uploadPath + "/" + prefix;
        }
        if (prefix.endsWith("/")) {
            uploadPath = uploadPath + datePathStr;
        } else {
            uploadPath = uploadPath + "/" + datePathStr;
        }
        File dir = new File(uploadPath);
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException();
        }

        String originalFileName = uploadFile.getOriginalFilename();
        String extName = "";
        if (originalFileName != null) {
            extName = originalFileName.substring(originalFileName.lastIndexOf('.') + 1);
        }
        String saveFileName = idCenterService.getSyncIdString()
                .concat(".")
                .concat(extName);

        long fileSize = uploadFile.getSize();

        String filepath = Paths.get(uploadPath, saveFileName).toString();
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)))) {
            stream.write(uploadFile.getBytes());
        }

        BufferedImage image = ImageIO.read(uploadFile.getInputStream());
        if (image != null) {
            String thumbnailFilename = saveFileName.substring(0,saveFileName.lastIndexOf(".")+1) + "thumbnail.jpg";
            String thumbnail = Paths.get(uploadPath, thumbnailFilename).toString();
            Thumbnails.of(new File(filepath))
                    .scale(0.2)
                    .outputFormat("jpg")
                    .toFile(thumbnail);
            fileInfo.setThumbImg(uploadPath + "/" + thumbnailFilename);
        }

        originalFileName = originalFileName != null ? originalFileName.substring(0, originalFileName.lastIndexOf('.')) : "";
        fileInfo.setOriFileName(originalFileName);
        fileInfo.setSaveFileName(saveFileName);
        fileInfo.setExt(extName);


        fileInfo.setSize(fileSize);
        fileInfo.setPath(uploadPath + "/" +saveFileName);
        fileInfo.setMimeType(uploadFile.getContentType());
        return fileInfo;
    }
}
