package at.imperial.spigot.events;

import at.imperial.spring.domain.Territory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.IOException;
import java.util.HashSet;


public enum AreaManager {

    INSTANCE;

    @Getter
    private HashSet<Territory> territories;

    public void initialize() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();

        this.territories = objectMapper.readValue(classLoader.getResource("territories.json"), new TypeReference<HashSet<Territory>>() {});
    }
}
