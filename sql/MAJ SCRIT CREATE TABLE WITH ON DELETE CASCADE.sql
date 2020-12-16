

ALTER TABLE Articles_Vendus DROP CONSTRAINT articles_utilisateurs_fk;
ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_utilisateurs_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
	ON DELETE CASCADE
    ON UPDATE NO ACTION 

ALTER TABLE Encheres DROP CONSTRAINT ventes_utilisateurs_fk;
ALTER TABLE ENCHERES
    ADD CONSTRAINT ventes_utilisateurs_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur )
	ON DELETE CASCADE
    ON UPDATE NO ACTION 


ALTER TABLE UTIlISATEURS DROP CONSTRAINT utilisateurs_email_uq;

ALTER TABLE UTIlISATEURS ALTER COLUMN email VARCHAR(50) NOT NULL

ALTER TABLE UTILISATEURS ADD constraint utilisateurs_email_uq UNIQUE (email)

	


