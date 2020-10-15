package com.app.objects.templatetypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CorporateStyle {

    private String company;
    private String maketSize;
    private String maketOrientation;
    private String info;
    private String platform;
    private Date deadline;
    private String button;
    private String buttonText;
    private String primaryMaketText;
    private String secondaryMaketText;
    private String contacts;
    private String examplesReady;
    //private String[] source;

    @Override
    public String toString() {
        return "CorporateStyle{" +
                "company='" + company + '\'' +
                ", maketSize='" + maketSize + '\'' +
                ", maketOrientation='" + maketOrientation + '\'' +
                ", info='" + info + '\'' +
                ", platform='" + platform + '\'' +
                ", deadline=" + deadline +
                ", button='" + button + '\'' +
                ", buttonText='" + buttonText + '\'' +
                ", primaryMaketText='" + primaryMaketText + '\'' +
                ", secondaryMaketText='" + secondaryMaketText + '\'' +
                ", contacts='" + contacts + '\'' +
                ", examplesReady='" + examplesReady + '\'' +
                // ", source=" + source +
                '}';
    }
}