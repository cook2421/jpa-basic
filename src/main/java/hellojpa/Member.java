package hellojpa;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class Member {//extends BaseEntity {

    @Id
    @GeneratedValue/*(
            strategy = GenerationType.SEQUENCE,
            generator = "member_seq_generator"
    )*/
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 기간
    @Embedded
    private Period workPeriod;

    // 주소
    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    /*@ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns =
        @JoinColumn(name = "MEMBER_ID")
    )
    private List<Address> addressHistory = new ArrayList<>();*/


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBE_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();


    /*
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column=@Column(name="WORK_CITY")),
            @AttributeOverride(name="street",
                    column=@Column(name="WORK_STREET")),
            @AttributeOverride(name="zipcode",
                    column=@Column(name="WORK_ZIPCODE"))
    })
    private Address workAddress;
    // 한 엔티티에서 같은 값을 사용하려면 컬럼명이 중복되기 때문에 @AttributeOverrides 하면 됨.
    */

    /*@Column(name = "name", nullable = false)
    private String name;*/

    /*@Column(name = "TEAM_ID")
    private Long teamId;*/

    /*@ManyToOne(fetch = FetchType.LAZY) // @ManyToOne은 LAZY 세팅 다 해놔야 함. (EAGER가 기본값)
    @JoinColumn(name = "TEAM_ID")
    private Team team;*/
    /*
        즉시로딩(EAGER)를 쓰면 안되는 이유 2가지
        1. 여러 테이블이 모두 EAGER로 엮여있으면 한 방에 여러 테이블에 조인 걸림 (성능 저하)
        2. JPQL 사용 시 한 테이블만 가져오려고 해도 해당 엔티티에 다른 엔티티가 EAGER로 참조 걸려있으면 다시 쿼리해 옴.
           만약 JPQL 결과값이 리스트면 참조 엔티티 조회를 위한한쿼리가 N번 나감.
    */

    /*@OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();*/



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

    /*public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }*/

    /*public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }*/

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    /*public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }*/

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
