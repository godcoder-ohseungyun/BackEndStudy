package hellojpa.manyToOne;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;


    @Column(name = "USERNAME")
    private String name;

    private int age;

    @ManyToOne  // 관계: n - 1
    @JoinColumn(name = "TEAM_ID")
    private Team team;  //연관 관계 주인

    /**
     *  객체의 두 관계중 하나를 연관관계의 주인으로 지정
     * • 연관관계의 '오직' 주인만이 외래 키를 관리(등록, 수정)
     * • 주인이 아닌쪽은 읽기만 가능
     * • 주인은 mappedBy 속성 사용X
     * • 주인이 아니면 mappedBy 속성으로 주인 지정
     */

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);  //연관관계 편의 메소드
    }
}