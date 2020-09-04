package cn.az.code.mvc.service.impl;

import cn.az.code.mvc.dto.UserDto;
import cn.az.code.mvc.entity.UserInfo;
import cn.az.code.mvc.mapper.UserMapper;
import cn.az.code.mvc.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author az
 * @since 09/04/20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    /**
     * 分页查询
     *
     * @param userDto 用户信息
     * @return 用户信息
     */
    @Override
    public IPage<UserInfo> selectPage(UserDto userDto) {
        Page<UserInfo> page = new Page<>(userDto.getPageIndex(), userDto.getPageSize());
        return baseMapper.selectPage(page, Wrappers.<UserInfo>lambdaQuery()
                .like(StringUtils.hasLength(userDto.getUsername()), UserInfo::getUsername, userDto.getUsername())
                .like(StringUtils.hasLength(userDto.getAddress()), UserInfo::getAddress, userDto.getAddress()));
    }

    /**
     * 添加新用户
     *
     * @param userInfo 用户信息
     * @return 新增结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addUserInfo(UserInfo userInfo) {
        boolean ret = save(userInfo);
        return ret ? 1 : -1;
    }
}
