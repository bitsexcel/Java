//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xmldsig11;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Clase Java para FieldIDType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="FieldIDType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://www.w3.org/2009/xmldsig11#}Prime"/&gt;
 *         &lt;element ref="{http://www.w3.org/2009/xmldsig11#}TnB"/&gt;
 *         &lt;element ref="{http://www.w3.org/2009/xmldsig11#}PnB"/&gt;
 *         &lt;element ref="{http://www.w3.org/2009/xmldsig11#}GnB"/&gt;
 *         &lt;any processContents='lax' namespace='##other'/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FieldIDType", namespace = "http://www.w3.org/2009/xmldsig11#", propOrder = {
    "prime",
    "tnB",
    "pnB",
    "gnB",
    "any"
})
public class FieldIDType {

    @XmlElement(name = "Prime", namespace = "http://www.w3.org/2009/xmldsig11#")
    protected PrimeFieldParamsType prime;
    @XmlElement(name = "TnB", namespace = "http://www.w3.org/2009/xmldsig11#")
    protected TnBFieldParamsType tnB;
    @XmlElement(name = "PnB", namespace = "http://www.w3.org/2009/xmldsig11#")
    protected PnBFieldParamsType pnB;
    @XmlElement(name = "GnB", namespace = "http://www.w3.org/2009/xmldsig11#")
    protected CharTwoFieldParamsType gnB;
    @XmlAnyElement(lax = true)
    protected Object any;

    /**
     * Obtiene el valor de la propiedad prime.
     * 
     * @return
     *     possible object is
     *     {@link PrimeFieldParamsType }
     *     
     */
    public PrimeFieldParamsType getPrime() {
        return prime;
    }

    /**
     * Define el valor de la propiedad prime.
     * 
     * @param value
     *     allowed object is
     *     {@link PrimeFieldParamsType }
     *     
     */
    public void setPrime(PrimeFieldParamsType value) {
        this.prime = value;
    }

    /**
     * Obtiene el valor de la propiedad tnB.
     * 
     * @return
     *     possible object is
     *     {@link TnBFieldParamsType }
     *     
     */
    public TnBFieldParamsType getTnB() {
        return tnB;
    }

    /**
     * Define el valor de la propiedad tnB.
     * 
     * @param value
     *     allowed object is
     *     {@link TnBFieldParamsType }
     *     
     */
    public void setTnB(TnBFieldParamsType value) {
        this.tnB = value;
    }

    /**
     * Obtiene el valor de la propiedad pnB.
     * 
     * @return
     *     possible object is
     *     {@link PnBFieldParamsType }
     *     
     */
    public PnBFieldParamsType getPnB() {
        return pnB;
    }

    /**
     * Define el valor de la propiedad pnB.
     * 
     * @param value
     *     allowed object is
     *     {@link PnBFieldParamsType }
     *     
     */
    public void setPnB(PnBFieldParamsType value) {
        this.pnB = value;
    }

    /**
     * Obtiene el valor de la propiedad gnB.
     * 
     * @return
     *     possible object is
     *     {@link CharTwoFieldParamsType }
     *     
     */
    public CharTwoFieldParamsType getGnB() {
        return gnB;
    }

    /**
     * Define el valor de la propiedad gnB.
     * 
     * @param value
     *     allowed object is
     *     {@link CharTwoFieldParamsType }
     *     
     */
    public void setGnB(CharTwoFieldParamsType value) {
        this.gnB = value;
    }

    /**
     * Obtiene el valor de la propiedad any.
     * 
     * @return
     *     possible object is
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public Object getAny() {
        return any;
    }

    /**
     * Define el valor de la propiedad any.
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     {@link Object }
     *     
     */
    public void setAny(Object value) {
        this.any = value;
    }

}
