package com.teste.agenda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.agenda.Models.Contato;
import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    Optional<Contato> findById(Long id);
}
