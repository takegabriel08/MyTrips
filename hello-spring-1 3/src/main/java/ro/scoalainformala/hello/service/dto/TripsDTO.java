package ro.scoalainformala.hello.service.dto;

import java.time.LocalDate;

public class TripsDTO {
    private long id;
    private String title;
    private String description;
    private String photo1Title;
    private String photo2Title;
    private String photo1Description;
    private String photo2Description;
    private String startDate;
    private String endDate;

    public TripsDTO() {
    }

    public TripsDTO(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public TripsDTO(long id, String title, String description,
                    String photo1Title, String photo2Title,
                    String photo1Description, String photo2Description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo1Title = photo1Title;
        this.photo2Title = photo2Title;
        this.photo1Description = photo1Description;
        this.photo2Description = photo2Description;
    }

    public TripsDTO(long id, String title, String description, String photo1Title, String photo2Title,
                    String photo1Description, String photo2Description, String startDate, String endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photo1Title = photo1Title;
        this.photo2Title = photo2Title;
        this.photo1Description = photo1Description;
        this.photo2Description = photo2Description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getPhoto1Title() {
        return photo1Title;
    }

    public void setPhoto1Title(String photo1Title) {
        this.photo1Title = photo1Title;
    }

    public String getPhoto2Title() {
        return photo2Title;
    }

    public void setPhoto2Title(String photo2Title) {
        this.photo2Title = photo2Title;
    }

    public String getPhoto1Description() {
        return photo1Description;
    }

    public void setPhoto1Description(String photo1Description) {
        this.photo1Description = photo1Description;
    }

    public String getPhoto2Description() {
        return photo2Description;
    }

    public void setPhoto2Description(String photo2Description) {
        this.photo2Description = photo2Description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "TripsDTO" +
                "id =" + id +
                ", title =" + title + '\'' +
                ", description =" + description + '\'' +
                ' ';
    }
}