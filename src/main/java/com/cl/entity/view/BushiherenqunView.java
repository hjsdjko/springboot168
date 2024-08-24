package com.cl.entity.view;

import com.cl.entity.BushiherenqunEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 不适合人群
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2024-03-12 00:03:19
 */
@TableName("bushiherenqun")
public class BushiherenqunView  extends BushiherenqunEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public BushiherenqunView(){
	}
 
 	public BushiherenqunView(BushiherenqunEntity bushiherenqunEntity){
 	try {
			BeanUtils.copyProperties(this, bushiherenqunEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
