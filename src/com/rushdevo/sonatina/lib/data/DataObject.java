package com.rushdevo.sonatina.lib.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author jasonrush
 *
 */
public abstract class DataObject extends SQLiteOpenHelper {
	private Integer id;
	
	public DataObject(Context ctx, String name, CursorFactory factory, int databaseVersion) {
		super(ctx, name, factory, databaseVersion);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
