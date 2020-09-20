package com.app.objects;

public class Task {

    private String company;
    private String maket;
    private String info;
    private String platform;
    private String buttonText;
    private String slogan;
    private String description;
    private String contacts;

    @Override
    public String toString() {
        return "Task{" +
                "company='" + company + '\'' +
                ", maket='" + maket + '\'' +
                ", info='" + info + '\'' +
                ", platform='" + platform + '\'' +
                ", buttonText='" + buttonText + '\'' +
                ", slogan='" + slogan + '\'' +
                ", description='" + description + '\'' +
                ", contacts='" + contacts + '\'' +
                '}';
    }


    public String getCompany() {
        return company;
    }

    public String getMaket() {
        return maket;
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

    public String getSlogan() {
        return slogan;
    }

    public String getDescription() {
        return description;
    }

    public String getContacts() {
        return contacts;
    }
}
