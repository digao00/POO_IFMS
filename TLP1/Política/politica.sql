-- Criação do banco de dados (opcional, descomente se necessário)
-- CREATE DATABASE estrutura_estado_brasileiro;

-- Tabela para os Poderes do Estado
CREATE TABLE poderes_estado (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    descricao TEXT NOT NULL,
    finalidade TEXT NOT NULL
);

-- Tabela para os Níveis de Governo
CREATE TABLE niveis_governo (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    descricao TEXT NOT NULL
);

-- Tabela para Cargos/Agentes Políticos
CREATE TABLE cargos_politicos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    nivel_governo_id INTEGER REFERENCES niveis_governo(id),
    poder_estado_id INTEGER REFERENCES poderes_estado(id),
    responsabilidades TEXT NOT NULL,
    requisitos TEXT,
    mandato VARCHAR(200)
);

-- Inserção dos Poderes do Estado
INSERT INTO poderes_estado (nome, descricao, finalidade) VALUES
('Executivo', 'Poder responsável pela administração e implementação de políticas públicas', 'Governar o povo e administrar os interesses públicos, cumprindo as leis e garantindo serviços essenciais'),
('Legislativo', 'Poder responsável pela elaboração de leis e fiscalização do Executivo', 'Representar o povo, elaborar leis, fiscalizar os atos do Poder Executivo e aprovar orçamentos'),
('Judiciário', 'Poder responsável pela aplicação da lei e solução de conflitos', 'Garantir o cumprimento das leis, resolver conflitos entre cidadãos ou entre cidadãos e o Estado, e proteger direitos individuais e coletivos');

-- Inserção dos Níveis de Governo
INSERT INTO niveis_governo (nome, descricao) VALUES
('Federal', 'Governo da União, responsável por todo o território nacional'),
('Estadual', 'Governo dos estados da federação'),
('Municipal', 'Governo dos municípios'),
('Distrital', 'Governo do Distrito Federal');

-- Inserção dos Cargos Políticos
-- Federal
INSERT INTO cargos_politicos (nome, nivel_governo_id, poder_estado_id, responsabilidades, requisitos, mandato) VALUES
('Presidente da República', 1, 1, 'Chefe de Estado e de Governo, comanda a administração federal, representa o país internacionalmente, sanciona ou veta leis', 'Brasileiro nato, maior de 35 anos, filiado a partido político, pleno exercício dos direitos políticos', '4 anos com possibilidade de reeleição'),
('Senador', 1, 2, 'Representar os estados, elaborar leis federais, fiscalizar o Presidente, processar e julgar autoridades', 'Brasileiro nato, maior de 35 anos, filiado a partido político, pleno exercício dos direitos políticos', '8 anos'),
('Deputado Federal', 1, 2, 'Elaborar leis federais, fiscalizar o Poder Executivo, autorizar processo contra Presidente', 'Brasileiro, maior de 21 anos, filiado a partido político, pleno exercício dos direitos políticos', '4 anos'),
('Ministro do STF', 1, 3, 'Julgar ações de constitucionalidade, processos contra autoridades, e recursos extraordinários', 'Notável saber jurídico e reputação ilibada, maior de 35 e menor de 65 anos', 'Vitalicío com aposentadoria compulsória aos 75 anos');

-- Estadual
INSERT INTO cargos_politicos (nome, nivel_governo_id, poder_estado_id, responsabilidades, requisitos, mandato) VALUES
('Governador', 2, 1, 'Administrar o estado, implementar políticas estaduais, sancionar ou vetar leis estaduais', 'Brasileiro, maior de 30 anos, filiado a partido político, pleno exercício dos direitos políticos', '4 anos com possibilidade de reeleição'),
('Deputado Estadual', 2, 2, 'Elaborar leis estaduais, fiscalizar o Governador, autorizar processo contra Governador', 'Brasileiro, maior de 21 anos, filiado a partido político, pleno exercício dos direitos políticos', '4 anos'),
('Desembargador', 2, 3, 'Julgar processos em segunda instância, uniformizar a interpretação da lei no estado', 'Notável saber jurídico, experiência como juiz', 'Vitalicío com aposentadoria compulsória');

