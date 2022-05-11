package com.example.hcpmtrong.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Builder<T> {

	public final T instance;
	private final Class<?> clazz;

	public Builder(Class<T> clazz) throws InstantiationException,
		IllegalAccessException {
		super();
		this.clazz = clazz;
		this.instance = clazz.newInstance();
	}

	void setProperty(String name, Object value)
		throws IllegalAccessException, IllegalArgumentException,
		InvocationTargetException, NoSuchMethodException,
		SecurityException {

		Method method = clazz.getMethod(name, value.getClass());
		method.invoke(instance, value);
	}

	T create() {
		return instance;
	}
}
