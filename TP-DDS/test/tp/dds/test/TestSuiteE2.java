package tp.dds.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tp.dds.test.e2.Entrega2Test1;
import tp.dds.test.e2.Entrega2Test2;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   Entrega2Test1.class
  ,Entrega2Test2.class
})

public class TestSuiteE2 {

}