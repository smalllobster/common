
package com.yuxiang.common.util;

import com.yuxiang.common.constant.Constant;
import org.aspectj.lang.JoinPoint;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 功能说明: 注解相关工具类
 * 开发人员: kjp
 * 开发时间: 2018/6/28 <br>
 * 功能描述: 注解相关工具类<br>
 */
public class AnnotationUtil {

  /**
   * 获取某个包下被clazz注解的类
   *
   * @param packageName
   * @param clazz
   * @return
   */
  public static Set<Class> getAnnotatedClasses(String packageName, Class clazz) {
    Reflections reflections = new Reflections(packageName);
    Set<Class> classSet = reflections.getTypesAnnotatedWith(clazz);
    return classSet;
  }

  /**
   * 获取某个类下面被annotation注解的方法
   *
   * @param clazz
   * @param annotation
   * @return
   */
  public static List<Method> getAnnotatedMethods(Class clazz, Class annotation) {
    Method[] methods = clazz.getDeclaredMethods();
    List<Method> filterMethods = new ArrayList<>();
    for (Method method : methods) {
      if (!Objects.isNull(method.getAnnotation(annotation))) {
        filterMethods.add(method);
      }
    }
    return filterMethods;
  }

  /**
   * 获取某个类下面被annotation注解的域
   *
   * @param clazz
   * @param annotation
   * @return
   */
  public static List<Field> getAnnotatedFields(Class clazz, Class annotation) {
    Field[] fields = clazz.getDeclaredFields();
    List<Field> filterFields = new ArrayList<>();
    for (Field field : fields) {
      if (!Objects.isNull(field.getAnnotation(annotation))) {
        filterFields.add(field);
      }
    }
    return filterFields;
  }

  /**
   * 获取某个类下面被annotation注解的域的值
   *
   * @param clazz
   * @param annotation
   * @return
   */
  public static Map<Field, Object> getAnnotatedFieldsMap(Class clazz, Class annotation) {
    Map<Field, Object> filterFields = new HashMap<>();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      Object annotationInstance = field.getAnnotation(annotation);
      if (!Objects.isNull(annotationInstance)) {
        filterFields.put(field, annotationInstance);
      }
    }
    return filterFields;
  }

  /**
   * 获取某些类下面被annotation注解的域的值
   *
   * @param clazzSet
   * @param annotation
   * @return
   */
  public static Map<String, Object> getAnnotatedFieldsMap(Collection<Class> clazzSet, Class annotation) {
    Map<String, Object> filterFields = new HashMap<>();
    for (Class clazz : clazzSet) {
      Field[] fields = clazz.getDeclaredFields();
      for (Field field : fields) {
        Object annotationInstance = field.getAnnotation(annotation);
        if (!Objects.isNull(annotationInstance)) {
          filterFields.put(clazz.getName() + Constant.CLASS_FIELD_SPLIT_SYMBOL + field.getName(), annotationInstance);
        }
      }
    }
    return filterFields;
  }

  /**
   * 获取切面注解信息
   *
   * @param joinPoint
   * @return
   * @throws ClassNotFoundException
   */
  public static Method getAnnotationMethod(JoinPoint joinPoint) throws ClassNotFoundException {
    String targetName = joinPoint.getTarget().getClass().getName();
    String methodName = joinPoint.getSignature().getName();
    Object[] arguments = joinPoint.getArgs();
    Class targetClass = Class.forName(targetName);
    Method[] methods = targetClass.getMethods();
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        Class[] clazzs = method.getParameterTypes();
        if (clazzs.length == arguments.length) {
          return method;
        }
      }
    }
    return null;
  }

}
