package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.UserShangPing;

import java.util.List;

public interface UserShangPingService extends IService<UserShangPing> {
    Long selectShangpinidList(Long userId);

    List<Long> selectId(Long userId);
}
