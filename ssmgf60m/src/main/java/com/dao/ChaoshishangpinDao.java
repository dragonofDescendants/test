package com.dao;

import com.entity.ChaoshishangpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChaoshishangpinVO;
import com.entity.view.ChaoshishangpinView;


/**
 * 超市商品
 *
 * @author
 * @email
 * @date 2022-04-26 19:49:31
 */
public interface ChaoshishangpinDao extends BaseMapper<ChaoshishangpinEntity> {

	List<ChaoshishangpinVO> selectListVO(@Param("ew") Wrapper<ChaoshishangpinEntity> wrapper);

	ChaoshishangpinVO selectVO(@Param("ew") Wrapper<ChaoshishangpinEntity> wrapper);

	List<ChaoshishangpinView> selectListView(@Param("ew") Wrapper<ChaoshishangpinEntity> wrapper);

	List<ChaoshishangpinView> selectListView(@Param("params") Map<String, Object> params,Pagination page,@Param("ew") Wrapper<ChaoshishangpinEntity> wrapper);

	ChaoshishangpinView selectView(@Param("ew") Wrapper<ChaoshishangpinEntity> wrapper);


}
