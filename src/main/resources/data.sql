INSERT INTO CLIENTES
(DNI,NOMBRE, APELLIDO,FECHANACIMIENTO)
values
(10100100,'Mario' , 'Santos',  '1963-10-04'),
(10100200,'Gabriel', 'Medina',  '1960-11-20'),
(10100300,'Emilio' , 'Ravena',  '1963-02-10'),
(10100400,'Pablo' , 'Lampone',  '1969-07-05'),
(10100500,'Fabiana' , 'Cantilo',  '1959-03-03');

INSERT INTO PRODUCTOS
(DESCRIPCION,PRECIO,STOCK,CODIGO)
values
('Guitarra Electrica Gibson Epiphone' ,65000,4,'EGG101'),
('Guitarra Electrica Gibson Modern Collection' ,54200,1,'EGG102'),
('Guitarra Electrica Gibson Epiphone para Zurdos' ,72000,2,'EGG103'),
('Guitarra Acustica Gibson Epiphone Slash J-45' ,282000,2,'AGG101'),
('Bajo Gibson Kramer Striker 422s' ,102000,2,'EBG101'),
('Bajo Gibson Flying V Bass' ,762000,1,'EBG102'),
('Bajo Gibson Eb4 Satin Vintage SunBurst' ,762000,1,'EBG103');

INSERT INTO COMPROBANTE(IDCLIENTE,NOMBREAPELLIDO,IDPRODUCTO,DESCRIPCION,CANTIDAD,FECHA,TOTAL)
values
(4,'Pablo Lampone',1,'Guitarra Electrica Gibson Epiphone',1,'2022-6-18',65000);