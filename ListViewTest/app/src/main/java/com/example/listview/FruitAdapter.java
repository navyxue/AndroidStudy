package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;

    /**
     * 重写父类构造方法，用于将上下文、ListView子项布局的id和数据传进来
     * @param context
     * @param textViewResourceId
     * @param objects
     */
    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    /**
     * 重写父类getView()方法，此方法在每个子项被滚动到屏幕内的时候会被调用
     * @param position
     * @param convertView 用于将之前加载好的布局进行缓存，以便之后可以进行重用
     * @param parent 父组件
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 通过getItem()方法得到当前项的Fruit实例，然后使用LayoutInflater来为这个子项加载传入的布局
        Fruit fruit = getItem(position);
        View view = null;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            // 将ViewHolder存储在View中
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }
}

class ViewHolder {
    ImageView fruitImage;
    TextView fruitName;
}
