package com.teste.agenda;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import com.teste.agenda.Models.Contato;
import com.teste.agenda.Repository.ContatoRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class ContatoRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ContatoRepository contatoRepository;

    @Test
    @DisplayName("Should return contact successfully from db!")
    void findContactByIdSuccess() {
        Long id = 1L;
        Contato data = new Contato(id, "fulano", "fulano@email.com", "22345678907");
        this.createContato(data);
        Optional<Contato> founderContact = this.contatoRepository.findById(id);
        assertThat(founderContact.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not return contact from db when contact not exists!")
    void findContactByIdCase2() {
        Long id = 1L;
        Optional<Contato> founderContact = this.contatoRepository.findById(id);
        assertThat(founderContact.isEmpty()).isTrue();
    }

    private Contato createContato(Contato data) {
        Contato newContato = new Contato(data);
        this.entityManager.persist(newContato);
        return newContato;
    }
}
