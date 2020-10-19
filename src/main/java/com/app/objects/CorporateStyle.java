package com.app.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "corporate_style")
public class CorporateStyle implements Template{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "maket_size")
    private String maketSize;

    @Column(name = "maket_orientation")
    private String maketOrientation;

    @Column(name = "info")
    private String info;

    @Column(name = "platform")
    private String platform;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "button_required")
    private String buttonRequired;

    @Column(name = "button_text")
    private String buttonText;

    @Column(name = "primary_maket_text")
    private String primaryMaketText;

    @Column(name = "secondary_maket_text")
    private String secondaryMaketText;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "example_ready")
    private String examplesReady;
    //private String[] source;

    @Override
    public String toString() {
        return "CorporateStyle{" +
                "companyName='" + companyName + '\'' +
                ", maketSize='" + maketSize + '\'' +
                ", maketOrientation='" + maketOrientation + '\'' +
                ", info='" + info + '\'' +
                ", platform='" + platform + '\'' +
                ", deadline=" + deadline +
                ", buttonRequired='" + buttonRequired + '\'' +
                ", buttonText='" + buttonText + '\'' +
                ", primaryMaketText='" + primaryMaketText + '\'' +
                ", secondaryMaketText='" + secondaryMaketText + '\'' +
                ", contacts='" + contacts + '\'' +
                ", examplesReady='" + examplesReady + '\'' +
                // ", source=" + source +
                '}';
    }

    public boolean getButtonRequired() {
        return buttonRequired.equalsIgnoreCase("yes");
    }

}