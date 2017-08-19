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

INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','CargueOptimizadoMB','cargueOptimizadoMB',NULL);
--- Import para el objeto complejo: ArchivoSeleccionadoVO 
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.vo','ArchivoSeleccionadoVO', NULL,NULL);

-- Creacion de entradas al servicio
INSERT INTO PUBLICADOR_WS_CLASES (nombreCLase, packageClass,archivoXSD) VALUES(
		'CargueArchivos','com.unico.ws.webservice','<?xml version="1.0" encoding="UTF-8"?>

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
    <xsd:complexType name="CargueArchivos">
        <xsd:sequence>
            <xsd:element name="fechaCargar" type="xsd:date"/>
            <xsd:element name="idTipoArchivoSeleccionado" type="xsd:int"/>
            <xsd:element name="nombreArchivo" type="xsd:string"/> 
        </xsd:sequence>
    </xsd:complexType>
    <xsd:element name="CargueArchivos" type="CargueArchivos"/>
</xsd:schema>');

-- Consulta clases
DECLARE @idClaseCargueArchivos INT
SET @idClaseCargueArchivos = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'CargueArchivos')

-- Id clase  ArchivoSeleccionadoVO
DECLARE @idClaseArchivoSeleccionadoVO INT
SET @idClaseArchivoSeleccionadoVO = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'ArchivoSeleccionadoVO')

-- Definicion datos a usar, consultar si existen o si no se deben crear
---- INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('','int',NULL,NULL);--6 En caso de que se deba crear
DECLARE @idClaseDate INT
SET @idClaseDate = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'Date')

DECLARE @idClaseInt INT
SET @idClaseInt = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'int')

DECLARE @idClaseString INT
SET @idClaseString = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'String')

-- Obtener el id del registro. 
DECLARE @idClaseCargueOptimizadoMB INT
SET @idClaseCargueOptimizadoMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'CargueOptimizadoMB')

 -- Operaciones (definir firma de web services)
-- Descripción de las operaciones que va contener el web service
INSERT INTO PUBLICADOR_WS_OPERACIONES (nombreOperacion, descripcionOperacion, metodoOperacion,idClaseRetornoOperacion)VALUES ('CargueArchivoOptimizado','Realiza el cargue de un archivo','cargueArchivoOptimizado',null);

DECLARE @idOpCargueArchivoOptimizado INT
SET @idOpCargueArchivoOptimizado = (SELECT idOperacion FROM PUBLICADOR_WS_OPERACIONES WHERE nombreOperacion LIKE 'CargueArchivoOptimizado')

---- Asociar la operacion al webservice
-- Tabla relación de las operaciones que pertenecen a un ws
INSERT INTO PUBLICADOR_WS_ESTRUCTURA_OPERACIONES  (idWs, idOperacion)VALUES(@idWs,@idOpCargueArchivoOptimizado);

-- Parametros que se le van a enviar a la operacion del servicio
-- Parametros operacion
-- Atributos de las operaciones que dependen de la definición de la firma de la operación
INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClaseValor,ordenParametro)  VALUES(@idOpCargueArchivoOptimizado,'usuarioDTO',@idClaseUsuarioDTO,1);

INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClaseValor,ordenParametro)  VALUES(@idOpCargueArchivoOptimizado,'CargueArchivos',@idClaseCargueArchivos,2);

-- Firma completa

-- Atributos de los MB
DECLARE @idParamUsuarioDTOCargueArchivos INT
SET @idParamUsuarioDTOCargueArchivos = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES WHERE nombreParametro LIKE 'usuarioDTO' AND idOperacion=@idOpCargueArchivoOptimizado)
DECLARE @idParamCargueArchivos INT
SET @idParamCargueArchivos = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES WHERE nombreParametro LIKE 'CargueArchivos')

-- Metodos operacion
-- Métodos que involucran el flujo funcional a parametrizar (ej CargueOptimizado, login)
-- Permite pasar información de respuesta de un método a otro
-- MB Aplicacion
-- MB Login

DECLARE @idMetLogin INT
SET @idMetLogin = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'login')

-- MB CargueOptimizado

INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('handleChangedTipoArchivoCargue',null,@idClaseCargueOptimizadoMB,null);
INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('cargarArchivoOptimizado',null,@idClaseCargueOptimizadoMB,null);
-- setArchivoSeleccionadoVO
INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('setArchivoSeleccionadoVO',null,@idClaseCargueOptimizadoMB,null);

DECLARE @idMetoHandleChangedTipoArchivoCargue INT
SET @idMetoHandleChangedTipoArchivoCargue = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'handleChangedTipoArchivoCargue')

DECLARE @idMetCargarArchivoOptimizado INT
SET @idMetCargarArchivoOptimizado = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'cargarArchivoOptimizado')

DECLARE @idMetSetCargarArchivoVO INT
SET @idMetSetCargarArchivoVO = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'setArchivoSeleccionadoVO')

--- aqui se insertan los parametros que tiene definido el metodo 
INSERT INTO PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES (idMetodoOperacion, nombreParametro,idClase,ordenParametro)VALUES (@idMetSetCargarArchivoVO,'archivoSeleccionadoVO',@idClaseArchivoSeleccionadoVO,1);
DECLARE @idParamMetArchivoSeleccionadoVO INT
SET @idParamMetArchivoSeleccionadoVO = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES WHERE nombreparametro LIKE 'archivoSeleccionadoVO')



