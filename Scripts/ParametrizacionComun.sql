USE DBDYNAMICWS
GO

	-- Webservice
	-- package_ws: Donde se va generar el WebService (tiempo de compilación)
	-- idClassEJBRemote: EJB intermediario entre el cliente y el publicador del Web Service - comunicación   
	INSERT INTO PUBLICADOR_WS_ESTRUCTURA (nombreWs, portName,targetNamespace,package_ws,descripcionWs,contextoWS,contexto_app_cliente,idClassEJBRemote,habilitado) 
		VALUES ('CentroComputoWS','CentroComputoWSPort','http://com.ath.unico.centroComputo.ws/','com.ath.unico.cc.webservice','Web Service Centro Computo Unico',
			'CentroComputo' ,'CentroComputoWeb',@idClaseDynamicExcecutionSessionBeanRemote,1);
	 DECLARE @idWs INT
	 SET @idWs = (SELECT idWs FROM PUBLICADOR_WS_ESTRUCTURA WHERE nombreWs LIKE 'CentroComputoWS')

INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','AplicacionMB','aplicacionMB',NULL);--9
INSERT INTO PUBLICADOR_WS_CLASES (packageClass,nombreCLase,aliasClase,archivoXSD) VALUES('com.ath.intranet.cc.web.managedbean','LoginMB','loginMB',NULL);--9
	 
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

INSERT INTO PUBLICADOR_WS_METODOS_OPERACIONES (metodoOperacion, descripcionMetdOperacion,idClasePrincipalMetdOperacion,idClaseMetdRetornoOperacion)VALUES ('login',null,@idClaseLoginMB,@idClaseString);

