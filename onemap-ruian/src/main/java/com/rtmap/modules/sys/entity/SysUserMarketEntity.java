

package com.rtmap.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户与角色对应关系
 *
 *
 */
@Data
@TableName("sys_user_market")
public class SysUserMarketEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@TableId
	private Long id;

	/**
	 * 用户ID
	 */
	private Long userId;

	/**
	 * 项目ID
	 */
	private String marketId;

	/**
	 * 当前登陆人是否有权限查看这个 market
	 */
	@TableField(exist=false)
	private String marketName;

	/**
	 *  market底下的mall 的list列表
	 */
	@TableField(exist=false)
	private List<PropertyEntity> propertyList;

	/**
	 * 当前登陆人是否有权限查看这个 market
	 */
	@TableField(exist=false)
	private int status;
}
