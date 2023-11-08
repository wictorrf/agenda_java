package com.teste.agenda.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teste.agenda.Dtos.ContatoDto;
import com.teste.agenda.Models.Contato;
import com.teste.agenda.Repository.ContatoRepository;
import com.teste.agenda.Services.Validations.ResponseApi;
import com.teste.agenda.Services.Validations.ValidationContato;

import jakarta.transaction.Transactional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private ValidationContato validationContato;

    @Transactional
    public ResponseEntity<ResponseApi<Contato>> createContact(ContatoDto data) {
        ResponseEntity<ResponseApi<Contato>> validationResponses = validationContato.validationContatoDto(data);
        if (!validationResponses.getBody().success()) {
            return validationResponses;
        }
        Contato newContact = new Contato(data);
        this.contatoRepository.save(newContact);
        return ResponseApi.success("Contato cadastrado com sucesso!", newContact);
    }

    @Transactional
    public ResponseEntity<ResponseApi<Contato>> updateContact(ContatoDto data, Long id) {
        Optional<Contato> existContact = this.contatoRepository.findById(id);
        ResponseEntity<ResponseApi<Contato>> validationResponses = validationContato.validationContatoDto(data);
        if (!validationResponses.getBody().success()) {
            return validationResponses;
        }
        if (existContact.isPresent()) {
            Contato contact = existContact.get();
            contact.setName(data.name());
            contact.setEmail(data.email());
            contact.setPhone(data.phone());
            this.contatoRepository.save(contact);
            return ResponseApi.success("Contato atualizado com sucesso!", contact);
        } else {
            return ResponseApi.error("Contato com o id: " + id + " nao encontrado!");
        }
    }

    public ResponseEntity<ResponseApi<Contato>> deleteContact(Long id) {
        Optional<Contato> existContact = this.contatoRepository.findById(id);
        if (existContact.isPresent()) {
            this.contatoRepository.deleteById(id);
            return ResponseApi.success("Contato deletado com sucesso", null);
        } else {
            return ResponseApi.error("Contato com o id: " + id + " nao encontrado!");
        }
    }

    public List<Contato> getAllContacts() {
        List<Contato> listContacts = this.contatoRepository.findAll();
        return listContacts;
    }

    public Optional<Contato> getById(Long id) {
        Optional<Contato> result = this.contatoRepository.findById(id);
        return result;
    }
}
