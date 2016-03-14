package source.zx.com.categorydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Description
 *
 * @version 1.0
 *          time 15:56 2016/3/10.
 * @auther zhangxiao
 */
public class CategoryItemHelper implements AdapterView.OnItemClickListener {

    private View rootView;

    private ListView groupList;

    private ListView childList;

    private CategoryAdapter groupAdapter;

    private CategoryChildAdapter childAdapter;

    private CateFilterSelListener cateFilterSelListener;

    private CateFilterStatusListener cateFilterStatusListener;

    private CategoryGroupBean tempGroupBean;

    // 临时存储的点击父分类的地址
    private int temGroupIndex = -1;
    // 点击父分类，以及对应的子分类的确定的地址位置，用来显示下次再展示过滤内容时候的数据内容
    private int preGroupIndex = -1, preChildIndex = -1;

    public CategoryItemHelper(Context context, ViewGroup viewGroup, boolean blockCate) {
        // 初始化数据，用来注册回调方法
        if (context instanceof CateFilterSelListener) {
            cateFilterSelListener = (CateFilterSelListener) context;
        }
        // 初始化view
        rootView = LayoutInflater.from(context).inflate(R.layout.category_filter, viewGroup, false);
        groupList = (ListView) rootView.findViewById(R.id.group_list);
        childList = (ListView) rootView.findViewById(R.id.child_list);
        // 用来实现底部半透明的view点击的时候隐藏分类列表,这个view主要是listview数据少的时候会出现的问题
        rootView.findViewById(R.id.block_filter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidenCategory();
            }
        });
        // 用来实现底部半透明的view点击的时候隐藏分类列表
        rootView.findViewById(R.id.list_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidenCategory();
            }
        });

        groupList.setOnItemClickListener(this);

        childList.setOnItemClickListener(this);

        groupAdapter = new CategoryGroupAdapter(context);

        groupList.setAdapter(groupAdapter);

        // 判断是否是锁住子分类
        if (blockCate) {
            childList.setVisibility(View.GONE);
        } else {
            childAdapter = new CategoryChildAdapter(context);
            childList.setAdapter(childAdapter);
        }

        viewGroup.addView(rootView);
        hidenCategory();
    }

    public void initData(List<CategoryGroupBean> groupBeanList) {
        groupAdapter.setDataList(groupBeanList);
        groupAdapter.notifyDataSetChanged();
    }

    /**
     * 显示分类内容
     */
    public void showCategory() {

        refreshChangeChoice();

        if (cateFilterStatusListener != null) {
            cateFilterStatusListener.beforeShowFilter(this);
        }
        rootView.setVisibility(View.VISIBLE);

    }

    // 如果显示分类数据的时候，改变的数据内容，则切换回来的时候要进行判断时候恢复数据以及选择状态
    private void refreshChangeChoice() {
        if (preChildIndex > -1) {
            int tempChildIndex = childList.getCheckedItemPosition();
            int temGroupIndex = groupList.getCheckedItemPosition();
            if (temGroupIndex != preGroupIndex || tempChildIndex != preChildIndex) {

                CategoryGroupBean itemGroup = (CategoryGroupBean) groupAdapter.getItem(preGroupIndex);
                childAdapter.setDataList(itemGroup.getCateChildList());
                childAdapter.notifyDataSetChanged();
                childList.setSelection(0);
                childList.setItemChecked(preChildIndex,true);

            }
        }
    }

    /**
     * 当前分类是否显示
     */
    public boolean isShowCategory() {
        return rootView.getVisibility() == View.VISIBLE;
    }

    /**
     * 隐藏分类内容
     */
    public void hidenCategory() {
        if (cateFilterStatusListener != null) {

            cateFilterStatusListener.beforeHidenFilter(this);
        }
        rootView.setVisibility(View.GONE);
    }

    /**
     * 子分类list是否被锁住
     *
     * @return
     */
    public boolean isBlockChildList() {
        return childList.getVisibility() == View.GONE;
    }

    // 设置分类选择的回调事件
    public void setCateFilterSelListener(CateFilterSelListener cateFilterSelListener) {
        this.cateFilterSelListener = cateFilterSelListener;
    }

    // 设置分类状态的回调事件，这个回调主要用于在manager中进行管理显示的状态
    public void setCateFilterStatusListener(CateFilterStatusListener cateFilterStatusListener) {
        this.cateFilterStatusListener = cateFilterStatusListener;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.group_list) {
            CategoryGroupBean itemGroup = (CategoryGroupBean) groupAdapter.getItem(position);
            // 如果子分类的list被锁住以及，有子分类，但是当前这个item 子分类的数据为空


            if (isBlockChildList() || !itemGroup.hasChildList()) {
                preGroupIndex = position;
                if (cateFilterSelListener != null) {
                    cateFilterSelListener.selectItem(itemGroup, null);
                }
                hidenCategory();
            } else {
                // 如果父级分类进行切换的时候，需要刷新子分类的内容，以及清除之前的选择状态，并移动到第一个元素的内容
                if (tempGroupBean != itemGroup) {
                    temGroupIndex = position;
                    tempGroupBean = itemGroup;

                    if (preGroupIndex == temGroupIndex) {
                        refreshChangeChoice();
                    } else {
                        childAdapter.setDataList(itemGroup.getCateChildList());
                        childAdapter.notifyDataSetChanged();
                        childList.setSelection(0);
                        childList.clearChoices();
                    }


                }
            }

        } else if (parent.getId() == R.id.child_list) {
            // 选择了子分类，隐藏分类内容，以及调用外部回调
            preGroupIndex = temGroupIndex;
            preChildIndex = position;
            if (cateFilterSelListener != null) {
                cateFilterSelListener.selectItem(tempGroupBean, (CategoryBaseBean) childAdapter.getItem(position));
            }
            hidenCategory();
        }
    }

}
