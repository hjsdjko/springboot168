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

import com.cl.entity.HuodongxinxiEntity;
import com.cl.entity.view.HuodongxinxiView;

import com.cl.service.HuodongxinxiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 活动信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-12 00:03:18
 */
@RestController
@RequestMapping("/huodongxinxi")
public class HuodongxinxiController {
    @Autowired
    private HuodongxinxiService huodongxinxiService;

    @Autowired
    private StoreupService storeupService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,HuodongxinxiEntity huodongxinxi,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date huodongshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date huodongshijianend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("zhubanfang")) {
			huodongxinxi.setZhubanfangzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<HuodongxinxiEntity> ew = new EntityWrapper<HuodongxinxiEntity>();
                if(huodongshijianstart!=null) ew.ge("huodongshijian", huodongshijianstart);
                if(huodongshijianend!=null) ew.le("huodongshijian", huodongshijianend);

		PageUtils page = huodongxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huodongxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,HuodongxinxiEntity huodongxinxi, 
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date huodongshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date huodongshijianend,
		HttpServletRequest request){
        EntityWrapper<HuodongxinxiEntity> ew = new EntityWrapper<HuodongxinxiEntity>();
                if(huodongshijianstart!=null) ew.ge("huodongshijian", huodongshijianstart);
                if(huodongshijianend!=null) ew.le("huodongshijian", huodongshijianend);

		PageUtils page = huodongxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huodongxinxi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( HuodongxinxiEntity huodongxinxi){
       	EntityWrapper<HuodongxinxiEntity> ew = new EntityWrapper<HuodongxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( huodongxinxi, "huodongxinxi")); 
        return R.ok().put("data", huodongxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(HuodongxinxiEntity huodongxinxi){
        EntityWrapper< HuodongxinxiEntity> ew = new EntityWrapper< HuodongxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( huodongxinxi, "huodongxinxi")); 
		HuodongxinxiView huodongxinxiView =  huodongxinxiService.selectView(ew);
		return R.ok("查询活动信息成功").put("data", huodongxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        HuodongxinxiEntity huodongxinxi = huodongxinxiService.selectById(id);
		huodongxinxi.setClicknum(huodongxinxi.getClicknum()+1);
		huodongxinxi.setClicktime(new Date());
		huodongxinxiService.updateById(huodongxinxi);
		huodongxinxi = huodongxinxiService.selectView(new EntityWrapper<HuodongxinxiEntity>().eq("id", id));
        return R.ok().put("data", huodongxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        HuodongxinxiEntity huodongxinxi = huodongxinxiService.selectById(id);
		huodongxinxi.setClicknum(huodongxinxi.getClicknum()+1);
		huodongxinxi.setClicktime(new Date());
		huodongxinxiService.updateById(huodongxinxi);
		huodongxinxi = huodongxinxiService.selectView(new EntityWrapper<HuodongxinxiEntity>().eq("id", id));
        return R.ok().put("data", huodongxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody HuodongxinxiEntity huodongxinxi, HttpServletRequest request){
    	huodongxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(huodongxinxi);
        huodongxinxiService.insert(huodongxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody HuodongxinxiEntity huodongxinxi, HttpServletRequest request){
    	huodongxinxi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(huodongxinxi);
        huodongxinxiService.insert(huodongxinxi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody HuodongxinxiEntity huodongxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(huodongxinxi);
        huodongxinxiService.updateById(huodongxinxi);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<HuodongxinxiEntity> list = new ArrayList<HuodongxinxiEntity>();
        for(Long id : ids) {
            HuodongxinxiEntity huodongxinxi = huodongxinxiService.selectById(id);
            huodongxinxi.setSfsh(sfsh);
            huodongxinxi.setShhf(shhf);
            list.add(huodongxinxi);
        }
        huodongxinxiService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        huodongxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,HuodongxinxiEntity huodongxinxi, HttpServletRequest request,String pre){
        EntityWrapper<HuodongxinxiEntity> ew = new EntityWrapper<HuodongxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicknum");
        params.put("order", "desc");
		PageUtils page = huodongxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huodongxinxi), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 按收藏推荐
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,HuodongxinxiEntity huodongxinxi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String inteltypeColumn = "huodongxingji";
        List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("userid", userId).eq("tablename", "huodongxinxi").orderBy("addtime", false));
        List<String> inteltypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<HuodongxinxiEntity> huodongxinxiList = new ArrayList<HuodongxinxiEntity>();
        //去重
        if(storeups!=null && storeups.size()>0) {
            for(StoreupEntity s : storeups) {
                huodongxinxiList.addAll(huodongxinxiService.selectList(new EntityWrapper<HuodongxinxiEntity>().eq(inteltypeColumn, s.getInteltype())));
            }
        }
        EntityWrapper<HuodongxinxiEntity> ew = new EntityWrapper<HuodongxinxiEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = huodongxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, huodongxinxi), params), params));
        List<HuodongxinxiEntity> pageList = (List<HuodongxinxiEntity>)page.getList();
        if(huodongxinxiList.size()<limit) {
            int toAddNum = (limit-huodongxinxiList.size())<=pageList.size()?(limit-huodongxinxiList.size()):pageList.size();
            for(HuodongxinxiEntity o1 : pageList) {
                boolean addFlag = true;
                for(HuodongxinxiEntity o2 : huodongxinxiList) {
                    if(o1.getId().intValue()==o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    huodongxinxiList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        } else if(huodongxinxiList.size()>limit) {
            huodongxinxiList = huodongxinxiList.subList(0, limit);
        }
        page.setList(huodongxinxiList);
        return R.ok().put("data", page);
    }







}
