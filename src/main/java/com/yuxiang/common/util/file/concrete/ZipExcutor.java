
package com.yuxiang.common.util.file.concrete;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 功能说明:
 * 开发人员: kjp
 * 开发时间: 2018/10/26 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class ZipExcutor extends SimpleFileVisitor<Path> {

  private ZipOutputStream zos;

  private Path sourceDir;

  public ZipExcutor(Path sourceDir, ZipOutputStream zos) {
    this.sourceDir = sourceDir;
    this.zos = zos;
  }

  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {

    try {
      Path targetFile = sourceDir.relativize(file);

      zos.putNextEntry(new ZipEntry(targetFile.toString()));

      byte[] bytes = Files.readAllBytes(file);
      zos.write(bytes, 0, bytes.length);
      zos.closeEntry();

    } catch (IOException ex) {
      System.err.println(ex);
    }

    return FileVisitResult.CONTINUE;
  }
}