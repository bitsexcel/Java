//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xmlenc11;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.xml.security.binding.xmlenc.ReferenceList;


/**
 * <p>Clase Java para DerivedKeyType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DerivedKeyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.w3.org/2009/xmlenc11#}KeyDerivationMethod" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}ReferenceList" minOccurs="0"/&gt;
 *         &lt;element name="DerivedKeyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MasterKeyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Recipient" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anyURI" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DerivedKeyType", namespace = "http://www.w3.org/2009/xmlenc11#", propOrder = {
    "keyDerivationMethod",
    "referenceList",
    "derivedKeyName",
    "masterKeyName"
})
public class DerivedKeyType {

    @XmlElement(name = "KeyDerivationMethod", namespace = "http://www.w3.org/2009/xmlenc11#")
    protected KeyDerivationMethodType keyDerivationMethod;
    @XmlElement(name = "ReferenceList", namespace = "http://www.w3.org/2001/04/xmlenc#")
    protected ReferenceList referenceList;
    @XmlElement(name = "DerivedKeyName", namespace = "http://www.w3.org/2009/xmlenc11#")
    protected String derivedKeyName;
    @XmlElement(name = "MasterKeyName", namespace = "http://www.w3.org/2009/xmlenc11#")
    protected String masterKeyName;
    @XmlAttribute(name = "Recipient")
    protected String recipient;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "Type")
    @XmlSchemaType(name = "anyURI")
    protected String type;

    /**
     * Obtiene el valor de la propiedad keyDerivationMethod.
     * 
     * @return
     *     possible object is
     *     {@link KeyDerivationMethodType }
     *     
     */
    public KeyDerivationMethodType getKeyDerivationMethod() {
        return keyDerivationMethod;
    }

    /**
     * Define el valor de la propiedad keyDerivationMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyDerivationMethodType }
     *     
     */
    public void setKeyDerivationMethod(KeyDerivationMethodType value) {
        this.keyDerivationMethod = value;
    }

    /**
     * Obtiene el valor de la propiedad referenceList.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceList }
     *     
     */
    public ReferenceList getReferenceList() {
        return referenceList;
    }

    /**
     * Define el valor de la propiedad referenceList.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceList }
     *     
     */
    public void setReferenceList(ReferenceList value) {
        this.referenceList = value;
    }

    /**
     * Obtiene el valor de la propiedad derivedKeyName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDerivedKeyName() {
        return derivedKeyName;
    }

    /**
     * Define el valor de la propiedad derivedKeyName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDerivedKeyName(String value) {
        this.derivedKeyName = value;
    }

    /**
     * Obtiene el valor de la propiedad masterKeyName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterKeyName() {
        return masterKeyName;
    }

    /**
     * Define el valor de la propiedad masterKeyName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterKeyName(String value) {
        this.masterKeyName = value;
    }

    /**
     * Obtiene el valor de la propiedad recipient.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Define el valor de la propiedad recipient.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipient(String value) {
        this.recipient = value;
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

    /**
     * Obtiene el valor de la propiedad type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Define el valor de la propiedad type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
