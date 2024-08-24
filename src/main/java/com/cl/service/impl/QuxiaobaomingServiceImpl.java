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


import com.cl.dao.QuxiaobaomingDao;
import com.cl.entity.QuxiaobaomingEntity;
import com.cl.service.QuxiaobaomingService;
import com.cl.entity.view.QuxiaobaomingView;

@Service("quxiaobaomingService")
public class QuxiaobaomingServiceImpl extends ServiceImpl<QuxiaobaomingDao, QuxiaobaomingEntity> implements QuxiaobaomingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QuxiaobaomingEntity> page = this.selectPage(
                new Query<QuxiaobaomingEntity>(params).getPage(),
                new EntityWrapper<QuxiaobaomingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QuxiaobaomingEntity> wrapper) {
		  Page<QuxiaobaomingView> page =new Query<QuxiaobaomingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<QuxiaobaomingView> selectListView(Wrapper<QuxiaobaomingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QuxiaobaomingView selectView(Wrapper<QuxiaobaomingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
