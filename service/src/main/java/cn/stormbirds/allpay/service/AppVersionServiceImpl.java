package cn.stormbirds.allpay.service;

import cn.stormbirds.allpay.api.user.IAppVersionService;
import cn.stormbirds.allpay.dao.AppVersionDao;
import cn.stormbirds.allpay.model.web.AppVersion;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * APP版本历史记录 服务实现类
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019-08-06
 */
@Service
public class AppVersionServiceImpl extends ServiceImpl<AppVersionDao, AppVersion> implements IAppVersionService {

}
