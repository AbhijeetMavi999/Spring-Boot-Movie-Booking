package com.moviebooking.entities;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class Screen implements Comparable<Screen> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer screenId;
    private String type;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinemaDto;

    @OneToOne(mappedBy = "screenDto", orphanRemoval = true)
    private Movie movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Screen screen = (Screen) o;
        return screenId != null && Objects.equals(screenId, screen.screenId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public int compareTo(Screen otherScreen) {
        return (this.getScreenId() - otherScreen.getScreenId());
    }
}
