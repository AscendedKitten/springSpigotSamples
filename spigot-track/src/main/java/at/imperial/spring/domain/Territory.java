package at.imperial.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.UUID;

public class Territory {
    @Setter
    @Getter
    private int startX, startZ, endX, endZ;
    @Setter
    @Getter
    private String name;
    @JsonIgnore
    private final HashSet<UUID> occupants = new HashSet<>();

    public boolean addMember(UUID uuid) {
        return occupants.add(uuid);
    }

    public boolean contains(UUID uuid) {
        return occupants.contains(uuid);
    }

    public boolean removeMember(UUID uuid) {
        return occupants.remove(uuid);
    }
}