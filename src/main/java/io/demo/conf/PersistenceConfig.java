package io.demo.conf;

import io.tesler.model.core.config.PersistenceJPAConfig;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
		basePackages = "io.demo",
		entityManagerFactoryRef = "teslerEntityManagerFactory",
		transactionManagerRef = "teslerTransactionManager"
)
public class PersistenceConfig extends PersistenceJPAConfig {

	@Override
	protected List<String> getPackagesToScan() {
		List<String> result = new ArrayList<>(super.getPackagesToScan());
		result.add("io.demo");
		return result;
	}

}
