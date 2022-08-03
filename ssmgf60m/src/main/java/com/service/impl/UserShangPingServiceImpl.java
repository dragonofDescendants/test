
package com.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.UserDao;
import com.dao.UserShangPingDao;
import com.entity.UserEntity;
import com.entity.UserShangPing;
import com.service.UserService;
import com.service.UserShangPingService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 点击统计
 */
@Service("userShangPingService")
public class UserShangPingServiceImpl extends ServiceImpl<UserShangPingDao, UserShangPing> implements UserShangPingService {


    @Override
    public Long selectShangpinidList(Long userId) {
        return baseMapper.selectShangpinidList(userId);
    }

    @Override
    public List<Long>  selectId(Long userId) {
        return baseMapper.selectId(userId);
    }
}
