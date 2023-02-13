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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.xml.security.binding.xmldsig.DigestMethodType;


/**
 * <p>Clase Java para ConcatKDFParamsType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConcatKDFParamsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}DigestMethod"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="AlgorithmID" type="{http://www.w3.org/2001/XMLSchema}hexBinary" /&gt;
 *       &lt;attribute name="PartyUInfo" type="{http://www.w3.org/2001/XMLSchema}hexBinary" /&gt;
 *       &lt;attribute name="PartyVInfo" type="{http://www.w3.org/2001/XMLSchema}hexBinary" /&gt;
 *       &lt;attribute name="SuppPubInfo" type="{http://www.w3.org/2001/XMLSchema}hexBinary" /&gt;
 *       &lt;attribute name="SuppPrivInfo" type="{http://www.w3.org/2001/XMLSchema}hexBinary" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConcatKDFParamsType", namespace = "http://www.w3.org/2009/xmlenc11#", propOrder = {
    "digestMethod"
})
public class ConcatKDFParamsType {

    @XmlElement(name = "DigestMethod", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected DigestMethodType digestMethod;
    @XmlAttribute(name = "AlgorithmID")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] algorithmID;
    @XmlAttribute(name = "PartyUInfo")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] partyUInfo;
    @XmlAttribute(name = "PartyVInfo")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] partyVInfo;
    @XmlAttribute(name = "SuppPubInfo")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] suppPubInfo;
    @XmlAttribute(name = "SuppPrivInfo")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] suppPrivInfo;

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
     * Obtiene el valor de la propiedad algorithmID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getAlgorithmID() {
        return algorithmID;
    }

    /**
     * Define el valor de la propiedad algorithmID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithmID(byte[] value) {
        this.algorithmID = value;
    }

    /**
     * Obtiene el valor de la propiedad partyUInfo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getPartyUInfo() {
        return partyUInfo;
    }

    /**
     * Define el valor de la propiedad partyUInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartyUInfo(byte[] value) {
        this.partyUInfo = value;
    }

    /**
     * Obtiene el valor de la propiedad partyVInfo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getPartyVInfo() {
        return partyVInfo;
    }

    /**
     * Define el valor de la propiedad partyVInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartyVInfo(byte[] value) {
        this.partyVInfo = value;
    }

    /**
     * Obtiene el valor de la propiedad suppPubInfo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSuppPubInfo() {
        return suppPubInfo;
    }

    /**
     * Define el valor de la propiedad suppPubInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuppPubInfo(byte[] value) {
        this.suppPubInfo = value;
    }

    /**
     * Obtiene el valor de la propiedad suppPrivInfo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSuppPrivInfo() {
        return suppPrivInfo;
    }

    /**
     * Define el valor de la propiedad suppPrivInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuppPrivInfo(byte[] value) {
        this.suppPrivInfo = value;
    }

}
