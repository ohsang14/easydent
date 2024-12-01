package webproject.easydent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webproject.easydent.entities.FamilyAccount;
import webproject.easydent.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    User findByAccountType(String accountType);

    List<User> findByEmailAndIsFamilyLeaderAndFamilyAccount(String email, Boolean isLeader, FamilyAccount familyAccount);

    List<User> findByFamilyAccount(FamilyAccount familyAccount); //family account 찾기
}