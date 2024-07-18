package com.robdig7x.apipay.domain.mapper;

import com.robdig7x.apipay.domain.model.dto.ContaDTO;
import com.robdig7x.apipay.domain.model.entity.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContaMapper {

    ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    ContaDTO toDTO(Conta conta);

    Conta toEntity(ContaDTO contaDTO);

    void updateContaFrom(ContaDTO contaDTO, @MappingTarget Conta existingConta);
}