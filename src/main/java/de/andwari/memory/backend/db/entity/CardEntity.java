package de.andwari.memory.backend.db.entity;

import de.andwari.memory.backend.model.enums.CardLayout;
import de.andwari.memory.backend.model.enums.CardType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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
    @Column(columnDefinition = "varchar(255)")
    private CardType cardType;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private CardLayout cardLayout;

    @ManyToOne
    @JoinColumn(name = "set_id", nullable = false)
    private SetEntity set;

    private String pictureUri;

    private String manaCost;

    @ManyToMany
    @JoinTable(
            name = "card_shape",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "shape_id"))
    @EqualsAndHashCode.Exclude
    private List<ShapeEntity> shapes;

    private Boolean ready;


}
