package com.padelmons.PadelMons.Dto;

import com.padelmons.PadelMons.entities.Player;

public class PlayerDTO {
    public String name;
    public String surname;
    public int age;
    public String gender;
    public String imgUrl;
    public String email;
    public String phone;


    public PlayerDTO(Player player) {
        this.name = player.getName();
        this.surname = player.getSurname();
        this.age = player.getAge();
        this.gender = player.getGender();
        this.imgUrl = player.getImgUrl();
        this.email = player.getDataContact() != null ? player.getDataContact().getEmail() : null;
        this.phone = player.getDataContact() != null ? player.getDataContact().getPhone() : null;
    }
}
