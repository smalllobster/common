package com.yuxiang.common.util.editor;

import org.apache.commons.lang3.StringEscapeUtils;

import java.beans.PropertyEditorSupport;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 10:37 <br>
 * 功能描述: <br>
 */
public class StringEditor extends PropertyEditorSupport {

  @Override
  public String getAsText() {
    Object value = getValue();
    return value != null ? value.toString() : "";
  }

  @Override
  public void setAsText(String text) {
    setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
  }

}