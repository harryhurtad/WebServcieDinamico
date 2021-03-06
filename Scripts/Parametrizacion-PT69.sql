USE  DBDynamicWS
GO

--CONSTANTES_PARAMETRIZACION_WS
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'CONSTANTES_PARAMETRIZACION_WS')
	BEGIN 
	DROP TABLE CONSTANTES_PARAMETRIZACION_WS
END
--PUBLICADOR_WS_ESTADO_INVOC_OPER
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_ESTADO_INVOC_OPER')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_ESTADO_INVOC_OPER
END

--PUBLICADOR_WS_ESTRUCTURA_OPERACIONES
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_ESTRUCTURA_OPERACIONES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_ESTRUCTURA_OPERACIONES
END

--PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN
END

--METODOS_OPERACIONES_JSF
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'METODOS_OPERACIONES_JSF')
	BEGIN 
	DROP TABLE METODOS_OPERACIONES_JSF
END

--PARAMETROS_TO_MAP_INST_CLASS
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PARAMETROS_TO_MAP_INST_CLASS')
	BEGIN 
	DROP TABLE PARAMETROS_TO_MAP_INST_CLASS
END

--PARAMETROS_TO_MAP_METODOS
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PARAMETROS_TO_MAP_METODOS')
	BEGIN 
	DROP TABLE PARAMETROS_TO_MAP_METODOS
END


--PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES')
	BEGIN 
	--TRUNCATE TABLE PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE;
	DROP TABLE PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES;
END


--PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE
END





--PUBLICADOR_WS_METODOS_OPERACIONES
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_METODOS_OPERACIONES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_METODOS_OPERACIONES
END








--PUBLICADOR_WS_PARAMETROS_OPERACIONES
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_PARAMETROS_OPERACIONES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_PARAMETROS_OPERACIONES
END

--PUBLICADOR_WS_OPERACIONES
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_OPERACIONES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_OPERACIONES
END

--PUBLICADOR_WS_DEPENDENCIA_CLASES
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_DEPENDENCIA_CLASES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_DEPENDENCIA_CLASES
END

--PUBLICADOR_WS_CLASES_ATRIBUTOS
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_CLASES_ATRIBUTOS')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_CLASES_ATRIBUTOS
END


--PUBLICADOR_WS_ESTRUCTURA
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_ESTRUCTURA')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_ESTRUCTURA
END

--PUBLICADOR_WS_CLASES
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_CLASES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_CLASES
END


GO


-- Se crea Tablas de definicion de Ws
--PUBLICADOR_WS_ESTRUCTURA
--PUBLICADOR_WS_CLASES
--PUBLICADOR_WS_CLASES_ATRIBUTOS
--PUBLICADOR_WS_DEPENDENCIA_CLASES
--PUBLICADOR_WS_OPERACIONES
--PUBLICADOR_WS_PARAMETROS_OPERACIONES
--PARAMETROS_TO_MAP_INST_CLASS
--PUBLICADOR_WS_METODOS_OPERACIONES
--PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES
--PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE
--PARAMETROS_TO_MAP_METODOS
--PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN
--PUBLICADOR_WS_ESTRUCTURA_OPERACIONES
--PUBLICADOR_WS_ESTADO_INVOC_OPER
--METODOS_OPERACIONES_JSF
--CONSTANTES_PARAMETRIZACION_WS



-- CONSTANTES_PARAMETRIZACION_WS
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'CONSTANTES_PARAMETRIZACION_WS')
	BEGIN
	CREATE TABLE CONSTANTES_PARAMETRIZACION_WS
	(
		nombre_parametro VARCHAR(100)  NOT NULL ,
		valor_parametro VARCHAR(100)  NOT NULL,
		habilitado BIT NOT NULL,		
		PRIMARY KEY (nombre_parametro),
		
		
	);

END

