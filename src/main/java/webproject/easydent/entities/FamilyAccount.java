package webproject.easydent.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class FamilyAccount { // 가족 계정
    //사용자 아이디 + 관계
    @Id
    String id; //임시 아이디

    private String relationship;

    @OneToMany(mappedBy = "familyAccount")
    private List<User> members = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "leader_id")
    private User leader;  // 가족 그룹 대표자

    LocalDateTime createdAt;
}
