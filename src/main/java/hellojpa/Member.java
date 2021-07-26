package hellojpa;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
/*@SequenceGenerator(
        name = "member_seq_generator",
        sequenceName = "member_seq",
        allocationSize = 50
)*/
/*@TableGenerator(
        name = "member_seq_generator",
        table = "my_sequences",
        pkColumnValue = "member_seq", allocationSize = 1
)*/
public class Member {

    @Id
    @GeneratedValue/*(
            strategy = GenerationType.SEQUENCE,
            generator = "member_seq_generator"
    )*/
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    /*@Column(name = "name", nullable = false)
    private String name;*/

    /*@Column(name = "TEAM_ID")
    private Long teamId;*/

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /*public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }*/

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
