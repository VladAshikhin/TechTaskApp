package com.app.objects.templatetypes;

import lombok.Getter;

@Getter
public class Banner {
    private Integer id;
    private String name;
    private String size;
    private String format;

    public Banner(Integer id, String name, String size, String format) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.format = format;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