-- Municipal
INSERT INTO cargos_politicos (nome, nivel_governo_id, poder_estado_id, responsabilidades, requisitos, mandato) VALUES
('Prefeito', 3, 1, 'Administrar o município, implementar políticas municipais, sancionar ou vetar leis municipais', 'Brasileiro, maior de 21 anos, filiado a partido político, pleno exercício dos direitos políticos', '4 anos com possibilidade de reeleição'),
('Vereador', 3, 2, 'Elaborar leis municipais, fiscalizar o Prefeito, propor emendas ao orçamento municipal', 'Brasileiro, maior de 18 anos, filiado a partido político, pleno exercício dos direitos políticos', '4 anos'),
('Juiz de Direito', 3, 3, 'Julgar processos em primeira instância, aplicar a lei a casos concretos', 'Bacharel em Direito, aprovado em concurso público', 'Vitalicío com aposentadoria compulsória'),
('Vice-Prefeito', 3, 1, 'Auxiliar o prefeito, substituí-lo em impedimentos, assumir responsabilidades delegadas', 'Brasileiro, maior de 21 anos, filiado a partido político, pleno exercício dos direitos políticos', '4 anos com possibilidade de reeleição'); -- Adicionado o cargo de Vice-Prefeito

-- Distrital (DF)
INSERT INTO cargos_politicos (nome, nivel_governo_id, poder_estado_id, responsabilidades, requisitos, mandato) VALUES
('Governador do DF', 4, 1, 'Administrar o Distrito Federal, cumprindo funções equivalentes a prefeito e governador', 'Brasileiro, maior de 30 anos, filiado a partido político, pleno exercício dos direitos políticos', '4 anos com possibilidade de reeleição'),
('Deputado Distrital', 4, 2, 'Funções legislativas do DF, equivalentes a vereador e deputado estadual', 'Brasileiro, maior de 21 anos, filiado a partido político, pleno exercício dos direitos políticos', '4 anos');


-- Tabela de Partidos (com espectro completo)
CREATE TABLE partidos (
    id_partido SERIAL PRIMARY KEY,
    sigla VARCHAR(50) NOT NULL UNIQUE,
    nome_completo VARCHAR(200) NOT NULL,
    orientacao VARCHAR(50) NOT NULL CHECK (orientacao IN (
        'EXTREMA-ESQUERDA', 'ESQUERDA', 'CENTRO-ESQUERDA',
        'CENTRO', 'CENTRO-DIREITA', 'DIREITA', 'EXTREMA-DIREITA'
    ))
);

-- Tabela de Representantes (CORRIGIDA - `cargo` e `nivel` removidos, `id_cargo` adicionado como FK)
CREATE TABLE representantes_cg (
    id_representante SERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    id_cargo INTEGER NOT NULL REFERENCES cargos_politicos(id), -- CHAVE ESTRANGEIRA CORRIGIDA
    id_partido INTEGER REFERENCES partidos(id_partido),
    mandato_inicio INTEGER NOT NULL, -- ano de início
    mandato_fim INTEGER NOT NULL    -- ano de término
);

-- Inserção dos partidos com atuação em CG (mantidos os IDs para consistência)
INSERT INTO partidos (sigla, nome_completo, orientacao) VALUES
('PL', 'Partido Liberal', 'DIREITA'), -- id_partido = 1
('PT', 'Partido dos Trabalhadores', 'ESQUERDA'), -- id_partido = 2
('MDB', 'Movimento Democrático Brasileiro', 'CENTRO'), -- id_partido = 3
('PP', 'Partido Progressista', 'DIREITA'), -- id_partido = 4
('PSD', 'Partido Social Democrático', 'CENTRO'), -- id_partido = 5
('Republicanos', 'Republicanos', 'DIREITA'), -- id_partido = 6
('PSDB', 'Partido da Social Democracia Brasileira', 'CENTRO-DIREITA'), -- id_partido = 7
('PDT', 'Partido Democrático Trabalhista', 'CENTRO-ESQUERDA'), -- id_partido = 8
('PSB', 'Partido Socialista Brasileiro', 'CENTRO-ESQUERDA'), -- id_partido = 9
('NOVO', 'Partido Novo', 'DIREITA'), -- id_partido = 10
('PSC', 'Partido Social Cristão', 'EXTREMA-DIREITA'), -- id_partido = 11
('PCB', 'Partido Comunista Brasileiro', 'EXTREMA-ESQUERDA'), -- id_partido = 12
('PSOL', 'Partido Socialismo e Liberdade', 'EXTREMA-ESQUERDA'), -- id_partido = 13
('Avante', 'Avante', 'CENTRO'); -- id_partido = 14

-- Inserção dos representantes de Campo Grande (dados atualizados 2025 - CORRIGIDOS PARA USAR id_cargo)
-- É crucial obter os IDs dos cargos da tabela cargos_politicos.
-- Os valores abaixo são exemplos de como ficariam, assumindo que você já
-- rodou as inserções de cargos e pode consultar os IDs.

