package source.zx.com.categorydemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class TitleCheckView extends LinearLayout implements Checkable {

    private TextView checkText;

    private ImageView checkAnim;

    private LinearLayout checkLlyt;

    private boolean checkStatus;

    private Animation rotateAnim;

    private Animation rotateRevAnim;


    public TitleCheckView(Context context) {
        super(context);
        initInternalView(context);
    }

    public TitleCheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInternalView(context);
    }

    public TitleCheckView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initInternalView(context);
    }

    /**
     * 初始化內部view
     *
     * @param context
     */
    private void initInternalView(Context context) {

        rotateAnim = AnimationUtils.loadAnimation(context, R.anim.check_rotate);
        rotateRevAnim = AnimationUtils.loadAnimation(context, R.anim.check_rotate_reverse);
        rotateAnim.setFillAfter(true);
        rotateRevAnim.setFillAfter(true);


        View rootView = LayoutInflater.from(context).inflate(R.layout.check_view_layout,
                this, true);
        checkText = (TextView) rootView.findViewById(R.id.check_text);

        checkAnim = (ImageView) rootView.findViewById(R.id.check_anim);

        checkLlyt = (LinearLayout) rootView.findViewById(R.id.check_llyt);

    }

    @Override
    public void setChecked(boolean checked) {
        if(checkStatus!=checked){
            checkStatus = checked;
            checkText.setSelected(checkStatus);
            checkLlyt.setSelected(checkStatus);
//        checkAnim.clearAnimation();
            if(checkStatus){
                checkAnim.startAnimation(rotateAnim);
            }else{
                checkAnim.startAnimation(rotateRevAnim);
            }

        }

    }

    @Override
    public boolean isChecked() {
        return checkStatus;
    }

    @Override
    public void toggle() {
        checkStatus = !checkStatus;
        setChecked(checkStatus);
    }

    /**
     * 设置文字内容
     * @param text
     */
    public void setCheckText(String text){
        checkText.setText(text);
    }
}
