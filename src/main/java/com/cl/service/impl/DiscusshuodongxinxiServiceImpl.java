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


import com.cl.dao.DiscusshuodongxinxiDao;
import com.cl.entity.DiscusshuodongxinxiEntity;
import com.cl.service.DiscusshuodongxinxiService;
import com.cl.entity.view.DiscusshuodongxinxiView;

@Service("discusshuodongxinxiService")
public class DiscusshuodongxinxiServiceImpl extends ServiceImpl<DiscusshuodongxinxiDao, DiscusshuodongxinxiEntity> implements DiscusshuodongxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscusshuodongxinxiEntity> page = this.selectPage(
                new Query<DiscusshuodongxinxiEntity>(params).getPage(),
                new EntityWrapper<DiscusshuodongxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusshuodongxinxiEntity> wrapper) {
		  Page<DiscusshuodongxinxiView> page =new Query<DiscusshuodongxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<DiscusshuodongxinxiView> selectListView(Wrapper<DiscusshuodongxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscusshuodongxinxiView selectView(Wrapper<DiscusshuodongxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
