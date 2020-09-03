
package com.yuxiang.common.util.tree;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuxiang.common.exception.YXException;
import com.yuxiang.common.util.helper.StringHelper;
import com.yuxiang.common.util.tree.builder.IComponentBuilder;
import com.yuxiang.common.util.tree.component.AbstractTreeComponent;
import com.yuxiang.common.util.tree.component.TreeComponent;
import com.yuxiang.common.util.tree.enums.TreeComponentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能说明: 树
 * 开发人员: kjp
 * 开发时间: 2018/8/30 <br>
 * 功能描述: 树<br>
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Tree extends AbstractTreeComponent implements ITree {

  public static final int MAX_LENGTH = 10000;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Map<String, TreeComponent> idMapData = new HashMap<>();

  @Override
  @JsonIgnore
  public TreeComponent getRoot() {
    if (this.getChildren().size() == 1) {
      return this.getChildren().get(0);
    }
    throw new YXException("根节点不唯一");
  }

  @Override
  public TreeComponent getTree(String id) {
    return idMapData.get(id);
  }

  @Override
  public List<TreeComponent> getChildrenTrees(String id) {
    List<TreeComponent> list = idMapData.values().stream()
        .filter(e -> Objects.equals(e.getPid(), id))
        .collect(Collectors.toList());
    return list;
  }

  @Override
  public TreeComponent getParentTree(String id) {
    TreeComponent component = idMapData.get(id);
    return idMapData.get(component.getPid());
  }

  @Override
  public TreeComponent getTree(String id, int offset) {
    TreeComponent component = idMapData.get(id);
    if (offset < 1) {
      return component;
    }
    for (int i = 0; i < offset; i++) {
      component = idMapData.get(component.getPid());
    }
    return component;
  }

  @Override
  @JsonIgnore
  public List<TreeComponent> getNodes() {
    return getNodes(this.getChildren());
  }

  @Override
  public List<TreeComponent> getNodes(Collection<TreeComponent> components) {
    List<TreeComponent> result = new ArrayList<>();
    return getNodes(components, result);
  }

  @Override
  public List<TreeComponent> getNodes(Collection<TreeComponent> components, List<TreeComponent> result) {
    if (components.size() == 0) {
      return result;
    }
    for (TreeComponent component : components) {
      result.add(component);
      if (component.getChildren().size() > 0) {
        getNodes(component.getChildren(), result);
      }
    }
    return result;
  }

  @Override
  public <K> List<K> getLeaves(Class<K> clazz) {
    return getLeaves(this.getChildren(), clazz);
  }

  @Override
  public <K> List<K> getLeaves(String id, Class<K> clazz) {
    return getLeaves(getChildrenTrees(id), clazz);
  }

  @Override
  public <K> List<K> getLeaves(Collection<TreeComponent> components, Class<K> clazz) {
    List<K> result = new ArrayList<>();
    return getLeaves(components, clazz, result);
  }

  @Override
  public <K> List<K> getLeaves(Collection<TreeComponent> components, Class<K> clazz, List<K> result) {
    if (components.size() == 0) {
      return result;
    }
    List<TreeComponent> branches = new ArrayList<>();
    for (TreeComponent component : components) {
      if (component.getType() == TreeComponentType.LEAF &&
          clazz.isAssignableFrom(component.getContent().getClass())) {
        result.add((K) component.getContent());
      } else {
        branches.addAll(component.getChildren());
      }
    }
    result.addAll(getLeaves(branches, clazz));
    return result;
  }

  @Override
  public List<Object> getContent(String id, int offset, List<Object> result) {
    return getContent(id, offset, result, Object.class);
  }

  @Override
  public <K> List<K> getContent(String id, int offset, List<K> result, Class<K> clazz) {
    TreeComponent component = idMapData.get(id);
    if (component.getType() == TreeComponentType.BRANCH || component.getType() == TreeComponentType.ROOT) {
      if (offset < 0) {
        getContent(component.getPid(), offset + 1, result, clazz);
      } else if (offset > 0) {
        for (TreeComponent child : component.getChildren()) {
          getContent(child.getId(), offset - 1, result, clazz);
        }
      }
    }
    if (clazz.isAssignableFrom(component.getContent().getClass())) {
      result.add((K) component.getContent());
    }
    return result;
  }

  @Override
  public List<Object> getChildren(String id) {
    return getChildren(id, Object.class);
  }

  @Override
  public <K> List<K> getChildren(String id, Class<K> clazz) {
    List<K> list = new ArrayList<>();
    list = getContent(id, MAX_LENGTH, list, clazz);
    list.remove(list.size() - 1);
    return list;
  }

  @Override
  public List<Object> getChildren(String id, int length) {
    return getChildren(id, length, Object.class);
  }

  @Override
  public <K> List<K> getChildren(String id, int length, Class<K> clazz) {
    List<K> list = new ArrayList<>();
    list = getContent(id, length, list, clazz);
    list.remove(list.size() - 1);
    return list;
  }

  @Override
  public List<Object> getChildrenAndSelf(String id, int length) {
    return getChildrenAndSelf(id, length, Object.class);
  }

  @Override
  public <K> List<K> getChildrenAndSelf(String id, int length, Class<K> clazz) {
    List<K> list = new ArrayList<>();
    list = getContent(id, length, list, clazz);
    return list;
  }

  @Override
  public <K> List<K> getChildrenAndSelf(String id, Class<K> clazz) {
    TreeComponent treeComponent = idMapData.get(id);
    return getChildrenAndSelf(id, treeComponent.getLength(), clazz);
  }

  @Override
  public <K> List<K> getChildrenAndSelf(Class<K> clazz) {
    String id = this.getChildren().get(0).getId();
    return getChildrenAndSelf(id, clazz);
  }

  @Override
  public List<Object> getParent(String id) {
    return getParent(id, Object.class);
  }

  @Override
  public <K> List<K> getParent(String id, Class<K> clazz) {
    List<K> list = new ArrayList<>();
    list = getContent(id, -1, list, clazz);
    list.remove(list.size() - 1);
    return list;
  }

  @Override
  public List<Object> getParents(String id, int length) {
    return getParents(id, length, Object.class);
  }

  @Override
  public <K> List<K> getParents(String id, int length, Class<K> clazz) {
    List<K> list = new ArrayList<>();
    list = getContent(id, -length, list, clazz);
    list.remove(list.size() - 1);
    return list;
  }

  @Override
  public List<Object> getParents(String id) {
    return getParents(id, Object.class);
  }

  @Override
  public <K> List<K> getParents(String id, Class<K> clazz) {
    List<K> list = new ArrayList<>();
    list = getContent(id, -MAX_LENGTH, list, clazz);
    list.remove(list.size() - 1);
    return list;
  }

  @Override
  public <K> Tree add(Map<IComponentBuilder<K>, Collection<K>> data) {
    // 将原始数据组装成树节点
    for (Map.Entry<IComponentBuilder<K>, Collection<K>> entry : data.entrySet()) {
      IComponentBuilder builder = entry.getKey();
      add(builder, entry.getValue());
    }
    return this;
  }

  @Override
  public <K> Tree add(IComponentBuilder<K> builder, Collection<K> children) {
    if (Objects.isNull(children) || children.size() == 0) {
      return this;
    }
    List<TreeComponent> components = new ArrayList<>();
    children.stream()
        .forEach(e -> {
          TreeComponent component = new TreeComponent();
          component.setId(builder.getId(e));
          component.setPid(builder.getPid(e));
          component.setName(builder.getName(e));
          component.setContent(e);
          components.add(component);
        });
    add(components);
    return this;
  }

  @Override
  public Tree add(Collection<TreeComponent> components) {
    long time = System.currentTimeMillis();
    components.forEach(component -> {
      idMapData.put(component.getId(), component);
      component.setType(TreeComponentType.LEAF);
    });
    for (TreeComponent component : components) {
      if (StringHelper.isBlank(component.getPid()) || !idMapData.containsKey(component.getPid())) {
        // 父节点不存在，即为根节点的情况
        // 添加到根节点集合
        component.setType(TreeComponentType.ROOT);
        children.add(component);
      } else {
        // 下挂到父节点
        idMapData.get(component.getPid()).add(component);
      }
      // 判断是否为根节点的父
      if (children.size() > 1) {
        for (int i = children.size() - 1; i >= 0; i--) {
          TreeComponent root = children.get(i);
          if (Objects.equals(root.getPid(), component.getId())) {
            // 原有根节点下挂到新的根，并变成分支节点
            component.add(root);
            root.setType(TreeComponentType.BRANCH);
            children.remove(root);
          }
        }
      }
    }
    time = System.currentTimeMillis() - time;
    if (time > 1000) {
      log.warn("树形构建完成，耗时{}ms", time);
    }
    return this;
  }

  @Override
  public boolean isEmpty() {
    return this.idMapData.isEmpty();
  }

  @Override
  public int size() {
    return this.idMapData.size();
  }

}
