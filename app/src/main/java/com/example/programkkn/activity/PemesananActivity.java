package com.example.programkkn.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.programkkn.R;
import com.example.programkkn.database.DatabaseHelper;
import com.example.programkkn.session.SessionManager;

import java.util.Calendar;
import java.util.HashMap;

public class PemesananActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Spinner spinMenu1, spinMenu2,spinMenu3, spinMenu4,spinMenu5, spinMenu6,spinJumlah1, spinJumlah2,spinJumlah3, spinJumlah4,spinJumlah5, spinJumlah6;
    SessionManager session;
    String email;
    int id_book;
    public String sMenu1, sMenu2, sMenu3, sMenu4, sMenu5, sMenu6, sTanggal, sJumlah1, sJumlah2, sJumlah3, sJumlah4, sJumlah5, sJumlah6;
    int jmlJumlah1, jmlJumlah2, jmlJumlah3, jmlJumlah4, jmlJumlah5, jmlJumlah6;
    int hargaJumlah1, hargaJumlah2,hargaJumlah3, hargaJumlah4,hargaJumlah5, hargaJumlah6;
    int hargaTotalJumlah1, hargaTotalJumlah2, hargaTotalJumlah3, hargaTotalJumlah4,hargaTotalJumlah5, hargaTotalJumlah6,hargaTotal;
    private EditText etTanggal;
    private DatePickerDialog dpTanggal;
    Calendar newCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        dbHelper = new DatabaseHelper(PemesananActivity.this);
        db = dbHelper.getReadableDatabase();

        final String[] menu1 = {"Ayam Bakar"};
        final String[] menu2 = {"Ayam Goreng"};
        final String[] menu3 = {"Sop Iga"};
        final String[] menu4 = {"Soto Betawi Daging"};
        final String[] menu5 = {"Soto Betawi Ayam"};
        final String[] menu6 = {"Nasi"};
        final String[] jumlah1 = {"0", "1", "2", "3", "4", "5","6", "7", "8", "9", "10"};
        final String[] jumlah2 = {"0", "1", "2", "3", "4", "5","6", "7", "8", "9", "10"};
        final String[] jumlah3 = {"0", "1", "2", "3", "4", "5","6", "7", "8", "9", "10"};
        final String[] jumlah4 = {"0", "1", "2", "3", "4", "5","6", "7", "8", "9", "10"};
        final String[] jumlah5 = {"0", "1", "2", "3", "4", "5","6", "7", "8", "9", "10"};
        final String[] jumlah6 = {"0", "1", "2", "3", "4", "5","6", "7", "8", "9", "10"};


        spinMenu1 = findViewById(R.id.menu1);
        spinMenu2 = findViewById(R.id.menu2);
        spinMenu3 = findViewById(R.id.menu3);
        spinMenu4 = findViewById(R.id.menu4);
        spinMenu5 = findViewById(R.id.menu5);
        spinMenu6 = findViewById(R.id.menu6);
        spinJumlah1 = findViewById(R.id.jumlah1);
        spinJumlah2 = findViewById(R.id.jumlah2);
        spinJumlah3 = findViewById(R.id.jumlah3);
        spinJumlah4 = findViewById(R.id.jumlah4);
        spinJumlah5 = findViewById(R.id.jumlah5);
        spinJumlah6 = findViewById(R.id.jumlah6);

        ArrayAdapter<CharSequence> adapterMenu1 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, menu1);
        adapterMenu1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMenu1.setAdapter(adapterMenu1);

        ArrayAdapter<CharSequence> adapterMenu2 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, menu2);
        adapterMenu2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMenu2.setAdapter(adapterMenu2);

        ArrayAdapter<CharSequence> adapterMenu3 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, menu3);
        adapterMenu3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMenu3.setAdapter(adapterMenu3);

        ArrayAdapter<CharSequence> adapterMenu4 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, menu4);
        adapterMenu4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMenu4.setAdapter(adapterMenu4);

        ArrayAdapter<CharSequence> adapterMenu5 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, menu5);
        adapterMenu5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMenu5.setAdapter(adapterMenu5);

        ArrayAdapter<CharSequence> adapterMenu6 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, menu6);
        adapterMenu6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMenu6.setAdapter(adapterMenu6);

        ArrayAdapter<CharSequence> adapterJumlah1 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, jumlah1);
        adapterJumlah1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJumlah1.setAdapter(adapterJumlah1);

        ArrayAdapter<CharSequence> adapterJumlah2 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, jumlah2);
        adapterJumlah2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJumlah2.setAdapter(adapterJumlah2);

        ArrayAdapter<CharSequence> adapterJumlah3 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, jumlah3);
        adapterJumlah3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJumlah3.setAdapter(adapterJumlah3);

        ArrayAdapter<CharSequence> adapterJumlah4 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, jumlah4);
        adapterJumlah4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJumlah4.setAdapter(adapterJumlah4);

        ArrayAdapter<CharSequence> adapterJumlah5 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, jumlah5);
        adapterJumlah5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJumlah5.setAdapter(adapterJumlah5);

        ArrayAdapter<CharSequence> adapterJumlah6 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, jumlah6);
        adapterJumlah6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJumlah6.setAdapter(adapterJumlah6);

        spinMenu1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sMenu1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinMenu2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sMenu2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinMenu3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sMenu3 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinMenu4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sMenu4 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinMenu5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sMenu5 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinMenu6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sMenu6 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJumlah1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sJumlah1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJumlah2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sJumlah2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJumlah3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sJumlah3 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJumlah4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sJumlah4 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJumlah5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sJumlah5 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinJumlah6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sJumlah6 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button btnBook = findViewById(R.id.book);

        etTanggal = findViewById(R.id.tanggal_berangkat);
        etTanggal.setInputType(InputType.TYPE_NULL);
        etTanggal.requestFocus();
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        email = user.get(SessionManager.KEY_EMAIL);
        setDateTimeField();

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perhitunganHarga();
                if (sMenu1 != null && sMenu2 != null && sMenu3 != null && sMenu4 != null  && sMenu5 != null && sMenu6 != null && sTanggal != null && sJumlah1 != null && sJumlah2 != null && sJumlah3 != null && sJumlah4 != null  && sJumlah5 != null && sJumlah6 != null) {
                        AlertDialog dialog = new AlertDialog.Builder(PemesananActivity.this)
                                .setTitle("Simpan Pemesanan?")
                                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        try {
                                            db.execSQL("INSERT INTO TB_BOOK (menu,menuu,menuuu,menuuuu,menuuuuu,menuuuuuu, tanggal, jumlah,jumlahh,jumlahhh,jumlahhhh,jumlahhhhh,jumlahhhhhh) VALUES ('" +
                                                    sMenu1 + "','" +
                                                    sMenu2 + "','" +
                                                    sMenu3 + "','" +
                                                    sMenu4 + "','" +
                                                    sMenu5 + "','" +
                                                    sMenu6 + "','" +
                                                    sTanggal + "','" +
                                                    sJumlah1 + "','" +
                                                    sJumlah2 + "','" +
                                                    sJumlah3 + "','" +
                                                    sJumlah4 + "','" +
                                                    sJumlah5 + "','" +
                                                    sJumlah6 + "');");
                                                    cursor = db.rawQuery("SELECT id_book FROM TB_BOOK ORDER BY id_book DESC", null);
                                            cursor.moveToLast();
                                            if (cursor.getCount() > 0) {
                                                cursor.moveToPosition(0);
                                                id_book = cursor.getInt(0);
                                            }
                                            db.execSQL("INSERT INTO TB_HARGA (username, id_book, harga_jumlah, harga_jumlahh, harga_jumlahhh, harga_jumlahhhh, harga_jumlahhhhh, harga_jumlahhhhhh, harga_total) VALUES ('" +
                                                    email + "','" +
                                                    id_book + "','" +
                                                    hargaJumlah1 + "','" +
                                                    hargaJumlah2 + "','" +
                                                    hargaJumlah3 + "','" +
                                                    hargaJumlah4 + "','" +
                                                    hargaJumlah5 + "','" +
                                                    hargaJumlah6 + "','" +
                                                    hargaTotal + "');");
                                            Toast.makeText(PemesananActivity.this, "Pemesanan berhasil", Toast.LENGTH_LONG).show();
                                            finish();
                                        } catch (Exception e) {
                                            Toast.makeText(PemesananActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                                .setNegativeButton("Tidak", null)
                                .create();
                        dialog.show();
                } else {
                    Toast.makeText(PemesananActivity.this, "Mohon lengkapi data pemesanan!", Toast.LENGTH_LONG).show();
                }
            }
        });

        setupToolbar();

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbKrl);
        toolbar.setTitle("Form Pemesanan");
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

    public void perhitunganHarga() {
        if (sMenu1 == "Ayam Bakar"){
            hargaJumlah1 = 15000;
            if (sMenu2 == "Ayam Goreng") {
                hargaJumlah2 = 15000;
                if (sMenu3 == "Sop Iga") {
                    hargaJumlah3 = 27000;
                    if (sMenu4 == "Soto Betawi Daging") {
                        hargaJumlah4 = 20000;
                        if (sMenu5 == "Soto Betawi Ayam") {
                            hargaJumlah5 = 20000;
                            if (sMenu6 == "Nasi") {
                                hargaJumlah6 = 5000;
                            }
                        }
                    }
                }
            }
        }

        jmlJumlah1 = Integer.parseInt(sJumlah1);
        jmlJumlah2 = Integer.parseInt(sJumlah2);
        jmlJumlah3 = Integer.parseInt(sJumlah3);
        jmlJumlah4 = Integer.parseInt(sJumlah4);
        jmlJumlah5 = Integer.parseInt(sJumlah5);
        jmlJumlah6 = Integer.parseInt(sJumlah6);


        hargaTotalJumlah1 = jmlJumlah1 * hargaJumlah1;
        hargaTotalJumlah2 = jmlJumlah2 * hargaJumlah2;
        hargaTotalJumlah3 = jmlJumlah3 * hargaJumlah3;
        hargaTotalJumlah4 = jmlJumlah4 * hargaJumlah4;
        hargaTotalJumlah5 = jmlJumlah5 * hargaJumlah5;
        hargaTotalJumlah6 = jmlJumlah6 * hargaJumlah6;

        hargaTotal = hargaTotalJumlah1 + hargaTotalJumlah2 + hargaTotalJumlah3 + hargaTotalJumlah4 + hargaTotalJumlah5 + hargaTotalJumlah6;
    }

    private void setDateTimeField() {
        etTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggal.show();
            }
        });

        dpTanggal = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                String[] bulan = {"Januari", "Februari", "Maret", "April", "Mei",
                        "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
                sTanggal = dayOfMonth + " " + bulan[monthOfYear] + " " + year;
                etTanggal.setText(sTanggal);

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}