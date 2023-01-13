package com.example.mymaskan.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymaskan.R;
import com.example.mymaskan.model.ItemData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.appbar.AppBarLayout;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.bendik.simplerangeview.SimpleRangeView;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    @BindView(R.id.et_search_main)
    AutoCompleteTextView etSearch;
    @BindView(R.id.recycler_main)
    RecyclerView recycler;
    @BindView(R.id.tv_type_main)
    TextView tvType;
    @BindView(R.id.tv_price_main)
    TextView tvPrice;
    @BindView(R.id.tv_area_main)
    TextView tvArea;
    @BindView(R.id.tv_rooms_main)
    TextView tvRooms;

    /*@BindView(R.id.l_type_main)
    LinearLayout lType;
    @BindView(R.id.l_price_main)
    LinearLayout lPrice;
    @BindView(R.id.l_area_main)
    LinearLayout lArea;
    @BindView(R.id.l_room_main)
    LinearLayout lRooms;*/


    String type = "";
    int countPrice = 900000, countArea = 5000, countRoom = 10, countBath = 100;
    int minPrice = 0, maxPrice = countPrice, minArea = 0, maxArea = countArea, minRoom = 0, maxRoom = countRoom, minBath = 0, maxBath = countBath - 1;


    private GoogleMap mMap;
    LatLng cairo = new LatLng(30.033333, 31.233334);
    LatLng sydney = new LatLng(-34, 151);

    ArrayList<String> govs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    search();
                    return true;
                }
                return false;
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.f_map_main);
        mapFragment.getMapAsync(this);


        //i can not understand it
        AppBarLayout appbar = findViewById(R.id.appbar_main);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();

        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(AppBarLayout appBarLayout) {
                return false;
            }
        });
        params.setBehavior(behavior);


        changeStatBarColor(MainActivity.this, R.color.colorBase);


        ItemListAdapter adapter = new ItemListAdapter();

        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        itemViewModel.sendItems("p");
        itemViewModel.listMutableLiveData.observe(this, new Observer<ArrayList<ItemData>>() {
            @Override
            public void onChanged(ArrayList<ItemData> itemData) {
                adapter.setList(itemData);
            }
        });
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));


        govs = new ArrayList<>();
        govs.add("Alexandria");
        govs.add("Aswan");
        govs.add("Asyut");
        govs.add("Beheira");
        govs.add("Beni Suef");
        govs.add("Cairo");
        govs.add("Faiyoum");
        govs.add("Ismaillia");
        govs.add("Dakahlia");
        govs.add("Damietta");
        govs.add("Gharbia");
        govs.add("Giza");
        govs.add("Kafr El-Sheikh");
        govs.add("Luxor");
        govs.add("Matruh");
        govs.add("Minya");
        govs.add("Monufia");
        govs.add("New Valley");
        govs.add("North Sinai");
        govs.add("Port Said");
        govs.add("Qalyubia");
        govs.add("Qena");
        govs.add("Red Sea");
        govs.add("Sharqia");
        govs.add("Sohag");
        govs.add("South Sinai");
        govs.add("Suez");





        AutoCompleteAdapter autoCompleteTextView = new AutoCompleteAdapter(getBaseContext(), 0, R.id.et_search_main, govs);
        etSearch.setAdapter(autoCompleteTextView);

    }

    void changeStatBarColor(AppCompatActivity activity, int color) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(activity, color));

    }


    @OnClick({R.id.tv_type_main, R.id.tv_price_main, R.id.tv_area_main, R.id.tv_rooms_main, R.id.btn_search_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_type_main:
                Dialog dialog;
                TextView tvTitle, tvItem1, tvItem2;
                Button btnOkP;


                dialog = new Dialog(this);
                dialog.setContentView(R.layout.select_item);
                tvTitle = dialog.findViewById(R.id.tv_title_select_item);
                tvItem1 = dialog.findViewById(R.id.tv_item1_select_item);
                tvItem2 = dialog.findViewById(R.id.tv_item2_select_item);
                btnOkP = dialog.findViewById(R.id.btn_ok_select_item);


                if (tvType.getText().toString().equals(tvItem1.getText().toString())) {
                    tvItem1.setBackgroundResource(R.drawable.background_dark_orange_selected);
                } else if (tvType.getText().toString().equals(tvItem2.getText().toString())) {
                    tvItem2.setBackgroundResource(R.drawable.background_dark_orange_selected);
                }

        /*WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);*/
                tvTitle.setText(getString(R.string.type));
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                String item1=tvItem1.getText().toString(),item2=tvItem2.getText().toString();
                tvItem1.setOnClickListener(e -> {
                    if (!type.equals(item1)){
                        tvItem1.setBackgroundResource(R.drawable.background_dark_orange_selected);
                        tvItem2.setBackgroundColor(Color.TRANSPARENT);
                        type = String.valueOf(tvItem1.getText());
                    }else {
                        tvItem1.setBackgroundColor(Color.TRANSPARENT);
                        type = "";
                    }
                });

                tvItem2.setOnClickListener(e -> {
                    if (!type.equals(item2)){
                        tvItem2.setBackgroundResource(R.drawable.background_dark_orange_selected);
                        tvItem1.setBackgroundColor(Color.TRANSPARENT);
                        type = tvItem2.getText().toString();
                    }else {
                        tvItem2.setBackgroundColor(Color.TRANSPARENT);
                        type = "";
                    }
                });

                btnOkP.setOnClickListener(e -> {
                    tvType.setText(type);
                    if (!type.equals("")) {
                        /*tvType.setVisibility(View.VISIBLE);
                        lType.setVisibility(View.GONE);*/
                        tvType.setText(type);
                        tvType.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    }else {
                        /*tvType.setVisibility(View.GONE);
                        lType.setVisibility(View.VISIBLE);*/
                        tvType.setText(getString(R.string.type));
                        tvType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_type,0,0,0);
                    }
                    dialog.dismiss();

                });
                break;
            case R.id.tv_price_main:
                setRanger(tvPrice, getString(R.string.price), countPrice, minPrice, maxPrice,R.drawable.ic_price);
                break;
            case R.id.tv_area_main:
                setRanger(tvArea, getString(R.string.area), countArea, minArea, maxArea,R.drawable.ic_area);
                break;
            case R.id.tv_rooms_main:
                setRanger(tvRooms, getString(R.string.room), countRoom, minRoom, maxRoom,R.drawable.ic_rooms);
                break;
            case R.id.btn_search_main:
                search();
                break;
        }
    }
    void search(){
        LatLng first = null, last = null;
        double[] location;
        String cityName = etSearch.getText().toString();
        if (!cityName.equals("")) {
            mMap.clear();
            location = getLatLng(cityName+" egypt");
            if (location!=null){
                for (int i = 0; i < 7; i++) {
                    double lat = location[0] + getDouble(), lng = location[1] + getDouble();
                    if (i == 0) {
                        first = new LatLng(lat, lng);
                    } else if (i == 6) {
                        last = new LatLng(lat, lng);
                    }
                    LatLng customMarkerLocationOne = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(customMarkerLocationOne).icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MainActivity.this)))).setTitle("Hotel Nirulas Noida");

                }

                /*LatLng customMarkerLocationOne = new LatLng(28.583911, 77.319116);
                LatLng customMarkerLocationTwo = new LatLng(28.583078, 77.313744);
                LatLng customMarkerLocationThree = new LatLng(28.580903, 77.317408);
                LatLng customMarkerLocationFour = new LatLng(28.580108, 77.315271);

                mMap.addMarker(new MarkerOptions().position(customMarkerLocationOne).icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MainActivity.this)))).setTitle("Hotel Nirulas Noida");
                mMap.addMarker(new MarkerOptions().position(customMarkerLocationTwo).icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MainActivity.this)))).setTitle("Hotel Nirulas Noida");
                mMap.addMarker(new MarkerOptions().position(customMarkerLocationThree).icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MainActivity.this)))).setTitle("Hotel Nirulas Noida");
                mMap.addMarker(new MarkerOptions().position(customMarkerLocationFour).icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MainActivity.this)))).setTitle("Hotel Nirulas Noida");
*/
                //LatLngBound will cover all your marker on Google Maps
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(first); //Taking Point A (First LatLng)
                builder.include(last); //Taking Point B (Second LatLng)
                LatLngBounds bounds = builder.build();
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 200);
                mMap.moveCamera(cu);
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            }else {
                Toast.makeText(this, "no city", Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(this, "nothing", Toast.LENGTH_SHORT).show();
        }

    }
    void setRanger(TextView textView, String title, int myCount, int min, int max,int icon) {
        Dialog dialog;
        SimpleRangeView rsRange;
        TextView tvTitle;
        Button btnOkP;
        EditText tvMin, tvMax;


        dialog = new Dialog(this);
        dialog.setContentView(R.layout.one_range);
        tvTitle = dialog.findViewById(R.id.tv_title_one_range);
        rsRange = dialog.findViewById(R.id.rs_range_one_range);
        tvMin = dialog.findViewById(R.id.et_min_one_range);
        tvMax = dialog.findViewById(R.id.et_max_one_range);
        btnOkP = dialog.findViewById(R.id.btn_ok_one_range);


        int x;
        if (myCount >= 1000) {
            x = 1000;
        } else {
            x = 1;
        }
        rsRange.setCount((myCount / x) + 1);

        rsRange.setStart(min / x);
        rsRange.setEnd(max / x);

        if (min != 0) {
            tvMin.setHint(format(min));
        }
        if (max != myCount) {
            tvMax.setHint(format(max));
        }


        /*WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);*/
        tvTitle.setText(title);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


        rsRange.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @Override
            public void onStartRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                tvMin.setHint(format(i * x));
            }

            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                tvMax.setHint(format(i * x));
            }
        });

        tvMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //String textMin = tvMin.getText().toString();


                if (!s.toString().equals("")) {
                    if (isNum(s.toString()) && ((Integer.parseInt(s.toString()) >= x) || (Integer.parseInt(s.toString()) == 0))) {
                        rsRange.setStart(Integer.parseInt(s.toString()) / x);
                    } else {
                        Toast.makeText(MainActivity.this, "min error", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //String textMax = tvMax.getText().toString();

                if (!s.toString().equals("")) {
                    if (isNum(s.toString()) && (Integer.parseInt(s.toString()) <= myCount)) {
                        rsRange.setEnd(Integer.parseInt(s.toString()) / x);
                    } else {
                        Toast.makeText(MainActivity.this, "max error", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnOkP.setOnClickListener(e -> {



            if (title.equals("price")) {
                minPrice = rsRange.getStart() * x;
                maxPrice = rsRange.getEnd() * x;
            } else if (title.equals("area")) {
                minArea = rsRange.getStart() * x;
                maxArea = rsRange.getEnd() * x;
            } else {
                minRoom = rsRange.getStart() * x;
                maxRoom = rsRange.getEnd() * x;
            }


            if (rsRange.getStart() == 0 && rsRange.getEnd() == myCount / x) {
                /*textView.setText("any");
                textView.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);*/
                textView.setText(title);
                textView.setCompoundDrawablesWithIntrinsicBounds(icon,0,0,0);
            } else if (rsRange.getStart() == 0) {
                /*textView.setText("up to " + format(rsRange.getEnd() * x));
                textView.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);*/
                textView.setText("up to " + format(rsRange.getEnd() * x));
                textView.setCompoundDrawables(null,null,null,null);
            } else if (rsRange.getEnd() == myCount / x) {
                /*textView.setText(format(rsRange.getStart() * x) + "+");
                textView.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);*/
                textView.setText(format(rsRange.getStart() * x) + "+");
                textView.setCompoundDrawables(null,null,null,null);
            } else {
                /*textView.setText(format(rsRange.getStart() * x) + " - " + format(rsRange.getEnd() * x));
                textView.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);*/
                textView.setText(format(rsRange.getStart() * x) + " - " + format(rsRange.getEnd() * x));
                textView.setCompoundDrawables(null,null,null,null);
            }

            dialog.dismiss();

        });
    }



    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();

    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    boolean isNum(String s) {
        boolean b = true;
        for (char c : s.toCharArray()) {
            if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')) {
                b = false;
                break;
            }
        }
        return b;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //28.9186968 - 30.815435800000003
        LatLng sydney = new LatLng(28.9186968, 30.815435800000003);
        mMap.addMarker(new MarkerOptions().position(sydney).title("hey"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,8));
    }

    double[] getLatLng(String city) {
        double[] latLng = new double[2];
        Geocoder gc = new Geocoder(getBaseContext());

        try {
            List<Address> ads = gc.getFromLocationName(city, 1);
            if (ads.size()!=0){
                latLng[0] = ads.get(0).getLatitude();
                latLng[1] = ads.get(0).getLongitude();
            }else {
                latLng=null;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return latLng;

    }

    public static Bitmap createCustomMarker(Context context) {

        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }

    double getDouble() {
        Random random = new Random();
        return 0.01 + (0.09 - 0.01) * random.nextDouble();
    }
}



                /*Dialog dialogR;
                SimpleRangeView rsRangeR, rsRangeB;
                TextView tvTitleR;
                EditText etMinR, etMinB, etMaxR, etMaxB;
                Button btnOkR;


                dialogR = new Dialog(this);
                dialogR.setContentView(R.layout.two_ranges);
                tvTitleR = dialogR.findViewById(R.id.tv_title_two_ranges);

                rsRangeR = dialogR.findViewById(R.id.rs_range_room_two_ranges);
                rsRangeB = dialogR.findViewById(R.id.rs_range_bath_two_ranges);

                etMinR = dialogR.findViewById(R.id.et_min_room_two_ranges);
                etMaxR = dialogR.findViewById(R.id.et_max_room_two_ranges);

                etMinB = dialogR.findViewById(R.id.et_min_bath_two_ranges);
                etMaxB = dialogR.findViewById(R.id.et_max_bath_two_ranges);

                btnOkR = dialogR.findViewById(R.id.btn_ok_two_ranges);


                rsRangeR.setCount(countRoom);
                rsRangeB.setCount(countBath);

                rsRangeR.setStart(0);
                rsRangeB.setStart(0);

                rsRangeR.setEnd(countRoom);
                rsRangeB.setEnd(countBath);

                if (minRoom != 0) {
                    rsRangeR.setStart(minRoom);
                    etMinR.setText(String.valueOf(minRoom));
                }
                if (minBath != 0) {
                    rsRangeB.setStart(minBath);
                    etMinB.setText(String.valueOf(minBath));
                }
                if (maxRoom != countRoom - 1) {
                    rsRangeR.setEnd(maxRoom);
                    etMaxR.setText(String.valueOf(maxRoom));
                }
                if (maxBath != countBath - 1) {
                    rsRangeB.setEnd(maxBath);
                    etMaxB.setText(String.valueOf(maxBath));
                }


                tvTitleR.setText("Rooms & Baths");
                dialogR.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogR.show();

                rsRangeR.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
                    @Override
                    public void onStartRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                        etMinR.setText(String.valueOf(i));
                    }

                    @Override
                    public void onEndRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                        etMaxR.setText(String.valueOf(i));

                    }
                });


                rsRangeB.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
                    @Override
                    public void onStartRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                        etMinB.setText(String.valueOf(i));
                    }

                    @Override
                    public void onEndRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                        etMaxB.setText(String.valueOf(i));
                    }
                });


                btnOkR.setOnClickListener(e -> {

                    String room = "", bath = "";

                    minRoom = rsRangeR.getStart();
                    maxRoom = rsRangeR.getEnd();
                    minBath = rsRangeB.getStart();
                    maxBath = rsRangeB.getEnd();


                    if ((rsRangeR.getStart() == 0 || etMinR.getText().toString().equals("")) && (rsRangeR.getEnd() == countRoom - 1 || etMaxR.getText().toString().equals(""))) {
                        room = "any";
                    } else if (rsRangeR.getStart() == 0 || etMinR.getText().toString().equals("")) {
                        room = "up to " + rsRangeR.getEnd();
                    } else if (rsRangeR.getEnd() == countRoom - 1 || etMaxR.getText().toString().equals("")) {
                        room = rsRangeR.getStart() + "+";
                    } else {
                        room = rsRangeR.getStart() + " - " + rsRangeR.getEnd();
                    }

                    if ((rsRangeB.getStart() == 0 || etMinB.getText().toString().equals("")) && (rsRangeB.getEnd() == countBath - 1 || etMaxB.getText().toString().equals(""))) {
                        bath = "any";
                    } else if (rsRangeB.getStart() == 0 || etMinB.getText().toString().equals("")) {
                        bath = "up to " + rsRangeB.getEnd();
                    } else if (rsRangeB.getEnd() == countBath - 1 || etMaxB.getText().toString().equals("")) {
                        bath = rsRangeB.getStart() + "+";
                    } else {
                        bath = rsRangeB.getStart() + " - " + rsRangeB.getEnd();
                    }

                    tvRooms.setVisibility(View.GONE);

                    tvRoomsN.setText(room);
                    tvBathsN.setText(bath);
                    tvRoomsN.setVisibility(View.VISIBLE);
                    tvBathsN.setVisibility(View.VISIBLE);


                    dialogR.dismiss();

                });*/
