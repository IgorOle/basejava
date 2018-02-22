package com.igorole.basejava.webapp.view;

import com.igorole.basejava.webapp.model.ContentResumeHTML;

public class OutHTMLResume {
    ContentResumeHTML contentResumeHTML;

    public OutHTMLResume(ContentResumeHTML contentResumeHTML) {
        this.contentResumeHTML = contentResumeHTML;
    }

    public void show() {
        System.out.println(contentResumeHTML.getHTMLFullName());
        System.out.println(contentResumeHTML.getHtmlContact());
        System.out.println(contentResumeHTML.getHTMLAllSecions());
    }
}
