
package com.yuxiang.common.util.editor;

import com.yuxiang.common.util.helper.DateHelper;

import java.beans.PropertyEditorSupport;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/6/5 <br>
 * 功能描述: <br>
 */
public class DateEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) {
    setValue(DateHelper.parseDate(text));
  }
}
