package co.com.gsdd.test.main;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.com.gsdd.test.bo.FactoryBO;
import co.com.gsdd.test.bo.UsuarioBO;
import co.com.gsdd.test.constants.GeneralConstants;
import co.com.gsdd.test.constants.NumericConstants;
import co.com.gsdd.test.entities.Usuario;

/**
 * Check the basic functions.
 * 
 * @author Great System Development Dynamic [GSDD] <br>
 *         Alexander Galvis Grisales <br>
 *         alex.galvis.sistemas@gmail.com <br>
 *
 */
public class TestCrud {

    public static final String LOG_EMPTY_LIST = "Empty list";
    private static final Logger LOGGER =
            LoggerFactory.getLogger(TestCrud.class);

    public void validateCrud() {
        final int eight = NumericConstants.EIGHT;
        final int bigValue = 999999;
        Usuario u = new Usuario();
        u.setNombres(randomString(eight));
        u.setApellidos(randomString(eight));
        u.setLogin(randomString(eight));
        u.setContrasena(randomString(eight));
        u.setNumeroIdentificacion(stringIdentificacion(bigValue));
        UsuarioBO uBO = FactoryBO.getBean(UsuarioBO.class, UsuarioBO.NOMBRE_BO);
        // Create
        uBO.createUsuario(u);
        // List
        listarUsuario(uBO);
        // Update
        u.setNumeroIdentificacion(stringIdentificacion(bigValue));
        uBO.updateUsuario(u);
        // List
        listarUsuario(uBO);
        // Delete
        //uBO.deleteUsuario(u);
        // List
        listarUsuario(uBO);
    }

    /**
     * Permite listar a los usuarios a peticion.
     * 
     * @param bo
     *            el BO empleado.
     */
    public void listarUsuario(UsuarioBO bo) {
        List<Usuario> lu = bo.getUsuarioList();
        if (lu != null && !lu.isEmpty()) {
            for (Usuario r : lu) {
                LOGGER.info(new StringBuilder(GeneralConstants.OPEN_BRACKET)
                        .append(r.toString())
                        .append(GeneralConstants.CLOSE_BRACKET).toString());
            }
        } else {
            LOGGER.info(LOG_EMPTY_LIST);
        }
    }

    public String randomString(Integer longitud) {
        String rnd = UUID.randomUUID().toString();
        if (rnd != null && rnd.length() >= longitud) {
            return rnd.substring(0, longitud);
        } else {
            return rnd;
        }
    }

    /**
     * Random generation for identificacion.
     * 
     * @param max
     *            top value.
     * @return random identificacion value.
     */
    public String stringIdentificacion(Integer max) {
        int random = (new Random().nextInt() * max) + NumericConstants.ONE;
        return String.valueOf(random);
    }
}
