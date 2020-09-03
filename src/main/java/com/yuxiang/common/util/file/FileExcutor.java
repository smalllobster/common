
package com.yuxiang.common.util.file;


import java.io.*;
import com.yuxiang.common.util.helper.StringHelper;

/**
 * 功能说明: 文件处理执行接口
 * 开发人员: kjp
 * 开发时间: 2018/7/2 <br>
 * 功能描述: 文件处理执行接口<br>
 */
public interface FileExcutor {

  /**
   * 修复路径，将 \\ 或 / 等替换为 File.separator
   *
   * @param path 路径
   * @return 路径 string
   */
  default String path(String path) {
    String p = StringHelper.replace(path, "\\", "/");
    p = StringHelper.join(StringHelper.split(p, "/"), "/");
    if (!StringHelper.startsWithAny(p, "/") && StringHelper.startsWithAny(path, "\\", "/")) {
      p += "/";
    }
    if (!StringHelper.endsWithAny(p, "/") && StringHelper.endsWithAny(path, "\\", "/")) {
      p = p + "/";
    }
    return p;
  }

  /**
   * 增加文件结尾/
   *
   * @param name 文件路径
   * @return 文件路径 string
   */
  default String addEndSlash(String name) {
    return StringHelper.isEmpty(name) || name.endsWith("/") ? name : name + "/";
  }

  /**
   * 移除文件结尾/
   *
   * @param name 文件路径
   * @return 文件路径 string
   */
  default String clearEndSlash(String name) {
    return StringHelper.isEmpty(name) || !name.endsWith("/") ? name : name.substring(0, name.length() - 1);
  }

  /**
   * 复制文件，可以复制单个文件或文件夹
   *
   * @param srcFileName  待复制的文件名
   * @param descFileName 目标文件名
   * @return 如果复制成功 ，则返回true，否是返回false
   * @throws IOException the io exception
   */
  boolean copy(String srcFileName, String descFileName) throws IOException;

  /**
   * 复制单个文件，如果目标文件存在，则不覆盖
   *
   * @param srcFileName  待复制的文件名
   * @param descFileName 目标文件名
   * @return 如果复制成功 ，则返回true，否则返回false
   * @throws IOException the io exception
   */
  boolean copyFile(String srcFileName, String descFileName) throws IOException;

  /**
   * 复制单个文件
   *
   * @param srcFileName  待复制的文件名
   * @param descFileName 目标文件名
   * @param coverlay     如果目标文件已存在，是否覆盖
   * @return 如果复制成功 ，则返回true，否则返回false
   * @throws IOException the io exception
   */
  boolean copyFileCover(String srcFileName,
                        String descFileName, boolean coverlay) throws IOException;

  /**
   * 复制整个目录的内容，如果目标目录存在，则不覆盖
   *
   * @param srcDirName  源目录名
   * @param descDirName 目标目录名
   * @return 如果复制成功返回true ，否则返回false
   * @throws IOException the io exception
   */
  boolean copyDirectory(String srcDirName, String descDirName) throws IOException;

  /**
   * 复制整个目录的内容
   *
   * @param srcDirName  源目录名
   * @param descDirName 目标目录名
   * @param coverlay    如果目标目录存在，是否覆盖
   * @return 如果复制成功返回true ，否则返回false
   * @throws IOException the io exception
   */
  boolean copyDirectoryCover(String srcDirName,
                             String descDirName, boolean coverlay) throws IOException;

  /**
   * 复制整个目录的内容
   *
   * @param folder      源目录
   * @param descDirName 目的地址
   * @return boolean boolean
   * @throws IOException the io exception
   */
  boolean copyFolder(File folder, String descDirName) throws IOException;

  /**
   * Stream copy, use default Constant.buf_size.
   *
   * @param is InputStream
   * @param os OutputStream
   * @throws IOException IO异常
   */
  void copy(InputStream is, OutputStream os) throws IOException;

  /**
   * copy data from reader to writer.
   *
   * @param reader Reader
   * @param writer Writer
   * @throws IOException IO异常
   */
  void copy(Reader reader, Writer writer) throws IOException;

