package api.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rowDto")
public class RowDto {
    @XmlElement
    public int getTest() {
        return test;
    }

    @XmlElement
    public void setTest(int test) {
        this.test = test;
    }

    private int test;
}
