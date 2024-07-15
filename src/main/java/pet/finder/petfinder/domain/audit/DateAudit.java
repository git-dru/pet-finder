package pet.finder.petfinder.domain.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

/**
 * Base abstract class for entities that need createdAt and updatedAt audit
 * fields.
 * Uses Spring Data JPA's auditing features.
 */
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public abstract class DateAudit implements Serializable {

    /**
     * Automatic timestamp when entity is created.
     */
    @CreatedDate
    private Instant createdAt;

    /**
     * Automatic timestamp when entity is last modified.
     */
    @LastModifiedDate
    private Instant updatedAt;
}
