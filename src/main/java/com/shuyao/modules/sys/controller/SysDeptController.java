package com.shuyao.modules.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qiniu.util.StringUtils;
import com.shuyao.common.utils.Constant;
import com.shuyao.common.utils.RedisUtils;
import com.shuyao.common.utils.Resp;
import com.shuyao.modules.sys.entity.SysDeptEntity;
import com.shuyao.modules.sys.service.SysDeptService;


/**
 * 部门管理
 * 
 * @author shuyao
 * @email shuyao@gmail.com
 * @date 2017-09-20
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController {
	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:dept:list")
	public List<SysDeptEntity> list(){
		Map<String, Object> map = new HashMap<>();
		//如果不是超级管理员，则只能查询本部门及子部门数据
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("deptFilter", sysDeptService.getSubDeptIdList(getDeptId(),true));
		}
		List<SysDeptEntity> deptList = sysDeptService.queryList(map);

		return deptList;
	}

	/**
	 * 选择部门(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:dept:select")
	public Resp select(){
		Map<String, Object> map = new HashMap<>();
		//如果不是超级管理员，则只能查询本部门及子部门数据
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("deptFilter", sysDeptService.getSubDeptIdList(getDeptId(),true));
		}
		List<SysDeptEntity> deptList = sysDeptService.queryList(map);

		//添加一级部门
		if(getUserId() == Constant.SUPER_ADMIN){
			SysDeptEntity root = new SysDeptEntity();
			root.setDeptId(0L);
			root.setName("一级部门");
			root.setParentId(-1L);
			root.setOpen(true);
			deptList.add(root);
		}

		return Resp.ok().put("deptList", deptList);
	}
	
	/**
	 * 选择部门
	 */
	@RequestMapping("/select/{menu}")
	public Resp selectByMenu(@PathVariable("menu") String menu){
		Map<String, Object> map = new HashMap<>();
		//如果不是超级管理员，则只能查询本部门及子部门数据
 		List<Map<String,Object>> deptList = new ArrayList<>();

		if(getUserId() == Constant.SUPER_ADMIN){
			map.put("deptFilter", sysDeptService.getSubDeptIdList(getDeptId(),true));
	 	} else {
			//查询用户所有的roles所在的部门
	 		String roleFilter = StringUtils.join(getUser().getRoleIdList(), ",");
	 		if ("tanzhen".equals(menu)) {
	 			menu = "37";	
	 		}
	 		map.put("menu", menu);
	 		if (StringUtils.isNullOrEmpty(roleFilter)) {
	 			roleFilter = null;
	 		}
	 		map.put("roleFilter", roleFilter);
		}
		List<SysDeptEntity> list = sysDeptService.getSubDeptIdByMenu(map);
 		List<SysDeptEntity> list4s = new ArrayList<SysDeptEntity>();
 		final Map<Long,String> parentMap = new HashMap<>();
 		list.forEach(sysDeptEntity ->{
 			if (sysDeptEntity.getDeptType().equals("C")) {
 				parentMap.putIfAbsent(sysDeptEntity.getDeptId(), sysDeptEntity.getParentName() +"--"+ sysDeptEntity.getName());
 			}if (sysDeptEntity.getDeptType().equals("D")) {
 				parentMap.putIfAbsent(sysDeptEntity.getDeptId(), sysDeptEntity.getName());
 			}if (sysDeptEntity.getDeptType().equals("S")) {
 				list4s.add(sysDeptEntity);
 			}
 		});
 		
 		list4s.stream()
 			.collect(Collectors.groupingBy(SysDeptEntity:: getParentId))
 			.forEach((parentId,parentList) -> {
 				Map<String,Object> tempMap = new HashMap<>();
 				tempMap.put("parentId", parentId);
 				tempMap.put("parentName",parentMap.get(parentId));
 				tempMap.put("list", parentList);
 				deptList.add(tempMap);
 			});
		return Resp.ok().put("deptList", deptList);
	}

	/**
	 * 上级部门Id(管理员则为0)
	 */
	@RequestMapping("/info")
	@RequiresPermissions("sys:dept:list")
	public Resp info(){
		long deptId = 0;
		if(getUserId() != Constant.SUPER_ADMIN){
			SysDeptEntity dept = sysDeptService.queryObject(getDeptId());
			deptId = dept.getParentId();
		}

		return Resp.ok().put("deptId", deptId);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{deptId}")
	@RequiresPermissions("sys:dept:info")
	public Resp info(@PathVariable("deptId") Long deptId){
		SysDeptEntity dept = sysDeptService.queryObject(deptId);
		
		return Resp.ok().put("dept", dept);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:dept:save")
	public Resp save(@RequestBody SysDeptEntity dept){
		sysDeptService.save(dept);
		
		return Resp.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:update")
	public Resp update(@RequestBody SysDeptEntity dept){
		sysDeptService.update(dept);
		
		return Resp.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:dept:delete")
	public Resp delete(long deptId){
		//判断是否有子部门
		List<Long> deptList = sysDeptService.queryDetpIdList(deptId);
		if(deptList.size() > 0){
			return Resp.erroresp("请先删除子部门");
		}

		sysDeptService.delete(deptId);
		
		return Resp.ok();
	}
	
}
