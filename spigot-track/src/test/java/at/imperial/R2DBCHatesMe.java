package at.imperial;

import at.imperial.spring.domain.PlayerLocationData;
import at.imperial.spring.persistence.LocationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataR2dbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class R2DBCHatesMe {

    @Autowired
    LocationRepository repository;

    @Autowired
    DatabaseClient client;

    @Before
    public void setup() {
        List<String> statements = Arrays.asList(
                "DROP TABLE IF EXISTS PLAYER;",
                "CREATE TABLE IF NOT EXISTS PLAYER\n" +
                        "    (playerName VARCHAR(16) NOT NULL PRIMARY KEY,\n" +
                        "    locX INTEGER,\n" +
                        "    locZ INTEGER\n" +
                        ");");

        statements.forEach(it -> client.execute(it)
                .fetch()
                .rowsUpdated()
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete());
    }

    @Test
    public void ensureCanInsertAndRetrieve() {
        PlayerLocationData sampleData = new PlayerLocationData("Steve", 20, 20);
        repository.save(sampleData.asNew()).subscribe();

        repository.findByName("Steve")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();
    }

}
