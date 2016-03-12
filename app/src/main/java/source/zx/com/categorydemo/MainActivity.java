package source.zx.com.categorydemo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import source.zx.com.categorydemo.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements FragmentList.OnListFragmentInteractionListener
,View.OnClickListener,CateFilterSelListener{

    private List<CategoryGroupBean> groupList = new ArrayList<>();
    private List<CategoryGroupBean> childList = new ArrayList<>();
    private FrameLayout filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFakeData();

        initView();
    }

    private void initFakeData() {

        filter = (FrameLayout) findViewById(R.id.filter_content);



//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);
//
//        for(int i = 0 ; i < 30;i++){
//            String item = "testStr "+ i;
//            adapter.add(item);
//        }
//
//        content.setAdapter(adapter);

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTran = fragManager.beginTransaction();

        fragTran.add(R.id.list_content , new FragmentList());
        fragTran.commit();

        // 二级数据假数据
        for(int i = 0 ; i <20 ; i++){
            CategoryGroupBean item = new CategoryGroupBean();

            item.setCateName("一级分类："+(i+1));

            List<CategoryBaseBean> childList = new ArrayList<>();

            for(int j = 0 ; j < 20 ; j ++){
                CategoryBaseBean itemChild = new CategoryBaseBean();

                itemChild.setCateName("一级分类："+(i+1)+"二级分类："+(j+1));
                childList.add(itemChild);
            }

            item.setCateChildList(childList);

            groupList.add(item);
        }

        for(int i = 0 ; i <3 ; i++){
            CategoryGroupBean item = new CategoryGroupBean();

            item.setCateName("测试单独分类："+(i+1));

            childList.add(item);
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }


    private void initView() {
        View firstBtn = findViewById(R.id.filter_title_f);


        View secondBtn = findViewById(R.id.filter_title_s);


        CategoryItemHelper  firstHelper = new CategoryItemHelper(this,filter,false);
        firstHelper.initData(groupList);

        CategoryItemHelper secondHelper = new CategoryItemHelper(this,filter,true);
        secondHelper.initData(childList);

        CategoryManager manager = new CategoryManager();
        manager.addBindBtnWithCate(firstBtn,firstHelper);
        manager.addBindBtnWithCate(secondBtn,secondHelper);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void selectItem(CategoryGroupBean groupBean, CategoryBaseBean childBean) {
        Log.i("selectitem","CategoryGroupBean:"+ groupBean.toString()+" CategoryBaseBean:"+(childBean!=null?childBean.toString():"null"));
    }
}
