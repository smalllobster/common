
package com.yuxiang.common.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * 功能说明: 基础分页工具，适用于实现了Collections接口的数据集的分页
 * 开发人员: kjp
 * 开发时间: 2019/5/27 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */

@Slf4j
public class BasePageUtil {

  public static void main(String[] args){
    List list = new ArrayList();
    for (int i = 1; i <= 400; i++) {
      list.add(new Integer(i));
    }
    PageData t = getInstance().getPageData(list, 5);
    log.info(t.toString());
  }

  private static BasePageUtil instance;

  public static BasePageUtil getInstance() {
    if (instance == null) {
      synchronized (BasePageUtil.class) {
        if (instance == null) {
          instance = new BasePageUtil();
        }
      }
    }
    return instance;
  }

  public <T extends Collection> PageData<T> getPageData(T datas, int pageNo, int pageSize) {
    int size = datas.size();
    int pages = (int) Math.ceil(((double) size) / pageSize);
    int rPageNo = pageNo > pages ? pages : pageNo;

    PageData ans = getInstance().new PageData();

    T records = null;
    try {
      records = (T) datas.getClass().newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    if (Objects.nonNull(records)) {
      Iterator iterator = datas.iterator();
      int i = 0, s = ((rPageNo - 1) > 0 ? (rPageNo - 1) : 0 ) * pageSize + 1, e = rPageNo * pageSize;
      while (iterator.hasNext()) {
        i++;
        Object o = iterator.next();
        if (i >= s) records.add(o);
        if (i >= e) break;
      }
    }
    ans.setTotal(size);
    ans.setCurrent(rPageNo);
    ans.setPages(pages);
    ans.setSize(pageSize);
    ans.setRecords(records);
    return ans;
  }

  /**
   * 返回第几页，每页20条数据
   *
   * @param datas
   * @param pageNo
   * @param <T>
   * @return
   */
  public <T extends Collection> PageData<T> getPageData(T datas, int pageNo) {
    return getPageData(datas, pageNo, 20);
  }

  @Data
  @NoArgsConstructor
  public class PageData<T extends Collection> {
    /**
     * 总数
     */
    private int total;

    /**
     * 每页显示条数，默认 10
     */
    private int size = 20;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 当前页
     */
    private int current = 1;

    /**
     * 记录
     */
    private T records;
  }
}
