package webproject.easydent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webproject.easydent.entities.Dentistry;

import java.util.Optional;

public interface DentistryRepository extends JpaRepository<Dentistry, String> {
    
    Optional<Dentistry> findByName(String name); // 병원 이름으로 조회
    
    Optional<Dentistry> findByCategory(String category); // 카테고리별 조회
}
