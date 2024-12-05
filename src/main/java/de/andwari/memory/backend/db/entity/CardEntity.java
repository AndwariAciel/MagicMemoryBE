package de.andwari.memory.backend.db.entity;

import de.andwari.memory.backend.model.enums.CardLayout;
import de.andwari.memory.backend.model.enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt;

    private String name;
    private String scryfallId;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    private CardLayout cardLayout;

    @ManyToOne
    @JoinColumn(name = "set_id", nullable = false)
    private SetEntity set;

    private String pictureUri;
    private String manaCost;

    @ManyToOne
    @JoinColumn(name = "mask_id")
    @EqualsAndHashCode.Exclude
    private MaskEntity mask;


}
