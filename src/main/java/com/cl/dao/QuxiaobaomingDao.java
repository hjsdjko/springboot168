package com.cl.dao;

import com.cl.entity.QuxiaobaomingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QuxiaobaomingView;


/**
 * 取消报名
 * 
 * @author 
 * @email 
 * @date 2024-03-12 00:03:18
 */
public interface QuxiaobaomingDao extends BaseMapper<QuxiaobaomingEntity> {
	
	List<QuxiaobaomingView> selectListView(@Param("ew") Wrapper<QuxiaobaomingEntity> wrapper);

	List<QuxiaobaomingView> selectListView(Pagination page,@Param("ew") Wrapper<QuxiaobaomingEntity> wrapper);
	
	QuxiaobaomingView selectView(@Param("ew") Wrapper<QuxiaobaomingEntity> wrapper);
	

}
