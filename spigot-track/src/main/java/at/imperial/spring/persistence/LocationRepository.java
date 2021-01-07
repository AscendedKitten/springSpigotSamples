package at.imperial.spring.persistence;


import at.imperial.spring.domain.PlayerLocationData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface LocationRepository extends ReactiveCrudRepository<PlayerLocationData, String> {
    @Query("SELECT * FROM PLAYER WHERE playerName = :playerName")
    Mono<PlayerLocationData> findByName(String playerName);
}