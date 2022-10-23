package pl.edu.pg.eti.kask.rpg.university.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.kask.rpg.character.entity.Professor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entity for system university. Represents information about particular university as well as credentials for authorization and
 * authentication needs.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "universities")
public class University implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * University's given name.
     */
    private String name;

    /**
     * University's location.
     */
    private String location;

    /**
     * List of university's characters.
     */
    @OneToMany(mappedBy = "university", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Professor> professors;

}
