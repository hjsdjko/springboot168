package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.ZhubanfangEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.ZhubanfangView;


/**
 * 主办方
 *
 * @author 
 * @email 
 * @date 2024-03-12 00:03:18
 */
public interface ZhubanfangService extends IService<ZhubanfangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhubanfangView> selectListView(Wrapper<ZhubanfangEntity> wrapper);
   	
   	ZhubanfangView selectView(@Param("ew") Wrapper<ZhubanfangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhubanfangEntity> wrapper);
   	

}

