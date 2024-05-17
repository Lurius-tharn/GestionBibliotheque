package com.esiee.gestionbibliotheque.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {
    private Integer id;
    private String nom;

}
