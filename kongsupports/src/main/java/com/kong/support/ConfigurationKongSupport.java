package com.kong.support;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * kong's support package configuration class
 */
public class ConfigurationKongSupport {

   private   String regexExpressionConfigPath;

   {
      URL resource = ClassLoader.getSystemClassLoader().getResource("kong_support_config.properties");
      try {
         Properties properties = new Properties();
         properties.load(resource.openStream());
         regexExpressionConfigPath = properties.getProperty("regex.expression.file");

      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}
