package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.QuxiaobaomingEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.QuxiaobaomingView;


/**
 * 取消报名
 *
 * @author 
 * @email 
 * @date 2024-03-12 00:03:18
 */
public interface QuxiaobaomingService extends IService<QuxiaobaomingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<QuxiaobaomingView> selectListView(Wrapper<QuxiaobaomingEntity> wrapper);
   	
   	QuxiaobaomingView selectView(@Param("ew") Wrapper<QuxiaobaomingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<QuxiaobaomingEntity> wrapper);
   	

}

