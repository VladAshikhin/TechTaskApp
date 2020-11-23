package com.app.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "presentation")
public class Presentation implements Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "format")
    private String format;

    @Column(name = "file_bytes")
    private byte[] fileBytes;

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    @Override
    public String toString() {
        return "Presentation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", fileBytes='" + Arrays.toString(fileBytes) + '\'' +
                '}';
    }
}
