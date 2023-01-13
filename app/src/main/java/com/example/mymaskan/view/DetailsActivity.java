package com.example.mymaskan.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.mymaskan.R;
import com.example.mymaskan.model.ItemData;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity {


    @BindView(R.id.vp_images_details)
    ViewPager vpImages;
    @BindView(R.id.tv_price_details)
    TextView tvPrice;
    @BindView(R.id.tv_area_details)
    TextView tvArea;
    @BindView(R.id.tv_rooms_details)
    TextView tvRooms;
    @BindView(R.id.tv_address_details)
    TextView tvAddress;
    @BindView(R.id.tv_date_details)
    TextView tvDate;

    int currentPage=0;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        changeStatBarColor(DetailsActivity.this, R.color.colorBase);
        MyPagerAdapter adapter = new MyPagerAdapter(this);
        vpImages.setAdapter(adapter);

        AppBarLayout appbar = findViewById(R.id.appbar_details);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return false;
            }
        });
        params.setBehavior(behavior);


        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 4) {
                    currentPage = 0;
                }
                vpImages.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);

        /*List<SlideModel> imagesList=new ArrayList<>();
        imagesList.add(new SlideModel("https://picsum.photos/id/896/300/200", ScaleTypes.CENTER_CROP));
        imagesList.add(new SlideModel("https://picsum.photos/id/894/300/200",ScaleTypes.CENTER_CROP));
        imagesList.add(new SlideModel("https://picsum.photos/id/892/300/200",ScaleTypes.CENTER_CROP));
        imagesList.add(new SlideModel("https://picsum.photos/id/891/300/200",ScaleTypes.CENTER_CROP));
        isImages.setImageList(imagesList);*/

    }
/*
    void setDialog(int layout) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        dialog = adb.setView(layout).create();
        // (That new View is just there to have something inside the dialog that can grow big enough to cover the whole screen.)

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }*/

    void changeStatBarColor(AppCompatActivity activity, int color) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(activity, color));

    }


    @OnClick({R.id.btn_contact_details, R.id.btn_tour_details})
    public void onClick(View view) {
        /*Dialog dialog;
        switch (view.getId()) {
            case R.id.btn_contact_details:


                dialog = new Dialog(this);
                dialog.setContentView(R.layout.contact);
                Button btnDone=dialog.findViewById(R.id.btn_contact);
                btnDone.setOnClickListener(e->{
                    dialog.dismiss();
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                //setDialog(R.layout.contact);
                break;
            case R.id.btn_tour_details:


                Button btnPersonal, btnVideo, btnRequest;
                RecyclerView recycler;

                dialog = new Dialog(this);
                dialog.setContentView(R.layout.tour);
                recycler = dialog.findViewById(R.id.recycler_tour);
                btnPersonal = dialog.findViewById(R.id.btn_personal_tour);
                btnVideo = dialog.findViewById(R.id.btn_video_tour);
                btnRequest = dialog.findViewById(R.id.btn_request_tour);



                *//*WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(lp);*//*
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();


                //dates adapter

                DatesAdapter adapter = new DatesAdapter(R.color.colorGreen);
                ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
                itemViewModel.sendItems("d");
                itemViewModel.listMutableLiveData.observe(this, new Observer<ArrayList<ItemData>>() {
                    @Override
                    public void onChanged(ArrayList<ItemData> itemData) {
                        adapter.setList(itemData);
                    }
                });
                recycler.setAdapter(adapter);
                recycler.setLayoutManager(new LinearLayoutManager(this));


                btnPersonal.setOnClickListener(e -> {
                    btnPersonal.setBackgroundResource(R.drawable.background_white_selected);
                    btnVideo.setBackgroundColor(Color.TRANSPARENT);
                    //btnVideo.setBackground(getDrawable(R.drawable.background_white));
                    Toast.makeText(this, "personal", Toast.LENGTH_SHORT).show();
                });
                btnVideo.setOnClickListener(e -> {
                    btnVideo.setBackgroundResource(R.drawable.background_white_selected);
                    btnPersonal.setBackgroundColor(Color.TRANSPARENT);

                    //btnPersonal.setBackground(getDrawable(R.drawable.background_white));
                    Toast.makeText(this, "video", Toast.LENGTH_SHORT).show();
                });
                btnRequest.setOnClickListener(e -> {
                    if (adapter.getSelectedItem()!=null) {
                        btnRequest.setText(adapter.getSelectedItem().price);
                    }else {
                        Toast.makeText(this, "request", Toast.LENGTH_SHORT).show();
                    }
                    
                });

                break;
        }*/
    }
}