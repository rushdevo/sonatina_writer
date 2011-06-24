package com.rushdevo.sonatina.lib.data;

import static android.provider.BaseColumns._ID;
import static com.rushdevo.sonatina.lib.Constants.DATABASE_NAME;
import static com.rushdevo.sonatina.lib.Constants.DATABASE_VERSION;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author jasonrush
 * 
 * Describes a staff of music
 * 
 */
public class Part extends DataObject {
	// Properties
	private Integer position;
	private String name;
	private Score score;
	private List<Measure> measures;
	
	private Context ctx;
	
	// Table and column info
	public static final String TABLE_NAME = "parts"; 
	public static final String NAME = "name";
	public static final String POSITION = "position";
	public static final String SCORE_ID = "score_id";
	public static final String[] COLUMNS = { _ID, NAME, POSITION, SCORE_ID };
	
	public Part(Context ctx, Integer position) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		this.position = position;
		this.ctx = ctx;
	}
	
	// GETTERS AND SETTERS
	public Integer getPosition() {
		return this.position;
	}
	
	public void setPosition(Integer position) {
		this.position = position;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}

	public List<Measure> getMeasures() {
		return measures;
	}

	public Score getScore() {
		return score;
	}

	public String getName() {
		return name;
	}
	
	public Context getCtx() {
		return ctx;
	}

	// ASSOCIATIONS
	/**
	 * @param score - The score whos parts we want
	 * @return list of parts. If score is a new record (not saved to db yet)
	 * this will be a one-item list with a default record. Otherwise, it will
	 * be the parts associated with the score in the database
	 */
	public static List<Part> forScoreOrDefault(Context ctx, Score score) {
		if (score.isNewRecord()) {
			List<Part> parts = new ArrayList<Part>();
			parts.add(Part.defaultPart(ctx));
			return parts;
		} else {
			return Part.forScore(score);
		}
	}
	
	// SQLLiteOpenHelper methods
	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE " + TABLE_NAME + " (");
		sql.append(_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
		sql.append(NAME + " TEXT, ");
		sql.append(POSITION + " INTEGER");
		sql.append(SCORE_ID + " INTEGER");
		sql.append(");");
		db.execSQL(sql.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO This is just stubbed out for now, need to implement a version-by-version upgrade
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	// PRIVATE METHODS
	/**
	 * 
	 * @param score: The score who's parts you want
	 * @return a list of parts that belong to the score
	 */
	private static List<Part> forScore(Score score) {
		// TODO
		return null;
	}
	
	/**
	 * 
	 * @return part: A default part
	 */
	private static Part defaultPart(Context ctx) {
		return new Part(ctx, 0);
	}
}
