package com.app.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "logo")
public class Logo implements Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "format")
    private String format;

    @Column(name = "file_bytes")
    private byte[] fileBytes;

    public Logo(String name, String size, String format, byte[] fileBytes) {
        this.name = name;
        this.size = size;
        this.format = format;
        this.fileBytes = fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    @Override
    public String toString() {
        return "Logo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", format='" + format + '\'' +
                ", fileBytes='" + Arrays.toString(fileBytes) + '\'' +
                '}';
    }
}
