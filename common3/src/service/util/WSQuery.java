/*
 * WSDummyQuery.java
 *
 * Created on Sep 8, 2007, 7:08:59 PM
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service.util;

import bean.admin.AclUser;
import constants.Constants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import service.ParamStruct;
import service.ReturnStruct;
import service.ServiceAdapter;
import util.DBClient;


/**
 *
 * @author Budoy Entokwa
 */
public class WSQuery implements Query {

    String sql;
    int selectType;
    Class beanType;

    public Class getBeanType() {
        return beanType;
    }

    public List getLst() {
        return lst;
    }

    public int getSelectType() {
        return selectType;
    }

    public String getSql() {
        return sql;
    }

    public WSQuery(int selectType) {
        this.selectType = selectType;
    }

    WSQuery(String arg0, int selectType) {
        this.selectType = selectType;
        sql = arg0;
    }

    public List getResultList() {
        return DBClient.getList(sql);
    }

    public Object getSingleResult() {
        ParamStruct param = new ParamStruct();
        param.setActionCommand(Constants.SELECT_SINGLE);
        param.setData(new AclUser());
        param.setHelperSQL(sql);
        param.setServiceName(Constants.PERSISTENCE_SERVICE);

        ReturnStruct ret = ServiceAdapter.getInstance().call(param);
        if (ret != null && ret.getStatus() == Constants.SUCCESS) {
            return ret.getData();
        } else {
            return new ArrayList();
        }
    }

    public int executeUpdate() {
        ParamStruct param = new ParamStruct();
        param.setActionCommand(Constants.EXECUTE_ONLY);
        param.setHelperSQL(sql);
        param.setServiceName(Constants.PERSISTENCE_SERVICE);

        ReturnStruct ret = ServiceAdapter.getInstance().call(param);
        return ret.getStatus();
    }

    public Query setMaxResults(int arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query setFirstResult(int arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query setHint(String arg0, Object arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query setParameter(String arg0, Object arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query setParameter(String arg0, Date arg1, TemporalType arg2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query setParameter(String arg0, Calendar arg1, TemporalType arg2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query setParameter(int arg0, Object arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query setParameter(int arg0, Date arg1, TemporalType arg2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query setParameter(int arg0, Calendar arg1, TemporalType arg2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Query setFlushMode(FlushModeType arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private List lst;

	public int getFirstResult() {
		// TODO Auto-generated method stub
		return 0;
	}

	public FlushModeType getFlushMode() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getHints() {
		// TODO Auto-generated method stub
		return null;
	}

	public LockModeType getLockMode() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMaxResults() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Parameter<?> getParameter(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Parameter<?> getParameter(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Parameter<T> getParameter(String arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Parameter<T> getParameter(int arg0, Class<T> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getParameterValue(Parameter<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getParameterValue(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getParameterValue(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Parameter<?>> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isBound(Parameter<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public Query setLockMode(LockModeType arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> Query setParameter(Parameter<T> arg0, T arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Query setParameter(Parameter<Calendar> arg0, Calendar arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Query setParameter(Parameter<Date> arg0, Date arg1, TemporalType arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T unwrap(Class<T> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
