package com.entity.view;

import com.entity.DiscusschaoshishangpinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 超市商品评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2022-04-26 19:49:31
 */
@TableName("discusschaoshishangpin")
public class DiscusschaoshishangpinView  extends DiscusschaoshishangpinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscusschaoshishangpinView(){
	}
 
 	public DiscusschaoshishangpinView(DiscusschaoshishangpinEntity discusschaoshishangpinEntity){
 	try {
			BeanUtils.copyProperties(this, discusschaoshishangpinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
