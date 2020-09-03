
package com.yuxiang.common.result;

import com.yuxiang.common.constant.Constant;
import com.yuxiang.common.enums.ResultEnum;
import com.yuxiang.common.exception.YXException;
import com.yuxiang.common.util.helper.StringHelper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/6/20 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Slf4j
public class ResultVOUtil {

    private static final String SET_PREFIX = "set";
    private static final String GET_PREFIX = "get";
    private static final String ASC = "asc";

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO success(Object object) {
        return success(object, ResultEnum.SUCCESS.getMsg());
    }

    public static ResultVO success(Object object, String msg) {
        return result(object, ResultEnum.SUCCESS.getCode(), msg);
    }

    public static ResultVO error() {
        return error(ResultEnum.ERROR.getMsg());
    }

    public static ResultVO error(String msg) {
        return result(null, ResultEnum.ERROR.getCode(), msg);
    }

    public static ResultVO error(ResultEnum resultEnum) {
        return result(null, resultEnum);
    }

    public static ResultVO result(Object object, ResultEnum resultEnum) {
        return result(object, resultEnum.getCode(), resultEnum.getMsg());
    }

    public static ResultVO result(Object object, int code, String msg) {
        try {
            object = formatSelectData(object);
        } catch (Exception e) {
            log.error("下拉框数据格式化异常：{}", e.getMessage());
            return ResultVO.builder()
                    .code(ResultEnum.ERROR.getCode())
                    .msg(ResultEnum.ERROR.getMsg())
                    .data(null)
                    .build();
        }
        ResultVO resultVO = ResultVO.builder()
                .code(code)
                .msg(msg)
                .data(object)
                .build();
        return resultVO;
    }

    private static Object formatSelectData(Object object) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (!Objects.isNull(request.getHeader(Constant.SELECT_REQUEST_FORMAT))) {
            try {
                // 获取域方法
                String keyFieldName = request.getHeader(Constant.SELECT_REQUEST_KEY).trim();
                String valueFieldName = request.getHeader(Constant.SELECT_REQUEST_VALUE).trim();
                String sortFieldName = request.getHeader(Constant.SELECT_REQUEST_SORT).trim();
                // 是否保留原始数据
                boolean raw = Objects.equals("true", request.getHeader(Constant.SELECT_REQUEST_RAW).trim());
                int index = sortFieldName.indexOf(" ");
                final int asc = index > -1 ? (Objects.equals(sortFieldName.substring(index + 1), ASC) ? 1 : -1) : 1;
                sortFieldName = index > -1 ? sortFieldName.substring(0, index) : sortFieldName;

                Collection collection = (Collection) object;
                // 排序及转换
                if (Objects.nonNull(collection) && !collection.isEmpty()) {
                    Class clazz = collection.iterator().next().getClass();
                    Method getKeyMethod = clazz.getMethod(GET_PREFIX + StringHelper.upperCaseFirstLetter(keyFieldName));
                    Method getValueMethod = clazz.getMethod(GET_PREFIX + StringHelper.upperCaseFirstLetter(valueFieldName));
                    Method getSortMethod = StringHelper.isBlank(sortFieldName) ? null : clazz.getMethod(GET_PREFIX + StringHelper.upperCaseFirstLetter(sortFieldName));
                    List<SelectData> selectData = (List<SelectData>) collection.stream()
                            .map(obj -> {
                                try {
                                    SelectData data = SelectData.builder()
                                            .key(getKeyMethod.invoke(obj))
                                            .value(getValueMethod.invoke(obj))
                                            .sort(Objects.isNull(getSortMethod) ? 0 : (Comparable) getSortMethod.invoke(obj))
                                            .build();
                                    if (raw) {
                                        data.setRaw(obj);
                                    }
                                    return data;
                                } catch (Exception e) {
                                    throw new YXException(e.getMessage());
                                }
                            })
                            .sorted((a, b) -> asc * ((SelectData) a).getSort().compareTo(((SelectData) b).getSort()))
                            .collect(Collectors.toList());
                    return selectData;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return object;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class SelectData {
        private Object key;
        private Object value;
        private Comparable sort;
        private Object raw;
    }
}
