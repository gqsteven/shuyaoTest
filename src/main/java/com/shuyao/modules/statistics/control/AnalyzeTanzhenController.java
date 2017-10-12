package com.shuyao.modules.statistics.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shuyao.common.utils.DateUtils;
import com.shuyao.common.utils.PageUtils;
import com.shuyao.common.utils.Query;
import com.shuyao.common.utils.Resp;
import com.shuyao.modules.statistics.entity.AnalyzeTanzhenEntity;
import com.shuyao.modules.statistics.service.AnalyzeTanzhenService;
import com.shuyao.modules.sys.controller.AbstractController;



/**
 * 统计分析
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-07 00:29:39
 */
@RestController
@RequestMapping("/analyzetanzhen")
public class AnalyzeTanzhenController extends AbstractController{
	@Autowired
	private AnalyzeTanzhenService analyzeTanzhenService;
	
	/**
	 * 查询
	 */
	@RequestMapping("/query")
	public Resp query(@RequestParam Map<String, Object> params){
		//查询列表数据
        //Query query = new Query(params);
		//将json数组转化为java数据 
		if (params != null && params.get("comName") != null) {
			
			Gson gson = new Gson();
			List<String> comList = gson.fromJson((String)params.get("comName"), new TypeToken<List<String>>(){}.getType());
			
		    //List comlist = JSONArray.parseArray((String)params.get("comName"), String.class);
		    params.put("comName", comList.toArray());
		}
		
		List<Map<String, Object>> list = analyzeTanzhenService.queryByCon(params);
		
		int i = 2;
		List listKey = new ArrayList();
		listKey.add("宝信");
		listKey.add("宝马");
		List list1 = new ArrayList();		
		List list2 = new ArrayList();
		
		if (list != null && list.size() !=0) {
			for (Map map: list) {
				if(listKey.get(0).equals(map.get("name"))) {
					list1.add(map);
				} else {
					list2.add(map);
				}
			}
		}
		
		List<String> monList = new ArrayList<String>();
		if (params != null && StringUtils.isNotBlank((String)params.get("startTime")) ) {
			monList = DateUtils.getMonthBetween((String)params.get("startTime"), "");
		} else {
			monList = DateUtils.getMonthBetween("", "");
		}
		List<String> listVal1 = new ArrayList();		
		List<String> listVal2 = new ArrayList();
		List series = new ArrayList();
		int j1 = 0;
		int s1 = list1.size();
		Map map1;
		int j2 = 0;
		int s2 = list2.size();
		Map map2;
		for (String str: monList) {
			if (j1 < s1) {
				map1 = (Map)list1.get(j1);
				if (str.equals(map1.get("time"))) {
					listVal1.add(map1.get("count").toString());
					j1++;
				}else {
					listVal1.add("0");
				}
			} else {
				listVal1.add("0");
			}
			if (j2 < s2) {
				map2 = (Map)list2.get(j2);
				if (str.equals(map2.get("time"))) {
					listVal2.add(map2.get("count").toString());
					j2++;
				}else {
					listVal2.add("0");
				}
			} else {
				listVal2.add("0");
			}
		}
		series.add(listVal1);
		series.add(listVal2);
		Resp r = Resp.ok();
		r.put("xKey", monList);
		r.put("bKey", listKey);
		r.put("series", series);
		return r;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("analyzetanzhen:list")
	public Resp list(@RequestParam Map<String, Object> params){
	   if (params != null && params.get("comName") != null) {
		   Gson gson = new Gson();
		   List<String> comList = gson.fromJson((String)params.get("comName"), new TypeToken<List<String>>(){}.getType());
		   params.put("comName", comList.toArray());
		}
		//查询列表数据
        Query query = new Query(params);

		List<AnalyzeTanzhenEntity> analyzeTanzhenList = analyzeTanzhenService.queryList(query);
		int total = analyzeTanzhenService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(analyzeTanzhenList, total, query.getLimit(), query.getPage());
		
		return Resp.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("analyzetanzhen:info")
	public Resp info(@PathVariable("id") Long id){
		AnalyzeTanzhenEntity analyzeTanzhen = analyzeTanzhenService.queryObject(id);
		
		return Resp.ok().put("analyzeTanzhen", analyzeTanzhen);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("analyzetanzhen:save")
	public Resp save(@RequestBody AnalyzeTanzhenEntity analyzeTanzhen){
		analyzeTanzhenService.save(analyzeTanzhen);
		
		return Resp.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("analyzetanzhen:update")
	public Resp update(@RequestBody AnalyzeTanzhenEntity analyzeTanzhen){
		analyzeTanzhenService.update(analyzeTanzhen);
		
		return Resp.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("analyzetanzhen:delete")
	public Resp delete(@RequestBody Long[] ids){
		analyzeTanzhenService.deleteBatch(ids);
		
		return Resp.ok();
	}
	
}
