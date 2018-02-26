package com.wcompany.mrwah.health_services;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by mrwah on 2/24/2018.
 */

public class SliderAdapter extends PagerAdapter{
    Context context;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context)
    {
        this.context= context;
    }
public int[] slide_images={
        R.mipmap.illustration1,
        R.mipmap.illustration2,
        R.mipmap.illustration3
    };
    public String[] slide_descriptions={
            "ne pas manquez votre calendrier de prise de médicaments",
            "des docteurs spécialistes peuvent vous aider et vous répondront à vous questions",
            "vous pouvez trouver un médecin pour vous visiter à la maison si vous avez besoin"
    };
    @Override
    public int getCount() {
        return slide_descriptions.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view== (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
     layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView sliderDescription = (TextView) view.findViewById(R.id.slide_desc_text);

        slideImageView.setImageResource(slide_images[position]);
        sliderDescription.setText(slide_descriptions[position]);

        container.addView(view);
    return view;
    };

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
