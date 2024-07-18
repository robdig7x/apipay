CREATE TABLE conta (
    id BIGSERIAL primary key,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE,
    valor DECIMAL(10, 2) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    situacao VARCHAR(20) NOT NULL
);