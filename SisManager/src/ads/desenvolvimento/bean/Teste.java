/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads.desenvolvimento.bean;

import ads.desenvolvimento.dao.ProdutoDAO;
import ads.desenvolvimento.bean.util.PersistenceUtil;

/**
 *
 * @author sergio
 */
public class Teste {
    public static void main(String[] args) {
        Produto p = new Produto();
        p.setDescricao("Mouse");
        ProdutoDAO pd = new ProdutoDAO(ProdutoDAO.fabricarConexao("myPU"));
        pd.cadastrar(p);
        
        /*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        emf.close();*/
    }
}
