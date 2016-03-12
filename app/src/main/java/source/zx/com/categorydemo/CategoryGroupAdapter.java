package source.zx.com.categorydemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Description  用于分类中显示父分类的适配器
 *
 * @version 1.0
 *          time 9:53 2016/3/12.
 * @auther zhangxiao
 */
public class CategoryGroupAdapter extends CategoryAdapter {

    public CategoryGroupAdapter(Context context) {
        super(context);
    }

    public CategoryGroupAdapter(Context context, List<? extends CategoryBaseBean> dataList) {
        super(context, dataList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        CategoryViewHolder holder;
        CategoryBaseBean item = (CategoryBaseBean) getItem(position);
        if (convertView == null) {
            view = View.inflate(mContext, R.layout.category_group_item, null);
            holder = new CategoryViewHolder();
            holder.cateGroupName = (TextView) view.findViewById(R.id.cate_group_name);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (CategoryViewHolder) convertView.getTag();
        }
        holder.cateGroupName.setText(item.getCateName());
        return view;
    }

    private static class CategoryViewHolder {
        TextView cateGroupName;
    }
}
