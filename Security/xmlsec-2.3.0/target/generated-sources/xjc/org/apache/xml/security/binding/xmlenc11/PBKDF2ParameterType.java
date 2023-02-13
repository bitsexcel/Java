//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xmlenc11;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para PBKDF2ParameterType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PBKDF2ParameterType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Salt"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;choice&gt;
 *                   &lt;element name="Specified" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
 *                   &lt;element name="OtherSource" type="{http://www.w3.org/2009/xmlenc11#}AlgorithmIdentifierType"/&gt;
 *                 &lt;/choice&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="IterationCount" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="KeyLength" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/&gt;
 *         &lt;element name="PRF" type="{http://www.w3.org/2009/xmlenc11#}PRFAlgorithmIdentifierType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PBKDF2ParameterType", namespace = "http://www.w3.org/2009/xmlenc11#", propOrder = {
    "salt",
    "iterationCount",
    "keyLength",
    "prf"
})
public class PBKDF2ParameterType {

    @XmlElement(name = "Salt", namespace = "http://www.w3.org/2009/xmlenc11#", required = true)
    protected PBKDF2ParameterType.Salt salt;
    @XmlElement(name = "IterationCount", namespace = "http://www.w3.org/2009/xmlenc11#", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger iterationCount;
    @XmlElement(name = "KeyLength", namespace = "http://www.w3.org/2009/xmlenc11#", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger keyLength;
    @XmlElement(name = "PRF", namespace = "http://www.w3.org/2009/xmlenc11#", required = true)
    protected PRFAlgorithmIdentifierType prf;

    /**
     * Obtiene el valor de la propiedad salt.
     * 
     * @return
     *     possible object is
     *     {@link PBKDF2ParameterType.Salt }
     *     
     */
    public PBKDF2ParameterType.Salt getSalt() {
        return salt;
    }

    /**
     * Define el valor de la propiedad salt.
     * 
     * @param value
     *     allowed object is
     *     {@link PBKDF2ParameterType.Salt }
     *     
     */
    public void setSalt(PBKDF2ParameterType.Salt value) {
        this.salt = value;
    }

    /**
     * Obtiene el valor de la propiedad iterationCount.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIterationCount() {
        return iterationCount;
    }

    /**
     * Define el valor de la propiedad iterationCount.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIterationCount(BigInteger value) {
        this.iterationCount = value;
    }

    /**
     * Obtiene el valor de la propiedad keyLength.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getKeyLength() {
        return keyLength;
    }

    /**
     * Define el valor de la propiedad keyLength.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setKeyLength(BigInteger value) {
        this.keyLength = value;
    }

    /**
     * Obtiene el valor de la propiedad prf.
     * 
     * @return
     *     possible object is
     *     {@link PRFAlgorithmIdentifierType }
     *     
     */
    public PRFAlgorithmIdentifierType getPRF() {
        return prf;
    }

    /**
     * Define el valor de la propiedad prf.
     * 
     * @param value
     *     allowed object is
     *     {@link PRFAlgorithmIdentifierType }
     *     
     */
    public void setPRF(PRFAlgorithmIdentifierType value) {
        this.prf = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;choice&gt;
     *         &lt;element name="Specified" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/&gt;
     *         &lt;element name="OtherSource" type="{http://www.w3.org/2009/xmlenc11#}AlgorithmIdentifierType"/&gt;
     *       &lt;/choice&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "specified",
        "otherSource"
    })
    public static class Salt {

        @XmlElement(name = "Specified", namespace = "http://www.w3.org/2009/xmlenc11#")
        protected byte[] specified;
        @XmlElement(name = "OtherSource", namespace = "http://www.w3.org/2009/xmlenc11#")
        protected AlgorithmIdentifierType otherSource;

        /**
         * Obtiene el valor de la propiedad specified.
         * 
         * @return
         *     possible object is
         *     byte[]
         */
        public byte[] getSpecified() {
            return specified;
        }

        /**
         * Define el valor de la propiedad specified.
         * 
         * @param value
         *     allowed object is
         *     byte[]
         */
        public void setSpecified(byte[] value) {
            this.specified = value;
        }

        /**
         * Obtiene el valor de la propiedad otherSource.
         * 
         * @return
         *     possible object is
         *     {@link AlgorithmIdentifierType }
         *     
         */
        public AlgorithmIdentifierType getOtherSource() {
            return otherSource;
        }

        /**
         * Define el valor de la propiedad otherSource.
         * 
         * @param value
         *     allowed object is
         *     {@link AlgorithmIdentifierType }
         *     
         */
        public void setOtherSource(AlgorithmIdentifierType value) {
            this.otherSource = value;
        }

    }

}
