package com.rushdevo.sonatina.lib.data;

import static android.provider.BaseColumns._ID;
import static com.rushdevo.sonatina.lib.Constants.DATABASE_NAME;
import static com.rushdevo.sonatina.lib.Constants.DATABASE_VERSION;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author jasonrush
 * Describes a measure of music for a part
 */
public class Measure extends DataObject {
	// Properties
	private Integer position;
	
	private Context ctx;
	
	// Table and Columns
	public static final String TABLE_NAME = "measures"; 
	public static final String POSITION = "position";
	public static final String PART_ID = "part_id";
	public static final String[] COLUMNS = { _ID, POSITION, PART_ID };
	
	public Measure(Context ctx, Integer position) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		this.position = position;
		this.ctx = ctx;
	}
	
	// GETTERS AND SETTERS
	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getPosition() {
		return position;
	}
	
	public Context getCtx() {
		return ctx;
	}

	// SQLiteOpenHelper overrides
	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE " + TABLE_NAME + " (");
		sql.append(_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append(POSITION + " INTEGER");
		sql.append(PART_ID + " INTEGER");
		sql.append(");");
		db.execSQL(sql.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO This is just stubbed out for now, need to implement a version-by-version upgrade
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}
