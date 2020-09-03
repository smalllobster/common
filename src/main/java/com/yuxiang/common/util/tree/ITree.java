
package com.yuxiang.common.util.tree;


import com.yuxiang.common.util.tree.builder.IComponentBuilder;
import com.yuxiang.common.util.tree.component.ITreeComponent;
import com.yuxiang.common.util.tree.component.TreeComponent;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 功能说明: 树形接口
 * 开发人员: kjp
 * 开发时间: 2018/8/30 <br>
 * 功能描述: 树形接口<br>
 */
public interface ITree extends ITreeComponent {

  /**
   * 获取根节点
   *
   * @return
   */
  TreeComponent getRoot();

  /**
   * 获取分支树
   */
  TreeComponent getTree(String id);

  /**
   * 获取子分支树
   *
   * @param id
   * @return
   */
  List<TreeComponent> getChildrenTrees(String id);

  /**
   * 获取父分支树
   *
   * @param id
   * @return
   */
  TreeComponent getParentTree(String id);

  /**
   * 根据偏移量获取父分支树
   *
   * @param id
   * @param offset
   * @return
   */
  TreeComponent getTree(String id, int offset);

  /**
   * 获取节点
   *
   * @return
   */
  List<TreeComponent> getNodes();

  /**
   * 获取节点
   *
   * @param components
   * @return
   */
  List<TreeComponent> getNodes(Collection<TreeComponent> components);

  /**
   * 获取节点
   *
   * @param components
   * @param result
   * @return
   */
  List<TreeComponent> getNodes(Collection<TreeComponent> components, List<TreeComponent> result);

  /**
   * 获取叶子节点
   *
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getLeaves(Class<K> clazz);

  /**
   * 获取叶子节点
   *
   * @param id
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getLeaves(String id, Class<K> clazz);

  /**
   * 获取叶子节点
   *
   * @param components
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getLeaves(Collection<TreeComponent> components, Class<K> clazz);

  /**
   * 获取叶子节点
   *
   * @param components
   * @param clazz
   * @param result
   * @param <K>
   * @return
   */
  <K> List<K> getLeaves(Collection<TreeComponent> components, Class<K> clazz, List<K> result);

  /**
   * 根据偏移量获取节点信息
   *
   * @param id
   * @param offset
   * @param result
   * @return
   */
  List<Object> getContent(String id, int offset, List<Object> result);

  /**
   * 根据偏移量获取节点信息
   *
   * @param id
   * @param offset
   * @param result
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getContent(String id, int offset, List<K> result, Class<K> clazz);

  /**
   * 获取直接子节点
   *
   * @param id
   * @return
   */
  List<Object> getChildren(String id);

  /**
   * 获取直接子节点
   *
   * @param id
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getChildren(String id, Class<K> clazz);

  /**
   * 获取子节点
   *
   * @param id
   * @param length
   * @return
   */
  List<Object> getChildren(String id, int length);

  /**
   * 获取子节点
   *
   * @param id
   * @param length
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getChildren(String id, int length, Class<K> clazz);

  /**
   * 获取子节点和自己
   *
   * @param id
   * @param length
   * @return
   */
  List<Object> getChildrenAndSelf(String id, int length);

  /**
   * 获取子节点和自己
   *
   * @param id
   * @param length
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getChildrenAndSelf(String id, int length, Class<K> clazz);

  /**
   * 获取子节点和自己
   *
   * @param id
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getChildrenAndSelf(String id, Class<K> clazz);

  /**
   * 获取子节点和自己
   *
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getChildrenAndSelf(Class<K> clazz);

  /**
   * 获取父节点信息
   *
   * @param id
   * @return
   */
  List<Object> getParent(String id);

  /**
   * 获取父节点信息
   *
   * @param id
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getParent(String id, Class<K> clazz);

  /**
   * 获取父节点信息
   *
   * @param id
   * @param length
   * @return
   */
  List<Object> getParents(String id, int length);

  /**
   * 获取父节点信息
   *
   * @param id
   * @param length
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getParents(String id, int length, Class<K> clazz);

  /**
   * 获取所有父节点信息
   *
   * @param id
   * @return
   */
  List<Object> getParents(String id);

  /**
   * 获取所有父节点信息
   *
   * @param id
   * @param clazz
   * @param <K>
   * @return
   */
  <K> List<K> getParents(String id, Class<K> clazz);

  /**
   * 添加节点数据
   */
  <K> Tree add(Map<IComponentBuilder<K>, Collection<K>> nodes);

  /**
   * 添加节点数据
   *
   * @param builder
   * @param nodes
   * @param <K>
   */
  <K> Tree add(IComponentBuilder<K> builder, Collection<K> nodes);

  /**
   * 添加节点数据
   *
   * @param components
   */
  Tree add(Collection<TreeComponent> components);

  /**
   * 树为空
   *
   * @return
   */
  boolean isEmpty();

  /**
   * 树节点数量
   *
   * @return
   */
  int size();

}
