package tp.dds.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tp.dds.test.e2.Entrega2Test1;
import tp.dds.test.e2.Entrega2Test2;
import tp.dds.test.e2.Entrega2Test3;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   Entrega2Test1.class
  ,Entrega2Test2.class
  ,Entrega2Test3.class
})

public class TestSuiteE2 {

}