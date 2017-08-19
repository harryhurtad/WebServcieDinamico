USE DBDYNAMICWS
GO

-- Inicio Parametrizacion de elementos comunes 

IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE
			packageClass = 'com.ath.intranet.cc.web.managedbean'
			AND nombreCLase = 'AplicacionMB')
BEGIN 
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','AplicacionMB','aplicacionMB',NULL);
END
DECLARE @idClaseAplicacionMB INT
SET @idClaseAplicacionMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'AplicacionMB')

IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 
			packageClass = 'com.ath.intranet.cc.web.managedbean'
			AND nombreCLase = 'LoginMB')
BEGIN 
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','LoginMB','loginMB',NULL);
END
DECLARE @idClaseLoginMB INT
SET @idClaseLoginMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'LoginMB')

IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 
			packageClass = 'com.unico.ws.interfaces'
			AND nombreCLase = 'DynamicExcecutionSessionBeanRemote')
BEGIN
	INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD,JNDI) VALUES('com.unico.ws.interfaces','DynamicExcecutionSessionBeanRemote',NULL,NULL,'ejb/DynamicExcecutionSessionBean');
END
DECLARE @idClaseDynamicExcecutionSessionBeanRemote INT
SET @idClaseDynamicExcecutionSessionBeanRemote = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'DynamicExcecutionSessionBeanRemote')


IF NOT EXISTS(
		SELECT * FROM PUBLICADOR_WS_CLASES WHERE 
			packageClass = 'com.unico.ws.webservice'
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
		SELECT * FROM PUBLICADOR_WS_ESTRUCTURA WHERE
			nombreWs = 'CentroComputoWS')
BEGIN 
	INSERT INTO PUBLICADOR_WS_ESTRUCTURA (nombreWs, portName,targetNamespace,package_ws,descripcionWs,contextoWS,contexto_app_cliente,idClassEJBRemote,habilitado) 
		VALUES ('CentroComputoWS','CentroComputoWSPort','http://com.ath.unico.centroComputo.ws/','com.ath.unico.cc.webservice','Web Service Centro Computo Unico',
			'CentroComputo' ,'CentroComputoWeb',@idClaseDynamicExcecutionSessionBeanRemote,1);
END

-- Obtener id del servicio
DECLARE @idWs INT
SET @idWs = (SELECT idWs FROM PUBLICADOR_WS_ESTRUCTURA WHERE nombreWs LIKE 'CentroComputoWS')

--> Fin parametrizacion elementos comunes


-- Clases
-- Se usan para definir las clases cuando el cliente las requiere (Reflexion), las clases que el cliente necesita para realizar el flujo funcional
-- - se definen los objetos que representan los parámetros del webservice: 
-- - Para definir los objetos que utilizan los métodos de las operaciones del flujo funcional
-- - ManagedBean que involucran el flujo funcional

INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','MonitorLogAsincronicoUnicoMB','monitorLogAsincronicoUnicoMB',NULL);
	


INSERT INTO PUBLICADOR_WS_CLASES (nombreCLase, packageClass,archivoXSD) VALUES(
		'ConsultaMonitorLog','com.unico.ws.webservice','<?xml version="1.0" encoding="UTF-8"?>

<xsd:schema version="1.0"
			xmlns:xsd="http://www.w3.org/2001/XMLSchema"
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
    <xsd:complexType name="ConsultaMonitorLog">
        <xsd:sequence>
            <xsd:element name="fechaNegocio" type="xsd:date" nillable="true"/>
            <xsd:element name="fechaEjecucion" type="xsd:date"/>
            <xsd:element name="usuario" type="xsd:string"/>
            <xsd:element name="idProcesoSeleccionado" type="xsd:string"/>
            <xsd:element name="idModuloSeleccionado" type="xsd:string"/>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:element name="ConsultaMonitorLog" type="ConsultaMonitorLog"/>
</xsd:schema>');


-- Consulta clases
DECLARE @idClaseConsultaMonitorLog INT
SET @idClaseConsultaMonitorLog = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'ConsultaMonitorLog')

DECLARE @idClaseString INT
SET @idClaseString = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'String')
DECLARE @idClaseDate INT
SET @idClaseDate = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'Date')


DECLARE @idClaseMonitorLogAsincronicoUnicoMB INT
SET @idClaseMonitorLogAsincronicoUnicoMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'MonitorLogAsincronicoUnicoMB')




-- Dependencia clases
 -- no hay


 -- Operaciones
-- Descripción de las operaciones que va contener el web service
INSERT INTO PUBLICADOR_WS_OPERACIONES (nombreOperacion, descripcionOperacion, metodoOperacion,idClaseRetornoOperacion)VALUES ('BusquedaLogAsincronico','Realiza la busqueda de un registro','busquedaLogAsincronico',null);

DECLARE @idOpBusquedaLogAsincronico INT
SET @idOpBusquedaLogAsincronico = (SELECT idOperacion FROM PUBLICADOR_WS_OPERACIONES WHERE nombreOperacion LIKE 'BusquedaLogAsincronico')


