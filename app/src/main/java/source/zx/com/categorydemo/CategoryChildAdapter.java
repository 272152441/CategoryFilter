package source.zx.com.categorydemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Description  用来显示分类子分类的适配器
 *
 * @version 1.0
 *          time 9:53 2016/3/12.
 * @auther zhangxiao
 */
public class CategoryChildAdapter extends CategoryAdapter {

    public CategoryChildAdapter(Context context) {
        super(context);
    }

    public CategoryChildAdapter(Context context, List<? extends CategoryBaseBean> dataList) {
        super(context, dataList);
    }

}
