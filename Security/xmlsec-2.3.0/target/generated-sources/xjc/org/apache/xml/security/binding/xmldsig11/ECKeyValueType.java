//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xmldsig11;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Clase Java para ECKeyValueType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ECKeyValueType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="ECParameters" type="{http://www.w3.org/2009/xmldsig11#}ECParametersType"/&gt;
 *           &lt;element name="NamedCurve" type="{http://www.w3.org/2009/xmldsig11#}NamedCurveType"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="PublicKey" type="{http://www.w3.org/2009/xmldsig11#}ECPointType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECKeyValueType", namespace = "http://www.w3.org/2009/xmldsig11#", propOrder = {
    "ecParameters",
    "namedCurve",
    "publicKey"
})
public class ECKeyValueType {

    @XmlElement(name = "ECParameters", namespace = "http://www.w3.org/2009/xmldsig11#")
    protected ECParametersType ecParameters;
    @XmlElement(name = "NamedCurve", namespace = "http://www.w3.org/2009/xmldsig11#")
    protected NamedCurveType namedCurve;
    @XmlElement(name = "PublicKey", namespace = "http://www.w3.org/2009/xmldsig11#", required = true)
    protected byte[] publicKey;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Obtiene el valor de la propiedad ecParameters.
     * 
     * @return
     *     possible object is
     *     {@link ECParametersType }
     *     
     */
    public ECParametersType getECParameters() {
        return ecParameters;
    }

    /**
     * Define el valor de la propiedad ecParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link ECParametersType }
     *     
     */
    public void setECParameters(ECParametersType value) {
        this.ecParameters = value;
    }

    /**
     * Obtiene el valor de la propiedad namedCurve.
     * 
     * @return
     *     possible object is
     *     {@link NamedCurveType }
     *     
     */
    public NamedCurveType getNamedCurve() {
        return namedCurve;
    }

    /**
     * Define el valor de la propiedad namedCurve.
     * 
     * @param value
     *     allowed object is
     *     {@link NamedCurveType }
     *     
     */
    public void setNamedCurve(NamedCurveType value) {
        this.namedCurve = value;
    }

    /**
     * Obtiene el valor de la propiedad publicKey.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPublicKey() {
        return publicKey;
    }

    /**
     * Define el valor de la propiedad publicKey.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPublicKey(byte[] value) {
        this.publicKey = value;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
