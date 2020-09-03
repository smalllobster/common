
package com.yuxiang.common.util.file.concrete;

import com.yuxiang.common.constant.Constant;
import com.yuxiang.common.util.file.AbstractFileExcutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

/**
 * 功能说明: IO处理
 * 开发人员: kjp
 * 开发时间: 2018/7/2 <br>
 * 功能描述: IO处理<br>
 */
@Slf4j
@Component
public class IOFileExcutor extends AbstractFileExcutor {

  public void copy(InputStream is, OutputStream os, int bufSize) throws IOException {
    byte[] buf = new byte[bufSize];
    int c;
    try {
      while ((c = is.read(buf)) != -1) {
        os.write(buf, 0, c);
      }
    } finally {
      close(is);
    }
  }

  @Override
  public void copy(Reader reader, Writer writer) throws IOException {
    char[] buf = new char[Constant.BUF_SIZE];
    int len;
    try {
      while ((len = reader.read(buf)) != -1) {
        writer.write(buf, 0, len);
      }
    } finally {
      close(reader);
    }
  }

  @Override
  public boolean deleteFile(String fileName) {
    File file = new File(fileName);
    if (file.exists() && file.isFile()) {
      if (file.delete()) {
        log.debug("删除文件 {} 成功!", fileName);
        return true;
      } else {
        log.debug("删除文件 {} 失败!", fileName);
        return false;
      }
    } else {
      log.debug(String.format(Constant.FILE_NOT_FIND, fileName));
      return true;
    }
  }

  @Override
  public boolean createFile(String descFileName) throws IOException {
    File file = new File(descFileName);
    if (file.exists()) {
      throw new FileAlreadyExistsException(String.format("文件 %s 已存在!", descFileName));
    }
    if (descFileName.endsWith(File.separator)) {
      throw new IOException(String.format("%s 为目录，不能创建目录!", descFileName));
    }
    if (!file.getParentFile().exists() && !mkParentDirs(file)) {
      throw new IOException("创建文件所在的目录失败!");
    }

    // 创建文件
    try {
      if (file.createNewFile()) {
        log.debug("{} 文件创建成功!", descFileName);
        return true;
      } else {
        log.debug("{} 文件创建失败!", descFileName);
        return false;
      }
    } catch (IOException e) {
      log.debug(String.format("%s 文件创建失败!", descFileName), e);
      return false;
    }
  }

  @Override
  public boolean createDirectory(String descDirName) throws IOException {
    String descDirNames = descDirName;
    if (!descDirNames.endsWith(File.separator)) {
      descDirNames = descDirNames + File.separator;
    }
    File descDir = new File(descDirNames);
    if (descDir.exists()) {
      throw new FileAlreadyExistsException(String.format("目录 %s 已存在!", descDirNames));
    }
    // 创建目录
    if (descDir.mkdirs()) {
      log.debug("目录 {} 创建成功!", descDirNames);
      return true;
    } else {
      log.debug("目录 {} 创建失败!", descDirNames);
      return false;
    }
  }

  @Override
  public void saveData(OutputStream os, byte[] data) throws IOException {
    try {
      os.write(data);
    } finally {
      close(os);
    }
  }

  @Override
  public void saveData(OutputStream os, String data) throws IOException {
    try (
        BufferedOutputStream bos = new BufferedOutputStream(os, Constant.BUF_SIZE);
    ) {
      byte[] bs = data.getBytes(Constant.DEFAULT_ENCODING);
      bos.write(bs);
    }
  }

}
