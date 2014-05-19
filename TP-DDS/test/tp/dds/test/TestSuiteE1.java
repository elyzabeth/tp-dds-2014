package tp.dds.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tp.dds.test.e1.TPE1Prioridad1;
import tp.dds.test.e1.TPE1Prioridad2;
import tp.dds.test.e1.TPE1Prioridad3;
import tp.dds.test.e1.TPE1Prioridad4;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   TPE1Prioridad1.class,
   TPE1Prioridad2.class,
   TPE1Prioridad3.class,
   TPE1Prioridad4.class
   //TPE1Prioridad5.class
})

public class TestSuiteE1 {

}