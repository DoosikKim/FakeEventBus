package test.doosik.com.fakeeventbus;

import java.util.List;

/**
 * Created by dskim98 on 16. 8. 2..
 */
public class EventData {
    // the datalist object being sent using the bus
    private List<String> datalist;

    public EventData(List<String> datalist)
    {
        this.datalist = datalist;
    }

    /**
     * @return the datalist
     */
    public List<String> getList()
    {
        return datalist;
    }
}
