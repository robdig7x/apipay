package com.robdig7x.apipay.domain.repository;

import com.robdig7x.apipay.domain.model.entity.Conta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@DataJpaTest

public class ContaRepositoryTest {
    @Test
    public void testee() {
        assertNotNull("Não implementado ainda.");
    }
    /*
    @Autowired
    private ContaRepository contaRepository;

    @Test
    public void testSaveConta() {
        Conta conta = new Conta();
        conta.setDataVencimento(LocalDate.now());
        conta.setValor(BigDecimal.valueOf(100));
        conta.setDescricao("Teste");

        Conta savedConta = contaRepository.save(conta);

        assertNotNull(savedConta);
    }

    @Test
    public void testFindAllContas() {
        Conta conta = new Conta();
        conta.setDataVencimento(LocalDate.now());
        conta.setValor(BigDecimal.valueOf(100));
        conta.setDescricao("Teste");

        contaRepository.save(conta);

        List<Conta> contas = contaRepository.findAll();
        assertEquals(1, contas.size());
    }

    @Test
    public void testFindById() {
        Conta conta = new Conta();
        conta.setDataVencimento(LocalDate.now());
        conta.setValor(BigDecimal.valueOf(100));
        conta.setDescricao("Teste");

        Conta savedConta = contaRepository.save(conta);

        Conta foundConta = contaRepository.findById(savedConta.getId()).orElse(null);
        assertNotNull(foundConta);
    }

    @Test
    public void testDeleteConta() {
        Conta conta = new Conta();
        conta.setDataVencimento(LocalDate.now());
        conta.setValor(BigDecimal.valueOf(100));
        conta.setDescricao("Teste");

        Conta savedConta = contaRepository.save(conta);
        contaRepository.deleteById(savedConta.getId());

        List<Conta> contas = contaRepository.findAll();
        assertEquals(0, contas.size());
    }

     */
}
