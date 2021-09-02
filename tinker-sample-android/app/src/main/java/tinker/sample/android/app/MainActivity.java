/*
 * Tencent is pleased to support the open source community by making Tinker available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tinker.sample.android.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

import tinker.sample.android.R;
import tinker.sample.android.util.FileSDCardUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Tinker.MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bt_main_showlog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"我是BUG");
                Toast.makeText(MainActivity.this,"我是BUG",Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.bt_main_load_local_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = FileSDCardUtil.getInstance().getSandboxPublickDiskTinkerCacheDir(MainActivity.this,"patch_signed_7zip.apk");
                File file = new File(path);
                if (file.exists()){
                    TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), file.getAbsolutePath());
                }
            }
        });
    }

}
