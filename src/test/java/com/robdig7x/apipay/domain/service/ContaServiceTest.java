package com.robdig7x.apipay.domain.service;

import com.robdig7x.apipay.domain.model.dto.ContaDTO;
import com.robdig7x.apipay.domain.model.entity.Conta;
import com.robdig7x.apipay.domain.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    public ContaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveConta() {
        Conta conta = new Conta();
        when(contaRepository.save(any(Conta.class))).thenReturn(conta);

        ContaDTO contaDTO = new ContaDTO();

        Conta savedConta = contaService.save(contaDTO);

        assertNotNull(savedConta);
    }

    @Test
    public void testFindAllContas() {
        Page<Conta> pageComUmaConta = new PageImpl<>(Collections.singletonList(new Conta()));

        when(contaRepository.findAll(PageRequest.of(1, 10))).thenReturn(pageComUmaConta);

        assertFalse(contaService.findAll().isEmpty());
    }

    @Test
    public void testFindById() {
        Conta conta = new Conta();
        conta.setId(1L);
        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));

        Conta foundConta = contaService.findById(1L);

        assertNotNull(foundConta);
    }

    @Test
    public void testUpdateConta() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setDataVencimento(LocalDate.now());
        contaDTO.setValor(BigDecimal.valueOf(100));
        contaDTO.setDescricao("Teste");

        Conta conta = new Conta();
        conta.setId(1L);
        conta.setValor(BigDecimal.valueOf(100));
        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));
        when(contaRepository.save(any(Conta.class))).thenReturn(conta);

        Conta updatedConta = contaService.update(1L, contaDTO);

        assertNotNull(contaService);
        assertEquals(updatedConta.getValor(), contaDTO.getValor());
    }

    @Test
    public void testDeleteConta() {
        Conta conta = new Conta();
        conta.setId(1L);
        when(contaRepository.findById(1L)).thenReturn(Optional.of(conta));

        assertDoesNotThrow(() -> contaService.delete(1L));
    }
}