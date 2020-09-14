package io.wisoft.core.utils;

import io.wisoft.core.accounts.exception.MultipartNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;

public class FileManagementUtil {

  private final static String DIR_PATH = Paths.get("").toAbsolutePath().toString();
  public final static String IMAGE_PATH = DIR_PATH + "/img";

  public static void directorySetting() {
    File directory = new File(IMAGE_PATH);

    if (!directory.exists()) {
      try {
        directory.mkdir();
      } catch (Exception e) {
        e.getStackTrace();
      }
    }
  }

  public static String getExtension(MultipartFile multipartFile) {
    String originalFilename = multipartFile.getOriginalFilename();

    if (originalFilename == null)
      throw new MultipartNotFoundException();

    String[] split = originalFilename.split("\\.");
    return split[split.length - 1];
  }

}
