package com.yuxiang.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum DateEnum {
  YYYYMM("yyyyMM"),//例：201811
  YYYYMMDD("yyyyMMdd"),//例：20180626
  YYYYMMDDHHMMSS("yyyyMMddHHmmss"),//例：20180626093422

  MM_DD("MM-dd"),//例：01-01
  YYYY_MM("yyyy-MM"),//例：2018-06
  YYYY_MM_DD("yyyy-MM-dd"),//例：2018-06-26
  YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),//例：2018-06-26 09:35:01
  YYYY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS");//例：2018-06-26 09:35:40.115

  private String formatCode;

}
