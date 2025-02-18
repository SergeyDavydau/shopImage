package com.shop.shopimage.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;

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
    @Size(min = 1, max = 30, message = "имя не должно быть больше 30 символов")
    String name;

    @NotBlank
    @Size(max = 2000, message = "описание не должно быть больше 2000 символов")
    String description;

    @Column(name = "create_date")
    Date  createDate;

    Date  editDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="author_id")
    User authorId;


}
