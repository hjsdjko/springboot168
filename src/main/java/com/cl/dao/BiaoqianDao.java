package com.cl.dao;

import com.cl.entity.BiaoqianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BiaoqianView;


/**
 * 标签
 * 
 * @author 
 * @email 
 * @date 2024-03-12 00:03:18
 */
public interface BiaoqianDao extends BaseMapper<BiaoqianEntity> {
	
	List<BiaoqianView> selectListView(@Param("ew") Wrapper<BiaoqianEntity> wrapper);

	List<BiaoqianView> selectListView(Pagination page,@Param("ew") Wrapper<BiaoqianEntity> wrapper);
	
	BiaoqianView selectView(@Param("ew") Wrapper<BiaoqianEntity> wrapper);
	

}
