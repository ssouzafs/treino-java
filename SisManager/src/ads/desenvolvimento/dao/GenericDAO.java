/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads.desenvolvimento.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author sergio
 */
public class GenericDAO</* PRIMARY KEY* */PK, T> {

    protected EntityManager em;

    public GenericDAO(EntityManager em) {
        this.em = em;
    }

    // CRIAR
    public void cadastrar(T entity) {
        try {

            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "CADASTRO REALIZADO!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR: " + e.getMessage());
        }

    }

    // RECUPERAR DO BANCO
    public T pesquisar(PK pk) {
        em.getTransaction().begin();
        T entity = (T) em.find(getTypeClass(), pk);
        em.getTransaction().commit();
        return entity;
    }

    // ATUALIZAR
    public void atualizar(T entity) {

        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "ALTERAÇÃO REALIZADA!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR REGISTRO:  " + e.getMessage());
        }

    }

    // DELETAR
    public void remover(PK pk) {

        try {
            em.getTransaction().begin();
            pk = (PK) em.find(getTypeClass(), pk);
            em.remove(pk);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "REMOÇÃO REALIZADA!!!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO AO REMOVER REGISTRO:  "
                    + e.getMessage());
        }

    }

    // LISTAR TODOS TIPOS
    public List<T> listarTodos() {

        em.getTransaction().begin();
        List<T> entities = em.createQuery("FROM " + getTypeClass().getName()).getResultList();
        em.getTransaction().commit();
        return entities;

    }

    // RETORNA QUAL A CLASSE DO TIPO GENÉRICO
    private Class<?> getTypeClass() {
        /**
         * A classe tem que ser com dois "zz" pq com "ss" é uma palavra
         * reservada *
         */
        Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().
                getGenericSuperclass()).getActualTypeArguments()[1];

        return clazz;
    }

    public static EntityManager fabricarConexao(String persistenceUnitName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public void resetTransition() {
        if (em.isOpen()) {
            em.close();
        }
    }
}
