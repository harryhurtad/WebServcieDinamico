--USE DBDYNAMICWS
--GO
--
--
---- Clases
---- Se usan para definir las clases cuando el cliente las requiere (Reflexion), las clases que el cliente necesita para realizar el flujo funcional
---- - se definen los objetos que representan los parámetros del webservice: 
---- - Para definir los objetos que utilizan los métodos de las operaciones del flujo funcional
---- - ManagedBean que involucran el flujo funcional

IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'com.unico.ws.mb'
			AND nombreCLase = 'SelectOneMenuVManageBean')
BEGIN 
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.unico.ws.mb','SelectOneMenuVManageBean','selectOneMenuVManageBean',NULL);
END
DECLARE @idClaseSelectOneMenuVManageBean INT
SET @idClaseSelectOneMenuVManageBean = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'SelectOneMenuVManageBean')


IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'com.unico.ws.interfaces'
			AND nombreCLase = 'DynamicExampleSessionBeanRemote')
BEGIN
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD,JNDI) VALUES('com.unico.ws.interfaces','DynamicExampleSessionBeanRemote',NULL,NULL,'ejb/DynamicExampleSessionBean');
END
DECLARE @idClaseDynamicExampleSessionBeanRemote INT
SET @idClaseDynamicExampleSessionBeanRemote = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'DynamicExampleSessionBeanRemote')


IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'com.unico.ws.webservice'
			AND nombreCLase = 'UsuarioDTO')
BEGIN 
	INSERT INTO PUBLICADOR_WS_CLASES (nombreCLase, packageClass,archivoXSD) VALUES(
			'UsuarioDTO','com.unico.ws.webservice','<?xml version="1.0" encoding="ISO-8859-1"?>

	<xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema"
				elementFormDefault="qualified"
				xmlns:jaxb="http://java.sun.com/xml/ns/jaxb"
				xmlns:xjc="http://java.sun.com/xml/ns/jaxb/xjc"
				jaxb:extensionBindingPrefixes="xjc"
				jaxb:version="1.0">
		<xsd:annotation>
			<xsd:appinfo>
				<jaxb:globalBindings generateIsSetMethod="true">
					<xjc:serializable uid="12343"/>
				</jaxb:globalBindings>
			</xsd:appinfo>
		</xsd:annotation>
		<xsd:complexType name="UsuarioDTO">
			<xsd:sequence>
				<xsd:element name="usuario" type="xsd:string"/>
				<xsd:element name="clave" type="xsd:string"/>
			</xsd:sequence>
		</xsd:complexType>
		<xsd:element name="usuarioDTO" type="UsuarioDTO"/>
	</xsd:schema>
	');
END

DECLARE @idClaseUsuarioDTO INT
SET @idClaseUsuarioDTO = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'UsuarioDTO')


IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'java.lang'
			AND nombreCLase = 'String')
BEGIN
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('java.lang','String',NULL,NULL);
END

DECLARE @idClaseString INT
SET @idClaseString = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'String')


IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'java.util'
			AND nombreCLase = 'List')
BEGIN
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('java.util','List',NULL,NULL);
END

DECLARE @idClaseList INT
SET @idClaseList = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'List')

--
----
----INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('java.lang','Integer',NULL,NULL);--6
----INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('','int',NULL,NULL);--6
----
----INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('java.util','Date',NULL,NULL);--6
----INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('javax.faces.component.html','HtmlSelectOneMenu',NULL,NULL);--8
----INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('javax.faces.event','ValueChangeEvent',NULL,NULL);--9
----
----INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','CargueOptimizadoMB','cargueOptimizadoMB',NULL);--9
----INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.vo','ArchivoSeleccionadoVO',NULL,NULL);--9
--

--
--
---- Consulta clases
----DECLARE @idClaseHelper INT
----SET @idClaseHelper = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'WSDynamicUtil')
----DECLARE @idClaseInteger INT
----SET @idClaseInteger = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'Integer')
----DECLARE @idClaseInt INT
----SET @idClaseInt = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'int')
----

----DECLARE @idClaseDate INT
----SET @idClaseDate = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'Date')
----DECLARE @idClaseHtmlSelectOneMenu INT
----SET @idClaseHtmlSelectOneMenu = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'HtmlSelectOneMenu')
----DECLARE @idClaseValueChangeEvent INT
----SET @idClaseValueChangeEvent = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'ValueChangeEvent')
----
----DECLARE @idClaseCargueOptimizadoMB INT
----SET @idClaseCargueOptimizadoMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'CargueOptimizadoMB')
----
---DECLARE @idClaseRealizarCargueDTO INT
----SET @idClaseRealizarCargueDTO = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'RealizarCargueDTO')
----DECLARE @idClaseArchivoSeleccionadoVO INT
----SET @idClaseArchivoSeleccionadoVO = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'ArchivoSeleccionadoVO')
--
---- Dependencia clases
-- -- no hay
--

