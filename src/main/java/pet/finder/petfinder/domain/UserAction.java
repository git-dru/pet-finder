package pet.finder.petfinder.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import pet.finder.petfinder.enums.ActionType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Represents an action performed by a user on a pet in the application.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class UserAction {
    /**
     * Primary key identifier for the user action.
     */
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(name = "primary_sequence", sequenceName = "primary_sequence", allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_sequence")
    private Long id;

    /**
     * Identifier of the pet involved in the action.
     */
    @Column(nullable = false)
    private Long petId;

    /**
     * Type of action performed (e.g., adoption, foster, donation).
     */
    @Enumerated(EnumType.STRING)
    @Column
    private ActionType action;

    /**
     * User who performed the action (optional, can be null for system actions).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_user_id", nullable = true)
    private User actionUser;

    /**
     * Date and time when the action was created.
     */
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    /**
     * Date and time when the action was last updated.
     */
    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;
}
