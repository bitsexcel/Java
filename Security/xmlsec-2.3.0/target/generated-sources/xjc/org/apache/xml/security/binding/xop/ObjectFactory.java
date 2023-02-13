//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xop;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.apache.xml.security.binding.xop package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Include_QNAME = new QName("http://www.w3.org/2004/08/xop/include", "Include");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.apache.xml.security.binding.xop
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Include }
     * 
     */
    public Include createInclude() {
        return new Include();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Include }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Include }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2004/08/xop/include", name = "Include")
    public JAXBElement<Include> createInclude(Include value) {
        return new JAXBElement<Include>(_Include_QNAME, Include.class, null, value);
    }

}
