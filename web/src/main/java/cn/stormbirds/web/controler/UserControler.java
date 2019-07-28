package cn.stormbirds.web.controler;

import cn.stormbirds.model.users.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

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
