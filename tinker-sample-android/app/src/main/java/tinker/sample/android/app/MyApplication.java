package tinker.sample.android.app;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class MyApplication extends TinkerApplication {
    private static final String THINKER_APPLICATION_LIKE = "tinker.sample.android.app.SampleApplicationLike";
    public MyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, THINKER_APPLICATION_LIKE,
                "com.tencent.tinker.loader.TinkerLoader", false, false);
    }
}
