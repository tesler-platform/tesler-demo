package io.demo.conf;

import io.tesler.api.config.TeslerBeanProperties;
import io.tesler.api.service.tx.ITransactionStatus;
import io.tesler.core.config.APIConfig;
import io.tesler.core.config.CoreApplicationConfig;
import io.tesler.core.config.UIConfig;
import io.tesler.model.core.config.PersistenceJPAConfig;
import io.tesler.model.core.tx.TeslerJpaTransactionManagerForceActiveAware;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@Import({
		CoreApplicationConfig.class,
		PersistenceJPAConfig.class,
		UIConfig.class,
		APIConfig.class
})
@EnableJpaRepositories(basePackages = "io.demo")
@EntityScan({"io.tesler", "io.demo"})
public class ApplicationConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("*")
						.allowedHeaders("*");
			}
		};
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			final ApplicationContext applicationContext,
			final TeslerBeanProperties teslerBeanProperties,
			final ITransactionStatus txStatus) {
		return new TeslerJpaTransactionManagerForceActiveAware(applicationContext, teslerBeanProperties, txStatus);
	}

}
