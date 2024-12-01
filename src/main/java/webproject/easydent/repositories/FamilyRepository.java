package webproject.easydent.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import webproject.easydent.entities.FamilyAccount;
import webproject.easydent.entities.User;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<FamilyAccount, Long> {
    Optional<FamilyAccount> findByMember(User user);
}
