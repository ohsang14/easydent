package webproject.easydent.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webproject.easydent.entities.FamilyAccount;
import webproject.easydent.entities.User;
import webproject.easydent.repositories.FamilyRepository;
import webproject.easydent.repositories.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FamilyAccountService {

    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    private String generateFamilyGroupId(String email, String relationship) {
        String emailPrefix = email.split("@")[0];  // @ 앞부분만 추출
        return emailPrefix + "_" + relationship;  // 이메일_관계 형태로 조합
    }

    @Transactional
    public FamilyAccount createFamilyGroup(User leader, String relationship) {

        String id = generateFamilyGroupId(leader.getEmail(), relationship);

        FamilyAccount familyAccount = new FamilyAccount();
        familyAccount.setId(id);  // 생성된 ID 설정
        familyAccount.setLeader(leader);
        familyAccount.setRelationship(relationship);
        familyAccount.setCreatedAt(LocalDateTime.now());

        leader.setFamilyAccount(familyAccount);
        leader.setIsFamilyLeader(true);

        return familyRepository.save(familyAccount);
    }

    // 가족 구성원 추가 메소드
    @Transactional
    public void addFamilyMember(String leaderEmail, String memberEmail, String relationship) {
        User leader = userRepository.findByEmail(leaderEmail)
                .orElseThrow(() -> new RuntimeException("Leader not found"));

        User member = userRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        FamilyAccount familyAccount = leader.getFamilyAccount();
        if (familyAccount == null) {
            throw new RuntimeException("Family group not found");
        }

        // 구성원 정보 업데이트
        member.setFamilyAccount(familyAccount);
        member.setIsFamilyLeader(false);

        userRepository.save(member);
    }
}