-- PUBLICADOR_WS_CLASES
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_CLASES')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_CLASES
	(
		idClase INT IDENTITY(1,1),
		nombreCLase VARCHAR(100)  NOT NULL,
		aliasClase VARCHAR(100) NULL,
		packageClass VARCHAR(100)  NOT NULL,
		archivoXSD VARCHAR(max) NULL, 	
	    JNDI VARCHAR(100) NULL,
		PRIMARY KEY (idClase),
		CONSTRAINT AK_PUBLICADOR_WS_CLASES_packageClass_nombreCLase UNIQUE(packageClass,nombreCLase)
		
	);

END


--PUBLICADOR_WS_CLASES_ATRIBUTOS
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_CLASES_ATRIBUTOS')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_CLASES_ATRIBUTOS
	(
		idAtributo INT IDENTITY(1,1),
		idClasePrincipal INT NOT NULL,
		nombreAtributo VARCHAR(100)  NOT NULL,
		idClaseAtributo INT NOT NULL,	
		
		PRIMARY KEY (idAtributo),
			CONSTRAINT FK_PUBLICADOR_WS_CLASES_ATRIBUTOS_idClasePrincipal
		FOREIGN KEY (idClasePrincipal) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
		CONSTRAINT FK_PUBLICADOR_WS_CLASES_ATRIBUTOS_idClaseAtributo
		FOREIGN KEY (idClaseAtributo)		
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
	);

END


-- PUBLICADOR_WS_ESTRUCTURA
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_ESTRUCTURA')	
	BEGIN
	CREATE TABLE PUBLICADOR_WS_ESTRUCTURA
	(
		idWs INT IDENTITY(1,1),
		nombreWs VARCHAR(100) NOT NULL,
		portName VARCHAR(100)  NOT NULL,	
		targetNamespace VARCHAR(100)  NOT NULL,
		package_ws VARCHAR(100)  NOT NULL,		
		descripcionWs VARCHAR(100),
		contextoWS VARCHAR(max)  NOT NULL,
		idClassEJBRemote  INT NOT NULL,		
		habilitado      BIT NOT NULL,
		contexto_app_cliente VARCHAR(max) NULL,
		
		PRIMARY KEY (idWs),
		
		CONSTRAINT FK_PUBLICADOR_WS_ESTRUCTURA_idClassEJBRemote
		FOREIGN KEY (idClassEJBRemote) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),

		CONSTRAINT AK_PUBLICADOR_WS_ESTRUCTURA_nombreWs UNIQUE(nombreWs)

	);

END



-- PUBLICADOR_WS_DEPENDENCIA_CLASES
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_DEPENDENCIA_CLASES')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_DEPENDENCIA_CLASES
	(
	     
		idClassDependencia INT NOT NULL ,
		idClase INT NOT NULL ,
		ordenCompilacion INT NOT NULL,	
		
		PRIMARY KEY (idClassDependencia,idClase),
		
		CONSTRAINT FK_PUBLICADOR_WS_DEPENDENCIA_CLASES_idClase
		FOREIGN KEY (idClase) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
		CONSTRAINT FK_PUBLICADOR_WS_DEPENDENCIA_CLASES_idClassDependencia
		FOREIGN KEY (idClassDependencia)		
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
		
	);

END

-- PUBLICADOR_WS_OPERACIONES
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_OPERACIONES')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_OPERACIONES
	(
		idOperacion INT IDENTITY(1,1),
		nombreOperacion VARCHAR(100)  NOT NULL,		
		descripcionOperacion VARCHAR(100) NULL,
		metodoOperacion  VARCHAR(100)  NOT NULL,	
		idClaseRetornoOperacion INT,
		PRIMARY KEY (idOperacion),
		
			
		CONSTRAINT FK_PUBLICADOR_WS_OPERACIONES_idClaseRetornoOperacion
		FOREIGN KEY (idClaseRetornoOperacion) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),

		CONSTRAINT AK_PUBLICADOR_WS_OPERACIONES UNIQUE(nombreOperacion),
		
	);

END

