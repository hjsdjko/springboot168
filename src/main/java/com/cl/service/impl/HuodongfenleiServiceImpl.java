package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.HuodongfenleiDao;
import com.cl.entity.HuodongfenleiEntity;
import com.cl.service.HuodongfenleiService;
import com.cl.entity.view.HuodongfenleiView;

@Service("huodongfenleiService")
public class HuodongfenleiServiceImpl extends ServiceImpl<HuodongfenleiDao, HuodongfenleiEntity> implements HuodongfenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<HuodongfenleiEntity> page = this.selectPage(
                new Query<HuodongfenleiEntity>(params).getPage(),
                new EntityWrapper<HuodongfenleiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<HuodongfenleiEntity> wrapper) {
		  Page<HuodongfenleiView> page =new Query<HuodongfenleiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<HuodongfenleiView> selectListView(Wrapper<HuodongfenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public HuodongfenleiView selectView(Wrapper<HuodongfenleiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
