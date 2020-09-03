
package com.yuxiang.common.util.file;

import com.yuxiang.common.constant.Constant;
import com.yuxiang.common.exception.YXException;
import com.yuxiang.common.util.file.concrete.ZipExcutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/7/2 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Slf4j
public abstract class AbstractFileExcutor implements FileExcutor {

  @Override
  public boolean copy(String srcFileName, String descFileName) throws IOException {
    File file = new File(srcFileName);
    if (!file.exists()) {
      log.debug(String.format(Constant.FILE_NOT_FIND, srcFileName));
      return false;
    } else {
      if (file.isFile()) {
        return !copyFile(srcFileName, descFileName);
      } else {
        return !copyDirectory(srcFileName, descFileName);
      }
    }
  }

  @Override
  public void copy(InputStream is, OutputStream os) throws IOException {
    copy(is, os, Constant.BUF_SIZE);
  }

  @Override
  public boolean copyFile(String srcFileName, String descFileName) throws IOException {
    return copyFileCover(srcFileName, descFileName, false);
  }

  @Override
  public boolean copyFileCover(String srcFileName,
                               String descFileName, boolean coverlay) throws IOException {
    File srcFile = new File(srcFileName);
    // 判断源文件是否存在
    if (!srcFile.exists()) {
      throw new FileNotFoundException(String.format("复制文件失败，源文件 %s 不存在!", srcFileName));
    } else if (!srcFile.isFile()) { // 判断源文件是否是合法的文件
      throw new FileNotFoundException(String.format("复制文件失败，%s 不是一个文件!", srcFileName));
    }
    File descFile = new File(descFileName);
    // 判断目标文件是否存在
    if (descFile.exists()) {
      // 如果目标文件存在，并且允许覆盖
      if (coverlay) {
        if (delFile(descFileName)) {
          throw new IOException(String.format("删除目标文件 %s 失败!", descFileName));
        }
      } else {
        throw new FileAlreadyExistsException(String.format("复制文件失败，目标文件 %s 已存在!", descFileName));
      }
    } else {
      if (!descFile.getParentFile().exists() && !mkParentDirs(descFile)) {
        throw new IOException("创建目标文件所在的目录失败!");
      }
    }

    // 准备复制文件
    try (
        InputStream ins = new FileInputStream(srcFile);
        OutputStream outs = new FileOutputStream(descFile);
    ) {
      copy(ins, outs);
      return true;
    } catch (Exception e) {
      log.warn("复制文件失败：", e);
      return false;
    }
  }

  @Override
  public boolean copyDirectory(String srcDirName, String descDirName) throws IOException {
    return copyDirectoryCover(srcDirName, descDirName, false);
  }

  @Override
  public boolean copyDirectoryCover(String srcDirName,
                                    String descDirName, boolean coverlay) throws IOException {
    File srcDir = new File(srcDirName);
    // 判断源目录是否存在
    if (!srcDir.exists()) {
      throw new FileNotFoundException(String.format("复制目录失败，源目录 %s 不存在!", srcDirName));
    } else if (!srcDir.isDirectory()) { // 判断源目录是否是目录
      throw new FileNotFoundException(String.format("复制目录失败，%s 不是一个目录!", srcDirName));
    }
    // 如果目标文件夹名不以文件分隔符结尾，自动添加文件分隔符
    String descDirNames = descDirName;
    if (!descDirNames.endsWith(File.separator)) {
      descDirNames = descDirNames + File.separator;
    }
    File descDir = new File(descDirNames);
    // 如果目标文件夹存在
    if (descDir.exists()) {
      if (coverlay) {
        // 允许覆盖目标目录
        if (delFile(descDirNames)) {
          throw new IOException(String.format("删除目录 %s 失败!", descDirNames));
        }
      } else {
        throw new FileAlreadyExistsException(String.format("目标目录复制失败，目标目录 %s 已存在!", descDirNames));
      }
    } else {
      if (!descDir.mkdirs()) {
        throw new IOException("创建目标目录失败!");
      }
    }

    return copyFolder(srcDir, descDirName);
  }