-- PUBLICADOR_WS_PARAMETROS_OPERACIONES
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_PARAMETROS_OPERACIONES')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_PARAMETROS_OPERACIONES
	(
		idParametroOperacion INT IDENTITY(1,1),
		idOperacion INT,
		nombreParametro VARCHAR(100),
		idClaseLlave INT,
		idClaseValor INT,
		idClaseCollection INT,
		ordenParametro INT,

		PRIMARY KEY (idParametroOperacion),

		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_OPERACIONES_idOperacion
		FOREIGN KEY (idOperacion) 
		REFERENCES PUBLICADOR_WS_OPERACIONES(idOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_OPERACIONES_idClaseLlave
		FOREIGN KEY (idClaseLlave) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),

		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_OPERACIONES_idClaseValor
		FOREIGN KEY (idClaseValor) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_OPERACIONES_idClaseCollection
		FOREIGN KEY (idClaseCollection) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
	);

END






-- PUBLICADOR_WS_METODOS_OPERACIONES
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_METODOS_OPERACIONES')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_METODOS_OPERACIONES
	(
		idMetodoOperacion INT IDENTITY(1,1),		
		metodoOperacion VARCHAR(100) NOT NULL,
		descripcionMetdOperacion VARCHAR(100) null,
		idClasePrincipalMetdOperacion int NOT NULL,	
		idClaseMetdRetornoOperacion int NULL,				
		PRIMARY KEY (idMetodoOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_METODOS_OPERACIONES_idClasePrincipalMetdOperacion
		FOREIGN KEY (idClasePrincipalMetdOperacion) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
		CONSTRAINT FK_PUBLICADOR_WS_METODOS_OPERACIONES_idClaseMetdRetornoOperacion
		FOREIGN KEY (idClaseMetdRetornoOperacion) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),	
		
		
	);

END

--PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE
	(
		idEjecucionOperacion INT IDENTITY(1,1),
		idOperacion INT NOT NULL,
		idMetodoOperacion INT ,
		idClaseToBuildInst INT ,
		idClassManageBeanFind INT ,		
		ordenEjecucion INT NOT NULL,		
		PRIMARY KEY (idEjecucionOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE_idOperacion
		FOREIGN KEY (idOperacion) 
		REFERENCES PUBLICADOR_WS_OPERACIONES(idOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE_idMetodoOperacion
		FOREIGN KEY (idMetodoOperacion) 
		REFERENCES PUBLICADOR_WS_METODOS_OPERACIONES(idMetodoOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE_idClaseToBuildInst
		FOREIGN KEY (idClaseToBuildInst) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
	
		CONSTRAINT FK_PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE_idClassManageBeanFind
		FOREIGN KEY (idClassManageBeanFind) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),

		
	);

END


--PARAMETROS_TO_MAP_INST_CLASS
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PARAMETROS_TO_MAP_INST_CLASS')
	BEGIN
	CREATE TABLE PARAMETROS_TO_MAP_INST_CLASS
	(
		idParamToMapInstClass INT IDENTITY(1,1),
		idEjecucionOperacion  INT NOT NULL,
		idParametroOperacionWS INT NOT NULL,
		nombreParametroGetWS  VARCHAR(100) ,
		idAtributoToSetValueClass  INT NOT NULL,		
		

		PRIMARY KEY (idParamToMapInstClass),

		CONSTRAINT FK_PARAMETROS_TO_MAP_INST_CLASS_idParamToMapInstClass
		FOREIGN KEY (idParametroOperacionWS) 
		REFERENCES PUBLICADOR_WS_PARAMETROS_OPERACIONES(idParametroOperacion),
		
		CONSTRAINT FK_PARAMETROS_TO_MAP_INST_CLASS_idAtributoToSetValueClass
		FOREIGN KEY (idAtributoToSetValueClass) 
		REFERENCES PUBLICADOR_WS_CLASES_ATRIBUTOS(idAtributo),
		
		CONSTRAINT FK_PARAMETROS_TO_MAP_INST_CLASS_idEjecucionOperacion
		FOREIGN KEY (idEjecucionOperacion) 
		REFERENCES PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idEjecucionOperacion),

		
	);

END


-- PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES
	(
		idParametroOperacion INT IDENTITY(1,1),
		idMetodoOperacion INT,
		nombreParametro VARCHAR(100),	
		idClase INT,
		ordenParametro INT NOT NULL,
		

		PRIMARY KEY (idParametroOperacion),

		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES_idMetodoOperacion
		FOREIGN KEY (idMetodoOperacion) 
		REFERENCES PUBLICADOR_WS_METODOS_OPERACIONES(idMetodoOperacion),		
		
		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES_idClase
		FOREIGN KEY (idClase) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
	
	);

END



--PARAMETROS_TO_MAP_METODOS
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PARAMETROS_TO_MAP_METODOS')
	BEGIN
	CREATE TABLE PARAMETROS_TO_MAP_METODOS
	(
		idParamToMapMetodo INT IDENTITY(1,1),
		idParametroOperacion  INT  NULL,
		idParametroOperacionWS INT  NULL,
		nombreParametroGetWS  VARCHAR(100) NULL,
		idResultEjecInstClass INT	NULL,	
		

		PRIMARY KEY (idParamToMapMetodo),

		CONSTRAINT FK_PARAMETROS_TO_MAP_METODOS_idParamToMapInstClass
		FOREIGN KEY (idParametroOperacionWS) 
		REFERENCES PUBLICADOR_WS_PARAMETROS_OPERACIONES(idParametroOperacion),
		
		CONSTRAINT FK_PARAMETROS_TO_MAP_METODOS_idParametroOperacion
		FOREIGN KEY (idParametroOperacion) 
		REFERENCES PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES(idParametroOperacion),
		
			
		CONSTRAINT FK_PARAMETROS_TO_MAP_METODOS_idResultEjecInstClass
		FOREIGN KEY (idResultEjecInstClass) 
		REFERENCES PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idEjecucionOperacion),

		
	);

