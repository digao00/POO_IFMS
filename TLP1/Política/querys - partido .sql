/* Método para inserir um novo partido (versão com objeto Partido) */
INSERT INTO partidos (sigla, nome_completo, orientacao) VALUES (?, ?, ?)

/* Método para atualizar um partido existente */
UPDATE partidos SET sigla = ?, nome_completo = ?, orientacao = ? WHERE id_partido = ?

/* Método para excluir um partido (com verificação de uso) */
DELETE FROM partidos WHERE id_partido = ?

/* Método para buscar um partido por ID */
SELECT * FROM partidos WHERE id_partido = ?

/* Método para listar todos os partidos ordenados por sigla */
SELECT * FROM partidos ORDER BY sigla

/* Método para listar partidos por orientação política */
SELECT * FROM partidos WHERE orientacao = ? ORDER BY sigla

/* Método para verificar se um partido está sendo utilizado */
SELECT COUNT(*) FROM representantes_cg WHERE id_partido = ?