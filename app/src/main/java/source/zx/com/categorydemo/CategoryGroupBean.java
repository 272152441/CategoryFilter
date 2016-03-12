package source.zx.com.categorydemo;

import java.util.List;

/**
 * Description
 *
 * @version 1.0
 *          time 11:20 2016/3/11.
 * @auther zhangxiao
 */
public class CategoryGroupBean extends  CategoryBaseBean {

    private List<CategoryBaseBean> cateChildList;


    public List<CategoryBaseBean> getCateChildList() {
        return cateChildList;
    }

    public void setCateChildList(List<CategoryBaseBean> cateChildList) {
        this.cateChildList = cateChildList;
    }

    public boolean hasChildList(){
        return cateChildList!=null&&cateChildList.size()>0;
    }
}
