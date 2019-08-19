DROP TABLE IF EXISTS CLIENTS;
 
CREATE TABLE CLIENTS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  clientCode VARCHAR(250) NOT NULL,
  clientName VARCHAR(250) NOT NULL
);
 
INSERT INTO CLIENTS (clientCode, clientName) VALUES
  ('BLACKR', 'Dangote'),
  ('MILL', 'More Info Ltd.'),
  ('TRS', 'Trade Reporter Inc.'),
  ('MPLMT', 'Misc PLMT'),
  ('UBS', 'UBS bank'),
  ('CITI', 'CITI Bank'),
  ('JP', 'JP Morgan Bank');
  
  
DROP TABLE IF EXISTS STOCKS;
 
CREATE TABLE STOCKS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  stockCode VARCHAR(250) NOT NULL,
  lastPrice VARCHAR(250) NOT NULL
);

INSERT INTO STOCKS (stockCode, lastPrice) VALUES
  ('IBM', 120.45),
  ('GOOG', 230.15),
  ('PTS',34.55),
  ('APPL',27.25),
  ('INFY',78.40),
  ('TESLA', 56.95),
  ('TAMP', 66.65);





