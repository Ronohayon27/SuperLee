import HR.Bussiness.Days;
import HR.Bussiness.Super;
import HR.Bussiness.Weekly;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperTest {
    @Test
    public void addWorker() {
        Super Super1 = new Super("zolretzah");
        Super1.AddWorker("1");
        Super1.AddWorker("2");
        assertEquals(2,Super1.GetWorkers().size());
    }
    @Test
    public void removeWorker() {
        Super Super1 = new Super("zolretzah");
        Super1.AddWorker("1");
        Super1.AddWorker("2");
        assertEquals(2,Super1.GetWorkers().size());
        Super1.RemoveWorker("1");
        Super1.RemoveWorker("2");
        assertEquals(0,Super1.GetWorkers().size());
    }
    @Test
    public void getName() {
        Super Super1 = new Super("zolretzah");
        assertEquals("zolretzah",Super1.GetName());

    }
    @Test
    public void hasWeekly() {
        Super Super1 = new Super("zolretzah");
        assertFalse(Super1.HasWeekly());
        Weekly week=new Weekly();
        Super1.AddWeekly(week);
        assertTrue(Super1.HasWeekly());
    }
    @Test
    public void getEnd_evening() {
        Super Super1 = new Super("zolretzah");
        assertEquals(23,Super1.getEnd_evening(Days.Sunday),0);
        assertEquals(23,Super1.getEnd_evening(Days.Saturday),0);
        assertEquals(23,Super1.getEnd_evening(Days.Wednesday),0);
    }
    @Test
    public void setEnd_evening() {
        Super Super1 = new Super("zolretzah");
        assertEquals(23,Super1.getEnd_evening(Days.Sunday),0);
        Super1.setEnd_evening(Days.Sunday,10.00);
        assertEquals(10,Super1.getEnd_evening(Days.Sunday),0);
    }

    @Test
    public void getEnd_morning() {
        Super Super1 = new Super("zolretzah");
        assertEquals(14,Super1.getEnd_morning(Days.Sunday),0);
        assertEquals(14,Super1.getEnd_morning(Days.Saturday),0);
        assertEquals(14,Super1.getEnd_morning(Days.Wednesday),0);
    }
    @Test
    public void setEnd_morning() {
        Super Super1 = new Super("zolretzah");
        assertEquals(14,Super1.getEnd_morning(Days.Sunday),0);
        Super1.setEnd_morning(Days.Sunday,10.00);
        assertEquals(10,Super1.getEnd_morning(Days.Sunday),0);
    }
    @Test
    public void getStart_evening() {
        Super Super1 = new Super("zolretzah");
        assertEquals(14,Super1.getStart_evening(Days.Sunday),0);
        assertEquals(14,Super1.getStart_evening(Days.Saturday),0);
        assertEquals(14,Super1.getStart_evening(Days.Wednesday),0);
    }
    @Test
    public void setStart_evening() {
        Super Super1 = new Super("zolretzah");
        assertEquals(14,Super1.getStart_evening(Days.Sunday),0);
        Super1.setStart_evening(Days.Sunday,10.00);
        assertEquals(10,Super1.getStart_evening(Days.Sunday),0);
    }
    @Test
    public void getStart_morning() {
        Super Super1 = new Super("zolretzah");
        assertEquals(7,Super1.getStart_morning(Days.Sunday),0);
        assertEquals(7,Super1.getStart_morning(Days.Saturday),0);
        assertEquals(7,Super1.getStart_morning(Days.Wednesday),0);
    }
    @Test
    public void setStart_morning() {
        Super Super1 = new Super("zolretzah");
        assertEquals(7,Super1.getStart_morning(Days.Sunday),0);
        Super1.setStart_morning(Days.Sunday,10.00);
        assertEquals(10,Super1.getStart_morning(Days.Sunday),0);
    }

}