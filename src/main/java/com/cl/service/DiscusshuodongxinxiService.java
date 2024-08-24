package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscusshuodongxinxiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusshuodongxinxiView;


/**
 * 活动信息评论表
 *
 * @author 
 * @email 
 * @date 2024-03-12 00:03:19
 */
public interface DiscusshuodongxinxiService extends IService<DiscusshuodongxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscusshuodongxinxiView> selectListView(Wrapper<DiscusshuodongxinxiEntity> wrapper);
   	
   	DiscusshuodongxinxiView selectView(@Param("ew") Wrapper<DiscusshuodongxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscusshuodongxinxiEntity> wrapper);
   	

}

