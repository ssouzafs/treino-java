package ads.desenvolvimento.bean.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sergio
 */
public class PersistenceUtil {

    private static final EntityManagerFactory FACTORY
            = Persistence.createEntityManagerFactory("sisManagerBD");

    public static EntityManager getEntityManager() {

        return FACTORY.createEntityManager();
    }
}
