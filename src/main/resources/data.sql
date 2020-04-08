Drop table if exists cities;

create table cities(
	id int 			auto_increment NOT NULL primary key,
	cityname 			varchar(50),
	yandcitydescribe	varchar(50),
	gocitydescribe 	varchar(50)
);

insert into cities (cityName, yandCityDescribe, goCityDescribe) values
	('Екатеринбург','yekaterinburg','-yekaterinburg-4517/'),
	('Верхняя Пышма','verhnyaya-pyshma','-verkhnyaya-pyshma-12758/'),
	('Сочи','sochi','-sochi-5233/');
	
	