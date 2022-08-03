package com.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.entity.*;
import com.service.*;
import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.view.ChaoshishangpinView;

import com.utils.PageUtils;
import com.utils.R;
import com.utils.MD5Util;
import com.utils.MPUtil;
import com.utils.CommonUtil;

/**
 * 超市商品
 * 后端接口
 * @author
 * @email
 * @date 2022-04-26 19:49:31
 */
@RestController
@RequestMapping("/chaoshishangpin")
public class ChaoshishangpinController {
    @Autowired
    private ChaoshishangpinService chaoshishangpinService;


    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserShangPingService userShangPingService;



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChaoshishangpinEntity chaoshishangpin,
		HttpServletRequest request){

        EntityWrapper<ChaoshishangpinEntity> ew = new EntityWrapper<ChaoshishangpinEntity>();
		PageUtils page = chaoshishangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chaoshishangpin), params), params));
        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChaoshishangpinEntity chaoshishangpin,
		HttpServletRequest request){
        EntityWrapper<ChaoshishangpinEntity> ew = new EntityWrapper<ChaoshishangpinEntity>();
		PageUtils page = chaoshishangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chaoshishangpin), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChaoshishangpinEntity chaoshishangpin){
       	EntityWrapper<ChaoshishangpinEntity> ew = new EntityWrapper<ChaoshishangpinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chaoshishangpin, "chaoshishangpin"));
        return R.ok().put("data", chaoshishangpinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChaoshishangpinEntity chaoshishangpin){
        EntityWrapper< ChaoshishangpinEntity> ew = new EntityWrapper< ChaoshishangpinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chaoshishangpin, "chaoshishangpin"));
		ChaoshishangpinView chaoshishangpinView =  chaoshishangpinService.selectView(ew);
		return R.ok("查询超市商品成功").put("data", chaoshishangpinView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChaoshishangpinEntity chaoshishangpin = chaoshishangpinService.selectById(id);
		chaoshishangpin.setClicknum(chaoshishangpin.getClicknum()+1);
		chaoshishangpin.setClicktime(new Date());
		chaoshishangpinService.updateById(chaoshishangpin);
        return R.ok().put("data", chaoshishangpin);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChaoshishangpinEntity chaoshishangpin = chaoshishangpinService.selectById(id);
		chaoshishangpin.setClicknum(chaoshishangpin.getClicknum()+1);
		chaoshishangpin.setClicktime(new Date());
		chaoshishangpinService.updateById(chaoshishangpin);
        return R.ok().put("data", chaoshishangpin);
    }



    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R thumbsup(@PathVariable("id") String id,String type){
        ChaoshishangpinEntity chaoshishangpin = chaoshishangpinService.selectById(id);
        if(type.equals("1")) {
        	chaoshishangpin.setThumbsupnum(chaoshishangpin.getThumbsupnum()+1);
        } else {
        	chaoshishangpin.setCrazilynum(chaoshishangpin.getCrazilynum()+1);
        }
        chaoshishangpinService.updateById(chaoshishangpin);
        return R.ok();
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChaoshishangpinEntity chaoshishangpin, HttpServletRequest request){
    	chaoshishangpin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chaoshishangpin);

        chaoshishangpinService.insert(chaoshishangpin);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChaoshishangpinEntity chaoshishangpin, HttpServletRequest request){
    	chaoshishangpin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(chaoshishangpin);

        chaoshishangpinService.insert(chaoshishangpin);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChaoshishangpinEntity chaoshishangpin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chaoshishangpin);
        chaoshishangpinService.updateById(chaoshishangpin);//全部更新
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chaoshishangpinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);

		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}

		Wrapper<ChaoshishangpinEntity> wrapper = new EntityWrapper<ChaoshishangpinEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = chaoshishangpinService.selectCount(wrapper);
		return R.ok().put("count", count);
	}

	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ChaoshishangpinEntity chaoshishangpin, HttpServletRequest request,String pre){
        EntityWrapper<ChaoshishangpinEntity> ew = new EntityWrapper<ChaoshishangpinEntity>();
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
		PageUtils page = chaoshishangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chaoshishangpin), params), params));
        return R.ok().put("data", page);
    }


	/**
     * 协同算法（按用户购买推荐）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,ChaoshishangpinEntity chaoshishangpin, HttpServletRequest request){
    	String userId = request.getSession().getAttribute("userId").toString();
    	String goodtypeColumn = "shangpinleixing";
    	List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>().eq("userid", userId).eq("tablename", "chaoshishangpin").orderBy("addtime", false));
        List<String> goodtypes = new ArrayList<String>();
    	Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
    	List<ChaoshishangpinEntity> chaoshishangpinList = new ArrayList<ChaoshishangpinEntity>();
	//去重
    	List<OrdersEntity> ordersDist = new ArrayList<OrdersEntity>();
    	for(OrdersEntity o1 : orders) {
    		boolean addFlag = true;
    		for(OrdersEntity o2 : ordersDist) {
    			if(o1.getGoodid()==o2.getGoodid() || o1.getGoodtype().equals(o2.getGoodtype())) {
    				addFlag = false;
    				break;
    			}
    		}
    		if(addFlag) ordersDist.add(o1);
    	}
        if(ordersDist!=null && ordersDist.size()>0) {
        	for(OrdersEntity o : ordersDist) {
        		chaoshishangpinList.addAll(chaoshishangpinService.selectList(new EntityWrapper<ChaoshishangpinEntity>().eq(goodtypeColumn, o.getGoodtype())));
        	}
        }
    	EntityWrapper<ChaoshishangpinEntity> ew = new EntityWrapper<ChaoshishangpinEntity>();


        if(StringUtils.isNotEmpty(userId)){
            Long resultUserId=userShangPingService.selectShangpinidList(Long.parseLong(userId));
            if(resultUserId == null || resultUserId == Long.parseLong(userId)){
                params.put("userId",userId);
                params.put("sort", "user_click.count");
                params.put("order", "asc");
            }else {
                params.put("userId",resultUserId);
                params.put("sort", "user_click.count");
                params.put("order", "desc");
                List<Long> ids=userShangPingService.selectId(Long.parseLong(userId));
                ew.notIn("chaoshishangpin.id",ids);
            }
        }else {
            params.put("userId",null);
            params.put("sort", "id");
            params.put("order", "desc");
        }


        PageUtils page = chaoshishangpinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chaoshishangpin), params), params));
        List<ChaoshishangpinEntity> pageList = (List<ChaoshishangpinEntity>)page.getList();
        if(chaoshishangpinList.size()<limit) {
        	int toAddNum = (limit-chaoshishangpinList.size())<=pageList.size()?(limit-chaoshishangpinList.size()):pageList.size();
            for(ChaoshishangpinEntity o1 : pageList) {
                boolean addFlag = true;
                for(ChaoshishangpinEntity o2 : chaoshishangpinList) {
                    if(o1.getId().intValue()==o2.getId().intValue()) {
                        addFlag = false;
                        break;
                    }
                }
                if(addFlag) {
                    chaoshishangpinList.add(o1);
                    if(--toAddNum==0) break;
                }
            }
        } else if(chaoshishangpinList.size()>limit) {
            chaoshishangpinList = chaoshishangpinList.subList(0, limit);
        }
        page.setList(chaoshishangpinList);
	return R.ok().put("data", page);
    }


    /**
     * 统计用户点击
     * @return
     */
    @RequestMapping("/userClick")
    public R userClick(Long shangpingId, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        UserShangPing user = userShangPingService.selectOne(new EntityWrapper<UserShangPing>().eq("userid", userId).eq("shangpinid",shangpingId));
        if(user == null){
            UserShangPing userShangPing=new UserShangPing();
            userShangPing.setUserid(Long.parseLong(userId));
            userShangPing.setShangpinid(shangpingId);
            userShangPing.setCount(1);
            userShangPingService.insert(userShangPing);
        }else {
            user.setCount(user.getCount()+1);
            userShangPingService.updateById(user);
        }
        return R.ok();
    }


}
