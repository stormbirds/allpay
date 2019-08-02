package cn.stormbirds.allpayusers.controler;

import cn.stormbirds.allpaymodel.request.ResultCode;
import cn.stormbirds.allpaymodel.request.ResultJson;
import cn.stormbirds.allpaymodel.users.User;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 *
 * <p> UserControler.java
 * </p>
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/7/29 10:05
 *
 */
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
@RestController
@RequestMapping(value = "/api/v1/users")
@Slf4j
public class UserControler {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @NacosValue(value = "${spring.datasource:空}", autoRefreshed = true)
    private String dataSource;

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        log.info("UserControler {} {}", useLocalCache,dataSource);
        return useLocalCache;
    }
    @GetMapping("/find/{id}")
    public User findById(@PathVariable("id") int id) {
        return new User();
    }
    @PutMapping("/update")
    public boolean update(@RequestBody User user) {
        return true;
    }
    @DeleteMapping("/delete/{id}")
    public ResultJson delete(@PathVariable("id") int id) {
        return ResultJson.failure(ResultCode.getFailureCode(id,String.valueOf(id)),new HashMap<String, ResultCode>(){{
            put("",ResultCode.FAILURE);
        }});
    }
}
