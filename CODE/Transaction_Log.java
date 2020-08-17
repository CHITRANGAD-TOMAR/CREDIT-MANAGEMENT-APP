package com.example.cm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.cm.Databases.Database_Credit_Table;
import com.example.cm.Databases.Database_Transaction_Table;
import com.example.cm.List.ListAdapter_For_Transaction;
import com.example.cm.List.ListAdapter_For_Users;
import com.example.cm.List.Transaction_Items;
import com.example.cm.List.User_Items;
import com.example.cm.UserOperation.Add_User;
import com.example.cm.UserOperation.Update_User;
import com.example.cm.UserOperation.View_User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Transaction_Log extends AppCompatActivity {
    GridView gridView;
    ArrayList<Transaction_Items> list;
    ListAdapter_For_Transaction adapter = null;
    Database_Transaction_Table mdatabase_transaction_table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction__log);

        gridView = (GridView) findViewById(R.id.gridview_for_transaction);
        list = new ArrayList<Transaction_Items>();
        adapter = new ListAdapter_For_Transaction(this, R.layout.transaction_list, list);
        gridView.setAdapter(adapter);
        mdatabase_transaction_table = new Database_Transaction_Table(this);

        // Show Users info in the list
        Cursor data = mdatabase_transaction_table.getData();
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String transaction_id = data.getString(1);
            String sender_name = data.getString(2);
            String sender_accountno = data.getString(3);
            String reciever_name = data.getString(4);
            String reciever_accountno = data.getString(5);
            String transferred_amount = data.getString(6);

            list.add(new Transaction_Items(id, transaction_id, sender_name, sender_accountno, reciever_name, reciever_accountno, transferred_amount));
        }
        adapter.notifyDataSetChanged();

    }

}
