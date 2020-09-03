
package com.yuxiang.common.util.tree.util;


import com.yuxiang.common.util.tree.Tree;
import com.yuxiang.common.util.tree.component.TreeComponent;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能说明: 树形静态工具
 * 开发人员: kjp
 * 开发时间: 2018/9/17 <br>
 * 功能描述: 树形静态工具<br>
 */
public final class TreeUtil {

  private static final String TREE_ROOT = "treeRoot";

  /**
   * 树合成
   *
   * @param trees
   * @return
   */
  public static final Tree combine(List<Tree> trees) {
    if (trees.size() == 1) {
      return trees.get(0);
    }
    List<TreeComponent> treeComponents = trees.stream()
        .flatMap(tree -> tree.getIdMapData().values().stream())
        .collect(Collectors.toList());
    treeComponents.forEach(component -> component.setChildren(new ArrayList<>()));
    Tree tree = new Tree();
    tree.add(treeComponents);
    return tree;
  }

  /**
   * 添加根节点
   *
   * @param components
   * @param rootName
   * @return
   */
  public static final Tree addRoot(Collection<TreeComponent> components, String rootName) {
    Tree tree = rebuildTree(components);
    return addRoot(tree, rootName);
  }

  public static final Tree addRoot(Tree tree, String rootName) {
    Map<String, Object> rootContent = new HashMap<>();
    rootContent.put("name", rootName);
    rootContent.put("id", TREE_ROOT);
    TreeComponent root = TreeComponent.builder()
        .id(TREE_ROOT)
        .name(rootName)
        .content(rootContent)
        .build();
    return addRoot(tree, root);
  }

  public static final Tree addRoot(Collection<TreeComponent> components, TreeComponent root) {
    Tree tree = rebuildTree(components);
    return addRoot(tree, root);
  }

  public static final Tree addRoot(Tree tree, TreeComponent root) {
    List<TreeComponent> components = tree.getChildren();
    if (components.size() > 0) {
      components.forEach(e -> e.setPid(root.getId()));
    }

    tree.add(Arrays.asList(root));
    return tree;
  }

  private static final Tree rebuildTree(Collection<TreeComponent> components) {
    Tree tree = new Tree();
    if (Objects.nonNull(components) && components.size() > 0) {
      Set<TreeComponent> allNodes = new HashSet<>();
      while (true) {
        allNodes.addAll(components);
        components = components.stream()
            .flatMap(e -> e.getChildren().stream())
            .collect(Collectors.toList());
        if (components.size() == 0) {
          break;
        }
      }
      allNodes.forEach(e -> e.setChildren(new ArrayList<>()));

      tree.add(allNodes);
    }
    return tree;
  }

}
