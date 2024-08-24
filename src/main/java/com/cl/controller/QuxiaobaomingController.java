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

import com.cl.entity.QuxiaobaomingEntity;
import com.cl.entity.view.QuxiaobaomingView;

import com.cl.service.QuxiaobaomingService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 取消报名
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-12 00:03:18
 */
@RestController
@RequestMapping("/quxiaobaoming")
public class QuxiaobaomingController {
    @Autowired
    private QuxiaobaomingService quxiaobaomingService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QuxiaobaomingEntity quxiaobaoming,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date huodongshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date huodongshijianend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("zhubanfang")) {
			quxiaobaoming.setZhubanfangzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			quxiaobaoming.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<QuxiaobaomingEntity> ew = new EntityWrapper<QuxiaobaomingEntity>();
                if(huodongshijianstart!=null) ew.ge("huodongshijian", huodongshijianstart);
                if(huodongshijianend!=null) ew.le("huodongshijian", huodongshijianend);

		PageUtils page = quxiaobaomingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, quxiaobaoming), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,QuxiaobaomingEntity quxiaobaoming, 
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date huodongshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date huodongshijianend,
		HttpServletRequest request){
        EntityWrapper<QuxiaobaomingEntity> ew = new EntityWrapper<QuxiaobaomingEntity>();
                if(huodongshijianstart!=null) ew.ge("huodongshijian", huodongshijianstart);
                if(huodongshijianend!=null) ew.le("huodongshijian", huodongshijianend);

		PageUtils page = quxiaobaomingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, quxiaobaoming), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QuxiaobaomingEntity quxiaobaoming){
       	EntityWrapper<QuxiaobaomingEntity> ew = new EntityWrapper<QuxiaobaomingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( quxiaobaoming, "quxiaobaoming")); 
        return R.ok().put("data", quxiaobaomingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QuxiaobaomingEntity quxiaobaoming){
        EntityWrapper< QuxiaobaomingEntity> ew = new EntityWrapper< QuxiaobaomingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( quxiaobaoming, "quxiaobaoming")); 
		QuxiaobaomingView quxiaobaomingView =  quxiaobaomingService.selectView(ew);
		return R.ok("查询取消报名成功").put("data", quxiaobaomingView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QuxiaobaomingEntity quxiaobaoming = quxiaobaomingService.selectById(id);
		quxiaobaoming = quxiaobaomingService.selectView(new EntityWrapper<QuxiaobaomingEntity>().eq("id", id));
        return R.ok().put("data", quxiaobaoming);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QuxiaobaomingEntity quxiaobaoming = quxiaobaomingService.selectById(id);
		quxiaobaoming = quxiaobaomingService.selectView(new EntityWrapper<QuxiaobaomingEntity>().eq("id", id));
        return R.ok().put("data", quxiaobaoming);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody QuxiaobaomingEntity quxiaobaoming, HttpServletRequest request){
    	quxiaobaoming.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(quxiaobaoming);
        quxiaobaomingService.insert(quxiaobaoming);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody QuxiaobaomingEntity quxiaobaoming, HttpServletRequest request){
    	quxiaobaoming.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(quxiaobaoming);
        quxiaobaomingService.insert(quxiaobaoming);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody QuxiaobaomingEntity quxiaobaoming, HttpServletRequest request){
        //ValidatorUtils.validateEntity(quxiaobaoming);
        quxiaobaomingService.updateById(quxiaobaoming);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<QuxiaobaomingEntity> list = new ArrayList<QuxiaobaomingEntity>();
        for(Long id : ids) {
            QuxiaobaomingEntity quxiaobaoming = quxiaobaomingService.selectById(id);
            quxiaobaoming.setSfsh(sfsh);
            quxiaobaoming.setShhf(shhf);
            list.add(quxiaobaoming);
        }
        quxiaobaomingService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        quxiaobaomingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
