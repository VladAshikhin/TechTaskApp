package com.app.objects.templatetypes;

import lombok.Getter;

@Getter
public class Logo {

    private Integer id;
    private String name;
    private String size;
    private String format;

    public Logo(Integer id, String name, String size, String format) {
        this.id = id;
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
