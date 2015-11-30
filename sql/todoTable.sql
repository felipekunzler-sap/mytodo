CREATE TABLE todo(
   id INT NOT NULL AUTO_INCREMENT,
   id_user INT NOT NULL, 
   description VARCHAR(255) NOT NULL,
   done BOOLEAN NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (id_user) REFERENCES user(id)
);