  /**
   * Stream copy.
   *
   * @param is      InputStream
   * @param os      OutputStream
   * @param bufSize int
   * @throws IOException IO异常
   */
  void copy(InputStream is, OutputStream os, int bufSize) throws IOException;

  /**
   * 将目标的文件或目录移动到新位置上.
   *
   * @param srcFileName  待复制的文件名
   * @param descFileName 目标文件名
   * @return 如果移动成功 ，则返回true，否是返回false
   * @throws IOException the io exception
   */
  boolean move(String srcFileName, String descFileName) throws IOException;

  /**
   * 删除文件，可以删除单个文件或文件夹
   *
   * @param fileName 被删除的文件名
   * @return 如果删除成功 ，则返回true，否是返回false
   */
  boolean delFile(String fileName);

  /**
   * 删除单个文件
   *
   * @param fileName 被删除的文件名
   * @return 如果删除成功 ，则返回true，否则返回false
   */
  boolean deleteFile(String fileName);

  /**
   * 删除目录及目录下的文件
   *
   * @param dirName 被删除的目录所在的文件路径
   * @return 如果目录删除成功 ，则返回true，否则返回false
   */
  boolean deleteDirectory(String dirName);

  /**
   * 清空一个目录.
   *
   * @param dirName 需要清除的目录.如果该参数实际上是一个file,不处理,返回true,
   * @return 是否清除成功 boolean
   */
  boolean clearFolder(String dirName);

  /**
   * 清空目录
   *
   * @param folder 目标目录
   * @return 是否清除成功
   */
  boolean clearFolder(File folder);

  /**
   * 创建单个文件
   *
   * @param descFileName 文件名，包含路径
   * @return 如果创建成功 ，则返回true，否则返回false
   * @throws IOException the io exception
   */
  boolean createFile(String descFileName) throws IOException;

  /**
   * 创建目录
   *
   * @param descDirName 目录名,包含路径
   * @return 如果创建成功 ，则返回true，否则返回false
   * @throws IOException the io exception
   */
  boolean createDirectory(String descDirName) throws IOException;

  /**
   * 从指定Reader中读取数据字符串.
   *
   * @param reader Reader
   * @return String string
   * @throws IOException IO异常
   */
  String read(Reader reader) throws IOException;

  /**
   * 保存一个数据到指定文件中.
   *
   * @param file 文件
   * @param data 内容
   * @throws IOException IO异常
   */
  void saveData(File file, String data) throws IOException;

  /**
   * 创建父目录
   *
   * @param file 文件
   * @return boolean
   */
  boolean mkParentDirs(File file);

  /**
   * 将数据保存到指定位置上.
   *
   * @param file   文件
   * @param data   内容
   * @param append 是否追加
   * @throws IOException IO异常
   */
  void saveData(String file, String data, Boolean append) throws IOException;

  /**
   * 保存一个数据到指定文件中
   *
   * @param file   文件
   * @param data   内容
   * @param append 是否追加
   * @throws IOException IO异常
   */
  void saveData(File file, String data, Boolean append) throws IOException;

  /**
   * 保存bytes到一个输出流中并且关闭它
   *
   * @param os   输出流
   * @param data 内容
   * @throws IOException IO异常
   */
  void saveData(OutputStream os, byte[] data) throws IOException;

  /**
   * 保存String到指定输出流中.
   *
   * @param os   输出流
   * @param data 内容
   * @throws IOException IO异常
   */
  void saveData(OutputStream os, String data) throws IOException;

  /**
   * 将数据保存到指定位置上.
   *
   * @param file 文件路径
   * @param data 内容
   * @throws IOException IO异常
   */
  void saveData(String file, String data) throws IOException;

  /**
   * 文件下载
   *
   * @param path
   * @param showName
   */
  void downLoadFile(String path, String showName) throws UnsupportedEncodingException;

  /**
   * 压缩文件
   * @param s
   * @param fileName
   */
  void zipFile(String s, String fileName) throws IOException;
}
