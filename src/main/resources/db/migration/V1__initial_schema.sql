-- Create usuarios table
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

-- Create usuario_roles table
CREATE TABLE usuario_roles (
    usuario_id BIGINT NOT NULL,
    role VARCHAR(255) NOT NULL,
    CONSTRAINT fk_usuario_roles_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Create centro_custo table
CREATE TABLE centro_custo (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL
);

-- Create contas_pagar table
CREATE TABLE contas_pagar (
    id BIGSERIAL PRIMARY KEY,
    centro_custo_id BIGINT NOT NULL,
    valor_previsto DECIMAL(15,2) NOT NULL,
    valor_pago DECIMAL(15,2),
    data_conta DATE NOT NULL,
    informacao_pagamento VARCHAR(500),
    status VARCHAR(20) NOT NULL,
    usuario_id BIGINT,
    CONSTRAINT fk_contas_pagar_centro_custo FOREIGN KEY (centro_custo_id) REFERENCES centro_custo(id),
    CONSTRAINT fk_contas_pagar_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Create indexes
CREATE INDEX idx_usuarios_username ON usuarios(username);
CREATE INDEX idx_contas_pagar_status ON contas_pagar(status);
CREATE INDEX idx_contas_pagar_data_conta ON contas_pagar(data_conta);
CREATE INDEX idx_contas_pagar_centro_custo ON contas_pagar(centro_custo_id);
CREATE INDEX idx_contas_pagar_usuario ON contas_pagar(usuario_id);
