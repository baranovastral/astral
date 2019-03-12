create table users(
    id int not null identity,
    login varchar(255) not null,
    password varchar(255) not null,
    unique (login),
    primary key(id)
);

create table notes(
    id int not null identity,
    message LONGVARCHAR,
    author int,
    image BLOB,
    foreign key(author) references users(id),
    primary key(id) 
);
