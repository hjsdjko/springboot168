package com.cl.dao;

import com.cl.entity.HuodongfenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.HuodongfenleiView;


/**
 * 活动分类
 * 
 * @author 
 * @email 
 * @date 2024-03-12 00:03:18
 */
public interface HuodongfenleiDao extends BaseMapper<HuodongfenleiEntity> {
	
	List<HuodongfenleiView> selectListView(@Param("ew") Wrapper<HuodongfenleiEntity> wrapper);

	List<HuodongfenleiView> selectListView(Pagination page,@Param("ew") Wrapper<HuodongfenleiEntity> wrapper);
	
	HuodongfenleiView selectView(@Param("ew") Wrapper<HuodongfenleiEntity> wrapper);
	

}
