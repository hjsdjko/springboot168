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

import com.cl.entity.ZhubanfangEntity;
import com.cl.entity.view.ZhubanfangView;

import com.cl.service.ZhubanfangService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 主办方
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-12 00:03:18
 */
@RestController
@RequestMapping("/zhubanfang")
public class ZhubanfangController {
    @Autowired
    private ZhubanfangService zhubanfangService;



    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		ZhubanfangEntity u = zhubanfangService.selectOne(new EntityWrapper<ZhubanfangEntity>().eq("zhubanfangzhanghao", username));
        if(u==null || !u.getZhubanfangmima().equals(EncryptUtil.md5(password))) {
            return R.error("账号或密码不正确");
        }
		String token = tokenService.generateToken(u.getId(), username,"zhubanfang",  "主办方" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody ZhubanfangEntity zhubanfang){
    	//ValidatorUtils.validateEntity(zhubanfang);
    	ZhubanfangEntity u = zhubanfangService.selectOne(new EntityWrapper<ZhubanfangEntity>().eq("zhubanfangzhanghao", zhubanfang.getZhubanfangzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		zhubanfang.setId(uId);
        zhubanfang.setZhubanfangmima(EncryptUtil.md5(zhubanfang.getZhubanfangmima()));
        zhubanfangService.insert(zhubanfang);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        ZhubanfangEntity u = zhubanfangService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	ZhubanfangEntity u = zhubanfangService.selectOne(new EntityWrapper<ZhubanfangEntity>().eq("zhubanfangzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setZhubanfangmima(EncryptUtil.md5("123456"));
        zhubanfangService.updateById(u);
        return R.ok("密码已重置为：123456");
    }


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhubanfangEntity zhubanfang,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("zhubanfang")) {
			zhubanfang.setZhubanfangzhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<ZhubanfangEntity> ew = new EntityWrapper<ZhubanfangEntity>();

		PageUtils page = zhubanfangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhubanfang), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhubanfangEntity zhubanfang, 
		HttpServletRequest request){
        EntityWrapper<ZhubanfangEntity> ew = new EntityWrapper<ZhubanfangEntity>();

		PageUtils page = zhubanfangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhubanfang), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhubanfangEntity zhubanfang){
       	EntityWrapper<ZhubanfangEntity> ew = new EntityWrapper<ZhubanfangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhubanfang, "zhubanfang")); 
        return R.ok().put("data", zhubanfangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhubanfangEntity zhubanfang){
        EntityWrapper< ZhubanfangEntity> ew = new EntityWrapper< ZhubanfangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhubanfang, "zhubanfang")); 
		ZhubanfangView zhubanfangView =  zhubanfangService.selectView(ew);
		return R.ok("查询主办方成功").put("data", zhubanfangView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhubanfangEntity zhubanfang = zhubanfangService.selectById(id);
		zhubanfang = zhubanfangService.selectView(new EntityWrapper<ZhubanfangEntity>().eq("id", id));
        return R.ok().put("data", zhubanfang);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhubanfangEntity zhubanfang = zhubanfangService.selectById(id);
		zhubanfang = zhubanfangService.selectView(new EntityWrapper<ZhubanfangEntity>().eq("id", id));
        return R.ok().put("data", zhubanfang);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhubanfangEntity zhubanfang, HttpServletRequest request){
    	zhubanfang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhubanfang);
    	ZhubanfangEntity u = zhubanfangService.selectOne(new EntityWrapper<ZhubanfangEntity>().eq("zhubanfangzhanghao", zhubanfang.getZhubanfangzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		zhubanfang.setId(new Date().getTime());
        zhubanfang.setZhubanfangmima(EncryptUtil.md5(zhubanfang.getZhubanfangmima()));
        zhubanfangService.insert(zhubanfang);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhubanfangEntity zhubanfang, HttpServletRequest request){
    	zhubanfang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(zhubanfang);
    	ZhubanfangEntity u = zhubanfangService.selectOne(new EntityWrapper<ZhubanfangEntity>().eq("zhubanfangzhanghao", zhubanfang.getZhubanfangzhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		zhubanfang.setId(new Date().getTime());
        zhubanfang.setZhubanfangmima(EncryptUtil.md5(zhubanfang.getZhubanfangmima()));
        zhubanfangService.insert(zhubanfang);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhubanfangEntity zhubanfang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhubanfang);
        ZhubanfangEntity zhubanfangEntity = zhubanfangService.selectById(zhubanfang.getId());
        if(StringUtils.isNotBlank(zhubanfang.getZhubanfangmima()) && !zhubanfang.getZhubanfangmima().equals(zhubanfangEntity.getZhubanfangmima())) {
            zhubanfang.setZhubanfangmima(EncryptUtil.md5(zhubanfang.getZhubanfangmima()));
        }
        zhubanfangService.updateById(zhubanfang);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhubanfangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
