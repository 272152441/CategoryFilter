package source.zx.com.categorydemo;

/**
 * Description
 *
 * @version 1.0
 *          time 15:58 2016/3/11.
 * @auther zhangxiao
 */
public interface CateFilterSelListener {

    // 当选择一个分类过滤条件后执行的回调
    void selectItem(CategoryGroupBean groupBean,CategoryBaseBean childBean);
}
