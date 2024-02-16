package com.project.api.springusermanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.api.springusermanagement.entities.Post;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long id;

    @Size(min = 4, max = 10, message = "El usuario es requerido y debe tener entre 4 y 10 caracteres")
    private String username;

    @Size(min = 1, max = 50, message = "El nombre del usuario es requerido y debe tener entre 1 y 50 caracteres")
    private String name;

    @Size(min = 1, max = 100, message = "Los apellidos del usuario son requeridos y debe tener entre 1 y 100 caracteres")
    private String lastName;

    @Size(min = 8, message = "La contraseña del usuario es requerida")
    private String password;

    @Size(min = 8, message = "La repeticion de la contraseña del usuario es requerida")
    private String repeatedPassword;

    @Digits(integer=9, fraction = 0, message = "El número de telefono movil del usuario debe tener 9 dígitos")
    private Integer cellphone;

    private boolean enabled;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date creationDate;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date modificationDate;

    private List<PostDTO> posts;

    private String role;

    private String jwt;

}
