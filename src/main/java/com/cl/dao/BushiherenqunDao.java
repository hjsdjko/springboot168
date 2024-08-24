package com.cl.dao;

import com.cl.entity.BushiherenqunEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BushiherenqunView;


/**
 * 不适合人群
 * 
 * @author 
 * @email 
 * @date 2024-03-12 00:03:19
 */
public interface BushiherenqunDao extends BaseMapper<BushiherenqunEntity> {
	
	List<BushiherenqunView> selectListView(@Param("ew") Wrapper<BushiherenqunEntity> wrapper);

	List<BushiherenqunView> selectListView(Pagination page,@Param("ew") Wrapper<BushiherenqunEntity> wrapper);
	
	BushiherenqunView selectView(@Param("ew") Wrapper<BushiherenqunEntity> wrapper);
	

}
