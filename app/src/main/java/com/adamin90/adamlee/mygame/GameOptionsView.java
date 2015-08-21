package com.adamin90.adamlee.mygame;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.adamin90.adamlee.mygame.mvp.ViewAttacher;
import com.adamin90.adamlee.mygame.util.ElasticEvaluator;
import com.adamin90.adamlee.mygame.util.TintUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/8/21.
 */
public class GameOptionsView extends RelativeLayout {
    private static final TypeEvaluator b;
    Presenter a;
    private final int c;
    @InjectView(R.id.game_options_close)
    ImageView close;
    private final int d;
    private final int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private AnimatorSet l;
    private AnimatorSet m;
    private AnimatorSet n;
    @InjectView(R.id.game_options_next)
    ImageView next;
    private AnimatorSet o;
    @InjectView(R.id.game_options_previous)
    ImageView previous;
    @InjectView(R.id.game_options_screenshot)
    ImageView screenshot;
    @InjectView(R.id.game_options_sound_container)
    View soundContainer;
    @InjectView(R.id.game_options_sound_inner)
    ImageView soundInner;
    @InjectView(R.id.game_options_sound_middle)
    ImageView soundMiddle;
    @InjectView(R.id.game_options_sound_outer)
    ImageView soundOuter;

    interface Presenter extends ViewAttacher<GameOptionsView> {
        boolean isMenuExpanded();

        boolean isNextEnabled();

        boolean isPreviousEnabled();

        boolean isSoundEnabled();

        void nextLevel();

        void previousLevel();

        void screenshot();

        void toggleMenu();

        void toggleSound();
    }

    /* renamed from: com.balysv.loop.GameOptionsView.5 */
    class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ View a;
        final /* synthetic */ GameOptionsView b;

        AnonymousClass5(GameOptionsView gameOptionsView, View view) {
            this.b = gameOptionsView;
            this.a = view;
        }

        public void onAnimationStart(Animator animator) {
            this.a.setSelected(true);
        }

        public void onAnimationEnd(Animator animator) {
            this.a.setSelected(false);
        }
    }

    static {
        b = new ArgbEvaluator();
    }

    public GameOptionsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = -1;
        this.g = -1;
        this.a = GameOptionsPresenter.a(getContext());
        this.c = getResources().getDimensionPixelSize(R.dimen.options_item_size);
        m=new AnimatorSet();
        n=new AnimatorSet();
        o=new AnimatorSet();
        this.e = this.c + (getResources().getDimensionPixelSize(R.dimen.options_close_spacing) / 2);
        this.d = (int) ((((float) this.c) * 0.8f) + ((float) getResources().getDimensionPixelSize(R.dimen.options_item_spacing)));
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject((View) this);
//        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a.attachView(this);
    }

    protected void onDetachedFromWindow() {
        this.a.detachView(this);
        this.n = new AnimatorSet();
        this.m = new AnimatorSet();
        super.onDetachedFromWindow();
    }

    @OnClick({R.id.game_options_next, R.id.game_options_previous})
    public void onClickArrow(View view) {
        if (view.isEnabled() && !view.isSelected()) {
            a(view);
            if (view == this.next) {
                this.a.nextLevel();
            } else {
                this.a.previousLevel();
            }
        }
    }

    @OnClick({R.id.game_options_sound_container})
    public void onClickSoundToggle(View view) {
        this.a.toggleSound();
        a(view);
        a(this.a.isSoundEnabled());
    }

    @OnClick({R.id.game_options_close})
    public void onClickClose() {
        if (this.a.isMenuExpanded()) {
            this.a.toggleMenu();
        }
    }

    @OnClick({R.id.game_options_open})
    public void onClickOpen() {
        if (!this.a.isMenuExpanded() && !this.k) {
            this.a.toggleMenu();
        }
    }

    @OnClick({R.id.game_options_screenshot})
    public void onClickScreenshot() {
        this.a.screenshot();
    }

    void setOptionsColor(int i) {
        this.j = Color.HSVToColor(new float[]{(float) i, 0.85f, 0.97f});
        TintUtils.tintImages(Arrays.asList(new ImageView[]{this.screenshot}), Integer.valueOf(this.j));
        this.h = Color.HSVToColor(new float[]{(float) i, 0.1f, 0.91f});
        this.i = Color.HSVToColor(new float[]{(float) i, 0.42f, 0.58f});
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(500);
        valueAnimator.setObjectValues(new Object[]{Integer.valueOf(this.f), Integer.valueOf(this.h)});
        valueAnimator.setEvaluator(b);
//        valueAnimator.addUpdateListener(GameOptionsView$$Lambda$1.a(this));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                c(animation);
            }
        });
        ValueAnimator valueAnimator2 = new ValueAnimator();
        valueAnimator2.setDuration(500);
        valueAnimator2.setObjectValues(new Object[]{Integer.valueOf(this.g), Integer.valueOf(this.i)});
        valueAnimator2.setEvaluator(b);
