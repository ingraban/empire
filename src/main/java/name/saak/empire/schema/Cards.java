//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.07 at 09:28:43 AM CET 
//


package name.saak.empire.schema;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}demand-card"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "demandCard"
})
@XmlRootElement(name = "cards")
public class Cards {

    @XmlElement(name = "demand-card", required = true)
    protected DemandCard demandCard;

    /**
     * Gets the value of the demandCard property.
     * 
     * @return
     *     possible object is
     *     {@link DemandCard }
     *     
     */
    public DemandCard getDemandCard() {
        return demandCard;
    }

    /**
     * Sets the value of the demandCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link DemandCard }
     *     
     */
    public void setDemandCard(DemandCard value) {
        this.demandCard = value;
    }

}
