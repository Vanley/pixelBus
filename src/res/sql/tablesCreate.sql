CREATE TABLE productdb(id VARCHAR(32) PRIMARY KEY,displayName VARCHAR(128), description VARCHAR(512), category VARCHAR(128), seller VARCHAR(32), price FLOAT, quantity INT, creationTime BIGINT)
CREATE TABLE sellerdb(ppsn VARCHAR(32) PRIMARY KEY,firstName VARCHAR(64), lastName VARCHAR(64), email VARCHAR(64))
