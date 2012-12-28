package com.hillert.apptools.hibernate;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.TypeResolver;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

public class GenericEnumUserType implements UserType, ParameterizedType {

	private Class<? extends Enum> enumClass;
	private Class<?> identifierType;
	private Method identifierMethod;
	private Method valueOfMethod;
	private static final String defaultIdentifierMethodName = "name";
	private static final String defaultValueOfMethodName = "valueOf";
	private AbstractSingleColumnStandardBasicType type;
	private int[] sqlTypes;

	@Override
	public void setParameterValues(Properties parameters) {
		String enumClassName = parameters.getProperty("enumClass");
		try {
			enumClass = Class.forName(enumClassName).asSubclass(Enum.class);
		} catch (ClassNotFoundException exception) {
			throw new HibernateException("Enum class not found", exception);
		}

		String identifierMethodName = parameters.getProperty(
				"identifierMethod", defaultIdentifierMethodName);

		try {
			identifierMethod = enumClass.getMethod(identifierMethodName,
					new Class[0]);
			identifierType = identifierMethod.getReturnType();
		} catch (Exception exception) {
			throw new HibernateException("Failed to optain identifier method",
					exception);
		}

		TypeResolver tr = new TypeResolver();
		type = (AbstractSingleColumnStandardBasicType) tr.basic(identifierType
				.getName());
		if (type == null) {
			throw new HibernateException("Unsupported identifier type "
					+ identifierType.getName());
		}
		sqlTypes = new int[] { type.sqlType() };

		String valueOfMethodName = parameters.getProperty("valueOfMethod",
				defaultValueOfMethodName);

		try {
			valueOfMethod = enumClass.getMethod(valueOfMethodName,
					new Class[] { identifierType });
		} catch (Exception exception) {
			throw new HibernateException("Failed to optain valueOf method",
					exception);
		}
	}

	@Override
	public Class returnedClass() {
		return enumClass;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor sessionImplementor, Object owner) throws HibernateException,
			SQLException {

		Object identifier = type.get(rs, names[0], sessionImplementor);

		if (identifier == null) {
			return null;
		}

		try {
			return valueOfMethod.invoke(enumClass, new Object[] { identifier });
		} catch (Exception exception) {
			throw new HibernateException(
					"Exception while invoking valueOfMethod of enumeration class: ",
					exception);
		}
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor arg3) throws HibernateException, SQLException {
		try {
			Object identifier = value != null ? identifierMethod.invoke(value,
					new Object[0]) : null;
			st.setObject(index, identifier);
		} catch (Exception exception) {
			throw new HibernateException(
					"Exception while invoking identifierMethod of enumeration class: ",
					exception);

		}
	}

	@Override
	public int[] sqlTypes() {
		return sqlTypes;
	}

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean isMutable() {
		return false;
	}

	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

}
