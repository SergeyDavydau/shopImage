package com.shop.shopimage.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Date;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "имя не должно быть пустым")
    @Size(max = 30, message = "имя не должно быть больше 30 символов")
    String name;

    @NotBlank
    @Size(max = 2000, message = "описание не должно быть больше 2000 символов")
    String description;

    Date  createData;

    Date  editDate;

    @ManyToOne
    User user;


}
