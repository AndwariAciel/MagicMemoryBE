package de.andwari.memory.backend.db.entity;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

import de.andwari.memory.backend.model.enums.SetType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "set")
public class SetEntity {

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

    private String scryfallId;
    private String name;
    private String code;
    private String url;
    private LocalDate releaseDate;

    @Enumerated(STRING)
    private SetType type;
    private int cards;
    private String iconUrl;
    @EqualsAndHashCode.Exclude
    private boolean released;

}
