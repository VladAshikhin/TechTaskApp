package com.app.objects.templatetypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "logo")
public class Logo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "format")
    private String format;

    public Logo(String name, String size, String format) {
        this.name = name;
        this.size = size;
        this.format = format;
    }

    @Override
    public String toString() {
        return "Logo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
