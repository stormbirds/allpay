package cn.stormbirds.allpayweb.controler;

import cn.stormbirds.allpaymodel.users.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 *
 * <p> UserControler.java
 * </p>
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/7/29 11:52
 *
 */
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserControler {

    @PostMapping("/add")
    public boolean addUser(@RequestBody User user) {
        return false;
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
    public boolean delete(@PathVariable("id") int id) {
        return true;
    }
}
