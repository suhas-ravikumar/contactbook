DROP TABLE IF EXISTS contactdata;
  
CREATE TABLE contactdata (
  emailId VARCHAR(255) NOT NULL PRIMARY KEY,
  name VARCHAR(255),
  mobileNo BIGINT,
  organization VARCHAR(255),
  designation VARCHAR(255),
  streetName VARCHAR(255),
  city VARCHAR(255),
  stateName VARCHAR(255),
  pinCode INT(6) NOT NULL
);

INSERT INTO contactdata (emailId, name, mobileNo, organization, designation, streetName, city,stateName, pinCode) VALUES
  ('john.doe@gmail.com','John Doe',1234567689, 'ABC','Manager','1st Street', 'Delhi', 'Delhi', 101121),
  ('jane.doe@gmail.com','Jane Doe',9876543211, 'PQR','Lead','2st Street', 'Mumbai', 'MH', 401154),
  ('iron.man@avengers.com','Iron Man',9994441115, 'XYZ','Developer','3st Street', 'Bengaluru', 'KA', 510100);