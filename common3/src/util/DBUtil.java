/*
 * DBUtil.java
 *
 * Created on Sep 8, 2007, 5:30:13 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.swing.JLabel;

import service.util.IBean;
import springbean.AAAConfig;
import constants.Constants;

/**
 *
 * @author Budoy Entokwa
 */
public class DBUtil {
    static boolean alreadySetup;
    static int maxRecordSize = 500;
    private static Map<String, String> mapKey = new HashMap();
    static EntityManagerFactory factory;

    public static EntityManagerFactory getFactory() {
    	if (factory==null) {
    		factory = javax.persistence.Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT, mapKey);
    	}
    	return factory;
    }
    
    public static void setupDerby() {
        mapKey.put("toplink.jdbc.user","");
        mapKey.put("toplink.jdbc.password","");
        mapKey.put("toplink.jdbc.url","jdbc:derby:derbyDB;create=true");
        mapKey.put("toplink.jdbc.driver","org.apache.derby.jdbc.EmbeddedDriver");
        mapKey.put("toplink.jdbc.write-connections.max","1");
    }

    public static DBUtil getInstance() {
        if (!alreadySetup) {
            alreadySetup = true;
            DBUtil util = new DBUtil();
            mapKey.put("toplink.ddl-generation","create-tables");
            long i = (Long) util.selectSingleColumn("SELECT COUNT(*) FROM AppMenu", null);
            if (i==0) {
                AAAConfig.getInstance().setupDB(new JLabel(""));
            }
            mapKey.put("toplink.ddl-generation","NONE");
        }
        return new DBUtil();
    }

    public IBean insertBean(IBean bean, javax.persistence.EntityManager entityManager) {
        IBean newBean = BeanUtil.getTrueIBean(bean);
        if (entityManager == null) {
            entityManager = getFactory().createEntityManager();
            newBean.updateMe(entityManager);
        } else {
            newBean.updateMe(entityManager);
        }
        BeanUtil.copyBean(newBean, bean);
        return bean;
    }

    @SuppressWarnings(value = "unchecked")
    public List selectBean(IBean bean, String helperSQL, int selectType, javax.persistence.EntityManager entityManager) {
        List lst = null;
        if (entityManager == null) {
            entityManager = getFactory().createEntityManager();
            Query query = null;
            if (selectType == Constants.SELECT_NATIVE) {
                query = entityManager.createNativeQuery(helperSQL);
            } else {
                query = entityManager.createQuery(helperSQL);
            }
            query.setMaxResults(maxRecordSize);
            lst = query.getResultList();
            entityManager.close();
        } else {
            Query query = null;
            if (selectType == Constants.SELECT_NATIVE) {
                query = entityManager.createNativeQuery(helperSQL);
            } else {
                query = entityManager.createQuery(helperSQL);
            }
            query.setMaxResults(maxRecordSize);
            lst = query.getResultList();
        }
        if (lst==null) return new ArrayList();
        return lst;
    }

    public List selectBean(String sql, javax.persistence.EntityManager entityManager) {
        List lst = null;
        if (entityManager == null) {
            entityManager = getFactory().createEntityManager();
            Query query = entityManager.createQuery(sql);
            query.setMaxResults(maxRecordSize);
            lst = query.getResultList();
            entityManager.close();
        } else {
            Query query = entityManager.createQuery(sql);
            query.setMaxResults(maxRecordSize);
            lst = query.getResultList();
        }
        if (lst==null) return new ArrayList();
        return lst;
    }

    public List selectBean(String sql, javax.persistence.EntityManager entityManager, int start, int recSize) {
        List lst = null;
        if (entityManager == null) {
            entityManager = getFactory().createEntityManager();
            Query query = entityManager.createQuery(sql);
            query.setMaxResults(recSize);
            query.setFirstResult(start);
            lst = query.getResultList();
            entityManager.close();
        } else {
            Query query = entityManager.createQuery(sql);
            query.setMaxResults(recSize);
            query.setFirstResult(start);
            lst = query.getResultList();
        }
        if (lst==null) return new ArrayList();
        return lst;
    }
    
    public List selectBeanNative(String sql, javax.persistence.EntityManager entityManager) {
        List lst = null;
        if (entityManager == null) {
            entityManager = getFactory().createEntityManager();
            Query query = entityManager.createNativeQuery(sql);
            query.setMaxResults(maxRecordSize);
            lst = query.getResultList();
            entityManager.close();
        } else {
            Query query = entityManager.createNativeQuery(sql);
            query.setMaxResults(maxRecordSize);
            lst = query.getResultList();
        }
        if (lst==null) return new ArrayList();
        return lst;
    }

    public Object getSingleBean(String sql, javax.persistence.EntityManager entityManager) {
        Object obj = null;
        if (entityManager == null) {
            entityManager = getFactory().createEntityManager();
            Query query = entityManager.createQuery(sql);
            query.setMaxResults(1);
            List lst = query.getResultList();
            if (lst != null && lst.size() > 0) {
                obj = lst.get(0);
            }
//            obj = query.getSingleResult();
            entityManager.close();
        } else {
            Query query = entityManager.createQuery(sql);
//            obj = query.getSingleResult();
            query.setMaxResults(1);
            List lst = query.getResultList();
            if (lst != null && lst.size() > 0) {
                obj = lst.get(0);
            }
        }
        return obj;
    }

    public void executeSQL(String sql, javax.persistence.EntityManager entityManager) {
    	try {
            if (entityManager == null) {
                entityManager = getFactory().createEntityManager();
                entityManager.getTransaction().begin();
                Query query = entityManager.createQuery(sql);
                query.executeUpdate();
                entityManager.getTransaction().commit();
                entityManager.close();
            } else {
                Query query = entityManager.createQuery(sql);
                query.executeUpdate();
            }
    	} catch(Exception e) {
    		cleanFailedConnection(entityManager, e);
    	}
    }

    public void executeSQLNative(String sql, javax.persistence.EntityManager entityManager) {
    	try {
            if (entityManager == null) {
                entityManager = getFactory().createEntityManager();
                entityManager.getTransaction().begin();
                Query query = entityManager.createNativeQuery(sql);
                query.executeUpdate();
                entityManager.getTransaction().commit();
                entityManager.close();
            } else {
                Query query = entityManager.createNativeQuery(sql);
                query.executeUpdate();
            }
    	}
    	catch (Exception e) {
    		cleanFailedConnection(entityManager, e);
    	}
    }

    public Object selectSingleColumn(String sql, javax.persistence.EntityManager entityManager) {
        Object obj = null;
        if (entityManager == null) {
            entityManager = getFactory().createEntityManager();
            Query query = entityManager.createNativeQuery(sql);
            query.setMaxResults(1);
            obj = query.getSingleResult();
            entityManager.close();
        } else {
            Query query = entityManager.createNativeQuery(sql);
            query.setMaxResults(1);
            obj = query.getSingleResult();
        }
        if (obj instanceof Vector) {
            Vector vec = (Vector) obj;
            if (vec != null && vec.size() > 0) {
                return vec.get(0);
            }
        }
        return obj;
    }

    public IBean updateBean(IBean bean, javax.persistence.EntityManager entityManager) {
        return insertBean(bean, entityManager);
    }

    public void deleteBean(IBean bean, javax.persistence.EntityManager entityManager) {
    	try {
            IBean newBean = BeanUtil.getTrueIBean(bean);
            if (entityManager == null) {
                entityManager = getFactory().createEntityManager();
                IBean tmp = newBean.getMe(entityManager);
                entityManager.getTransaction().begin();
                entityManager.remove(tmp);
                entityManager.getTransaction().commit();
                entityManager.close();
            } else {
                IBean tmp = newBean.getMe(entityManager);
                entityManager.getTransaction().begin();
                entityManager.remove(tmp);
                entityManager.getTransaction().commit();
                entityManager.close();
            }
    	}
    	catch (Exception e) {
    		cleanFailedConnection(entityManager, e);
    	}
    }

    public static void initializeCollection(Collection coll) {
        if (coll == null) {
            return;
        }
        coll.size();
    }
    
    public static void cleanFailedConnection(javax.persistence.EntityManager entityManager, Exception e) {
		if (entityManager!=null) {
			EntityTransaction tran = entityManager.getTransaction();
			if (tran.isActive()) {
				tran.rollback();
			}
			entityManager.close();
		}
    }
}
