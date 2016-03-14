package source.zx.com.categorydemo;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @version 1.0
 *          time 10:02 2016/3/12.
 * @auther zhangxiao
 */
public class CategoryManager implements View.OnClickListener,CateFilterStatusListener{

    private Map<View,CategoryItemHelper> bindMap = new HashMap<>();

    private Map<CategoryItemHelper,View> bindMapHelp = new HashMap<>();

    public void addBindBtnWithCate(View btn , CategoryItemHelper itemHelper){
        if(btn==null||itemHelper==null){
            throw  new IllegalArgumentException("addBindBtnWithCate argument is null");
        }
        btn.setOnClickListener(this);
        itemHelper.setCateFilterStatusListener(this);
        bindMap.put(btn,itemHelper);
        bindMapHelp.put(itemHelper,btn);
    }


    @Override
    public void onClick(View v) {

        for(View item :bindMap.keySet()){
            CategoryItemHelper itemHelper =bindMap.get(item);
            if(v.getId()==item.getId()){
                if(!itemHelper.isShowCategory()){
                    itemHelper.showCategory();
                }
            }else {
                if(itemHelper.isShowCategory()){
                    itemHelper.hidenCategory();
                }
            }
        }
    }

    private void setBtnSelect(View btn){
        if(btn instanceof TitleCheckView){
            ((TitleCheckView) btn).setChecked(true);
        }
    }

    private void remBtnSelect(View btn){
        if(btn instanceof TitleCheckView){
            ((TitleCheckView) btn).setChecked(false);
        }

    }

    @Override
    public void beforeShowFilter(CategoryItemHelper itemHelper) {
        setBtnSelect(bindMapHelp.get(itemHelper));
    }

    @Override
    public void beforeHidenFilter(CategoryItemHelper itemHelper) {
        remBtnSelect(bindMapHelp.get(itemHelper));
    }
}
