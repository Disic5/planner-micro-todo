package ru.den.planner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "activity", schema = "java_begin", catalog = "javaBegin")
public class Activity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    //создается только один раз с помощью триггера в БД
    @Column(name = "uuid", updatable = false)
    private String uuid;

    @Basic
    @Column(name = "activated", nullable = false)

    //автоматическое преобразование булеана в число, конвертация
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean activated;

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id.equals(activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
