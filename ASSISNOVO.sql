create database dbfone;
use dbfone;
create table funcionarios(
idfunc int primary key auto_increment,
usuario varchar(20) not null,
login varchar(20) not null unique,
senha varchar(250) not null,
perfil  varchar(20) not null
);
insert into funcionarios (usuario,login,senha,perfil)
values('Ian Lee Gonzalez','ianlee',md5(123456),'admin');
create table clientes (
idcli int primary key auto_increment,
nome varchar (20) not null,
cep char(8) ,
endereco varchar(50) not null,
numero varchar (10) not null,
bairro varchar (50) not null ,
cidade varchar (50) not null,
complemento varchar (50) ,
uf varchar (250) not null,
doc varchar (15) not null,
telefone varchar (50) not null,
email varchar (100) not null unique
);
create table os (
os int primary key auto_increment,
dataos timestamp default current_timestamp,
idcli int not null,
idfunc int,
tipo varchar (100),
garantia date,
marca varchar (40) not null,
modelo varchar (50) not null,
acessorio varchar (50) ,
defeitocli varchar(100)  not null,
defeitotec varchar (250) ,
obs varchar (150) ,
dataretirada date ,
pecas decimal (10,2),
osstatus varchar (50) not null,
formadepagamento varchar (20),
senhacel varchar(50),
valor decimal (10,2)
);
drop table os;
drop table clientes;