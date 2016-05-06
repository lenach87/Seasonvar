package name.valch.repository;

import name.valch.entity.Serial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SerialRepository extends JpaRepository<Serial, Long> {

    List<Serial> findByNameIgnoreCaseContaining(String name);
    List<Serial> findByName(String name);

    Serial findById(Long id);
}
