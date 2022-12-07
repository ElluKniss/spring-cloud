package com.dw;

import static org.junit.Assert.assertTrue;

import com.dw.service.CommonUse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(value = {"com.dw.App"})
public class AppTest 
{
    @Resource
    private CommonUse commonUse;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testAop(){
        System.out.println(commonUse);
        commonUse.action();
    }
}
