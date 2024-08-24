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


import com.cl.dao.BushiherenqunDao;
import com.cl.entity.BushiherenqunEntity;
import com.cl.service.BushiherenqunService;
import com.cl.entity.view.BushiherenqunView;

@Service("bushiherenqunService")
public class BushiherenqunServiceImpl extends ServiceImpl<BushiherenqunDao, BushiherenqunEntity> implements BushiherenqunService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BushiherenqunEntity> page = this.selectPage(
                new Query<BushiherenqunEntity>(params).getPage(),
                new EntityWrapper<BushiherenqunEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BushiherenqunEntity> wrapper) {
		  Page<BushiherenqunView> page =new Query<BushiherenqunView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<BushiherenqunView> selectListView(Wrapper<BushiherenqunEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BushiherenqunView selectView(Wrapper<BushiherenqunEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
