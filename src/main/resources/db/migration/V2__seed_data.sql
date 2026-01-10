-- Insert default admin user
-- Password is 'admin123' encrypted with BCrypt
INSERT INTO usuarios (username, password, nome, ativo) 
VALUES ('admin', '$2b$12$kqAST3ZXicZkhnfGd9NwtujojsTZrBg9oJW0gduAMIAWqu4kVtI7K', 'Administrador', true);

-- Insert admin role
INSERT INTO usuario_roles (usuario_id, role) 
VALUES ((SELECT id FROM usuarios WHERE username = 'admin'), 'ROLE_ADMIN');

-- Insert sample cost centers
INSERT INTO centro_custo (descricao) VALUES ('Alimentação');
INSERT INTO centro_custo (descricao) VALUES ('Transporte');
INSERT INTO centro_custo (descricao) VALUES ('Moradia');
INSERT INTO centro_custo (descricao) VALUES ('Saúde');
INSERT INTO centro_custo (descricao) VALUES ('Educação');
INSERT INTO centro_custo (descricao) VALUES ('Lazer');
INSERT INTO centro_custo (descricao) VALUES ('Investimentos');
INSERT INTO centro_custo (descricao) VALUES ('Outros');
