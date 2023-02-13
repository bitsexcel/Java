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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConfigurationType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConfigurationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Properties" type="{http://www.xmlsecurity.org/NS/configuration}PropertiesType"/&gt;
 *         &lt;element name="SecurityHeaderHandlers" type="{http://www.xmlsecurity.org/NS/configuration}SecurityHeaderHandlersType"/&gt;
 *         &lt;element name="TransformAlgorithms" type="{http://www.xmlsecurity.org/NS/configuration}TransformAlgorithmsType"/&gt;
 *         &lt;element name="JCEAlgorithmMappings" type="{http://www.xmlsecurity.org/NS/configuration}JCEAlgorithmMappingsType"/&gt;
 *         &lt;element name="ResourceResolvers" type="{http://www.xmlsecurity.org/NS/configuration}ResourceResolversType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="target" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfigurationType", namespace = "http://www.xmlsecurity.org/NS/configuration", propOrder = {
    "properties",
    "securityHeaderHandlers",
    "transformAlgorithms",
    "jceAlgorithmMappings",
    "resourceResolvers"
})
public class ConfigurationType {

    @XmlElement(name = "Properties", namespace = "http://www.xmlsecurity.org/NS/configuration", required = true)
    protected PropertiesType properties;
    @XmlElement(name = "SecurityHeaderHandlers", namespace = "http://www.xmlsecurity.org/NS/configuration", required = true)
    protected SecurityHeaderHandlersType securityHeaderHandlers;
    @XmlElement(name = "TransformAlgorithms", namespace = "http://www.xmlsecurity.org/NS/configuration", required = true)
    protected TransformAlgorithmsType transformAlgorithms;
    @XmlElement(name = "JCEAlgorithmMappings", namespace = "http://www.xmlsecurity.org/NS/configuration", required = true)
    protected JCEAlgorithmMappingsType jceAlgorithmMappings;
    @XmlElement(name = "ResourceResolvers", namespace = "http://www.xmlsecurity.org/NS/configuration", required = true)
    protected ResourceResolversType resourceResolvers;
    @XmlAttribute(name = "target")
    protected String target;

    /**
     * Obtiene el valor de la propiedad properties.
     * 
     * @return
     *     possible object is
     *     {@link PropertiesType }
     *     
     */
    public PropertiesType getProperties() {
        return properties;
    }

    /**
     * Define el valor de la propiedad properties.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertiesType }
     *     
     */
    public void setProperties(PropertiesType value) {
        this.properties = value;
    }

    /**
     * Obtiene el valor de la propiedad securityHeaderHandlers.
     * 
     * @return
     *     possible object is
     *     {@link SecurityHeaderHandlersType }
     *     
     */
    public SecurityHeaderHandlersType getSecurityHeaderHandlers() {
        return securityHeaderHandlers;
    }

    /**
     * Define el valor de la propiedad securityHeaderHandlers.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityHeaderHandlersType }
     *     
     */
    public void setSecurityHeaderHandlers(SecurityHeaderHandlersType value) {
        this.securityHeaderHandlers = value;
    }

    /**
     * Obtiene el valor de la propiedad transformAlgorithms.
     * 
     * @return
     *     possible object is
     *     {@link TransformAlgorithmsType }
     *     
     */
    public TransformAlgorithmsType getTransformAlgorithms() {
        return transformAlgorithms;
    }

    /**
     * Define el valor de la propiedad transformAlgorithms.
     * 
     * @param value
     *     allowed object is
     *     {@link TransformAlgorithmsType }
     *     
     */
    public void setTransformAlgorithms(TransformAlgorithmsType value) {
        this.transformAlgorithms = value;
    }

    /**
     * Obtiene el valor de la propiedad jceAlgorithmMappings.
     * 
     * @return
     *     possible object is
     *     {@link JCEAlgorithmMappingsType }
     *     
     */
    public JCEAlgorithmMappingsType getJCEAlgorithmMappings() {
        return jceAlgorithmMappings;
    }

    /**
     * Define el valor de la propiedad jceAlgorithmMappings.
     * 
     * @param value
     *     allowed object is
     *     {@link JCEAlgorithmMappingsType }
     *     
     */
    public void setJCEAlgorithmMappings(JCEAlgorithmMappingsType value) {
        this.jceAlgorithmMappings = value;
    }

    /**
     * Obtiene el valor de la propiedad resourceResolvers.
     * 
     * @return
     *     possible object is
     *     {@link ResourceResolversType }
     *     
     */
    public ResourceResolversType getResourceResolvers() {
        return resourceResolvers;
    }

    /**
     * Define el valor de la propiedad resourceResolvers.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceResolversType }
     *     
     */
    public void setResourceResolvers(ResourceResolversType value) {
        this.resourceResolvers = value;
    }

    /**
     * Obtiene el valor de la propiedad target.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Define el valor de la propiedad target.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

}