END



-- PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN //get y set del ManageBean de JSF
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN
	(
	    idMetodoToMap INT IDENTITY(1,1),
		idOperacion INT,
		idParametroOperacionWS INT NOT NULL,
		nombreGetFieldWS VARCHAR(100),		
		idClaseManageBean INT NOT NULL,
		idClaseFielManageBean INT NOT NULL,
		nombreAtributoManageBean VARCHAR(100) NOT NULL,
		
		
		
		PRIMARY KEY (idMetodoToMap),
		
		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN_idParametroOperacionWS
		FOREIGN KEY (idParametroOperacionWS) 
		REFERENCES PUBLICADOR_WS_PARAMETROS_OPERACIONES(idParametroOperacion),	

		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN_idClaseManageBean
		FOREIGN KEY (idClaseManageBean) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN_idClaseFielManageBean
		FOREIGN KEY (idClaseFielManageBean) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),

		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN_idOperacion
		FOREIGN KEY (idOperacion)
		REFERENCES PUBLICADOR_WS_OPERACIONES(idOperacion),
		
		
	);

END

-- PUBLICADOR_WS_ESTRUCTURA_OPERACIONES
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_ESTRUCTURA_OPERACIONES')	
	BEGIN
	CREATE TABLE PUBLICADOR_WS_ESTRUCTURA_OPERACIONES
	(
		idWs INT NOT NULL,
		idOperacion INT NOT NULL,
		
		
		CONSTRAINT FK_PUBLICADOR_WS_ESTRUCTURA_OPERACIONES_idWs
		FOREIGN KEY (idWs) 
		REFERENCES PUBLICADOR_WS_ESTRUCTURA(idWs),
		
		CONSTRAINT FK_PUBLICADOR_WS_ESTRUCTURA_OPERACIONES_idMetodoOperacion
		FOREIGN KEY (idOperacion) 
		REFERENCES PUBLICADOR_WS_OPERACIONES(idOperacion),
	);

END

