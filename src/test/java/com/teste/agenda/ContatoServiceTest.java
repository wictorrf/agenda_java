package com.teste.agenda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.teste.agenda.Models.Contato;
import com.teste.agenda.Repository.ContatoRepository;
import com.teste.agenda.Services.ContatoService;
import com.teste.agenda.Services.Validations.ValidationContato;

public class ContatoServiceTest {

    @Mock
    private ContatoRepository contatoRepository;

    @Mock
    private ValidationContato validationContato;

    @Autowired
    @InjectMocks
    ContatoService contatoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should verify if the return of contacts is ok")
    void getAllContactsTest() {
        Contato request = new Contato(1L, "wictor", "wictor@email.com", "11234567890");
        when(this.contatoRepository.findAll()).thenReturn(List.of(request));
        assertEquals(List.of(request), contatoService.getAllContacts());
        verify(contatoRepository).findAll();
        verifyNoMoreInteractions(contatoRepository);
    }

    @Test
    @DisplayName("Should verify if return contact with a id")
    void getContactByIdTest() {
        Contato request = new Contato(1L, "wictor", "wictor@email.com", "11234567890");
        Optional<Contato> contact = Optional.of(request);
        when(contatoService.getById(1L)).thenReturn(contact);
        assertEquals(contatoRepository.findById(1L), contact);
        verify(contatoRepository).findById(1L);
        verifyNoMoreInteractions(contatoRepository);
    }
}
