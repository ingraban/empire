//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.07 at 09:28:43 AM CET 
//


package name.saak.empire.schema;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlElementRefs;
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
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{}clear"/>
 *         &lt;element ref="{}mountain"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "clearOrMountain"
})
@XmlRootElement(name = "row")
public class Row {

    @XmlElementRefs({
        @XmlElementRef(name = "clear", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mountain", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<RowMilepost>> clearOrMountain;

    /**
     * Gets the value of the clearOrMountain property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clearOrMountain property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClearOrMountain().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link RowMilepost }{@code >}
     * {@link JAXBElement }{@code <}{@link RowMilepost }{@code >}
     * 
     * 
     */
    public List<JAXBElement<RowMilepost>> getClearOrMountain() {
        if (clearOrMountain == null) {
            clearOrMountain = new ArrayList<JAXBElement<RowMilepost>>();
        }
        return this.clearOrMountain;
    }

}
