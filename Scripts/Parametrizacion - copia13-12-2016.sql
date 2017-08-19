USE DBDynamicWS
GO

IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_ESTRUCTURA_OPERACIONES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_ESTRUCTURA_OPERACIONES
END


IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN
END


IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES
            WHERE TABLE_NAME = 'PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES')
BEGIN
	DROP TABLE PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES
END

IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_PARAMETROS_OPERACIONES')
BEGIN 
	DROP TABLE PUBLICADOR_WS_PARAMETROS_OPERACIONES
END
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_METODOS_OPERACIONES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_METODOS_OPERACIONES
END
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_OPERACIONES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_OPERACIONES
END
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_DEPENDENCIA_CLASES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_DEPENDENCIA_CLASES
END

IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_ESTRUCTURA')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_ESTRUCTURA
END
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_ESTRUCTURA_OPERACIONES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_ESTRUCTURA_OPERACIONES
END
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_ESTADO_INVOC_OPER')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_ESTADO_INVOC_OPER
END
IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_CLASES')
	BEGIN 
	DROP TABLE PUBLICADOR_WS_CLASES
END

IF EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'CONSTANTES_PARAMETRIZACION_WS')
	BEGIN 
	DROP TABLE CONSTANTES_PARAMETRIZACION_WS
END

GO


-- Se crea Tablas de definicion de Ws
--PUBLICADOR_WS_ESTRUCTURA
--PUBLICADOR_WS_CLASES
--PUBLICADOR_WS_DEPENDENCIA_CLASES
--PUBLICADOR_WS_OPERACIONES
--PUBLICADOR_WS_METODOS_OPERACIONES
--PUBLICADOR_WS_PARAMETROS_OPERACIONES
--PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES
--PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN
--PUBLICADOR_WS_ESTRUCTURA_OPERACIONES
--PUBLICADOR_WS_ESTADO_INVOC_OPER
--CONSTANTES_PARAMETRIZACION_WS
--PUBLICADOR_WS_CLASES_ATRIBUTOS
--PUBLICADOR_WS_CLASES_PARAM_CONSTRUCTOR
--PUBLICADOR_WS_CLASES_CONSTRUCTOR_INST
--PUBLICADOR_WS_CLASES_CONSTRUCTOR_INST
--PLAN_EJEC_CONSTRUCTOR_PARAMS_TO_MAP
--PLAN_EJECUCION_CONSTRUCTOR_PARAM_TO_MAP


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
		URLPublicacionWs VARCHAR(max)  NOT NULL,
		idClassEJBRemote  INT NOT NULL,		
		habilitado      BIT NOT NULL,
		
		
		PRIMARY KEY (idWs),
		
		CONSTRAINT FK_PUBLICADOR_WS_ESTRUCTURA_idClassEJBRemote
		FOREIGN KEY (idClassEJBRemote) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
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

		CONSTRAINT AK_PUBLICADOR_WS_OPERACIONES UNIQUE(idOperacion,nombreOperacion),
		
	);

END

-- PUBLICADOR_WS_METODOS_OPERACIONES
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_METODOS_OPERACIONES')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_METODOS_OPERACIONES
	(
		idMetodoOperacion INT IDENTITY(1,1),
		idOperacion INT NOT NULL,
		metodoOperacion VARCHAR(100) NOT NULL,
		descripcionMetdOperacion VARCHAR(100) null,
		idClasePrincipalMetdOperacion int NOT NULL,	
		idClaseMetdRetornoOperacion int NULL,
		idClassManageBeanRetorno INT NULL,
		PRIMARY KEY (idMetodoOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_METODOS_OPERACIONES_idOperacion
		FOREIGN KEY (idOperacion) 
		REFERENCES PUBLICADOR_WS_OPERACIONES(idOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_METODOS_OPERACIONES_idClasePrincipalMetdOperacion
		FOREIGN KEY (idClasePrincipalMetdOperacion) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
		CONSTRAINT FK_PUBLICADOR_WS_METODOS_OPERACIONES_idClaseMetdRetornoOperacion
		FOREIGN KEY (idClaseMetdRetornoOperacion) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),	
	
		CONSTRAINT FK_PUBLICADOR_WS_METODOS_OPERACIONES_idClassManageBeanRetorno
		FOREIGN KEY (idClassmanageBeanRetorno) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),

		
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
		idClase INT,
		ordenParametro INT,

		PRIMARY KEY (idParametroOperacion),

		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_OPERACIONES_idOperacion
		FOREIGN KEY (idOperacion) 
		REFERENCES PUBLICADOR_WS_OPERACIONES(idOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_OPERACIONES_idClase
		FOREIGN KEY (idClase) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),

		
	);

