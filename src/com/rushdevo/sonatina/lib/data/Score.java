package com.rushdevo.sonatina.lib.data;

import static android.provider.BaseColumns._ID;
import static com.rushdevo.sonatina.lib.Constants.DATABASE_NAME;
import static com.rushdevo.sonatina.lib.Constants.DATABASE_VERSION;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * @author jasonrush
 * 
 * Describes a score
 * 
 */
public class Score extends DataObject {
	// Properties
	private String workTitle;
	private String creator;
	private List<Part> parts;
	
	// Table and column info
	public static final String TABLE_NAME = "scores"; 
	public static final String WORK_TITLE = "work_title";
	public static final String CREATOR = "creator";
	public static final String[] COLUMNS = { _ID, WORK_TITLE, CREATOR };

	/**
	 * Score constructor for a new score
	 * The workTitle defaults to 'Untitled'
	 * 
	 * @param ctx - The Context initializing this score
	 * @throws RecordNotFoundException
	 */
	public Score(Context ctx) throws RecordNotFoundException {
		this(null, ctx);
	}
	
	/**
	 * Score constructor
	 * 
	 * @param id - the database id of the 
	 * @param ctx - The Context initializing this score
	 * @throws RecordNotFoundException
	 */
	public Score(Integer id, Context ctx) throws RecordNotFoundException {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		setId(id);
		loadOrDefault();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE " + TABLE_NAME + " (");
		sql.append(_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append(WORK_TITLE + " TEXT, ");
		sql.append(CREATOR + " TEXT");
		sql.append(");");
		db.execSQL(sql.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO This is just stubbed out for now, need to implement a version-by-version upgrade
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	//////// GETTERS AND SETTERS ////////
	
	public List<Part> getParts() {
		if (this.parts == null) {
			if (isNewRecord()) {
				this.parts = new ArrayList<Part>();
				this.parts.add(Part.defaultPart());
			} else {
				this.parts = Part.forScore(this);
			}
		}
		return this.parts;
	}
	
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}

	public String getWorkTitle() {
		return workTitle;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreator() {
		return creator;
	}
	
	/**
	 * @return true if this is a new record (the _ID field is not set)
	 */
	public Boolean isNewRecord() {
		return getId() == null;
	}
	
	////////// PRIVATE ///////////
	private void loadOrDefault() throws RecordNotFoundException {
		if (this.getId() == null) setupDefaultScore();
		else loadScore(getId());
	}
	
	private void setupDefaultScore() {
		setWorkTitle("Untitled");
	}
	
	private void loadScore(Integer id) throws RecordNotFoundException {
		SQLiteDatabase db = getReadableDatabase();
		String selection = _ID + " = ?";
		String[] selectionArgs = { id.toString() };
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, selection, selectionArgs, null, null, null, "1");
		if (cursor.moveToNext()) {
			this.setId(idFromCursor(cursor));
			this.setWorkTitle(workTitleFromCursor(cursor));
			this.setCreator(creatorFromCursor(cursor));
		} else {
			throw new RecordNotFoundException();
		}
	}
	
	////// CURSOR HELPERS /////////
	
	private Integer idFromCursor(Cursor cursor) {
		return cursor.getInt(0);
	}
	
	private String workTitleFromCursor(Cursor cursor) {
		return cursor.getString(1);
	}
	
	private String creatorFromCursor(Cursor cursor) {
		return cursor.getString(2);
	}
}
