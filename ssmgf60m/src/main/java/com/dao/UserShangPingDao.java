
package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.UserEntity;
import com.entity.UserShangPing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品点击量
 */
public interface UserShangPingDao extends BaseMapper<UserShangPing> {

    Long selectShangpinidList(Long userId);

    List<Long> selectId(Long userId);
}
