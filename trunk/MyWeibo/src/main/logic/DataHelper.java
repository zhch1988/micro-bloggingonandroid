package main.logic;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import main.myweibo.OAuth;

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import weibo.constant.Constant;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class DataHelper {
	// 数据库名称
	private static String DB_NAME = "user_login_data.db";
	// 数据库版本
	private static int DB_VERSION = 1;
	private SQLiteDatabase db;
	private SqliteHelper dbHelper;
	
	

	public DataHelper(Context context) {
		dbHelper = new SqliteHelper(context, DB_NAME, null, DB_VERSION);
		db = dbHelper.getWritableDatabase();
	}

	public void Close() {
		db.close();
		dbHelper.close();
	}

	// 获取users表中的UserID、Access Token、Access Secret的记录
	public List<UserInfo> GetUserList() {
		List<UserInfo> userList = new ArrayList<UserInfo>();
		Cursor cursor = db.query(SqliteHelper.TB_NAME, null, null, null, null,
				null, UserInfo.ID + " DESC");
		cursor.moveToFirst();
		while (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
			UserInfo user = new UserInfo();
			user.setId(cursor.getString(0));
			user.setUserId(cursor.getString(1));
			//新constant
			Constant.user_id = cursor.getString(1);
			Constant.userKey =cursor.getString(2);
			Constant.userSecret =cursor.getString(3);
			//....................................
			user.setToken(cursor.getString(2));
			user.setTokenSecret(cursor.getString(3));
			if (cursor.getBlob(5)!=null) {		
				user.setUserName(cursor.getString(4));
				ByteArrayInputStream stream = new ByteArrayInputStream(
						cursor.getBlob(5));
				Drawable icon = Drawable.createFromStream(stream, "image");
				user.setUserIcon(icon);
			}
			userList.add(user);
			Log.v("userList.add(user", "OK");
			cursor.moveToNext();
		}
		cursor.close();

		return userList;
	}

	// 判断users表中的是否包含某个UserID的记录
	public Boolean HaveUserInfo(String UserId) {
		Boolean b = false;
		Cursor cursor = db.query(SqliteHelper.TB_NAME, null, UserInfo.USERID
				+ "=" + UserId, null, null, null, null);
		b = cursor.moveToFirst();
		Log.e("HaveUserInfo", b.toString());
		cursor.close();
		return b;
	}

	// 更新users表的记录，根据UserId更新用户昵称和用户图标
	public int UpdateUserInfo(String userName, Bitmap userIcon, String UserId) {
		ContentValues values = new ContentValues();
		values.put(UserInfo.USERNAME, userName);
		// BLOB类型
		final ByteArrayOutputStream os = new ByteArrayOutputStream();
		// 将Bitmap压缩成PNG编码，质量为100%存储
		userIcon.compress(Bitmap.CompressFormat.PNG, 100, os);
		// 构造SQLite的Content对象，这里也可以使用raw
		values.put(UserInfo.USERICON, os.toByteArray());
		int id = db.update(SqliteHelper.TB_NAME, values, UserInfo.USERID + "="
				+ UserId, null);
		Log.e("UpdateUserInfo2", id + "");
		return id;
	}

	// 用api获取头像
	public void UpdateUserInfo(Context context, List<UserInfo> userList) {
		DataHelper dbHelper = new DataHelper(context);
		OAuth auth = new OAuth();
		String url = "http://api.t.sina.com.cn/users/show.json";
		Log.e("userCount", userList.size() + "");
		for (UserInfo user : userList) {
			if (user != null) {
				List params = new ArrayList();
				params.add(new BasicNameValuePair("source", auth.consumerKey));
				params.add(new BasicNameValuePair("user_id", user.getUserId()));
				HttpResponse response = auth.SignRequest(user.getToken(),
						user.getTokenSecret(), url, params);
				if (200 == response.getStatusLine().getStatusCode()) {
					try {
						Log.v("getStatusLine", "OK");
						InputStream is = response.getEntity().getContent();
						Reader reader = new BufferedReader(
								new InputStreamReader(is), 4000);
						StringBuilder buffer = new StringBuilder((int) response
								.getEntity().getContentLength());
						try {
							char[] tmp = new char[1024];
							int l;
							while ((l = reader.read(tmp)) != -1) {
								buffer.append(tmp, 0, l);
							}
						} finally {
							reader.close();
						}
						String string = buffer.toString();
						response.getEntity().consumeContent();
						JSONObject data = new JSONObject(string);
						String ImgPath = data.getString("profile_image_url");
						Log.v("ImgPath", ImgPath);
						Bitmap userIcon = returnBitMap(ImgPath);

						String userName = data.getString("screen_name");
						dbHelper.UpdateUserInfo(userName, userIcon,
								user.getUserId());
						Log.v("ImgPath after", ImgPath);

					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		}
		dbHelper.Close();
	}

	public static Bitmap returnBitMap(String url) {
		Log.v("returnBitMap", "url=" + url);
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

	// 更新users表的记录
	public int UpdateUserInfo(UserInfo user) {
		ContentValues values = new ContentValues();
		values.put(UserInfo.USERID, user.getUserId());
		values.put(UserInfo.TOKEN, user.getToken());
		values.put(UserInfo.TOKENSECRET, user.getTokenSecret());
		int id = db.update(SqliteHelper.TB_NAME, values, UserInfo.USERID + "="
				+ user.getUserId(), null);
		Log.e("UpdateUserInfo", id + "");
		return id;
	}

	// 添加users表的记录
	public Long SaveUserInfo(UserInfo user) {
		ContentValues values = new ContentValues();
		values.put(UserInfo.USERID, user.getUserId());
		values.put(UserInfo.TOKEN, user.getToken());
		values.put(UserInfo.TOKENSECRET, user.getTokenSecret());
		Long uid = db.insert(SqliteHelper.TB_NAME, UserInfo.ID, values);
		Log.e("SaveUserInfo", uid + "");
		return uid;
	}

	// 删除users表的记录
	public int DelUserInfo(String UserId) {
		int id = db.delete(SqliteHelper.TB_NAME,
				UserInfo.USERID + "=" + UserId, null);
		Log.e("DelUserInfo", id + "");
		return id;
	}
}