-- IDs de cargos (para referência, você pode precisar consultar seu DB após as inserções)
-- SELECT id, nome, nivel_governo_id FROM cargos_politicos WHERE nome = 'Prefeito' AND nivel_governo_id = 3; -- ID do Prefeito Municipal
-- SELECT id, nome, nivel_governo_id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3; -- ID do Vereador Municipal
-- SELECT id, nome, nivel_governo_id FROM cargos_politicos WHERE nome = 'Deputado Estadual' AND nivel_governo_id = 2; -- ID do Deputado Estadual
-- SELECT id, nome, nivel_governo_id FROM cargos_politicos WHERE nome = 'Deputado Federal' AND nivel_governo_id = 1; -- ID do Deputado Federal
-- SELECT id, nome, nivel_governo_id FROM cargos_politicos WHERE nome = 'Senador' AND nivel_governo_id = 1; -- ID do Senador
-- SELECT id, nome, nivel_governo_id FROM cargos_politicos WHERE nome = 'Vice-Prefeito' AND nivel_governo_id = 3; -- ID do Vice-Prefeito Municipal (Assumindo que você criou este cargo)

INSERT INTO representantes_cg (nome, id_cargo, id_partido, mandato_inicio, mandato_fim) VALUES
-- Executivo Municipal
('Adriane Lopes', (SELECT id FROM cargos_politicos WHERE nome = 'Prefeito' AND nivel_governo_id = 3), 1, 2025, 2028), -- PL
-- Corrigido para buscar o ID do cargo 'Vice-Prefeito' que foi adicionado.
('Paulo Siufi', (SELECT id FROM cargos_politicos WHERE nome = 'Vice-Prefeito' AND nivel_governo_id = 3), 1, 2025, 2028), -- PL

-- Legislativo Municipal (Vereadores - lista completa atual)
('Dr. Wilson Sami', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 1, 2025, 2028), -- PL
('Marcelo Bluma', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 1, 2025, 2028), -- PL
('Pastor Jerfferson', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 11, 2025, 2028), -- PSC
('Professor João César', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 2, 2025, 2028), -- PT
('Beto Sabino', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 4, 2025, 2028), -- PP
('Enfermeira Cida Amaral', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 3, 2025, 2028), -- MDB
('Júnior Longo', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 5, 2025, 2028), -- PSD
('Téo', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 6, 2025, 2028), -- Republicanos
('Dr. Loester', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 7, 2025, 2028), -- PSDB
('Carlão', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 8, 2025, 2028), -- PDT
('Comandante Fábio', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 1, 2025, 2028), -- PL
('Francisco Braz', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 3, 2025, 2028), -- MDB
('Isaura Mônica', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 9, 2025, 2028), -- PSB (Corrigido para 'Vereador' se for o cargo padrão)
('Paulo Lanzetta', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 10, 2025, 2028), -- NOVO
('Sargento Elizeu', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 1, 2025, 2028), -- PL
('Vinicius Abreu', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 14, 2025, 2028), -- Avante
('Willian Maksoud', (SELECT id FROM cargos_politicos WHERE nome = 'Vereador' AND nivel_governo_id = 3), 13, 2025, 2028), -- PSOL

-- Representantes Estaduais com base em CG
('Eduardo Rocha', (SELECT id FROM cargos_politicos WHERE nome = 'Deputado Estadual' AND nivel_governo_id = 2), 1, 2023, 2027), -- PL
('Pedro Kemp', (SELECT id FROM cargos_politicos WHERE nome = 'Deputado Estadual' AND nivel_governo_id = 2), 8, 2023, 2027), -- PDT
('Professor Rinaldo', (SELECT id FROM cargos_politicos WHERE nome = 'Deputado Estadual' AND nivel_governo_id = 2), 3, 2023, 2027), -- MDB

-- Representantes Federais com base em CG
('Beto Pereira', (SELECT id FROM cargos_politicos WHERE nome = 'Deputado Federal' AND nivel_governo_id = 1), 3, 2023, 2027), -- MDB
('Lucas Redecker', (SELECT id FROM cargos_politicos WHERE nome = 'Deputado Federal' AND nivel_governo_id = 1), 1, 2023, 2027), -- PL

-- Senadores com base em CG
('Nelsinho Trad', (SELECT id FROM cargos_politicos WHERE nome = 'Senador' AND nivel_governo_id = 1), 3, 2023, 2031); -- MDB
