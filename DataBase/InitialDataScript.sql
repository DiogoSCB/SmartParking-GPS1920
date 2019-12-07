-- Parques
insert into park (name) values ("Funcionários");
insert into park (name) values ("Dirigentes");
insert into park (name) values ("Clientes");

-- Lugares de estacionamento para cada parque
insert into parkingspace (reserved, idpark) values (false, 1);
insert into parkingspace (reserved, idpark) values (false, 1);
insert into parkingspace (reserved, idpark) values (false, 1);
insert into parkingspace (reserved, idpark) values (false, 1);
insert into parkingspace (reserved, idpark) values (false, 1);
insert into parkingspace (reserved, idpark) values (false, 1);
insert into parkingspace (reserved, idpark) values (false, 1);
insert into parkingspace (reserved, idpark) values (false, 1);
insert into parkingspace (reserved, idpark) values (false, 1);
insert into parkingspace (reserved, idpark) values (false, 1);
insert into parkingspace (reserved, idpark) values (false, 1);

insert into parkingspace (reserved, idpark) values (false, 2);
insert into parkingspace (reserved, idpark) values (false, 2);
insert into parkingspace (reserved, idpark) values (false, 2);
insert into parkingspace (reserved, idpark) values (false, 2);
insert into parkingspace (reserved, idpark) values (false, 2);
insert into parkingspace (reserved, idpark) values (false, 2);
insert into parkingspace (reserved, idpark) values (false, 2);
insert into parkingspace (reserved, idpark) values (false, 2);

insert into parkingspace (reserved, idpark) values (false, 3);
insert into parkingspace (reserved, idpark) values (false, 3);
insert into parkingspace (reserved, idpark) values (false, 3);
insert into parkingspace (reserved, idpark) values (false, 3);
insert into parkingspace (reserved, idpark) values (false, 3);

-- Utilizador
insert into user (name, licenseplate, entrydate, departuredate, email, idparkingspace, idpark) values ("Nuno Santos", "69FC44", null, null,  "nuninho@gmail.com", null, 1);
insert into user (name, licenseplate, entrydate, departuredate, email, idparkingspace, idpark) values ("Ana Santos", "30FF20", null, null,  "ok@gmail.com", null, 1);
insert into user (name, licenseplate, entrydate, departuredate, email, idparkingspace, idpark) values ("Miguel Santos", "70AA70", null, null,  "adeus@gmail.com", null, 1);

-- Pedido
insert into request (requestdate, state, iduser) values ("2019-12-04", 0, 1);
