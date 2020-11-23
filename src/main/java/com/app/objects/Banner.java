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
@Table(name = "banner")
public class Banner implements Template {
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

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", format='" + format + '\'' +
                ", fileBytes='" + Arrays.toString(fileBytes) + '\'' +
                '}';
    }
}