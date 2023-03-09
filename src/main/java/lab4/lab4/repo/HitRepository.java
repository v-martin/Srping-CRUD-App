package lab4.lab4.repo;

import lab4.lab4.entity.Hit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HitRepository extends JpaRepository<Hit, String> {
    List<Hit> findAllPointsByUserId(Long userId);
}