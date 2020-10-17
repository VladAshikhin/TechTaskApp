package com.app.objects.enums;

public enum TemplateType {
    LOGO("logo"),
    BANNER("banner"),
    PRESENTATION("presentation"),
    CORPORATE_STYLE("corporateStyle"),
    NOT_DEFINED("NotDefined");

    private String value;

    TemplateType(String value) {
        this.value = value;
    }


    public static TemplateType getType(Object objectType) {
        String type = String.valueOf(objectType);
        switch (type) {
            case "logo":
                return LOGO;
            case "banner":
                return BANNER;
            case "presentation":
                return PRESENTATION;
            case "corporateStyle":
                return CORPORATE_STYLE;
            default:
                return NOT_DEFINED;
        }
    }
}
