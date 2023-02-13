//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xmldsig11;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CharTwoFieldParamsType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CharTwoFieldParamsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharTwoFieldParamsType", namespace = "http://www.w3.org/2009/xmldsig11#", propOrder = {
    "m"
})
@XmlSeeAlso({
    TnBFieldParamsType.class,
    PnBFieldParamsType.class
})
public class CharTwoFieldParamsType {

    @XmlElement(name = "M", namespace = "http://www.w3.org/2009/xmldsig11#", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger m;

    /**
     * Obtiene el valor de la propiedad m.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getM() {
        return m;
    }

    /**
     * Define el valor de la propiedad m.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setM(BigInteger value) {
        this.m = value;
    }

}
