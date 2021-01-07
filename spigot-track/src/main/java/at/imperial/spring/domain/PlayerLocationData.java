package at.imperial.spring.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@RequiredArgsConstructor
@Table("Player")
public class PlayerLocationData implements Persistable<String> {

    @NonNull
    @Column("playerName")
    @Id
    private String playerName;
    @NonNull
    @Column("locX")
    private int locX;
    @NonNull
    @Column("locZ")
    private int locZ;

    @Transient
    private boolean isNew;

    @Override
    @Transient
    public boolean isNew() {
        return this.isNew;
    }

    public PlayerLocationData asNew() {
        this.isNew = true;
        return this;
    }

    public String getId() {
        return playerName;
    }
}