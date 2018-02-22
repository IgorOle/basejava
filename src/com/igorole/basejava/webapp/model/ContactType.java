package com.igorole.basejava.webapp.model;

public enum ContactType {
    TELEFON("Телефон"),
    SKYPE("Skype"),
    EMAIL("e-mail"),
    LINKEDIN("LinkedIt"),
    GITHUB("GitHub"),
    OVERFLOW("StackoverFlow"),
    HOMEPAGE("Домашняя страница");
    private String contactTitle;

    ContactType(String title) {
        this.contactTitle = title;
    }

}
