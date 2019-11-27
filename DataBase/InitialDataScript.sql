-- Parques
insert into park (totalparkingspaces, freeparkingspaces) values (10, 10);
insert into park (totalparkingspaces, freeparkingspaces) values (8, 8);
insert into park (totalparkingspaces, freeparkingspaces) values (5, 5);

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

-- Pedido
insert into request (requestdate, state, iduser) values ("2019-12-04", 0, 1);
