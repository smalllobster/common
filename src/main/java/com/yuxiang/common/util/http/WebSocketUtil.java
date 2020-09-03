
package com.yuxiang.common.util.http;

import javax.websocket.Session;
import java.io.IOException;

/**
 * 功能说明: websocket工具
 * 开发人员: kjp
 * 开发时间: 2018/7/23 <br>
 * 功能描述: websocket工具<br>
 */
public interface WebSocketUtil {

  /**
   * 广播消息
   *
   * @param message
   */
  void sendMessage(String message);

  /**
   * 发送消息
   *
   * @param message
   * @param userId
   */
  void sendMessage(String message, String userId);

  /**
   * 发送消息
   *
   * @param message
   * @param session
   */
  void sendMessage(String message, Session session) throws IOException;
}
