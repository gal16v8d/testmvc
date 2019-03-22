package co.com.gsdd.test.constants;

import lombok.Getter;
import lombok.Setter;

/**
 * Util for create DB conection, check spring-customer file.
 * @author Great System Development Dynamic [GSDD] <br>
 *         Alexander Galvis Grisales <br>
 *         alex.galvis.sistemas@gmail.com <br>
 *
 */
@Getter
@Setter
public class DBConnection {

	public static final String DRIVER_CLASS = "org.postgresql.Driver";
	public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/test";
	public static final String USER = "postgres";
	public static final String PASS = "postgres";

	private String driverClass;
	private String jdbcUrl;
	private String user;
	private String password;
	
	public DBConnection() {
		super();
		driverClass = DRIVER_CLASS;
		jdbcUrl = JDBC_URL;
		user = USER;
		password = PASS;
	}
}
