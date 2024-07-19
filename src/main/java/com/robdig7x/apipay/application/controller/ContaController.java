package com.robdig7x.apipay.application.controller;

import com.robdig7x.apipay.domain.model.dto.ContaDTO;
import com.robdig7x.apipay.domain.model.entity.Conta;
import com.robdig7x.apipay.domain.service.ContaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contas")
@SecurityRequirement(name = "apipay")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody ContaDTO contaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.save(contaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody ContaDTO contaDTO) {
        return ResponseEntity.ok(contaService.update(id, contaDTO));
    }

    @PatchMapping("/{id}/situacao")
    public ResponseEntity<Conta> updateStatus(@PathVariable Long id, @RequestParam String situacao) {
        return ResponseEntity.ok(contaService.updateStatus(id, situacao));
    }

    @GetMapping
    public ResponseEntity<Page<Conta>> getAll(@RequestParam LocalDate startDate,
                                              @RequestParam LocalDate endDate,
                                              @RequestParam(required = false) String descricao,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        return ResponseEntity.ok(contaService.findAllByDateAndDescription(startDate, endDate, descricao, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getById(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.findById(id));
    }

    @GetMapping("/total-pago")
    public ResponseEntity<BigDecimal> getTotalPaidByPeriod(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(contaService.findTotalPaidByPeriod(startDate, endDate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConta(@PathVariable Long id) {
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importCsv(@RequestParam("file") MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<ContaDTO> contas = reader.lines()
                    .skip(1) // Skip header
                    .map(line -> {
                        String[] fields = line.split(",");
                        ContaDTO contaDTO = new ContaDTO();
                        contaDTO.setDataVencimento(LocalDate.parse(fields[0]));
                        contaDTO.setDataPagamento(LocalDate.parse(fields[1]));
                        contaDTO.setValor(new BigDecimal(fields[2]));
                        contaDTO.setDescricao(fields[3]);
                        contaDTO.setSituacao(fields[4]);
                        return contaDTO;
                    })
                    .collect(Collectors.toList());
            contas.forEach(contaService::save);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}