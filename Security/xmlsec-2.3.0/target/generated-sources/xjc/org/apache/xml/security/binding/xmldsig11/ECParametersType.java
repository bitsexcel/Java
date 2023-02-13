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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ECParametersType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ECParametersType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FieldID" type="{http://www.w3.org/2009/xmldsig11#}FieldIDType"/&gt;
 *         &lt;element name="Curve" type="{http://www.w3.org/2009/xmldsig11#}CurveType"/&gt;
 *         &lt;element name="Base" type="{http://www.w3.org/2009/xmldsig11#}ECPointType"/&gt;
 *         &lt;element name="Order" type="{http://www.w3.org/2000/09/xmldsig#}CryptoBinary"/&gt;
 *         &lt;element name="CoFactor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ValidationData" type="{http://www.w3.org/2009/xmldsig11#}ECValidationDataType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECParametersType", namespace = "http://www.w3.org/2009/xmldsig11#", propOrder = {
    "fieldID",
    "curve",
    "base",
    "order",
    "coFactor",
    "validationData"
})
public class ECParametersType {

    @XmlElement(name = "FieldID", namespace = "http://www.w3.org/2009/xmldsig11#", required = true)
    protected FieldIDType fieldID;
    @XmlElement(name = "Curve", namespace = "http://www.w3.org/2009/xmldsig11#", required = true)
    protected CurveType curve;
    @XmlElement(name = "Base", namespace = "http://www.w3.org/2009/xmldsig11#", required = true)
    protected byte[] base;
    @XmlElement(name = "Order", namespace = "http://www.w3.org/2009/xmldsig11#", required = true)
    protected byte[] order;
    @XmlElement(name = "CoFactor", namespace = "http://www.w3.org/2009/xmldsig11#")
    protected BigInteger coFactor;
    @XmlElement(name = "ValidationData", namespace = "http://www.w3.org/2009/xmldsig11#")
    protected ECValidationDataType validationData;

    /**
     * Obtiene el valor de la propiedad fieldID.
     * 
     * @return
     *     possible object is
     *     {@link FieldIDType }
     *     
     */
    public FieldIDType getFieldID() {
        return fieldID;
    }

    /**
     * Define el valor de la propiedad fieldID.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldIDType }
     *     
     */
    public void setFieldID(FieldIDType value) {
        this.fieldID = value;
    }

    /**
     * Obtiene el valor de la propiedad curve.
     * 
     * @return
     *     possible object is
     *     {@link CurveType }
     *     
     */
    public CurveType getCurve() {
        return curve;
    }

    /**
     * Define el valor de la propiedad curve.
     * 
     * @param value
     *     allowed object is
     *     {@link CurveType }
     *     
     */
    public void setCurve(CurveType value) {
        this.curve = value;
    }

    /**
     * Obtiene el valor de la propiedad base.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBase() {
        return base;
    }

    /**
     * Define el valor de la propiedad base.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBase(byte[] value) {
        this.base = value;
    }

    /**
     * Obtiene el valor de la propiedad order.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getOrder() {
        return order;
    }

    /**
     * Define el valor de la propiedad order.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setOrder(byte[] value) {
        this.order = value;
    }

    /**
     * Obtiene el valor de la propiedad coFactor.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCoFactor() {
        return coFactor;
    }

    /**
     * Define el valor de la propiedad coFactor.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCoFactor(BigInteger value) {
        this.coFactor = value;
    }

    /**
     * Obtiene el valor de la propiedad validationData.
     * 
     * @return
     *     possible object is
     *     {@link ECValidationDataType }
     *     
     */
    public ECValidationDataType getValidationData() {
        return validationData;
    }

    /**
     * Define el valor de la propiedad validationData.
     * 
     * @param value
     *     allowed object is
     *     {@link ECValidationDataType }
     *     
     */
    public void setValidationData(ECValidationDataType value) {
        this.validationData = value;
    }

}