END


-- PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES')
	BEGIN
	CREATE TABLE PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES
	(
		idParametroOperacion INT IDENTITY(1,1),
		idMetodoOperacion INT NOT NULL,
		nombreParametro VARCHAR(100) NOT NULL,	
		idClase INT NOT NULL,
		ordenParametro INT NOT NULL,
		idMetOperacionDependencia INT,
	

		PRIMARY KEY (idParametroOperacion),

		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES_idMetodoOperacion
		FOREIGN KEY (idMetodoOperacion) 
		REFERENCES PUBLICADOR_WS_METODOS_OPERACIONES(idMetodoOperacion),

		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES_idOperacionDependencia
		FOREIGN KEY (idMetOperacionDependencia) 
		REFERENCES PUBLICADOR_WS_METODOS_OPERACIONES(idMetodoOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES_idClase
		FOREIGN KEY (idClase) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),
		
	
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
		nombreGetFieldWS VARCHAR(100) NOT NULL,
		idClaseFieldWS INT NOT NULL,
		idClaseParamManageBean INT NOT NULL,
		nombreAtributoManageBean VARCHAR(100) NOT NULL,
		idClaseAtributoManageBean INT NOT NULL,
		
		
		PRIMARY KEY (idMetodoToMap),
		
		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN_idParametroOperacionWS
		FOREIGN KEY (idParametroOperacionWS) 
		REFERENCES PUBLICADOR_WS_PARAMETROS_OPERACIONES(idParametroOperacion),
		
		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN_idClaseParamManageBean
		FOREIGN KEY (idClaseParamManageBean) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),

		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN_idClaseFieldWS
		FOREIGN KEY (idClaseFieldWS) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),

		CONSTRAINT FK_PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN_idClaseAtributoManageBean
		FOREIGN KEY (idClaseAtributoManageBean) 
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


--
--PUBLICADOR_WS_CLASES_ATRIBUTOS

IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_CLASES_ATRIBUTOS')	
	BEGIN
	CREATE TABLE PUBLICADOR_WS_CLASES_ATRIBUTOS
	(
		idAtributoClase  INT IDENTITY(1,1),
		idClase INT NOT NULL,
		nombre VARCHAR(100)  NOT NULL,	
		tipoClase  INT NOT NULL
		
		
		PRIMARY KEY (idAtributoClase),
		
		
		CONSTRAINT FK_PUBLICADOR_WS_CLASES_ATRIBUTOS_idClase
		FOREIGN KEY (idClase) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),

		CONSTRAINT FK_PUBLICADOR_WS_CLASES_ATRIBUTOS_tipoClase
		FOREIGN KEY (tipoClase) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),	
		
	);

END

--PUBLICADOR_WS_CLASES_PARAM_CONSTRUCTOR
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PUBLICADOR_WS_CLASES_PARAM_CONSTRUCTOR')	
	BEGIN
	CREATE TABLE PUBLICADOR_WS_CLASES_PARAM_CONSTRUCTOR
	(
		idParamConstClase  INT IDENTITY(1,1),
		idClasePrincipal INT NOT NULL
		idClassParam INT NOT NULL
		orden INT NOT NULL,
		
		
		PRIMARY KEY (idParamConstClase),
		
		
		CONSTRAINT FK_PUBLICADOR_WS_CLASES_PARAM_CONSTRUCTOR_idClasePrincipal
		FOREIGN KEY (idClasePrincipal) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),	

		CONSTRAINT FK_PUBLICADOR_WS_CLASES_PARAM_CONSTRUCTOR_idClassParam
		FOREIGN KEY (idClassParam) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),		
		
	);

END


--PUBLICADOR_WS_CLASES_CONSTRUCTOR_INST

IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PLAN_EJECUCION_CONSTRUCTOR_INSTANACIA')	
	BEGIN
	CREATE TABLE PLAN_EJECUCION_CONSTRUCTOR_INSTANACIA
	(
		idConstructorInst  INT IDENTITY(1,1),
		idClassPrincipal INT NOT NULL,
		idClassToBuild INT NOT NULL,
		ordenEjec INT NOT NULL,
		
		
		PRIMARY KEY (idConstructorInst),
		
		
		CONSTRAINT FK_PLAN_EJECUCION_CONSTRUCTOR_INSTANACIA_idClassToBuild
		FOREIGN KEY (idClassToBuild) 
		REFERENCES PUBLICADOR_WS_CLASES(idClase),	

		CONSTRAINT FK_PLAN_EJECUCION_CONSTRUCTOR_INSTANACIA_idClassPrincipal
		FOREIGN KEY (idClassPrincipal) 
		REFERENCES PUBLICADOR_WS_CLASES(idClassPrincipal),		
		
	);

END

--PLAN_EJEC_CONSTRUCTOR_PARAMS_TO_MAP
IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PLAN_EJEC_CONSTRUCTOR_PARAMS_TO_MAP')	
	BEGIN
	CREATE TABLE PLAN_EJEC_CONSTRUCTOR_PARAMS_TO_MAP
	(
		idPlanConstInstConst  INT IDENTITY(1,1),
		idConstructorInst INT NOT NULL
		idOperacion INT NOT NULL
		idParamConstClase INT NOT NULL,
		idAtributoWS INT NOT NULL,
	
		
		PRIMARY KEY (idPlanConstInstConst),
		
		
		CONSTRAINT FK_PLAN_CONSTRUCTOR_INSTANACIA_POR_CONSTRUCTOR_idConstructorInst
		FOREIGN KEY (idConstructorInst) 
		REFERENCES PUBLICADOR_WS_CLASES_CONSTRUCTOR_INST(idConstructorInst),	

		CONSTRAINT FK_PLAN_CONSTRUCTOR_INSTANACIA_POR_CONSTRUCTOR_idOperacion
		FOREIGN KEY (idOperacion) 
		REFERENCES PUBLICADOR_WS_OPERACIONES(idOperacion),		
		
		CONSTRAINT FK_PLAN_CONSTRUCTOR_INSTANACIA_POR_CONSTRUCTOR_idParamConstClase
		FOREIGN KEY (idParamConstClase) 
		REFERENCES PUBLICADOR_WS_CLASES_PARAM_CONSTRUCTOR(idParamConstClase),	

		CONSTRAINT FK_PLAN_CONSTRUCTOR_INSTANACIA_POR_CONSTRUCTOR_idAtributoWS
		FOREIGN KEY (idAtributoWS) 
		REFERENCES PUBLICADOR_WS_CLASES_ATRIBUTOS(idAtributoClase),		
	);

END


--PLAN_EJEC_CONSTRUCTOR_PARAMS_TO_MAP

IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PLAN_EJEC_CONSTRUCTOR_PARAMS_TO_MAP')	
	BEGIN
	CREATE TABLE PLAN_EJEC_CLASE_PARAMS_TO_MAP
	(
		idPlanConstInstConst  INT IDENTITY(1,1),
		idConstructorInst INT NOT NULL,
		idOperacion INT NOT NULL,	
		idAtributoClassWS INT NOT NULL
		idAtributoInstClassEjec INT NOT NULL,
	
		
		PRIMARY KEY (idPlanConstInstConst),
		
		
		CONSTRAINT FK_PLAN_EJEC_CONSTRUCTOR_PARAMS_TO_MAP_idConstructorInst
		FOREIGN KEY (idConstructorInst) 
		REFERENCES PUBLICADOR_WS_CLASES_CONSTRUCTOR_INST(idConstructorInst),	

		CONSTRAINT FK_PLAN_EJEC_CONSTRUCTOR_PARAMS_TO_MAP_idOperacion
		FOREIGN KEY (idOperacion) 
		REFERENCES PUBLICADOR_WS_OPERACIONES(idOperacion),		
		
		CONSTRAINT FK_PLAN_EJEC_CONSTRUCTOR_PARAMS_TO_MAP_idParamConstClase
		FOREIGN KEY (idParamConstClase) 
		REFERENCES PUBLICADOR_WS_CLASES_PARAM_CONSTRUCTOR(idParamConstClase),	

		CONSTRAINT FK_PLAN_EJEC_CONSTRUCTOR_PARAMS_TO_MAP_idAtributoClassWS
		FOREIGN KEY (idAtributoClassWS) 
		REFERENCES PUBLICADOR_WS_CLASES_ATRIBUTOS(idAtributoClase),		
	);

