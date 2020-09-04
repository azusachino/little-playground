package cn.az.code.mvc.service;

import cn.az.code.mvc.dto.UserDto;
import cn.az.code.mvc.entity.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author az
 * @since 09/04/20
 */
public interface UserService extends IService<UserInfo> {

    /**
     * 分页查询
     *
     * @param userDto 用户信息
     * @return 用户信息
     */
    IPage<UserInfo> selectPage(UserDto userDto);

    /**
     * 添加新用户
     *
     * @param userInfo 用户信息
     * @return 新增结果
     */
    Integer addUserInfo(UserInfo userInfo);
}
