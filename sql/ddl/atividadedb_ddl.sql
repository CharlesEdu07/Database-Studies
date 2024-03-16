-- DROP TABLE IF EXISTS
DROP TABLE IF EXISTS atividade CASCADE;

DROP TABLE IF EXISTS projeto CASCADE;

DROP TABLE IF EXISTS departamento CASCADE;

DROP TABLE IF EXISTS funcionario CASCADE;

-- Criação das Tabelas
CREATE TABLE funcionario (
    codigo SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    sexo CHAR(1),
    dtNasc DATE,
    salario DECIMAL(10, 2),
    codSupervisor INTEGER,
    codDepto INTEGER
);

CREATE TABLE departamento (
    codigo SERIAL PRIMARY KEY,
    sigla VARCHAR(10),
    descricao VARCHAR(50),
    codGerente INTEGER,
    UNIQUE (sigla)
);

CREATE TABLE projeto (
    codigo SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    descricao VARCHAR(250),
    codResponsavel INTEGER,
    codDepto INTEGER,
    dataInicio DATE,
    dataFim DATE,
    UNIQUE (nome)
);

CREATE TABLE atividade (
    codigo SERIAL PRIMARY KEY,
    descricao VARCHAR(250),
    codProjeto INTEGER,
    dataInicio DATE,
    dataFim DATE
);

-- Adição de Restrições de Chave Estrangeira
ALTER TABLE
    funcionario
ADD
    CONSTRAINT funcSuperFK FOREIGN KEY (codSupervisor) REFERENCES funcionario(codigo) ON DELETE
SET
    NULL ON UPDATE CASCADE,
ADD
    CONSTRAINT funcDeptoFK FOREIGN KEY (codDepto) REFERENCES departamento(codigo) ON DELETE
SET
    NULL ON UPDATE CASCADE;

ALTER TABLE
    departamento
ADD
    CONSTRAINT deptGerenteFK FOREIGN KEY (codGerente) REFERENCES funcionario(codigo) ON DELETE
SET
    NULL ON UPDATE CASCADE;

ALTER TABLE
    projeto
ADD
    CONSTRAINT projRespFK FOREIGN KEY (codResponsavel) REFERENCES funcionario(codigo) ON DELETE
SET
    NULL ON UPDATE CASCADE,
ADD
    CONSTRAINT projDeptoFK FOREIGN KEY (codDepto) REFERENCES departamento(codigo) ON DELETE
SET
    NULL ON UPDATE CASCADE;

ALTER TABLE
    atividade
ADD
    CONSTRAINT atvProjFK FOREIGN KEY (codProjeto) REFERENCES projeto(codigo) ON DELETE
SET
    NULL ON UPDATE CASCADE;