package com.example.tiago.criminalintent3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;


import com.example.tiago.criminalintent3.database.CrimeBaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;
    private Context mContext;
    private SQLiteDatabase mDataBase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }


    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
        mContext = context.getApplicationContext();
        mDataBase = new CrimeBaseHelper(mContext).getWritableDatabase();
        for (int i = 1; i <= 100; i++) {
            Crime crime = new Crime();
            crime.setMtitle("Crime #" + i);
            crime.setmSolved(i % 2 == 0);
            crime.setCallPolice(crime.isCallPoliceSolved());
            crime.setmDate(new Date());
            mCrimes.add(crime);

        }
    }


    public List<Crime> getCrimes() {
        return mCrimes;

    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getmID().equals(id)) {
                return crime;
            }
        }
        return null;
    }


}