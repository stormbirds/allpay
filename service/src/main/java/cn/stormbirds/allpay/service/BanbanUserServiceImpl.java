package cn.stormbirds.allpay.service;

import cn.stormbirds.allpay.api.user.IBanbanUserService;
import cn.stormbirds.allpay.model.users.BanbanUser;
import cn.stormbirds.allpay.dao.BanbanUserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-03
 */
@Service
public class BanbanUserServiceImpl extends ServiceImpl<BanbanUserMapper, BanbanUser> implements IBanbanUserService {

}
