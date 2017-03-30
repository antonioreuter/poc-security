package poc.security.audit;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by 310280812 on 3/28/2017.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"poc.security"})
@EnableJpaRepositories(basePackages = {"poc.security"})
@EnableTransactionManagement
public class JpaConfig {
}