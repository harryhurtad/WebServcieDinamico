USE DBDYNAMICWS
GO

-- Clases
-- Se usan para definir las clases cuando el cliente las requiere (Reflexion), las clases que el cliente necesita para realizar el flujo funcional
-- - se definen los objetos que representan los parámetros del webservice: 
-- - Para definir los objetos que utilizan los métodos de las operaciones del flujo funcional
-- - ManagedBean que involucran el flujo funcional
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','AplicacionMB','aplicacionMB',NULL);--9
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','LoginMB','loginMB',NULL);--9
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','CargueOptimizadoMB','cargueOptimizadoMB',NULL);--9
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.vo','ArchivoSeleccionadoVO',NULL,NULL);--9

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
INSERT INTO PUBLICADOR_WS_CLASES (nombreCLase, packageClass,archivoXSD) VALUES(
		'RealizarCargueDTO','com.unico.ws.webservice','<?xml version="1.0" encoding="ISO-8859-1"?>

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
    <xsd:complexType name="RealizarCargueDTO">
        <xsd:sequence>
            <xsd:element name="idTipoArchivoSeleccionado" type="xsd:int"/>
            <xsd:element name="fechaCargar" type="xsd:date"/>
            <xsd:element name="nombreArchivo" type="xsd:string"/>
        </xsd:sequence>
    </xsd:complexType>
    <xsd:element name="realizarCargueDTO" type="RealizarCargueDTO"/>
</xsd:schema>
');


-- Consulta clases
DECLARE @idClaseHelper INT
SET @idClaseHelper = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'WSDynamicUtil')
DECLARE @idClaseDynamicExcecutionSessionBeanRemote INT
SET @idClaseDynamicExcecutionSessionBeanRemote = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'DynamicExcecutionSessionBeanRemote')
DECLARE @idClaseInteger INT
SET @idClaseInteger = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'Integer')
DECLARE @idClaseInt INT
SET @idClaseInt = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'int')

DECLARE @idClaseString INT
SET @idClaseString = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'String')
DECLARE @idClaseDate INT
SET @idClaseDate = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'Date')
DECLARE @idClaseHtmlSelectOneMenu INT
SET @idClaseHtmlSelectOneMenu = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'HtmlSelectOneMenu')
DECLARE @idClaseValueChangeEvent INT
SET @idClaseValueChangeEvent = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'ValueChangeEvent')

DECLARE @idClaseAplicacionMB INT
SET @idClaseAplicacionMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'AplicacionMB')
DECLARE @idClaseLoginMB INT
SET @idClaseLoginMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'LoginMB')
DECLARE @idClaseCargueOptimizadoMB INT
SET @idClaseCargueOptimizadoMB = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'CargueOptimizadoMB')

DECLARE @idClaseUsuarioDTO INT
SET @idClaseUsuarioDTO = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'UsuarioDTO')
DECLARE @idClaseRealizarCargueDTO INT
SET @idClaseRealizarCargueDTO = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'RealizarCargueDTO')
DECLARE @idClaseArchivoSeleccionadoVO INT
SET @idClaseArchivoSeleccionadoVO = (SELECT idClase FROM PUBLICADOR_WS_CLASES WHERE nombreCLase LIKE 'ArchivoSeleccionadoVO')

-- Dependencia clases
 -- no hay

-- Webservice
-- package_ws: Donde se va generar el WebService (tiempo de compilación)
-- idClassEJBRemote: EJB intermediario entre el cliente y el publicador del Web Service - comunicación   
INSERT INTO PUBLICADOR_WS_ESTRUCTURA (nombreWs, portName,targetNamespace,package_ws,descripcionWs,contextoWS,contexto_app_cliente,idClassEJBRemote,habilitado) 
	VALUES ('CentroComputoWS','CentroComputoWSPort','http://com.ath.unico.centroComputo.ws/','com.ath.unico.cc.webservice','Web Service Centro Computo Unico',
		'CentroComputo' ,'CentroComputoWeb',@idClaseDynamicExcecutionSessionBeanRemote,1);
 DECLARE @idWs INT
 SET @idWs = (SELECT idWs FROM PUBLICADOR_WS_ESTRUCTURA WHERE nombreWs LIKE 'CentroComputoWS')

 -- Operaciones
-- Descripción de las operaciones que va contener el web service
INSERT INTO PUBLICADOR_WS_OPERACIONES (nombreOperacion, descripcionOperacion, metodoOperacion,idClaseRetornoOperacion)VALUES ('RealizarCargue','Realiza el cargue de un archivo en unico','realizarCargue',null);

DECLARE @idOpRealizarCargue INT
SET @idOpRealizarCargue = (SELECT idOperacion FROM PUBLICADOR_WS_OPERACIONES WHERE nombreOperacion LIKE 'RealizarCargue')


---- Asociar la operacion al webservice
-- Tabla relación de las operaciones que pertenecen a un ws
INSERT INTO PUBLICADOR_WS_ESTRUCTURA_OPERACIONES  (idWs, idOperacion)VALUES(@idWs,@idOpRealizarCargue);


