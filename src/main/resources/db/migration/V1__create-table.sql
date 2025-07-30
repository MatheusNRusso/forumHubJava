CREATE TABLE perfil (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(64) NOT NULL UNIQUE,
                        nivel INT NOT NULL DEFAULT 0,
                        data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE cliente (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(64) NOT NULL,
                         email VARCHAR(64) NOT NULL UNIQUE,
                         senha VARCHAR(64) NOT NULL,
                         telefone VARCHAR(11) NOT NULL,
                         cpf VARCHAR(11) NOT NULL UNIQUE,
                         ativo TINYINT(1) NOT NULL DEFAULT 1,
                         data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE cliente_perfil (
                                cliente_id BIGINT NOT NULL,
                                perfil_id BIGINT NOT NULL,
                                PRIMARY KEY (cliente_id, perfil_id),
                                FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE,
                                FOREIGN KEY (perfil_id) REFERENCES perfil(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE curso (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nome VARCHAR(64) NOT NULL,
                       categoria ENUM('programacao', 'design', 'negocios', 'outros') NOT NULL,
                       data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE topico (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(64) NOT NULL,
                        mensagem TEXT NOT NULL,
                        resposta INT NOT NULL DEFAULT 0,
                        estado ENUM('ABERTO', 'FINALIZADO_RESOLVIDO', 'FINALIZADO_PENDENTE') DEFAULT 'ABERTO',
                        autor BIGINT NOT NULL,
                        curso BIGINT NOT NULL,
                        ativo TINYINT(1) NOT NULL DEFAULT 1,
                        data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (autor) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY (curso) REFERENCES curso(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE resposta (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          topico BIGINT NOT NULL,
                          autor BIGINT NOT NULL,
                          mensagem TEXT NOT NULL,
                          data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (topico) REFERENCES topico(id) ON DELETE CASCADE ON UPDATE CASCADE,
                          FOREIGN KEY (autor) REFERENCES cliente(id) ON DELETE CASCADE ON UPDATE CASCADE
);