  @Override
  public boolean copyFolder(File folder, String descDirName) throws IOException {
    File[] files = folder.listFiles();
    if (files != null) {
      for (File file : files) {
        // 如果是一个单个文件，则直接复制
        if ((file.isFile() && !copyFile(file.getAbsolutePath(), descDirName + file.getName()))
            || (file.isDirectory() && !copyDirectory(file.getAbsolutePath(), descDirName + file.getName()))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean move(String srcFileName, String descFileName) throws IOException {
    return copy(srcFileName, descFileName) && delFile(srcFileName);
  }

  @Override
  public boolean delFile(String fileName) {
    File file = new File(fileName);
    if (!file.exists()) {
      log.debug(String.format(Constant.FILE_NOT_FIND, fileName));
      return false;
    } else {
      if (file.isFile()) {
        return deleteFile(fileName);
      } else {
        return deleteDirectory(fileName);
      }
    }
  }

  @Override
  public boolean deleteDirectory(String dirName) {
    String dirNames = dirName;
    if (!dirNames.endsWith(File.separator)) {
      dirNames = dirNames + File.separator;
    }
    File dirFile = new File(dirNames);
    if (!dirFile.exists() || !dirFile.isDirectory()) {
      log.debug("{} 目录不存在!", dirNames);
      return true;
    }
    if (clearFolder(dirFile) && dirFile.delete()) {
      log.debug("删除目录 {} 成功!", dirName);
      return true;
    } else {
      log.debug("删除目录 {} 失败!", dirName);
      return false;
    }

  }

  @Override
  public boolean clearFolder(String dirName) {
    File file = new File(dirName);
    return file.isFile() || clearFolder(file);
  }

  @Override
  public boolean clearFolder(File folder) {
    File[] files = folder.listFiles();
    if (files != null) {
      for (File file : files) {
        if ((file.isFile() && !deleteFile(file.getAbsolutePath()))
            || (file.isDirectory() && !deleteDirectory(file.getAbsolutePath()))) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public String read(Reader reader) throws IOException {
    CharArrayWriter writer = new CharArrayWriter();
    copy(reader, writer);
    return writer.toString();
  }

  @Override
  public void saveData(File file, String data) throws IOException {
    if (!file.getParentFile().exists() && !mkParentDirs(file)) {
      return;
    }
    saveData(new FileOutputStream(file), data);
  }

  @Override
  public boolean mkParentDirs(File file) {
    return file.getParentFile().mkdirs();
  }

  @Override
  public void saveData(String file, String data, Boolean append) throws IOException {
    saveData(new File(file), data, append);
  }

  @Override
  public void saveData(File file, String data, Boolean append) throws IOException {
    if (!file.getParentFile().exists() && !mkParentDirs(file)) {
      return;
    }
    saveData(new FileOutputStream(file, append), data);
  }

  @Override
  public void saveData(String file, String data) throws IOException {
    saveData(new File(file), data);
  }

  @Override
  public void downLoadFile(String path, String fileName) throws UnsupportedEncodingException {
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletResponse response = servletRequestAttributes.getResponse();
    HttpServletRequest request = servletRequestAttributes.getRequest();
    File file = new File(path);
    if (file.exists()) {
      // 浏览器兼容
      String userAgent = request.getHeader("user-agent").toLowerCase();
      String showName = null;
      if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
        // IE
        showName = URLEncoder.encode(fileName, "UTF-8");
      } else {
        // 非IE
        showName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
      }
      response.setContentType("application/octet-stream");// 设置Content-Type为文件的MimeType
      response.addHeader("Content-Disposition", "attachment;filename=" + showName);// 设置文件名
      response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");

      int bufferSize = Constant.BUF_SIZE;
      // 6x128 KB = 768KB byte buffer
      ByteBuffer buff = ByteBuffer.allocateDirect(786432);

      try (
          FileInputStream fileInputStream = new FileInputStream(file);
          FileChannel fileChannel = fileInputStream.getChannel();
          ServletOutputStream fileOutputStream = response.getOutputStream()
      ) {
        response.setContentLengthLong(fileChannel.size());
        byte[] byteArr = new byte[bufferSize];
        int nRead, nGet;

        while ((nRead = fileChannel.read(buff)) != -1) {
          if (nRead == 0) {
            continue;
          }
          buff.position(0);
          buff.limit(nRead);
          while (buff.hasRemaining()) {
            nGet = Math.min(buff.remaining(), bufferSize);
            buff.get(byteArr, 0, nGet);
            fileOutputStream.write(byteArr, 0, nGet);
          }
          buff.clear();
        }
      } catch (IOException e) {
        throw new YXException("文件下载失败");
      }
    } else {
      throw new YXException("文件不存在");
    }
  }

  /**
   * 关闭流.
   *
   * @param closeable 流
   */
  public void close(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
      } catch (Exception e) {
        log.error("数据流关闭失败.", e);
      }
    }
  }

  @Override
  public void zipFile(String path, String targName) throws IOException {
    File file = new File(path);
    if (!file.exists()) {
      log.debug(String.format(Constant.FILE_NOT_FIND, path));
    } else {
      if (file.isFile()) {
        FileOutputStream fos = new FileOutputStream(targName);

        ZipOutputStream zos = new ZipOutputStream(fos);
        zos.putNextEntry(new ZipEntry(file.getName()));
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        zos.write(bytes, 0, bytes.length);
        zos.closeEntry();
        zos.close();
      } else {

        File targFile = new File(targName);

        Path sourceDir = Paths.get(path);

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(targFile));
        ZipExcutor zipExcutor = new ZipExcutor(sourceDir, zos);

        Files.walkFileTree(sourceDir, zipExcutor);
        zos.close();
      }
    }
  }
}
