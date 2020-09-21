package com.app.objects;

public class Task {

    private String company;
    private String maketSize;
    //maketOrientation
    private String info;
    private String platform;
    //private DateTime deadline;
    //private boolean buttonRequired;
    private String buttonText;
    private String primaryMaketText;
    private String secondaryMaketText;
    //filesPrepared
    // examplesReady
    private String contacts;

    @Override
    public String toString() {
        return "Task{" +
                "company='" + company + '\'' +
                ", maket='" + maketSize + '\'' +
                ", info='" + info + '\'' +
                ", platform='" + platform + '\'' +
                ", buttonText='" + buttonText + '\'' +
                ", slogan='" + primaryMaketText + '\'' +
                ", description='" + secondaryMaketText + '\'' +
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

    public Task() {}

    public String getCompany() {
        return company;
    }

    public String getMaketSize() {
        return maketSize;
    }

    public String getInfo() {
        return info;
    }

    public String getPlatform() {
        return platform;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getPrimaryMaketText() {
        return primaryMaketText;
    }

    public String getSecondaryMaketText() {
        return secondaryMaketText;
    }

    public String getContacts() {
        return contacts;
    }
}
