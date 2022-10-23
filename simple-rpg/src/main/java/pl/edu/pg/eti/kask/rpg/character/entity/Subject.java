package pl.edu.pg.eti.kask.rpg.character.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class for game characters' professions (classes). Describes name of the profession and skills available on
 * different levels.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "subjects")
public class Subject implements Serializable {

    /**
     * Unique id (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Name of the profession.
     */
    private String name;


}
