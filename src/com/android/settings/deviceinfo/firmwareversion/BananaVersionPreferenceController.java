/*
 * Copyright (C) 2019 The AospExtended Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;

import androidx.annotation.VisibleForTesting;

import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;

public class BananaVersionPreferenceController extends BasePreferenceController {

    @VisibleForTesting
    static final String BANANA_VERSION_PROPERTY = "ro.banana.displayed.version";
    static final String BANANA_RELEASETYPE_PROPERTY = "ro.banana.releasetype";

    public BananaVersionPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        String romVersion = SystemProperties.get(BANANA_VERSION_PROPERTY,
                this.mContext.getString(R.string.device_info_default));
        String romReleasetype = SystemProperties.get(BANANA_RELEASETYPE_PROPERTY,
                this.mContext.getString(R.string.device_info_default));
        if (!romVersion.isEmpty() && !romReleasetype.isEmpty())
            return romVersion + " | " + romReleasetype;
        else
            return mContext.getString(R.string.banana_version_default);
    }
}
