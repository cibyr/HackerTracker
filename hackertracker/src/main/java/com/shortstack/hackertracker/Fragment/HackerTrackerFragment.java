package com.shortstack.hackertracker.Fragment;

/**
 * Created by Whitney Champion on 4/18/14.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.shortstack.hackertracker.Activity.HomeActivity;
import com.shortstack.hackertracker.Adapter.DatabaseAdapter;
import com.shortstack.hackertracker.Adapter.DatabaseAdapterVendors;
import com.shortstack.hackertracker.Application.HackerTrackerApplication;
import com.shortstack.hackertracker.Common.Constants;
import com.shortstack.hackertracker.Model.Contest;
import com.shortstack.hackertracker.Model.Default;
import com.shortstack.hackertracker.Model.Event;
import com.shortstack.hackertracker.Model.Party;
import com.shortstack.hackertracker.Model.Speaker;
import com.shortstack.hackertracker.Model.Vendor;

import java.util.ArrayList;
import java.util.List;

public class HackerTrackerFragment extends Fragment {

    private FragmentManager fragmentManager;
    private Context context;
    private DatabaseAdapter dbHelper;
    private DatabaseAdapterVendors vendorDbHelper;

    public HackerTrackerFragment() {

        // get fragment manager
        fragmentManager = HomeActivity.fragmentManager;

        // get context
        context = HackerTrackerApplication.getAppContext();

        // database
        dbHelper = HackerTrackerApplication.dbHelper;
        vendorDbHelper = HackerTrackerApplication.vendorDbHelper;

    }

    public static String getTitle(Context ctxt, int position) {
        switch (position) {
            case -1:
                return Constants.DAY_0;
            case 0:
                return Constants.DAY_1;
            case 1:
                return Constants.DAY_2;
            case 2:
                return Constants.DAY_3;
            case 3:
                return Constants.DAY_4;
            default:
                return "";
        }
    }

    public static String getDate(int date) {
        String day = "";
        switch (date) {
            case 0:
                day = Constants.LONG_DAY_0;
                break;
            case 1:
                day = Constants.LONG_DAY_1;
                break;
            case 2:
                day = Constants.LONG_DAY_2;
                break;
            case 3:
                day = Constants.LONG_DAY_3;
                break;
            case 4:
                day = Constants.LONG_DAY_4;
                break;
        }
        return day;
    }

    public List<Vendor> getVendors() {
        ArrayList<Vendor> result = new ArrayList<Vendor>();

        SQLiteDatabase db = HackerTrackerApplication.vendorDbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM data ORDER BY title", new String[] {});

        try{
            if (cursor.moveToFirst()){
                do{

                    Vendor item = new Vendor();

                    item.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    item.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                    item.setImage(cursor.getString(cursor.getColumnIndex("image")));
                    item.setLink(cursor.getString(cursor.getColumnIndex("link")));

                    result.add(item);
                }while(cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }
        db.close();

        return result;
    }

    public List<Default> getItemByDate(String day, String type) {
        ArrayList<Default> result = new ArrayList<Default>();

        SQLiteDatabase db = HackerTrackerApplication.dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM data WHERE date=? AND type=? ORDER BY begin", new String[] {day, String.valueOf(type)});

        try{
            if (cursor.moveToFirst()){
                do{

                    Default item;

                    switch (type) {
                        case Constants.TYPE_SPEAKER:
                            item = new Speaker();
                            break;
                        case Constants.TYPE_CONTEST:
                            item = new Contest();
                            break;
                        case Constants.TYPE_EVENT:
                            item = new Event();
                            break;
                        case Constants.TYPE_PARTY:
                            item = new Party();
                            break;
                        case Constants.TYPE_SKYTALKS:
                            item = new Default();
                            break;
                        case Constants.TYPE_MESSAGE:
                            item = new Default();
                            break;
                        case Constants.TYPE_UNOFFICIAL:
                            item = new Default();
                            break;
                        case Constants.TYPE_DCIB:
                            item = new Default();
                            break;
                        case Constants.TYPE_STUPID:
                            item = new Default();
                            break;
                        case Constants.TYPE_KIDS:
                            item = new Default();
                            break;
                        case Constants.TYPE_JOKE:
                            item = new Default();
                            break;
                        default:
                            item = new Speaker();
                            break;
                    }

                    item.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    item.setType(cursor.getString(cursor.getColumnIndex("type")));
                    item.setDate(cursor.getString(cursor.getColumnIndex("date")));
                    item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    item.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                    item.setName(cursor.getString(cursor.getColumnIndex("who")));
                    item.setEnd(cursor.getString(cursor.getColumnIndex("end")));
                    item.setBegin(cursor.getString(cursor.getColumnIndex("begin")));
                    item.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                    item.setStarred(cursor.getInt(cursor.getColumnIndex("starred")));
                    item.setImage(cursor.getString(cursor.getColumnIndex("image")));
                    item.setLink(cursor.getString(cursor.getColumnIndex("link")));
                    item.setIsNew(cursor.getInt(cursor.getColumnIndex("is_new")));
                    item.setDemo(cursor.getInt(cursor.getColumnIndex("demo")));
                    item.setTool(cursor.getInt(cursor.getColumnIndex("tool")));
                    item.setExploit(cursor.getInt(cursor.getColumnIndex("exploit")));

                    result.add(item);
                }while(cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }
        db.close();

        return result;
    }

    public List<Default> getStars(String day) {
        ArrayList<Default> result = new ArrayList<Default>();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM data WHERE date=? AND starred=1 ORDER BY begin", new String[]{day});

        // get items from database

        try{
            if (cursor.moveToFirst()){
                do{

                    Default item = new Default();

                    item.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    item.setType(cursor.getString(cursor.getColumnIndex("type")));
                    item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                    item.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                    item.setName(cursor.getString(cursor.getColumnIndex("who")));
                    item.setDate(cursor.getString(cursor.getColumnIndex("date")));
                    item.setEnd(cursor.getString(cursor.getColumnIndex("end")));
                    item.setBegin(cursor.getString(cursor.getColumnIndex("begin")));
                    item.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                    item.setStarred(cursor.getInt(cursor.getColumnIndex("starred")));
                    item.setImage(cursor.getString(cursor.getColumnIndex("image")));
                    item.setLink(cursor.getString(cursor.getColumnIndex("link")));
                    item.setIsNew(cursor.getInt(cursor.getColumnIndex("is_new")));
                    item.setDemo(cursor.getInt(cursor.getColumnIndex("demo")));
                    item.setTool(cursor.getInt(cursor.getColumnIndex("tool")));
                    item.setExploit(cursor.getInt(cursor.getColumnIndex("exploit")));

                    result.add(item);

                }while(cursor.moveToNext());
            }
        }finally{
            cursor.close();
        }

        db.close();

        // return all items

        return result;
    }
}
