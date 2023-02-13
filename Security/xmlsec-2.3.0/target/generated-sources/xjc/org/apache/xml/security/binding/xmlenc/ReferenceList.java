//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xmlenc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice maxOccurs="unbounded"&gt;
 *         &lt;element name="DataReference" type="{http://www.w3.org/2001/04/xmlenc#}ReferenceType"/&gt;
 *         &lt;element name="KeyReference" type="{http://www.w3.org/2001/04/xmlenc#}ReferenceType"/&gt;
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
    "dataReferenceOrKeyReference"
})
@XmlRootElement(name = "ReferenceList", namespace = "http://www.w3.org/2001/04/xmlenc#")
public class ReferenceList {

    @XmlElementRefs({
        @XmlElementRef(name = "DataReference", namespace = "http://www.w3.org/2001/04/xmlenc#", type = JAXBElement.class),
        @XmlElementRef(name = "KeyReference", namespace = "http://www.w3.org/2001/04/xmlenc#", type = JAXBElement.class)
    })
    protected List<JAXBElement<ReferenceType>> dataReferenceOrKeyReference;

    /**
     * Gets the value of the dataReferenceOrKeyReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataReferenceOrKeyReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataReferenceOrKeyReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}
     * {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<ReferenceType>> getDataReferenceOrKeyReference() {
        if (dataReferenceOrKeyReference == null) {
            dataReferenceOrKeyReference = new ArrayList<JAXBElement<ReferenceType>>();
        }
        return this.dataReferenceOrKeyReference;
    }

}
