package io.wisoft.core;

import io.wisoft.core.utils.FileManagementUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

  public static void main(String[] args) {
    FileManagementUtil.directorySetting();
    SpringApplication.run(CoreApplication.class, args);
  }

}
