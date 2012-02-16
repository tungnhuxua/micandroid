package org.phox.where.controller;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;

public abstract class Storage<T> {

	private static HashMap<Class, Storage> storages = new HashMap<Class, Storage>();
	protected Context context;

	public Storage(Context context) {
		this.context = context;
	}

	public abstract void put(T item);

	public abstract void remove(T item);

	public abstract List<T> getAll();

	public abstract void clear();

	public static <S> Storage<S> getStorage(Class<? extends Storage<S>> clz,
			Context c) {
		if (!storages.containsKey(clz)) {
			Storage<S> str;
			try {
				str = clz.getConstructor(Context.class).newInstance(c);
			} catch (Exception e) {
				Log.e("Storage", "" + e.getMessage());
				e.printStackTrace();
				return null;
			}

			storages.put(clz, str);
			return str;
		}

		return storages.get(clz);
	}

}
