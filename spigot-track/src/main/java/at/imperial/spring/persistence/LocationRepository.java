package at.imperial.spring.persistence;


import at.imperial.spring.domain.PlayerLocationData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<PlayerLocationData, String> {
    PlayerLocationData findByName(String name);
}
