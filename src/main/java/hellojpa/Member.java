package hellojpa;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@SequenceGenerator(
        name = "member_seq_generator",
        sequenceName = "member_seq",
        allocationSize = 50
)
/*@TableGenerator(
        name = "member_seq_generator",
        table = "my_sequences",
        pkColumnValue = "member_seq", allocationSize = 1
)*/
public class Member {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_seq_generator"
    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
