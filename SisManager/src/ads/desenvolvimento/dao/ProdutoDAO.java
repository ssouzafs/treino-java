
package ads.desenvolvimento.dao;

import ads.desenvolvimento.bean.Produto;
import javax.persistence.EntityManager;

/**
 *
 * @author sergio
 */
public class ProdutoDAO  extends GenericDAO<Integer, Produto>{
    
    public ProdutoDAO(EntityManager em) {
        super(em);
    }
    
}
