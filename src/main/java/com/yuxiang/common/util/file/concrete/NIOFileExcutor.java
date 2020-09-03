
package com.yuxiang.common.util.file.concrete;

import com.yuxiang.common.constant.Constant;
import com.yuxiang.common.util.file.AbstractFileExcutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 功能说明: nio
 * 开发人员: kjp
 * 开发时间: 2018/7/2 <br>
 * 功能描述: nio<br>
 */
@Slf4j
@Component
public class NIOFileExcutor extends AbstractFileExcutor {

  @Override
  public void copy(Reader reader, Writer writer) throws IOException {

  }

  @Override
  public void copy(InputStream is, OutputStream os, int bufSize) throws IOException {
    try (
        FileChannel inFiC = ((FileInputStream) is).getChannel();
        FileChannel outFoC = ((FileOutputStream) os).getChannel();
    ) {
      while (inFiC.position() != inFiC.size()) {
        if ((inFiC.size() - inFiC.position()) < bufSize) {
          // 读取剩下的
          bufSize = (int) (inFiC.size() - inFiC.position());
        }
        inFiC.transferTo(inFiC.position(), bufSize, outFoC);
        // 内存映像复制
        //MappedByteBuffer inbuffer = inFiC.map(MapMode.READ_ONLY, inFiC.position(), bufSize);
        //outFoC.write(inbuffer);

        inFiC.position(inFiC.position() + bufSize);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean deleteFile(String fileName) {
    Path path = Paths.get(fileName);
    try {
      return Files.deleteIfExists(path);
    } catch (IOException e) {
      return false;
    }
  }

  @Override
  public boolean createFile(String descFileName) throws IOException {
    Path path = Paths.get(descFileName);
    if (Files.exists(path)) {
      throw new FileAlreadyExistsException(String.format("文件 %s 已存在!", descFileName));
    }
    if (descFileName.endsWith(File.separator)) {
      throw new IOException(String.format("%s 为目录，不能创建目录!", descFileName));
    }
    Path parent = path.getParent();
    if (!Files.exists(parent) && createDirectory(parent.getFileName().toString())) {
      throw new IOException("创建文件所在的目录失败!");
    }
    Files.createFile(Paths.get(descFileName));
    return true;
  }

  @Override
  public boolean createDirectory(String descDirName) throws IOException {
    String descDirNames = descDirName;
    if (!descDirNames.endsWith(File.separator)) {
      descDirNames = descDirNames + File.separator;
    }
    Path path = Paths.get(descDirNames);
    if (Files.exists(path)) {
      throw new FileAlreadyExistsException(String.format("目录 %s 已存在!", descDirNames));
    }
    Files.createDirectory(path);
    return true;
  }

  @Override
  public void saveData(OutputStream os, byte[] data) throws IOException {
    ByteBuffer dataBuffer = ByteBuffer.wrap(data);
    try (FileChannel channel = ((FileOutputStream) os).getChannel()) {
      channel.write(dataBuffer);
    }
  }

  @Override
  public void saveData(OutputStream os, String data) throws IOException {
    byte[] bs = data.getBytes(Constant.DEFAULT_ENCODING);
    ByteBuffer dataBuffer = ByteBuffer.wrap(bs);
    try (FileChannel channel = ((FileOutputStream) os).getChannel()) {
      channel.write(dataBuffer);
    }
  }
}
