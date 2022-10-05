package com.moviebooking.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class PVR extends Cinema {

    private String hallNumber;
    private String seatNumber;
    private Float price;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String showDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PVR pvr = (PVR) o;
        return getCinemaId() != null && Objects.equals(getCinemaId(), pvr.getCinemaId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