---- Asociar la operacion al webservice
-- Tabla relación de las operaciones que pertenecen a un ws
INSERT INTO PUBLICADOR_WS_ESTRUCTURA_OPERACIONES  (idWs, idOperacion)VALUES(@idWs,@idOpBusquedaLogAsincronico);


-- Parametros operacion
-- Atributos de las operaciones que dependen de la definición de la firma de la operación
INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClaseValor,ordenParametro)  VALUES(@idOpBusquedaLogAsincronico,'usuarioDTO',@idClaseUsuarioDTO,1);
INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClaseValor,ordenParametro)  VALUES(@idOpBusquedaLogAsincronico,'claseConsultaMonitorLog',@idClaseConsultaMonitorLog,2);

-- Atributos de los MB
DECLARE @idParamUsuarioDTOTOOPerBusquedaLog INT
SET @idParamUsuarioDTOTOOPerBusquedaLog = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES WHERE nombreParametro LIKE 'usuarioDTO' AND idOperacion=@idOpBusquedaLogAsincronico)
DECLARE @idParamConsultaMonitorLog INT
SET @idParamConsultaMonitorLog = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES WHERE nombreParametro LIKE 'claseConsultaMonitorLog')

-- Metodos operacion
-- Métodos que involucran el flujo funcional a parametrizar (ej CargueOptimizado, login)
-- Permite pasar información de respuesta de un método a otro
-- MB Aplicacion
-- MB Login

DECLARE @idMetLogin INT
SET @idMetLogin = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'login')
-- MB CargueOptimizado

INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('onChangeObtenerProceso',null,@idClaseMonitorLogAsincronicoUnicoMB,null);
DECLARE @idMetonHandleChangeObtenerProceso INT
SET @idMetonHandleChangeObtenerProceso = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'onChangeObtenerProceso')

INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('cargarResumenMonitorLog',null,@idClaseMonitorLogAsincronicoUnicoMB,null);
DECLARE @idMetHandlecargarResumenMonitorLog INT
SET @idMetHandlecargarResumenMonitorLog = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'cargarResumenMonitorLog')
		

-- Login
-- Métodos get y set del managedbean, para pasar información 
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamUsuarioDTOTOOPerBusquedaLog,@idOpBusquedaLogAsincronico,'getUsuario',@idClaseLoginMB,@idClaseString,'setNombre');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamUsuarioDTOTOOPerBusquedaLog,@idOpBusquedaLogAsincronico,'getClave',@idClaseLoginMB,@idClaseString,'setClave');
-- Busqueda log asincronico
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamConsultaMonitorLog,@idOpBusquedaLogAsincronico,'getFechaNegocio',@idClaseMonitorLogAsincronicoUnicoMB,@idClaseDate,'setFechaNegocio');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamConsultaMonitorLog,@idOpBusquedaLogAsincronico,'getFechaEjecucion',@idClaseMonitorLogAsincronicoUnicoMB,@idClaseDate,'setFechaEjecucion');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamConsultaMonitorLog,@idOpBusquedaLogAsincronico,'getUsuario',@idClaseMonitorLogAsincronicoUnicoMB,@idClaseString,'setUsuario');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamConsultaMonitorLog,@idOpBusquedaLogAsincronico,'getIdProcesoSeleccionado',@idClaseMonitorLogAsincronicoUnicoMB,@idClaseString,'setIdProcesoSeleccionado');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamConsultaMonitorLog,@idOpBusquedaLogAsincronico,'getIdModuloSeleccionado',@idClaseMonitorLogAsincronicoUnicoMB,@idClaseString,'setIdModuloSeleccionado');








-- PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE
-- Permite parametrizar los objetos que se van a construir en tiempo de ejecución y pasar dichos objetos a un método de operación
-- Permite parametrizar los objetos ManagedBean que serán utilizados en el flujo
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpBusquedaLogAsincronico,NULL,NULL,@idClaseAplicacionMB,1)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpBusquedaLogAsincronico,@idMetLogin,NULL,NULL,2)

INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpBusquedaLogAsincronico,@idMetonHandleChangeObtenerProceso,NULL,NULL,3)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpBusquedaLogAsincronico,@idMetHandlecargarResumenMonitorLog,NULL,NULL,4)



--METODOS_OPERACIONES_JSF
-- Configuración de eventos especiales de Faces.
--DECLARE @idParamOpRealizarCargueDTO INT
--SET @idParamOpRealizarCargueDTO = (select idParametroOperacion from PUBLICADOR_WS_PARAMETROS_OPERACIONES where nombreparametro like 'realizarCargueDTO')
--INSERT INTO METODOS_OPERACIONES_JSF (idMetodoOperacion, idClassParamEvent, idClassInternalValEvent,idClassUIComponentJSF,idParametroOperacionWS,nombreGetAtributoParamWS)VALUES (@idMetchangedTipoArchivoCargue,@idClaseValueChangeEvent,@idClaseInteger,@idClaseHtmlSelectOneMenu,@idParamOpRealizarCargueDTO,'getIdTipoArchivo');




