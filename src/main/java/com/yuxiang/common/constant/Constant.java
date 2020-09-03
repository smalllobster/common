
package com.yuxiang.common.constant;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/6/25 <br>
 * 功能描述: 常量<br>
 */
public final class Constant {
  /**
   * excel导出单sheet最大值
   */
  public static final Integer MAX_NUM = 5000;
  /**
   * 产品默认募集人数200
   */
  public static final Integer RECRUITMENT = 200;
  /**
   * 公共公司权限
   */
  public static final String ALL_COMPANY_NO = "all";
  /**
   * 平台用户公司号
   */
  public static final String PLATFORM_COMPANY_NO = "platform";
  /**
   * 缓存的公司号
   */
  public static final String CACHED_COMPANY_NO = "cached_company_no";
  /**
   * 缓存的权限：标识
   */
  public static final String CACHED_PERMISSION = "cached_permission";
  /**
   * 缓存的权限：标识+名称+备注
   */
  public static final String NEW_CACHED_PERMISSION = "new_cached_permission";
  /**
   * 用户唯一标识，用于区分多个地方登录的同一个用户
   */
  public static final String USER_UNIQUE_ID = "user_unique_id";
  /**
   * 缓存公司为空
   */
  public static final String NONE_COMPANY = "null";
  /**
   * 平台表前缀
   */
  public static final String SYS_TABLE_PREFIX = "sys_";
  /**
   * 数据库表名前缀
   */
  public static final String TABLE_PREFIX = "gns_";
  /**
   * tpd数据库表前缀
   */
  public static final String TABLE_TPD_PREFIX = "tpd_";
  /**
   * 表单表格配置
   */
  public static final String PTM_PREFIX = "ptm_";
  /**
   * 工作流数据库前缀
   */
  public static final String WORKFLOW_TABLE_PREFIX = "wfl_";
  /**
   * 资产管理数据库前缀
   */
  public static final String APM_TABLE_PREFIX = "apm_";

  public static final String STOCK_TABLE_PREFIX = "stock_";

  public static final String NQ_TABLE_PREFIX = "NQ_";
  /**
   * 消息中心数据库前缀
   */
  public static final String MSG_CENTER_TABLE_PREFIX = "msc_";
  /**
   * 微信公众号相关数据库表前缀
   */
  public static final String WX_MP_TABLE_PREFIX = "wxmp_";
  /**
   * 在线文档表前缀
   */
  public static final String DOCS_TABLE_PREFIX = "doc_";
  /**
   * qsm 数据库表前缀
   */
  public static final String TABLE_QSM_PREFIX = "qsm_";
  /**
   * ice 数据库表前缀
   */
  public static final String TABLE_ICE_PREFIX = "ice_";
  /**
   * 定时任务 数据库表前缀
   */
  public static final String TABLE_JOB_PREFIX = "job_";
  /**
   * 反洗钱 数据库表前缀
   */
  public static final String TABLE_MLS_PREFIX = "mls_";
  /**
   * 添金宝数据库表前缀
   */
  public static final String TABLE_TJB_PREFIX = "tjb_";
  /**
   * 分销系统
   */
  public static final String TABLE_GEC_PREFIX = "gec_";
  /**
   * 线下理财销售数据库表前缀
   */
  public static final String TABLE_UIF_PREFIX = "uif_";
  /**
   * 守护项目数据库表前缀
   */
  public static final String TABLE_LRK_PREFIX = "lrk_";
  /**
   * 守护公司数据库表前缀
   */
  public static final String TABLE_BUS_PREFIX = "bus_";
  /**
   * CCS表前缀
   */
  public static final String CCS_TABLE_PREFIX = "ccs_";
  /**
   * ROL表前缀
   */
  public static final String ROL_TABLE_PREFIX = "rol_";
  /**
   * 企业监控数据库表前缀
   */
  public static final String RMP_TABLE_PREFIX = "rmp_";

  public static final String RMP_ENTERPRISE_INDEX_TABLE_PREFIX = "eindex_";

  public static final String RMP_ENTERPRISE_BASE_TABLE_PREFIX = "ebase_";

  public static final String RMP_ENTERPRISE_JUDICIAL_TABLE_PREFIX = "ejudicial_";

  public static final String RMP_ENTERPRISE_OTHER_TABLE_PREFIX = "eother_";

  /**
   * 企业风控
   */
  public static final String T_TABLE_PREFIX = "t_";
  /**
   * dict对应的翻译信息
   */
  public static final String DICT_FIELD_MAP = "dict-field-map";
  /**
   * 常用格式化对应的翻译缓存key
   */
  public static final String FORMAT_FIELD_MAP = "format-field-map";
  /**
   * 常用格式化对应的字典缓存
   */
  public static final String FORMAT_VALUE_MAP = "format-value-map";
  /**
   * 选择下拉框标识
   */
  public static final String SELECT_REQUEST_FORMAT = "format";
  /**
   * 选择下拉框值
   */
  public static final String SELECT_REQUEST_KEY = "rkey";
  /**
   * 选择下拉框显示名称
   */
  public static final String SELECT_REQUEST_VALUE = "rvalue";
  /**
   * 选择下拉框排序
   */
  public static final String SELECT_REQUEST_SORT = "rsort";
  /**
   * 选择下拉框保留原始数据
   */
  public static final String SELECT_REQUEST_RAW = "raw";
  /**
   * 类名与成员变量之间的分隔符
   */
  public static final String CLASS_FIELD_SPLIT_SYMBOL = "#";
  /**
   * 字符串分隔符
   */
  public static final String STRING_SPLIT_DOLLAR = "$";//字符串分隔符
  /**
   * 可用
   */
  public static final String ENABLED_USEABLE = "1";
  /**
   * 停用
   */
  public static final String ENABLED_DIS_USERABLE = "0";
  /**
   * 注销
   */
  public static final Integer CANCLE_STATUS_YES = 1;
  /**
   * 正常
   */
  public static final Integer CANCLE_STATUS_NO = 0;
  /**
   * 菜单
   */
  public static final String MENU_TYPE = "1";
  /**
   * 按钮
   */
  public static final String BUTTON_TYPE = "2";
  /**
   * 删除标志 未删除
   */
  public static final String DEL_FLG_NO = "0";
  /**
   * 已删除
   */
  public static final String DEL_FLG_YES = "1";
  /**
   * 文件未发现
   */
  public static final String FILE_NOT_FIND = "%s 文件不存在!";
  /**
   * 下载文件路径
   */
  public static final String FILE_PATH = "download";
  /**
   * Buff Size
   */
  public static final int BUF_SIZE = 1024 * 100;
  /**
   * 默认编码
   */
  public static final String DEFAULT_ENCODING = "utf8";
  /**
   * 入会协议
   */
  public static final String USER_PROTOCOL = "ruhuishenqing_100000000000000001";
  /**
   * 交换机
   */
  public static final String EXCHANGE = "exchange";
  /**
   * topic前缀
   */
  public static final String TOPIC_PREFIX = "topic.";
  /**
   * redis过期延迟
   */
  public static final long ADDITIONAL_TIME = 1;
  /**
   * 系统缓存redis前缀
   */
  public static final String CACHE_PREFIX = "cache";
  /**
   * 微信openid关键字
   */
  public static final String WX_OPEN_ID_KEYWORD = "{{openId}}";
  /**
   * 表单提交token
   */
  public static final String FORM_TOKEN = "form-token:";

  /**
   * 表单提交token的key
   */
  public static final String FORM_TOKEN_KEY = "formtoken";

}
