package at.imperial.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@RedisHash("PlayerLocation")
@Data
@AllArgsConstructor
public class PlayerLocationData implements Serializable {

    @Id
    @Indexed
    @NonNull
    private String name;
    @NonNull
    private int locX;
    @NonNull
    private int locZ;

}