//        valueAnimator2.addUpdateListener(GameOptionsView$$Lambda$2.a(this));
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                b(animation);
            }
        });
        if (this.a.isMenuExpanded()) {
            valueAnimator.start();
        }
        valueAnimator2.start();
        this.f = this.h;
        this.g = this.i;
    }

    private /* synthetic */ void c(ValueAnimator valueAnimator) {
        TintUtils.tintImages(Arrays.asList(new ImageView[]{this.next, this.previous, this.soundInner, this.soundMiddle, this.soundOuter}), Integer.valueOf(((Integer) valueAnimator.getAnimatedValue()).intValue()));
    }

    private /* synthetic */ void b(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        TintUtils.tintImages(Arrays.asList(new ImageView[]{this.close}), Integer.valueOf(intValue));
        TintUtils.tintBackground(Arrays.asList(new View[]{this.next, this.previous, this.soundContainer}), intValue);
    }

    void a(boolean z, boolean z2) {
        int i = 50;
        this.m.end();
        this.n.end();
        this.o.end();
        if (z2) {
            int i2;
            this.o = new AnimatorSet();
            AnimatorSet animatorSet = this.o;
            Animator[] animatorArr = new Animator[3];
            animatorArr[0] = a(this.soundContainer, z, true, z ? 0 : 50);
            View view = this.next;
            boolean isNextEnabled = this.a.isNextEnabled();
            if (z) {
                i2 = 50;
            } else {
                i2 = 0;
            }
            animatorArr[1] = a(view, z, isNextEnabled, i2);
            view = this.previous;
            isNextEnabled = this.a.isPreviousEnabled();
            if (!z) {
                i = 0;
            }
            animatorArr[2] = a(view, z, isNextEnabled, i);
            animatorSet.playTogether(animatorArr);
            if (z) {
                this.m.start();
            } else {
                this.n.start();
            }
            this.o.start();
            return;
        }
        setMenuExpanded(z);
    }

    void setMenuExpanded(boolean z) {
        float f;
        int i = 0;
        float f2 = 0.125f;
        float f3 = 0.0f;
        float f4 = 1.0f;
        List asList = Arrays.asList(new ImageView[]{this.next, this.previous, this.soundInner, this.soundMiddle, this.soundOuter});
        if (z) {
            i = this.h;
        }
        TintUtils.tintImages(asList, Integer.valueOf(i));
        this.close.setTranslationY(z ? 0.0f : (float) this.c);
        this.soundContainer.setTranslationY(z ? 0.0f : (float) this.e);
        View view = this.soundContainer;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.125f;
        }
        view.setScaleX(f);
        view = this.soundContainer;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.125f;
        }
        view.setScaleY(f);
        view = this.soundContainer;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.2f;
        }
        view.setAlpha(f);
        this.next.setTranslationX(z ? 0.0f : (float) (-this.d));
        this.next.setTranslationY(z ? 0.0f : (float) this.e);
        ImageView imageView = this.next;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.125f;
        }
        imageView.setScaleX(f);
        imageView = this.next;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.125f;
        }
        imageView.setScaleY(f);
        imageView = this.next;
        if (z && this.a.isNextEnabled()) {
            f = 1.0f;
        } else {
            f = 0.2f;
        }
        imageView.setAlpha(f);
        this.previous.setTranslationX(z ? 0.0f : (float) this.d);
        ImageView imageView2 = this.previous;
        if (!z) {
            f3 = (float) this.e;
        }
        imageView2.setTranslationY(f3);
        ImageView imageView3 = this.previous;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.125f;
        }
        imageView3.setScaleX(f);
        imageView2 = this.previous;
        if (z) {
            f2 = 1.0f;
        }
        imageView2.setScaleY(f2);
        imageView2 = this.previous;
        if (!(z && this.a.isPreviousEnabled())) {
            f4 = 0.2f;
        }
        imageView2.setAlpha(f4);
    }

    void b(boolean z, boolean z2) {
        float f = 1.0f;
        if (this.a.isMenuExpanded()) {
            float f2;
            this.previous.setEnabled(z);
            ViewPropertyAnimator animate = this.previous.animate();
            if (z) {
                f2 = 1.0f;
            } else {
                f2 = 0.2f;
            }
            animate.alpha(f2).setDuration(150).start();
            this.next.setEnabled(z2);
            ViewPropertyAnimator animate2 = this.next.animate();
            if (!z2) {
                f = 0.2f;
            }
            animate2.alpha(f).setDuration(150).start();
        }
    }

    public void setScreenshotVisible(boolean z) {
        // if visible
        if (!z && this.screenshot.getVisibility() ==View.VISIBLE) {
            this.screenshot.animate().alpha(0.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {

                public void onAnimationEnd(Animator animator) {
                    screenshot.setVisibility(View.INVISIBLE);
                }
            }).start();
        }
        else if (this.screenshot.getVisibility() ==View.INVISIBLE) {
            this.screenshot.setVisibility(View.VISIBLE);
            this.screenshot.animate().alpha(1.0f).setDuration(200).setListener(null).start();
        }
//        ButterKnife.apply(Arrays.asList(new View[]{this.soundContainer, this.next, this.previous}), GameOptionsView$$Lambda$3.a());
        ButterKnife.apply(Arrays.asList(new View[]{this.soundContainer, this.next, this.previous}), new ButterKnife.Action<View>() {
            @Override
            public void apply(View view, int index) {
                view.setVisibility(View.INVISIBLE);
            }
        });
        if (z) {
            this.k = true;
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 0.2f});
//        ofFloat.addUpdateListener(GameOptionsView$$Lambda$4.a(this));
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                a(animation);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() {

            public void onAnimationStart(Animator animator) {
                k = true;
//                ButterKnife.apply(Arrays.asList(new View[]{this.a.soundContainer, this.a.next, this.a.previous}), GameOptionsView$2$$Lambda$1.a());
                ButterKnife.apply(Arrays.asList(new View[]{soundContainer, next, previous}), new ButterKnife.Action<View>() {
                    @Override
                    public void apply(View view, int index) {
                        view.setVisibility(View.VISIBLE);
                    }
                });
            }

            public void onAnimationEnd(Animator animator) {
                k = false;
            }
        });
        ofFloat.setStartDelay(1200);
        ofFloat.setDuration(500);
        ofFloat.start();
    }

    private /* synthetic */ void a(final ValueAnimator valueAnimator) {
//        ButterKnife.apply(Arrays.asList(new View[]{this.soundContainer, this.next, this.previous}), GameOptionsView$$Lambda$6.a(valueAnimator));
        ButterKnife.apply(Arrays.asList(new View[]{this.soundContainer, this.next, this.previous}), new ButterKnife.Action<View>() {
            @Override
            public void apply(View view, int index) {
                view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());

            }
        });
    }

    void setSoundEnabled(final boolean z) {
//        ButterKnife.apply(Arrays.asList(new ImageView[]{this.soundInner, this.soundMiddle, this.soundOuter}), GameOptionsView$$Lambda$5.a(z ? 1.0f : 0.2f));
        ButterKnife.apply(Arrays.asList(new ImageView[]{this.soundInner, this.soundMiddle, this.soundOuter}), new ButterKnife.Action<ImageView>() {
            @Override
            public void apply(ImageView view, int index) {
                ((ImageView) view).setAlpha(z ? 1.0f : 0.2f);


            }
        });
    }

    private void a() {
        this.m = new AnimatorSet();
        this.n = new AnimatorSet();
        this.o = new AnimatorSet();
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.close, View.TRANSLATION_Y, new float[]{(float) this.c}).setDuration(250);
        duration.setInterpolator(new AccelerateInterpolator());
        this.n.playTogether(new Animator[]{a(this.soundContainer, false, 0, this.e, 50), a(this.next, false, -this.d, this.e, 0), a(this.previous, false, this.d, this.e, 0), duration});
        this.n.addListener(new AnimatorListenerAdapter() {


            public void onAnimationStart(Animator animator) {
                setOptionItemsClickable(false);
            }

            public void onAnimationEnd(Animator animator) {
                TintUtils.tintImages(Arrays.asList(new ImageView[]{next, previous, soundInner, soundMiddle, soundOuter}), Integer.valueOf(0));
            }
        });
      ObjectAnimator r7=  ObjectAnimator.ofFloat(this.close, View.TRANSLATION_Y, new float[]{0.0f});
        r7.setDuration(250);
        r7.setStartDelay(50);
        duration.setInterpolator(new DecelerateInterpolator(3.0f));
        this.m.playTogether(new Animator[]{a(this.soundContainer, true, 0, 0, 0), a(this.next, true, 0, 0, 50), a(this.previous, true, 0, 0, 50), r7});
        this.m.addListener(new AnimatorListenerAdapter() {

            public void onAnimationStart(Animator animator) {
                TintUtils.tintImages(Arrays.asList(new ImageView[]{next, previous, soundInner, soundMiddle, soundOuter}), Integer.valueOf(h));
            }

            public void onAnimationEnd(Animator animator) {
                setOptionItemsClickable(true);
            }
        });
    }

    private ObjectAnimator a(View view, boolean z, boolean z2, int i) {
        Property property = View.ALPHA;
        float[] fArr = new float[1];
        float f = (z && z2) ? 1.0f : 0.2f;
        fArr[0] = f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, property, fArr);
        ofFloat.setEvaluator(new ElasticEvaluator(400.0f));
        ofFloat.setDuration(400);
        ofFloat.setStartDelay((long) i);
        return ofFloat;
    }

    private ObjectAnimator a(View view, boolean z, int i, int i2, int i3) {
        float f;
        float f2 = 1.0f;
        float f3 = 0.0f;
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[4];
        Property property = View.TRANSLATION_Y;
        float[] fArr = new float[1];
        fArr[0] = z ? 0.0f : (float) i2;
        propertyValuesHolderArr[0] = PropertyValuesHolder.ofFloat(property, fArr);
        Property property2 = View.TRANSLATION_X;
        float[] fArr2 = new float[1];
        if (!z) {
            f3 = (float) i;
        }
        fArr2[0] = f3;
        propertyValuesHolderArr[1] = PropertyValuesHolder.ofFloat(property2, fArr2);
        property = View.SCALE_X;
        fArr = new float[1];
        if (z) {
            f = 1.0f;
        } else {
            f = 0.125f;
        }
        fArr[0] = f;
        propertyValuesHolderArr[2] = PropertyValuesHolder.ofFloat(property, fArr);
        Property property3 = View.SCALE_Y;
        fArr2 = new float[1];
        if (!z) {
            f2 = 0.125f;
        }
        fArr2[0] = f2;
        propertyValuesHolderArr[3] = PropertyValuesHolder.ofFloat(property3, fArr2);
        List<PropertyValuesHolder> asList = Arrays.asList(propertyValuesHolderArr);
        for (PropertyValuesHolder evaluator : asList) {
            evaluator.setEvaluator(new ElasticEvaluator(400.0f));
        }
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, (PropertyValuesHolder[]) asList.toArray());
        ofPropertyValuesHolder.setDuration(400);
        ofPropertyValuesHolder.setStartDelay((long) i3);
        return ofPropertyValuesHolder;
    }

    private void a(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] animatorArr = new Animator[2];
        PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[2];
        propertyValuesHolderArr[0] = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{0.8f});
        propertyValuesHolderArr[1] = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.8f});
        animatorArr[0] = ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolderArr).setDuration(100);
        propertyValuesHolderArr = new PropertyValuesHolder[2];
        propertyValuesHolderArr[0] = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{1.0f});
        propertyValuesHolderArr[1] = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{1.0f});
        animatorArr[1] = ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolderArr).setDuration(150);
        animatorSet.playSequentially(animatorArr);
        animatorSet.addListener(new AnonymousClass5(this, view));
        animatorSet.start();
    }

    private void setOptionItemsClickable(boolean z) {
        boolean z2;
        boolean z3 = true;
        this.soundContainer.setEnabled(z);
        ImageView imageView = this.next;
        if (this.a.isNextEnabled() && z) {
            z2 = true;
        } else {
            z2 = false;
        }
        imageView.setEnabled(z2);
        ImageView imageView2 = this.previous;
        if (!(this.a.isPreviousEnabled() && z)) {
            z3 = false;
        }
        imageView2.setEnabled(z3);
    }

    private void a(boolean z) {
        Object obj = null;
        if (this.l != null) {
            this.l.end();
        }
        float f = z ? 1.0f : 0.2f;
        if (z) {
            obj = this.soundInner;
        } else {
            ImageView imageView = this.soundOuter;
        }
        Object obj2 = z ? this.soundOuter : this.soundInner;
      ObjectAnimator objectAnimator1=  ObjectAnimator.ofFloat((View) obj, View.ALPHA, new float[]{f}).setDuration(200);
        ObjectAnimator objectAnimator2=  ObjectAnimator.ofFloat(this.soundMiddle, View.ALPHA, new float[]{f});
        objectAnimator2.setDuration(200);
        objectAnimator2.setStartDelay(100);
       ObjectAnimator objectAnimator3= ObjectAnimator.ofFloat((View) obj2, View.ALPHA, new float[]{f});
        objectAnimator3.setDuration(200);
        objectAnimator3.setStartDelay(200);
        this.l = new AnimatorSet();
        this.l.playTogether(new Animator[]{objectAnimator1, objectAnimator2, objectAnimator3});
        this.l.start();
    }


    final /* synthetic */ class GameOptionsViewlambda1 implements ValueAnimator.AnimatorUpdateListener {
        private final GameOptionsView a;

        private GameOptionsViewlambda1(GameOptionsView gameOptionsView) {
            this.a = gameOptionsView;
        }


        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.a.c(valueAnimator);
        }
    }
}
