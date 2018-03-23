package com.igorole.basejava.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    public static class Activity implements Comparable<Activity>, Serializable {
        private LocalDate startDate;
        private LocalDate endDate;
        private String description;
        private String title;

        public Activity(LocalDate startDate, LocalDate endDate, String description, String title) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "startDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.description = description;
            this.title = title;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getDescription() {
            return description;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public int compareTo(Activity o) {
            return startDate.compareTo(o.startDate);
        }
    }

    public Organization() {
    }

    private ArrayList<Activity> activities = new ArrayList<>();
    private Link homePage;
    private String name;
    private String url;

    public Organization(String name, String url) {
        this.name = name;
        this.url = url;
        this.homePage = new Link(name, url);
    }

    public void addActivity(LocalDate startDate, LocalDate endDate, String title, String description) {
        this.activities.add(new Activity(startDate, endDate, title, description));
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!homePage.equals(that.homePage)) return false;
        if (!activities.containsAll(((Organization) o).activities)) return false;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, activities);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", list=" + activities +
                '}';
    }
}
