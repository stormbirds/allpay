package cn.stormbirds.allpay.web.controller;

import cn.stormbirds.allpay.api.user.IAppVersionService;
import cn.stormbirds.allpay.common.service.UploadService;
import cn.stormbirds.allpay.common.base.BaseController;
import cn.stormbirds.allpay.common.request.ResultJson;
import cn.stormbirds.allpay.common.entity.FileInfo;
import cn.stormbirds.allpay.model.web.AppVersion;
import cn.stormbirds.allpay.model.web.FindRoundEqModel;
import cn.stormbirds.allpay.web.service.IRankShopService;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * <p>
 * APP版本历史记录 前端控制器
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-06
 */
@Api(value = "APP版本管理控制器",description = "用于app版本管理")
@RestController
@RequestMapping(value = "/api/v1/appversionmanager")
public class AppVersionController extends BaseController {
    private final IAppVersionService appVersionService;

    private final UploadService uploadService;
    @NacosValue("${test.name:a}")
    private String name;
    @NacosValue("${test.age:0}")
    private int age;

    @Autowired
    private IRankShopService rankShopService;

    @Autowired
    public AppVersionController(IAppVersionService appVersionService, UploadService uploadService) {
        this.appVersionService = appVersionService;
        this.uploadService = uploadService;
    }


    @RequestMapping(value="/index", method= RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modal = new ModelAndView("appVersion/index");
        AppVersion appVersion = appVersionService.getOne(Wrappers.<AppVersion>lambdaQuery().orderByDesc(AppVersion::getAppId));
        modal.addObject("app", appVersion);
        return modal;
    }

    @GetMapping(value = "/getDev")
    public ResultJson getDev(){
        return showJsonResult(true, "ok",String.format("name=%s,age=%d",name,age));
    }

    @ApiOperation(value = "修改App信息")
    @RequestMapping(value="/update", method=RequestMethod.POST)
    @ResponseBody
    public ResultJson update(@ModelAttribute AppVersion record) {

        try {
            if(record.getVersionCode().isEmpty()) {
                throw new Exception("VersionCode不能为空");
            }
            if(record.getVersionName().isEmpty()) {
                throw new Exception("VersionName不能为空");
            }
            if(record.getAppUrl().isEmpty()) {
                throw new Exception("APK地址不能为空");
            }
            if(record.getForceUpgrade()==null){
                record.setForceUpgrade(false);
            }
            this.appVersionService.updateById(record);
        } catch (Exception e) {
            e.printStackTrace();
            return showJsonResult(false,e.getMessage(),null) ;
        }
        return showJsonResult(true,"更新成功",null) ;
    }

    @ApiOperation("上传App到服务器")
    @RequestMapping(value="/uploadApp", method=RequestMethod.POST)
    @ResponseBody
    public ResultJson uploadApp(@RequestParam(value = "file") MultipartFile file) {

        try {
            FileInfo fileInfo = uploadService.saveFile(file,"/app/");
            return showJsonResult(true,"上传成功",fileInfo) ;
        } catch (Exception e) {
            e.printStackTrace();
            return showJsonResult(false,"上传失败",null) ;
        }
    }

    @RequestMapping(value="/uploadDlg", method=RequestMethod.GET)
    public ModelAndView uploadDlg(){
        return new ModelAndView("appVersion/uploadDlg");
    }

    @ApiOperation(value = "获取范围内商家")
    @GetMapping(value = "/getFindModel")
    public List<FindRoundEqModel> getFindModel(@RequestParam double minLo , @RequestParam double maxLo ,
                                               @RequestParam double minLa , @RequestParam double maxLa){
        return rankShopService.getFindModel(minLo,maxLo,minLa,maxLa);
    }
}
