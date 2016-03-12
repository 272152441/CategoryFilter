package source.zx.com.categorydemo;

/**
 * Description
 *
 * @version 1.0
 *          time 10:51 2016/3/11.
 * @auther zhangxiao
 */
public class CategoryBaseBean {

    private String cateName;

    private Object saveItem;


    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Object getSaveItem() {
        return saveItem;
    }

    public void setSaveItem(Object saveItem) {
        this.saveItem = saveItem;
    }
}
