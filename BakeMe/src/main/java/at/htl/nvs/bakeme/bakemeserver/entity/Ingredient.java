package at.htl.nvs.bakeme.bakemeserver.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

@Entity
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer availableMonthsBitmask;

    public Ingredient() {}

    public Ingredient(String name, EnumSet<Month> availableMonths) {
        this.name = name;
        setAvailableMonths(availableMonths);
    }

    public EnumSet<Month> getAvailableMonths() {

        EnumSet<Month> availableMonths = EnumSet.noneOf(Month.class);
        for (int i = 0; i < 12; i++) {
            if ((availableMonthsBitmask & 1 << i) != 0) {
                availableMonths.add(Month.of(i + 1));
            }
        }
        return availableMonths;
    }

    public void setAvailableMonths(EnumSet<Month> availableMonths) {
        availableMonthsBitmask = 0;
        for (Month m: availableMonths) {
            availableMonthsBitmask |= 1<<m.ordinal();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailableMonthsBitmask() {
        return availableMonthsBitmask;
    }
}
