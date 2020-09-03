
package com.yuxiang.common.util.tree.component;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.yuxiang.common.util.tree.enums.TreeComponentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能说明: 树节点
 * 开发人员: kjp
 * 开发时间: 2018/8/30 <br>
 * 功能描述: 树节点<br>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "pid", "length", "type", "content", "nodes"})
public class TreeComponent extends AbstractTreeComponent {

  private String id;
  private String pid;
  private String name;
  private Object content;
  private TreeComponentType type;

  public void add(TreeComponent node) {
    if (this.type == TreeComponentType.LEAF) {
      this.type = TreeComponentType.BRANCH;
    }
    children.add(node);
  }

}
