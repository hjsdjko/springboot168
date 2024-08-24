package com.cl.dao;

import com.cl.entity.ZhubanfangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZhubanfangView;


/**
 * 主办方
 * 
 * @author 
 * @email 
 * @date 2024-03-12 00:03:18
 */
public interface ZhubanfangDao extends BaseMapper<ZhubanfangEntity> {
	
	List<ZhubanfangView> selectListView(@Param("ew") Wrapper<ZhubanfangEntity> wrapper);

	List<ZhubanfangView> selectListView(Pagination page,@Param("ew") Wrapper<ZhubanfangEntity> wrapper);
	
	ZhubanfangView selectView(@Param("ew") Wrapper<ZhubanfangEntity> wrapper);
	

}
