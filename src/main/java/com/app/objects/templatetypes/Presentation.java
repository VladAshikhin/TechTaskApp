package com.app.objects.templatetypes;

import lombok.Getter;

@Getter
public class Presentation {
    private Integer id;
    private String name;
    private String format;

    public Presentation(Integer id, String name, String format) {
        this.id = id;
        this.name = name;
        this.format = format;
    }

    @Override
    public String toString() {
        return "Presentation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
