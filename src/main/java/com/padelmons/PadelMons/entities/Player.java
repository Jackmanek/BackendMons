package com.padelmons.PadelMons.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Players")
public class Player {
    @Id
    @GeneratedValue(generator = "ID_GENERATOR")
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String imgUrl;
    @Embedded
    private DataContact dataContact;
    @ManyToOne()
    @JoinColumn(name = "team_id")
    @JsonBackReference("team-player")
    private Team team;
    public Player() {}
    public Player( String name, String surname, int age, String gender, String imgUrl, DataContact dataContact, Team team) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.imgUrl = imgUrl;
        this.dataContact = dataContact;
    }

    public Team getTeam(){
        return team;
    }
    public void setTeam(Team team){
        this.team = team;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public DataContact getDataContact() {
        return dataContact;
    }

    public void setDataContact(DataContact dataContact) {
        this.dataContact = dataContact;
    }
}
