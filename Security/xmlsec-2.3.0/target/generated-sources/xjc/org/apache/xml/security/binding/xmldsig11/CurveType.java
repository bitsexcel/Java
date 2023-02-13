//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xmldsig11;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CurveType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CurveType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="A" type="{http://www.w3.org/2000/09/xmldsig#}CryptoBinary"/&gt;
 *         &lt;element name="B" type="{http://www.w3.org/2000/09/xmldsig#}CryptoBinary"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurveType", namespace = "http://www.w3.org/2009/xmldsig11#", propOrder = {
    "a",
    "b"
})
public class CurveType {

    @XmlElement(name = "A", namespace = "http://www.w3.org/2009/xmldsig11#", required = true)
    protected byte[] a;
    @XmlElement(name = "B", namespace = "http://www.w3.org/2009/xmldsig11#", required = true)
    protected byte[] b;

    /**
     * Obtiene el valor de la propiedad a.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getA() {
        return a;
    }

    /**
     * Define el valor de la propiedad a.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setA(byte[] value) {
        this.a = value;
    }

    /**
     * Obtiene el valor de la propiedad b.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getB() {
        return b;
    }

    /**
     * Define el valor de la propiedad b.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setB(byte[] value) {
        this.b = value;
    }

}
