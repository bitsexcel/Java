//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xmlenc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EncryptedKeyType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EncryptedKeyType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.w3.org/2001/04/xmlenc#}EncryptedType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}ReferenceList" minOccurs="0"/&gt;
 *         &lt;element name="CarriedKeyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Recipient" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptedKeyType", namespace = "http://www.w3.org/2001/04/xmlenc#", propOrder = {
    "referenceList",
    "carriedKeyName"
})
public class EncryptedKeyType
    extends EncryptedType
{

    @XmlElement(name = "ReferenceList", namespace = "http://www.w3.org/2001/04/xmlenc#")
    protected ReferenceList referenceList;
    @XmlElement(name = "CarriedKeyName", namespace = "http://www.w3.org/2001/04/xmlenc#")
    protected String carriedKeyName;
    @XmlAttribute(name = "Recipient")
    protected String recipient;

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
     * Obtiene el valor de la propiedad carriedKeyName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarriedKeyName() {
        return carriedKeyName;
    }

    /**
     * Define el valor de la propiedad carriedKeyName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarriedKeyName(String value) {
        this.carriedKeyName = value;
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

}
