package com.teste.agenda.Models;

import com.teste.agenda.Dtos.ContatoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 5, max = 15)
    private String name;

    @Email(message = "Campo email está invalido!")
    private String email;

    @Size(min = 11, max = 11)
    private String phone;

    public Contato(ContatoDto data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
    }

    public Contato(Contato data) {
    }

}
