//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2022.04.26 a las 04:49:43 PM CDT 
//


package org.apache.xml.security.binding.xmlenc11;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.apache.xml.security.binding.xmlenc11 package. 
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

    private final static QName _ConcatKDFParams_QNAME = new QName("http://www.w3.org/2009/xmlenc11#", "ConcatKDFParams");
    private final static QName _DerivedKey_QNAME = new QName("http://www.w3.org/2009/xmlenc11#", "DerivedKey");
    private final static QName _KeyDerivationMethod_QNAME = new QName("http://www.w3.org/2009/xmlenc11#", "KeyDerivationMethod");
    private final static QName _PBKDF2Params_QNAME = new QName("http://www.w3.org/2009/xmlenc11#", "PBKDF2-params");
    private final static QName _MGF_QNAME = new QName("http://www.w3.org/2009/xmlenc11#", "MGF");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.apache.xml.security.binding.xmlenc11
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PBKDF2ParameterType }
     * 
     */
    public PBKDF2ParameterType createPBKDF2ParameterType() {
        return new PBKDF2ParameterType();
    }

    /**
     * Create an instance of {@link ConcatKDFParamsType }
     * 
     */
    public ConcatKDFParamsType createConcatKDFParamsType() {
        return new ConcatKDFParamsType();
    }

    /**
     * Create an instance of {@link DerivedKeyType }
     * 
     */
    public DerivedKeyType createDerivedKeyType() {
        return new DerivedKeyType();
    }

    /**
     * Create an instance of {@link KeyDerivationMethodType }
     * 
     */
    public KeyDerivationMethodType createKeyDerivationMethodType() {
        return new KeyDerivationMethodType();
    }

    /**
     * Create an instance of {@link MGFType }
     * 
     */
    public MGFType createMGFType() {
        return new MGFType();
    }

    /**
     * Create an instance of {@link AlgorithmIdentifierType }
     * 
     */
    public AlgorithmIdentifierType createAlgorithmIdentifierType() {
        return new AlgorithmIdentifierType();
    }

    /**
     * Create an instance of {@link PRFAlgorithmIdentifierType }
     * 
     */
    public PRFAlgorithmIdentifierType createPRFAlgorithmIdentifierType() {
        return new PRFAlgorithmIdentifierType();
    }

    /**
     * Create an instance of {@link PBKDF2ParameterType.Salt }
     * 
     */
    public PBKDF2ParameterType.Salt createPBKDF2ParameterTypeSalt() {
        return new PBKDF2ParameterType.Salt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConcatKDFParamsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ConcatKDFParamsType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2009/xmlenc11#", name = "ConcatKDFParams")
    public JAXBElement<ConcatKDFParamsType> createConcatKDFParams(ConcatKDFParamsType value) {
        return new JAXBElement<ConcatKDFParamsType>(_ConcatKDFParams_QNAME, ConcatKDFParamsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DerivedKeyType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DerivedKeyType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2009/xmlenc11#", name = "DerivedKey")
    public JAXBElement<DerivedKeyType> createDerivedKey(DerivedKeyType value) {
        return new JAXBElement<DerivedKeyType>(_DerivedKey_QNAME, DerivedKeyType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyDerivationMethodType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link KeyDerivationMethodType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2009/xmlenc11#", name = "KeyDerivationMethod")
    public JAXBElement<KeyDerivationMethodType> createKeyDerivationMethod(KeyDerivationMethodType value) {
        return new JAXBElement<KeyDerivationMethodType>(_KeyDerivationMethod_QNAME, KeyDerivationMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PBKDF2ParameterType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PBKDF2ParameterType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2009/xmlenc11#", name = "PBKDF2-params")
    public JAXBElement<PBKDF2ParameterType> createPBKDF2Params(PBKDF2ParameterType value) {
        return new JAXBElement<PBKDF2ParameterType>(_PBKDF2Params_QNAME, PBKDF2ParameterType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MGFType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MGFType }{@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2009/xmlenc11#", name = "MGF")
    public JAXBElement<MGFType> createMGF(MGFType value) {
        return new JAXBElement<MGFType>(_MGF_QNAME, MGFType.class, null, value);
    }

}
