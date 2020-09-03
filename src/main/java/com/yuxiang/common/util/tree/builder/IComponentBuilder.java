
package com.yuxiang.common.util.tree.builder;

/**
 * 功能说明: 节点封装接口
 * 开发人员: kjp
 * 开发时间: 2018/8/30 <br>
 * 功能描述: 节点封装接口<br>
 */
public interface IComponentBuilder<T> {

  /**
   * 获取编号
   *
   * @param t
   * @return
   */
  String getId(T t);

  /**
   * 获取父节点编号
   *
   * @param t
   * @return
   */
  String getPid(T t);

  /**
   * 获取节点名称
   *
   * @param t
   * @return
   */
  default String getName(T t) {
    return "";
  }

}
