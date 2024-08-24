package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.BushiherenqunEntity;
import com.cl.entity.view.BushiherenqunView;

import com.cl.service.BushiherenqunService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 不适合人群
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-12 00:03:19
 */
@RestController
@RequestMapping("/bushiherenqun")
public class BushiherenqunController {
    @Autowired
    private BushiherenqunService bushiherenqunService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BushiherenqunEntity bushiherenqun,
		HttpServletRequest request){
        EntityWrapper<BushiherenqunEntity> ew = new EntityWrapper<BushiherenqunEntity>();

		PageUtils page = bushiherenqunService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bushiherenqun), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,BushiherenqunEntity bushiherenqun, 
		HttpServletRequest request){
        EntityWrapper<BushiherenqunEntity> ew = new EntityWrapper<BushiherenqunEntity>();

		PageUtils page = bushiherenqunService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bushiherenqun), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BushiherenqunEntity bushiherenqun){
       	EntityWrapper<BushiherenqunEntity> ew = new EntityWrapper<BushiherenqunEntity>();
      	ew.allEq(MPUtil.allEQMapPre( bushiherenqun, "bushiherenqun")); 
        return R.ok().put("data", bushiherenqunService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BushiherenqunEntity bushiherenqun){
        EntityWrapper< BushiherenqunEntity> ew = new EntityWrapper< BushiherenqunEntity>();
 		ew.allEq(MPUtil.allEQMapPre( bushiherenqun, "bushiherenqun")); 
		BushiherenqunView bushiherenqunView =  bushiherenqunService.selectView(ew);
		return R.ok("查询不适合人群成功").put("data", bushiherenqunView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BushiherenqunEntity bushiherenqun = bushiherenqunService.selectById(id);
		bushiherenqun = bushiherenqunService.selectView(new EntityWrapper<BushiherenqunEntity>().eq("id", id));
        return R.ok().put("data", bushiherenqun);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BushiherenqunEntity bushiherenqun = bushiherenqunService.selectById(id);
		bushiherenqun = bushiherenqunService.selectView(new EntityWrapper<BushiherenqunEntity>().eq("id", id));
        return R.ok().put("data", bushiherenqun);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BushiherenqunEntity bushiherenqun, HttpServletRequest request){
    	bushiherenqun.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(bushiherenqun);
        bushiherenqunService.insert(bushiherenqun);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BushiherenqunEntity bushiherenqun, HttpServletRequest request){
    	bushiherenqun.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(bushiherenqun);
        bushiherenqunService.insert(bushiherenqun);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody BushiherenqunEntity bushiherenqun, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bushiherenqun);
        bushiherenqunService.updateById(bushiherenqun);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        bushiherenqunService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
