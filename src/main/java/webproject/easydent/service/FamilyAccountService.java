package webproject.easydent.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import webproject.easydent.entities.FamilyAccount;
import webproject.easydent.entities.User;
import webproject.easydent.repositories.FamilyRepository;
import webproject.easydent.repositories.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class FamilyAccountService {
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;

    @Transactional
    public FamilyAccount createFamilyGroup(User leader, String memberEmail, String relationship) {
        System.out.println("createFamilyGroup : " + leader.getEmail()+ " " +  memberEmail + " " +  relationship);
        User member = userRepository.findByEmail(memberEmail)
                .orElseThrow(() -> new IllegalArgumentException("초대할 구성원을 찾을 수 없습니다."));

        if (leader.getEmail().equals(memberEmail)) {
            throw new IllegalArgumentException("자신을 가족 구성원으로 초대할 수 없습니다.");
        }

        // 영속성 컨텍스트에서 leader 다시 조회
        User managedLeader = userRepository.findById(leader.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("리더를 찾을 수 없습니다."));

        FamilyAccount familyAccount = new FamilyAccount();
        familyAccount.setId(generateFamilyGroupId(memberEmail, relationship));
        familyAccount.setCreatedAt(LocalDateTime.now());
        familyAccount.setRelationship(relationship);
        familyAccount.setLeader(leader);
        familyAccount.setMember(member);

        // 리더와 멤버의 familyAccount 설정
        leader.setFamilyAccount(familyAccount);
        leader.setIsFamilyLeader(true);
        member.setFamilyAccount(familyAccount);
        member.setIsFamilyLeader(false);

        familyRepository.save(familyAccount);
        userRepository.save(leader);
        userRepository.save(member);
        log.info("Family group created - Leader: {}, Member: {}, Relationship: {}",
                leader.getEmail(), memberEmail, relationship);

        return familyAccount;
    }

    private String generateFamilyGroupId(String email, String relationship) {
        String emailPrefix = email.split("@")[0];
        return emailPrefix + "_" + relationship;
    }
 }
