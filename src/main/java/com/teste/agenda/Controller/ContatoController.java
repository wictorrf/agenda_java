package com.teste.agenda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.agenda.Dtos.ContatoDto;
import com.teste.agenda.Models.Contato;
import com.teste.agenda.Services.ContatoService;
import com.teste.agenda.Services.Validations.ResponseApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("contato")
public class ContatoController {

    @Autowired
    ContatoService contatoService;

    @PostMapping
    @Operation(summary = "Realizar cadastro de contatos no db!", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro do contato realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Algum erro nas validacoes foi detectado")
    })
    public ResponseEntity<ResponseApi<Contato>> createContact(@RequestBody @Valid ContatoDto data) {
        try {
            return this.contatoService.createContact(data);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponseApi<>(false, errorMessage, null));
        }
    }

    @GetMapping
    @Operation(summary = "Busca todos os contatos salvos no db!", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista carregada com sucesso!"),
    })
    public ResponseEntity<List<Contato>> getAllContacts() {
        List<Contato> result = this.contatoService.getAllContacts();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um contato do db!", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Algum erro nas validacoes foi detectado")
    })
    public ResponseEntity<ResponseApi<Contato>> updateContact(@RequestBody @Valid ContatoDto data,
            @PathVariable(value = "id") Long id) {
        try {
            return this.contatoService.updateContact(data, id);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponseApi<>(false, errorMessage, null));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um contato do db!", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato deletado com sucesso!"),
    })
    public ResponseEntity<ResponseApi<Contato>> deleteContact(@PathVariable(value = "id") Long id) {
        try {
            return this.contatoService.deleteContact(id);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(new ResponseApi<>(false, errorMessage, null));
        }
    }
}
