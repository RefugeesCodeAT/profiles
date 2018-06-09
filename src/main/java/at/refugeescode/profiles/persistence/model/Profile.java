package at.refugeescode.profiles.persistence.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Component
public class Profile {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String introduction;
    private String specialization;
    @Lob
    private byte[] picture;
    private String description;
    @ElementCollection
    private List<String> skills;
    private String githubUrl;
    private String bio;

    public Profile(String name) {
        this.name = name;
    }

    public Profile() {
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) &&
                Objects.equals(name, profile.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
