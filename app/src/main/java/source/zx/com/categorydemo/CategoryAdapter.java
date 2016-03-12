package source.zx.com.categorydemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @version 1.0
 *          time 10:46 2016/3/11.
 * @auther zhangxiao
 */
public class CategoryAdapter extends BaseAdapter {

    private List<? extends CategoryBaseBean> mDatas;

    protected Context mContext;

    public CategoryAdapter(Context context){
        mDatas = new ArrayList<>();
        mContext = context;
    }

    public CategoryAdapter(Context context,List<? extends CategoryBaseBean> dataList){
        if(dataList==null){
            throw  new IllegalArgumentException("CategoryAdapter construction argument is null");
        }

        mDatas = dataList;
        mContext = context;
    }

    public void setDataList(List<? extends CategoryBaseBean> dataList){
        mDatas = dataList;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        CategoryViewHolder holder;
        CategoryBaseBean item = mDatas.get(position);
        if (convertView == null) {
            view = View.inflate(mContext, R.layout.category_item, null);
            holder = new CategoryViewHolder();
            holder.cateName = (TextView) view.findViewById(R.id.cate_name);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (CategoryViewHolder) convertView.getTag();
        }
        holder.cateName.setText(item.getCateName());
        return view;
    }

    private static class CategoryViewHolder {
        TextView cateName;
    }


}
