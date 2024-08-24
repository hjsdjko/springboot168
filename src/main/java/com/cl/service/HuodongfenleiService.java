package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.HuodongfenleiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.HuodongfenleiView;


/**
 * 活动分类
 *
 * @author 
 * @email 
 * @date 2024-03-12 00:03:18
 */
public interface HuodongfenleiService extends IService<HuodongfenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<HuodongfenleiView> selectListView(Wrapper<HuodongfenleiEntity> wrapper);
   	
   	HuodongfenleiView selectView(@Param("ew") Wrapper<HuodongfenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<HuodongfenleiEntity> wrapper);
   	

}

