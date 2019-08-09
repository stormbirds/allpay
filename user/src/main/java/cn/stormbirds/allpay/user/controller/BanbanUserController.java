package cn.stormbirds.allpay.user.controller;


import cn.stormbirds.allpay.api.user.IBanbanUserService;
import cn.stormbirds.allpay.common.request.ResultJson;
import cn.stormbirds.allpay.model.users.User;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-03
 */
@Slf4j
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
@RestController
@RequestMapping("/api/v1")
public class BanbanUserController/* extends BaseController*/ {
    @Autowired
    private IBanbanUserService userService;

    @NacosValue(value = "${spring.datasource.username:}",autoRefreshed = true)
    private String username;

    @NacosValue(value = "${useLocalCache:false}",autoRefreshed = true)
    private boolean useLocalCache;

    @NacosValue(value = "${spring.datasource:空}",autoRefreshed = true)
    private String dataSource;

    @ApiOperation(value = "获取用户列表")
    @GetMapping(value = "/users")
    public ResultJson getUserList(){
        return ResultJson.ok(userService.list());
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        log.info("UserControler {} {}", useLocalCache,dataSource);
        return useLocalCache;
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/find/{id}")
    public User findById(@PathVariable("id") int id) {
        return new User();
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/update")
    public boolean update(@RequestBody User user) {
        return true;
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        return username;
    }

    @ApiOperation(value = "获取nacos配置")
    @GetMapping("/getnacos")
    public ResultJson getNacos(){
        return ResultJson.ok();
    }
}
