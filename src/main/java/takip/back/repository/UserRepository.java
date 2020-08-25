package takip.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import takip.back.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,String> {

    Optional<Users> findById(int id);

    Optional<Users> findByTCKno(String TCKNO);

    Optional<Users> findByName(String name);

    Optional<Users> findBySecretKey(String secretKey);

    boolean existsByName(String name);

    boolean existsByTCKno(String TCKNO);
}