END

--PLAN_EJECUCION_CONSTRUCTOR_PARAM_TO_MAP

IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_NAME = 'PLAN_EJECUCION_CONSTRUCTOR_PARAM_TO_MAP')	
	BEGIN
	CREATE TABLE PLAN_EJECUCION_CONSTRUCTOR_PARAM_TO_MAP
	(
		idConstructorInst  INT NOT NULL,
		idParamConstClase INT NOT NULL,
	
		
		
		CONSTRAINT FK_PLAN_EJECUCION_CONSTRUCTOR_PARAM_TO_MAP_idConstructorInst
		FOREIGN KEY (idConstructorInst) 
		REFERENCES PUBLICADOR_WS_CLASES(idConstructorInst),	

		CONSTRAINT FK_PLAN_EJECUCION_CONSTRUCTOR_PARAM_TO_MAP_idParamConstClase
		FOREIGN KEY (idParamConstClase) 
		REFERENCES PUBLICADOR_WS_CLASES_PARAM_CONSTRUCTOR(idParamConstClase),		
		
	);

END


INSERT INTO PUBLICADOR_WS_CLASES (nombreCLase, packageClass,archivoXSD) VALUES(
		'CalculadoraDTO','com.unico.ws.webservice','<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xs:schema version="1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	 xmlns:jaxb="http://java.sun.com/xml/ns/jaxb"
           xmlns:xjc="http://java.sun.com/xml/ns/jaxb/xjc"
           jaxb:extensionBindingPrefixes="xjc"
           jaxb:version="1.0"
>
	 <xs:annotation>
       <xs:appinfo>
          <jaxb:globalBindings generateIsSetMethod="true">
              <xjc:serializable uid="12343"/>
          </jaxb:globalBindings>
       </xs:appinfo>
    </xs:annotation>

	<xs:element name="CalculadoraDTO" type="CalculadoraDTO" />
	<xs:complexType name="CalculadoraDTO">
		<xs:sequence>
			 <xs:element name="dato1" type="xs:int" minOccurs="0" />
			<xs:element name="dato2" type="xs:int" minOccurs="0" />
		</xs:sequence>
	</xs:complexType>
</xs:schema>');
INSERT INTO PUBLICADOR_WS_CLASES (nombreCLase, aliasClase,packageClass,archivoXSD) VALUES(
		'EmployeeDTO',null,'com.unico.ws.webservice','<?xml version="1.0"?>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<xs:schema version="1.0"
           xmlns:xs="http://www.w3.org/2001/XMLSchema"
           elementFormDefault="qualified"
		   xmlns:jaxb="http://java.sun.com/xml/ns/jaxb"
           xmlns:xjc="http://java.sun.com/xml/ns/jaxb/xjc"
           jaxb:extensionBindingPrefixes="xjc"
           jaxb:version="1.0"
		   >
 <xs:annotation>
       <xs:appinfo>
          <jaxb:globalBindings generateIsSetMethod="true">
              <xjc:serializable uid="12343"/>
          </jaxb:globalBindings>
       </xs:appinfo>
    </xs:annotation>		   
    <xs:complexType name="EmployeeDTO">
        <xs:sequence>
            <xs:element name="nombre" type="xs:string"></xs:element>
            <xs:element name="apellido" type="xs:string"></xs:element>
            <xs:element name="id" type="xs:int"></xs:element>
            <xs:element name="ciudadNac" type="CiudadDTO"></xs:element>
        </xs:sequence>
    </xs:complexType>
    <xs:element name="EmployeeDTO" type="EmployeeDTO"></xs:element>
    <xs:complexType name="CiudadDTO">
        <xs:sequence>
            <xs:element name="Id" type="xs:integer"></xs:element>
            <xs:element name="nombre" type="xs:string"></xs:element>
        </xs:sequence>
    </xs:complexType>
</xs:schema>
');
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.unico.ws.webservice','CiudadDTO',NULL,NULL);
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.unico.ws.helper','WSDynamicUtil',NULL,NULL);
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD,JNDI) VALUES('com.unico.ws.interfaces','DynamicExcecutionSessionBeanRemote',NULL,NULL,'ejb/DynamicExcecutionSessionBean');
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('java.lang','Integer',NULL,NULL);
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.unico.ws.mb','CalculadoraJSFManagedBean','calculadoraJSFManagedBean',NULL);
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('javax.faces.component.html','HtmlSelectOneMenu',NULL,NULL);
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('java.lang','String',NULL,NULL);
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('javax.faces.event','ValueChangeEvent',NULL,NULL);
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.unico.ws.webservice','RealizarCargueDTO',NULL,'<?xml version="1.0" encoding="UTF-8"?>

<xsd:schema version="1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
 xmlns:jaxb="http://java.sun.com/xml/ns/jaxb"
           xmlns:xjc="http://java.sun.com/xml/ns/jaxb/xjc"
           jaxb:extensionBindingPrefixes="xjc"
           jaxb:version="1.0">
	 <xs:annotation>
       <xs:appinfo>
          <jaxb:globalBindings generateIsSetMethod="true">
              <xjc:serializable uid="12343"/>
          </jaxb:globalBindings>
       </xs:appinfo>
    </xs:annotation>	   
		   
    <xsd:complexType name="UsuarioDTOComplexType">
        <xsd:sequence>
            <xsd:element name="usuario" type="xsd:string"></xsd:element>
            <xsd:element name="password" type="xsd:string"></xsd:element>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:complexType name="RealizarCargueComplexType">
        <xsd:sequence>
            <xsd:element name="usuarioDTO" type="tns:UsuarioDTOComplexType"></xsd:element>
            <xsd:element name="dato1" type="xsd:integer"></xsd:element>
            <xsd:element name="dato2" type="xsd:integer"></xsd:element>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:element name="RealizarCargueDTO" type="tns:UsuarioDTOComplexType"></xsd:element>
</xsd:schema>
');
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.unico.ws.webservice','UsuarioDTO',NULL,NULL);


---INSERTAr WS
INSERT INTO PUBLICADOR_WS_ESTRUCTURA (nombreWs, portName,targetNamespace,package_ws,descripcionWs,URLPublicacionWs,idClassEJBRemote,habilitado) 
	VALUES ('UnicoDynamicExampleWS','UnicoDynamicExampleWSPort','http://com.prueba.unico.ws/','com.unico.ws.webservice','Web Services Ejemplo Unico',
		'DynamicWSEA-web/UnicoDynamicExampleWS' ,5,1);
INSERT INTO PUBLICADOR_WS_ESTRUCTURA (nombreWs, portName,targetNamespace,package_ws,descripcionWs,URLPublicacionWs,idClassEJBRemote,habilitado) 
	VALUES ('UnicoDynamicUnico','UnicoDynamicUnicoWSPort','http://com.prueba.unico.ws/','com.unico.ws.webserviceUnico','Web Services Ejemplo Unico',
		'DynamicWSEA-web/UnicoDynamicUnicoWS' ,5,1);


--Dependencia de metodos
INSERT INTO PUBLICADOR_WS_DEPENDENCIA_CLASES (idClassDependencia, idClase, ordenCompilacion)VALUES (3,2,1);
INSERT INTO PUBLICADOR_WS_DEPENDENCIA_CLASES (idClassDependencia, idClase, ordenCompilacion)VALUES (11,12,1);

--Operaciones
INSERT INTO PUBLICADOR_WS_OPERACIONES (nombreOperacion, descripcionOperacion, metodoOperacion,idClaseRetornoOperacion)VALUES ('Suma','Realiza la suma entre dos numeros','realizarSuma',6);
INSERT INTO PUBLICADOR_WS_OPERACIONES (nombreOperacion, descripcionOperacion, metodoOperacion,idClaseRetornoOperacion)VALUES ('Resta','Realiza la resta entre dos numeros','realizarResta',null);
INSERT INTO PUBLICADOR_WS_OPERACIONES (nombreOperacion, descripcionOperacion, metodoOperacion,idClaseRetornoOperacion)VALUES ('Buscar Empleados','Realiza la busqueda de un empleado x','buscarEmpleado',null);
INSERT INTO PUBLICADOR_WS_OPERACIONES (nombreOperacion, descripcionOperacion, metodoOperacion,idClaseRetornoOperacion)VALUES ('Realizar Cargue Sim','Realiza una prueba del cargue','realizarCargue',null);

--Metodo operaciones
INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (idOperacion, metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES (1,'realizarSuma',null,7,null);
INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (idOperacion, metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES (1,'realizarResta',null,7,null);
INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (idOperacion, metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES (2,'realizarResta',null,7,null);
INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (idOperacion, metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES (4,'selectionOption',null,,null);


--PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES
INSERT INTO PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES (idParametroOperacion,idMetodoOperacion,nombreParametro,idClase,ordenParametro)
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
		ordenParametro INT,
		idMetOperacionDependencia INT,
	

--PARAMETROS OPERACIONES
INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClase,ordenParametro)  VALUES(1,'entrada1',1,1);
--INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClase,ordenParametro)  VALUES(1,'entrada2',1,2);
INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClase,ordenParametro)  VALUES(2,'dato1',6,1);
INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClase,ordenParametro)  VALUES(2,'dato2',6,2);
INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClase,ordenParametro)  VALUES(3,'empleado',2,1);
--PARAMETROS METODO

