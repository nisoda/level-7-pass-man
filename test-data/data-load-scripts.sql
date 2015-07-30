CREATE TABLE IF NOT EXISTS 'PASSMAN'.'STORED_ACCOUNTS' (
  'ID' INT NOT NULL AUTO_INCREMENT COMMENT '',
  'MASTER_USER' VARCHAR(45) NOT NULL COMMENT '',
  'SITE' VARCHAR(45) NOT NULL COMMENT '',
  'USERNAME' VARCHAR(45) NOT NULL COMMENT '',
  'PASSWORD' VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY ('ID', 'SITE', 'MASTER_USER')  COMMENT '',
  UNIQUE INDEX 'idSTORED_ACCOUNTS_UNIQUE' ('ID' ASC)  COMMENT '')
ENGINE = InnoDB

CREATE TABLE IF NOT EXISTS 'PASSMAN'.'USER_LOGINS' (
  'ID' INT NOT NULL AUTO_INCREMENT COMMENT '',
  'USERNAME' VARCHAR(45) NOT NULL COMMENT '',
  'PASSWORD' VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY ('ID', 'USERNAME')  COMMENT '',
  UNIQUE INDEX 'id_UNIQUE' ('ID' ASC)  COMMENT '')
ENGINE = InnoDB

-- load USER_LOGINS --

INSERT INTO USER_LOGINS (username, password) VALUES ('Leacee', 'yahXoo8Oo');
INSERT INTO USER_LOGINS (username, password) VALUES ('Jament', 'Oovaiheaxo4ei');
INSERT INTO USER_LOGINS (username, password) VALUES ('Sibluself', 'AhfieNo0i');
INSERT INTO USER_LOGINS (username, password) VALUES ('Conothe', 'oCot1roo6');
INSERT INTO USER_LOGINS (username, password) VALUES ('Swerown', 'mahphee3Iu');



-- load STORED_ACCOUNTS --

INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Leacee', 'http://www.donothingfor2minutes.com', 'Heach1963', 'aifiRa0ed0a');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Leacee', 'http://weavesilk.com', 'Redet1983', 'Giechaiph3ee');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Leacee', 'http://thequietplaceproject.com/thethoughtsroom/', 'Appose', 'ii9IeWee');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Leacee', 'http://hereistoday.com', 'Weepty', 'taeb0uu1Tuph');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Leacee', 'http://tonematrix.audiotool.com', '', '');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Jament', 'http://www.rainymood.com', 'Afte1959', 'ahngao9Laigh');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Jament', 'http://www.picassohead.com/create.html', 'Musbang', 'ievae0Uupah');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Jament', 'http://eelslap.com', 'Aniffeepull', 'Ewii6yiki');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Jament', 'http://29a.ch/sandbox/2011/neonflames/', 'Tablead', 'dei9EiPai');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Jament', 'http://heeeeeeeey.com/', 'Quired', 'Hiejus4ajei');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Sibluself', 'http://cat-bounce.com/', 'Upway1990', 'MoaGoo4ai');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Sibluself', 'http://www.zefrank.com/snm/index.html', 'Housee', 'Daew2Aeg');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Sibluself', 'http://www.jacksonpollock.org/', 'Withavercoad', 'Pe1ahlei');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Sibluself', 'http://www.staggeringbeauty.com/', 'Shish1946', 'qua1ahPaiY');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Sibluself', 'http://www.unwrong.com/projects/fractalexplorer/', 'Aunce1975', 'chiuFi4m');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Conothe', 'http://www.fallingfalling.com/', 'Almosery', 'zee2phaeGh9');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Conothe', 'http://www.stumbleupon.com/su/7qCN6b/www.panoramas.dk/mars/mars-greeleyhaven2/tour.swf/', 'Doccoakere84', 'ahxohGh5to');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Conothe', 'http://thequietplaceproject.com/thequietplace', 'Godlenew', 'oi2iev6ap1Nah');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Conothe', 'http://www.barcinski-jeanjean.com/entries/line3d/', 'Milise', 'ohHai4Hohk');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Conothe', 'http://www.barcinski-jeanjean.com/entries/endlessintrestingness/', 'Faccon', 'ohs9moaD0m');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Swerown', 'http://www.nullingthevoid.com/', 'Bleanto31', 'OhFahpie1');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Swerown', 'http://maninthedark.com/', 'Thavivelball', 'aePiejii4Lie');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Swerown', 'http://www.stumbleupon.com/su/4nBDL4/blog.soulwire.co.uk/wp-content/uploads/2010/10/tonfall-sequencer.swf/', 'Sonfigh', 'xa6Roh2na');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Swerown', 'http://secretsfornicotine.com/', 'Ladon1946', 'KohShie3phie');
INSERT INTO STORED_ACCOUNTS (master_user, site, username, password) VALUES ('Swerown', 'http://drawminos.com/', 'Foreat1976', 'terijefieW6ah');