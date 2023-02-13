//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Clase Java para TransformAlgorithmType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="TransformAlgorithmType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="URI" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="JAVACLASS" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="INOUT" type="{http://www.xmlsecurity.org/NS/configuration}inOutAttrType" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransformAlgorithmType", namespace = "http://www.xmlsecurity.org/NS/configuration", propOrder = {
    "value"
})
public class TransformAlgorithmType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "URI", required = true)
    protected String uri;
    @XmlAttribute(name = "JAVACLASS", required = true)
    protected String javaclass;
    @XmlAttribute(name = "INOUT")
    protected InOutAttrType inout;

    /**
     * Obtiene el valor de la propiedad value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Define el valor de la propiedad value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Obtiene el valor de la propiedad uri.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURI() {
        return uri;
    }

    /**
     * Define el valor de la propiedad uri.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURI(String value) {
        this.uri = value;
    }

    /**
     * Obtiene el valor de la propiedad javaclass.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJAVACLASS() {
        return javaclass;
    }

    /**
     * Define el valor de la propiedad javaclass.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJAVACLASS(String value) {
        this.javaclass = value;
    }

    /**
     * Obtiene el valor de la propiedad inout.
     * 
     * @return
     *     possible object is
     *     {@link InOutAttrType }
     *     
     */
    public InOutAttrType getINOUT() {
        return inout;
    }

    /**
     * Define el valor de la propiedad inout.
     * 
     * @param value
     *     allowed object is
     *     {@link InOutAttrType }
     *     
     */
    public void setINOUT(InOutAttrType value) {
        this.inout = value;
    }

}
