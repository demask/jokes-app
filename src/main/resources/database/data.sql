DROP SCHEMA IF EXISTS `jokes_app`;

CREATE SCHEMA `jokes_app`;

use `jokes_app`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(512) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
  
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `joke`;

CREATE TABLE `joke` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `likes` int(11) DEFAULT null,
  `dislikes` int(11) DEFAULT null,
  `category_id` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `FK_CATEGORY_ID_idx` (`category_id`),

  CONSTRAINT `FK_CATEGORY` 
  FOREIGN KEY (`category_id`) 
  REFERENCES `category` (`id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;

INSERT INTO `category` VALUES 
	(1,'Chuck Norris'),
	(2,'Mujo i Haso'),
	(3,'Mali Ivica');

/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `joke` WRITE;
/*!40000 ALTER TABLE `joke` DISABLE KEYS */;

INSERT INTO `joke` VALUES 
	(1,
    'Zasto je Chuck Norris najjaci? Zato sto vjezba dva dana dnevno'
    ,10,5,1),

	(2,
    'Dosao Mujo u pizzeriju i narucio pizzu. Konobar ga
	upita: Zelite da vam izrezem pizzu na 6 ili 12 komada? 
	Ma na 6 komada, nema sanse da pojedem 12.'
    ,5,2,2),

    (3,
    'Kaze Mujo Fati: Uf, slomio nam se umivaonik.
    Kada? Ne kada, rekao sam umivaonik!'
    ,11,3,2),
    
    (4,
    'Ucitelj je govorio o godisnjim dobima. A zatim upita:
    Koje je najbolje vrijeme za branje jabuka?
	Ivica odgovori:
    Kad je susjedov pas na lancu!'
    ,4,5,3),
    
    (5,
	'Pita mali Ivica tatu: 
    Tata, tata, zasto se baloni ne smiju smijati?
    Zato sto ce puknut od smijeha. - odgovori mu tata.'
    ,15,2,3);

/*!40000 ALTER TABLE `joke` ENABLE KEYS */;
UNLOCK TABLES;
