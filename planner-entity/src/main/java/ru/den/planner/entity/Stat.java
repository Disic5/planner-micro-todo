package ru.den.planner.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;
/*
Общая статистика по задачам(независимо от категорий задач)
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "stat", schema = "java_begin", catalog = "javaBegin")
public class Stat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    // значения задаются в триггере в бд и из этого updatable = false т.к в коде это поле не нужно обновлять
    @Column(name = "completed_total", updatable = false)
    private Long completedTotal;

    @Column(name = "uncompleted_total", updatable = false)
    private Long uncompletedTotal;

//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public String toString() {
        return "completedTotal: " + completedTotal + "\n" +
                "uncompletedTotal: "  + uncompletedTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stat stat = (Stat) o;
        return id.equals(stat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
