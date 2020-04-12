package co.com.gsdd.test.main;

import co.com.gsdd.test.bo.FactoryBO;
import lombok.extern.slf4j.Slf4j;

/**
 * Desktop execution of the program.
 * 
 * @author Great System Development Dynamic [GSDD] <br>
 *         Alexander Galvis Grisales <br>
 *         alex.galvis.sistemas@gmail.com <br>
 *
 */
@Slf4j
public class TestMain {

	private static final String INIT_LOG = "Init...";

	public static void main(String[] args) {
		try {
			log.info("{}", INIT_LOG);
			new TestMain().init();
		} catch (Exception e) {
			log.error("Error", e);
			System.exit(1);
		}
	}

	public void init() throws Exception {
		FactoryBO.initContext();
		TestCrud tc = new TestCrud();
		tc.validateCrud();
	}

}
