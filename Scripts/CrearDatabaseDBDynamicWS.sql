USE [master]
GO

/****** Object:  Database [DBCargue]    Script Date: 03/02/2016 16:23:17 ******/
IF  EXISTS (SELECT name FROM sys.databases WHERE name = N'DBDynamicWS')
BEGIN
	ALTER DATABASE [DBDynamicWS] SET SINGLE_USER WITH ROLLBACK IMMEDIATE
	DROP DATABASE [DBDynamicWS]
END
GO

USE [master]
GO

/****** Object:  Database [DBCargue]    Script Date: 03/02/2016 16:23:17 ******/
CREATE DATABASE [DBDynamicWS] 

GO