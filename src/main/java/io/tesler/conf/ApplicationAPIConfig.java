package io.tesler.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Import(io.tesler.core.config.APIConfig.class)
@ComponentScan({"io.tesler.controller"})
public class ApplicationAPIConfig extends WebMvcConfigurerAdapter {

}
