GO

SET IDENTITY_INSERT [dbo].[CATEGORIES] ON 

INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (1, N'Informatique')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (2, N'Ameublement')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (3, N'Vêtement')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (4, N'Sport & Loisir')
SET IDENTITY_INSERT [dbo].[CATEGORIES] OFF

SET IDENTITY_INSERT [dbo].[UTILISATEURS] ON 

INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (1, N'admin', N'Bluth', N'Georges', N'gbluth@campus.fr', N'0241468598', N'16 av du monde', N'49000', N'Angers', N'Pa$$w0rd', 30000, 1)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (2, N'felix', N'Bluth', N'Félix', N'fbluth@campus.fr', N'0241461708', N'11 rue du chêne vert', N'85000', N'La Roche/Yon', N'Pa$$w0rd', 14500, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (3, N'tom', N'Bluth', N'Tom', N'tbluth@campus.fr', N'0695020202', N'5 rue de la mer', N'44000', N'Nantes', N'Pa$$w0rd', 15765, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (4, N'samuel', N'Bluth', N'Samuel', N'sbluth@campus.fr', N'0788030303', N'9 chemin des gites', N'44000', N'Nantes', N'Pa$$w0rd', 157260, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (5, N'luisa', N'Bluth', N'Luisa', N'lbluth@campus.fr', N'', N'9 rue de l''océan', N'85000', N'Les Sables d''Olonnes', N'Pa$$w0rd', 657841, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (6, N'laure', N'Bluth', N'Laure', N'laurebluth@campus.fr', N'0202020202', N'rue de Nantes', N'44000', N'Nantes', N'Pa$$w0rd', 487895, 0)
INSERT INTO [dbo].[UTILISATEURS] ([no_utilisateur],[pseudo],[nom],[prenom],[email],[telephone],[rue],[code_postal],[ville],[mot_de_passe],[credit],[administrateur]) VALUES(7,'alain','Terrieur','Alain','alain.terrieur@gmail.com','0645789854','9 rue des fleurs','44870','Nantes','Pa$$w0rd',540000, 0)
SET IDENTITY_INSERT [dbo].[UTILISATEURS] OFF

SET IDENTITY_INSERT [dbo].[ARTICLES_VENDUS] ON
INSERT INTO ARTICLES_VENDUS (no_article, nom_article ,description,date_debut_enchere,date_fin_enchere,prix_initial ,prix_vente, no_utilisateur,no_categorie,etat_vente,image) 
VALUES (1, 'Pc Portable', 'Lenovo T520', GETDATE() -3, GETDATE() + 3, 10, null, 5, 1, 'EC', null)
INSERT INTO ARTICLES_VENDUS (no_article,nom_article ,description,date_debut_enchere,date_fin_enchere,prix_initial ,prix_vente, no_utilisateur,no_categorie,etat_vente,image) 
VALUES (2, 'Veste SKI', 'Veste Organic Picture', GETDATE() -3, GETDATE() + 1, 10, null, 5, 3, 'EC', null)
INSERT INTO ARTICLES_VENDUS (no_article,nom_article ,description,date_debut_enchere,date_fin_enchere,prix_initial ,prix_vente, no_utilisateur,no_categorie,etat_vente,image) 
VALUES (3, 'Armoire Bois', 'Penderie 120*50', GETDATE() -4, GETDATE() + 3, 10, null, 6, 2, 'EC', null)
INSERT INTO ARTICLES_VENDUS (no_article,nom_article ,description,date_debut_enchere,date_fin_enchere,prix_initial ,prix_vente, no_utilisateur,no_categorie,etat_vente,image) 
VALUES (4, 'Enceinte pc', 'Enciente bluetooth 2.1', GETDATE() +1, GETDATE() + 4, 10, null, 6, 1, 'CR', null)
INSERT INTO ARTICLES_VENDUS (no_article,nom_article ,description,date_debut_enchere,date_fin_enchere,prix_initial ,prix_vente, no_utilisateur,no_categorie,etat_vente,image) 
VALUES (5, 'Chaussures element', 'Chaussure element T.42', GETDATE() +1, GETDATE() + 12, 10, null, 6, 3, 'CR', null)
INSERT INTO ARTICLES_VENDUS (no_article,nom_article ,description,date_debut_enchere,date_fin_enchere,prix_initial ,prix_vente, no_utilisateur,no_categorie,etat_vente,image) 
VALUES (6, 'Surf', 'SurfBoard 6.2', GETDATE() -10, GETDATE() - 4, 10, null, 7, 4, 'VD', null)
INSERT INTO ARTICLES_VENDUS (no_article,nom_article ,description,date_debut_enchere,date_fin_enchere,prix_initial ,prix_vente, no_utilisateur,no_categorie,etat_vente,image) 
VALUES (7, 'Montre', 'Montre sport Nickson', GETDATE() -7, GETDATE() -1, 10, null, 7, 4, 'VD', null)
SET IDENTITY_INSERT [dbo].[ARTICLES_VENDUS] OFF

INSERT INTO [dbo].[ENCHERES] ([no_utilisateur],[no_article] ,[date_enchere] ,[montant_enchere])
     VALUES  (2, 1, CURRENT_TIMESTAMP, 15),	(3, 1, CURRENT_TIMESTAMP +1, 26), (4, 1, CURRENT_TIMESTAMP +2, 37),	(2, 2, CURRENT_TIMESTAMP -1, 15), (3, 2, CURRENT_TIMESTAMP, 64),(3, 3, CURRENT_TIMESTAMP, 17),
			(3, 6, CURRENT_TIMESTAMP -5, 15),(4, 7, CURRENT_TIMESTAMP -4, 67),	(3, 7, CURRENT_TIMESTAMP -3, 98)

INSERT INTO [dbo].[RETRAITS] ([no_article],[rue],[code_postal],[ville])
     VALUES (1, '9 rue des Fleurs','44300','Nantes'), (6, 'ZA Les Terres Neuves','75000','Paris'), (5, 'Super U','44210','Pornic')

GO