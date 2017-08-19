USE DBDYNAMICWS
GO
--
--
---- Clases
---- Se usan para definir las clases cuando el cliente las requiere (Reflexion), las clases que el cliente necesita para realizar el flujo funcional
---- - se definen los objetos que representan los par�metros del webservice: 
---- - Para definir los objetos que utilizan los m�todos de las operaciones del flujo funcional
---- - ManagedBean que involucran el flujo funcional
IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'com.ath.intranet.cc.web.managedbean'
			AND nombreCLase = 'AplicacionMB')
BEGIN 
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','AplicacionMB','aplicacionMB',NULL);
END
DECLARE @idClaseAplicacionMB INT
SET @idClaseAplicacionMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'AplicacionMB')

IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'com.ath.intranet.cc.web.managedbean'
			AND nombreCLase = 'LoginMB')
BEGIN 
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','LoginMB','loginMB',NULL);
END
DECLARE @idClaseLoginMB INT
SET @idClaseLoginMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'LoginMB')

IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'com.ath.intranet.cc.web.managedbean'
			AND nombreCLase = 'InicioGeneracionRecaudosMB')
BEGIN 
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','InicioGeneracionRecaudosMB','inicioGeneracionRecaudosMB',NULL);
END
DECLARE @idClaseInicioGeneracionRecaudosMB INT
SET @idClaseInicioGeneracionRecaudosMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'InicioGeneracionRecaudosMB')


IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'com.unico.ws.interfaces'
			AND nombreCLase = 'DynamicExcecutionSessionBeanRemote')
BEGIN
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD,JNDI) VALUES('com.unico.ws.interfaces','DynamicExcecutionSessionBeanRemote',NULL,NULL,'ejb/DynamicExcecutionSessionBean');
END
DECLARE @idClaseDynamicExcecutionSessionBeanRemote INT
SET @idClaseDynamicExcecutionSessionBeanRemote = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'DynamicExcecutionSessionBeanRemote')


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
			AND packageClass = 'com.unico.ws.webservice'
			AND nombreCLase = 'InformacionRecaudosDTO')
BEGIN 
	INSERT INTO PUBLICADOR_WS_CLASES (nombreCLase, packageClass,archivoXSD) VALUES(
			'InformacionRecaudosDTO','com.unico.ws.webservice','<?xml version="1.0" encoding="ISO-8859-1"?>

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
				<xsd:complexType name="InformacionRecaudosDTO">
					<xsd:sequence>
						<xsd:element name="fecha" type="xsd:date"/>
						<xsd:element name="idGrupoConvenioSeleccionado" type="xsd:string"/>            
					</xsd:sequence>
				</xsd:complexType>
				<xsd:element name="informacionRecaudosDTO" type="InformacionRecaudosDTO"/>
			</xsd:schema>
	');
END
DECLARE @idClaseInformacionRecaudosDTO INT
SET @idClaseInformacionRecaudosDTO = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'InformacionRecaudosDTO')



IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'com.ath.intranet.cc.vo'
			AND nombreCLase = 'FiltroRecaudosVO')
BEGIN 
	INSERT INTO PUBLICADOR_WS_CLASES (nombreCLase, packageClass,archivoXSD) VALUES(
			'FiltroRecaudosVO','com.ath.intranet.cc.vo','<?xml version="1.0" encoding="ISO-8859-1"?>

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
				<xsd:complexType name="FiltroRecaudosVO">
					<xsd:sequence>
						<xsd:element name="idConvenio" type="xsd:int" nillable="true"/>
						<xsd:element name="idGrupoConvenio" type="xsd:int" nillable="true"/>
						<xsd:element name="nombreConvenio" type="xsd:string"/>
						<xsd:element name="idNombreConvenio" type="xsd:string"/>
					</xsd:sequence>
				</xsd:complexType>
				<xsd:element name="filtroRecaudosVO" type="FiltroRecaudosVO"/>
			</xsd:schema>
	');
END
DECLARE @idClaseFiltroRecaudosVO INT
SET @idClaseFiltroRecaudosVO = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'FiltroRecaudosVO')

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


IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 1=1
			AND packageClass = 'java.util'
			AND nombreCLase = 'Date')
