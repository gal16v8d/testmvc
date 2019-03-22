package co.com.gsdd.test.main;

import org.slf4j.LoggerFactory;

import co.com.gsdd.test.bo.FactoryBO;

/**
 * Desktop execution of the program.
 * 
 * @author Great System Development Dynamic [GSDD] <br>
 *         Alexander Galvis Grisales <br>
 *         alex.galvis.sistemas@gmail.com <br>
 *
 */
public class TestMain {

	private static final String INIT_LOG = "Init...";

	public static void main(String[] args) {
		try {
			LoggerFactory.getLogger(TestMain.class).info(INIT_LOG);
			new TestMain().init();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void init() throws Exception {
		FactoryBO.initContext();
		// Para testear funcionalidades con BD
		TestCrud tc = new TestCrud();
		tc.validateCrud();
	}

}
