USE [ENCHERES_BDD]
GO
SET IDENTITY_INSERT [dbo].[CATEGORIES] ON 

INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (1, N'Informatique')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (2, N'Ameublement')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (3, N'Vêtement')
INSERT [dbo].[CATEGORIES] ([no_categorie], [libelle]) VALUES (4, N'Sport & Loisir')
SET IDENTITY_INSERT [dbo].[CATEGORIES] OFF
SET IDENTITY_INSERT [dbo].[UTILISATEURS] ON 

INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (1, N'admin', N'Bluth', N'Georges', N'gbluth@campus.fr', N'0241468598', N'16 av du monde', N'49000', N'Angers', N'Pa$$w0rd', 0, 1)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (2, N'felix', N'Bluth', N'Félix', N'fbluth@campus.fr', N'0241461708', N'11 rue du chêne vert', N'85000', N'La Roche/Yon', N'Pa$$w0rd', 100, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (3, N'tom', N'Bluth', N'Tom', N'tbluth@campus.fr', N'0695020202', N'5 rue de la mer', N'44000', N'Nantes', N'Pa$$w0rd', 65, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (4, N'samuel', N'Bluth', N'Samuel', N'sbluth@campus.fr', N'0788030303', N'9 chemin des gites', N'44000', N'Nantes', N'Pa$$w0rd', 260, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (5, N'luisa', N'Bluth', N'Luisa', N'lbluth@campus.fr', N'', N'9 rue de l''océan', N'85000', N'Les Sables d''Olonnes', N'Pa$$w0rd', 0, 0)
INSERT [dbo].[UTILISATEURS] ([no_utilisateur], [pseudo], [nom], [prenom], [email], [telephone], [rue], [code_postal], [ville], [mot_de_passe], [credit], [administrateur]) VALUES (6, N'laure', N'Bluth', N'Laure', N'laurebluth@campus.fr', N'0202020202', N'rue de Nantes', N'44000', N'Nantes', N'Pa$$w0rd', 0, 0)
SET IDENTITY_INSERT [dbo].[UTILISATEURS] OFF

SET IDENTITY_INSERT [dbo].[ARTICLES_VENDUS] ON
INSERT INTO [dbo].[ARTICLES_VENDUS]
           ([no_article]
		   ,[nom_article]
           ,[description]
           ,[date_debut_enchere]
           ,[date_fin_enchere]
           ,[prix_initial]
           ,[prix_vente]
           ,[no_utilisateur]
           ,[no_categorie]
           ,[etat_vente]
           ,[image])
     VALUES
           (1
		   ,'article 1'
           ,'description article 1'
           ,getdate()
           ,getdate() + 1
           ,10
           ,null
           ,1
           ,1
           ,'EC'
           ,null);
INSERT INTO [dbo].[ARTICLES_VENDUS]
           ([no_article]
		   ,[nom_article]
           ,[description]
           ,[date_debut_enchere]
           ,[date_fin_enchere]
           ,[prix_initial]
           ,[prix_vente]
           ,[no_utilisateur]
           ,[no_categorie]
           ,[etat_vente]
           ,[image])
     VALUES
           (2
		   ,'article 2'
           ,'description article 2'
           ,getdate() + 1
           ,getdate() + 3
           ,5
           ,null
           ,2
           ,2
           ,DEFAULT
           ,null);
INSERT INTO [dbo].[ARTICLES_VENDUS]
           ([no_article]
		   ,[nom_article]
           ,[description]
           ,[date_debut_enchere]
           ,[date_fin_enchere]
           ,[prix_initial]
           ,[prix_vente]
           ,[no_utilisateur]
           ,[no_categorie]
           ,[etat_vente]
           ,[image])
     VALUES
           (3
		   ,'article 3'
           ,'description article 3'
           ,getdate()
           ,getdate() + 3
           ,15
           ,null
           ,3
           ,2
           ,'EC'
           ,null);
SET IDENTITY_INSERT [dbo].[ARTICLES_VENDUS] OFF