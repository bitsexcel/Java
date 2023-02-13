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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Clase Java para AlgorithmType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AlgorithmType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="URI" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *       &lt;attribute name="Description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="AlgorithmClass" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="RequirementLevel" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="SpecificationURL" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="JCEProvider" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="JCEName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="KeyLength" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="IVLength" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="RequiredKey" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AlgorithmType", namespace = "http://www.xmlsecurity.org/NS/configuration", propOrder = {
    "value"
})
public class AlgorithmType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "URI", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String uri;
    @XmlAttribute(name = "Description", required = true)
    protected String description;
    @XmlAttribute(name = "AlgorithmClass", required = true)
    protected String algorithmClass;
    @XmlAttribute(name = "RequirementLevel", required = true)
    protected String requirementLevel;
    @XmlAttribute(name = "SpecificationURL")
    protected String specificationURL;
    @XmlAttribute(name = "JCEProvider")
    protected String jceProvider;
    @XmlAttribute(name = "JCEName", required = true)
    protected String jceName;
    @XmlAttribute(name = "KeyLength")
    protected Integer keyLength;
    @XmlAttribute(name = "IVLength")
    protected Integer ivLength;
    @XmlAttribute(name = "RequiredKey")
    protected String requiredKey;

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
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtiene el valor de la propiedad algorithmClass.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgorithmClass() {
        return algorithmClass;
    }

    /**
     * Define el valor de la propiedad algorithmClass.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithmClass(String value) {
        this.algorithmClass = value;
    }

    /**
     * Obtiene el valor de la propiedad requirementLevel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequirementLevel() {
        return requirementLevel;
    }

    /**
     * Define el valor de la propiedad requirementLevel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequirementLevel(String value) {
        this.requirementLevel = value;
    }

    /**
     * Obtiene el valor de la propiedad specificationURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecificationURL() {
        return specificationURL;
    }

    /**
     * Define el valor de la propiedad specificationURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecificationURL(String value) {
        this.specificationURL = value;
    }

    /**
     * Obtiene el valor de la propiedad jceProvider.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJCEProvider() {
        return jceProvider;
    }

    /**
     * Define el valor de la propiedad jceProvider.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJCEProvider(String value) {
        this.jceProvider = value;
    }

    /**
     * Obtiene el valor de la propiedad jceName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJCEName() {
        return jceName;
    }

    /**
     * Define el valor de la propiedad jceName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJCEName(String value) {
        this.jceName = value;
    }

    /**
     * Obtiene el valor de la propiedad keyLength.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getKeyLength() {
        return keyLength;
    }

    /**
     * Define el valor de la propiedad keyLength.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setKeyLength(Integer value) {
        this.keyLength = value;
    }

    /**
     * Obtiene el valor de la propiedad ivLength.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIVLength() {
        return ivLength;
    }

    /**
     * Define el valor de la propiedad ivLength.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIVLength(Integer value) {
        this.ivLength = value;
    }

    /**
     * Obtiene el valor de la propiedad requiredKey.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiredKey() {
        return requiredKey;
    }

    /**
     * Define el valor de la propiedad requiredKey.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiredKey(String value) {
        this.requiredKey = value;
    }

}