--PARAMETROS TO MAP MANAGE GENA
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseFieldWS,idClaseParamManageBean,nombreAtributoManageBean,idClaseAtributoManageBean)VALUES(1,1,'getDato1',6,7,'setDato1',6);
--INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseFieldWS,idClaseParamManageBean,nombreAtributoManageBean,idClaseAtributoManageBean)VALUES(1,1,'getDato2',6,7,'setDato2',6);
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseFieldWS,idClaseParamManageBean,nombreAtributoManageBean,idClaseAtributoManageBean)VALUES(2,1,'getDato1',6,7,'setDato1',6);
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseFieldWS,idClaseParamManageBean,nombreAtributoManageBean,idClaseAtributoManageBean)VALUES(2,1,'getDato2',6,7,'setDato2',6);

---- PUBLICADOR_WS_ESTRUCTURA_OPERACIONES
INSERT INTO PUBLICADOR_WS_ESTRUCTURA_OPERACIONES  (idWs, idOperacion)VALUES(1,1);
INSERT INTO PUBLICADOR_WS_ESTRUCTURA_OPERACIONES  (idWs, idOperacion)VALUES(1,2);
INSERT INTO PUBLICADOR_WS_ESTRUCTURA_OPERACIONES  (idWs, idOperacion)VALUES(1,3);
INSERT INTO PUBLICADOR_WS_ESTRUCTURA_OPERACIONES  (idWs, idOperacion)VALUES(2,1);
INSERT INTO PUBLICADOR_WS_ESTRUCTURA_OPERACIONES  (idWs, idOperacion)VALUES(2,2);

--CONSTANTES_PARAMETRIZACION_WS
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('DIRECTORIO_ENTRADA','E:\\temp\\DynamicClassCompile\\scr',1);
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('DIRECTORIO_SALIDA','E:\\temp\\DynamicClassCompile\\output',1);
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('HOST_WS','localhost',1);
INSERT INTO  CONSTANTES_PARAMETRIZACION_WS (nombre_parametro,valor_parametro,habilitado) VALUES('PUERTO_WS','27001',1);		

--PUBLICADOR_WS_CLASES_ATRIBUTOS	
INSERT INTO  PUBLICADOR_WS_CLASES_ATRIBUTOS (idClase,nombre,tipoClase) VALUES(1,"dato1",6);
INSERT INTO  PUBLICADOR_WS_CLASES_ATRIBUTOS (idClase,nombre,tipoClase) VALUES(1,"dato2",6);