-- Parametros operacion
-- Atributos de las operaciones que dependen de la definición de la firma de la operación
INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClaseValor,ordenParametro)  VALUES(@idOpRealizarCargue,'usuarioDTO',@idClaseUsuarioDTO,1);
INSERT INTO PUBLICADOR_WS_PARAMETROS_OPERACIONES(idOperacion,nombreParametro,idClaseValor,ordenParametro)  VALUES(@idOpRealizarCargue,'realizarCargueDTO',@idClaseRealizarCargueDTO,2);

-- Atributos de los MB
DECLARE @idParamUsuarioDTOOpRealizarCargue INT
SET @idParamUsuarioDTOOpRealizarCargue = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES WHERE nombreParametro LIKE 'usuarioDTO')
DECLARE @idParamRealizarCargueDTOOpRealizarCargue INT
SET @idParamRealizarCargueDTOOpRealizarCargue = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_OPERACIONES WHERE nombreParametro LIKE 'realizarCargueDTO')




-- Atributos Clases
 -- Atributos clases especiales definidas como atributos en el managedBean
INSERT INTO PUBLICADOR_WS_CLASES_ATRIBUTOS(idClasePrincipal,nombreAtributo,idClaseAtributo) VALUES(@idClaseArchivoSeleccionadoVO,'idTipoArchivo',@idClaseInt)
INSERT INTO PUBLICADOR_WS_CLASES_ATRIBUTOS(idClasePrincipal,nombreAtributo,idClaseAtributo) VALUES(@idClaseArchivoSeleccionadoVO,'nombreTipoArchivo',@idClaseString)
INSERT INTO PUBLICADOR_WS_CLASES_ATRIBUTOS(idClasePrincipal,nombreAtributo,idClaseAtributo) VALUES(@idClaseArchivoSeleccionadoVO,'nombreArchivo',@idClaseString)
DECLARE @idAtributoIdTipoArchivoArchivoSeleccionadoVO INT
SET @idAtributoIdTipoArchivoArchivoSeleccionadoVO = (SELECT idAtributo FROM PUBLICADOR_WS_CLASES_ATRIBUTOS WHERE idClasePrincipal = @idClaseArchivoSeleccionadoVO AND nombreAtributo = 'idTipoArchivo')

DECLARE @idAtributonombreTipoArchivoArchivoSeleccionadoVO INT
SET @idAtributonombreTipoArchivoArchivoSeleccionadoVO = (SELECT idAtributo FROM PUBLICADOR_WS_CLASES_ATRIBUTOS WHERE idClasePrincipal = @idClaseArchivoSeleccionadoVO AND nombreAtributo = 'nombreTipoArchivo')

DECLARE @idAtributonombreArchivoArchivoSeleccionadoVO INT
SET @idAtributonombreArchivoArchivoSeleccionadoVO = (SELECT idAtributo FROM PUBLICADOR_WS_CLASES_ATRIBUTOS WHERE idClasePrincipal = @idClaseArchivoSeleccionadoVO AND nombreAtributo = 'nombreArchivo')



-- Login
-- Métodos get y set del managedbean, para pasar información 
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamUsuarioDTOOpRealizarCargue,@idOpRealizarCargue,'getUsuario',@idClaseLoginMB,@idClaseString,'setNombre');
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamUsuarioDTOOpRealizarCargue,@idOpRealizarCargue,'getClave',@idClaseLoginMB,@idClaseString,'setClave');
-- CargueOptimizadoMB
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamRealizarCargueDTOOpRealizarCargue,@idOpRealizarCargue,'getIdTipoArchivoSeleccionado',@idClaseCargueOptimizadoMB,@idClaseInt,'setIdTipoArchivoSeleccionado');--idTipoArchivoSeleccionado
INSERT INTO PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN  (idParametroOperacionWS,idOperacion,nombreGetFieldWS,idClaseManageBean,idClaseFielManageBean,nombreAtributoManageBean)VALUES(@idParamRealizarCargueDTOOpRealizarCargue,@idOpRealizarCargue,'getFechaCargar',@idClaseCargueOptimizadoMB,@idClaseDate,'setFechaCargar');--fechaCargar





-- Metodos operacion
-- Métodos que involucran el flujo funcional a parametrizar (ej CargueOptimizado, login)
-- Permite pasar información de respuesta de un método a otro
-- MB Aplicacion
-- MB Login




INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('login',null,@idClaseLoginMB,@idClaseString);
DECLARE @idMetLogin INT
SET @idMetLogin = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'login')

-- MB CargueOptimizado
INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('handleChangedTipoArchivoCargue',null,@idClaseCargueOptimizadoMB,null);
DECLARE @idMetHandleChangedTipoArchivoCargue INT
SET @idMetHandleChangedTipoArchivoCargue = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'handleChangedTipoArchivoCargue')

INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('setArchivoSeleccionadoVO',null,@idClaseCargueOptimizadoMB,null);
DECLARE @idMetSetArchivoSeleccionadoVO INT
SET @idMetSetArchivoSeleccionadoVO = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'setArchivoSeleccionadoVO')

INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('cargarArchivoOptimizado',null,@idClaseCargueOptimizadoMB,null);
DECLARE @idMetCargarArchivoOptimizado INT
SET @idMetCargarArchivoOptimizado = (SELECT idMetodoOperacion FROM PUBLICADOR_WS_METODOS_OPERACIONES WHERE metodoOperacion LIKE 'cargarArchivoOptimizado')

-- Parametos Metodos
-- Parámetros de los métodos que se invocan para el flujo
--INSERT INTO PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES (idMetodoOperacion, nombreParametro, idClase,ordenParametro)VALUES (@idMetchangedTipoArchivoCargue,'event',@idClaseValueChangeEvent,1);
--DECLARE @idParamMetchangedTipoArchivoCargue INT
--SET @idParamMetchangedTipoArchivoCargue = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES WHERE nombreparametro LIKE 'event')

INSERT INTO PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES (idMetodoOperacion, nombreParametro, idClase,ordenParametro)VALUES (@idMetSetArchivoSeleccionadoVO,'archivoSeleccionadoVO',@idClaseArchivoSeleccionadoVO,1);
DECLARE @idParamMetArchivoSeleccionadoVO INT
SET @idParamMetArchivoSeleccionadoVO = (SELECT idParametroOperacion FROM PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES WHERE nombreparametro LIKE 'archivoSeleccionadoVO')



-- PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE
-- Permite parametrizar los objetos que se van a construir en tiempo de ejecución y pasar dichos objetos a un método de operación
-- Permite parametrizar los objetos ManagedBean que serán utilizados en el flujo
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpRealizarCargue,NULL,NULL,@idClaseAplicacionMB,1)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpRealizarCargue,@idMetLogin,NULL,NULL,2)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpRealizarCargue,NULL,@idClaseArchivoSeleccionadoVO,NULL,3)
DECLARE @idEjecClaseArchivoSeleccionadoVO INT
SET @idEjecClaseArchivoSeleccionadoVO = (SELECT idEjecucionOperacion FROM PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE WHERE idOperacion = @idOpRealizarCargue AND idClaseToBuildInst = @idClaseArchivoSeleccionadoVO)

INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpRealizarCargue,@idMetSetArchivoSeleccionadoVO,NULL,NULL,4)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpRealizarCargue,@idMetHandleChangedTipoArchivoCargue,NULL,NULL,5)
INSERT INTO PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE(idOperacion,idMetodoOperacion,idClaseToBuildInst,idClassManageBeanFind,ordenEjecucion) VALUES(@idOpRealizarCargue,@idMetCargarArchivoOptimizado,NULL,NULL,6)

-- Pasa los valores de las instancias creadas en tiempo de ejecución a una clase
INSERT INTO PARAMETROS_TO_MAP_INST_CLASS(idEjecucionOperacion,idParametroOperacionWS,nombreParametroGetWS,idAtributoToSetValueClass) VALUES(@idEjecClaseArchivoSeleccionadoVO,@idParamRealizarCargueDTOOpRealizarCargue,'getIdTipoArchivoSeleccionado',@idAtributoIdTipoArchivoArchivoSeleccionadoVO)
INSERT INTO PARAMETROS_TO_MAP_INST_CLASS(idEjecucionOperacion,idParametroOperacionWS,nombreParametroGetWS,idAtributoToSetValueClass) VALUES(@idEjecClaseArchivoSeleccionadoVO,@idParamRealizarCargueDTOOpRealizarCargue,'getNombreArchivo',@idAtributonombreArchivoArchivoSeleccionadoVO)

-- PARAMETROS_TO_MAP_METODOS
-- 
INSERT INTO PARAMETROS_TO_MAP_METODOS (idParametroOperacion ,idParametroOperacionWS,nombreParametroGetWS,idResultEjecInstClass)
VALUES(@idParamMetArchivoSeleccionadoVO,NULL,NULL,@idEjecClaseArchivoSeleccionadoVO)
		


--METODOS_OPERACIONES_JSF
-- Configuración de eventos especiales de Faces.
--DECLARE @idParamOpRealizarCargueDTO INT
--SET @idParamOpRealizarCargueDTO = (select idParametroOperacion from PUBLICADOR_WS_PARAMETROS_OPERACIONES where nombreparametro like 'realizarCargueDTO')
--INSERT INTO METODOS_OPERACIONES_JSF (idMetodoOperacion, idClassParamEvent, idClassInternalValEvent,idClassUIComponentJSF,idParametroOperacionWS,nombreGetAtributoParamWS)VALUES (@idMetchangedTipoArchivoCargue,@idClaseValueChangeEvent,@idClaseInteger,@idClaseHtmlSelectOneMenu,@idParamOpRealizarCargueDTO,'getIdTipoArchivo');