-- Login
-- Métodos get y set del managedbean, para pasar información 
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamUsuarioDTOCargueArchivos,@idOpCargueArchivoOptimizado,'getUsuario',@idClaseLoginMB,@idClaseString,'setNombre');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamUsuarioDTOCargueArchivos,@idOpCargueArchivoOptimizado,'getClave',@idClaseLoginMB,@idClaseString,'setClave');
-- Cargue archivo
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamCargueArchivos,@idOpCargueArchivoOptimizado,'getFechaCargar',@idClaseCargueOptimizadoMB,@idClaseDate,'setFechaCargar');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamCargueArchivos,@idOpCargueArchivoOptimizado,'getIdTipoArchivoSeleccionado',@idClaseCargueOptimizadoMB,@idClaseInt,'setIdTipoArchivoSeleccionado');


-- Definir Orden ejecucion 
-- PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE
-- Permite parametrizar los objetos que se van a construir en tiempo de ejecución y pasar dichos objetos a un método de operación
-- Permite parametrizar los objetos ManagedBean que serán utilizados en el flujo
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpCargueArchivoOptimizado,NULL,NULL,@idClaseAplicacionMB,1)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpCargueArchivoOptimizado,@idMetLogin,NULL,NULL,2)

INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpCargueArchivoOptimizado,NULL,@idClaseArchivoSeleccionadoVO,NULL,3)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpCargueArchivoOptimizado,@idMetSetCargarArchivoVO,NULL,NULL,4)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpCargueArchivoOptimizado,@idMetoHandleChangedTipoArchivoCargue,NULL,NULL,5)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpCargueArchivoOptimizado,@idMetCargarArchivoOptimizado,NULL,NULL,6)

--- Creacion de la variable para crear el objeto en tiempo de ejecucion
DECLARE @idCreacionInsArchivoVO INT
SET @idCreacionInsArchivoVO = (SELECT idEjecucionOperacion FROM PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE WHERE idOperacion = @idOpCargueArchivoOptimizado AND idClaseToBuildInst = @idClaseArchivoSeleccionadoVO AND ordenEjecucion = 3 )

-- Creacion de un objeto complejo en tiempo de ejecucion 
-- Definir los atributos que tiene la clase ArchivoSeleccionadoVO por ser objeto complejo en tiempo de ejecucion

INSERT INTO PUBLICADOR_WS_CLASES_ATRIBUTOS(idClasePrincipal,nombreAtributo,idClaseAtributo) VALUES(@idClaseArchivoSeleccionadoVO,'idTipoArchivo',@idClaseInt)
INSERT INTO PUBLICADOR_WS_CLASES_ATRIBUTOS(idClasePrincipal,nombreAtributo,idClaseAtributo) VALUES(@idClaseArchivoSeleccionadoVO,'nombreTipoArchivo',@idClaseString)
INSERT INTO PUBLICADOR_WS_CLASES_ATRIBUTOS(idClasePrincipal,nombreAtributo,idClaseAtributo) VALUES(@idClaseArchivoSeleccionadoVO,'nombreArchivo',@idClaseString)

DECLARE @idCreacionInsNombreArchivo INT
SET @idCreacionInsNombreArchivo = (SELECT idAtributo FROM PUBLICADOR_WS_CLASES_ATRIBUTOS WHERE idClasePrincipal = @idClaseArchivoSeleccionadoVO AND nombreAtributo = 'nombreArchivo' )

DECLARE @idCreacionInsIdTipoArchivo INT
SET @idCreacionInsIdTipoArchivo = (SELECT idAtributo FROM PUBLICADOR_WS_CLASES_ATRIBUTOS WHERE idClasePrincipal = @idClaseArchivoSeleccionadoVO AND nombreAtributo = 'idTipoArchivo' )

-- indicar al ws  los atributos 

--INSERT INTO PARAMETROS_TO_MAP_INST_CLASS(idEjecucionOperacion,idParametroOperacionWS,nombreParametroGetWS,idAtributoToSetValueClass) VALUES(@idCreacionInsArchivoVO,@idParamCargueArchivos,'getNombreArchivo',@idCreacionInsNombreArchivo)
--INSERT INTO PARAMETROS_TO_MAP_INST_CLASS(idEjecucionOperacion,idParametroOperacionWS,nombreParametroGetWS,idAtributoToSetValueClass) VALUES(@idCreacionInsArchivoVO,@idParamCargueArchivos,'getIdTipoArchivoSeleccionado',@idCreacionInsIdTipoArchivo)

---- Solo se deja aqui cuando se esta parametrizando un atributo complejo
-- Pasar el valor anterior que se crea en tiempo de ejecucion al set del MB 
INSERT INTO PARAMETROS_TO_MAP_METODOS (idParametroOperacion ,idParametroOperacionWS,nombreParametroGetWS,idResultEjecInstClass)
VALUES(@idParamMetArchivoSeleccionadoVO,NULL,NULL,@idCreacionInsArchivoVO)



--- Crear objeto en tiempo de ejecucion y pasar los parametros. 


