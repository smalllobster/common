
package com.yuxiang.common.util.tree.component;

/**
 * 功能说明: 树节点
 * 开发人员: kjp
 * 开发时间: 2018/8/30 <br>
 * 功能描述: 树节点<br>
 */
public interface ITreeComponent {

  /**
   * 获取树最大高度
   *
   * @return
   */
  int getLength();

  /**
   * 获取树节点数量
   *
   * @return
   */
  int count();

  /**
   * 获取叶节点数量
   *
   * @return
   */
  int countLeaves();

}
