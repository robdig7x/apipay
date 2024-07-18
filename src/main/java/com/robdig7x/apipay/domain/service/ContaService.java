package com.robdig7x.apipay.domain.service;

import com.robdig7x.apipay.domain.mapper.ContaMapper;
import com.robdig7x.apipay.domain.model.dto.ContaDTO;
import com.robdig7x.apipay.domain.model.entity.Conta;
import com.robdig7x.apipay.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta save(ContaDTO contaDTO) {
        return contaRepository.save(ContaMapper.INSTANCE.toEntity(contaDTO));
    }

    public Conta update(Long id, ContaDTO contaDTO) {
        Conta existingConta = contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta not found"));
        ContaMapper.INSTANCE.updateContaFrom(contaDTO, existingConta);
        return contaRepository.save(existingConta);
    }

    public Conta updateStatus(Long id, String situacao) {
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta not found"));
        conta.setSituacao(situacao);
        return contaRepository.save(conta);
    }

    public Page<Conta> findAllByDateAndDescription(LocalDate startDate, LocalDate endDate, String descricao, int page, int size) {
        return contaRepository.findAllByDataVencimentoBetweenAndDescricaoContaining(startDate, endDate, descricao, PageRequest.of(page, size));
    }

    public Page<Conta> findAll() {
        return contaRepository.findAll(PageRequest.of(1, 10));
    }

    public Conta findById(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta not found"));
    }

    public BigDecimal findTotalPaidByPeriod(LocalDate startDate, LocalDate endDate) {
        List<Conta> contas = contaRepository.findAllByDataVencimentoBetweenAndDescricaoContaining(startDate, endDate, "");
        return contas.stream()
                .filter(conta -> "PAGO".equals(conta.getSituacao()))
                .map(Conta::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void delete(Long id) {
        contaRepository.deleteById(id);
    }
}