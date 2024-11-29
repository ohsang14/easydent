package webproject.easydent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import webproject.easydent.entities.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByCustomerName(String customerName);
    
    // 이 병원에 이 시간에 얼만큼 예약을 했는지
    List<Reservation> findByClinicNameAndReservedTime(String clinicName, String reservedTime);

}
