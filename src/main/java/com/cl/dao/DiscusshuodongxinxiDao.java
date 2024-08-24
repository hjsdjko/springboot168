package com.cl.dao;

import com.cl.entity.DiscusshuodongxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusshuodongxinxiView;


/**
 * 活动信息评论表
 * 
 * @author 
 * @email 
 * @date 2024-03-12 00:03:19
 */
public interface DiscusshuodongxinxiDao extends BaseMapper<DiscusshuodongxinxiEntity> {
	
	List<DiscusshuodongxinxiView> selectListView(@Param("ew") Wrapper<DiscusshuodongxinxiEntity> wrapper);

	List<DiscusshuodongxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusshuodongxinxiEntity> wrapper);
	
	DiscusshuodongxinxiView selectView(@Param("ew") Wrapper<DiscusshuodongxinxiEntity> wrapper);
	

}
