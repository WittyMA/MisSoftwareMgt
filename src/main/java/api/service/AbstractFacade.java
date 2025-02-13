package api.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import java.util.List;

public abstract class AbstractFacade<T> {
  private final Class<T> entityClass;
  
  public AbstractFacade(Class<T> entityClass) {
    this.entityClass = entityClass;
  }
  
  protected abstract EntityManager getEntityManager();
  
  public void create(T entity) {
    getEntityManager().persist(entity);
  }
  
  public void edit(T entity) {
    getEntityManager().merge(entity);
  }
  
  public void remove(T entity) {
    getEntityManager().remove(getEntityManager().merge(entity));
  }
  
  public T find(Object id) {
    return (T)getEntityManager().find(this.entityClass, id);
  }
  
  public List<T> findAll() {
    CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(this.entityClass);
    cq.select((Selection)cq.from(this.entityClass));
    return getEntityManager().createQuery(cq).getResultList();
  }
  
  public List<T> findRange(int[] range) {
    CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(this.entityClass);
    cq.select((Selection)cq.from(this.entityClass));
    TypedQuery<T> q = getEntityManager().createQuery(cq);
    q.setMaxResults(range[1] - range[0] + 1);
    q.setFirstResult(range[0]);
    return q.getResultList();
  }
  
  public int count() {
    CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
    Root<T> rt = cq.from(this.entityClass);
    cq.select((Selection)getEntityManager().getCriteriaBuilder().count((Expression)rt));
    return (getEntityManager().createQuery(cq).getSingleResult()).intValue();
  }
}
