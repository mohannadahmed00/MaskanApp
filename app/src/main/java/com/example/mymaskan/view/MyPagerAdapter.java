package com.example.mymaskan.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.mymaskan.R;

public class MyPagerAdapter extends PagerAdapter {
    Context context;
    //private int[] sliderImageId = new int[]{R.drawable.bath, R.drawable.home, R.drawable.tig};
    private String[] sliderImageURL = new String[]{"https://scontent-hbe1-1.xx.fbcdn.net/v/t1.0-9/122993189_662089837837388_205202376940549689_o.jpg?_nc_cat=111&ccb=2&_nc_sid=730e14&_nc_eui2=AeGckgRDJJOsC2RnYorP_DneN4CZS9gpItw3gJlL2Cki3E8W8-bZHKy89j92WByd3OM3XbppXcVZP7ZoC4OzcU2E&_nc_ohc=yQE6Oaago5wAX8q4AFO&_nc_ht=scontent-hbe1-1.xx&oh=75929680946d872f593272a40ee274c4&oe=5FC8614C"
            , "https://scontent.fcai20-2.fna.fbcdn.net/v/t1.0-9/122681393_662089821170723_5799743689894292133_o.jpg?_nc_cat=102&ccb=2&_nc_sid=730e14&_nc_eui2=AeFmgocdc5auPAkXSukBSKe3kK-NWENmjW2Qr41YQ2aNbWnpchtJcL4rSJC3A9LQKPO8l4HPJI6EIC_ooo7a5ume&_nc_ohc=_XRLRPs8z4EAX9bdAUl&_nc_ht=scontent.fcai20-2.fna&oh=c4b17e2a07062dc6215ea06b3f62925d&oe=5FCB5964"
            , "https://scontent.fcai20-2.fna.fbcdn.net/v/t1.0-9/122989057_662089747837397_8755288845439239197_o.jpg?_nc_cat=102&ccb=2&_nc_sid=730e14&_nc_eui2=AeGGRc23U_Fjsax2xqFo8ptLzLbWR9b92S3MttZH1v3ZLQ5bNoBpFjlgTA7KPg8uaXF3dHh5-qymUmzoj6P9GuHR&_nc_ohc=CM1pkcJc-zEAX9CMS0i&_nc_ht=scontent.fcai20-2.fna&oh=4d0e6b63283dedc961ff12707ab7ae55&oe=5FCAD479"
            ,"https://scontent.fcai20-2.fna.fbcdn.net/v/t1.0-9/122492348_662089741170731_1553154794545607361_o.jpg?_nc_cat=107&ccb=2&_nc_sid=730e14&_nc_eui2=AeEueP_zy--8SNWksh0MDbFbFpWTHbAxEZAWlZMdsDERkG93I1GNILa320LL8cwUSjCLafozGXnDt7lmuk6tubx4&_nc_ohc=7IV5YjGVAmQAX9uiyqg&_nc_ht=scontent.fcai20-2.fna&oh=a1cb91c257375464b48648390ee54bc3&oe=5FCB15E6"
            ,"https://scontent.fcai20-2.fna.fbcdn.net/v/t1.0-9/122905834_662089641170741_8178950456765309056_o.jpg?_nc_cat=106&ccb=2&_nc_sid=730e14&_nc_eui2=AeHKs7tDeiHeo-jXz5YXqQ8iguPnocLDZnCC4-ehwsNmcG4IGD9flgD6QRnrGY1LkaT2iCxg6XL7NViz1MVo1d84&_nc_ohc=z8VSVyy-LdsAX-caNkd&_nc_ht=scontent.fcai20-2.fna&oh=df11163fad1709ce7cca35591de786d7&oe=5FCB9E93"
            ,"https://scontent.fcai20-2.fna.fbcdn.net/v/t1.0-9/122873754_662089561170749_7671065464338151128_o.jpg?_nc_cat=104&ccb=2&_nc_sid=730e14&_nc_eui2=AeEmZyherCxeswr4TlWzi-HMlm7s7lDZUGqWbuzuUNlQasiBFSlvWpKSwdQLMzAahf5pNZnwA5XyabXfGnUd88O8&_nc_ohc=gDwbN9MRRt8AX9qrb4P&_nc_ht=scontent.fcai20-2.fna&oh=3dad7265cc83fca791aa99cd175c6421&oe=5FC85965"
            ,"https://scontent.fcai20-2.fna.fbcdn.net/v/t1.0-9/122995518_662089597837412_826962629852182743_o.jpg?_nc_cat=108&ccb=2&_nc_sid=730e14&_nc_eui2=AeELmewQilFUC_KnFKQW9cLLkRPVBuZ6Y2WRE9UG5npjZRKC7pawhF4QJ3gvNLy_jnP2Z65X3wxBPv5KvPU5-EAT&_nc_ohc=nTeAcS26ZdcAX9CfHTk&_nc_ht=scontent.fcai20-2.fna&oh=a2333be300d62753c53acdc8e5368fc3&oe=5FC862DE"};

    public MyPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderImageURL.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //imageView.setImageResource(sliderImageId[position]);
        Glide.with(context).load(sliderImageURL[position]).into(imageView);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
