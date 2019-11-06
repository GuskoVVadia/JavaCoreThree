import TaskThree.EnumarationArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnumarationArrayTest {

    EnumarationArray ea;

    @Before
    public void init(){
        ea = new EnumarationArray();
    }

    @Test
    public void checkTestOne(){
        Assert.assertEquals(true, ea.checkFourAndOne(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void checkTestTwo(){
        Assert.assertEquals(false, ea.checkFourAndOne(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void checkTestThree(){
        Assert.assertNotEquals(true, ea.checkFourAndOne(new int[]{2}));
    }

    @Test
    public void checkTestFour(){
        Assert.assertEquals(false, ea.checkFourAndOne(new int[]{2}));
    }


}
