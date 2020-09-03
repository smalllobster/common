
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

  WRONG_AUTHENTICATION(10001, "长时间未操作，请重新登录"),
  USER_AUTHENTICATION_ERR(10002, "用户不存在或密码错误"),
  ACCOUNT_EXPIRED_ERR(10003, "账户过期"),
  ACCOUNT_LOCKED(10004, "账户锁定"),
  ACCOUNT_DISABLED(10005, "账户不可用"),
  CREDENTIALS_EXPIRED(10006, "证书过期"),
  REALNAME_NOT_IDENTIFIED(10007, "请先完成实名认证"),

  MOBILE_PHONE_NOT_IDENTIFIED(10008, "请先完成联系方式认证"),
  PROTOCOL_NOT_IDENTIFIED(10009, "请先签署入会协议"),
  MOBILE_PHONE_BEEN_USED(10010, "联系方式已被使用"),

  DOCS_DOCUMENT_NOT_FOUND(15000, "文档模板不存在"),

  TENDER_ERROR(21000, "签约失败"),
  LESS_THAN_LOWEST_ACCOUNT(20001, "金额小于起投金额"),
  LESS_THAN_LOWEST_SINGLE_LIMIT(20002, "金额小于单笔最低限额"),
  MORE_THAN_MOST_SINGLE_LIMIT(20003, "金额超过单笔最大限额"),
  MORE_THAN_MOST_AMOUNT(20004, "签约金额超过累计限额"),
  MORE_THAN_ACCOUNT_WAIT(20005, "金额超过剩余额度"),
  STATUS_INVALID(20006, "该产品当前不可签约"),
  INCREASE_AMOUNT_INVALID(20007, "不满足金额递增规则"),
  MORE_THAN_MAX_TENDER_USERS(20008, "超过最大签约人数"),
  UNDEFINED_CALCULATOR(20009, "未定义的计息方式"),
  NO_SUCH_PRODUCT(200010, "未找到相关产品信息"),
  PROTOCOL_FILE_CREATING(200011, "协议生成中"),
  NO_SUCH_TENDER(200012, "找不到该签约记录"),
  ACCOUNT_WAIT_INVALID(200013, "不满足签约后剩余规模大于起购金额"),
  NO_NATURAL_HALF_YEAR(200014, "未配置自然半年还款日"),
  NO_NATURAL_QUARTERLY(200015, "未配置自然季度还款日"),
  NO_RISK_APPRAISAL(200016, "未进行风险评估"),

  ESIGN_INIT_PROJECT_FAIL(150000, "e签宝初始化失败"),
  ESIGN_ADD_ACCOUNT_FAIL(150001, "e签宝开户失败"),
  ESIGN_ADD_SEAL_FAIL(150002, "e签宝建章失败"),
  ESIGN_SIGN_SEAL_FAIL(150003, "签章失败"),
  ESIGN_DELETE_ACCOUNT_FAIL(150004, "e签宝销户失败"),

  CBHB_RECHARGE_ERR(300001, "渤海充值异常"),
  CBHB_TRANS_TYPE_ERR(300002, "交易类型异常"),
  CBHB_SIGN_ERR(300003, "渤海签约失败"),
  CBHB_QUERY_ERR(400001, "渤海查询失败"),
  CBHB_STOE_DATE_ERR(500001, "参数不合规，开始日期大于结束日期");

  private Integer code;
  private String msg;
}
