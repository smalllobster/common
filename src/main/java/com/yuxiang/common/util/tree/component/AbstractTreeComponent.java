
package com.yuxiang.common.util.tree.component;

import com.yuxiang.common.util.tree.enums.TreeComponentType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明: 树抽象类
 * 开发人员: kjp
 * 开发时间: 2018/8/30 <br>
 * 功能描述: 树抽象类<br>
 */
@Data
public abstract class AbstractTreeComponent implements ITreeComponent {

  protected List<TreeComponent> children = new ArrayList<>();

  @Override
  public int getLength() {
    int length = 0;
    int temp = 0;
    for (TreeComponent node : children) {
      temp = (node.getType() == TreeComponentType.LEAF ? 1 : node.getLength());
      length = length < temp ? temp : length;
    }
    return length + 1;
  }

  @Override
  public int count() {
    int count = 0;
    for (TreeComponent node : children) {
      count += (node.getType() == TreeComponentType.LEAF ? 1 : node.count());
    }
    return count + 1;
  }

  @Override
  public int countLeaves() {
    int count = 0;
    for (TreeComponent node : children) {
      count += (node.getType() == TreeComponentType.LEAF ? 1 : node.countLeaves());
    }
    return count;
  }
}
