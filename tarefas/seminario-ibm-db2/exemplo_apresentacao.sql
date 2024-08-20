create database teste

connect to teste user db2inst1 using pass

create table departamentos (id int primary key not null, nome varchar(100));
create table empregados (id int primary key not null, nome varchar(100), salario decimal(10, 2), departamento_id int, foreign key (departamento_id) references departamentos(id));

insert into departamentos (id, nome) values (1, 'Recursos Humanos');
insert into departamentos (id, nome) values (2, 'Financeiro');
insert into departamentos (id, nome) values (3, 'Desenvolvimento');
insert into departamentos (id, nome) values (4, 'Suporte');
insert into departamentos (id, nome) values (5, 'Vendas');

insert into empregados (id, nome, salario, departamento_id) values (1, 'João Silva', 3000, 1);
insert into empregados (id, nome, salario, departamento_id) values (2, 'Maria Oliveira', 3500, 2);
insert into empregados (id, nome, salario, departamento_id) values (3, 'Carlos Santos', 4000, 3);
insert into empregados (id, nome, salario, departamento_id) values (4, 'Ana Costa', 4500, 1);
insert into empregados (id, nome, salario, departamento_id) values (5, 'Fernanda Almeida', 5000.00, 2);

select empregados.nome as empregado, departamentos.nome as departamento from empregados join departamentos on empregados.departamento_id = departamentos.id;

select nome, salario, sum(salario) over (order by salario) as salario_acumulado from empregados;