--ESTADO PUBLICADOR_WS_ESTADO_INVOC_OPER
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_ESTADO_INVOC_OPER')	
	BEGIN
	CREATE TABLE PUBLICADOR_WS_ESTADO_INVOC_OPER
	(
		idEstOperacion INT IDENTITY(1,1),
		idOperacion INT NOT NULL,
		idRequest VARCHAR(max) NOT NULL,
		estado VARCHAR(50)  NOT NULL,		
		excepcion VARCHAR(100)  NULL,	
		fechaInicio datetime,
		fechaFinalizacion datetime,
		PRIMARY KEY (idEstOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_ESTADO_INVOC_OPER_idOperacion
		FOREIGN KEY (idOperacion) 
		REFERENCES PUBLICADOR_WS_OPERACIONES(idOperacion),
	);

END

--METODOS_OPERACIONES_JSF
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'METODOS_OPERACIONES_JSF')	
	BEGIN
	CREATE TABLE METODOS_OPERACIONES_JSF
	(
		idMetodoOperacionJSF INT IDENTITY(1,1),
		idMetodoOperacion INT NOT NULL,
		idClassParamEvent INT NOT NULL,
		idClassUIComponentJSF INT NOT NULL,
		idClassInternalValEvent INT NOT NULL,
		idParametroOperacionWS INT NOT NULL,
		nombreGetAtributoParamWS VARCHAR(50)  NULL,		
		

		PRIMARY KEY (idMetodoOperacionJSF),
		
		CONSTRAINT FK_METODOS_OPERACIONES_JSF_idMetodoOperacion
		FOREIGN KEY (idMetodoOperacion) 
		REFERENCES PUBLICADOR_WS_METODOS_OPERACIONES(idMetodoOperacion),
		
		CONSTRAINT FK_METODOS_OPERACIONES_JSF_idClassParamEvent
		FOREIGN KEY (idClassParamEvent) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
		CONSTRAINT FK_METODOS_OPERACIONES_JSF_idParametroOperacionWS
		FOREIGN KEY (idParametroOperacionWS) 
		REFERENCES PUBLICADOR_WS_PARAMETROS_OPERACIONES(idParametroOperacion),
		
		CONSTRAINT FK_METODOS_OPERACIONES_JSF_idClassInternalValEvent
		FOREIGN KEY (idClassInternalValEvent) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
		CONSTRAINT FK_METODOS_OPERACIONES_JSF_idClassUIComponentJSF
		FOREIGN KEY (idClassUIComponentJSF) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
	);

END

-- Constantes
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('DIRECTORIO_ENTRADA','D:\\Unico\\DynamicClassCompile\\scr',1);
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('DIRECTORIO_SALIDA','D:\\Unico\\DynamicClassCompile\\output',1);
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('HOST_WS','10.130.1.69',1);
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('PUERTO_WS','8101',1);	
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('CONTEXTO_WEB_APLICACION_CLIENTE','/CentroComputoWeb',1);		
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('RUTA_PROPERTIES_LOG4J','D:\\Unico\\DynamicClassCompile\\log4j.properties',1);		
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('RUTA_LOG_WS','D:\\Unico\\DynamicClassCompile',1);
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('RUTA_PROPERTIES_EJB_SOURCE','D:\DynamicWSUtil\ejb_source.properties',1);

-- Clases
-- Se usan para definir las clases cuando el cliente las requiere (Reflexion), las clases que el cliente necesita para realizar el flujo funcional
-- - se definen los objetos que representan los parámetros del webservice: 
-- - Para definir los objetos que utilizan los métodos de las operaciones del flujo funcional
-- - ManagedBean que involucran el flujo funcional
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.unico.ws.helper','WSDynamicUtil',NULL,NULL);
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD,JNDI) VALUES('com.unico.ws.interfaces','DynamicExcecutionSessionBeanRemote',NULL,NULL,'ejb/DynamicExcecutionSessionBean');
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('java.lang','Integer',NULL,NULL);--6
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('','int',NULL,NULL);--6
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('java.lang','String',NULL,NULL);--6
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('java.util','Date',NULL,NULL);--6
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('javax.faces.component.html','HtmlSelectOneMenu',NULL,NULL);--8
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('javax.faces.event','ValueChangeEvent',NULL,NULL);--9