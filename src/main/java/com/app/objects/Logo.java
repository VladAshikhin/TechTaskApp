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
@Table(name = "logo")
public class Logo implements Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "web")
    private String web;

    @Column(name = "details")
    private String details;

    @Column(name = "strong_points")
    private String strongPoints;
    @Column(name = "opponents")
    private String opponents;
    @Column(name = "logo_rus")
    private String logoRus;
    @Column(name = "logo_eng")
    private String logoEng;
    @Column(name = "logo_exists")
    private String logoExists;

    @Column(name = "style_minimalism")
    private int styleMinimalism;
    @Column(name = "style_font")
    private int styleFont;
    @Column(name = "style_elegant")
    private int styleElegant;
    @Column(name = "style_emblem")
    private int styleEmblem;
    @Column(name = "style_cubism")
    private int styleCubism;
    @Column(name = "style_retro")
    private int styleRetro;
    @Column(name = "style_other")
    private int styleOther;

    @Column(name = "font_preference")
    private int fontPreference;
    @Column(name = "font_preference_other")
    private String fontPreferenceOther;

    @Column(name = "file_bytes")
    private byte[] fileBytes;

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    @Override
    public String toString() {
        return "Logo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", web='" + web + '\'' +
                ", details='" + details + '\'' +
                ", strongPoints='" + strongPoints + '\'' +
                ", opponents='" + opponents + '\'' +
                ", logoRus='" + logoRus + '\'' +
                ", logoEng='" + logoEng + '\'' +
                ", logoExists='" + logoExists + '\'' +
                ", styleMinimalism='" + styleMinimalism + '\'' +
                ", styleFont='" + styleFont + '\'' +
                ", styleElegant='" + styleElegant + '\'' +
                ", styleEmblem='" + styleEmblem + '\'' +
                ", styleCubism='" + styleCubism + '\'' +
                ", styleRetro='" + styleRetro + '\'' +
                ", styleOther='" + styleOther + '\'' +
                ", fontPreference='" + fontPreference + '\'' +
                ", fileBytes=" + Arrays.toString(fileBytes) +
                '}';
    }
}
