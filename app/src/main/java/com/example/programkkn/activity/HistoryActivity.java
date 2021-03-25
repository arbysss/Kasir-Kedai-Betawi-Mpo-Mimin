package com.example.programkkn.activity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.programkkn.R;
import com.example.programkkn.adapter.HistoryAdapter;
import com.example.programkkn.database.DatabaseHelper;
import com.example.programkkn.model.HistoryModel;
import com.example.programkkn.session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    SessionManager session;
    String id_book = "", menu1,menu2,menu3,menu4,menu5,menu6, tanggal, jumlah1, jumlah2, jumlah3, jumlah4, jumlah5, jumlah6, total, riwayat;
    String email;
    TextView tvNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getReadableDatabase();

        tvNotFound = findViewById(R.id.noHistory);

        session = new SessionManager(getApplicationContext());

        HashMap<String, String> user = session.getUserDetails();

        email = user.get(SessionManager.KEY_EMAIL);

        refreshList();
        setupToolbar();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbHistory);
        toolbar.setTitle("Riwayat Pemesanan");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refreshList() {
        final ArrayList<HistoryModel> hasil = new ArrayList<>();
        cursor = db.rawQuery("SELECT * FROM TB_BOOK, TB_HARGA WHERE TB_BOOK.id_book = TB_HARGA.id_book AND username='" + email + "'", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            id_book = cursor.getString(0);
            menu1 = cursor.getString(1);
            menu2 = cursor.getString(2);
            menu3 = cursor.getString(3);
            menu4 = cursor.getString(4);
            menu5 = cursor.getString(5);
            menu6 = cursor.getString(6);
            tanggal = cursor.getString(7);
            jumlah1 = cursor.getString(8);
            jumlah2 = cursor.getString(9);
            jumlah3 = cursor.getString(10);
            jumlah4 = cursor.getString(11);
            jumlah5 = cursor.getString(12);
            jumlah6 = cursor.getString(13);
            total = cursor.getString(22);
            riwayat = "Berhasil melakukan pemesenan dengan menu :\n1." + menu1 + " dengan jumlah  " + jumlah1 + "\n2. " + menu2 + " dengan jumlah " + jumlah2 + "\n3. " + menu3 + " dengan jumlah " + jumlah3 + " \n4. " + menu4 + " dengan jumlah " + jumlah4 + "\n5. " + menu5 + " dengan jumlah " + jumlah5 + "\n6. " + menu6 + " dengan jumlah " + jumlah6 + ".";
            hasil.add(new HistoryModel(id_book, tanggal, riwayat, total, R.drawable.ic_account_circle_black_24dp));
        }

        ListView listBook = findViewById(R.id.list_booking);
        HistoryAdapter arrayAdapter = new HistoryAdapter(this, hasil);
        listBook.setAdapter(arrayAdapter);

        //delete data
        listBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String selection = hasil.get(i).getIdBook();
                final CharSequence[] dialogitem = {"Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        try {
                            db.execSQL("DELETE FROM TB_BOOK where id_book = " + selection + "");
                            id_book = "";
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        refreshList();
                    }
                });
                builder.create().show();
            }
        });

        if (id_book.equals("")) {
            tvNotFound.setVisibility(View.VISIBLE);
            listBook.setVisibility(View.GONE);
        } else {
            tvNotFound.setVisibility(View.GONE);
            listBook.setVisibility(View.VISIBLE);
        }

    }
}
