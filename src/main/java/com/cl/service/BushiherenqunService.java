package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.BushiherenqunEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.BushiherenqunView;


/**
 * 不适合人群
 *
 * @author 
 * @email 
 * @date 2024-03-12 00:03:19
 */
public interface BushiherenqunService extends IService<BushiherenqunEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BushiherenqunView> selectListView(Wrapper<BushiherenqunEntity> wrapper);
   	
   	BushiherenqunView selectView(@Param("ew") Wrapper<BushiherenqunEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BushiherenqunEntity> wrapper);
   	

}

