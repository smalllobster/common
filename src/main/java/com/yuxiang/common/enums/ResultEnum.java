
package com.yuxiang.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 功能说明: 返回错误结果集
 * 开发人员: kjp
 * 开发时间: 2020/09/03 <br>
 * 功能描述: 返回错误结果集<br>
 */
@Getter
@AllArgsConstructor
public enum ResultEnum implements CodeEnum {
  SUCCESS(200, "成功"),
  CACHECOMPANY(201, "切换公司"),
  ERROR(101, "服务器错误"),
  MISSING_PARAM_ERROR(102, "缺少必要参数"),
  PARAM_ERROR(103, "参数校验不通过"),
  SYSTEM_BUSY(104, "系统繁忙，请稍后再试"),
  SYSTEM_SINGLE_LOGIN_BUSY(105, "此账号已在他处登录，请退出后重新登录"),
  SYSTEM_NOT_PERMITED(106, "缺少权限"),
  NODE_NOT_EXIST(107, "节点不存在或已经启动"),
  NO_FORM_TOKEN(110, "表单Token不能为空!"),
  NO_USE_FORM_TOKEN(111, "请勿重复提交!"),

  WRONG_VALID_CODE(1001, "图形验证码错误"),
  EXPIRED_VALID_CODE(1002, "图形验证码过期"),
  EXPIRED_PHONE_VALID_CODE(1003, "短信验证码过期"),
  FILE_PARSE_ERROR(1004, "文件解析失败，请确定文件格式为docx"),
  FILE_GENERATE_ERROR(1005, "文件生成失败"),
  EMAIL_NOT_CONFIGED(1006, "发送邮箱未配置"),
  FILE_INVALID(1007, "文件无效"),
  FILE_NOT_EXIST(1008, "文件不存在"),

  WORKFLOW_START_ERROR(2001, "工作流发起失败"),
  WORKFLOW_CANNOT_START(2002, "无发起权限"),
  WORKFLOW_TASK_CANNOT_EXECUTE(2003, "无执行该任务的权限"),
  WORKFLOW_PROCESS_NOT_FOUND(2004, "流程不存在"),
  WORKFLOW_OBJECT_NOT_FOUND(2005, "未找到工作流相关对象"),
  WORKFLOW_TASK_CANNOT_ASSIGN(2006, "任务不存在或已被签收"),
  WORKFLOW_TASK_COMPLETE_ERROR(2007, "任务提交失败"),
  WORKFLOW_TASK_NOT_FOUND(2008, "找不到该任务"),
  WORKFLOW_CAN_NOT_ACCESS(2009, "无权限进行该操作"),

  QUESTIONNAIRE_NOT_FOUND(3001, "找不到问卷"),
  QUESTION_NOT_FOUND(3002, "找不到问题"),
  ANSWER_NOT_FOUND(3003, "找不到答案"),
  ANSWER_NOT_ASSIGN(3004, "未指定答案"),

  WRONG_AUTHENTICATION(4001, "长时间未操作，请重新登录"),
  USER_AUTHENTICATION_ERR(4002, "用户不存在或密码错误"),
  ACCOUNT_EXPIRED_ERR(4003, "账户过期"),
  ACCOUNT_LOCKED(4004, "账户锁定"),
  ACCOUNT_DISABLED(4005, "账户不可用"),
  CREDENTIALS_EXPIRED(4006, "证书过期"),
  REALNAME_NOT_IDENTIFIED(4007, "请先完成实名认证");

  private Integer code;
  private String msg;
}
