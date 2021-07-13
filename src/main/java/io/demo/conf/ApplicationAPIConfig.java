package io.demo.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Import(io.tesler.core.config.APIConfig.class)
@ComponentScan({"io.demo.controller"})
public class ApplicationAPIConfig extends WebMvcConfigurerAdapter {

}