BEGIN
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('java.util','Date',NULL,NULL);
END
DECLARE @idClaseDate INT
SET @idClaseDate = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'Date')

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
-- package_ws: Donde se va generar el WebService (tiempo de compilaci�n)
-- idClassEJBRemote: EJB intermediario entre el cliente y el publicador del Web Service - comunicaci�n   


IF NOT EXISTS(SELECT * FROM PUBLICADOR_WS_ESTRUCTURA WHERE nombreWs = 'CentroComputoWS')
BEGIN
	INSERT INTO PUBLICADOR_WS_ESTRUCTURA (nombreWs, portName,targetNamespace,package_ws,descripcionWs,contextoWS,contexto_app_cliente,idClassEJBRemote,habilitado) 
		VALUES ('CentroComputoWS','CentroComputoWSPort','http://com.ath.unico.centroComputo.ws/','com.ath.unico.cc.webservice','Web Service Centro Computo Unico',
			'CentroComputo' ,'CentroComputoWeb',@idClaseDynamicExcecutionSessionBeanRemote,1);
END

DECLARE @idWs INT
SET @idWs = (SELECT idWs FROM PUBLICADOR_WS_ESTRUCTURA WHERE nombreWs LIKE 'CentroComputoWS')


-- Operaciones
-- Descripci�n de las operaciones que va contener el web service
IF NOT EXISTS(SELECT * FROM PUBLICADOR_WS_OPERACIONES WHERE nombreOperacion = 'GenerarArchivosRecaudo')
BEGIN
	INSERT INTO PUBLICADOR_WS_OPERACIONES (nombreOperacion, descripcionOperacion, metodoOperacion,idClaseRetornoOperacion)VALUES ('GenerarArchivosRecaudo','Realiza la generacion de los archivos de recaudo en Unico','generarArchivosRecaudo',null);
END

DECLARE @idOpGenerarArchivosRecaudo INT
SET @idOpGenerarArchivosRecaudo = (SELECT idOperacion FROM PUBLICADOR_WS_OPERACIONES WHERE nombreOperacion LIKE 'GenerarArchivosRecaudo')

------ Asociar la operacion al webservice
---- Tabla relaci�n de las operaciones que pertenecen a un ws
IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_ESTRUCTURA_OPERACIONES 
		WHERE 1=1
		AND idWs = @idWs
		AND idOperacion = @idOpGenerarArchivosRecaudo)
BEGIN
	INSERT INTO PUBLICADOR_WS_ESTRUCTURA_OPERACIONES  (idWs, idOperacion)VALUES(@idWs,@idOpGenerarArchivosRecaudo);
END

-- Parametros operacion
-- Atributos de las operaciones que dependen de la definici�n de la firma de la operaci�n
IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES 
		WHERE 1=1
		AND idOperacion = @idOpGenerarArchivosRecaudo
		AND nombreParametro = 'usuarioDTO')
BEGIN	
	INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClaseLlave,idClaseValor,idClaseCollection,ordenParametro)  VALUES(@idOpGenerarArchivosRecaudo,'usuarioDTO',null,@idClaseUsuarioDTO,null,1);
END
DECLARE @idParamUsuarioDTOOpRealizarCruce INT
SET @idParamUsuarioDTOOpRealizarCruce = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES WHERE nombreParametro LIKE 'usuarioDTO' and idoperacion = @idOpGenerarArchivosRecaudo)


IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES 
		WHERE 1=1
		AND idOperacion = @idOpGenerarArchivosRecaudo
		AND nombreParametro = 'informacionRecaudosDTO')
BEGIN	
	INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClaseLlave,idClaseValor,idClaseCollection,ordenParametro)  VALUES(@idOpGenerarArchivosRecaudo,'informacionRecaudosDTO',null,@idClaseInformacionRecaudosDTO,null,2);
END
DECLARE @idParamInformacionRecaudosDTO INT
SET @idParamInformacionRecaudosDTO = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES WHERE nombreParametro LIKE 'informacionRecaudosDTO' and idoperacion = @idOpGenerarArchivosRecaudo)


IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES 
		WHERE 1=1
		AND idOperacion = @idOpGenerarArchivosRecaudo
		AND nombreParametro = 'filtroRecaudosVO')
BEGIN	
	INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClaseLlave,idClaseValor,idClaseCollection,ordenParametro)  VALUES(@idOpGenerarArchivosRecaudo,'filtroRecaudosVO',null,@idClaseFiltroRecaudosVO,@idClaseList,3);
