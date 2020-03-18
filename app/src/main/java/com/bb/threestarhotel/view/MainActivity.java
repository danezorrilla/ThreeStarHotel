package com.bb.threestarhotel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;

import com.bb.threestarhotel.R;
import com.bb.threestarhotel.adapter.GuestAdapter;
import com.bb.threestarhotel.database.GuestDatabaseHelper;
import com.bb.threestarhotel.model.Guest;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GuestAdapter.GuestInterface {

    private GuestDatabaseHelper databaseHelper;

    private List guestList = new ArrayList<Guest>();

    private ImageView imageView;

    @BindView(R.id.guest_list)
    RecyclerView guestRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        imageView = findViewById(R.id.main_logo);
        loadImage();

//        createGuest();
//        setUpRecyclerView();

        databaseHelper = new GuestDatabaseHelper(this);
        readDatabase();
        //addGuest();

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(guestRecyclerView);

    }

    private void createGuest(){
        guestList.add(new Guest("Kermit", "101",1 ,R.drawable.kermit));
        guestList.add(new Guest("Bert", "212", 2, R.drawable.bert));
        guestList.add(new Guest("Cookie Monster", "305", 3, R.drawable.cookie));
    }

    private void loadImage(){
        int image = R.drawable.hotel;
        Glide.with(this).load(image).into(imageView);
    }

    private void addGuest(){
        Guest KERMIT = new Guest("Kermit", "101", R.drawable.kermit);
        Guest BERT = new Guest("Bert", "237", R.drawable.bert);
        Guest COOKIE = new Guest("Cookie Monster", "369", R.drawable.cookie);
        //System.out.println(KERMIT);
        databaseHelper.addNewGuest(KERMIT);
        databaseHelper.addNewGuest(BERT);
        databaseHelper.addNewGuest(COOKIE);
        readDatabase();
    }

    public void readDatabase(){
        Cursor guestCursor = databaseHelper.readAllGuest();
        guestCursor.move(-1);

        guestList.clear();

        while(guestCursor.moveToNext()){
            String guestName = guestCursor.getString(guestCursor.getColumnIndex(GuestDatabaseHelper.COLUMN_GUEST_NAME));
            String guestRoom = guestCursor.getString(guestCursor.getColumnIndex(GuestDatabaseHelper.COLUMN_GUEST_ROOM_NUMBER));
            int guestId = guestCursor.getInt(guestCursor.getColumnIndex(GuestDatabaseHelper.COLUMN_GUEST_ID));
            int guestPicture = guestCursor.getInt(guestCursor.getColumnIndex(GuestDatabaseHelper.COLUMN_GUEST_PICTURE));

            guestList.add(new Guest(guestName, guestRoom, guestId, guestPicture));
        }

        setUpRecyclerView();
        guestCursor.close();

    }

    private void setUpRecyclerView(){
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        guestRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        guestRecyclerView.setAdapter(new GuestAdapter(guestList, this));
        guestRecyclerView.addItemDecoration(itemDecoration);
    }

    @Override
    public void deleteGuest(Guest guest){
        databaseHelper.deleteGuest(guest);
    }

}
