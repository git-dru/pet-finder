package pet.finder.petfinder.config;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DomainConfig is a Spring configuration class that configures JPA auditing,
 * entity scanning, and transaction management for the application domain.
 */
@Configuration
@EntityScan("pet.finder.petfinder.domain")
@EnableJpaRepositories("pet.finder.petfinder.repos")
@EnableTransactionManagement
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class DomainConfig {

    /**
     * Provides a bean for customizing the DateTimeProvider used by JPA auditing.
     * This bean returns the current OffsetDateTime as the auditing date-time.
     *
     * @return DateTimeProvider bean providing the current OffsetDateTime
     */
    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }

}
