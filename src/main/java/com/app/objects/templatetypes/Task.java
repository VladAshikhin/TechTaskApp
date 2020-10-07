package com.app.objects.templatetypes;

import lombok.Getter;

import java.util.Date;

@Getter
public class Task {

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
    private String[] source;
    // examplesReady
    private String contacts;

    @Override
    public String toString() {
        return "Task{" +
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
                ", source=" + source +
                ", contacts='" + contacts + '\'' +
                '}';
    }

    public Task(String company, String maketSize, String info, String platform, String buttonText, String primaryMaketText, String secondaryMaketText, String contacts) {
        this.company = company;
        this.maketSize = maketSize;
        this.info = info;
        this.platform = platform;
        this.buttonText = buttonText;
        this.primaryMaketText = primaryMaketText;
        this.secondaryMaketText = secondaryMaketText;
        this.contacts = contacts;
    }

    public Task() {
    }

}
