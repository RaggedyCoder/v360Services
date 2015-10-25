package com.thevolume360.audit;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.thevolume360.dao.AuditLogDao;
import com.thevolume360.domain.AuditLog;
import com.thevolume360.domain.Auditable;
import com.thevolume360.domain.enums.AuditAction;
import com.thevolume360.web.ApplicationContextProvider;

@SuppressWarnings("rawtypes")
@Component
public class AuditLogInterceptor extends EmptyInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5520571385214427977L;

	private static final Logger log = LoggerFactory.getLogger(AuditLogInterceptor.class);

	private Set<Object> inserts = new HashSet<Object>();
	private Set<Object> updates = new HashSet<Object>();
	private Set<Object> deletes = new HashSet<Object>();

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		log.debug("onSave()");

		if (entity instanceof Auditable) {
			inserts.add(entity);
		}

		return false;
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		log.debug("onFlushDirty()");

		if (entity instanceof Auditable) {
			updates.add(entity);
		}

		return false;
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		log.debug("onDelete()");

		if (entity instanceof Auditable) {
			deletes.add(entity);
		}
	}

	@Override
	public void preFlush(Iterator entities) {
		log.debug("preFlush()");
	}

	@Override
	public void postFlush(Iterator entities) {
		log.debug("postFlush()");

		AuditLogDao auditLogDao = (AuditLogDao) ApplicationContextProvider.getApplicationContext()
				.getBean("auditLogDao");

		try {

			for (Iterator it = inserts.iterator(); it.hasNext();) {
				Auditable entity = (Auditable) it.next();

				logEvent(AuditAction.CREATED, entity, auditLogDao);
			}

			for (Iterator it = updates.iterator(); it.hasNext();) {
				Auditable entity = (Auditable) it.next();

				logEvent(AuditAction.UPDATED, entity, auditLogDao);
			}

			for (Iterator it = deletes.iterator(); it.hasNext();) {
				Auditable entity = (Auditable) it.next();

				logEvent(AuditAction.DELETED, entity, auditLogDao);
			}

		} finally {
			inserts.clear();
			updates.clear();
			deletes.clear();
		}
	}

	private void logEvent(AuditAction action, Auditable entity, AuditLogDao auditLogDao) {
		log.debug(" action ={}, entity.getId()={}, entity.getLogDetail()={}", action, entity.getId());

		AuditLog auditLog = new AuditLog();
		auditLog.setAction(action);
		auditLog.setDetail(make(entity));
		auditLog.setEntityName(entity.getClass().getName());
		auditLog.setEntityId(entity.getId());

		auditLogDao.save(auditLog);
	}

	private String make(Object entity) {
		try {
			return ApplicationContextProvider.getObjectWriter().writeValueAsString(entity);
		} catch (Exception e) {
			log.error("Can't serialize entity", e);
		}
		return null;
	}
}
