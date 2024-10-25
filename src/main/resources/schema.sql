CREATE TABLE series (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    total_temporadas INT,
    nota_imdb DOUBLE,
    genero VARCHAR(255), -- Se 'Categoria' for uma enum ou tabela separada, pode ser ajustado conforme necessário
    sinopse TEXT,
    url_imagem VARCHAR(255),
    ano_lancamento VARCHAR(4), -- Pode ser ajustado para INT ou outro tipo dependendo da lógica
    atores TEXT
);