END
DECLARE @idParamFiltroRecaudosVO INT
SET @idParamFiltroRecaudosVO = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES WHERE nombreParametro LIKE 'filtroRecaudosVO' and idoperacion = @idOpGenerarArchivosRecaudo)


-- Metodos operacion
-- M�todos que involucran el flujo funcional a parametrizar (ej CargueOptimizado, login)
-- Permite pasar informaci�n de respuesta de un m�todo a otro
-- MB Aplicacion
-- MB Login
IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_METODOS_OPERACIONES 
		WHERE 1=1
		AND metodoOperacion = 'login')
BEGIN
	INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('login',null,@idClaseLoginMB,@idClaseString);
END
DECLARE @idMetLogin INT
SET @idMetLogin = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'login')


INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('generarRecaudo',null,@idClaseInicioGeneracionRecaudosMB,null);
DECLARE @idMetGenerarRecaudo INT
SET @idMetGenerarRecaudo = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'generarRecaudo')


--
---- PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE
---- Permite parametrizar los objetos que se van a construir en tiempo de ejecuci�n y pasar dichos objetos a un m�todo de operaci�n
---- Permite parametrizar los objetos ManagedBean que ser�n utilizados en el flujo
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpGenerarArchivosRecaudo,NULL,NULL,@idClaseAplicacionMB,1)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpGenerarArchivosRecaudo,@idMetLogin,NULL,NULL,2)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpGenerarArchivosRecaudo,@idMetGenerarRecaudo,NULL,NULL,3)


--
--
---- Parametos Metodos
---- Par�metros de los m�todos que se invocan para el flujo
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
---- Configuraci�n de eventos especiales de Faces.
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
---- Pasa los valores de las instancias creadas en tiempo de ejecuci�n a una clase
--INSERT INTO PARAMETROS_TO_MAP_INST_CLASS(idEjecucionOperacion,idParametroOperacionWS,nombreParametroGetWS,idAtributoToSetValueClass) VALUES(@idEjecClaseArchivoSeleccionadoVO,@idParamRealizarCargueDTOOpRealizarCargue,'getIdTipoArchivo',@idAtributoIdTipoArchivoArchivoSeleccionadoVO)
--INSERT INTO PARAMETROS_TO_MAP_INST_CLASS(idEjecucionOperacion,idParametroOperacionWS,nombreParametroGetWS,idAtributoToSetValueClass) VALUES(@idEjecClaseArchivoSeleccionadoVO,@idParamRealizarCargueDTOOpRealizarCargue,'getNombreArchivo',@idAtributonombreArchivoArchivoSeleccionadoVO)
--
--
-- 
---- Login
---- M�todos get y set del managedbean, para pasar informaci�n 
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamUsuarioDTOOpRealizarCruce,@idOpGenerarArchivosRecaudo,'getUsuario',@idClaseLoginMB,@idClaseString,'setNombre');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamUsuarioDTOOpRealizarCruce,@idOpGenerarArchivosRecaudo,'getClave',@idClaseLoginMB,@idClaseString,'setClave');
---- InicioGeneracionRecaudosMB
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamInformacionRecaudosDTO,@idOpGenerarArchivosRecaudo,'getFecha',@idClaseInicioGeneracionRecaudosMB,@idClaseDate,'setFecha');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamInformacionRecaudosDTO,@idOpGenerarArchivosRecaudo,'getIdGrupoConvenioSeleccionado',@idClaseInicioGeneracionRecaudosMB,@idClaseString,'setIdGrupoConvenioSeleccionado');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamFiltroRecaudosVO,@idOpGenerarArchivosRecaudo,null,@idClaseInicioGeneracionRecaudosMB,@idClaseList,'setListaConveniosSeleccionado');



--INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamRealizarCargueDTOOpRealizarCargue,@idOpRealizarCargue,'getIdTipoArchivo',@idClaseCargueOptimizadoMB,@idClaseInt,'setIdTipoArchivoSeleccionado');--idTipoArchivoSeleccionado
--INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamRealizarCargueDTOOpRealizarCargue,@idOpRealizarCargue,'getFechaCargar',@idClaseCargueOptimizadoMB,@idClaseDate,'setFechaCargar');--fechaCargar
-- select * from dbunico.dbo.unico_convenio 
--
