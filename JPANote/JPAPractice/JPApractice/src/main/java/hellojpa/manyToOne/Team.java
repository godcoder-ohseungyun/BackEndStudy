package hellojpa.manyToOne;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue //default auto
    private Long id;

    private String name;

    //양방향 맵핑  객체 Team -> Member 로도 이동가능하게
    @OneToMany(mappedBy = "team")  //연관관계 가짜주인: 진짜 주인을 명시 Member의 team
    List<Member> members = new ArrayList<Member>();

    public void setMembers(List<Member> members) {
        this.members = members;

    }

    public List<Member> getMembers() {
        return members;
    }

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
