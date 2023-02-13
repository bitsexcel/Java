//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xmldsig.pss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.apache.xml.security.binding.xmldsig.DigestMethodType;


/**
 * <p>Clase Java para RSAPSSParamsType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RSAPSSParamsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}DigestMethod" minOccurs="0"/&gt;
 *         &lt;element name="MaskGenerationFunction" type="{http://www.w3.org/2007/05/xmldsig-more#}MaskGenerationFunctionType" minOccurs="0"/&gt;
 *         &lt;element name="SaltLength" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="TrailerField" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RSAPSSParamsType", namespace = "http://www.w3.org/2007/05/xmldsig-more#", propOrder = {
    "digestMethod",
    "maskGenerationFunction",
    "saltLength",
    "trailerField"
})
@XmlSeeAlso({
    RSAPSSParams.class
})
public class RSAPSSParamsType {

    @XmlElement(name = "DigestMethod", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected DigestMethodType digestMethod;
    @XmlElement(name = "MaskGenerationFunction", namespace = "http://www.w3.org/2007/05/xmldsig-more#")
    protected MaskGenerationFunctionType maskGenerationFunction;
    @XmlElement(name = "SaltLength", namespace = "http://www.w3.org/2007/05/xmldsig-more#")
    protected Integer saltLength;
    @XmlElement(name = "TrailerField", namespace = "http://www.w3.org/2007/05/xmldsig-more#")
    protected Integer trailerField;

    /**
     * Obtiene el valor de la propiedad digestMethod.
     * 
     * @return
     *     possible object is
     *     {@link DigestMethodType }
     *     
     */
    public DigestMethodType getDigestMethod() {
        return digestMethod;
    }

    /**
     * Define el valor de la propiedad digestMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link DigestMethodType }
     *     
     */
    public void setDigestMethod(DigestMethodType value) {
        this.digestMethod = value;
    }

    /**
     * Obtiene el valor de la propiedad maskGenerationFunction.
     * 
     * @return
     *     possible object is
     *     {@link MaskGenerationFunctionType }
     *     
     */
    public MaskGenerationFunctionType getMaskGenerationFunction() {
        return maskGenerationFunction;
    }

    /**
     * Define el valor de la propiedad maskGenerationFunction.
     * 
     * @param value
     *     allowed object is
     *     {@link MaskGenerationFunctionType }
     *     
     */
    public void setMaskGenerationFunction(MaskGenerationFunctionType value) {
        this.maskGenerationFunction = value;
    }

    /**
     * Obtiene el valor de la propiedad saltLength.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSaltLength() {
        return saltLength;
    }

    /**
     * Define el valor de la propiedad saltLength.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSaltLength(Integer value) {
        this.saltLength = value;
    }

    /**
     * Obtiene el valor de la propiedad trailerField.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTrailerField() {
        return trailerField;
    }

    /**
     * Define el valor de la propiedad trailerField.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTrailerField(Integer value) {
        this.trailerField = value;
    }

}
