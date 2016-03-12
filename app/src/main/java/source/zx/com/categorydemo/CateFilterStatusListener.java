package source.zx.com.categorydemo;

/**
 * Description
 *
 * @version 1.0
 *          time 10:26 2016/3/12.
 * @auther zhangxiao
 */
public interface CateFilterStatusListener {

    void beforeShowFilter(CategoryItemHelper itemHelper);

    void beforeHidenFilter(CategoryItemHelper itemHelper);
}