---- Atributos Clases
-- -- Atributos clases especiales definidas como atributos en el managedBean
--INSERT INTO PUBLICADOR_WS_CLASES_ATRIBUTOS(idClasePrincipal,nombreAtributo,idClaseAtributo) VALUES(@idClaseArchivoSeleccionadoVO,'idTipoArchivo',@idClaseInt)
--INSERT INTO PUBLICADOR_WS_CLASES_ATRIBUTOS(idClasePrincipal,nombreAtributo,idClaseAtributo) VALUES(@idClaseArchivoSeleccionadoVO,'nombreArchivo',@idClaseString)
--
--DECLARE @idAtributoIdTipoArchivoArchivoSeleccionadoVO INT
--SET @idAtributoIdTipoArchivoArchivoSeleccionadoVO = (SELECT idAtributo FROM PUBLICADOR_WS_CLASES_ATRIBUTOS WHERE idClasePrincipal = @idClaseArchivoSeleccionadoVO AND nombreAtributo = 'idTipoArchivo')
--
--DECLARE @idAtributonombreArchivoArchivoSeleccionadoVO INT
--SET @idAtributonombreArchivoArchivoSeleccionadoVO = (SELECT idAtributo FROM PUBLICADOR_WS_CLASES_ATRIBUTOS WHERE idClasePrincipal = @idClaseArchivoSeleccionadoVO AND nombreAtributo = 'nombreArchivo')
--

-- Webservice
-- package_ws: Donde se va generar el WebService (tiempo de compilación)
-- idClassEJBRemote: EJB intermediario entre el cliente y el publicador del Web Service - comunicación   


IF NOT EXISTS(SELECT * FROM PUBLICADOR_WS_ESTRUCTURA WHERE nombreWs = 'ExampleWS')
BEGIN
	INSERT INTO PUBLICADOR_WS_ESTRUCTURA (nombreWs, portName,targetNamespace,package_ws,descripcionWs,contextoWS,contexto_app_cliente,idClassEJBRemote,habilitado) 
		VALUES ('ExampleWS','ExampleWSPort','http://com.ath.unico.exampleWS.ws/','com.ath.unico.cc.webservice','Web Service ejemplo',
			'Example' ,'UnicoTestAPPWS-web',@idClaseDynamicExampleSessionBeanRemote,1);
END

DECLARE @idWs INT
SET @idWs = (SELECT idWs FROM PUBLICADOR_WS_ESTRUCTURA WHERE nombreWs LIKE 'ExampleWS')


-- Operaciones
-- Descripción de las operaciones que va contener el web service
IF NOT EXISTS(SELECT * FROM PUBLICADOR_WS_OPERACIONES WHERE nombreOperacion = 'listarUsuarios')
BEGIN
	INSERT INTO PUBLICADOR_WS_OPERACIONES (nombreOperacion, descripcionOperacion, metodoOperacion,idClaseRetornoOperacion)VALUES ('listarUsuarios','Test para pasar un collection de obj','listarUsuarios',null);
END

DECLARE @idOplistarUsuarios INT
SET @idOplistarUsuarios = (SELECT idOperacion FROM PUBLICADOR_WS_OPERACIONES WHERE nombreOperacion LIKE 'listarUsuarios')

------ Asociar la operacion al webservice
---- Tabla relación de las operaciones que pertenecen a un ws
IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_ESTRUCTURA_OPERACIONES 
		WHERE 1=1
		AND idWs = @idWs
		AND idOperacion = @idOplistarUsuarios)
BEGIN
	INSERT INTO PUBLICADOR_WS_ESTRUCTURA_OPERACIONES  (idWs, idOperacion)VALUES(@idWs,@idOplistarUsuarios);
END

-- Parametros operacion
-- Atributos de las operaciones que dependen de la definición de la firma de la operación
IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES 
		WHERE 1=1
		AND idOperacion = @idOplistarUsuarios
		AND nombreParametro = 'listUsuarioDTO')
BEGIN
	INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClaseLlave,idClaseValor,idClaseCollection,ordenParametro)  VALUES(@idOplistarUsuarios,'listUsuarioDTO',null,@idClaseUsuarioDTO,@idClaseList,1);
END

DECLARE @idParamlistUsuarioDTO INT
SET @idParamlistUsuarioDTO = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES WHERE nombreParametro LIKE 'listUsuarioDTO' and idoperacion = @idOplistarUsuarios)


-- Metodos operacion
-- Métodos que involucran el flujo funcional a parametrizar (ej CargueOptimizado, login)
-- Permite pasar información de respuesta de un método a otro
-- MB Aplicacion
-- MB Login


INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('validarUsuarioSeleccionados',null,@idClaseSelectOneMenuVManageBean,null);
DECLARE @idMetValidarUsuarioSeleccionados INT
SET @idMetValidarUsuarioSeleccionados = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'validarUsuarioSeleccionados')

--
---- PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE
---- Permite parametrizar los objetos que se van a construir en tiempo de ejecución y pasar dichos objetos a un método de operación
---- Permite parametrizar los objetos ManagedBean que serán utilizados en el flujo
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOplistarUsuarios,NULL,NULL,@idClaseSelectOneMenuVManageBean,1)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOplistarUsuarios,@idMetValidarUsuarioSeleccionados,NULL,NULL,1)
--
--
---- Parametos Metodos
---- Parámetros de los métodos que se invocan para el flujo
--INSERT INTO PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES (idMetodoOperacion, nombreParametro, idClase,ordenParametro)VALUES (@idMetchangedTipoArchivoCargue,'event',@idClaseValueChangeEvent,1);
--DECLARE @idParamMetchangedTipoArchivoCargue INT
--SET @idParamMetchangedTipoArchivoCargue = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES WHERE nombreparametro LIKE 'event')
--
--INSERT INTO PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES (idMetodoOperacion, nombreParametro, idClase,ordenParametro)VALUES (@idMetSetArchivoSeleccionadoVO,'archivoSeleccionadoVO',@idClaseArchivoSeleccionadoVO,1);
--DECLARE @idParamMetArchivoSeleccionadoVO INT
--SET @idParamMetArchivoSeleccionadoVO = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES WHERE nombreparametro LIKE 'archivoSeleccionadoVO')
--
--
--
----METODOS_OPERACIONES_JSF
---- Configuración de eventos especiales de Faces.
--DECLARE @idParamOpRealizarCargueDTO INT
--SET @idParamOpRealizarCargueDTO = (select idParametroOperacion from PUBLICADOR_WS_PARAMETROS_OPERACIONES where nombreparametro like 'realizarCargueDTO')
--INSERT INTO METODOS_OPERACIONES_JSF (idMetodoOperacion, idClassParamEvent, idClassInternalValEvent,idClassUIComponentJSF,idParametroOperacionWS,nombreGetAtributoParamWS)VALUES (@idMetchangedTipoArchivoCargue,@idClaseValueChangeEvent,@idClaseInteger,@idClaseHtmlSelectOneMenu,@idParamOpRealizarCargueDTO,'getIdTipoArchivo');
--
--
---- PARAMETROS_TO_MAP_METODOS
---- 
--INSERT INTO PARAMETROS_TO_MAP_METODOS (idParametroOperacion ,idParametroOperacionWS,nombreParametroGetWS,idResultEjecInstClass)
--VALUES(@idParamMetArchivoSeleccionadoVO,NULL,NULL,@idEjecClaseArchivoSeleccionadoVO)
--		
---- Pasa los valores de las instancias creadas en tiempo de ejecución a una clase
--INSERT INTO PARAMETROS_TO_MAP_INST_CLASS(idEjecucionOperacion,idParametroOperacionWS,nombreParametroGetWS,idAtributoToSetValueClass) VALUES(@idEjecClaseArchivoSeleccionadoVO,@idParamRealizarCargueDTOOpRealizarCargue,'getIdTipoArchivo',@idAtributoIdTipoArchivoArchivoSeleccionadoVO)
--INSERT INTO PARAMETROS_TO_MAP_INST_CLASS(idEjecucionOperacion,idParametroOperacionWS,nombreParametroGetWS,idAtributoToSetValueClass) VALUES(@idEjecClaseArchivoSeleccionadoVO,@idParamRealizarCargueDTOOpRealizarCargue,'getNombreArchivo',@idAtributonombreArchivoArchivoSeleccionadoVO)
--
--
-- 
---- Login
---- Métodos get y set del managedbean, para pasar información 
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamlistUsuarioDTO,@idOplistarUsuarios,null,@idClaseSelectOneMenuVManageBean,@idClaseList,'setListaUsuarioSel');

---- CargueOptimizadoMB
--INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamRealizarCargueDTOOpRealizarCargue,@idOpRealizarCargue,'getIdTipoArchivo',@idClaseCargueOptimizadoMB,@idClaseInt,'setIdTipoArchivoSeleccionado');--idTipoArchivoSeleccionado
--INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamRealizarCargueDTOOpRealizarCargue,@idOpRealizarCargue,'getFechaCargar',@idClaseCargueOptimizadoMB,@idClaseDate,'setFechaCargar');--fechaCargar
-- select * from dbunico.dbo.unico_convenio 
--
