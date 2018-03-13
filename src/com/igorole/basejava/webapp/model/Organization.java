package com.igorole.basejava.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Organization implements Serializable{
    private static final long serialVersionUID = 1L;
    private class Activity implements Comparable<Activity>, Serializable {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String description;
        private final String title;

        public Activity(LocalDate startDate, LocalDate endDate, String description, String title) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "startDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.description = description;
            this.title = title;
        }
        @Override
        public int compareTo(Activity o) {
            return startDate.compareTo(o.startDate);
        }
    }
    private final Link homePage;
    private ArrayList<Activity> activities = new ArrayList<>();

    public Organization(String name, String url) {
        this.homePage = new Link(name, url);
    }

    public void addActivity(LocalDate startDate, LocalDate endDate, String title, String description) {
        this.activities.add(new Activity(startDate, endDate, title, description));
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
