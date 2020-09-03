
package com.yuxiang.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/11/30 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class ScriptUtil {

  /**
   * 执行js
   *
   * @param express
   * @param params
   * @param <T>
   * @param <E>
   * @return
   * @throws ScriptException
   */
  public static <T, E> E eval(String express, Map<String, T> params) throws ScriptException {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("js");
    if (params == null) {
      params = new HashMap<>();
    }
    Iterator<Map.Entry<String, T>> iter = params.entrySet().iterator();
    Map.Entry<String, T> entry = null;
    while (iter.hasNext()) {
      entry = iter.next();
      engine.put(entry.getKey(), entry.getValue());
    }
    E result = null;
    try {
      result = (E) engine.eval(express);
    } catch (ScriptException e) {
    }
    return result;
  }

  /**
   * 执行表达式
   *
   * @param express
   * @param params
   * @param <T>
   * @return
   */
  public static <T> Boolean evalBoolean(String express, Map<String, T> params) {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("js");
    if (params == null) {
      params = new HashMap<>();
    }
    Iterator<Map.Entry<String, T>> iter = params.entrySet().iterator();
    Map.Entry<String, T> entry = null;
    while (iter.hasNext()) {
      entry = iter.next();
      engine.put(entry.getKey(), entry.getValue());
    }
    Boolean result = null;
    try {
      result = (Boolean) engine.eval(express);
    } catch (ScriptException e) {
      result = false;
    }
    return result;
  }

  public static void main(String[] args) {
  }

}
